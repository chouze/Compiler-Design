import java.util.ArrayList;
import java.util.List;


public class Rule {
	String reduceTo;
	ArrayList<String> beforeDot, afterDot;


	public String toString(){
		String t = reduceTo;
		t += " -> ";
		for(String token : beforeDot){
			/*
			if(TableCreator.allNonTerminals.contains(token)){
				t += "NonTerminal(" + token + ") ";
			}
			else{
				t += "Terminal(" + token + ") ";
			}*/
			t += token + " ";
		}

		t += ".";

		for(String token : afterDot){/*
			if(TableCreator.allNonTerminals.contains(token)){
				t += "NonTerminal(" + token + ") ";
			}
			else{
				t += "Terminal(" + token + ") ";
			}*/
			t += token + " ";
		}

		return t;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((afterDot == null) ? 0 : afterDot.hashCode());
		result = prime * result + ((beforeDot == null) ? 0 : beforeDot.hashCode());
		result = prime * result + ((reduceTo == null) ? 0 : reduceTo.hashCode());
		return result;
	}


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


	public boolean isFinished(){
		return afterDot.isEmpty();
	}

	public Rule getNextVersion(){
		if(!this.isFinished()){
			Rule r = new Rule();
			r.reduceTo = this.reduceTo;
			r.beforeDot = new ArrayList<String>(this.beforeDot);
			r.afterDot = new ArrayList<String>(this.afterDot);
			r.beforeDot.add(r.afterDot.get(0));
			r.afterDot.remove(0);

			return r;
		}
		else{
			throw new ParsingException("Rule is already finished, cannot get the next verison");
		}
	}
	/*
	public boolean equals (Rule r){
		return this.reduceTo.equals(r.reduceTo) && this.beforeDot.equals(r.beforeDot) && this.afterDot.equals(r.afterDot);
	}*/
}
