package symbolTableBuilder;

import java.util.LinkedList;

public class FormalRestList extends LinkedList<FormalRest> 
{

	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
