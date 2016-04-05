package symbolTableBuilder;

public class NewArray extends New 
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
