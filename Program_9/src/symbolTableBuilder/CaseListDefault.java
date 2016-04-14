package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class CaseListDefault extends CaseList 
{
	Statement s;

	public CaseListDefault(Statement s) 
	{
		this.s = s;
	}
	
	public Object accept(Visitor v) 
	{
		return v.visit(this);
	}
}
