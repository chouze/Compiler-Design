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

public class SymbolTableTypeCheck implements Visitor {
	public SymbolTable symTab, symTabMethod, symTabClass, symTabProg;
	
	public String thisClass; //name of the current class being processed
	// I think it works like this:
	// symTab is the table in focus
	// symTabMethod is the table created when a method is created
	// symTabClass is the table created when a class is created
	// symTabProg is the table created at the time the program is
	//
	// This class

	public SymbolTableTypeCheck() {
//Temp
		//symTabProg = new SymbolTable();
	}

	public void visit(Program n) { // Program as defined in Bergmann's parser
		symTabProg = n.symTab; //need to make symTab in program
		n.mainClass.accept(this);
		n.classDecls.accept(this);
		
	
	}

	public void visit(MainClass n) {
		symTab = symTabClass = n.symTab;
		n.className.accept(this);
		n.args.accept(this);
		n.v.accept(this);
		n.stmt.accept(this); // ??
		
		
		
	}

	// everytime we put a new symbol into the symboltable, we have to create a
	// new binding for it
	public void visit(ClassDeclDeffSimple n) {
		symTab = symTabClass = n.symTab;
		n.className.accept(this);
		
		n.fields.accept(this); // enter fields into the symbol table
		n.methods.accept(this);
		thisClass = n.className.name;
	}

	/*
	 *  public void visit(ClassDeclSpec n) {
	 * symTabProg.put(n.className, new Binding(n.className, IdType.CLASS));
	 * n.className.accept(this); }
	 */

	
	public void visit(ClassDeclDeffExtend n) {
		symTabProg.check(n.extendedClass,  IdType.CLASS); 
		n.className.accept(this);
		n.extendedClass.accept(this);
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
		symTabProg.put(n.variableName, new Binding(n.variableName, IdType.VARIABLE, t.getClass().getSimpleName()));
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
		symTab = symTabMethod = n.symTab;
		n.type.accept(this);
	
		n.methodName.accept(this);
		n.parameters.accept(this);
		n.variables.accept(this);
		n.statements.accept(this);
		n.expReturn.accept(this);
				
	}

	
	public void visit(FormalList n) {
		n.type.accept(this);	
		n.parameterName.accept(this);
		n.moreParams.accept(this);	
	}

	
	public void visit(FormalRest n) {
		n.type.accept(this);
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
		n.id.accept(this);
		n.caseDefault.accept(this);
	}

	
	public void visit(Print n) {
		n.statementToPrint.accept(this);
	}

	
	public void visit(AssignSimple n) {
		///////////This is in assign in bergmann's 
		SymbolTable = symTab;
		symTab = check(n.id, IdType.VARIABLE);
		String tLeft = symTab.getType(n.id);
		n.id.accept(this);
		String tRight = n.value.accept(this);
		checkType(tLeft, tRight);		//should have the same type
		
		///////////
		//n.assignment.accept(this);
		
	}

	
	public void visit(AssignArray n) {
		SymbolTable symTab = check(n.id, "INTARRAY");
		n.id.accept(this);
		String indexType = n.index.accept(this);
		String valueType = n.value.accept(this);
		if(!valueType.equals("INTEGER")){
			System.out.println("Value assigned to int array should be of type int");
		}
		if(!indexType.equals("INTEGER"))
			System.out.prinln("Array index should be int");
		//n.arrayExp.accept(this);
		//n.assignExp.accept(this);	
	}
	
	

	
	public void visit(InitializeSimple n) {
		n.id.accept(this);
		n.assignExp.accept(this);
	}

	
	public void visit(InitializeArray n) {
		n.id.accept(this);
		n.arrayExp.accept(this);
		n.assignExp.accept(this);
	}

	
	public void visit(IncrementSimple n) {
		n.id.accept(this);
		n.assignExp.accept(this);
	}

	
	public void visit(IncrementArray n) {
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
	//the given identifier should have the given usage
	//if the usage is Variable, return its sumbol table
	private SymbolTable check(Identifier id, IdType idtype){
		SymbolTable result = symTabMethod; //assume id is a local variable
		Binding b = symTabMethod.get(id);
		if(b == null)
		{
			result = symTabClass;
			symTabClass.check(id,  idtype);
		}
		else
		{
			symTabMethod.check(id, idtype);
		}
		return result;
	}
	
	// return the symbol table for the given identifier, check its type
		private SymbolTable check(Identifier id, String type)
		{
			
		}
		
		/*
		 * public String visit(Exp n){
		 * 	String tLeft = null, tRight = null;
		 * 	if(n.left  != null){
		 * 		tLeft = n.left.accept(this);
		 * 	}
		 *	if(n.right  != null){
		 * 		tRight = n.left.accept(this);
		 * 	}
		 * 	if(tLeft == null)
		 * 		return tRight;
		 * 	if(tRight == null);
		 * 		return tLeft;
		 * 	return checkType(tLeft, tRight);
		 * }
		 * 
		 * public String visit(And n){
		 * 	String tLeft = n.left.accept(this);
		 * 	String tRight = n.right.accept(this;
		 *	//Types should be equal
		 *	//Types should be BOOLEAN
		 *	//return BOOLEAN
		 * 	return checkType(tLeft, tRight, "BOOLEAN", "BOOLEAN");
		 * }
		 * 
		 * public String visit(LessThan n){
		 * 	String tLeft = n.left.accept(this);
		 * 	String tRight = n.right.accept(this;
		 * 	return checkType(tLeft, tRight, "INTEGER", "BOOLEAN");
		 * }
		 * 
		 * public String visit(Plus n){
		 * 	String tLeft = n.left.accept(this);
		 * 	String tRight = n.right.accept(this;
		 * 	return checkType(tLeft, tRight, "INTEGER", "INTEGER");
		 * }
		 * 
		 *  //x[expr]
		 * public String visit(ArrayLookup n){
		 * 	String tLeft = n.left.accept(this);
		 * 	String tRight = n.right.accept(this;
		 * 	checkType(tLeft, "INTARRAY");
		 * 	return checkType(tRight, "INTEGER", tLeft); //return tRight?
		 * }
		 * 
		 * public String visit(ArrayLength n){
		 * 	String tLeft = n.left.accept(this);
		 * 	return checkType(tLeft, "INTARRAY", "INTEGER");
		 * 	}
		 * 
		 * public String visit(Call n){
		 * 	String tClass = n.receiver.accept(this);
		 * 	SymbolTable symTab = program.getSymbolTable(tClass);
		 * 	if(symTab == null)
		 * 	System.err.println("Method not defined in class..." + tClass);
		 * 	symTab.check(n.methodName, IdType.METHOD);
		 * 	Binding b = symTab.get(n.methodName);
		 * 	List<String> parms = b.getParms();
		 * 	List<String> exps = n.parms.getExps();
		 *  if(exps.size() != parms.size())
		 *  	System.err.println("Number of actual parms != number of formal parameters");
		 *  for(int i = 0; i < exps.size(); i++)
		 *  {
		 *  	if(exps.get(i).accept(this) != parms.get(i)
		 *  		System.err.println("Mismatching parameters");
		 *  }
		 * 	return b.getType();
		 * }
		 */
	
}
	
	