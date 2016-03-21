import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 
 * @author Christopher Houze, Clifford Black, David Carlin
 *
 */
public class TableCreator {
	//Specify which string the rules are separated by
	static final String ruleSeparator = "->";
	
	//Variable declarations
	ArrayList<String> allNonTerminals, allTerminals;
	HashMap <String, HashSet<String>> folSet;
	Scanner sc;
	File file;
	String filename;
	ArrayList<Rule> rules;
	LinkedHashSet<State> states;
	HashSet<Rule> nullableRules;

	/**
	 * Constructor
	 * @param filename the name of the grammar file
	 * @throws FileNotFoundException of the grammar file could not be found
	 */
	public TableCreator(String filename) throws FileNotFoundException{
		file = new File(filename);
		sc = new Scanner(new FileReader(file));
		rules = new ArrayList<Rule>();
		allNonTerminals = new ArrayList<String> (100);
		allTerminals = new ArrayList<String> (100);
		states = new LinkedHashSet<State> (200);
		folSet = new HashMap<String, HashSet<String>>();
		this.filename = filename;
	}

	
	/**
	 * Run the table creator to produce the output file
	 * @throws IOException if the files we unable to be accessed or created
	 */
	public void run() throws IOException{
		scanForTokens();
		readRules();
		createStates();
		findFOLs();
		createTable(filename.split("\\.")[0] + "_Output.csv");
	}
	
	
	/**
	 * Scan the input file for non terminal and terminal tokens
	 * In order for a token to found to be a non terminal, if must appear on the
	 * left hand side of a rule at least once. Any tokens that appear in the grammar, but 
	 * do not appear on the left hand side of a rule are found to be terminal
	 * @throws FileNotFoundException if the file could not be found
	 */
	public void scanForTokens() throws FileNotFoundException{
		Scanner lineScanner;
		String temp;
		while (sc.hasNext()){
			lineScanner = new Scanner(sc.nextLine());
			temp = lineScanner.next();
			if(!allNonTerminals.contains(temp)){
				allNonTerminals.add(temp);
			}
		}
		
		//Reset the scanner
		sc = new Scanner(new FileReader(file));
		
		while(sc.hasNext()){
			lineScanner = new Scanner(sc.nextLine());
			while(lineScanner.hasNext()){
				temp = lineScanner.next();
				if(!temp.equals(ruleSeparator) && !allNonTerminals.contains(temp) && !allTerminals.contains(temp)){
					allTerminals.add(temp);
				}
			}
		}
	}

	
	/**
	 * Read the grammar rules and create the rule objects
	 * @throws FileNotFoundException
	 */
	public void readRules() throws FileNotFoundException{
		Scanner lineScanner;
		sc = new Scanner(new FileReader(file));
		Rule r;
		
		while(sc.hasNext()){
			lineScanner = new Scanner(sc.nextLine());
			String temp = lineScanner.next();
			
			if(allNonTerminals.contains(temp)){
				r = new Rule(temp);
				lineScanner.next(); //Eat the ->
				while(lineScanner.hasNext()){
					r.afterDot.add(lineScanner.next());
				}
				rules.add(r);
			}
			else{
				lineScanner.close();
				throw new IllegalArgumentException();
			}
		}
	}


	/**
	 * Create the state objects based on the grammar rules
	 */
	public void createStates(){
		states = new LinkedHashSet<State>();
		State current = new State(states, rules);
		
		//Add the first rule
		current.add(rules.get(0)); 

		//Create the rest of the states based on the rules and the first state
		states = current.expand();
	}


	/**
	 * Find the FOL sets of all the non terminal characters
	 */
	public void findFOLs(){
		HashSet<String> fol = new HashSet<String>();

		for(String nt : allNonTerminals){
			fol = findFolHelp(nt, new HashSet<String>());
			folSet.put(nt, fol);
		}
	}

	/**
	 * Recursive helper method for finding the FOL set of a single non terminal
	 * @param nt the non terminal to find the FOL set for
	 * @param lookedAt the non terminals that have already had their FOL set calculated
	 * @return the set of all terminals that can follow this non terminal
	 */
	private HashSet<String> findFolHelp(String nt, Set<String> lookedAt){
		HashSet<String> fol = new HashSet<String>();
		lookedAt.add(nt);
		for(Rule r : rules){
			if(r.afterDot.contains(nt)){
				for(int i = 0; i < r.afterDot.size() - 1; i++){
					if(nt.equals(r.afterDot.get(i))){
						if(allNonTerminals.contains(r.afterDot.get(i+1))){
							if(!lookedAt.contains(r.afterDot.get(i + 1))){
								fol.addAll(findFolHelp(r.afterDot.get(i+1), lookedAt));
							}
							fol.addAll(findFirst(r.afterDot.get(i+1), new HashSet<String>()));
						}
						else {
							fol.add(r.afterDot.get(i+1));
						}
					}
				}
				if(r.afterDot.get(r.afterDot.size() - 1).equals(nt) && !lookedAt.contains(r.reduceTo)){
					fol.addAll(findFolHelp(r.reduceTo, lookedAt));
				}

			}
		}
		return fol;
	}


	/**
	 * Find the nullable rules in the rule set (currently unused)
	 * @return the set of all nullable rules
	 */
	@SuppressWarnings("unused")
	private HashSet<Rule> findNullableRules(){
		HashSet<Rule> nullableRules = new HashSet<Rule>();
		for(Rule r : rules){
			if(r.isFinished() || findNullableHelper(r, new HashSet<Rule>())){
				nullableRules.add(r);
			}
		}
		return nullableRules;

	}

	/**
	 * Helper method to determine if a specific rule is nullable
	 * @param rule the rule in question
	 * @param lookedAt the set of rules that have already been tested 
	 * @return whether or not the rule is nullable
	 */
	private boolean findNullableHelper(Rule rule, HashSet<Rule> lookedAt){
		lookedAt.add(rule);

		for(String nt : rule.afterDot){
			if(allNonTerminals.contains(nt)){
				for(Rule r : rules){
					if((!lookedAt.contains(r) && r.reduceTo.equals(nt)) && (r.isFinished() || findNullableHelper(r, lookedAt))){
						return true;
					}
					else{
						break;
					}
				}
			}
		}
		return false;
	}


	/**
	 * Find the set of terminals that could begin a derivation of the specified non terminal
	 * @param nt the non terminal in question
	 * @param lookedAt the set of non terminals that have already been tested
	 * @return
	 */
	private HashSet<String> findFirst(String nt, Set<String> lookedAt){
		HashSet<String> first = new HashSet<String>();
		for(Rule r : rules){
			if(r.reduceTo.equals(nt)){
				if(r.isFinished()){
					//first.add("Epsilon");
				}
				else if(!allNonTerminals.contains(r.afterDot.get(0))){
					first.add(r.afterDot.get(0));
				}
				else if(!lookedAt.contains(r.afterDot.get(0))){
					lookedAt.add(r.afterDot.get(0));
					first.addAll(findFirst(r.afterDot.get(0), lookedAt));
				}
			}
		}
		return first;
	}

	/**
	 * Create the output file
	 * @param filename the name of the output file
	 * @throws IOException if the output file could not be created
	 */
	public void createTable(String filename) throws IOException{
		Set<String> terminals = new LinkedHashSet<String>();
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		int resultSize = 0;


		for(Rule rule : rules){
			terminals.addAll(rule.afterDot.stream().
					filter(t -> !allNonTerminals.contains(t)).
					collect(Collectors.toList()));
		}

		for(String s : terminals){
			result.put(s, resultSize++);
		}
		for(String s : allNonTerminals){
			result.put(s, resultSize++);
		}

		FileWriter writer = new FileWriter(new File(filename));
		for(String s : terminals){
			if(s.equals(",")){
				writer.write("\"comma\"" + ",");
			}
			else{
				writer.write(s + ",");
			}

		}
		for(int i = 0; i < allNonTerminals.size() - 1; i++){
			writer.write(allNonTerminals.get(i) + ",");
		}
		writer.write(allNonTerminals.get(allNonTerminals.size() - 1) + "\n");

		String[] output; 
		for(State state : states){
			output = new String[resultSize];
			for(Rule rule : state.rules){
				if (rule.isFinished()){
					for(String folNT : folSet.get(rule.reduceTo)){
						output[result.get(folNT)] = "r" + rule.ruleNumber ;
					}
				}
				else if(rule.afterDot.get(0).equals("$")){
					output[result.get("$")] = "a";
				}
			}

			for(String s : state.transitions.keySet()){
				if(!allNonTerminals.contains(s)){
					output[result.get(s)] = "s" + state.transitions.get(s).stateNumber;
				}
				else{
					output[result.get(s)] = "g" + state.transitions.get(s).stateNumber;
				}
			}

			for(int i = 0; i < output.length - 1; i++){
				writer.write(output[i] == null ? "," : output[i] + ",");
			}
			writer.write(output[output.length - 1] == null ? "\n" : output[output.length - 1] + "\n");

		}

		writer.close();
	}
}
