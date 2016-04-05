package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class StatementList extends java.util.LinkedList<Statement> {
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}

}
