package symbolTableBuilder;

import java.util.List;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 *
 */
public interface Visitor 
{
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
	
	void visit(Statement n);
	String visit(Block n);
	String visit(If n);
	void visit(ElseIf n);
	String visit(Do n);
	String visit(While n);
	String visit(For n);
	String visit(Switch n);
	void visit(CaseListCase n);
	void visit(CaseListDefault n);
	String visit(Print n);
	void visit(InitializeSimple n);
	void visit(InitializeArray n);
	void visit(IncrementSimple n);
	void visit(IncrementArray n);

	void visit(ExpList n, Identifier methodId);
	String visit(ExpRest n);
	
	
	void visit(StatementList n);
	void visit(InitializationStm n);
	void visit(IncrementStm n);
	void visit(CaseList n);
	void visit(FormalRestList formalRestList);
	void visit(ElseIfList elseIfList);
	void visit(ExpRestList expRestList, Identifier id);
	void visit(DotArrayList dotArrayList);
	String visit(DotArray n);
	String visit(DotArrayArray n);
	
	String visit(Member n);
	String visit(MemberLength n);


	
	
	String visit(Identifier n);
	//Type
	String visit(Type n);
	String visit(IntArrayType n);
	String visit(BooleanType n);
	String visit(IntegerType n);
	String visit(IdentifierType n);	
	
	//Assign
	String visit(AssignSimple n);
	String visit(AssignArray n);
	
	//Member
	
	String visit(MemberId n);
	
	
	//Exp
	String visit(Exp n);
	String visit(Elist n);
	
	String visit(And n);
	String visit(Alist n);
	
	String visit(Less n);
	String visit(Llist n);
	String visit(LlistDifference n);
	String visit(LlistSum n);
	
	String visit(Term n);
	String visit(Tlist n);
	
	String visit(Not n);
	String visit(NotFactor n);
	String visit(NotSimple n);
	String visit(DotArrayMember n);	

	//Factor
	String visit(Factor n);
	String visit(IntegerLiteral n);
	String visit(True n);
	String visit(False n);
	String visit(IdentifierExp n);
	String visit(This n);
	
	//New
	String visit(FactorNew n);
	String visit(NewArray n);
	String visit(NewObject n);
	String visit(New n);
	
	
	

}
