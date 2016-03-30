package symbolTableBuilder;

/* 
 * A Visitor can perform operations on a Syntax Tree, such as
 * 	Build symbol tables
 * 	Type check
 * 	Optimize
 * 	Code Gen...
 * 
 * @author ()
 * @version (Mar 2016)
 */
public interface Visitor {
	void visit(Program n);
	void visit(MainClass n);
	void visit(ClassDecl n);
	void visit(ClassDeclSimple n);
	void visit(ClassDeclExtends n);
	
	void visit(MethodDecl n);
	
	String visit(Exp n);
	String visit(And n);
	String visit(LessThan n);
	String visit(Plus n);
	String visit(Mult n);
	//etc etc for every class in our syntax tree
}
