package symbolTableBuilder;

public class NewArray extends Exp 
{
	Exp e;

	public NewArray(Exp e) 
	{
		this.e = e;
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
