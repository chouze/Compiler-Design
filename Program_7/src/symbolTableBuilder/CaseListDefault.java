package symbolTableBuilder;

public class CaseListDefault extends CaseList 
{
	Statement s;

	public CaseListDefault(Statement s) 
	{
		this.s = s;
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
