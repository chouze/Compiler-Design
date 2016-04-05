package symbolTableBuilder;

public class True extends Factor
{
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
