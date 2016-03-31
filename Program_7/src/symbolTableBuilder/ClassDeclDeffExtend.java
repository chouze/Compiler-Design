package symbolTableBuilder;

public class ClassDeclDeffExtend extends ClassDeclDeff 
{
	Identifier extendedClass;
	VarDecl variableList;
	MethodDecl methodList;
	
	public ClassDeclDeffExtend(Identifier extendedClass, VarDecl variableList,
			MethodDecl methodList) 
	{
		this.extendedClass = extendedClass;
		this.variableList = variableList;
		this.methodList = methodList;
	}
	
	
}
