import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class TableCreator {
	static ArrayList<String> allNonTerminals;
	Scanner sc;
	File file;
	static ArrayList<Rule> rules;
	static ArrayList<State> states;

	public TableCreator(String filename) throws FileNotFoundException{
		file = new File(filename);
		sc = new Scanner(new FileReader(file));
		rules = new ArrayList<Rule>();
		allNonTerminals = new ArrayList<String> (100);
		states = new ArrayList<State> (200);
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
		State current = new State();
		current.add(rules.get(0)); //Add the first rule
		
		//System.out.println(current);
		
		current.expand();
		System.out.println(State.allStates);
	}
	
	
}
