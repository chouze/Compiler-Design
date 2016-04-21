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
		n.type = (Type) n.type.accept(this);
		n.variableType = (VarDeclType) n.variableType.accept(this, n.type);
		
		return n;
	}

	@Override
	public Object visit(VarDeclType n, Type t) {
		n.exp = (Exp) n.exp.accept(this);
		n.variableName = (Identifier) n.variableName.accept(this);
		
		return n;
	}

	@Override
	public Object visit(VarDeclList n) {
		for(VarDecl vd: n)
		{
			vd.accept(this);
		}
		
		return n;
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
		return n.sl.accept(this);
	}

	@Override
	public Object visit(If n) {
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
		n.e = (Exp) n.e.accept(this);
		n.multipleExp = (ExpRestList) n.multipleExp.accept(this, methodId);
		return n;
	}

	@Override
	public Object visit(ExpRest n) {
		n.e = (Exp) n.e.accept(this);
		return n;
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
		for (int i = 0; i < expRestList.size(); i++) {
			ExpRest e = expRestList.get(i);
			e = (ExpRest)e.accept(this); //Pretty sure this loops through the ExpRestList list and accepts all expressions in it, but not 100%
		}
		return expRestList;
	}

	@Override
	public Object visit(DotArrayList dotArrayList) {
		for(DotArray da: dotArrayList)
		{
			da.accept(this);
		}
		return dotArrayList;
	}

	@Override
	public Object visit(DotArray n) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object visit(DotArrayArray n) {
		return n.exp.accept(this);
	}

	@Override
	public Object visit(Member n) 
	{
		return null;
	}

	@Override
	public Object visit(MemberLength n) {
		return n.length.accept(this);
	}

	@Override
	public Object visit(Identifier n) {
		return n;
	}

	@Override
	public Object visit(Type n) {
		//abstract
		return n;
	}

	@Override
	public Object visit(IntArrayType n) {
		return n;
	}

	@Override
	public Object visit(BooleanType n) {
		return n;
	}

	@Override
	public Object visit(IntegerType n) {
		return n;
	}

	@Override
	public Object visit(IdentifierType n) {
		return n;
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
		n.id = (Identifier) n.id.accept(this);
		n.expList = (ExpList) n.expList.accept(this, n.id);
		
		return n;
	}



	@Override
	public Object visit(Object o1, Object o2) {
		return null;
	}

	@Override
	public Object visit(Exp n) {
		if(n.elist == null)
			return n.and.accept(this);
		
		Object temp1 = n.and.accept(this);
		Object temp2 = n.elist.accept(this);
		
		if(temp1 instanceof Boolean && temp2 instanceof Boolean)
			return ((Boolean)temp2) && ((Boolean)temp1);
		
		n.and = (And) temp1;
		n.elist = (Elist) temp2;
		return n;
	}

	@Override
	public Object visit(Elist n) {
		if(n.elist == null && n.and == null)
			return null;
		if(n.elist == null)
			return n.and.accept(this);
		
		Object temp1 = n.and.accept(this);
		Object temp2 = n.elist.accept(this);
		
		if(temp1 instanceof Boolean && temp2 instanceof Boolean)
			return ((Boolean)temp2) && ((Boolean)temp1);
		
		n.and = (And) temp1;
		n.elist = (Elist) temp2;
		return n;
	}


	@Override
	public Object visit(And n) {
		if(n.alist == null)
			return n.less.accept(this);
		
		Object temp1 = n.alist.accept(this);
		Object temp2 = n.less.accept(this);
		
		if(temp1 instanceof IntegerLiteral && temp2 instanceof IntegerLiteral)
			return ((IntegerLiteral)temp2).i < ((IntegerLiteral)temp1).i;
		
		n.alist = (Alist) temp1;
		n.less = (Less) temp2;
		return n;
		
	}

	@Override
	public Object visit(Alist n) {
		if(n.alist == null && n.less == null)
			return null;
		if(n.alist == null)
			return n.less.accept(this);
		
		Object temp1 = n.alist.accept(this);
		Object temp2 = n.less.accept(this);
		
		if(temp1 instanceof IntegerLiteral && temp2 instanceof IntegerLiteral)
			return ((IntegerLiteral)temp2).i < ((IntegerLiteral)temp1).i;
		
		n.alist = (Alist) temp1;
		n.less = (Less) temp2;
		return n;
	}

	@Override
	public Object visit(Less n) {
		if(n.llist == null)
			return n.term.accept(this);

		if(n.llist instanceof LlistSum)
		{
			Object temp1 = n.term.accept(this);
			Object temp2 = n.llist.accept(this);
			if(temp1 instanceof IntegerLiteral && temp2 instanceof IntegerLiteral)
			{
				return new IntegerLiteral((((IntegerLiteral) temp1).i) + (((IntegerLiteral) temp2).i)); 
			}
		}

		if(n.llist instanceof LlistDifference)
		{
			Object temp1 = n.term.accept(this);
			Object temp2 = n.llist.accept(this);
			if(temp1 instanceof IntegerLiteral && temp2 instanceof IntegerLiteral)
			{
				return new IntegerLiteral((((IntegerLiteral) temp1).i) - (((IntegerLiteral) temp2).i)); 
			}
		}

		n.term = (Term) n.term.accept(this);
		n.llist = (Llist) n.llist.accept(this);
		return n;
	}

	@Override
	public Object visit(Llist n) {

		if (n instanceof LlistDifference)
			return visit((LlistDifference) n);
		if ( n instanceof LlistSum)
			return visit((LlistSum) n);

		return null;
	}

	@Override
	public Object visit(LlistDifference n) {
		if(n.llist == null)
			return n.term.accept(this);

		if(n.llist instanceof LlistSum)
		{
			Object temp1 = n.term.accept(this);
			Object temp2 = n.llist.accept(this);
			if(temp1 instanceof IntegerLiteral && temp2 instanceof IntegerLiteral)
			{
				return new IntegerLiteral((((IntegerLiteral) temp1).i) + (((IntegerLiteral) temp2).i)); 
			}
		}

		if(n.llist instanceof LlistDifference)
		{
			Object temp1 = n.term.accept(this);
			Object temp2 = n.llist.accept(this);
			if(temp1 instanceof IntegerLiteral && temp2 instanceof IntegerLiteral)
			{
				return new IntegerLiteral((((IntegerLiteral) temp1).i) - (((IntegerLiteral) temp2).i)); 
			}
		}
		
		n.term = (Term) n.term.accept(this);
		n.llist = (Llist) n.llist.accept(this);
		return n;
	}

	@Override
	public Object visit(LlistSum n) {
		if(n.llist == null)
			return n.term.accept(this);

		if(n.llist instanceof LlistSum)
		{
			Object temp1 = n.term.accept(this);
			Object temp2 = n.llist.accept(this);
			if(temp1 instanceof IntegerLiteral && temp2 instanceof IntegerLiteral)
			{
				return new IntegerLiteral((((IntegerLiteral) temp1).i) + (((IntegerLiteral) temp2).i)); 
			}
		}

		if(n.llist instanceof LlistDifference)
		{
			Object temp1 = n.term.accept(this);
			Object temp2 = n.llist.accept(this);
			if(temp1 instanceof IntegerLiteral && temp2 instanceof IntegerLiteral)
			{
				return new IntegerLiteral((((IntegerLiteral) temp1).i) - (((IntegerLiteral) temp2).i)); 
			}
		}
		
		n.term = (Term) n.term.accept(this);
		n.llist = (Llist) n.llist.accept(this);
		return n;
	}

	@Override
	public Object visit(Term n) {
		if(n.tlist == null)
		{
			return n.not.accept(this);
		}

		//deal with multiplication
		Object temp1 = n.tlist.accept(this);
		Object temp2 = n.not.accept(this);
		if(temp1 instanceof IntegerLiteral && temp2 instanceof IntegerLiteral)
		{
			return new IntegerLiteral((((IntegerLiteral) temp1).i) * (((IntegerLiteral) temp2).i)); 
		}

		n.not = (Not)temp2;
		n.tlist = (Tlist) temp1;
		return n;
	}

	@Override
	public Object visit(Tlist n) {
		if(n.not == null && n.tlist == null){
			return null;
		}

		if(n.tlist == null)
		{
			return n.not.accept(this);
		}

		n.not = (Not)n.not.accept(this);
		n.tlist = (Tlist) n.tlist.accept(this);
		return n;
	}

	@Override
	public Object visit(Not n) {
		return null;
	}

	@Override
	public Object visit(NotFactor n) {
		if(n.dotList == null)
		{
			return n.factor.accept(this);
		}

		n.dotList = (DotArrayList) n.dotList.accept(this);
		n.factor = (Factor) n.factor.accept(this);
		return n;
	}

	@Override
	public Object visit(NotSimple n) {
		return n.not.accept(this);
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
		return n;
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
		return n;
	}

	@Override
	public Object visit(NewArray n) {
		return n;
	}

	@Override
	public Object visit(NewObject n) {
		return n;
	}

	@Override
	public Object visit(New n) {
		return null;
	}

}
