package symbolTableBuilder;

public class Term 
{
	Not not;
	Tlist tlist;
	
	public Term(Not not, Tlist tlist) 
	{
		this.not = not;
		this.tlist = tlist;
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
	
}
