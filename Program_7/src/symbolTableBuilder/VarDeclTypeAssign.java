package symbolTableBuilder;

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
