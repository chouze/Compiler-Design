package symbolTableBuilder;

public class CaseListCase extends CaseList 
{
	Exp caseExp;
	Statement s;
	CaseList caseList;
	
	public CaseListCase(Exp caseExp, Statement s, CaseList caseList) 
	{
		this.caseExp = caseExp;
		this.s = s;
		this.caseList = caseList;
	}
}
