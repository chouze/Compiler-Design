package symbolTableBuilder;

public abstract class ClassDecl
{
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
