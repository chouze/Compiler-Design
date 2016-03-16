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

public class TableCreator {
	ArrayList<String> allNonTerminals;
	HashMap <String, HashSet<String>> folSet;
	Scanner sc;
	File file;
	ArrayList<Rule> rules;
	LinkedHashSet<State> states;

	public TableCreator(String filename) throws FileNotFoundException{
		file = new File(filename);
		sc = new Scanner(new FileReader(file));
		rules = new ArrayList<Rule>();
		allNonTerminals = new ArrayList<String> (100);
		states = new LinkedHashSet<State> (200);
		folSet = new HashMap<String, HashSet<String>>();
	}

	public void scanForNTs(){
		Scanner lineScanner;
		while (sc.hasNext()){
			lineScanner = new Scanner(sc.nextLine());
			String temp = lineScanner.next();
			if(!allNonTerminals.contains(temp)){
				allNonTerminals.add(temp);
			}
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

		for(String nt : allNonTerminals){
			fol = findFolHelp(nt, new HashSet<String>());
			folSet.put(nt, fol);
		}
	}

	private HashSet<String> findFolHelp(String nt, Set<String> lookedAt){
		HashSet<String> fol = new HashSet<String>();
		lookedAt.add(nt);
		for(Rule r : rules){
			if(r.afterDot.contains(nt)){
				for(int i = 0; i < r.afterDot.size() - 1; i++){
					if(nt.equals(r.afterDot.get(i))){
						if(allNonTerminals.contains(r.afterDot.get(i+1)) && !lookedAt.contains(r.afterDot.get(i + 1))){
							fol.addAll(findFolHelp(r.afterDot.get(i+1), lookedAt));
						}
						else{
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
			output = new String[resultSize + 1];
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
			
			for(int i = 0; i < output.length -1; i++){
					writer.write(output[i] == null ? "," : output[i] + ",");
				}
				writer.write(output[output.length - 1] == null ? "\n" : output[output.length - 1] + "\n");
			
		}


		writer.close();
	}
}
