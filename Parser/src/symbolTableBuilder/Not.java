package symbolTableBuilder;

public class Not extends Exp 
{
	Exp e;

	public Not(Exp e) 
	{
		this.e = e;
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
