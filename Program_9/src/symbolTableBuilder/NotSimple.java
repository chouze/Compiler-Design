package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class NotSimple extends Not
{
	Not not;

	public NotSimple(Not not) 
	{
		this.not = not;
	}
	
	public Object accept(Visitor v) 
	{
		return v.visit(this);
	}
	
}
