package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class IntegerLiteral extends Factor 
{
	int i;

	public IntegerLiteral(int i)
	{
		this.i = i;
	}
	
	public String accept(Visitor v) 
	{
		return v.visit(this);
	}
}
