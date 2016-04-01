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

	public Object visit(Assign n) 
	{
		if(n.right.accept(this) instanceof Integer)
		{
			return ((Variable)n.left).value = new Constant((Integer) n.right.accept(this));
		}
		else
		{
			throw new IllegalArgumentException();
		}
	}

	public Object visit(Mod n) 
	{
		return(Integer)n.left.accept(this) % (Integer)n.accept(this);
	}

	public Object visit(Product n) 
	{
		return(Integer)n.left.accept(this) * (Integer)n.accept(this);
	}

	public Object visit(Quotient n) 
	{
		return(Integer)n.left.accept(this) / (Integer)n.accept(this);
	}

	public Object visit(Variable n) 
	{
		if(n.value != null)
		{
			return /*n.accept(this);*/ n.value;
		}
		else
		{
			throw new IllegalArgumentException("Can't evaluate an unassigned variable: " + n.variableName);
		}
	}

	public Object visit(Exp n) 
	{
		return n.accept(this);
	}
	

	
	
}
