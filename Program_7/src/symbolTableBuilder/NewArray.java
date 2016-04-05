package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class NewArray extends New 
{
	Exp e;

	public NewArray(Exp e) 
	{
		this.e = e;
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
