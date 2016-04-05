package symbolTableBuilder;

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
