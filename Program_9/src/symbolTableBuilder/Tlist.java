package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class Tlist 
{
	Not not;
	Tlist tlist;
	
	public Tlist(Not not, Tlist tlist) 
	{
		this.not = not;
		this.tlist = tlist;
	}
	
	public Tlist()
	{
		
	}
	
	public Object accept(Visitor v) 
	{
		return v.visit(this);
	}
	
}
