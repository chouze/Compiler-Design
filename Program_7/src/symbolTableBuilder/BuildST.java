package symbolTableBuilder;

/* 
 * A Visitor which builds symbol tables for a syntax tree.
 * Semantic phase, part 1
 * 
 * @author ()
 * @version (Mar 2016)
 */

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

	public void visit(ClassDecl n) {
		//Abstract class, no need to visit
	}
	
	//everytime we put a new symbol into the symboltable, we have to create a new binding for it
	public void visit(ClassDeclDeffSimple n){
		symTabProg.put(n.className, new Binding(n.className, IdType.CLASS));
		n.className.accept(this);
		symTab = symTabClass = n.symTab = new SymbolTable();
		n.fields.accept(this); //enter fields into the symbol table
		n.methods.accept(this);
	}

	@Override
	public void visit(ClassDeclSpec n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ClassDeclDeffExtend n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ClassDeclList n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(VarDecl n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(VarDeclType n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(VarDeclTypeAssign n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(MethodDecl n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(FormalList n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(FormalRest n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IntArrayType n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(BooleanType n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IntegerType n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IdentifierType n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Statement n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Block n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(If n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Do n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(While n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(For n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Switch n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Print n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(AssignSimple n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(AssignArray n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(AssignMultiple n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(InitializeSimple n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(InitializeArray n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IncrementSimple n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IncrementArray n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ElseIf n) {
		// TODO Auto-generated method stub
		
	}

	/*
	@Override
	public void visit(FormalVarExp n) {
		// TODO Auto-generated method stub
		
	}
*/
	@Override
	public void visit(CaseListCase n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(CaseListDefault n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ExpList n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ExpRest n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(And n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(LessThan n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Plus n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Minus n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Times n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ArrayLookUp n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ArrayLength n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Call n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IntergerLiteral n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(True n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(False n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IdentifierExp n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(This n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(NewArray n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(NewObject n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Not n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Identifier n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(VarDeclList n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(MethodDeclList n) {
		// TODO Auto-generated method stub
		
	}
	
	//continue in this manner for the following classes:
	//ClassDeclExtends, VarDecl, MethodDecl, Formal
}
