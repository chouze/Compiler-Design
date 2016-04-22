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
	Llist llist;
	Term term;
	public Llist(Llist l, Term t) 
	{
		this.term = t;
		this.llist = l;
	}
	
	public Llist()
	{
		
	}
	
	public Object accept(Visitor v) 
	{
		return v.visit(this);
	}
}
