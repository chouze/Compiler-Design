package symbolTableBuilder;

public class ClassDeclDeffExtend extends ClassDecl 
{
	Identifier className;
	Identifier extendedClass;
	VarDeclList variableList;
	MethodDeclList methodList;
	SymbolTable symTab;
	
	public ClassDeclDeffExtend(Identifier className, Identifier extendedClass, VarDeclList variableList,
			MethodDeclList methodList) 
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
