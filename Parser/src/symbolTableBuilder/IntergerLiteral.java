package symbolTableBuilder;

public class IntergerLiteral extends Exp 
{
	int i;

	public IntergerLiteral(int i) 
	{
		this.i = i;
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
