package symbolTableBuilder;

public class Times extends Exp
{
	Exp e1;
	Exp e2;
	
	public Times(Exp e1, Exp e2) {
		this.e1 = e1;
		this.e2 = e2;
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
