package symbolTableBuilder;

public class If extends Statement 
{
	Exp condition;
	Statement s;
	ElseIf elseIf;
	
	public If (Exp condition, Statement s, ElseIf elseIf){
		this.condition = condition;
		this.s = s;
		this.elseIf = elseIf;
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
