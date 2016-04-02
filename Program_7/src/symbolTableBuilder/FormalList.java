package symbolTableBuilder;

public class FormalList 
{
	Type type;
	Identifier parameterName;
	FormalRest moreParams;
	
	public FormalList(Type type, Identifier parameterName,
			FormalRest moreParams) 
	{
		this.type = type;
		this.parameterName = parameterName;
		this.moreParams = moreParams;
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
