package semanticBuild;

public class Equals implements Visitor {

	public Object visit(Sum n) 
	{
		return visit(n.left, n.right);
	}

	public Object visit(Difference n) 
	{
		return visit(n.left, n.right);
	}

	public Object visit(Constant n) 
	{
		return n.value;
	}

	public Object visit(Exp e1, Exp e2) 
	{
		if (!e1.getClass().equals(e2.getClass()))
			return false;

		if (e1 instanceof Sum)
			return (Boolean) visit(e1.left, e2.left) && (Boolean) visit(e1.right, e2.right)
					|| (Boolean) visit(e1.left, e2.right) && (Boolean) visit(e1.right, e2.left);

		if (e1 instanceof Difference)
			return (Boolean) visit(e1.left, e2.left) && (Boolean) visit(e1.right, e2.right);
		
		if (e1 instanceof Mod)
			return (Boolean) visit(e1.left, e2.left) && (Boolean) visit(e1.right, e2.right);
		
		if (e1 instanceof Product)
			return (Boolean) visit(e1.left, e2.left) && (Boolean) visit(e1.right, e2.right)
					|| (Boolean) visit(e1.left, e2.right) && (Boolean) visit(e1.right, e2.left);
		
		if (e1 instanceof Quotient)
			return (Boolean) visit(e1.left, e2.left) && (Boolean) visit(e1.right, e2.right);
		
		if (e1 instanceof Variable)
			return (Boolean) visit(((Variable)e1).value, ((Variable)e2).value);
		
		return ((Constant)e1).value.equals(((Constant)e2).value);
	}
	
	public Object visit(Assign n)
	{
		return visit(n.left, n.right);
	}

	public Object visit(Mod n) 
	{
		return visit(n.left, n.right);
	}

	public Object visit(Product n) 
	{
		return visit(n.left, n.right);
	}

	public Object visit(Quotient n) 
	{
		return visit(n.left, n.right);
	}

	public Object visit(Variable n) 
	{
		return visit(n.left, n.right);
	}

	public Object visit(Exp n) 
	{
		return n.accept(this);
	}
	
	
}
