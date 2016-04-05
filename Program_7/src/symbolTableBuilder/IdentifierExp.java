package symbolTableBuilder;

public class IdentifierExp extends Factor 
{
	String s;

	public IdentifierExp(String s) 
	{
		this.s = s;
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
