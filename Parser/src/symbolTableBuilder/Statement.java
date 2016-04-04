package symbolTableBuilder;

public abstract class Statement 
{
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
