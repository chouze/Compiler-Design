package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class While extends Statement 
{
	Exp condition;
	Statement s;
	
	public While(Exp condition, Statement s) 
	{
		this.condition = condition;
		this.s = s;
	}
	
	public String accept(Visitor v) 
	{
		return v.visit(this);
	}
}
