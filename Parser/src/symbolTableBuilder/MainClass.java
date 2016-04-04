package symbolTableBuilder;

public class MainClass 
{
	Identifier className;
	Identifier args; 
	VarDeclList v;
	Statement stmt;
	SymbolTable symTab;
	
	public MainClass(Identifier name, Identifier args, VarDeclList v, Statement s) 
	{
		this.className = name;
		this.args = args;
		this.v = v;
		this.stmt = s;
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
	
}
