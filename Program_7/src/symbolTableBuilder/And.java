package symbolTableBuilder;

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
