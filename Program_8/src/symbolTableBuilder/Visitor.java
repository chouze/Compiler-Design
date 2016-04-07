package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 *
 */
public interface Visitor 
{
	String visit(Alist n);
	void visit(Program n);
	void visit(MainClass n);
	void visit(ClassDecl n);
	void visit(ClassDeclDeffSimple n);
	void visit(ClassDeclDeffExtend n);
	void visit(ClassDeclList n);
	void visit(VarDecl n);
	void visit(VarDeclType n, Type t);
	void visit(VarDeclList n);
	void visit(MethodDecl n);
	void visit(MethodDeclList n);
	void visit(FormalList n);
	void visit(FormalRest n);
	String visit(IntArrayType n);
	String visit(BooleanType n);
	String visit(IntegerType n);
	String visit(IdentifierType n);
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
	void visit(InitializeSimple n);
	void visit(InitializeArray n);
	void visit(IncrementSimple n);
	void visit(IncrementArray n);
	void visit(ElseIf n);
	void visit(CaseListCase n);
	void visit(CaseListDefault n);
	void visit(ExpList n);
	void visit(ExpRest n);
	String visit(And n);
	String visit(IntegerLiteral n);
	String visit(True n);
	String visit(False n);
	String visit(IdentifierExp n);
	String visit(This n);
	void visit(NewArray n);
	void visit(NewObject n);
	String visit(Not n);
	void visit(Identifier n);
	String visit(Exp n);
	String visit(Type n);
	void visit(StatementList n);
	void visit(InitializationStm n);
	void visit(IncrementStm n);
	void visit(CaseList n);
	void visit(FormalRestList formalRestList);
	void visit(ElseIfList elseIfList);
	void visit(ExpRestList expRestList);
	void visit(DotArrayList dotArrayList);
	void visit(DotArray n);
	void visit(DotArrayArray n);
	void visit(DotArrayMember n);
	String visit(Elist n);
	String visit(Factor n);
	void visit(FactorNew n);
	String visit(Less n);
	String visit(Llist n);
	void visit(LlistDifference n);
	void visit(LlistSum n);
	void visit(Member n);
	void visit(MemberId n);
	void visit(MemberLength n);
	void visit(New n);
	void visit(NotFactor n);
	void visit(NotSimple n);
	String visit(Term n);
	String visit(Tlist n);
	
	

}
