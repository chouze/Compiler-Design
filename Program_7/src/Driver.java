/**
 * @author Christopher Houze, David Carlin, Clifford Black
 */
import java.io.FileNotFoundException;

import parsing.Parser;
import symbolTableBuilder.*;


public class Driver {

	public static void main(String[] args) 
	{
		BuildST bst = new BuildST();		
		Parser parser = null;
		
		try {
			parser = new Parser(args[0]);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Program prog = parser.Parse();
		bst.visit(prog);
		
		System.out.println(bst.symTabProg);
		//System.out.println(bst.symTabMethod);
		//System.out.println(bst.symTabClass);
	}

}



