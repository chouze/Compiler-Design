package semanticBuild;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class Product extends Exp 
{

	public Product(Exp e1, Exp e2)
	{
		left = e1;
		right = e2;
	}

	public Object accept(Visitor v) 
	{
		return v.visit(this);
	}

}
