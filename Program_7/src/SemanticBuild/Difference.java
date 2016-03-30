package semanticBuild;

public class Difference extends Exp
{
	public Difference(Exp l, Exp r) 
	{
		left = l;
		right = r;
	}

	public Integer accept(Visitor v)
	{
		return (Integer)v.visit(this);
	}
}
