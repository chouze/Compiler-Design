package symbolTableBuilder;

public class ClassDeclList extends java.util.LinkedList<ClassDecl>
{
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}