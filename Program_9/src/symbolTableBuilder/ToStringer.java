package symbolTableBuilder;

public class ToStringer implements Visitor {

	@Override
	public Object visit(Program n) {
		String m = "Program: " + n.mainClass.accept(this).toString();
		m += n.classDecls.accept(this);
		return m;
	}

	@Override
	public Object visit(MainClass n) {
		String s = "MainClass: " + n.className.accept(this).toString();
		s += "Arguments: " + n.args.accept(this);
		s += n.v.accept(this);
		s += n.stmt.accept(this);
		return s;
	}

	@Override
	public Object visit(ClassDecl n) {
		return null;
	}

	@Override
	public Object visit(ClassDeclDeffSimple n) {
		String s = "ClassName: " + n.className.name;
		s += n.fields.accept(this);
		s += n.methods.accept(this);
		return s;
	}

	@Override
	public Object visit(ClassDeclDeffExtend n) {
		String s = "ClassName: " + n.className.name;
		s += " extends " + n.extendedClass.accept(this);
		s += n.variableList.accept(this);
		s += n.methodList.accept(this);
		return s;
	}

	@Override
	public Object visit(ClassDeclList n) {
		String s = "";
		for(ClassDecl d : n){
			s += d.accept(this);
		}
		return s;
	}

	@Override
	public Object visit(VarDecl n) {
		String s = "VarDecl: " + n.type.accept(this).toString();
		s += n.variableType.accept(this, n.type);
		return s;
	}

	@Override
	public Object visit(VarDeclType n, Type t) {
		String s = n.variableName.accept(this).toString();
		if(n.exp != null){
			s += n.exp.accept(this);
		}
		
		return s;
	}

	@Override
	public Object visit(VarDeclList n) {
		String s = "";
		for(VarDecl v : n){
			s += v.accept(this);
		}
		return s;
	}

	@Override
	public Object visit(MethodDecl n) {
		String s = " MethodName: " + n.methodName.accept(this).toString();
		s += n.type.accept(this);
		s += n.parameters.accept(this);
		s += n.variables.accept(this);
		s += n.statement.accept(this);
		s += " Return: " + n.expReturn.accept(this);
		return s;
	}

	@Override
	public Object visit(MethodDeclList n) {
		String s = "";
		for(MethodDecl m : n){
			s += m.accept(this);
		}
		return s;
	}

	@Override
	public Object visit(FormalList n) {
		String s = "Params: ";
		s += n.type.accept(this);
		s += n.parameterName.accept(this);
		s += n.moreParams.accept(this);
		return s;
	}

	@Override
	public Object visit(FormalRest n) {
		String s = n.type.accept(this).toString();
		s += n.paramName.accept(this);
		return s;
	}

	@Override
	public Object visit(Statement n) {
		return null;
	}

	@Override
	public Object visit(Block n) {
		String s = "";
		s += n.sl.accept(this);
		return s;
	}

	@Override
	public Object visit(If n) {
		String s = "If ";
		s += n.condition.accept(this) + "then ";
		s += n.s.accept(this);
		s += n.elseIf.accept(this);
		return s;
	}

	@Override
	public Object visit(ElseIf n) {
		String s = "Else If: " + n.condition.accept(this).toString();
		s += n.s.accept(this);
		return s;
	}

	@Override
	public Object visit(Do n) {
		String s = "Do: " + n.s.accept(this).toString();
		s += "While: " + n.condition.accept(this).toString();
		return s;
	}

	@Override
	public Object visit(While n) {
		String s = "While: " + n.condition.accept(this).toString();
		s += n.s.accept(this);
		return s;
	}

	@Override
	public Object visit(For n) {
		String s = "For: " + n.initialize.accept(this).toString();
		s += n.e.accept(this);
		s += n.increment.accept(this);
		s += n.s.accept(this);
		return s;
	}

	@Override
	public Object visit(Switch n) {
		String s = "Switch: " + n.id.accept(this).toString();
		s += n.caseList.accept(this);
		return s;
	}

	@Override
	public Object visit(CaseListCase n) {
		String s = "Case: " + n.caseExp.accept(this).toString();
		s += n.s.accept(this);
		s += n.caseList.accept(this);
		return s;
	}

	@Override
	public Object visit(CaseListDefault n) {
		String s = "Default: ";
		s += n.s.accept(this);
		return s;
	}

	@Override
	public Object visit(Print n) {
		String s = "Print: " + n.statementToPrint.accept(this).toString();
		return s;
	}

	@Override
	public Object visit(InitializeSimple n) {
		String s = n.id.accept(this).toString();
		s += "= " + n.assignExp.accept(this);
		return s;
	}

	@Override
	public Object visit(InitializeArray n) {
		String s = n.id.accept(this).toString();
		s += n.arrayExp.accept(this);
		s += n.assignExp.accept(this);
		return s;
	}

	@Override
	public Object visit(IncrementSimple n) {
		String s = n.id.accept(this).toString();
		s += "= " + n.assignExp.accept(this);
		return s;
	}

	@Override
	public Object visit(IncrementArray n) {
		String s = n.id.accept(this).toString();
		s += n.arrayExp.accept(this);
		s += n.assignExp.accept(this);
		return s;
	}

	@Override
	public Object visit(ExpList n, Identifier methodId) {
		String s = n.e.accept(this).toString();
		s += n.multipleExp.accept(this, methodId);
		return s;
	}

	@Override
	public Object visit(ExpRest n) {
		String s = n.e.accept(this).toString();
		return s;
	}

	@Override
	public Object visit(StatementList n) {
		String s = "";
		for(Statement st : n){
			s += st.accept(this);
		}
		return s;
	}

	@Override
	public Object visit(InitializationStm n) {
		return null;
	}

	@Override
	public Object visit(IncrementStm n) {
		return null;
	}

	@Override
	public Object visit(CaseList n) {
		return null;
	}

	@Override
	public Object visit(FormalRestList n) {
		String s = "";
		for(FormalRest fr: n)
			s += fr.accept(this);
		
		return s;
	}

	@Override
	public Object visit(ElseIfList n) {
		String s = "";
		for(ElseIf ef: n)
			s += ef.accept(this);
		
		return s;
	}

	@Override
	public Object visit(ExpRestList n, Identifier id) {
		String s = "";
		for(ExpRest er: n)
		{
			s += er.accept(this);
		}
		return s;
	}

	@Override
	public Object visit(DotArrayList n) {

		String s = "";
		for(DotArray da: n)
		{
			s += da.accept(this);
		}
		return s;
	}

	@Override
	public Object visit(DotArray n) {
		return "";
	}

	@Override
	public Object visit(DotArrayArray n) {
		return "";
	}

	@Override
	public Object visit(Member n) {
		return "";
	}

	@Override
	public Object visit(MemberLength n) {
		return n.length.accept(this).toString();
	}

	@Override
	public Object visit(Identifier n) {
		return n.name + " ";
	}

	@Override
	public Object visit(Type n) {
		return null;
	}

	@Override
	public Object visit(IntArrayType n) {
		return "IntArray ";
	}

	@Override
	public Object visit(BooleanType n) {
		return "bool ";
	}

	@Override
	public Object visit(IntegerType n) {
		return "int ";
	}

	@Override
	public Object visit(IdentifierType n) {
		return n.id + " ";
	}

	@Override
	public Object visit(AssignSimple n) {
		String s = "Assign: " + n.id.accept(this).toString() + "= ";
		s += n.assignment.accept(this);
		return s;
	}

	@Override
	public Object visit(AssignArray n) {
		String s = n.id.accept(this).toString();
		s += n.index.accept(this);
		s += n.value.accept(this);
		return s;
	}

	@Override
	public Object visit(MemberId n) {
		String s = n.id.accept(this).toString();
		s += n.expList.accept(this, null);
		return s;
	}

	@Override
	public Object visit(Exp n) {
		String s = n.and.accept(this).toString();
		s += n.elist.accept(this);
		return s;
	}

	@Override
	public Object visit(Elist n) {
		if(n.and == null && n.elist == null){
			return "";
		}
		String s = "&& " + n.and.accept(this).toString();
		s += n.elist.accept(this);
		return s;
	}

	@Override
	public Object visit(And n) {
		String s = n.less.accept(this).toString();
		if(n.alist == null)
			return s;
		s += n.alist.accept(this);
		return s;
	}

	@Override
	public Object visit(Alist n) {
		if(n.alist == null && n.less == null){
			return "";
		}
		String s = "< " + n.less.accept(this).toString();
		s += n.alist.accept(this);
		return s;
	}

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

	@Override
	public Object visit(Object o1, Object o2) {
		return null;
	}

}
