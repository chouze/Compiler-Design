package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class Llist 
{
	Llist l;
	Term t;
	public Llist(Llist l, Term t) 
	{
		this.t = t;
		this.l = l;
	}
	
	public Llist()
	{
		
	}
	
	public Object accept(Visitor v) 
	{
		return v.visit(this);
	}
}
