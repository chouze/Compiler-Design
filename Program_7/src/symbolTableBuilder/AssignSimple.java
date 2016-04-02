package symbolTableBuilder;

public class AssignSimple extends Assign 
{
	Exp assignment;
	
	public AssignSimple(Identifier id, Exp assignment) 
	{
		super(id);
		this.assignment = assignment;
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
