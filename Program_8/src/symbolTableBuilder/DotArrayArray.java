package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class DotArrayArray extends DotArray 
{
	Exp exp;

	public DotArrayArray(Exp exp) 
	{
		this.exp = exp;
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
	
}
