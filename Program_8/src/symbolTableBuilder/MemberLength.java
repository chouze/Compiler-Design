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

	public String accept(Visitor v) 
	{
		return v.visit(this);
	}
}
