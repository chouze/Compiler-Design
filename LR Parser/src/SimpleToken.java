
/**
 * 
 * @author Christopher Houze, Clifford Black, David Carlin
 *
 */
public class SimpleToken {
	public String tokenName, tokenValue;
	
	/**
	 * Constructor for when the token is a keyword or other grammar specified character
	 * @param tokenName the name of the token
	 */
	public SimpleToken(String tokenName){
		this.tokenName = tokenName;
		this.tokenValue = tokenName;
	}
	
	/**
	 * Constructor for when the token is not the same as the contents, such as an identifier or number
	 * @param tokenName the name of the token
	 * @param tokenValue the content of the token
	 */
	public SimpleToken(String tokenName, String tokenValue){
		this.tokenName = tokenName;
		this.tokenValue = tokenValue;
	}
	
	/**
	 * Return a string representation of this object
	 * @return the string representing this object
	 */
	public String toString(){
		return this.tokenValue;
	}
}
