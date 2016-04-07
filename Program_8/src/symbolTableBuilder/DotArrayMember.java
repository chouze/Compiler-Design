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
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
	
}
