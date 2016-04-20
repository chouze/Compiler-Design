package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class Switch extends Statement
{
	Identifier id;
	CaseList caseList;
	
	public Switch(Identifier id, CaseList caseDefault) 
	{
		this.id = id;
		this.caseList = caseDefault;
	}
	
	public Object accept(Visitor v) 
	{
		return v.visit(this);
	}
}
