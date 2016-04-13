package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class AssignArray extends Assign 
{
	Identifier id;
	Exp index;
	Exp value;
	
	public AssignArray(Identifier id, Exp arrayExp, Exp assignExp) 
	{
		this.id = id;
		this.index = arrayExp;
		this.value = assignExp;
	}
	
	public  Object accept(Visitor v) 
	{
		return v.visit(this);
	}
}
