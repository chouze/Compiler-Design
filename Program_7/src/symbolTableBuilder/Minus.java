package symbolTableBuilder;

public class Minus extends Exp 
{
	Exp e1;
	Exp e2;
	
	public Minus(Exp e1, Exp e2) {
		this.e1 = e1;
		this.e2 = e2;
	}
}
