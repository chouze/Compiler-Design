package symbolTableBuilder;

/* 
 * A Visitor which builds symbol tables for a syntax tree.
 * Semantic phase, part 1
 * 
 * @author ()
 * @version (Mar 2016)
 */
/*
public class BuildST implements Visitor {
	SymbolTable symTab, symTabMethod, symTabClass, symTabProg;

	public BuildST() {
	}

	public void visit(Program n) { // Program as defined in Bergmann's parser
		symTab = symTabProg = new SymbolTable();
		n.mainClass.accept(this);
		n.classDecls.accept(this);
	}

	public void visit(MainClass n) {
		symTabProg.put(n.className, new Binding(n.className, IdType.CLASS));
		symTabProg.put(n.args, new Binding(n.args, IdType.VARIABLE));
		n.symTab = new SymbolTable();
		n.className.accept(this);
		n.args.accept(this);
		n.stmt.accept(this); // ??
	}

	public void visit(ClassDeclList n) {
		

	}
	
	//everytime we put a new symbol into the symboltable, we have to create a new binding for it
	public void visit(ClassDeclSimple n){
		symTabProg.put(n.className, new Binding(n.className, IdType.CLASS));
		n.className.accept(this);
		symTab = symTabClass = n.symTab = newSymbolTable();
		n.fields.accept(this); //enter fields into the symbol table
		n.methods.accept(this);
	}
	
	//continue in this manner for the following classes:
	//ClassDeclExtends, VarDecl, MethodDecl, Formal
}*/
