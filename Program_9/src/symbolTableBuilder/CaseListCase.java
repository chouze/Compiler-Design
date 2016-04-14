package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class CaseListCase extends CaseList 
{
	Exp caseExp;
	Statement s;
	CaseList caseList;
	Identifier id;
	
	public CaseListCase(Identifier id, Exp caseExp, Statement s, CaseList caseList) 
	{
		this.caseExp = caseExp;
		this.s = s;
		this.caseList = caseList;
		this.id = id;
	}

	public Object accept(Visitor v) 
	{
		return v.visit(this);
	}
}
