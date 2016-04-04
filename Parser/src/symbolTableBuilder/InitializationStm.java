package symbolTableBuilder;

public abstract class InitializationStm 
{
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
