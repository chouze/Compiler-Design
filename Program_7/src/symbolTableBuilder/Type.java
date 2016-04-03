package symbolTableBuilder;

public abstract class Type 
{
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
