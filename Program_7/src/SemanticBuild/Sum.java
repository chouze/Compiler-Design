package semanticBuild;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class Sum extends Exp 
{
	
	public Sum(Exp l, Exp r) {
		
		left = l;
		right = r;
	}

	public Object accept(Visitor v)
	{
		return v.visit(this);
	}
}
