package symbolTableBuilder;

public class IncrementArray extends IncrementStm 
{
	Identifier id;
	Exp arrayExp;
	Exp assignExp;
	
	public IncrementArray(Identifier id, Exp arrayExp, Exp assignExp) 
	{
		this.id = id;
		this.arrayExp = arrayExp;
		this.assignExp = assignExp;
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
