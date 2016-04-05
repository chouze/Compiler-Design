package symbolTableBuilder;

public class Exp extends Factor
{	
	And and;
	Elist elist;
	
	public Exp(And and, Elist elist) 
	{
		this.and = and;
		this.elist = elist;
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
	
	
}
