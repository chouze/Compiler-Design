package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class MemberId extends Member 
{
	Identifier id;
	ExpList expList;
	
	public MemberId(Identifier id, ExpList expList) 
	{
		this.id = id;
		this.expList = expList;
	}
	
	public String accept(Visitor v) 
	{
		return v.visit(this);
	}
	
}
