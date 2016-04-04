package symbolTableBuilder;

public class ArrayLength extends Exp
{
	Exp e1;

	public ArrayLength(Exp e1) 
	{
		this.e1 = e1;
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
