package symbolTableBuilder;

import java.util.HashMap;
import java.util.Map;

/* 
 * Store Identifiers with their bindings.
 * Quick lookup.
 * 
 * @author ()
 * @version (Mar 2016)
 */

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
		System.err.println("Identifier " + i + " already defined in this context.");
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
