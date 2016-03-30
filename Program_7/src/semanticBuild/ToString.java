package semanticBuild;

public class ToString implements Visitor 
{

	public Object visit(Assign n) 
	{
		return n.left.accept(this) + " = " + n.right.accept(this);
	}

	public Object visit(Constant n) 
	{
		return n.value;
	}

	public Object visit(Difference n) 
	{
		return "(" + n.left.accept(this) + " - " + n.right.accept(this) + ")";
	}

	public Object visit(Mod n) 
	{
		return "(" + n.left.accept(this) + " % " + n.right.accept(this) + ")";
	}

	public Object visit(Product n) 
	{
		return "(" + n.left.accept(this) + " * " + n.right.accept(this) + ")";
	}

	public Object visit(Quotient n) 
	{
		return "(" + n.left.accept(this) + " / " + n.right.accept(this) + ")";
	}

	public Object visit(Sum n) 
	{
		return "(" + n.left.accept(this) + " + " + n.right.accept(this) + ")";
	}

	public Object visit(Variable n) 
	{
		return n.variableName;
	}

	public Object visit(Exp n) 
	{
		return n.accept(this);
	}

}
