package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class Elist 
{
	And and;
	Elist elist;
	
	public Elist(And and, Elist elist) 
	{
		this.and = and;
		this.elist = elist;
	}

	public Elist() {
		// TODO Auto-generated constructor stub
	}
	
	public String accept(Visitor v) 
	{
		return v.visit(this);
	}
	
	
}
