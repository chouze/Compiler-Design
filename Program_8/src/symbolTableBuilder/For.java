package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

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
	
	public String accept(Visitor v) 
	{
		return v.visit(this);
	}
}
