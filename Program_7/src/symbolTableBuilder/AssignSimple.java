package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

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
