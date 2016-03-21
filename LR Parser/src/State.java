import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Christopher Houze, Clifford Black, David Carlin
 *
 */
public class State {
	//The current available state number
	private static int stateNumbers = 1;
	
	//Variable declarations
	int stateNumber;
	List<String> nonTerminals;
	LinkedHashSet<Rule> rules;
	Map<String, State> transitions;
	List<Rule> allRules;
	LinkedHashSet<State> allStates;
	String filename;

	/**
	 * Constructor
	 * @param allStates all the states that have been created so far
	 * @param allRules all the rules in the grammar
	 */
	public State (LinkedHashSet<State> allStates, List<Rule> allRules){
		nonTerminals = new ArrayList<String>();
		rules = new LinkedHashSet<Rule>();
		transitions = new HashMap<String, State>();
		this.allStates = allStates;
		this.allRules = allRules;
	}

	/**
	 * Overloaded Constructor
	 * @param allStates all the states that have been created so far
	 * @param allRules all the rules in the grammar
	 * @param outputFileName filename to output the state information to
	 */
	public State(LinkedHashSet<State> allStates, List<Rule> allRules, String outputFileName){
		nonTerminals = new ArrayList<String>();
		rules = new LinkedHashSet<Rule>();
		transitions = new HashMap<String, State>();
		this.allStates = allStates;
		this.allRules = allRules;
		this.filename = outputFileName;
	}


	/**
	 * Add the specified rule to the state, and all the rules that the specified rule requires
	 * @param rule the rule to add
	 */
	public void add(Rule rule){
		boolean newRules = true;
		Set<Rule> addingRules = new HashSet<Rule>();
		rules.add(rule);

		while(newRules){
			newRules = false;
			for(Rule r : rules){
				if(!r.isFinished()){
					addingRules = getRulesStartingWith(r.afterDot.get(0));
				}
			}
			for(Rule addingRule : addingRules){
				if(rules.add(addingRule)){
					newRules = true;
				}
			}
		}
	}

	
	/**
	 * Get all the rules that reduce to the specified non terminal
	 * @param nt the nonterminal to look for
	 * @return the set of rules that reduce to the specified non terminal
	 */
	private Set<Rule> getRulesStartingWith(String nt){
		HashSet<Rule> rulesStartingWithNT = new HashSet<Rule>();

		for(Rule r : allRules){
			if(r.reduceTo.equals(nt)){
				rulesStartingWithNT.add(r);
			}
		}
		return rulesStartingWithNT;
	}

	/**
	 * Create all the transitions from this state
	 * If the state that this state transitions to does not exist, create it
	 * @return all the states that have been created so far
	 */
	public LinkedHashSet<State> expand(){
		if(!allStates.contains(this)){
			allStates.add(this);
			this.stateNumber = stateNumbers++;

			State newState;
			List<Rule> continuingRules;

			for(Rule rule : rules){
				if(!rule.isFinished() && !rule.afterDot.get(0).equals("$")){
					newState = new State(allStates, allRules);

					continuingRules = getRulesContinuingWith(rule.afterDot.get(0));

					for(Rule tempRule : continuingRules){
						newState.add(tempRule.getNextVersion());
					}

					if(!allStates.contains(newState)){
						transitions.put(rule.afterDot.get(0), newState);
						allStates = newState.expand();
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

		printStates();

		return allStates;
	}

	
	/**
	 * Print the state and their information to a file
	 */
	private void printStates(){
		try {
			PrintStream ps;
			if(filename != null){
				ps = new PrintStream(new File(filename));
			}
			else{
				ps = new PrintStream(new File("States.txt"));
			}
			for(State s : allStates){
				ps.print(s.toString() + "\r\n");
			}
			ps.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * Get all rules whose next token is the specified token
	 * @param token the token to look for
	 * @return an ArrayList of all the rules whose first token after the dot matches this token
	 */
	private ArrayList<Rule> getRulesContinuingWith(String token){
		ArrayList<Rule> list = new ArrayList<Rule> ();

		for(Rule rule : rules){
			if(!rule.isFinished() && rule.afterDot.get(0).equals(token)){
				list.add(rule);
			}
		}
		return list;
	}



	/**
	 * Auto Generated
	 * @return the hash representation of this object
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nonTerminals == null) ? 0 : nonTerminals.hashCode());
		result = prime * result + ((rules == null) ? 0 : rules.hashCode());
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

	/**
	 * Get a string representation of the object
	 * @return a string representing the object
	 */
	public String toString(){
		String temp = "State Number: " + stateNumber + "\r\n";
		for(Rule rule : rules){
			temp += rule.toString() + "\r\n";
		}
		temp += "\r\nTransitions: \r\n";
		if(!transitions.isEmpty()){
			for(String key : transitions.keySet()){
				temp += "Transition to " + transitions.get(key).stateNumber + " on " + key + "\r\n";
			}
		}
		else{
			temp += "None";
		}
		temp += "\r\n\r\n";
		return temp;
	}
}
