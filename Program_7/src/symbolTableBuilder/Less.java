package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class Less 
{
	Term term;
	Llist llist;
	
	public Less(Term term, Llist llist) 
	{
		this.term = term;
		this.llist = llist;
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
	
}
