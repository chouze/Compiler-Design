
import java.io.IOException;

/**
 * 
 * @author Christopher Houze, Clifford Black, David Carlin
 *
 */
public class ParserDriver {

	public static void main(String[] args) throws IOException {
		TableCreator tableCreator = new TableCreator(args[1]);
		tableCreator.run();
		
		Parser parser = new Parser(args[0], args[1]);
		parser.parseInput();
	}

}

