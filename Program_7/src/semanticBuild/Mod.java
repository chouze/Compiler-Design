package semanticBuild;

public class Mod extends Exp 
{
	public Mod(Exp e1, Exp e2)
	{
		left = e1;
		right = e2;
	}
	
	public Object accept(Visitor v) 
	{
		return v.visit(this);
	}

}
