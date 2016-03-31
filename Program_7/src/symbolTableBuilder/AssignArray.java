package symbolTableBuilder;

public class AssignArray extends Assign 
{
	Exp arrayExp;
	Exp assignExp;
	
	public AssignArray(Identifier id, Exp arrayExp, Exp assignExp) 
	{
		super(id);
		this.arrayExp = arrayExp;
		this.assignExp = assignExp;
	};
}
