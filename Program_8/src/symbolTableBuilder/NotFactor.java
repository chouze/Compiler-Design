package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class NotFactor extends Not
{
	Factor factor;
	DotArrayList dotList;
	
	public NotFactor(Factor factor, DotArrayList dotList) 
	{
		this.factor = factor;
		this.dotList = dotList;
	}
	
	
}
