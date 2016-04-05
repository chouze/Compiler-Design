package symbolTableBuilder;

public class NewObject extends New 
{
	Identifier id;

	public NewObject(Identifier id) 
	{
		this.id = id;
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
