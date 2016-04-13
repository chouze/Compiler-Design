package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class IdentifierExp extends Factor 
{
	String s;

	public IdentifierExp(String s) 
	{
		this.s = s;
	}
	
	public Object accept(Visitor v) 
	{
		return v.visit(this);
	}
}
