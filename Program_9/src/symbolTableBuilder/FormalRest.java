package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class FormalRest extends java.util.ArrayList<FormalRest>
{
	Type type;
	Identifier paramName;
	
	public FormalRest(Type type, Identifier paramName) 
	{
		this.type = type;
		this.paramName = paramName;
	}
	
	public Object accept(Visitor v) 
	{
		return v.visit(this);
	}
}
