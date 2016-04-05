package symbolTableBuilder;

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
