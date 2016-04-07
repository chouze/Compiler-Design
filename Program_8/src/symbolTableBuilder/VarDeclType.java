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
	Exp exp;

	public VarDeclType(Identifier varName) 
	{
		variableName = varName;
		exp = null;
	}

	public VarDeclType(Identifier varName, Exp exp) 
	{
		variableName = varName;
		this.exp = exp;
	}

	public void accept(Visitor v, Type t) 
	{
		v.visit(this, t);
	}
}
