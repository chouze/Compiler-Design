package symbolTableBuilder;

public class ClassDeclDeffExtend extends ClassDecl 
{
	Identifier className;
	Identifier extendedClass;
	VarDecl variableList;
	MethodDecl methodList;
	
	public ClassDeclDeffExtend(Identifier className, Identifier extendedClass, VarDecl variableList,
			MethodDecl methodList) 
	{
		this.className = className;
		this.extendedClass = extendedClass;
		this.variableList = variableList;
		this.methodList = methodList;
	}
	
	
}
