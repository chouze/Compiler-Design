package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class NewObject extends New 
{
	Identifier id;

	public NewObject(Identifier id) 
	{
		this.id = id;
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
