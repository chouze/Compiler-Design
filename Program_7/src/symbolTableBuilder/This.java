package symbolTableBuilder;

public class This extends Factor 
{
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
