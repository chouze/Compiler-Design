package symbolTableBuilder;

public class IdentifierType extends Type 
{
	String id;

	public IdentifierType(String id) 
	{
		this.id = id;
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
