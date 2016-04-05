package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

import java.util.LinkedList;

public class ExpRestList extends LinkedList<ExpRest> {

	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
