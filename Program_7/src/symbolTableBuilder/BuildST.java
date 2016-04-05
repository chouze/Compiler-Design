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
	SymbolTable symTab, symTabMethod, symTabClass, symTabProg;
	// I think it works like this:
	// symTab is the table in focus
	// symTabMethod is the table created when a method is created
	// symTabClass is the table created when a class is created
	// symTabProg is the table created at the time the program is
	//
	// This class

	public BuildST() {
//Temp
		symTabProg = new SymbolTable();
	}

	public void visit(Program n) { // Program as defined in Bergmann's parser
		symTab = symTabProg = new SymbolTable();
		n.mainClass.accept(this);
		n.classDecls.accept(this);
	}

	public void visit(MainClass n) {
		symTabProg.put(n.className, new Binding(n.className, IdType.CLASS));
		n.className.accept(this);
		symTabProg.put(n.args, new Binding(n.args, IdType.VARIABLE));
		n.args.accept(this);
		n.symTab = new SymbolTable(); // each mainclass has it's own symboltable since it is static
		n.stmt.accept(this); // ??
	}

	// everytime we put a new symbol into the symboltable, we have to create a
	// new binding for it
	public void visit(ClassDeclDeffSimple n) {
		symTabProg.put(n.className, new Binding(n.className, IdType.CLASS));
		n.className.accept(this);
		symTab = symTabClass = n.symTab = new SymbolTable(); 
		n.fields.accept(this); // enter fields into the symbol table
		n.methods.accept(this);
	}

	/*
	 * @Override public void visit(ClassDeclSpec n) {
	 * symTabProg.put(n.className, new Binding(n.className, IdType.CLASS));
	 * n.className.accept(this); }
	 */

	@Override
	public void visit(ClassDeclDeffExtend n) {
		symTabProg.put(n.className, new Binding(n.className, IdType.CLASS));
		n.className.accept(this);

		symTabProg.put(n.extendedClass, new Binding(n.extendedClass, IdType.CLASS));
		n.extendedClass.accept(this);

		symTab = symTabClass = n.symTab = new SymbolTable(); // class needs a symbol table

		n.variableList.accept(this);

	}

	@Override
	public void visit(ClassDeclList n) {
		// not sure what to do here, it's essentially for ClassDecl*, do I accept ClassDecl multiple times? or call visit(ClassDecl) multiple times?	
	}

	@Override
	public void visit(VarDecl n) {
		Type t = n.type;
		t.accept(this);
		n.variableType.accept(this);
		
	}

	@Override
	public void visit(VarDeclType n) {
		symTabProg.put(n.variableName, new Binding(n.variableName, IdType.VARIABLE));
		n.variableName.accept(this);
		n.variableAssign.accept(this);
	}

	@Override
	public void visit(VarDeclTypeAssign n) {
		Exp e = n.exp;
		e.accept(this);	
	}

	@Override
	public void visit(MethodDecl n) {
		Type t = n.type;
		t.accept(this);
		symTabProg.put(n.methodName, new Binding(n.methodName, IdType.METHOD));
		n.methodName.accept(this);
		n.parameters.accept(this);
		n.variables.accept(this);
		n.statements.accept(this);
		Exp e = n.expReturn;
		//if(!(e instanceof "same class as t" )){error} throw error if the return type isnt the same as the type declared in the header
		e.accept(this);
	}

	@Override
	public void visit(FormalList n) {
		Type t = n.type;
		t.accept(this);	
		symTabProg.put(n.parameterName, new Binding(n.parameterName, IdType.VARIABLE));
		n.parameterName.accept(this);
		n.moreParams.accept(this);	
	}

	@Override
	public void visit(FormalRest n) {
		Type t = n.type;
		t.accept(this);
		symTabProg.put(n.paramName, new Binding(n.paramName, IdType.VARIABLE));
		n.paramName.accept(this);
	}
	
	@Override
	public void visit(Block n) {
		n.sl.accept(this);
	}

	@Override
	public void visit(If n) {
		//if(!(n.condition instanceof booleanExp)){error} may need booleanExp type
		n.condition.accept(this);
		n.s.accept(this);
		n.elseIf.accept(this);
	}

	@Override
	public void visit(Do n) {
		n.s.accept(this);
		//if(!(n.condition instanceof booleanExp)){error} may need booleanExp type
		n.condition.accept(this);
	}

	@Override
	public void visit(While n) {
		//if(!(n.condition instanceof booleanExp)){error} may need booleanExp type
		n.condition.accept(this);
		n.s.accept(this);
	}

	@Override
	public void visit(For n) {
		n.initialize.accept(this);
		//need to check expression type 
		n.e.accept(this);
		n.increment.accept(this);
		n.s.accept(this);
	}

	@Override
	public void visit(Switch n) {
		symTabProg.put(n.id, new Binding(n.id, IdType.VARIABLE));
		n.id.accept(this);
		n.caseDefault.accept(this);
	}

	@Override
	public void visit(Print n) {
		//not sure if have to type check expression here
		n.statementToPrint.accept(this);
	}

	@Override
	public void visit(AssignSimple n) {
		n.assignment.accept(this);
	}

	@Override
	public void visit(AssignArray n) {
		n.arrayExp.accept(this);
		//type check assignExp for integer
		n.assignExp.accept(this);	
	}

	@Override
	public void visit(InitializeSimple n) {
		symTabProg.put(n.id, new Binding(n.id, IdType.VARIABLE));
		n.id.accept(this);
		n.assignExp.accept(this);
	}

	@Override
	public void visit(InitializeArray n) {
		symTabProg.put(n.id, new Binding(n.id, IdType.VARIABLE));
		n.id.accept(this);
		n.arrayExp.accept(this);
		n.assignExp.accept(this);
	}

	@Override
	public void visit(IncrementSimple n) {
		symTabProg.put(n.id, new Binding(n.id, IdType.VARIABLE));
		n.id.accept(this);
		n.assignExp.accept(this);
	}

	@Override
	public void visit(IncrementArray n) {
		symTabProg.put(n.id, new Binding(n.id, IdType.VARIABLE));
		n.id.accept(this);
		n.arrayExp.accept(this);
		n.assignExp.accept(this);
	}

	@Override
	public void visit(ElseIf n) {
		n.condition.accept(this);
		n.s.accept(this);
	}

	@Override
	public void visit(CaseListCase n) {
		n.caseExp.accept(this);
		n.s.accept(this);
		n.caseList.accept(this);
	}

	@Override
	public void visit(CaseListDefault n) {
		n.s.accept(this);
	}

	@Override
	public void visit(ExpList n) {
		n.e.accept(this);
		n.multipleExp.accept(this);
	}

	@Override
	public void visit(ExpRest n) {
		n.e.accept(this);
	}

	@Override
	public void visit(Alist n) {
		n.alist.accept(this);
		n.less.accept(this);
		
	}

	@Override
	public void visit(ClassDecl n) {
		
		
	}

	@Override
	public void visit(VarDeclList n) {
		for(VarDecl v: n)
		{
			v.type.accept(this);
			v.variableType.accept(this);
		}
	}

	@Override
	public void visit(MethodDeclList n) {
		for(MethodDecl m: n)
		{
			visit(m);
		}
	}

	@Override
	public void visit(IntArrayType n) {
		//does nothing
	}

	@Override
	public void visit(BooleanType n) {
		//does nothing
		
	}

	@Override
	public void visit(IntegerType n) {
		//does nothing
		
	}

	@Override
	public void visit(IdentifierType n) {
		//does nothing
	}

	@Override
	public void visit(Statement n) {
		//does nothing
		
	}

	@Override
	public void visit(And n) {
		n.less.accept(this);
		n.alist.accept(this);
	}

	@Override
	public void visit(IntegerLiteral n) {
		//does nothing
		
	}

	@Override
	public void visit(True n) {
		//does nothing
		
	}

	@Override
	public void visit(False n) {
		//does nothing
		
	}

	@Override
	public void visit(IdentifierExp n) {
		//does nothing
		
	}

	@Override
	public void visit(This n) {
		//does nothing
		
	}

	@Override
	public void visit(NewArray n) {
		n.e.accept(this);
	}

	@Override
	public void visit(NewObject n) {
		symTabProg.put(n.id, new Binding(n.id, IdType.CLASS));
		n.id.accept(this);
	}

	@Override
	public void visit(Not n) {
		//does nothing
	}

	@Override
	public void visit(Identifier n) {
		//does nothing
	}

	@Override
	public void visit(Exp n) {
		//does nothing
		
	}

	@Override
	public void visit(Type n) {
		//does nothing
	}

	@Override
	public void visit(StatementList n) {
		for(Statement s: n){
			visit(s);
		}
		
	}

	@Override
	public void visit(InitializationStm n) {
		//does nothing
		
	}

	@Override
	public void visit(IncrementStm n) {
		// does nothing
		
	}

	@Override
	public void visit(CaseList n) {
		// does nothing
		
	}

	@Override
	public void visit(FormalRestList formalRestList) {
		
		
	}

	@Override
	public void visit(ElseIfList elseIfList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ExpRestList expRestList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(DotArrayList dotArrayList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(DotArray n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(DotArrayArray n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(DotArrayMember n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Elist n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Factor n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(FactorNew n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Less n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Llist n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(LlistDifference n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(LlistSum n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Member n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(MemberId n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(MemberLength n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(New n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(NotFactor n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(NotSimple n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Term n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Tlist n) {
		// TODO Auto-generated method stub
		
	}
}
	
	