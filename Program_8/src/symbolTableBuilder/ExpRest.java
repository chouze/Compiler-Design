package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class ExpRest extends java.util.LinkedList<Exp> 
{
	Exp e; //?
	
	public ExpRest(Exp e) 
	{
		this.e = e;
	}
	
	public String accept(Visitor v) 
	{
		return v.visit(this);
	}
}
