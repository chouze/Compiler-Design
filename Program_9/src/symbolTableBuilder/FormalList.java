package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

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

	public Object accept(Visitor v) 
	{
		return v.visit(this);
	}
}
