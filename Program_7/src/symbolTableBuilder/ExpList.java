package symbolTableBuilder;

public class ExpList 
{
	Exp e;
	ExpRestList multipleExp;
	
	public ExpList(Exp e, ExpRestList multipleExp) 
	{
		this.e = e;
		this.multipleExp = multipleExp;
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
