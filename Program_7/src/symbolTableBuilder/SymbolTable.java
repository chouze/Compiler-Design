package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

import java.util.HashMap;
import java.util.Map;


public class SymbolTable {
	Map<String, Binding> symTab;

	public SymbolTable() 
	{
		symTab = new HashMap<String, Binding>();
	}

	public SymbolTable put(Identifier i, Binding b)
	{
		if(!symTab.containsKey(i.getName()))
		{
			symTab.put(i.getName(), b);
			return this;
		}
		System.err.println("Identifier '" + i.name + "' already defined in this context.");
		return null;
	}
	
	public String toString(){	
		String temp = "";
		
		for (String s : symTab.keySet()) {
			temp += "Binding: " + symTab.get(s) + "\n";
		}
		temp += "End table\n";
		return temp;
	}

}
