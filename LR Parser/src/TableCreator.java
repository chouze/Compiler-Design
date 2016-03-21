import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class TableCreator {
	static final String ruleSeparator = "->";
	ArrayList<String> allNonTerminals, allTerminals;
	HashMap <String, HashSet<String>> folSet;
	Scanner sc;
	File file;
	ArrayList<Rule> rules;
	LinkedHashSet<State> states;
	HashSet<Rule> nullableRules;

	public TableCreator(String filename) throws FileNotFoundException{
		file = new File(filename);
		sc = new Scanner(new FileReader(file));
		rules = new ArrayList<Rule>();
		allNonTerminals = new ArrayList<String> (100);
		allTerminals = new ArrayList<String> (100);
		states = new LinkedHashSet<State> (200);
		folSet = new HashMap<String, HashSet<String>>();
		
	}

	public void scanForNTs() throws FileNotFoundException{
		Scanner lineScanner;
		String temp;
		while (sc.hasNext()){
			lineScanner = new Scanner(sc.nextLine());
			temp = lineScanner.next();
			if(!allNonTerminals.contains(temp)){
				allNonTerminals.add(temp);
			}
		}
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

		for(String s : allNonTerminals){
			//	System.out.println(s);
		}

	}

	public void readRules() throws FileNotFoundException{
		Scanner lineScanner;
		sc = new Scanner(new FileReader(file));
		Rule r;
		while(sc.hasNext()){
			lineScanner = new Scanner(sc.nextLine());
			r = new Rule();
			String temp = lineScanner.next();
			if(allNonTerminals.contains(temp)){
				r.reduceTo = temp;
				lineScanner.next(); //Eat the ->
				r.beforeDot = new ArrayList<String>();
				r.afterDot = new ArrayList<String>();
				r.ruleNumber = Rule.ruleNumbers++;
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


	public void createStates(){
		states = new LinkedHashSet<State>();
		State current = new State(states, rules);
		current.add(rules.get(0)); //Add the first rule

		states = current.expand();
		//System.out.println(states);
	}


	public void findFOLs(){
		HashSet<String> fol = new HashSet<String>();
		nullableRules = findNullableRules();

		for(String nt : allNonTerminals){
			fol = findFolHelp(nt, new HashSet<String>());
			folSet.put(nt, fol);
		}
		//System.out.println(folSet.get("Type")/*findFirst("Statement", new HashSet<String>())*/ + "\n");
		//System.out.println(findFirst("Type", new HashSet<String>()));
	}

	private HashSet<String> findFolHelp(String nt, Set<String> lookedAt){
		HashSet<String> fol = new HashSet<String>();
		lookedAt.add(nt);
		for(Rule r : rules){
			if(r.afterDot.contains(nt)){
				for(int i = 0; i < r.afterDot.size() - 1; i++){
					if(nt.equals(r.afterDot.get(i))){
						if(allNonTerminals.contains(r.afterDot.get(i+1)) /*&& !lookedAt.contains(r.afterDot.get(i + 1))*/){
							if(!lookedAt.contains(r.afterDot.get(i + 1))){
								fol.addAll(findFolHelp(r.afterDot.get(i+1), lookedAt));
							}
							//Check for nullable first
							fol.addAll(findFirst(r.afterDot.get(i+1), new HashSet<String>()));
						}
						else if(!allNonTerminals.contains(r.afterDot.get(i+1))){
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
	 * Add nullable finder
	 */
	private HashSet<Rule> findNullableRules(){
		HashSet<Rule> nullableRules = new HashSet<Rule>();
		for(Rule r : rules){
			if(r.isFinished() || findNullableHelper(r, new HashSet<Rule>())){
				nullableRules.add(r);
			}
		}
		return nullableRules;

	}

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


	private HashSet<String> findFirst(String nt, Set<String> lookedAt){
		HashSet<String> first = new HashSet<String>();
		for(Rule r : rules){
			if(r.reduceTo.equals(nt) && !r.isFinished() ){
				if(!allNonTerminals.contains(r.afterDot.get(0))){
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
						if(output[result.get(folNT)] == null){
							output[result.get(folNT)] = "r" + rule.ruleNumber ;
						}
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
