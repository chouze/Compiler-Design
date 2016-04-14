package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

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
	
	public Object accept(Visitor v) 
	{
		return v.visit(this);
	}
	
}
