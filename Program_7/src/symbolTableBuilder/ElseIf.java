package symbolTableBuilder;

public class ElseIf 
{
	Exp condition;
	Statement s;
	
	public ElseIf(Exp condition, Statement s) 
	{
		this.condition = condition;
		this.s = s;
	}
}
