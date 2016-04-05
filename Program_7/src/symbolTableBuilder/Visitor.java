package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 *
 */
public interface Visitor 
{
	void visit(Alist n);
	void visit(Program n);
	void visit(MainClass n);
	void visit(ClassDecl n);
	void visit(ClassDeclDeffSimple n);
	void visit(ClassDeclDeffExtend n);
	void visit(ClassDeclList n);
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
	void visit(InitializeSimple n);
	void visit(InitializeArray n);
	void visit(IncrementSimple n);
	void visit(IncrementArray n);
	void visit(ElseIf n);
	void visit(CaseListCase n);
	void visit(CaseListDefault n);
	void visit(ExpList n);
	void visit(ExpRest n);
	void visit(And n);
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
	void visit(FormalRestList formalRestList);
	void visit(ElseIfList elseIfList);
	void visit(ExpRestList expRestList);
	void visit(DotArrayList dotArrayList);
	void visit(DotArray n);
	void visit(DotArrayArray n);
	void visit(DotArrayMember n);
	void visit(Elist n);
	void visit(Factor n);
	void visit(FactorNew n);
	void visit(Less n);
	void visit(Llist n);
	void visit(LlistDifference n);
	void visit(LlistSum n);
	void visit(Member n);
	void visit(MemberId n);
	void visit(MemberLength n);
	void visit(New n);
	void visit(NotFactor n);
	void visit(NotSimple n);
	void visit(Term n);
	void visit(Tlist n);
	
	

}
