package symbolTableBuilder;

public class While extends Statement 
{
	Exp condition;
	Statement s;
	
	public While(Exp condition, Statement s) 
	{
		this.condition = condition;
		this.s = s;
	}
}
