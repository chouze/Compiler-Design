package semanticBuild;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class Simplify implements Visitor{

	public Exp visit(Sum n) 
	{
		return new Sum((Exp)n.left.accept(this), (Exp)n.right.accept(this)) ;
	}

	public Exp visit(Difference n) 
	{
		Equals eq = new Equals();
		Exp result =  new Difference((Exp)n.left.accept(this), (Exp)n.right.accept(this));
		
		if( (Boolean)eq.visit(result.left,result.right) )
			return new Constant(0);
		return result;
	}

	public Exp visit(Constant n) 
	{
		return n;
	}
	
	public Exp visit(Assign n)
	{
		return new Assign((Variable)n.left, (Exp)n.right.accept(this)); 
	}

	public Object visit(Mod n) 
	{
		Equals eq = new Equals();
		Exp result = new Mod((Exp)n.left.accept(this), (Exp)n.right.accept(this));
		
		if((Boolean)eq.visit(result.left,result.right))
		{
			return new Constant(0);
		}
		
		return result;
	}

	public Object visit(Product n) 
	{
		if(n.right instanceof Constant)
		{
			if(((Constant)n.right).value == 0)
			{
				return new Constant(0);
			}
		}
		
		if(n.left instanceof Constant)
		{
			if(((Constant)n.left).value == 0)
			{
				return new Constant(0);
			}
		}
		
		return new Product((Exp)n.left.accept(this), (Exp)n.right.accept(this));
	}

	public Object visit(Quotient n) 
	{
		if(n.right instanceof Constant)
		{
			if(((Constant)n.right).value == 0)
			{
				return null;
			}
		}
		
		if(n.left instanceof Constant)
		{
			if(((Constant)n.left).value == 0)
			{
				return new Constant(0);
			}
		}
		
		return new Quotient((Exp)n.left.accept(this), (Exp)n.right.accept(this));
	}

	public Object visit(Variable n) 
	{
		return n.value.accept(this);
	}

	public Object visit(Exp n) 
	{
		return n.accept(this);
	}
	
	
}
