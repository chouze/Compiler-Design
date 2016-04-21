package symbolTableBuilder;

public class Eval implements Visitor {

	@Override
	public Object visit(Program n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(MainClass n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ClassDecl n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ClassDeclDeffSimple n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ClassDeclDeffExtend n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ClassDeclList n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(VarDecl n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(VarDeclType n, Type t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(VarDeclList n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(MethodDecl n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(MethodDeclList n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(FormalList n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(FormalRest n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Statement n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Block n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(If n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ElseIf n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Do n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(While n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(For n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Switch n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(CaseListCase n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(CaseListDefault n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Print n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(InitializeSimple n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(InitializeArray n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(IncrementSimple n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(IncrementArray n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ExpList n, Identifier methodId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ExpRest n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(StatementList n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(InitializationStm n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(IncrementStm n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(CaseList n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(FormalRestList formalRestList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ElseIfList elseIfList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(ExpRestList expRestList, Identifier id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(DotArrayList dotArrayList) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(DotArray n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(DotArrayArray n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Member n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(MemberLength n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Identifier n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Type n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(IntArrayType n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(BooleanType n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(IntegerType n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(IdentifierType n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(AssignSimple n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(AssignArray n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(MemberId n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Exp n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Elist n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(And n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Alist n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Less n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Llist n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(LlistDifference n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(LlistSum n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Term n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(Tlist n) {
		if(n.not.accept(this) instanceof Boolean){
			return n.not.accept(this);
		}
		else if(n.not.accept(this) instanceof Integer){
			return ((Integer)(n.not.accept(this)) * ((Integer) n.tlist.accept(this)));
		}
		else{
			return null;
		}
	}

	@Override
	public Object visit(Not n) {
		return null;
	}

	@Override
	public Object visit(NotFactor n) {
		//TODO do something, maybe
		return null;
	}

	@Override
	public Object visit(NotSimple n) {
		return ! (boolean)n.not.accept(this);
	}

	@Override
	public Object visit(DotArrayMember n) {
		return n.member.accept(this);
	}

	@Override
	public Object visit(Factor n) {
		return null;
	}

	@Override
	public Object visit(IntegerLiteral n) {
		return n.i;
	}

	@Override
	public Object visit(True n) {
		return true;
	}

	@Override
	public Object visit(False n) {
		return false;
	}

	@Override
	public Object visit(IdentifierExp n) {
		return n.s;
	}

	@Override
	public Object visit(This n) {
		return "this";
	}

	@Override
	public Object visit(FactorNew n) {
		return n.accept(this);
	}

	@Override
	public Object visit(NewArray n) {
		return n.e.accept(this);
	}

	@Override
	public Object visit(NewObject n) {
		return n.id.accept(this);
	}

	@Override
	public Object visit(New n) {
		return null;
	}

	@Override
	public Object visit(Object o1, Object o2) {
		return null;
	}

}
