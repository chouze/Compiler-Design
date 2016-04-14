package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */


public class MethodDeclList extends java.util.ArrayList<MethodDecl> {

	
	public Object accept(Visitor v) 
	{
		return v.visit(this);
	}
}
