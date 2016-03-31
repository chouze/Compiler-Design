package symbolTableBuilder;

public class AssignMultiple extends Statement 
{
	Type type;
	Identifier varName;
	Exp assignExp;
	FormalVarExp moreDeclare;
	
	public AssignMultiple(Type type, Identifier varName, Exp assignExp,
			FormalVarExp moreDeclare) 
	{
		this.type = type;
		this.varName = varName;
		this.assignExp = assignExp;
		this.moreDeclare = moreDeclare;
	}
}
