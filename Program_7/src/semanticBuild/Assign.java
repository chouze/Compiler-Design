package semanticBuild;

public class Assign extends Exp {

	public Assign(Exp e1, Exp e2)
	{
		if(e1 instanceof Variable)
		{
			left = e1;
			right = e2;
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}
	
	public Object accept(Visitor v) 
	{
		return v.visit(this);
	}

}
