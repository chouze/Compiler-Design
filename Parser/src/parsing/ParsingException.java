/**
 * @author Christopher Houze, David Carlin, Clifford Black
 */
package parsing;
import lexing.node.Token;

public class ParsingException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ParsingException(String message){
		super(message);
	}

	public ParsingException(){
		super();
	}
	
	public ParsingException(Token token){
		super("error on token: " +  token.getClass().getName() + ": " + token.toString() + " at line: " + token.getLine() + " position: " + token.getPos());
	}
	
	public ParsingException(Token token, String wantedName){
		super("error on token: " +  token.getClass().getName() + ": " + token.toString() + " at line: " + token.getLine() + " position: " + token.getPos()
		+ "\nwanted to eat: " + wantedName);
	}
}
