package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class Program 
{
	MainClass mainClass;
	ClassDeclList classDecls;
	
	public Program(MainClass mainClass, ClassDeclList classDecls) {
		super();
		this.mainClass = mainClass;
		this.classDecls = classDecls;
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
