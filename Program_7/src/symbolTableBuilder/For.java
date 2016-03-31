package symbolTableBuilder;

public class For extends Statement 
{
	InitializationStm initialize;
	Exp e;
	IncrementStm increment;
	Statement s;
	
	public For(InitializationStm initialize, Exp e, IncrementStm increment,
			Statement s) 
	{
		this.initialize = initialize;
		this.e = e;
		this.increment = increment;
		this.s = s;
	}
}
