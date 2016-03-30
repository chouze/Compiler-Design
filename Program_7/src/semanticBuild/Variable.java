package semanticBuild;

public class Variable extends Exp 
{
	String variableName;
	Constant value;
	
	public Variable(String name)
	{
		variableName = name;
	}
	
	public Object accept(Visitor v) 
	{
		return v.visit(this);
	}

}
