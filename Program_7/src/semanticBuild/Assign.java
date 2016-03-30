package semanticBuild;

public class Assign extends Exp {

	public Assign(Exp e1, Exp e2)
	{
		left = e1;
		right = e2;
	}
	
	public Object accept(Visitor v) 
	{
		return v.visit(this);
	}

}
