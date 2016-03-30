package semanticBuild;

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
