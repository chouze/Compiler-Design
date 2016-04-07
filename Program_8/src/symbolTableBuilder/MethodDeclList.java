package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

import java.util.LinkedList;

public class MethodDeclList extends LinkedList<MethodDecl> {

	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
