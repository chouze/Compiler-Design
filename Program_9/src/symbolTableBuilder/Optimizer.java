package symbolTableBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * SymbolTableTypeCheck
 * 
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 *
 */

public class Optimizer implements Visitor {
	
	public Optimizer() 
	{

	}

	public Object visit(Program n) {
		n.mainClass = (MainClass) n.mainClass.accept(this);
		n.classDecls = (ClassDeclList) n.classDecls.accept(this);
		return n;
	}

	public Object visit(MainClass n) {
		n.className.accept(this); //identifier, no need to optimize
		n.args.accept(this); //identifier
		n.v.accept(this); //variables, no need to optimize
		n.stmt = (Statement)n.stmt.accept(this); 
		return n;
	}

	public Object visit(ClassDeclDeffSimple n) {
		n.className.accept(this); //identifier
		n.fields.accept(this); //variables
		n.methods = (MethodDeclList)n.methods.accept(this);
		return n;
	}

	public Object visit(ClassDeclDeffExtend n) {
		n.className.accept(this); //identifier
		n.extendedClass.accept(this); //identifier
		n.variableList.accept(this); //variables
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
		t.accept(this); //Type, no need to optimize
		n.variableType.accept(this, t); //variables
		return n;
	}

	public Object visit(VarDeclType n, Type t) {
		if (n.exp != null) {
			n.exp = (Exp) n.exp.accept(this); 
		}
		return n;
	}

	public Object visit(MethodDecl n) {
		n.type.accept(this); //identifier
		n.methodName.accept(this); //identifier
		n.parameters.accept(this); //parameters, no need to optimize
		n.variables.accept(this); //variables
		n.statement = (Statement) n.statement.accept(this); 
		n.expReturn = (Exp)n.expReturn.accept(this);
		return n;
	}

	public Object visit(FormalList n) {
		n.type.accept(this); //type
		n.parameterName.accept(this); //identifier
		n.moreParams.accept(this); //more types/identifiers, no need to optimize
		return n;
	}

	public Object visit(FormalRest n) {
		n.type.accept(this); //type
		n.paramName.accept(this); //identifier
		return n;
	}

	public Object visit(Block n) {
		n.sl = (StatementList)n.sl.accept(this);
		return n;
	}

	public Object visit(If n) {
		n.condition = (Exp)n.condition.accept(this);
		n.s = (Statement)n.s.accept(this);
		n.elseIf = (ElseIfList)n.elseIf.accept(this);
		return n;
	}

	public Object visit(Do n) {
		n.s = (Statement)n.s.accept(this);
		n.condition = (Exp)n.condition.accept(this);
		return n;
	}

	public Object visit(While n) {
		n.condition = (Exp)n.condition.accept(this);
		n.s = (Statement)n.s.accept(this);
		return n;
	}

	public Object visit(For n) {
		n.initialize = (InitializationStm)n.initialize.accept(this);
		n.e = (Exp)n.e.accept(this);
		n.increment = (IncrementStm)n.increment.accept(this);
		n.s = (Statement)n.s.accept(this);
		return n;
	}

	public Object visit(Switch n) {
		n.id.accept(this); //identifier
		n.caseDefault = (CaseList)n.caseDefault.accept(this);
		return n;
	}

	public Object visit(Print n) {
		n.statementToPrint = (Exp) n.statementToPrint.accept(this);
		return n;
	}

	public Object visit(InitializeSimple n) {
		n.id.accept(this); //identifier
		n.assignExp = (Exp)n.assignExp.accept(this);
		return n;
	}

	public Object visit(InitializeArray n) {
		n.id.accept(this); //identifier
		n.arrayExp = (Exp)n.arrayExp.accept(this);
		n.assignExp = (Exp)n.assignExp.accept(this);
		return n;
	}

	public Object visit(IncrementSimple n) {
		n.id.accept(this); //identifier
		n.assignExp = (Exp)n.assignExp.accept(this);
		return n;
	}

	public Object visit(IncrementArray n) {
		n.id.accept(this); //identifier
		n.arrayExp = (Exp)n.arrayExp.accept(this);
		n.assignExp = (Exp) n.assignExp.accept(this);
		return null;
	}

	public Object visit(ElseIf n) {
		n.condition = (Exp) n.condition.accept(this);
		n.s = (Statement) n.s.accept(this);
		return n;
	}

	public Object visit(CaseListCase n) {
		n.caseExp = (Exp)n.caseExp.accept(this);
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

	public Object visit(ClassDecl n) {
		return n; //should never be called
	}

	

	public Object visit(Statement n) {
		return n; //should never be called
	}

	public Object visit(Identifier n) {
		return n;
	}



	public Object visit(InitializationStm n) {
		return n; //should never be called
	}

	public Object visit(IncrementStm n) {
		return n; //should never be called
	}

	public Object visit(CaseList n) {
		return n; //should never be called
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
		for (Statement s : n) {
			s.accept(this);
		}
		return n;
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
/*
 * ExpRestList, not qure how to visit it just yet, needs work
 */
	public Object visit(ExpRestList n, Identifier id) 
	{	
		for (int i = 0; i < n.size(); i++) {
			//(String)expRestList.get(i).accept(this);
		}
		return n;
	}

	public Object visit(DotArrayList n) {
		for (DotArray e : n) {
			if (e instanceof DotArrayArray) {
				((DotArrayArray) e).accept(this);
			} else if (e instanceof DotArrayMember) {
				((DotArrayMember) e).accept(this);
			}
		}
		return n;
	}

	public Object visit(DotArray n) {
		return n; //
	}

	public Object visit(DotArrayArray n) {
		return n.exp.accept(this);
	}

	public Object visit(Member n) {
		// does nothing
		return n;
	}

	public Object visit(MemberLength n) {
		return n;
	}

	public Object visit(AssignSimple n) {
		symTab = symTabMethod;
		symTab.check(n.id, IdType.VARIABLE);
		String tLeft = symTab.getType(n.id);
		n.id.accept(this);
		String tRight = (String)n.assignment.accept(this);
		symTab.check(tLeft, tRight); // should have the same type
		return n;
	}

	public Object visit(AssignArray n) {
		symTab.check(n.id, "IntArrayType");
		n.id.accept(this);
		String indexType = (String)n.index.accept(this);
		String valueType = (String)n.value.accept(this);
		if (!valueType.equals("IntegerType")) {
			System.out.println("Value assigned to int array should be of type int");
		}
		if (!indexType.equals("IntegerType"))
			System.out.println("Array index should be int");
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
		if(symTabProg.get(new Identifier(n.s)) != null)
		{
			symTab = symTabProg.getChild(n.s);
			return n;
		}

		symTab = symTabMethod;
		Binding bind = symTab.get(new Identifier(n.s));

		return n; // got binding, return the type
	}

	public Object visit(This n) {
		return n;
	}

	public Object visit(NewArray n) {
		symTab.check((String)n.e.accept(this), "IntegerType");
		return n;
	}

	public Object visit(NewObject n) {
		symTab = symTabProg.getChild(n.id.name);
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
		String left, right;
		left = (String)n.and.accept(this);
		if (n.elist.and != null) {
			right = (String)n.elist.accept(this);
			if (!(left.equals(right)))
				System.err.println("Mismatching Types in Exp");
			return n;
		}
		return n;
	}

	public Object visit(Elist n) {
		String left, right;
		left = (String)n.and.accept(this);
		if (n.elist.and != null) {
			right = (String)n.elist.accept(this);
			if (!(left.equals(right)))
				System.err.println("Mismatching Types in Elist");
			return n;
		}
		return n;
	}

	public Object visit(And n) {
		String left, right;
		left = (String)n.less.accept(this);
		if (n.alist.less != null) 
		{
			right = (String)n.alist.accept(this);
			if (!(left.equals(right)))
				System.err.println("Mismatching Types in And");
			return "BooleanType";
		}
		return n;
	}

	public Object visit(Alist n) {
		String left, right;
		left = (String)n.less.accept(this);
		if (n.alist.less != null) {
			right = (String)n.alist.accept(this);
			if (!(left.equals(right)))
				System.err.println("Mismatching Types in Alist");
			return n;
		}
		return n;
	}

	public Object visit(Less n) {
		String left, right;
		left = (String)n.term.accept(this);
		if ((n.llist != null) && ((n.llist instanceof LlistDifference) || (n.llist instanceof LlistSum))) {
			right = (String)n.llist.accept(this);
			if (!(left.equals(right)))
				System.err.println("Mismatching Types in Less");
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
		String left, right;
		left = (String)n.term.accept(this);
		if ((n.llist != null) && ((n.llist instanceof LlistDifference) || (n.llist instanceof LlistSum))) {
			right = (String)n.term.accept(this);
			if (!(left.equals(right)))
				System.err.println("Mismatching Types in LlistDifference");
			return n;
		}
		return n;
	}

	public Object visit(LlistSum n) {
		String left, right;
		left = (String)n.term.accept(this);
		if ((n.llist != null) && ((n.llist instanceof LlistDifference) || (n.llist instanceof LlistSum))) {
			right = (String)n.term.accept(this);
			if (!(left.equals(right)))
				System.err.println("Mismatching Types in LlistSum");
			return n;
		}
		return n;
	}

	public Object visit(Term n) {
		String left, right;
		left = (String)n.not.accept(this);
		if (n.tlist.not != null) {
			right = (String)n.tlist.accept(this);
			if (!(left.equals(right)))
				System.err.println("Mismatching Types in Term");
			return n;
		}
		return n;
	}

	public Object visit(Tlist n) {
		String left, right;
		left = (String)n.not.accept(this);
		if (n.tlist.not != null) {
			right = (String)n.tlist.accept(this);
			if (!(left.equals(right)))
				System.err.println("Mismatching Types in Term");
			return n;
		}
		return n;

	}

	public Object visit(NotFactor n) {
		String returnType = (String)n.factor.accept(this);
		if (!(n.dotList.isEmpty())) {
			return n.dotList.get(n.dotList.size() - 1).accept(this);
		} else
			return n;
	}

	public Object visit(NotSimple n) {
		return n;
	}

	public Object visit(Factor n) {
		return n;
	}

	public Object visit(FactorNew n) {
		if (n.newObject instanceof NewObject)
			return ((NewObject) n.newObject).accept(this);
		else
			return ((NewArray) n.newObject).accept(this);
	}

	public Object visit(MemberId n) {
		if (n.expList != null) {
			n.expList.accept(this, n.id);
		}
		return n;
	}

	public Object visit(New n) {
		return n;
	}

	@Override
	public Object visit(Object o1, Object o2) {
		
		return n;
	}

}
