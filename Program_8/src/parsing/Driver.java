package parsing;
/**
 * @author Christopher Houze, David Carlin, Clifford Black
 */
import java.io.FileNotFoundException;

import symbolTableBuilder.*;


public class Driver {

	public static void main(String[] args) 
	{
		BuildST bst = new BuildST();
		SymbolTableTypeCheck sttc = new SymbolTableTypeCheck();
		Parser parser = null;
		
		try {
			parser = new Parser(args[0]);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Program prog = parser.Parse();
		bst.visit(prog);
		sttc.visit(prog);
		
	}

}



