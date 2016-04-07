package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

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
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
