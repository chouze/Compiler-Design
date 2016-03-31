package symbolTableBuilder;

public class FormalRest extends java.util.LinkedList 
{
	Type type;
	Identifier paramName;
	
	public FormalRest(Type type, Identifier paramName) 
	{
		this.type = type;
		this.paramName = paramName;
	}
}
