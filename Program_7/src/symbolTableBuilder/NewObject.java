package symbolTableBuilder;

public class NewObject extends Exp 
{
	Identifier id;

	public NewObject(Identifier id) 
	{
		this.id = id;
	}
}
