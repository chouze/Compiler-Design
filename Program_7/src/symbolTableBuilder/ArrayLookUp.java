package symbolTableBuilder;

public class ArrayLookUp extends Exp
{
	Exp e1;
	Exp e2;
	
	public ArrayLookUp(Exp e1, Exp e2) {
		this.e1 = e1;
		this.e2 = e2;
	}
}
