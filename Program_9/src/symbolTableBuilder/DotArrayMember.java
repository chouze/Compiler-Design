package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class DotArrayMember extends DotArray 
{
	Member member;

	public DotArrayMember(Member member) 
	{
		this.member = member;
	}
	
	public Object accept(Visitor v) 
	{
		return v.visit(this);
	}
	
}
