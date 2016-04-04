package symbolTableBuilder;

/* 
 * A Visitor can perform operations on a Syntax Tree, such as
 * 	Build symbol tables
 * 	Type check
 * 	Optimize
 * 	Code Gen...
 * 
 * @author ()
 * @version (Mar 2016)
 */
public interface Visitor {
	void visit(Program n);
	void visit(MainClass n);
	//void visit(ClassDeclSpec n);
	void visit(ClassDeclDeffSimple n);
	void visit(ClassDeclDeffExtend n);
	void visit(ClassDeclList n); //ask what this is
	void visit(VarDecl n);
	void visit(VarDeclType n);
	void visit(VarDeclTypeAssign n);
	void visit(VarDeclList n);
	void visit(MethodDecl n);
	void visit(MethodDeclList n);
	void visit(FormalList n);
	void visit(FormalRest n);
	void visit(IntArrayType n);
	void visit(BooleanType n);
	void visit(IntegerType n);
	void visit(IdentifierType n);
	void visit(Statement n);
	void visit(Block n);
	void visit(If n);
	void visit(Do n);
	void visit(While n);
	void visit(For n);
	void visit(Switch n);
	void visit(Print n);
	void visit(AssignSimple n);
	void visit(AssignArray n);
	void visit(AssignMultiple n);
	void visit(InitializeSimple n);
	void visit(InitializeArray n);
	void visit(IncrementSimple n);
	void visit(IncrementArray n);
	void visit(ElseIf n);
	//void visit(FormalVarExp n);
	void visit(CaseListCase n);
	void visit(CaseListDefault n);
	void visit(ExpList n);
	void visit(ExpRest n);
	void visit(And n);
	void visit(LessThan n);
	void visit(Plus n);
	void visit(Minus n);
	void visit(Times n);
	void visit(ArrayLookUp n);
	void visit(ArrayLength n);
	void visit(Call n);
	void visit(IntegerLiteral n);
	void visit(True n);
	void visit(False n);
	void visit(IdentifierExp n);
	void visit(This n);
	void visit(NewArray n);
	void visit(NewObject n);
	void visit(Not n);
	void visit(Identifier n);
	void visit(Exp n);
	void visit(Type n);
	void visit(StatementList n);
	void visit(InitializationStm n);
	void visit(IncrementStm n);
	void visit(CaseList n);
	
	/*
	String visit(Exp n);
	String visit(And n);
	String visit(LessThan n);
	String visit(Plus n);
	String visit(Mult n);
	//etc etc for every class in our syntax tree
	 * */
}
