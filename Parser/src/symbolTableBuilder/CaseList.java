package symbolTableBuilder;

public abstract class CaseList 
{
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
