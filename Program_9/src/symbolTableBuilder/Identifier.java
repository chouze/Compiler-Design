package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

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
	
	public Object accept(Visitor v) 
	{
		return v.visit(this);
	}
	
	public boolean equals(Identifier id){
		
		return (id.name).equals(id.name);
	}
	public String toString(){
		return name;
	}
}
