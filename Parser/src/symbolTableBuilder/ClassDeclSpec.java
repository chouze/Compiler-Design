package symbolTableBuilder;

public class ClassDeclSpec 
{
	Identifier className;

	public ClassDeclSpec(Identifier className) 
	{
		this.className = className;
	}

	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
