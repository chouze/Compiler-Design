
public class SimpleToken {
	public String tokenName, tokenValue;
	
	public SimpleToken(String tokenName){
		this.tokenName = tokenName;
		this.tokenValue = tokenName;
	}
	
	public SimpleToken(String tokenName, String tokenValue){
		this.tokenName = tokenName;
		this.tokenValue = tokenValue;
	}
	
	public String toString(){
		return this.tokenValue;
	}
}
