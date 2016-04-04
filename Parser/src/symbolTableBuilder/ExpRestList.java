package symbolTableBuilder;

import java.util.LinkedList;

public class ExpRestList extends LinkedList<ExpRest> {

	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
