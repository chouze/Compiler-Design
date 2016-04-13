package symbolTableBuilder;

/* 
 * A Visitor which builds symbol tables for a syntax tree. Routes through a program and as identifiers are created, they are bound and added to the Symbol Tree.
 * Provides no functionality other than creating a tree for now.
 * Semantic phase, part 1
 * 
 * @author ()
 * @version (Mar 2016)
 */
/*
 * Need to go through each method and determine if a symbol table is needed or not, and if so, what is it's scope.
 * Notes:
 * if it's an identifier, always add it to the program table along with it's IdType.
 * I assume you should add it to the current symTab as well but I'm not sure.
 * Type checking is commented out atm since all we need is symbol tables but it's a good idea to know where to type check
 * Need to check types, expressions, and statements.
 * I think for the base cases, like integers, identifiers, etc, we don't need to process anything yet
 * 
 */

public class BuildST implements Visitor {
	public SymbolTable symTab, symTabMethod, symTabClass, symTabProg;
	
	// I think it works like this:
	// symTab is the table in focus
	// symTabMethod is the table created when a method is created
	// symTabClass is the table created when a class is created
	// symTabProg is the table created at the time the program is
	//
	// This class

	public BuildST() {
		// Temp
		// symTabProg = new SymbolTable();
	}

	public Object visit(Program n) { // Program as defined in Bergmann's parser
		symTab = symTabProg = n.symTab = new SymbolTable();
		n.mainClass.accept(this);
		n.classDecls.accept(this);
		System.out.println("Program Table: \n" + n.symTab);
	}

	public Object visit(MainClass n) {
		symTabProg.put(n.className, new Binding(n.className, IdType.CLASS));
		
		
		n.className.accept(this);
		n.args.accept(this);
		symTab = symTabClass = n.symTab = new SymbolTable(); // each mainclass
																// has it's own
																// symboltable
																// since it is
																// static
		symTab.put(new Identifier("args"), new Binding(new Identifier("args"), IdType.VARIABLE, "Object[]"));
		n.v.accept(this);
		n.stmt.accept(this); // ??
		symTabProg.putChild(n.className.name, n.symTab);
		System.out.println("MainClass " + n.className.name + " Table: \n" + n.symTab);
	}

	// everytime we put a new symbol into the symboltable, we have to create a
	// new binding for it
	public Object visit(ClassDeclDeffSimple n) {
		symTabProg.put(n.className, new Binding(n.className, IdType.CLASS));
		
		symTab = symTabClass = n.symTab = new SymbolTable();
		n.className.accept(this);
		n.fields.accept(this); // enter fields into the symbol table
		n.methods.accept(this);
		symTabProg.putChild(n.className.name, n.symTab);
		System.out.println("SimpleClass " + n.className.name + " Table: \n" + n.symTab);
	}

	/*
	 * public Object visit(ClassDeclSpec n) { symTabProg.put(n.className, new
	 * Binding(n.className, IdType.CLASS)); n.className.accept(this); }
	 */

	public Object visit(ClassDeclDeffExtend n) {
		symTab = symTabClass = n.symTab = new SymbolTable(); // class needs a
																// symbol table
		
		symTabProg.put(n.className, new Binding(n.className, IdType.CLASS));
		n.className.accept(this);

		symTabProg.put(n.extendedClass, new Binding(n.extendedClass, IdType.CLASS));
		n.extendedClass.accept(this);
		symTabProg.putChild(n.className.name, n.symTab);
		n.variableList.accept(this);
		System.out.println("Extended Class " + n.className.name + " Table: \n" + n.symTab);
		

	}

	public Object visit(ClassDeclList n) {
		for (ClassDecl c : n) {
			if (c instanceof ClassDeclDeffSimple) {
				((ClassDeclDeffSimple) c).accept(this);
				// visit((ClassDeclDeffSimple)c);
			}

			if (c instanceof ClassDeclDeffExtend) {
				((ClassDeclDeffExtend) c).accept(this);
				// visit((ClassDeclDeffExtend)c);
			}

			// visit(c);
		}
	}

	public Object visit(VarDecl n) {
		Type t = n.type;
		t.accept(this);
		n.variableType.accept(this, t);

	}

	public Object visit(VarDeclType n, Type t) {
		n.variableName.accept(this);
		if (t instanceof IdentifierType)
		{
			symTab.put(n.variableName, new Binding(n.variableName, IdType.VARIABLE, ((IdentifierType)t).id));
		}
		else
			symTab.put(n.variableName, new Binding(n.variableName, IdType.VARIABLE, t.getClass().getSimpleName()));

		if (!(n.exp == null))
			n.exp.accept(this);
	}

	public Object visit(MethodDecl n) {
		Binding bind = new Binding(n.methodName, IdType.METHOD, n.type.getClass().getSimpleName());
		
		bind.addParams(n.parameters);
		symTabClass.put(n.methodName, bind);
		// symTab.put(n.methodName, bind);
		symTab = symTabMethod = n.symTab = new SymbolTable();
		n.type.accept(this);
		n.methodName.accept(this);
		n.parameters.accept(this);
		n.variables.accept(this);
		n.statement.accept(this);
		Exp e = n.expReturn;
		e.accept(this);
		symTabClass.putChild(n.methodName.name, n.symTab);
		System.out.println("Method " + n.methodName.name + " Table: \n" + n.symTab);

	}

	public Object visit(FormalList n) {
		Type t = n.type;
		t.accept(this);
		if (t instanceof IdentifierType)
		{
			symTab.put(n.parameterName, new Binding(n.parameterName, IdType.VARIABLE, ((IdentifierType)t).id));
		}
		else
			symTab.put(n.parameterName, new Binding(n.parameterName, IdType.VARIABLE, t.getClass().getSimpleName()));
		//symTab.put(n.parameterName, new Binding(n.parameterName, IdType.VARIABLE, t.getClass().getSimpleName()));
		n.parameterName.accept(this);
		n.moreParams.accept(this);
	}

	public Object visit(FormalRest n) {
		Type t = n.type;
		t.accept(this);
		if (t instanceof IdentifierType)
		{
			symTab.put(n.paramName, new Binding(n.paramName, IdType.VARIABLE, ((IdentifierType)t).id));
		}
		else
			symTab.put(n.paramName, new Binding(n.paramName, IdType.VARIABLE, t.getClass().getSimpleName()));
		//symTab.put(n.paramName, new Binding(n.paramName, IdType.VARIABLE, t.getClass().getSimpleName()));
		n.paramName.accept(this);
	}

	public Object visit(Block n) {
		n.sl.accept(this);
		return null;
	}

	public Object visit(If n) {
		n.condition.accept(this);
		n.s.accept(this);
		n.elseIf.accept(this);
		return null;
	}

	public Object visit(Do n) {
		n.s.accept(this);
		n.condition.accept(this);
		return null;
	}

	public Object visit(While n) {
		n.condition.accept(this);
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
		//symTab.put(n.id, new Binding(n.id, IdType.VARIABLE));
		n.id.accept(this);
		n.caseDefault.accept(this);
		return null;
	}

	public Object visit(Print n) {
		n.statementToPrint.accept(this);
		return null;
	}

	public Object visit(AssignSimple n) {
		n.assignment.accept(this);
		return null;
	}

	public Object visit(AssignArray n) {
		n.index.accept(this);
		n.value.accept(this);
		return null;
	}

	public Object visit(InitializeSimple n) {
		//symTab.put(n.id, new Binding(n.id, IdType.VARIABLE, n.getClass().getSimpleName()));
		n.id.accept(this);
		n.assignExp.accept(this);
	}

	public Object visit(InitializeArray n) {
		//symTab.put(n.id, new Binding(n.id, IdType.VARIABLE, "INTARRAY"));
		n.id.accept(this);
		n.arrayExp.accept(this);
		n.assignExp.accept(this);
	}

	public Object visit(IncrementSimple n) {
		//symTab.put(n.id, new Binding(n.id, IdType.VARIABLE));
		n.id.accept(this);
		n.assignExp.accept(this);
	}

	public Object visit(IncrementArray n) {
		//symTab.put(n.id, new Binding(n.id, IdType.VARIABLE));
		n.id.accept(this);
		n.arrayExp.accept(this);
		n.assignExp.accept(this);
	}

	public Object visit(ElseIf n) {
		n.condition.accept(this);
		n.s.accept(this);
	}

	public Object visit(CaseListCase n) {
		n.caseExp.accept(this);
		n.s.accept(this);
		n.caseList.accept(this);
	}

	public Object visit(CaseListDefault n) {
		n.s.accept(this);
	}

	public Object visit(ExpList n, Identifier id) {
		n.e.accept(this);
		n.multipleExp.accept(this, id);
	}

	public Object visit(ExpRest n) {
		n.e.accept(this);
		return null;
	}

	public Object visit(Alist n) {
		n.alist.accept(this);
		n.less.accept(this);
		return null;

	}

	public Object visit(ClassDecl n) {
		// does nothing
	}

	public Object visit(VarDeclList n) {
		for (VarDecl v : n) {
			v.accept(this);
			// visit(v);
		}
	}

	public Object visit(MethodDeclList n) {
		for (MethodDecl m : n) {
			m.accept(this);
			// visit(m);
		}
	}

	public Object visit(IntArrayType n) {
		return null;
		// does nothing
	}

	public Object visit(BooleanType n) {
		return null;
		// does nothing

	}

	public Object visit(IntegerType n) {
		return null;
		// does nothing

	}

	public Object visit(IdentifierType n) {
		return null;
		// does nothing
	}

	public Object visit(Statement n) {
		// does nothing

	}

	public Object visit(And n) {
		n.less.accept(this);
		n.alist.accept(this);
		return null;
	}

	public Object visit(IntegerLiteral n) {
		return null;
		// does nothing

	}

	public Object visit(True n) {
		return null;
		// does nothing

	}

	public Object visit(False n) {
		return null;
		// does nothing

	}

	public Object visit(IdentifierExp n) {
		return null;
		// does nothing

	}

	public Object visit(This n) {
		return null;
		// does nothing

	}

	public Object visit(NewArray n) {
		n.e.accept(this);
		return null;
	}

	public Object visit(NewObject n) {
		symTab.put(n.id, new Binding(n.id, IdType.CLASS));
		n.id.accept(this);
		return null;

	}

	public Object visit(Not n) {
		return null;
	}

	public Object visit(Identifier n) {
		// does nothing
		return null;
	}

	public Object visit(Exp n) {
		return null;
		// does nothing

	}

	public Object visit(Type n) {
		return null;
		// does nothing
	}

	public Object visit(StatementList n) {
		for (Statement s : n) {
			visit(s);
		}

	}

	public Object visit(InitializationStm n) {
		// does nothing

	}

	public Object visit(IncrementStm n) {
		// does nothing

	}

	public Object visit(CaseList n) {
		// does nothing

	}

	public Object visit(FormalRestList formalRestList) {
		for (FormalRest f : formalRestList) {
			f.accept(this);
			// visit(f);
		}

	}

	public Object visit(ElseIfList elseIfList) {
		for (ElseIf e : elseIfList) {
			e.accept(this);
			// visit(e);
		}
	}

	public Object visit(ExpRestList expRestList, Identifier id) {
		for (ExpRest e : expRestList) {
			e.accept(this);
			// visit(e);
		}
	}

	public Object visit(DotArrayList dotArrayList) {
		for (DotArray e : dotArrayList) {
			if (e instanceof DotArrayArray) {
				((DotArrayArray) e).accept(this);
			} else if (e instanceof DotArrayMember) {
				((DotArrayMember) e).accept(this);
			}
			// visit(e);
		}

	}

	public Object visit(DotArray n) {
		// does nothing
		return null;

	}

	public Object visit(DotArrayArray n) {
		n.exp.accept(this);
return null;
	}

	public Object visit(DotArrayMember n) {
		n.member.accept(this);
		return null;
	}

	public Object visit(Elist n) {
		n.and.accept(this);
		n.elist.accept(this);
		return null;
	}

	public Object visit(Factor n) {
		return null;
		// does nothing
	}

	public Object visit(FactorNew n) {
		n.newObject.accept(this);
		return null;

	}

	public Object visit(Less n) {
		n.llist.accept(this);
		n.term.accept(this);
		return null;

	}

	public Object visit(Llist n) {
		return null;

	}

	public Object visit(LlistDifference n) {
		n.llist.accept(this);
		n.term.accept(this);
		return null;
	}

	public Object visit(LlistSum n) {
		n.llist.accept(this);
		n.term.accept(this);
		return null;
	}

	public Object visit(Member n) {
		// does nothing
		return null;
	}

	public Object visit(MemberId n) {
		Binding bind = new Binding(n.id, IdType.METHOD);
		bind.addParams(n.expList);
		symTab.put(n.id, bind);
		n.id.accept(this);
		n.expList.accept(this, n.id);
		return null;
	}

	public Object visit(MemberLength n) {
		// does nothing
return null;
	}

	public Object visit(New n) {
		// does nothing
		return null;
	}

	public Object visit(NotFactor n) {
		n.factor.accept(this);
		n.dotList.accept(this);
		return null;

	}

	public Object visit(NotSimple n) {
		n.not.accept(this);
		return null;

	}

	public Object visit(Term n) {
		n.not.accept(this);
		n.tlist.accept(this);
		return null;

	}

	public Object visit(Tlist n) {
		n.not.accept(this);
		n.tlist.accept(this);
		return null;

	}
}
