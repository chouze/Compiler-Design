package symbolTableBuilder;

public abstract class Member {
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
