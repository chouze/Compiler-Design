package symbolTableBuilder;

public abstract class Assign extends Statement 
{
	Identifier id;
	
	public Assign(Identifier id2) 
	{
		this.id = id2;
	}

}
