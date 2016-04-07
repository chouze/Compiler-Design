package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class IdentifierType extends Type 
{
	String id;

	public IdentifierType(String id) 
	{
		this.id = id;
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
