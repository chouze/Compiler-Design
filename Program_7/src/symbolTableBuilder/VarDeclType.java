package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class VarDeclType 
{
	Identifier variableName;
	VarDeclTypeAssign variableAssign;
	
	public VarDeclType(Identifier variableName, VarDeclTypeAssign variableAssign) 
	{
		this.variableName = variableName;
		this.variableAssign = variableAssign;
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
