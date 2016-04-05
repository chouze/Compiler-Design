package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

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
