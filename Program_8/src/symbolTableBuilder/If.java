package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

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
	
	public String accept(Visitor v) 
	{
		return v.visit(this);
	}
}
