import java.util.Stack;

public class TableRule {
	String LSide;
	Stack<String> RSide;
	
	public TableRule(String LSide, Stack<String> RSide)
	{
		this.LSide = LSide;
		this.RSide = RSide;
	}
	
	public String getLSide(){
		
		return LSide;
	}
	
	public Stack<String> getRSide(){
		
		return (Stack<String>) RSide.clone();
	}
	
}
