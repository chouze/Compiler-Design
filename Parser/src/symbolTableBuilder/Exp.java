package symbolTableBuilder;

public abstract class Exp 
{
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
