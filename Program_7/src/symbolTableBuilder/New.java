package symbolTableBuilder;

public abstract class New {
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
