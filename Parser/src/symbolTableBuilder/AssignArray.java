package symbolTableBuilder;

public class AssignArray extends Assign 
{
	Exp arrayExp;
	Exp assignExp;
	
	public AssignArray(Exp arrayExp, Exp assignExp) 
	{
		this.arrayExp = arrayExp;
		this.assignExp = assignExp;
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
