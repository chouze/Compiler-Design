import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TableState {
	Map<String, String> actionMap;

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
	
	/*
	 * @return action to occur based on input
	 */
	public String getAction(String input){
		return actionMap.get(input);
	}

	public String toString(){
		String temp = "";
		
		for (String s : actionMap.keySet()) {
			temp += s + " = " + actionMap.get(s) + "\n";
		}
		temp += "End state\n";
		return temp;
	}
}
