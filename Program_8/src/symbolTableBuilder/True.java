package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class True extends Factor
{
	public String accept(Visitor v) 
	{
		return v.visit(this);
	}
}
