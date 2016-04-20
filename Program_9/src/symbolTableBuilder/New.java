package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public abstract class New extends Factor{
	public Object accept(Visitor v) 
	{
		return v.visit(this);
	}
}
