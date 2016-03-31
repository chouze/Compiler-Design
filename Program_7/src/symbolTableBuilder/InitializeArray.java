package symbolTableBuilder;

public class InitializeArray extends InitializationStm
{
	Identifier id;
	Exp arrayExp;
	Exp assignExp;
	
	public InitializeArray(Identifier id, Exp arrayExp, Exp assignExp) 
	{
		this.id = id;
		this.arrayExp = arrayExp;
		this.assignExp = assignExp;
	}
}
