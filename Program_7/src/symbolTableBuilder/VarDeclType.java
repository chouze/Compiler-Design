package symbolTableBuilder;

public class VarDeclType 
{
	Identifier variableName;
	VarDeclTypeAssign variableAssign;
	
	public VarDeclType(Identifier variableName, VarDeclTypeAssign variableAssign) 
	{
		this.variableName = variableName;
		this.variableAssign = variableAssign;
	}
}
