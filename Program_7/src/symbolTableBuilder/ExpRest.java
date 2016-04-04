package symbolTableBuilder;

import java.util.LinkedList;

public class ExpRest extends LinkedList 
{
	Exp e; //?
	
	public ExpRest(Exp e) 
	{
		this.e = e;
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
