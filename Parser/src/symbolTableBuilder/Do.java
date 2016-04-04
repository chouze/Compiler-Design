package symbolTableBuilder;

public class Do extends Statement
{
	Statement s;
	Exp condition;
	
	public Do(Statement s, Exp condition) 
	{
		this.s = s;
		this.condition = condition;
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
