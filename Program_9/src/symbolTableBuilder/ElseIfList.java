package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */


public class ElseIfList extends java.util.ArrayList<ElseIf> {

	
	public Object accept(Visitor v) 
	{
		return v.visit(this);
	}
}
