package symbolTableBuilder;

public class Llist 
{
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
