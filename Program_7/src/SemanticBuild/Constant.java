package semanticBuild;

/**
 * A Constant is an Expression which has a value
 * @author ()
 * @version ()
 */
public class Constant extends Exp 
{
	Integer value;
	
	public Constant(Integer n)
	{
		value = n;
	}
	
	public Object accept(Visitor v)
	{
		return v.visit(this);
	}
}
