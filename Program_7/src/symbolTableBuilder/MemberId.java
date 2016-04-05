package symbolTableBuilder;

public class MemberId extends Member 
{
	Identifier id;
	ExpList expList;
	
	public MemberId(Identifier id, ExpList expList) 
	{
		this.id = id;
		this.expList = expList;
	}
	
	
}
