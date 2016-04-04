package symbolTableBuilder;

import java.util.LinkedList;
import java.util.List;

/* 
 * A Binding associates an Identifier with 
 * 	Its usage: class, method, variable
 * 	Its type, or return type for methods
 * 	For methods, a List of the parameter types
 * 
 * @author ()
 * @version (Mar 2016)
 */
public class Binding {
	Identifier id; //This class type should exist in our parser.
	IdType usage;
	String type;
	List<String> parms; //parameter types
	
	public Binding (Identifier i, IdType u, String t)
	{
		id = i;
		usage = u;
		type = t;
		if(u == IdType.METHOD)
			parms = new LinkedList<String>();
	}
	
	//class names do not have a type
	public Binding (Identifier i, IdType u)
	{
		id = i;
		usage = u;
		if(u == IdType.METHOD)
			parms = new LinkedList<String>();
	}
	
	public void addParams(ExpList el){
		parms.add(el.e.getClass().toString());
		if(!el.multipleExp.isEmpty()){
			for(Object e: el.multipleExp){
				parms.add(e.getClass().toString());
			}
		}	
	}
	
	public String toString()
	{
		String temp = "(ID: " + id.name + ", Usage: " + usage;
		
		if(!(type == null))
			temp += ", Type: " + type;
		
		if(usage == IdType.METHOD){
			temp += ", Params: " + parms;
		}
		
		return temp + ")";
	}
}
