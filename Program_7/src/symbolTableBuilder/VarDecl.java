package symbolTableBuilder;

public class VarDecl extends java.util.LinkedList
{
	Type type;
	VarDeclType variableType;
	
	public VarDecl(Type type, VarDeclType variableType) 
	{
		this.type = type;
		this.variableType = variableType;
	}
	
	
}
