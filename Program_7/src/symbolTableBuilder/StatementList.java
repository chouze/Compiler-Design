package symbolTableBuilder;

public class StatementList extends java.util.LinkedList<Statement> {
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}

}
