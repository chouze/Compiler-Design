package symbolTableBuilder;

import java.util.LinkedList;

public class ElseIfList extends LinkedList<ElseIf> {

	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
