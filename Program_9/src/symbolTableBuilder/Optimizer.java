package symbolTableBuilder;

/**
 * 
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 *
 */

public class Optimizer implements Visitor {

	Equal eq = new Equal();
	Eval ev = new Eval();

	public Optimizer() {

	}

	public Object visit(Program n) {
		n.mainClass = (MainClass) n.mainClass.accept(this);
		n.classDecls = (ClassDeclList) n.classDecls.accept(this);
		return n;
	}

	public Object visit(MainClass n) {
		n.className.accept(this); // identifier, no need to optimize
		n.args.accept(this); // identifier
		n.v.accept(this); // variables, no need to optimize
		n.stmt = (Statement) n.stmt.accept(this);
		return n;
	}

	public Object visit(ClassDeclDeffSimple n) {
		n.className.accept(this); // identifier
		n.fields.accept(this); // variables
		n.methods = (MethodDeclList) n.methods.accept(this);
		return n;
	}

	public Object visit(ClassDeclDeffExtend n) {
		n.className.accept(this); // identifier
		n.extendedClass.accept(this); // identifier
		n.variableList.accept(this); // variables
		n.methodList = (MethodDeclList) n.methodList.accept(this);
		return n;
	}

	public Object visit(ClassDeclList n) {
		for (ClassDecl c : n) {
			if (c instanceof ClassDeclDeffSimple) {
				((ClassDeclDeffSimple) c).accept(this);
			}

			if (c instanceof ClassDeclDeffExtend) {
				((ClassDeclDeffExtend) c).accept(this);
			}
		}
		return n;
	}

	public Object visit(VarDecl n) {
		Type t = n.type;
		t.accept(this); // Type, no need to optimize
		n.variableType.accept(this, t); // variables
		return n;
	}

	public Object visit(VarDeclType n, Type t) {
		if (n.exp != null && ev.visit(n.exp) != null) {
			n.exp = (Exp) ev.visit(n.exp);
			System.out.println("Optimizing: variable declarations with initialization");
		}
		return n;
	}

	public Object visit(MethodDecl n) {
		n.type.accept(this); // identifier
		n.methodName.accept(this); // identifier
		n.parameters.accept(this); // parameters, no need to optimize
		n.variables.accept(this); // variables
		n.statement = (Statement) n.statement.accept(this);
		n.expReturn = (Exp) n.expReturn.accept(this);
		return n;
	}

	public Object visit(FormalList n) {
		return n;
	}

	public Object visit(FormalRest n) {
		n.type.accept(this); // type
		n.paramName.accept(this); // identifier
		return n;
	}

	public Object visit(Block n) {
		n.sl = (StatementList) n.sl.accept(this);
		return n;
	}

	public Object visit(If n) {
		n.s = (Statement)n.s.accept(this);
		n.elseIf = (ElseIfList)n.elseIf.accept(this);

		boolean sameStatements = true;
		boolean oneTrue = false;
		for(ElseIf ef : n.elseIf){
			if(!(boolean) eq.visit(n.s.accept(this), ef.s.accept(this))){
				sameStatements = false;
				break;
			}
			if(ef != null && ef.condition != null){
				Object o = ev.visit(ef.condition);
				if(o instanceof Boolean && (boolean)o){
					oneTrue = true;
				}
				
			}
		}

		if(sameStatements && oneTrue){
			System.out.println("Optimizing: factoring duplicated statements from if");
			return n.s;
		}

		boolean sameExp = true;
		for(ElseIf ef : n.elseIf){
			if(!(boolean) eq.visit(n.condition.accept(this), ef.condition.accept(this))){
				sameExp = false;
				break;
			}
		}
		if(sameExp){
			//Get rid of the old elseIfs
			System.out.println("Optimizing: removing elseifs with identical conditions\n");
			n.elseIf.clear();
		}

		Object o = ev.visit(n.condition);
		if(o instanceof Boolean){
			if((Boolean)o == true){
				System.out.println("Optimizing: removed else if as if evaluated to true");
				return n.s;
			}
			else{
				if(n.elseIf.size() < 2)
					System.out.println("Optimizing: removed if statement as it evaluated to false, kept else if");
					return n.elseIf.get(0).s;
			}
		}

		return n;
	}

	public Object visit(Do n) {
		n.s = (Statement) n.s.accept(this);
		Object o = ev.visit(n.condition);
		if(o instanceof Boolean)
		{
			n.condition = bool((Boolean) o);
			if(!(Boolean)o){
				System.out.println("Optimizing: replaced loop with just loop content because loop only executes once");
				return n.s;
			}
		}
		return n;
	}

	public Object visit(While n) {
		Object o = ev.visit(n.condition);
		n.s = (Statement) n.s.accept(this);
		if(o instanceof Boolean)
		{
			n.condition = bool((Boolean) o);
			
			if(!(Boolean) o)
				System.out.println("Optimizing: removed while loop as it evaluated to false");
				return new Block(new StatementList());
		}
		return n;
	}

	private Exp bool(Boolean o) {
		if(o)
			return new Exp(new And(new Less(new Term(new NotFactor(new True(), new DotArrayList()), new Tlist()), new Llist()), new Alist()), new Elist());
		else
			return new Exp(new And(new Less(new Term(new NotFactor(new False(), new DotArrayList()), new Tlist()), new Llist()), new Alist()), new Elist());
	}

	public Object visit(For n) {
		n.initialize = (InitializationStm) n.initialize.accept(this);
		n.e = (Exp) n.e.accept(this);
		n.increment = (IncrementStm) n.increment.accept(this);
		n.s = (Statement) n.s.accept(this);
		return n;
	}

	public Object visit(Switch n) {
		n.id.accept(this); // identifier
		n.caseList = (CaseList) n.caseList.accept(this);
		return n;
	}

	public Object visit(Print n) {
		n.statementToPrint = (Exp) n.statementToPrint.accept(this);
		return n;
	}

	public Object visit(InitializeSimple n) {
		n.id.accept(this); // identifier
		n.assignExp = (Exp) n.assignExp.accept(this);
		return n;
	}

	public Object visit(InitializeArray n) {
		n.id.accept(this); // identifier
		n.arrayExp = (Exp) n.arrayExp.accept(this);
		n.assignExp = (Exp) n.assignExp.accept(this);
		return n;
	}

	public Object visit(IncrementSimple n) {
		n.id.accept(this); // identifier
		n.assignExp = (Exp) n.assignExp.accept(this);
		return n;
	}

	public Object visit(IncrementArray n) {
		n.id.accept(this); // identifier
		n.arrayExp = (Exp) n.arrayExp.accept(this);
		n.assignExp = (Exp) n.assignExp.accept(this);
		return n;
	}

	public Object visit(ElseIf n) {
		n.condition = (Exp) n.condition.accept(this);
		n.s = (Statement) n.s.accept(this);
		return n;
	}

	public Object visit(CaseListCase n) {
		n.caseExp = (Exp) n.caseExp.accept(this);
		n.s = (Statement) n.s.accept(this);
		n.caseList = (CaseList) n.caseList.accept(this);
		n.id.accept(this);
		return n;
	}

	public Object visit(CaseListDefault n) {
		n.s = (Statement) n.s.accept(this);
		return n;
	}

	public Object visit(ExpList n, Identifier id) {
		n.e = (Exp) n.e.accept(this);
		n.multipleExp = (ExpRestList) n.multipleExp.accept(this, id);
		return n;
	}

	public Object visit(ExpRest n) {
		n.e = (Exp) n.e.accept(this);
		return n;
	}

	public Object visit(FormalRestList n) {
		for (FormalRest f : n) {
			f.accept(this);
		}
		return n;
	}

	public Object visit(ElseIfList n) {
		for (ElseIf e : n) {
			e.accept(this);
		}
		return n;
	}

	public Object visit(StatementList n) {
		StatementList sl = new StatementList();
		for (Statement s : n) {
			sl.add((Statement)s.accept(this));
		}
		return sl;
	}

	public Object visit(VarDeclList n) {
		for (VarDecl v : n) {
			v.accept(this);
		}
		return n;
	}

	public Object visit(MethodDeclList n) {
		for (MethodDecl m : n) {
			m.accept(this);
		}
		return n;
	}

	public Object visit(ExpRestList n, Identifier id) {
		for (int i = 0; i < n.size(); i++) {
			ExpRest e = n.get(i);
			e = (ExpRest)e.accept(this);
		}
		return n;
	}

	public Object visit(DotArrayList n) {
		for (DotArray e : n) {
			if (e instanceof DotArrayArray) {
				e = (DotArrayArray) ((DotArrayArray) e).accept(this);
			} else if (e instanceof DotArrayMember) {
				((DotArrayMember)e).member = (Member) ((DotArrayMember) e).member.accept(this);
			}
		}
		return n;
	}

	public Object visit(DotArrayArray n) {
		return n.exp.accept(this);
	}

	public Object visit(AssignSimple n) {
		n.id = (Identifier)n.id.accept(this); // identifier
		Object o = ev.visit(n.assignment);
		if(o instanceof Integer)
		{
			n.assignment = integer((Integer) o);
			System.out.println("Optimizing: simplified assigning expression");
		}

		return n;
	}

	private Exp integer(Integer o) {
		return new Exp(new And(new Less(new Term(new NotFactor(new IntegerLiteral(o), new DotArrayList()), new Tlist()), new Llist()), new Alist()), new Elist());
	}

	public Object visit(AssignArray n) {
		n.id.accept(this); // identifier
		n.index = (Exp) n.index.accept(this);
		n.value = (Exp) n.value.accept(this);
		return n;
	}

	public Object visit(NewArray n) {
		n.e = (Exp) n.e.accept(this);
		return n;
	}

	public Object visit(NewObject n) {
		return n;
	}

	public Object visit(Not n) {
		if (n instanceof NotFactor)
			return ((NotFactor) n).accept(this);
		if (n instanceof NotSimple)
			return ((NotSimple) n).accept(this);
		return n;
	}

	public Object visit(Type n) {
		return n;
	}

	public Object visit(DotArrayMember n) {
		if (n.member instanceof MemberId) {
			return ((MemberId) n.member).accept(this);
		}
		return ((MemberLength) n.member).accept(this);
	}

	public Object visit(Exp n) {
		n.and = (And) n.and.accept(this);
		if (n.elist.and != null) {
			n.elist = (Elist) n.elist.accept(this);
			return n;
		}
		return n;
	}

	public Object visit(Elist n) {
		n.and = (And) n.and.accept(this);
		if (n.elist.and != null) {
			n.elist = (Elist) n.elist.accept(this);
			return n;
		}
		return n;
	}

	public Object visit(And n) {
		n.less = (Less) n.less.accept(this);
		if (n.alist.less != null) {
			n.alist = (Alist) n.alist.accept(this);
			return n;
		}
		return n;
	}

	public Object visit(Alist n) {
		n.less = (Less) n.less.accept(this);
		if (n.alist.less != null) {
			n.alist = (Alist) n.alist.accept(this);
			return n;
		}
		return n;
	}

	public Object visit(Less n) {
		n.term = (Term) n.term.accept(this);
		if ((n.llist != null) && ((n.llist instanceof LlistDifference) || (n.llist instanceof LlistSum))) {
			n.llist = (Llist) n.llist.accept(this);
			return n;
		}
		return n;
	}

	public Object visit(Llist n) {
		if (n instanceof LlistDifference)
			return visit((LlistDifference) n);
		else if (n instanceof LlistSum)
			return visit((LlistSum) n);
		return n;
	}

	public Object visit(LlistDifference n) {
		n.term = (Term) n.term.accept(this);
		if ((n.llist != null) && ((n.llist instanceof LlistDifference) || (n.llist instanceof LlistSum))) {
			Equal eq = new Equal();
			if((boolean) eq.visit(n.term, n.llist)){
				return new IntegerLiteral(0);
			}
			n.llist = (Llist) n.llist.accept(this);
			return n;
		}
		return n;
	}

	public Object visit(LlistSum n) {
		n.term = (Term) n.term.accept(this);
		if ((n.llist != null) && ((n.llist instanceof LlistDifference) || (n.llist instanceof LlistSum))) {
			n.llist = (Llist) n.llist.accept(this);
			return n;
		}
		return n;
	}

	public Object visit(Term n) {
		n.not = (Not) n.not.accept(this);
		if (n.tlist.not != null) {
			n.tlist = (Tlist) n.tlist.accept(this);
			return n;
		}
		return n;
	}

	public Object visit(Tlist n) {
		n.not = (Not) n.not.accept(this);
		if (n.tlist.not != null) {
			n.tlist = (Tlist) n.tlist.accept(this);
			return n;
		}
		return n;
	}

	public Object visit(NotFactor n) {
		n.factor = (Factor) n.factor.accept(this);
		n.dotList = (DotArrayList) n.dotList.accept(this);
		return n;
	}

	public Object visit(FactorNew n) {
		if (n.newObject instanceof NewObject)
			return ((NewObject) n.newObject).accept(this);
		else
			return ((NewArray) n.newObject).accept(this);
	}

	public Object visit(MemberId n) {
		n.expList = (ExpList) n.expList.accept(this, n.id);
		return n;
	}

	public Object visit(ClassDecl n) {
		return n;
	}

	public Object visit(Statement n) {
		return n;
	}

	public Object visit(Identifier n) {
		return n;
	}

	public Object visit(InitializationStm n) {
		return n;
	}

	public Object visit(IncrementStm n) {
		return n;
	}

	public Object visit(CaseList n) {
		return n;
	}
	public Object visit(DotArray n) {
		return n;
	}

	public Object visit(Member n) {
		return n;
	}

	public Object visit(MemberLength n) {
		return n;
	}
	public Object visit(IntArrayType n) {
		return n;
	}

	public Object visit(BooleanType n) {
		return n;
	}

	public Object visit(IntegerType n) {
		return n;
	}

	public Object visit(IdentifierType n) {
		return n;
	}

	public Object visit(IntegerLiteral n) {
		return n;
	}

	public Object visit(True n) {
		return n;
	}

	public Object visit(False n) {
		return n;
	}

	public Object visit(IdentifierExp n) {
		return n;
	}

	public Object visit(This n) {
		return n;
	}

	public Object visit(NotSimple n) {
		return n;
	}

	public Object visit(Factor n) {
		return n;
	}

	public Object visit(New n) {
		return n;
	}

	@Override
	public Object visit(Object o1, Object o2) {
		return null;
	}

}
