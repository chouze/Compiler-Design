package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class FactorNew extends Factor 
{
	New newObject;

	public FactorNew(New newObject) 
	{
		this.newObject = newObject;
	}
	
}
