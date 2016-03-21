import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 
 * @author Christopher Houze, Clifford Black, David Carlin
 *
 */
public class TableState {
	Map<String, String> actionMap;

	/**
	 * Constructor
	 * @param headerArr the header from the parsing table
	 * @param actions the row from the parsing table representing this state
	 */
	public TableState(String[] headerArr, String actions)
	{
		actionMap = new HashMap<String,String>();
		
		Scanner actionScanner = new Scanner(actions);
		actionScanner.useDelimiter("");
		
		String input = "", temp;
		int index = 0;
		
		while(actionScanner.hasNext()){
			temp = actionScanner.next();
			if(temp.equals(",")){
				actionMap.put(headerArr[index++], input);
				input = "";
			}
			else{
				input += temp;
			}		
		}
		actionMap.put(headerArr[index++], input);
		actionScanner.close();
		
	}
	
	/**
	 * Get the action based on the given input
	 * @param input the input to check
	 * @return action to occur based on input
	 */
	public String getAction(String input){
		return actionMap.get(input);
	}

	/**
	 * Return a string representation of this object
	 * @return the string representing this object
	 */
	public String toString(){
		String temp = "";
		
		for (String s : actionMap.keySet()) {
			temp += s + " = " + actionMap.get(s) + "\n";
		}
		temp += "End state\n";
		return temp;
	}
}
