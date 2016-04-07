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
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
