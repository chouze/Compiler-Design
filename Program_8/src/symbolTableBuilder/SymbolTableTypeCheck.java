package symbolTableBuilder;

import java.util.HashMap;
import java.util.List;
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

	// public String thisClass; // need to save the class name in order to
	// search
	// it's symbol table for a variable not found in
	// method

	// how do I search the symbol table of other classes for their methods?
	public Map<String, SymbolTable> tableMap = new HashMap<String, SymbolTable>();

	public SymbolTableTypeCheck() {
		// symTabProg = new SymbolTable();

	}

	public void visit(Program n) {
		symTabProg = n.symTab;
		n.mainClass.accept(this);
		n.classDecls.accept(this);
	}

	public void visit(MainClass n) {
		symTab = symTabClass = n.symTab;
		n.className.accept(this);
		n.args.accept(this);
		n.v.accept(this);
		n.stmt.accept(this);
	}

	public void visit(ClassDeclDeffSimple n) {
		symTab = symTabClass = n.symTab;

		n.className.accept(this);
		n.fields.accept(this); // enter fields into the symbol table
		n.methods.accept(this);
		// thisClass = n.className.name;
	}

	public void visit(ClassDeclDeffExtend n) {
		symTab = symTabClass = n.symTab;

		symTabProg.check(n.extendedClass, IdType.CLASS);
		n.className.accept(this);
		n.extendedClass.accept(this);
		n.variableList.accept(this);
	}

	public void visit(ClassDeclList n) {
		for (ClassDecl c : n) {
			if (c instanceof ClassDeclDeffSimple) {
				((ClassDeclDeffSimple) c).accept(this);
			}

			if (c instanceof ClassDeclDeffExtend) {
				((ClassDeclDeffExtend) c).accept(this);
			}
		}
	}

	public void visit(VarDecl n) {
		Type t = n.type;
		t.accept(this);
		n.variableType.accept(this, t);
	}

	public void visit(VarDeclType n, Type t) {
		// String eType = null;
		// String tLeft = symTab.getType(n.variableName);
		// n.variableName.accept(this); <---not needed to accept an identifier
		symTab.check(n.variableName, IdType.VARIABLE);
		if (n.exp != null) {
			symTab.check(n.variableName, n.exp.accept(this)); // should have the
																// same type
		}
	}

	public void visit(MethodDecl n) {
		symTab = symTabMethod = n.symTab;
		String methodReturnType = n.type.accept(this);
		n.methodName.accept(this);
		n.parameters.accept(this);
		n.variables.accept(this);
		n.statements.accept(this);
		String expReturnType = n.expReturn.accept(this);
		symTab.check(methodReturnType, expReturnType); // if the return type of
														// the
	}

	public void visit(FormalList n) {
		n.type.accept(this);
		n.parameterName.accept(this);
		n.moreParams.accept(this);
	}

	public void visit(FormalRest n) {
		n.type.accept(this);
		n.paramName.accept(this);
	}

	public void visit(Block n) {
		n.sl.accept(this);
	}

	public void visit(If n) {
		symTab.check(n.condition.accept(this), "BOOLEAN");
		n.s.accept(this);
		n.elseIf.accept(this);
	}

	public void visit(Do n) {
		symTab.check(n.condition.accept(this), "BOOLEAN");
		n.s.accept(this);
	}

	public void visit(While n) {
		symTab.check(n.condition.accept(this), "BOOLEAN");
		n.s.accept(this);
	}

	public void visit(For n) {
		n.initialize.accept(this);
		n.e.accept(this);
		n.increment.accept(this);
		n.s.accept(this);
	}

	public void visit(Switch n) {
		n.id.accept(this);
		n.caseDefault.accept(this);
	}

	public void visit(Print n) {
		n.statementToPrint.accept(this);
	}

	public void visit(InitializeSimple n) {
		symTab.check(n.id, IdType.VARIABLE);
		String tLeft = symTab.getType(n.id);
		// n.id.accept(this);
		String tRight = n.assignExp.accept(this);
		symTab.check(tLeft, tRight); // should have the same type
	}

	public void visit(InitializeArray n) {
		n.id.accept(this);
		n.arrayExp.accept(this);
		n.assignExp.accept(this);
		symTab.check(n.id, "IntArrayType");
		n.id.accept(this);
		String indexType = n.arrayExp.accept(this);
		String valueType = n.assignExp.accept(this);
		if (!valueType.equals("IntegerType")) {
			System.out.println("Value assigned to int array should be of type int");
		}
		if (!indexType.equals("IntegerType"))
			System.out.println("Array index should be int");
	}

	public void visit(IncrementSimple n) {
		symTab.check(n.id, IdType.VARIABLE);
		String tLeft = symTab.getType(n.id);
		n.id.accept(this);
		String tRight = n.assignExp.accept(this);
		symTab.check(tLeft, tRight); // should have the same type
		n.id.accept(this);
		n.assignExp.accept(this);
	}

	public void visit(IncrementArray n) {
		n.id.accept(this);
		n.arrayExp.accept(this);
		n.assignExp.accept(this);
		symTab.check(n.id, "IntArrayType");
		n.id.accept(this);
		String indexType = n.arrayExp.accept(this);
		String valueType = n.assignExp.accept(this);
		if (!valueType.equals("IntegerType")) {
			System.out.println("Value assigned to int array should be of type int");
		}
		if (!indexType.equals("IntegerType"))
			System.out.println("Array index should be int");
		n.id.accept(this);
		n.arrayExp.accept(this);
		n.assignExp.accept(this);
	}

	public void visit(ElseIf n) {
		symTab.check(n.condition.accept(this), "BOOLEAN");
		n.s.accept(this);
	}

	public void visit(CaseListCase n) {
		String caseExp;

		caseExp = n.caseExp.accept(this);
		symTab.check(n.id, caseExp);
		n.s.accept(this);
		n.caseList.accept(this);
	}

	public void visit(CaseListDefault n) {
		n.s.accept(this);
	}

	public void visit(ExpList n, Identifier id) {

		Binding bind = symTab.get(id);
		symTab.check(n.e.accept(this), symTab.get(id).parms.get(1));
		n.multipleExp.accept(this);
	}

	public String visit(ExpRest n) {
		return n.e.accept(this);
	}

	public void visit(ClassDecl n) {
		// does nothing
	}

	public void visit(VarDeclList n) {
		for (VarDecl v : n) {
			v.accept(this);
		}
	}

	public void visit(MethodDeclList n) {
		for (MethodDecl m : n) {
			m.accept(this);
		}
	}

	public void visit(Statement n) {
		// does nothing

	}

	public String visit(Identifier n) {
		SymbolTable ST;
		// First check current symbol table for identifier
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
		// If not there, check class symbolTable
		// If not there, check Program symbol table
		IdType usage = ST.get(n).usage;
		if (!(usage.equals(IdType.CLASS)))
			return symTab.getType(n);
		// is a class
		// change current symboltable to that class symbol table
		// System.out.println(n.name +": \n"+ST.getChild(n.name));
		// symTab = ST.getChild(n.name);
		// System.out.println("/////////////////" +ST.getChild(n.name));
		return n.name;
	}

	public void visit(StatementList n) {
		for (Statement s : n) {
			visit(s);
		}

	}

	public void visit(InitializationStm n) {
		// does nothing

	}

	public void visit(IncrementStm n) {
		// does nothing

	}

	public void visit(CaseList n) {
		// does nothing

	}

	public void visit(FormalRestList formalRestList) {
		for (FormalRest f : formalRestList) {
			f.accept(this);
		}

	}

	public void visit(ElseIfList elseIfList) {
		for (ElseIf e : elseIfList) {
			e.accept(this);
		}
	}

	public void visit(ExpRestList expRestList, Identifier id) {
		Binding bind = symTab.get(id);
		for (int i = 0; i < expRestList.size(); i++) {
			String typeInSymTab = bind.parms.get(i + 1); // +1 since we already
															// tested the first
															// item in the
															// params list in
															// explist
			String typeOfExp = expRestList.get(i).accept(this);
			symTab.check(typeInSymTab, typeOfExp);

		}
	}

	public void visit(DotArrayList dotArrayList) {
		for (DotArray e : dotArrayList) {
			if (e instanceof DotArrayArray) {
				((DotArrayArray) e).accept(this);
			} else if (e instanceof DotArrayMember) {
				((DotArrayMember) e).accept(this);
			}
		}
	}

	public String visit(DotArray n) {
		// does nothing
		return null;
	}

	public String visit(DotArrayArray n) {
		return n.exp.accept(this);
	}

	public String visit(Member n) {
		// does nothing
		return null;
	}

	public String visit(MemberLength n) {
		return "IntegerType";
	}

	public String visit(AssignSimple n) {
		symTab.check(n.id, IdType.VARIABLE);
		String tLeft = symTab.getType(n.id);
		n.id.accept(this);
		String tRight = n.assignment.accept(this);
		symTab.check(tLeft, tRight); // should have the same type
		return tRight;
	}

	public String visit(AssignArray n) {
		symTab.check(n.id, "IntArrayType");
		n.id.accept(this);
		String indexType = n.index.accept(this);
		String valueType = n.value.accept(this);
		if (!valueType.equals("IntegerType")) {
			System.out.println("Value assigned to int array should be of type int");
		}
		if (!indexType.equals("IntegerType"))
			System.out.println("Array index should be int");
		return valueType;
	}

	public String visit(IntArrayType n) {
		return n.getClass().getSimpleName();
	}

	public String visit(BooleanType n) {
		return n.getClass().getSimpleName();
	}

	public String visit(IntegerType n) {
		return n.getClass().getSimpleName();
	}

	public String visit(IdentifierType n) {
		return n.id;
	}

	public String visit(IntegerLiteral n) {
		// return n.getClass().getSimpleName();
		return "IntegerType";
	}

	public String visit(True n) {
		// return n.getClass().getSimpleName();
		return "BooleanType";
	}

	public String visit(False n) {
		// return n.getClass().getSimpleName();
		return "BooleanType";
	}

	public String visit(IdentifierExp n) {
		// return n.getClass().getSimpleName();
		Binding bind = symTab.get(new Identifier(n.s));
		if (bind.usage.equals(IdType.CLASS)) { // if it's a class, change the
												// symbol table
			symTab = symTabProg.getChild(n.s);
			return n.s;
		}
		return bind.getType(); // got binding, return the
								// type
	}

	public String visit(This n) {
		return n.getClass().getSimpleName();
	}

	public String visit(NewArray n) {
		symTab.check(n.e.accept(this), "IntegerType");
		return "IntArrayType";
	}

	public String visit(NewObject n) {

		return n.id.accept(this);
	}

	public String visit(Not n) {
		if (n instanceof NotFactor)
			return ((NotFactor) n).accept(this);
		if (n instanceof NotSimple)
			return ((NotSimple) n).accept(this);

		return null;
		// does nothing
	}

	public String visit(Type n) {
		return null;
	}

	public String visit(DotArrayMember n) {
		if (n.member instanceof MemberId) {
			return ((MemberId) n.member).accept(this);
		}
		return ((MemberLength) n.member).accept(this);
	}

	//////////////////////////////////////////////////////////////////////// EXP
	public String visit(Exp n) { // if elist isn't null, return elist, else
									// return and
		/*
		 * String left, right; left = n.and.accept(this); if (n.elist != null) {
		 * right = n.elist.accept(this); if (!(left.equals(right)))
		 * System.err.println("Mismatching Types in Exp"); return right; }
		 * return left;
		 */
		System.out.println(n.and.accept(this));
		return null;
	}

	public String visit(Elist n) {
		/*
		 * String left, right; left = n.and.accept(this); if (n.elist != null) {
		 * right = n.elist.accept(this); if (!(left.equals(right)))
		 * System.err.println("Mismatching Types in Elist"); return right; }
		 * return left;
		 */
		return n.and.accept(this);
	}

	public String visit(And n) {
		/*
		 * String left, right; left = n.less.accept(this); if (n.alist != null)
		 * { right = n.alist.accept(this); if (!(left.equals(right)))
		 * System.err.println("Mismatching Types in And"); return right; }
		 * return left;
		 */
		return n.less.accept(this);
	}

	public String visit(Alist n) {
		/*
		 * String left, right; left = n.less.accept(this); if (n.alist != null)
		 * { right = n.alist.accept(this); if (!(left.equals(right)))
		 * System.err.println("Mismatching Types in Alist"); return right; }
		 * return left;
		 */
		return null;
	}

	public String visit(Less n) {
		/*
		 * String left, right; left = n.term.accept(this); if ((n.llist !=
		 * null)&& ((n.llist instanceof LlistDifference) ||(n.llist instanceof
		 * LlistSum) )) { right = n.llist.accept(this); if
		 * (!(left.equals(right))) System.err.println(
		 * "Mismatching Types in Less"); return right; } return left;
		 */
		return n.term.accept(this);
	}

	public String visit(Llist n) {
		/*
		 * if(n instanceof LlistDifference) return
		 * ((LlistDifference)n).accept(this); else if(n instanceof LlistSum)
		 * return ((LlistSum)n).accept(this); String llistType =
		 * n.getClass().getSimpleName(); return null;
		 */
		return null;
	}

	public String visit(LlistDifference n) {
		/*
		 * String left, right; left = n.llist.accept(this); if (n.llist != null)
		 * { right = n.term.accept(this); if (!(left.equals(right)))
		 * System.err.println("Mismatching Types in Less"); return right; }
		 * return left;
		 */
		return null;
	}

	public String visit(LlistSum n) {
		/*
		 * String left, right; left = n.llist.accept(this); if (n.llist != null)
		 * { right = n.term.accept(this); if (!(left.equals(right)))
		 * System.err.println("Mismatching Types in Less"); return right; }
		 * return left;
		 */
		return null;
	}

	public String visit(Term n) {
		/*
		 * String left, right; left = n.not.accept(this); if (n.tlist.not !=
		 * null) { right = n.tlist.accept(this); if (!(left.equals(right)))
		 * System.err.println("Mismatching Types in Term"); return right; }
		 * return left;
		 */
		return n.not.accept(this);
	}

	public String visit(Tlist n) {
		/*
		 * String left, right; left = n.not.accept(this); right =
		 * n.tlist.accept(this); if (n.tlist.not != null) { right =
		 * n.tlist.accept(this); if (!(left.equals(right))) System.err.println(
		 * "Mismatching Types in Term"); return right; } return left;
		 */
		return null;
	}

	public String visit(NotFactor n) {
		String returnType = n.factor.accept(this);
		if (!(n.dotList.isEmpty()))
			return n.dotList.getLast().accept(this);
		else
			return returnType;
	}

	public String visit(NotSimple n) {
		return n.not.accept(this);
	}

	public String visit(Factor n) {
		return null;
		// does nothing
	}

	public String visit(FactorNew n) {
		if (n.newObject instanceof NewObject)
			return ((NewObject) n.newObject).accept(this);
		else
			return ((NewArray) n.newObject).accept(this);
	}

	public String visit(MemberId n) {
		if (n.expList != null) {
			n.expList.accept(this, n.id);
		}
		return n.id.accept(this);
	}

	public String visit(New n) {
		return null;
	}

	/*
	 * public String visit(Exp n){ String tLeft = null, tRight = null; if(n.left
	 * != null){ tLeft = n.left.accept(this); } if(n.right != null){ tRight =
	 * n.left.accept(this); } if(tLeft == null) return tRight; if(tRight ==
	 * null); return tLeft; return checkType(tLeft, tRight); }
	 * 
	 * public String visit(And n){ String tLeft = n.left.accept(this); String
	 * tRight = n.right.accept(this; //Types should be equal //Types should be
	 * BOOLEAN //return BOOLEAN return checkType(tLeft, tRight, "BOOLEAN",
	 * "BOOLEAN"); }
	 * 
	 * public String visit(LessThan n){ String tLeft = n.left.accept(this);
	 * String tRight = n.right.accept(this; return checkType(tLeft, tRight,
	 * "INTEGER", "BOOLEAN"); }
	 * 
	 * public String visit(Plus n){ String tLeft = n.left.accept(this); String
	 * tRight = n.right.accept(this; return checkType(tLeft, tRight, "INTEGER",
	 * "INTEGER"); }
	 * 
	 * //x[expr] public String visit(ArrayLookup n){ String tLeft =
	 * n.left.accept(this); String tRight = n.right.accept(this;
	 * checkType(tLeft, "INTARRAY"); return checkType(tRight, "INTEGER", tLeft);
	 * //return tRight? }
	 * 
	 * public String visit(ArrayLength n){ String tLeft = n.left.accept(this);
	 * return checkType(tLeft, "INTARRAY", "INTEGER"); }
	 * 
	 * public String visit(Call n){ String tClass = n.receiver.accept(this);
	 * SymbolTable symTab = program.getSymbolTable(tClass); if(symTab == null)
	 * System.err.println("Method not defined in class..." + tClass);
	 * symTab.check(n.methodName, IdType.METHOD); Binding b =
	 * symTab.get(n.methodName); List<String> parms = b.getParms(); List<String>
	 * exps = n.parms.getExps(); if(exps.size() != parms.size())
	 * System.err.println(
	 * "Number of actual parms != number of formal parameters"); for(int i = 0;
	 * i < exps.size(); i++) { if(exps.get(i).accept(this) != parms.get(i)
	 * System.err.println("Mismatching parameters"); } return b.getType(); }
	 */

	/*
	 * //the given identifier should have the given usage //if the usage is
	 * Variable, return its symbol table private SymbolTable check(Identifier
	 * id, IdType idtype){ SymbolTable result = symTabMethod; //assume id is a
	 * local variable Binding b = symTabMethod.get(id); if(b == null) { result =
	 * symTabClass; symTabClass.check(id, idtype); } else {
	 * symTabMethod.check(id, idtype); } return result; }
	 * 
	 * // return the symbol table for the given identifier, check its type
	 * private SymbolTable check(Identifier id, String type) { SymbolTable
	 * result = symTabMethod; //assume id is a local variable Binding b =
	 * symTabMethod.get(id); if(b == null) { result = symTabClass;
	 * symTabClass.check(id, type); } else { symTabMethod.check(id, type); }
	 * return result; }
	 * 
	 * private boolean checkType(String e1, String e2) { if(e1.equals(e2))
	 * return true;
	 * 
	 * System.err.println("Types do not match"); return false; }
	 */

}
