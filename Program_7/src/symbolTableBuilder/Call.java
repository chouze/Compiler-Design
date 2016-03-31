package symbolTableBuilder;

public class Call extends Exp
{
	Exp e;
	Identifier id;
	ExpList el;
	
	public Call(Exp e, Identifier id, ExpList el) 
	{
		this.e = e;
		this.id = id;
		this.el = el;
	}
	
}
