package symbolTableBuilder;

public class IntegerLiteral extends Exp 
{
	int i;

	public IntegerLiteral(int i)
	{
		this.i = i;
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
