package symbolTableBuilder;

import java.util.LinkedList;

public class MethodDeclList extends LinkedList<MethodDecl> {

	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
