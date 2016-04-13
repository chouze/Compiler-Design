package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */


public class ExpRestList extends java.util.ArrayList<ExpRest> {

	
	public void accept(Visitor v, Identifier id) 
	{
		v.visit(this, id);
	}
}
