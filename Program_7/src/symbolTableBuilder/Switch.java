package symbolTableBuilder;

public class Switch extends Statement
{
	Identifier id;
	CaseList caseDefault;
	
	public Switch(Identifier id, CaseList caseDefault) 
	{
		this.id = id;
		this.caseDefault = caseDefault;
	}
}
