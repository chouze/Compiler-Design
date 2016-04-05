package symbolTableBuilder;

public class Alist 
{
	Less less;
	Alist alist;
	
	public Alist(Less less, Alist alist) 
	{
		this.less = less;
		this.alist = alist;
	}
	
	public Alist()
	{
		
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
	
	
}