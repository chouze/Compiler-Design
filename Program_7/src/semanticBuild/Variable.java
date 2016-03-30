package semanticBuild;

public class Variable extends Exp 
{
	String variableName;
	
	public Variable(String name)
	{
		variableName = name;
	}
	
	public Object accept(Visitor v) 
	{
		return v.visit(this);
	}

}
