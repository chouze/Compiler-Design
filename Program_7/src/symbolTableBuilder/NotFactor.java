package symbolTableBuilder;

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
