package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

import java.util.LinkedList;

public class FormalRestList extends LinkedList<FormalRest> 
{

	
	public Object accept(Visitor v) 
	{
		return v.visit(this);
	}
}
