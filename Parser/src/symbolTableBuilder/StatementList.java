package symbolTableBuilder;

public class StatementList extends java.util.LinkedList {
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}

}
