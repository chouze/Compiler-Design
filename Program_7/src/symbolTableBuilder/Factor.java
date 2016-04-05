package symbolTableBuilder;

public abstract class Factor {
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
