package symbolTableBuilder;

public class AssignSimple extends Assign 
{
	Exp assignment;
	
	public AssignSimple(Exp assignment) 
	{
		this.assignment = assignment;
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
