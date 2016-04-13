package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class MemberLength extends Member 
{
	Exp length;
	
	public MemberLength(Exp length){
		this.length = length;
	}
	
	public MemberLength(){
		
	}
	
	public Object accept(Visitor v) 
	{
		return v.visit(this);
	}
}
