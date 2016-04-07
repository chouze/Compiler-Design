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
	CaseList caseDefault;
	
	public Switch(Identifier id, CaseList caseDefault) 
	{
		this.id = id;
		this.caseDefault = caseDefault;
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
