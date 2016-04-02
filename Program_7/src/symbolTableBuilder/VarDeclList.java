package symbolTableBuilder;

public class VarDeclList extends java.util.LinkedList <VarDecl>{
	
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
