package symbolTableBuilder;

public class False extends Factor
{
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
