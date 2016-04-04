package symbolTableBuilder;

public class ClassDeclDeffExtend extends ClassDecl 
{
	Identifier className;
	Identifier extendedClass;
	VarDecl variableList;
	MethodDecl methodList;
	SymbolTable symTab;
	
	public ClassDeclDeffExtend(Identifier className, Identifier extendedClass, VarDecl variableList,
			MethodDecl methodList) 
	{
		this.className = className;
		this.extendedClass = extendedClass;
		this.variableList = variableList;
		this.methodList = methodList;
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
