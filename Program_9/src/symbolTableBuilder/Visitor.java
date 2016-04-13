package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 *
 */
public interface Visitor 
{
	Object visit(Program n);
	Object visit(MainClass n);
	
	Object visit(ClassDecl n);
	Object visit(ClassDeclDeffSimple n);
	Object visit(ClassDeclDeffExtend n);
	Object visit(ClassDeclList n);
	
	
	Object visit(VarDecl n); 
	Object visit(VarDeclType n, Type t); 
	Object visit(VarDeclList n);
	
	Object visit(MethodDecl n);
	Object visit(MethodDeclList n);
	Object visit(FormalList n);
	Object visit(FormalRest n);
	
	Object visit(Statement n);
	Object visit(Block n);
	Object visit(If n);
	Object visit(ElseIf n);
	Object visit(Do n);
	Object visit(While n);
	Object visit(For n);
	Object visit(Switch n);
	Object visit(CaseListCase n);
	Object visit(CaseListDefault n);
	Object visit(Print n);
	Object visit(InitializeSimple n);
	Object visit(InitializeArray n);
	Object visit(IncrementSimple n);
	Object visit(IncrementArray n);

	Object visit(ExpList n, Identifier methodId);
	Object visit(ExpRest n);
	
	
	Object visit(StatementList n);
	Object visit(InitializationStm n);
	Object visit(IncrementStm n);
	Object visit(CaseList n);
	Object visit(FormalRestList formalRestList);
	Object visit(ElseIfList elseIfList);
	Object visit(ExpRestList expRestList, Identifier id);
	Object visit(DotArrayList dotArrayList);
	Object visit(DotArray n);
	Object visit(DotArrayArray n);
	
	Object visit(Member n);
	Object visit(MemberLength n);


	
	
	Object visit(Identifier n);
	//Type
	Object visit(Type n);
	Object visit(IntArrayType n);
	Object visit(BooleanType n);
	Object visit(IntegerType n);
	Object visit(IdentifierType n);	
	
	//Assign
	Object visit(AssignSimple n);
	Object visit(AssignArray n);
	
	//Member
	
	Object visit(MemberId n);
	
	
	//Exp
	Object visit(Exp n);
	Object visit(Elist n);
	
	Object visit(And n);
	Object visit(Alist n);
	
	Object visit(Less n);
	Object visit(Llist n);
	Object visit(LlistDifference n);
	Object visit(LlistSum n);
	
	Object visit(Term n);
	Object visit(Tlist n);
	
	Object visit(Not n);
	Object visit(NotFactor n);
	Object visit(NotSimple n);
	Object visit(DotArrayMember n);	

	//Factor
	Object visit(Factor n);
	Object visit(IntegerLiteral n);
	Object visit(True n);
	Object visit(False n);
	Object visit(IdentifierExp n);
	Object visit(This n);
	
	//New
	Object visit(FactorNew n);
	Object visit(NewArray n);
	Object visit(NewObject n);
	Object visit(New n);
	
	Object visit(Object o1, Object o2);
	

}
