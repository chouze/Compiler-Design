package symbolTableBuilder;

public class ClassDeclDeffSimple extends ClassDeclDeff 
{
	VarDecl variableList;
	MethodDecl methodList;
	
	public ClassDeclDeffSimple(VarDecl variableList, MethodDecl methodList) 
	{
		this.variableList = variableList;
		this.methodList = methodList;
	}
	
	
	
}
