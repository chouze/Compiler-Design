package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

import java.util.LinkedList;

public class ExpRest extends LinkedList 
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
