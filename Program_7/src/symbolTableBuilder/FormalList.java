package symbolTableBuilder;

public class FormalList 
{
	Type type;
	Identifier parameterName;
	FormalRestList moreParams;
	
	public FormalList(Type type, Identifier parameterName,
			FormalRestList moreParams) 
	{
		this.type = type;
		this.parameterName = parameterName;
		this.moreParams = moreParams;
	}
	
	public FormalList() {
		// TODO Auto-generated constructor stub
	}

	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
