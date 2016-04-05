package symbolTableBuilder;

/* 
 * A Visitor which builds symbol tables for a syntax tree. Routes through a program and as identifiers are created, they are bound and added to the Symbol Tree.
 * Provides no functionality other than creating a tree for now.
 * Semantic phase, part 1
 * 
 * @author ()
 * @version (Mar 2016)
 */
/*
 * Need to go through each method and determine if a symbol table is needed or not, and if so, what is it's scope.
 * Notes:
 * if it's an identifier, always add it to the program table along with it's IdType.
 * I assume you should add it to the current symTab as well but I'm not sure.
 * Type checking is commented out atm since all we need is symbol tables but it's a good idea to know where to type check
 * Need to check types, expressions, and statements.
 * I think for the base cases, like integers, identifiers, etc, we don't need to process anything yet
 * 
 */

public class BuildST implements Visitor {
	public SymbolTable symTab, symTabMethod, symTabClass, symTabProg;
	// I think it works like this:
	// symTab is the table in focus
	// symTabMethod is the table created when a method is created
	// symTabClass is the table created when a class is created
	// symTabProg is the table created at the time the program is
	//
	// This class

	public BuildST() {
//Temp
		//symTabProg = new SymbolTable();
	}

	public void visit(Program n) { // Program as defined in Bergmann's parser
		symTab = symTabProg = new SymbolTable();
		n.mainClass.accept(this);
		n.classDecls.accept(this);
		System.out.println("Program Table: \n" + symTabProg);
	}

	public void visit(MainClass n) {
		symTabProg.put(n.className, new Binding(n.className, IdType.CLASS));
		n.className.accept(this);
		n.args.accept(this);
		symTab = symTabClass = n.symTab = new SymbolTable(); // each mainclass has it's own symboltable since it is static
		n.v.accept(this);
		n.stmt.accept(this); // ??
		
		
		System.out.println("MainClass " + n.className.name +" Table: \n" + n.symTab);
	}

	// everytime we put a new symbol into the symboltable, we have to create a
	// new binding for it
	public void visit(ClassDeclDeffSimple n) {
		symTabProg.put(n.className, new Binding(n.className, IdType.CLASS));
		symTab = symTabClass = n.symTab = new SymbolTable(); 
		n.className.accept(this);
		n.fields.accept(this); // enter fields into the symbol table
		n.methods.accept(this);
		
		System.out.println("Simple Class " + n.className.name +" Table: \n" + n.symTab);
	}

	/*
	 *  public void visit(ClassDeclSpec n) {
	 * symTabProg.put(n.className, new Binding(n.className, IdType.CLASS));
	 * n.className.accept(this); }
	 */

	
	public void visit(ClassDeclDeffExtend n) {
		symTab = symTabClass = n.symTab = new SymbolTable(); // class needs a symbol table
		
		symTabProg.put(n.className, new Binding(n.className, IdType.CLASS));
		n.className.accept(this);

		symTabProg.put(n.extendedClass, new Binding(n.extendedClass, IdType.CLASS));
		n.extendedClass.accept(this);

		
		System.out.println("Extended Class " + n.className.name +" Table: \n" + n.symTab);
		n.variableList.accept(this);

	}

	
	public void visit(ClassDeclList n) {
		for(ClassDecl c: n)
		{
			if(c instanceof ClassDeclDeffSimple)
			{
				((ClassDeclDeffSimple)c).accept(this);
				//visit((ClassDeclDeffSimple)c);
			}
			
			if(c instanceof ClassDeclDeffExtend)
			{
				((ClassDeclDeffExtend)c).accept(this);
				//visit((ClassDeclDeffExtend)c);
			}
			
			//visit(c);
		}
	}

	
	public void visit(VarDecl n) {
		Type t = n.type;
		t.accept(this);
		n.variableType.accept(this, t);
		
	}

	
	public void visit(VarDeclType n, Type t) {
		n.variableName.accept(this);
		symTab.put(n.variableName, new Binding(n.variableName, IdType.VARIABLE, t.getClass().getSimpleName()));
		
		if(!(n.variableAssign.exp == null))
			n.variableAssign.accept(this);
	}

	
	public void visit(VarDeclTypeAssign n) {
		Exp e = n.exp;
		e.accept(this);	
	}

	
	public void visit(MethodDecl n) {
		Binding bind = new Binding(n.methodName, IdType.METHOD, n.type.getClass().getSimpleName());
		bind.addParams(n.parameters);
		symTabClass.put(n.methodName, bind);
		//symTab.put(n.methodName, bind);
		symTab = symTabMethod = n.symTab = new SymbolTable(); 
		n.type.accept(this);
		n.methodName.accept(this);
		n.parameters.accept(this);
		n.variables.accept(this);
		n.statements.accept(this);
		Exp e = n.expReturn;
		e.accept(this);
		
		System.out.println("Method " + n.methodName.name +" Table: \n" + n.symTab);
		
	}

	
	public void visit(FormalList n) {
		Type t = n.type;
		t.accept(this);	
		symTab.put(n.parameterName, new Binding(n.parameterName, IdType.VARIABLE, t.getClass().getSimpleName()));
		n.parameterName.accept(this);
		n.moreParams.accept(this);	
	}

	
	public void visit(FormalRest n) {
		Type t = n.type;
		t.accept(this);
		symTab.put(n.paramName, new Binding(n.paramName, IdType.VARIABLE, t.getClass().getSimpleName()));
		n.paramName.accept(this);
	}
	
	
	public void visit(Block n) {
		n.sl.accept(this);
	}

	
	public void visit(If n) {
		n.condition.accept(this);
		n.s.accept(this);
		n.elseIf.accept(this);
	}

	
	public void visit(Do n) {
		n.s.accept(this);
		n.condition.accept(this);
	}

	
	public void visit(While n) {
		n.condition.accept(this);
		n.s.accept(this);
	}

	
	public void visit(For n) {
		n.initialize.accept(this); 
		n.e.accept(this);
		n.increment.accept(this);
		n.s.accept(this);
	}

	
	public void visit(Switch n) {
		symTab.put(n.id, new Binding(n.id, IdType.VARIABLE));
		n.id.accept(this);
		n.caseDefault.accept(this);
	}

	
	public void visit(Print n) {
		n.statementToPrint.accept(this);
	}

	
	public void visit(AssignSimple n) {
		n.assignment.accept(this);
	}

	
	public void visit(AssignArray n) {
		n.arrayExp.accept(this);
		n.assignExp.accept(this);	
	}

	
	public void visit(InitializeSimple n) {
		symTab.put(n.id, new Binding(n.id, IdType.VARIABLE));
		n.id.accept(this);
		n.assignExp.accept(this);
	}

	
	public void visit(InitializeArray n) {
		symTab.put(n.id, new Binding(n.id, IdType.VARIABLE));
		n.id.accept(this);
		n.arrayExp.accept(this);
		n.assignExp.accept(this);
	}

	
	public void visit(IncrementSimple n) {
		symTab.put(n.id, new Binding(n.id, IdType.VARIABLE));
		n.id.accept(this);
		n.assignExp.accept(this);
	}

	
	public void visit(IncrementArray n) {
		symTab.put(n.id, new Binding(n.id, IdType.VARIABLE));
		n.id.accept(this);
		n.arrayExp.accept(this);
		n.assignExp.accept(this);
	}

	
	public void visit(ElseIf n) {
		n.condition.accept(this);
		n.s.accept(this);
	}

	
	public void visit(CaseListCase n) {
		n.caseExp.accept(this);
		n.s.accept(this);
		n.caseList.accept(this);
	}

	
	public void visit(CaseListDefault n) {
		n.s.accept(this);
	}

	
	public void visit(ExpList n) {
		n.e.accept(this);
		n.multipleExp.accept(this);
	}

	
	public void visit(ExpRest n) {
		n.e.accept(this);
	}

	
	public void visit(Alist n) {
		n.alist.accept(this);
		n.less.accept(this);
		
	}

	
	public void visit(ClassDecl n) {
		//does nothing
	}

	
	public void visit(VarDeclList n) {
		for(VarDecl v: n)
		{
			v.accept(this);
			//visit(v);
		}
	}

	
	public void visit(MethodDeclList n) {
		for(MethodDecl m: n)
		{
			m.accept(this);
			//visit(m);
		}
	}

	
	public void visit(IntArrayType n) {
		//does nothing
	}

	
	public void visit(BooleanType n) {
		//does nothing
		
	}

	
	public void visit(IntegerType n) {
		//does nothing
		
	}

	
	public void visit(IdentifierType n) {
		//does nothing
	}

	
	public void visit(Statement n) {
		//does nothing
		
	}

	
	public void visit(And n) {
		n.less.accept(this);
		n.alist.accept(this);
	}

	
	public void visit(IntegerLiteral n) {
		//does nothing
		
	}

	
	public void visit(True n) {
		//does nothing
		
	}

	
	public void visit(False n) {
		//does nothing
		
	}

	
	public void visit(IdentifierExp n) {
		//does nothing
		
	}

	
	public void visit(This n) {
		//does nothing
		
	}

	
	public void visit(NewArray n) {
		n.e.accept(this);
	}

	
	public void visit(NewObject n) {
		symTab.put(n.id, new Binding(n.id, IdType.CLASS));
		n.id.accept(this);
		
	}

	
	public void visit(Not n) {
		//does nothing
	}

	
	public void visit(Identifier n) {
		//does nothing
	}

	
	public void visit(Exp n) {
		//does nothing
		
	}

	
	public void visit(Type n) {
		//does nothing
	}

	
	public void visit(StatementList n) {
		for(Statement s: n){
			visit(s);
		}
		
	}

	
	public void visit(InitializationStm n) {
		//does nothing
		
	}

	
	public void visit(IncrementStm n) {
		// does nothing
		
	}

	
	public void visit(CaseList n) {
		// does nothing
		
	}

	
	public void visit(FormalRestList formalRestList) {
		for(FormalRest f: formalRestList){
			f.accept(this);
			//visit(f);
		}
		
	}

	
	public void visit(ElseIfList elseIfList) {
		for(ElseIf e: elseIfList){
			e.accept(this);
			//visit(e);
		}
	}

	
	public void visit(ExpRestList expRestList) {
		for(ExpRest e: expRestList){
			e.accept(this);
			//visit(e);
		}
	}

	
	public void visit(DotArrayList dotArrayList) {
		for(DotArray e: dotArrayList){
			if(e instanceof DotArrayArray){
			((DotArrayArray)e).accept(this);
			}
			else if (e instanceof DotArrayMember){
				((DotArrayMember)e).accept(this);
			}
			//visit(e);
		}
		
	}

	
	public void visit(DotArray n) {
		// does nothing
		
	}

	
	public void visit(DotArrayArray n) {
		n.exp.accept(this);
		
	}

	
	public void visit(DotArrayMember n) {
		n.member.accept(this);
	}

	
	public void visit(Elist n) {
		n.and.accept(this);
		n.elist.accept(this);
	}

	
	public void visit(Factor n) {
		//does nothing
	}

	
	public void visit(FactorNew n) {
		n.newObject.accept(this);
		
	}

	
	public void visit(Less n) {
		n.llist.accept(this);
		n.term.accept(this);
		
	}

	
	public void visit(Llist n) {
		//does nothing
		
	}

	
	public void visit(LlistDifference n) {
		n.llist.accept(this);
		n.term.accept(this);
	}

	
	public void visit(LlistSum n) {
		n.llist.accept(this);
		n.term.accept(this);
		
	}

	
	public void visit(Member n) {
		// does nothing
		
	}

	
	public void visit(MemberId n) {
		Binding bind =new Binding(n.id, IdType.METHOD);
		bind.addParams(n.expList);
		symTab.put(n.id, bind);
		n.id.accept(this);
		n.expList.accept(this);
	}

	
	public void visit(MemberLength n) {
		// does nothing
		
	}

	
	public void visit(New n) {
		// does nothing
	}

	
	public void visit(NotFactor n) {
		n.factor.accept(this);
		n.dotList.accept(this);
		
	}

	
	public void visit(NotSimple n) {
		n.not.accept(this);
		
	}

	
	public void visit(Term n) {
		n.not.accept(this);
		n.tlist.accept(this);
		
	}

	
	public void visit(Tlist n) {
		n.not.accept(this);
		n.tlist.accept(this);
		
	}
}
	
	