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
	Map<String, SymbolTable> children;

	public SymbolTable() {
		symTab = new HashMap<String, Binding>();
		children = new HashMap<String, SymbolTable>();
	}

	public void putChild(String id, SymbolTable st){
		children.put(id, st);
	}
	
	public SymbolTable getChild(String id){
		return children.get(id);
	}
	
	public boolean containsChild(String id)
	{
		return children.containsKey(id);
	}
	public String getType(Identifier id) {
		String s = id.name;
		return symTab.get(s).type;
	}

	public SymbolTable put(Identifier i, Binding b) {
		if (!symTab.containsKey(i.getName())) {
			symTab.put(i.getName(), b);
			return this;
		}
		System.err.println("Identifier '" + i.name + "' already defined in this context.");
		return null;
	}

	public Binding get(Identifier i) {
		return symTab.get(i.name);
	}

	public boolean check(String name, IdType usage) {
		return symTab.get(name).usage.equals(usage);
	}

	// Check that the given identifier is the correct usage: variable, class, method

	public void check(Identifier i, IdType it) {
		Binding binding = symTab.get(i.name);
		if (binding == null)
			System.err.println("Undefined Symbol: " + i.name);
		else if (binding.getIdType() != it)
			System.err.println("Incorrect usage for " + i);
	}

	/* The two Identifiers should be of the same type */
	public void check(Identifier i1, Identifier i2) {
		Binding binding1 = symTab.get(i1);
		Binding binding2 = symTab.get(i2);
		if (binding1 == null)
			System.err.println("Undefined Symbol:- " + i1);
		if (binding2 == null)
			System.err.println("Undefined Symbol:= " + i2);
		if (!(binding1.getType()).equals(binding2.getType())) {
			System.err.println("Types do not match ... ");
		}
	}

	/* Check that the given identifier is of the correct type **/
	public void check(Identifier id, String type) {
		Binding b = get(id);
		if (b == null)
			System.err.println(id + " undefined ");
		if (!(get(id)).getType().equals(type)) {
			System.err.println("Wrong type for given identifier " + id + " should be " + type);
		}
	}

	public void check(String e1, String e2) {
		if (!e1.equals(e2))
			System.err.println("Types do not match: " + e1 + " and " + e2);
		else
		System.out.println("Types do match: " + e1 + " and " + e2);
	}
	
	public boolean find(Identifier id){
		return symTab.containsKey(id.name);
	}

	public String toString() {
		String temp = "";

		for (String s: symTab.keySet()) {
			temp += "Binding: " + symTab.get(s) + "\n";
		}

		temp += "End table\n";
		
		
		return temp;
	}

}
