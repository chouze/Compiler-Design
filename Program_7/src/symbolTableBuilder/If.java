package symbolTableBuilder;

public class If extends Statement 
{
	Exp condition;
	Statement s;
	ElseIfList elseIf;
	
	public If (Exp condition, Statement s, ElseIfList elseIf){
		this.condition = condition;
		this.s = s;
		this.elseIf = elseIf;
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
