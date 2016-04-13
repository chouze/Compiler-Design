package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class VarDeclList extends java.util.ArrayList <VarDecl>{
	
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
