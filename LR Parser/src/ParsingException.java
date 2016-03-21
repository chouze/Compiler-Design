/**
 * 
 * @author Christopher Houze, Clifford Black, David Carlin
 *
 */
public class ParsingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	/**
	 * Create a new ParsingException with the specified message
	 * @param message the message to display
	 */
	public ParsingException(String message){
		super(message);
	}
}
