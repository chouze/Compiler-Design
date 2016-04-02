package semanticBuild;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class Variable extends Exp 
{
	String variableName;
	Constant value;
	
	public Variable(String name)
	{
		variableName = name;
	}
	
	public Object accept(Visitor v) 
	{
		return v.visit(this);
	}

}
