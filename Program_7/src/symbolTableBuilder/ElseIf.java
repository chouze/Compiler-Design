package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class ElseIf 
{
	Exp condition;
	Statement s;
	
	public ElseIf(Exp condition, Statement s) 
	{
		this.condition = condition;
		this.s = s;
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
