package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class And 
{
	Less less;
	Alist alist;
	
	public And(Less less, Alist alist) 
	{
		this.less = less;
		this.alist = alist;
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
