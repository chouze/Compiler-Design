package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class InitializeSimple extends InitializationStm 
{
	Identifier id;
	Exp assignExp;
	
	public InitializeSimple(Identifier id, Exp assignExp) 
	{
		this.id = id;
		this.assignExp = assignExp;
	}
	
	public Object accept(Visitor v) 
	{
		return v.visit(this);
	}
}
