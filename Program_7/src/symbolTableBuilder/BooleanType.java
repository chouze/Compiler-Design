package symbolTableBuilder;

public class BooleanType extends Type 
{
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
