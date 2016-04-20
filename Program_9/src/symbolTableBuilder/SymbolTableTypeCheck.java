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

public class SymbolTableTypeCheck implements Visitor {
	public SymbolTable symTab, symTabMethod, symTabClass, symTabProg;
	public Map<String, SymbolTable> tableMap = new HashMap<String, SymbolTable>();

	public SymbolTableTypeCheck() 
	{

	}

	public Object visit(Program n) {
		symTabProg = n.symTab;
		n.mainClass.accept(this);
		n.classDecls.accept(this);
		return null;
	}

	public Object visit(MainClass n) {
		symTab = symTabClass = n.symTab;
		n.className.accept(this);
		n.args.accept(this);
		n.v.accept(this);
		n.stmt.accept(this);
		return null;
	}

	public Object visit(ClassDeclDeffSimple n) {
		symTab = symTabClass = n.symTab;
		n.className.accept(this);
		n.fields.accept(this); // enter fields into the symbol table
		n.methods.accept(this);
		return null;
	}

	public Object visit(ClassDeclDeffExtend n) {
		symTab = symTabClass = n.symTab;

		symTabProg.check(n.extendedClass, IdType.CLASS);
		n.className.accept(this);
		n.extendedClass.accept(this);
		n.variableList.accept(this);
		return null;
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
		return null;
	}

	public Object visit(VarDecl n) {
		Type t = n.type;
		t.accept(this);
		n.variableType.accept(this, t);
		return null;
	}

	public Object visit(VarDeclType n, Type t) {
		symTab.check(n.variableName, IdType.VARIABLE);
		if (n.exp != null) {
			symTab.check(n.variableName, (String)n.exp.accept(this)); // should have the same type
		}
		return null;
	}

	public Object visit(MethodDecl n) {
		symTab = symTabMethod = n.symTab;
		String methodReturnType = (String)n.type.accept(this);
		n.methodName.accept(this);
		n.parameters.accept(this);
		n.variables.accept(this);
		n.statement.accept(this);
		String expReturnType = (String)n.expReturn.accept(this);
		symTab.check(methodReturnType, expReturnType); // if the return type of the
		return null;
	}

	public Object visit(FormalList n) {
		n.type.accept(this);
		n.parameterName.accept(this);
		n.moreParams.accept(this);
		return null;
	}

	public Object visit(FormalRest n) {
		n.type.accept(this);
		n.paramName.accept(this);
		return null;
	}

	public Object visit(Block n) {
		n.sl.accept(this);

		return null;
	}

	public Object visit(If n) {
		symTab.check((String)n.condition.accept(this), "BooleanType");
		n.s.accept(this);
		n.elseIf.accept(this);
		return null;
	}

	public Object visit(Do n) {
		symTab.check((String)n.condition.accept(this), "BooleanType");
		n.s.accept(this);
		return null;
	}

	public Object visit(While n) {
		symTab.check((String)n.condition.accept(this), "BooleanType");
		n.s.accept(this);
		return null;
	}

	public Object visit(For n) {
		n.initialize.accept(this);
		n.e.accept(this);
		n.increment.accept(this);
		n.s.accept(this);
		return null;
	}

	public Object visit(Switch n) {
		n.id.accept(this);
		n.caseList.accept(this);
		return null;
	}

	public Object visit(Print n) {
		n.statementToPrint.accept(this);
		return null;
	}

	public Object visit(InitializeSimple n) {
		symTab.check(n.id, IdType.VARIABLE);
		String tLeft = symTab.getType(n.id);
		String tRight = (String)n.assignExp.accept(this);
		symTab.check(tLeft, tRight); // should have the same type
		return null;
	}

	public Object visit(InitializeArray n) {
		n.id.accept(this);
		n.arrayExp.accept(this);
		n.assignExp.accept(this);
		symTab.check(n.id, "IntArrayType");
		n.id.accept(this);
		String indexType = (String)n.arrayExp.accept(this);
		String valueType = (String)n.assignExp.accept(this);
		if (!valueType.equals("IntegerType")) {
			System.out.println("Value assigned to int array should be of type int");
		}
		if (!indexType.equals("IntegerType"))
			System.out.println("Array index should be int");
		return null;
	}

	public Object visit(IncrementSimple n) {
		symTab.check(n.id, IdType.VARIABLE);
		String tLeft = symTab.getType(n.id);
		n.id.accept(this);
		String tRight = (String)n.assignExp.accept(this);
		symTab.check(tLeft, tRight); // should have the same type
		n.id.accept(this);
		n.assignExp.accept(this);
		return null;
	}

	public Object visit(IncrementArray n) {
		n.id.accept(this);
		n.arrayExp.accept(this);
		n.assignExp.accept(this);
		symTab.check(n.id, "IntArrayType");
		n.id.accept(this);
		String indexType = (String)n.arrayExp.accept(this);
		String valueType = (String)n.assignExp.accept(this);
		if (!valueType.equals("IntegerType")) {
			System.out.println("Value assigned to int array should be of type int");
		}
		if (!indexType.equals("IntegerType"))
			System.out.println("Array index should be int");
		n.id.accept(this);
		n.arrayExp.accept(this);
		n.assignExp.accept(this);
		return null;
	}

	public Object visit(ElseIf n) {
		symTab.check((String)n.condition.accept(this), "BooleanType");
		n.s.accept(this);
		return null;
	}

	public Object visit(CaseListCase n) {
		String caseExp;

		caseExp = (String)n.caseExp.accept(this);
		symTab.check(n.id, caseExp);
		n.s.accept(this);
		n.caseList.accept(this);
		return null;
	}

	public Object visit(CaseListDefault n) {
		n.s.accept(this);
		return null;
	}

	public Object visit(ExpList n, Identifier id) {

		Binding bind = symTab.get(id);
		symTab.check((String)n.e.accept(this), bind.parms.get(0));
		n.multipleExp.accept(this, id);
		return null;
	}

	public Object visit(ExpRest n) {
		return n.e.accept(this);
	}

	public Object visit(ClassDecl n) {
		return null;
	}

	public Object visit(VarDeclList n) {
		for (VarDecl v : n) {
			v.accept(this);
		}
		return null;
	}

	public Object visit(MethodDeclList n) {
		for (MethodDecl m : n) {
			m.accept(this);
		}
		return null;
	}

	public Object visit(Statement n) {
		return null;

	}

	public Object visit(Identifier n) {
		SymbolTable ST;
		// First check current symbol table for identifier
		// If not there, check class symbolTable
		// If not there, check Program symbol table
		if (symTab.find(n))
			ST = symTab;
		else if (symTabClass.find(n))
			ST = symTabClass;
		else if (symTabProg.find(n))
			ST = symTabProg;
		else {
			System.err.println("Identifier not found: " + n);
			return null;
		}
		IdType usage = ST.get(n).usage;
		if (!(usage.equals(IdType.CLASS)))
			return ST.getType(n);
		return n.name;
	}

	public Object visit(StatementList n) {
		for (Statement s : n) {
			s.accept(this);
		}
		return null;
	}

	public Object visit(InitializationStm n) {
		// does nothing
		return null;
	}

	public Object visit(IncrementStm n) {
		// does nothing
		return null;
	}

	public Object visit(CaseList n) {
		// does nothing
		return null;
	}

	public Object visit(FormalRestList formalRestList) {
		for (FormalRest f : formalRestList) {
			f.accept(this);
		}
		return null;
	}

	public Object visit(ElseIfList elseIfList) {
		for (ElseIf e : elseIfList) {
			e.accept(this);
		}
		return null;
	}

	public Object visit(ExpRestList expRestList, Identifier id) 
	{	
		Binding bind;
		if(symTab.get(id) != null)
			bind = symTab.get(id);
		else if(symTabMethod.get(id) != null)
			bind = symTabMethod.get(id);
		else if (symTabClass.get(id) != null)
			bind = symTabClass.get(id);
		else
			bind = symTabProg.get(id);
		for (int i = 0; i < expRestList.size(); i++) {
			String typeInSymTab = bind.parms.get(i + 1);
			String typeOfExp = (String)expRestList.get(i).accept(this);
			symTab.check(typeInSymTab, typeOfExp);
		}
		return null;
	}

	public Object visit(DotArrayList dotArrayList) {
		for (DotArray e : dotArrayList) {
			if (e instanceof DotArrayArray) {
				((DotArrayArray) e).accept(this);
			} else if (e instanceof DotArrayMember) {
				((DotArrayMember) e).accept(this);
			}
		}
		return null;
	}

	public Object visit(DotArray n) {
		// does nothing
		return null;
	}

	public Object visit(DotArrayArray n) {
		return n.exp.accept(this);
	}

	public Object visit(Member n) {
		// does nothing
		return null;
	}

	public Object visit(MemberLength n) {
		return "IntegerType";
	}

	public Object visit(AssignSimple n) {
		symTab = symTabMethod;
		symTab.check(n.id, IdType.VARIABLE);
		String tLeft = symTab.getType(n.id);
		n.id.accept(this);
		String tRight = (String)n.assignment.accept(this);
		symTab.check(tLeft, tRight); // should have the same type
		return tRight;
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
		return valueType;
	}

	public Object visit(IntArrayType n) {
		return n.getClass().getSimpleName();
	}

	public Object visit(BooleanType n) {
		return n.getClass().getSimpleName();
	}

	public Object visit(IntegerType n) {
		return n.getClass().getSimpleName();
	}

	public Object visit(IdentifierType n) {
		return n.id;
	}

	public Object visit(IntegerLiteral n) {
		return "IntegerType";
	}

	public Object visit(True n) {
		return "BooleanType";
	}

	public Object visit(False n) {
		return "BooleanType";
	}

	public Object visit(IdentifierExp n) {
		if(symTabProg.get(new Identifier(n.s)) != null)
		{
			symTab = symTabProg.getChild(n.s);
			return n.s;
		}

		symTab = symTabMethod;
		Binding bind = symTab.get(new Identifier(n.s));

		return bind.getType(); // got binding, return the type
	}

	public Object visit(This n) {
		return n.getClass().getSimpleName();
	}

	public Object visit(NewArray n) {
		symTab.check((String)n.e.accept(this), "IntegerType");
		return "IntArrayType";
	}

	public Object visit(NewObject n) {
		symTab = symTabProg.getChild(n.id.name);
		return n.id.name;
	}

	public Object visit(Not n) {
		if (n instanceof NotFactor)
			return ((NotFactor) n).accept(this);
		if (n instanceof NotSimple)
			return ((NotSimple) n).accept(this);

		return null;
	}

	public Object visit(Type n) {
		return null;
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
			return right;
		}
		return left;
	}

	public Object visit(Elist n) {
		String left, right;
		left = (String)n.and.accept(this);
		if (n.elist.and != null) {
			right = (String)n.elist.accept(this);
			if (!(left.equals(right)))
				System.err.println("Mismatching Types in Elist");
			return right;
		}
		return left;
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
		return left;
	}

	public Object visit(Alist n) {
		String left, right;
		left = (String)n.less.accept(this);
		if (n.alist.less != null) {
			right = (String)n.alist.accept(this);
			if (!(left.equals(right)))
				System.err.println("Mismatching Types in Alist");
			return right;
		}
		return left;
	}

	public Object visit(Less n) {
		String left, right;
		left = (String)n.term.accept(this);
		if ((n.llist != null) && ((n.llist instanceof LlistDifference) || (n.llist instanceof LlistSum))) {
			right = (String)n.llist.accept(this);
			if (!(left.equals(right)))
				System.err.println("Mismatching Types in Less");
			return right;
		}
		return left;
	}

	public Object visit(Llist n) {
		if (n instanceof LlistDifference)
			return visit((LlistDifference) n);
		else if (n instanceof LlistSum)
			return visit((LlistSum) n);
		return null;
	}

	public Object visit(LlistDifference n) {
		String left, right;
		left = (String)n.term.accept(this);
		if ((n.llist != null) && ((n.llist instanceof LlistDifference) || (n.llist instanceof LlistSum))) {
			right = (String)n.term.accept(this);
			if (!(left.equals(right)))
				System.err.println("Mismatching Types in LlistDifference");
			return right;
		}
		return left;
	}

	public Object visit(LlistSum n) {
		String left, right;
		left = (String)n.term.accept(this);
		if ((n.llist != null) && ((n.llist instanceof LlistDifference) || (n.llist instanceof LlistSum))) {
			right = (String)n.term.accept(this);
			if (!(left.equals(right)))
				System.err.println("Mismatching Types in LlistSum");
			return right;
		}
		return left;
	}

	public Object visit(Term n) {
		String left, right;
		left = (String)n.not.accept(this);
		if (n.tlist.not != null) {
			right = (String)n.tlist.accept(this);
			if (!(left.equals(right)))
				System.err.println("Mismatching Types in Term");
			return right;
		}
		return left;
	}

	public Object visit(Tlist n) {
		String left, right;
		left = (String)n.not.accept(this);
		if (n.tlist.not != null) {
			right = (String)n.tlist.accept(this);
			if (!(left.equals(right)))
				System.err.println("Mismatching Types in Term");
			return right;
		}
		return left;

	}

	public Object visit(NotFactor n) {
		String returnType = (String)n.factor.accept(this);
		if (!(n.dotList.isEmpty())) {
			return n.dotList.get(n.dotList.size() - 1).accept(this);
		} else
			return returnType;
	}

	public Object visit(NotSimple n) {
		return n.not.accept(this);
	}

	public Object visit(Factor n) {
		return null;
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
		return n.id.accept(this);
	}

	public Object visit(New n) {
		return null;
	}

	@Override
	public Object visit(Object o1, Object o2) {
		
		return null;
	}

}
