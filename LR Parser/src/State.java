import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class State {
	static int stateNumbers = 1;
	int stateNumber;
	List<String> nonTerminals;
	LinkedHashSet<Rule> rules;
	Map<String, State> transitions;
	List<Rule> allRules;
	List<String> allTerminals;
	//public static Set<State> allStates = new LinkedHashSet<State>(); //Are you in good hands?
	private LinkedHashSet<State> allStates;

	public State (LinkedHashSet<State> allStates, List<Rule> allRules/*, List<String> allTerminals*/){
		nonTerminals = new ArrayList<String>();
		rules = new LinkedHashSet<Rule>();
		transitions = new HashMap<String, State>();
		this.allStates = allStates;
		this.allRules = allRules;
		//this.allTerminals = allTerminals;
	}
/*
	public void add(Rule rule){
		List<Rule> newRules = new ArrayList<Rule>();
		rules.add(rule);
		boolean changed = true;

		while(changed){
			changed = false;
			newRules.clear();
			for(Rule r : rules){
				if (!r.isFinished() && !nonTerminals.contains(r.reduceTo)){
					nonTerminals.add(r.reduceTo);
					changed = true;
					newRules.addAll(allRules.stream()
							.filter(t -> t.reduceTo.equals(r.afterDot.get(0))).
							collect(Collectors.toList()));
					
					
					if(r.afterDot.get(0).equals("Factor")){
						System.out.println(r.afterDot.get(0) + " " + r.reduceTo);
						System.out.println(newRules);
					}
				}
			}
			rules.addAll(newRules);
		}
	}
*/
	
	public void add(Rule rule){
		boolean newRules = true;
		Set<Rule> addingRules = new HashSet<Rule>();
		Set<String> usedNonTerminals = new HashSet<String>();
		rules.add(rule);
		
		while(newRules){
			newRules = false;
			for(Rule r : rules){
				if(!r.isFinished()){
					addingRules = getRulesStartingWith(r.afterDot.get(0));
				}
				/*
				if (!r.isFinished() && !usedNonTerminals.contains(r.afterDot.get(0))){ //Keep going
					for(Rule tempRule : allRules){
						if(r.afterDot.get(0).equals(tempRule.reduceTo)){
							
							if(addingRules.add(tempRule)){
								newRules = true;
							}
							addingRules.add(tempRule);
							if(!tempRule.isFinished()){
								usedNonTerminals.remove(tempRule.afterDot.get(0));
							}
							
						}
					}
				}
			
				usedNonTerminals.add(r.reduceTo);*/
				
			}
			for(Rule addingRule : addingRules){
				if(rules.add(addingRule)){
					newRules = true;
				}
			}
			//rules.addAll(addingRules);
			/*
			for(Rule r : addingRules){
				if(rules.add(r)){
					newRules = true;
					//System.out.println("Successfully added rule: " + r);
				}
				else{
					//System.out.println("Failed to add rule: " + r + " to set:\n");
					
				}
				
			}
			for(Rule r2 : rules){
				//System.out.println(r2);
			}
			//System.out.println();*/
			
		}
	}
	
	private Set<Rule> getRulesStartingWith(String nt){
		HashSet<Rule> rulesStartingWithNT = new HashSet<Rule>();
		
		for(Rule r : allRules){
			if(r.reduceTo.equals(nt)){
				rulesStartingWithNT.add(r);
			}
		}
		return rulesStartingWithNT;
	}

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

					//newState.add(rule.getNextVersion());

					//System.out.println(newState);
					/*
					if(transitions.containsKey(rule.afterDot.get(0))){
						transitions.get(rule.afterDot.get(0)).add(rule.getNextVersion());
					}*/

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

		try {
			PrintStream bf = new PrintStream(new File("States.txt"));
			for(State s : allStates){
				bf.print(s.toString() + "\r\n");
			}
			bf.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return allStates;
	}

	public ArrayList<Rule> getRulesContinuingWith(String token){
		ArrayList<Rule> list = new ArrayList<Rule> ();

		for(Rule rule : rules){
			if(!rule.isFinished() && rule.afterDot.get(0).equals(token)){
				list.add(rule);
			}
		}
		return list;
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
		/*
		temp += "\r\nReductions: \r\n";
		for(Rule r : rules){
			if (r.isFinished()){
				temp += "Reduce on " + r + "\r\n";
			}
			else{
				temp += "Can't reduce " + r + "\r\n";
			}
		}*/

		temp += "\r\n\r\n";
		return temp;
	}
}
