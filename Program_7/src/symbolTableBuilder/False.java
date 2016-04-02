package symbolTableBuilder;

public class False extends Exp
{
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
