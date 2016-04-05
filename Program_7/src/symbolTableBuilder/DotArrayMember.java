package symbolTableBuilder;

public class DotArrayMember extends DotArray 
{
	Member member;

	public DotArrayMember(Member member) 
	{
		this.member = member;
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
	
}
