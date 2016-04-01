package semanticBuild;


public class Eval implements Visitor
{

	public Integer visit(Sum n) 
	{
		return (Integer)n.left.accept(this) + (Integer)n.right.accept(this);
	}

	public Integer visit(Difference n) 
	{
		return (Integer)n.left.accept(this) - (Integer)n.right.accept(this);
	}

	public Integer visit(Constant n) 
	{
		return n.value;
	}

	public Integer visit(Assign n) 
	{
		if(n.right.accept(this) instanceof Integer)
		{
			((Variable)n.left).value = new Constant((Integer)n.right.accept(this));
			return (Integer)n.right.accept(this);
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}

	public Integer visit(Mod n) 
	{
		return(Integer)n.left.accept(this) % (Integer)n.right.accept(this);
	}

	public Integer visit(Product n) 
	{
		return(Integer)n.left.accept(this) * (Integer)n.right.accept(this);
	}

	public Integer visit(Quotient n) 
	{
		return(Integer)n.left.accept(this) / (Integer)n.right.accept(this);
	}

	public Integer visit(Variable n) 
	{
		if(n.value != null)
		{
			return /*n.accept(this);*/ (Integer)n.value.accept(this);
		}
		else
		{
			throw new IllegalArgumentException("Can't evaluate an unassigned variable: " + n.variableName);
		}
	}

	public Integer visit(Exp n) 
	{
		return (Integer)n.accept(this);
	}
	

	
	
}
