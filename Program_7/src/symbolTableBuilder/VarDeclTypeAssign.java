package symbolTableBuilder;

public class VarDeclTypeAssign 
{
	Exp exp;

	public VarDeclTypeAssign(Exp exp) 
	{
		this.exp = exp;
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}