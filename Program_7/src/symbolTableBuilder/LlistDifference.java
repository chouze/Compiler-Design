package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class LlistDifference extends Llist 
{
	Term term;
	Llist llist;
	
	public LlistDifference(Term term, Llist llist) 
	{
		this.term = term;
		this.llist = llist;
	}
}
