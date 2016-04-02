package symbolTableBuilder;

import java.util.LinkedList;

public class FormalVarExp extends LinkedList 
{
	Type type;
	Identifier paramName;
	Exp assignValue;
	
	public FormalVarExp(Type type, Identifier paramName, Exp assignValue) 
	{
		this.type = type;
		this.paramName = paramName;
		this.assignValue = assignValue;
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
