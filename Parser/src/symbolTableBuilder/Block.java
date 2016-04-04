package symbolTableBuilder;

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
