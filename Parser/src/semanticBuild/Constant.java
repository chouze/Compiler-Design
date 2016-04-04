package semanticBuild;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class Constant extends Exp 
{
	Integer value;
	
	public Constant(Integer n)
	{
		value = n;
	}
	
	public Object accept(Visitor v)
	{
		return v.visit(this);
	}
}
