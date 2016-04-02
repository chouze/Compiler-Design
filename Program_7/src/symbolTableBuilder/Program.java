package symbolTableBuilder;

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
