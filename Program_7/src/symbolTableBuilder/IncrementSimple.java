package symbolTableBuilder;

public class IncrementSimple extends IncrementStm 
{
	Identifier id;
	Exp assignExp;
	
	public IncrementSimple(Identifier id, Exp assignExp) 
	{
		this.id = id;
		this.assignExp = assignExp;
	}
}
