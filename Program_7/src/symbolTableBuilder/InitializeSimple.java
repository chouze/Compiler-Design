package symbolTableBuilder;

public class InitializeSimple extends InitializationStm 
{
	Identifier id;
	Exp assignExp;
	
	public InitializeSimple(Identifier id, Exp assignExp) 
	{
		this.id = id;
		this.assignExp = assignExp;
	}
}
