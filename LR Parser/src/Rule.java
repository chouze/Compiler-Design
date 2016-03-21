import java.util.ArrayList;

/**
 * 
 * @author Christopher Houze, Clifford Black, David Carlin
 *
 */
public class Rule {
	private static int ruleNumbers = 0;
	String reduceTo;
	ArrayList<String> beforeDot, afterDot;
	int ruleNumber;
	
	/**
	 * Constructor
	 * @param reduceTo the nonterminal that this rule reduces to
	 */
	public Rule (String reduceTo){
		this.reduceTo = reduceTo;
		this.beforeDot = new ArrayList<String>();
		this.afterDot = new ArrayList<String>();
		this.ruleNumber = ruleNumbers++;
	}

	
	/**
	 * Get the next version of the rule, if there is one
	 * @return the new version of the rule
	 */
	public Rule getNextVersion(){
		if(!this.isFinished()){
			Rule r = new Rule(this.reduceTo);
			r.beforeDot = new ArrayList<String>(this.beforeDot);
			r.afterDot = new ArrayList<String>(this.afterDot);
			r.beforeDot.add(r.afterDot.get(0));
			r.afterDot.remove(0);
			r.ruleNumber = this.ruleNumber;
			return r;
		}
		else{
			throw new ParsingException("Rule is already finished, cannot get the next verison");
		}
	}
	
	
	/**
	 * Find out whether or not the rule has reached the end
	 * @return true if the rule is finished, false otherwise
	 */
	public boolean isFinished(){
		return afterDot.isEmpty();
	}
	
	
	/**
	 * Get a string representation of the object
	 * @return a string representing the object
	 */
	public String toString(){
		String t = reduceTo;
		t += " -> ";
		
		for(String token : beforeDot){
			t += token + " ";
		}

		t += ".";

		for(String token : afterDot){
			t += token + " ";
		}
		
		return t;
	}


	/**
	 * Auto Generated
	 * @return the hash representation of this object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((afterDot == null) ? 0 : afterDot.hashCode());
		result = prime * result + ((beforeDot == null) ? 0 : beforeDot.hashCode());
		result = prime * result + ((reduceTo == null) ? 0 : reduceTo.hashCode());
		return result;
	}


	/**
	 * Auto Generated
	 * @param obj the object to compare against
	 * @return whether the two objects are equal
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rule other = (Rule) obj;
		if (afterDot == null) {
			if (other.afterDot != null)
				return false;
		} else if (!afterDot.equals(other.afterDot))
			return false;
		if (beforeDot == null) {
			if (other.beforeDot != null)
				return false;
		} else if (!beforeDot.equals(other.beforeDot))
			return false;
		if (reduceTo == null) {
			if (other.reduceTo != null)
				return false;
		} else if (!reduceTo.equals(other.reduceTo))
			return false;
		return true;
	}


	

	
	
}
