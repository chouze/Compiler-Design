import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TableState {
	Map<String, String> actionMap;
	
	public TableState(String header, String actions)
	{
		actionMap = new HashMap<String,String>();
		
		String[] headerArr = header.split(",");
		
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
		actionScanner.close();
		
		for(String s : actionMap.keySet()){
			System.out.println(actionMap.get(s));
		}
		System.out.println("End state");
		/*
		System.out.println(actionArr.length);
		for(String s : actionArr){
			System.out.println(s);
		}
		for(int i = 0; i < headerArr.length; i++){
			actionMap.put(headerArr[i], actionArr[i]);
		}
		/*
		//Scanner headerScanner = new Scanner(header);
		Scanner actionScanner = new Scanner(actions);
		
		//headerScanner.useDelimiter(",");
		actionScanner.useDelimiter(",");
		
		String key;
		String value;
		
		while (headerScanner.hasNext())
		{
			key = headerScanner.next();
			value = actionScanner.next();
			actionMap.put(key, value);
			System.out.println(key + " = " +value);
		}
		
		headerScanner.close();
		actionScanner.close();*/
	}
	
	
	public String getAction(String input){
		return actionMap.get(input);
	}
	
	public String toString()
	{
		String temp = "";
		for(String key: actionMap.keySet())
		{
			temp = temp + ","+ actionMap.get(key);
		}
		return temp;
		
	}
}
