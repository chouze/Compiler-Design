/**
 * @author Christopher Houze, David Carlin, Clifford Black
 */
import parsing.Parser;
import symbolTableBuilder.*;


public class Driver {

	public static void main(String[] args) 
	{
		BuildST bst = new BuildST();
		
		Parser parser = new Parser();
		
		bst.visit(parser.Parse());
		
	}

}



