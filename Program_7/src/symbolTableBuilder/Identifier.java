package symbolTableBuilder;

public class Identifier {
	String name;
	public Identifier (String n)
	{
		name = n;
	}

	String getName()
	{
		return name;
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
