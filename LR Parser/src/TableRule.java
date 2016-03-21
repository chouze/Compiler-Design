import java.util.Stack;

/**
 * 
 * @author Christopher Houze, Clifford Black, David Carlin
 *
 */
public class TableRule {
	String LSide;
	Stack<String> RSide;

	/**
	 * Constructor
	 * @param LSide the left side of the rule
	 * @param RSide the right side of the rule
	 */
	public TableRule(String LSide, Stack<String> RSide)
	{
		this.LSide = LSide;
		this.RSide = RSide;
	}

	/**
	 * Getter
	 * @return the left hand side of the rule
	 */
	public String getLSide(){
		return LSide;
	}

	/**
	 * Getter
	 * @return the right hand side of the rule
	 */
	@SuppressWarnings("unchecked")
	public Stack<String> getRSide(){
		return (Stack<String>) RSide.clone();
	}

}
