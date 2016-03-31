package symbolTableBuilder;

public class Plus extends Exp 
{
	Exp e1;
	Exp e2;
	
	public Plus(Exp e1, Exp e2) {
		this.e1 = e1;
		this.e2 = e2;
	}
}
