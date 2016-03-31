package symbolTableBuilder;

public class ClassDeclDeffSimple extends ClassDecl 
{
	Identifier id;
	VarDecl variableList;
	MethodDecl methodList;
	
	public ClassDeclDeffSimple(Identifier id, VarDecl variableList, MethodDecl methodList) 
	{
		this.id = id;
		this.variableList = variableList;
		this.methodList = methodList;
	}
	
	
	
}
