package symbolTableBuilder;

import java.util.LinkedList;

public class DotArrayList extends LinkedList<DotArray> 
{

	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
