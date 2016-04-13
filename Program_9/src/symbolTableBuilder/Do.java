package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class Do extends Statement
{
	Statement s;
	Exp condition;
	
	public Do(Statement s, Exp condition) 
	{
		this.s = s;
		this.condition = condition;
	}
	
	public Object accept(Visitor v) 
	{
		return v.visit(this);
	}
}
