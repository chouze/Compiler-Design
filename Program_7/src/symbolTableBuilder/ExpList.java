package symbolTableBuilder;

public class ExpList 
{
	Exp e;
	ExpRest multipleExp;
	
	public ExpList(Exp e, ExpRest multipleExp) 
	{
		this.e = e;
		this.multipleExp = multipleExp;
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
