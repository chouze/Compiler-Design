package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class Block extends Statement 
{
	StatementList sl;

	public Block(StatementList sl) 
	{
		this.sl = sl;
	}
	
	public Object accept(Visitor v) 
	{
		return v.visit(this);
	}
}
