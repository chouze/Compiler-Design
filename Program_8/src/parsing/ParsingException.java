/**
 * @author Christopher Houze, David Carlin, Clifford Black
 */
package parsing;
import lexing.node.Token;

/**
 * Exception class for our parser.
 * Creates error messages to help debug a program written in our language
 *
 */
public class ParsingException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Create a parsing exception with the given message
	 * @param message the message to describe the exception
	 */
	public ParsingException(String message){
		super(message);
	}

	/**
	 * Create a generic parsing exception
	 */
	public ParsingException(){
		super();
	}
	
	/**
	 * Generates an exception with a message based on the given token
	 * @param token the token that caused the exception
	 */
	public ParsingException(Token token){
		super("\nerror on token: " +  token.getClass().getName() + ": \"" + token.toString() + "\"\nline: " + token.getLine() + " position: " + token.getPos());
	}
	
	/**
	 * Generates an exception with a message based on the given token and the expected token name
	 * @param token the token that caused the exception
	 * @param wantedName the name of the token that the parser was expecting
	 */
	public ParsingException(Token token, String wantedName){
		super("\nerror on token: " +  token.getClass().getName() + ": \"" + token.toString() + "\"\nline: " + token.getLine() + " position: " + token.getPos()
		+ "\nwanted to eat: " + wantedName);
	}
}
