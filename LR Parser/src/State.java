import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class State {
	static int stateNumbers = 0;
	int stateNumber;
	public Set<String> nonTerminals;
	public Set<Rule> rules;
	public Map<String, State> transitions;
	public static Set<State> allStates = new LinkedHashSet<State>(); //Are you in good hands?

	public State (){
		nonTerminals = new HashSet<String>();
		rules = new LinkedHashSet<Rule>();
		transitions = new HashMap<String, State>();

	}

	public void add(Rule rule){
		List<Rule> newRules = new ArrayList<Rule>();
		rules.add(rule);
		/*
		nonTerminals.add(rule.reduceTo);

		newRules = TableCreator.rules.stream()
				.filter(t -> t.reduceTo.equals(rule.afterDot.get(0))).
				collect(Collectors.toList());
		rules.addAll(newRules);*/
		boolean changed = true;

		while(changed){
			changed = false;
			newRules.clear();
			for(Rule r : rules){
				if (!r.isFinished() && !nonTerminals.contains(r.reduceTo)){
					nonTerminals.add(r.reduceTo);
					newRules.addAll(TableCreator.rules.stream()
							.filter(t -> t.reduceTo.equals(r.afterDot.get(0))).
							collect(Collectors.toList()));
					changed = true;
				}
			}
			rules.addAll(newRules);
		}
	}


	public void expand(){
		if(!allStates.contains(this)){
			allStates.add(this);
			this.stateNumber = stateNumbers++;

			State newState;

			for(Rule rule : rules){
				if(!rule.isFinished()){
					newState = new State();
					newState.add(rule.getNextVersion());

					//System.out.println(newState);
					if(!allStates.contains(newState)){
						transitions.put(rule.afterDot.get(0), newState);
						newState.expand();
					}
					else{
						for(State state : allStates){
							if (state.equals(newState)){
								transitions.put(rule.afterDot.get(0), state);
							}
						}
					}

				}
			}

		}



	}




	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nonTerminals == null) ? 0 : nonTerminals.hashCode());
		result = prime * result + ((rules == null) ? 0 : rules.hashCode());
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
		State other = (State) obj;
		if (nonTerminals == null) {
			if (other.nonTerminals != null)
				return false;
		} else if (!nonTerminals.equals(other.nonTerminals))
			return false;
		if (rules == null) {
			if (other.rules != null)
				return false;
		} else if (!rules.equals(other.rules))
			return false;
		return true;
	}
	/*
	public boolean equals(State s){
		return this.nonTerminals.equals(s.nonTerminals) && this.rules.equals(s.rules);
	}*/

	public String toString(){
		String temp = "State Number: " + stateNumber + "\n";
		for(Rule rule : rules){
			temp += rule.toString() + "\n";
		}
		temp += "\nTransitions: \n";
		if(!transitions.isEmpty()){
			for(String key : transitions.keySet()){
				temp += "Transition to " + transitions.get(key).stateNumber + " on " + key + "\n";
			}
		}
		else{
			temp += "None";
		}

		temp += "\n\n";
		return temp;
	}
}
