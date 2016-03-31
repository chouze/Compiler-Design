package symbolTableBuilder;

public class MainClass 
{
	Identifier name;
	Identifier args; 
	VarDecl v;
	Statement s;
	
	public MainClass(Identifier name, Identifier args, VarDecl v, Statement s) 
	{
		this.name = name;
		this.args = args;
		this.v = v;
		this.s = s;
	}
	
}
