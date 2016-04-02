package semanticBuild;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public abstract class Exp 
{
	Exp left;
	Exp right;
	
	public abstract Object accept(Visitor v);
}
