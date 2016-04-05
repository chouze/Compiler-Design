package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class VarDeclTypeAssign 
{
	Exp exp;

	public VarDeclTypeAssign(Exp exp) 
	{
		this.exp = exp;
	}
	
	public VarDeclTypeAssign() {
		// TODO Auto-generated constructor stub
	}

	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
