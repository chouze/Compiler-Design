package symbolTableBuilder;

public abstract class Not
{
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
