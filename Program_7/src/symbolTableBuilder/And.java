package symbolTableBuilder;

public class And extends Exp 
{
	Exp e1;
	Exp e2;
	
	public And(Exp e1, Exp e2) 
	{
		this.e1 = e1;
		this.e2 = e2;
	}
	
}
