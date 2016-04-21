package symbolTableBuilder;

public class Eval implements Visitor {

	@Override
	public Object visit(Program n) {
		n.mainClass = (MainClass) n.mainClass.accept(this);
		n.classDecls = (ClassDeclList) n.classDecls.accept(this);
		
		return n;
	}

	@Override
	public Object visit(MainClass n) {
		n.className = (Identifier) n.className.accept(this);
		n.args = (Identifier) n.args.accept(this);
		n.v = (VarDeclList) n.v.accept(this);
		n.stmt = (Statement) n.stmt.accept(this);
		
		return n;
	}

	public Object visit(ClassDecl n) 
	{
		//abstract
		return null;
	}

	@Override
	public Object visit(ClassDeclDeffSimple n) {
		n.className = (Identifier) n.className.accept(this);
		n.fields = (VarDeclList) n.fields.accept(this);
		n.methods = (MethodDeclList) n.methods.accept(this);
	
		return n;
	}

	@Override
	public Object visit(ClassDeclDeffExtend n) {
		n.className = (Identifier) n.className.accept(this);
		n.extendedClass = (Identifier) n.extendedClass.accept(this);
		n.variableList = (VarDeclList) n.variableList.accept(this);
		n.methodList = (MethodDeclList) n.methodList.accept(this);
	
		return n;
	}

	@Override
	public Object visit(ClassDeclList n) {
		for(ClassDecl cd : n)
		{
			cd.accept(this);
		}
		
		return n;
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
		n.methodName = (Identifier) n.methodName.accept(this);
		n.type = (Type) n.type.accept(this);
		n.parameters = (FormalList)n.parameters.accept(this);
		n.variables = (VarDeclList) n.variables.accept(this);
		n.statement = (Statement) n.statement.accept(this);
		n.expReturn = (Exp) n.expReturn.accept(this);
		
		return n;
	}

	@Override
	public Object visit(MethodDeclList n) {
		for(MethodDecl md : n)
		{
			md.accept(this);
		}
		
		return n;
	}

	@Override
	public Object visit(FormalList n) {
		n.type = (Type) n.type.accept(this);
		n.parameterName = (Identifier) n.parameterName.accept(this);
		n.moreParams = (FormalRestList) n.moreParams.accept(this);
		
		return n;
	}

	@Override
	public Object visit(FormalRest n) {
		n.type = (Type) n.type.accept(this);
		n.paramName = (Identifier) n.paramName.accept(this);
		
		return n;
	}

	@Override
	public Object visit(Statement n) {
		//abstract
		return null;
	}

	@Override
	public Object visit(Block n) {
		n.sl = (StatementList) n.sl.accept(this);
		
		return n;
	}

	@Override
	public Object visit(If n) {
		n.condition = (Exp) n.condition.accept(this);
		n.s = (Statement) n.s.accept(this);
		n.elseIf = (ElseIfList) n.elseIf.accept(this);
		
		return n;
	}

	@Override
	public Object visit(ElseIf n) {
		n.condition = (Exp) n.condition.accept(this);
		n.s = (Statement) n.s.accept(this);
		
		return n;
	}

	@Override
	public Object visit(Do n) {
		n.condition = (Exp) n.condition.accept(this);
		n.s = (Statement) n.s.accept(this);
		
		return n;
	}

	@Override
	public Object visit(While n) {
		n.condition = (Exp) n.condition.accept(this);
		n.s = (Statement) n.s.accept(this);
		
		return n;
	}

	@Override
	public Object visit(For n) {
		n.initialize = (InitializationStm) n.initialize.accept(this);
		n.e = (Exp) n.e.accept(this);
		n.increment = (IncrementStm) n.increment.accept(this);
		n.s = (Statement) n.s.accept(this);
		
		return n;
	}

	@Override
	public Object visit(Switch n) {
		n.id = (Identifier) n.id.accept(this);
		n.caseList = (CaseList) n.caseList.accept(this);
		
		return n;
	}

	@Override
	public Object visit(CaseListCase n) {
		n.id = (Identifier) n.id.accept(this);
		n.caseList = (CaseList) n.caseList.accept(this);
		n.caseExp = (Exp) n.caseExp.accept(this);
		n.caseList = (CaseListCase) n.caseList.accept(this);
		
		return n;		
	}

	@Override
	public Object visit(CaseListDefault n) {
		n.s = (Statement) n.s.accept(this);
		
		return n;
	}

	@Override
	public Object visit(Print n) {
		n.statementToPrint = (Exp) n.statementToPrint.accept(this);
		
		return n;
	}

	@Override
	public Object visit(InitializeSimple n) {
		n.id = (Identifier) n.id.accept(this);
		n.assignExp = (Exp) n.assignExp.accept(this);
		
		return n;
	}

	@Override
	public Object visit(InitializeArray n) {
		n.id = (Identifier) n.id.accept(this);
		n.assignExp = (Exp) n.assignExp.accept(this);
		n.arrayExp = (Exp) n.arrayExp.accept(this);
		
		return n;
	}

	@Override
	public Object visit(IncrementSimple n) {
		n.id = (Identifier) n.id.accept(this);
		n.assignExp = (Exp) n.assignExp.accept(this);
		
		return n;
	}

	@Override
	public Object visit(IncrementArray n) {
		n.id = (Identifier) n.id.accept(this);
		n.assignExp = (Exp) n.assignExp.accept(this);
		n.arrayExp = (Exp) n.arrayExp.accept(this);
		
		return n;
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
		for(Statement s : n)
		{
			s.accept(this);
		}
		
		return n;
	}

	@Override
	public Object visit(InitializationStm n) {
		//abstract
		return null;
	}

	@Override
	public Object visit(IncrementStm n) {
		//abstract
		return null;
	}

	@Override
	public Object visit(CaseList n) {
		//abstract
		return null;
	}

	@Override
	public Object visit(FormalRestList formalRestList) {
		for(FormalRest fr : formalRestList)
		{
			fr.accept(this);
		}
		
		return formalRestList;
	}

	@Override
	public Object visit(ElseIfList elseIfList) {
		for(ElseIf ef : elseIfList)
		{
			ef.accept(this);
		}
		
		return elseIfList;
	}

	@Override
	public Object visit(ExpRestList expRestList, Identifier id) {
		for(ExpRest er : expRestList)
		{
			er.accept(this);
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
		//abstract
		return null;
	}

	@Override
	public Object visit(DotArrayArray n) {
		n.exp = (Exp) n.exp.accept(this);
		
		return n;
	}

	@Override
	public Object visit(Member n) 
	{
		//abstract
		return null;
	}

	@Override
	public Object visit(MemberLength n) {
		n.length = (Exp) n.length.accept(this);
		
		return n;
	}

	@Override
	public Object visit(Identifier n) {
		return n;
	}

	@Override
	public Object visit(Type n) {
		//abstract
		return null;
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
		n.id = (Identifier) n.id.accept(this);
		n.assignment = (Exp) n.assignment.accept(this);
		
		return n;
	}

	@Override
	public Object visit(AssignArray n) {
		n.id = (Identifier) n.id.accept(this);
		n.index = (Exp) n.index.accept(this);
		n.value = (Exp) n.value.accept(this);
		
		return n;
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
