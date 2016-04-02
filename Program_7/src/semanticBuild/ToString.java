package semanticBuild;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class ToString implements Visitor 
{

	public String visit(Assign n) 
	{
		return n.left.accept(this) + " = " + n.right.accept(this);
	}

	public String visit(Constant n) 
	{
		return n.value.toString();
	}

	public String visit(Difference n) 
	{
		return "(" + n.left.accept(this) + " - " + n.right.accept(this) + ")";
	}

	public String visit(Mod n) 
	{
		return "(" + n.left.accept(this) + " % " + n.right.accept(this) + ")";
	}

	public String visit(Product n) 
	{
		return "(" + n.left.accept(this) + " * " + n.right.accept(this) + ")";
	}

	public String visit(Quotient n) 
	{
		return "(" + n.left.accept(this) + " / " + n.right.accept(this) + ")";
	}

	public String visit(Sum n) 
	{
		return "(" + n.left.accept(this) + " + " + n.right.accept(this) + ")";
	}

	public String visit(Variable n) 
	{
		if(n.value != null){
			return n.value.accept(this).toString();
		}
		else {
			return n.variableName;
		}
	}

	public String visit(Exp n) 
	{
		return n.accept(this).toString();
	}

}
