package symbolTableBuilder;

public abstract class IncrementStm 
{
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
