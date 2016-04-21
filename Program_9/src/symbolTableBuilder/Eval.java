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
	public Object visit(Object o1, Object o2) {
		return null;
	}
	
	@Override
	public Object visit(Exp n) {
		if(n.and.accept(this) instanceof Boolean && n.elist.accept(this) instanceof Boolean){
			return (boolean)n.and.accept(this) && (boolean)n.elist.accept(this);
		}
		return n;
	}

	@Override
	public Object visit(Elist n) {
		if(n.and == null && n.elist == null){
			return null;
		}
		if(n.and.accept(this) instanceof Boolean && n.elist.accept(this) instanceof Boolean){
			return (boolean)n.and.accept(this) && (boolean)n.elist.accept(this);
		}
		return n;
	}

	
	@Override
	public Object visit(And n) {
		
		//String s = n.less.accept(this).toString();
		if(n.alist == null)
			return n.less.accept(this);
		if(n.alist.accept(this) instanceof Integer && n.less.accept(this) instanceof Integer){
			return (Integer)n.less.accept(this) < (Integer)n.alist.accept(this);
		}
		return n;
	}

	@Override
	public Object visit(Alist n) {
		if(n.alist == null){
			if(n.less == null){
				return null;
			}
			else{
				return n.less.accept(this);
			}
		}
		if(n.less == null){
			return n.alist.accept(this);
		}
		
		if(n.alist.accept(this) instanceof Integer && n.less.accept(this) instanceof Integer){
			return (Integer)n.less.accept(this) < (Integer)n.alist.accept(this);
		}
		return n;
	}

	/**
	 * From tostringer past here
	 */
	@Override
	public Object visit(Less n) {
		String s = n.term.accept(this).toString();
		if(n.llist == null)
			return s;
		s += n.llist.accept(this);
		return s;
	}

	@Override
	public Object visit(Llist n) {
		
		if (n instanceof LlistDifference)
			return (String) visit((LlistDifference) n);
		if ( n instanceof LlistSum)
			return (String) visit((LlistSum) n);
		
		return "";
	}

	@Override
	public Object visit(LlistDifference n) {
		String s = " - " + n.term.accept(this).toString();
		if(n.llist == null)
			return s;
		s += n.llist.accept(this);
		return s;
	}

	@Override
	public Object visit(LlistSum n) {
		String s = " + " + n.term.accept(this).toString();
		if(n.llist == null)
			return s;
		s += n.llist.accept(this);
		return s;
	}

	@Override
	public Object visit(Term n) {
		String s = n.not.accept(this).toString();
		if(n.tlist == null)
			return s;
		s += n.tlist.accept(this);
		return s;
	}

	@Override
	public Object visit(Tlist n) {
		if(n.not == null && n.tlist == null){
			return "";
		}
		String s = "* " + n.not.accept(this).toString();
		s += n.tlist.accept(this);
		return s;
	}

	@Override
	public Object visit(Not n) {
		return "";
	}

	@Override
	public Object visit(NotFactor n) {
		String s = n.factor.accept(this).toString();
		s += n.dotList.accept(this);
		return s;
	}

	@Override
	public Object visit(NotSimple n) {
		String s = "!" + n.not.accept(this).toString();
		return s;
	}

	@Override
	public Object visit(DotArrayMember n) {
		String s = "." + n.member.accept(this).toString();
		return s;
	}

	@Override
	public Object visit(Factor n) {
		
		return null;
	}

	@Override
	public Object visit(IntegerLiteral n) {
		return n.i + " ";
	}

	@Override
	public Object visit(True n) {
		return "true ";
	}

	@Override
	public Object visit(False n) {
		return "false ";
	}

	@Override
	public Object visit(IdentifierExp n) {
		return n.s + " ";
	}

	@Override
	public Object visit(This n) {
		return "this ";
	}

	@Override
	public Object visit(FactorNew n) {
		return n.newObject.accept(this).toString();
	}

	@Override
	public Object visit(NewArray n) {
		return n.e.accept(this).toString();
	}

	@Override
	public Object visit(NewObject n) {
		return n.id.accept(this).toString();
	}

	@Override
	public Object visit(New n) {
		return null;
	}

}
