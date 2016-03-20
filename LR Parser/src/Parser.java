import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PushbackReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Parser {

	Map<Integer, TableState> stateMap;
	Map<Integer, TableRule> ruleMap;
	Stack<String> inputStack;
	Stack<Integer> previousStates;
	Queue<String> inputQueue;
	TableState currentState;
	TableState previousState;
	String currentToken;
	ArrayList<String> acceptableTokens;
	String[] inputArr;

	int startStateNum = 1;
	int startRuleNum = 0;

	public Parser() {
		// Prework
		try {
			makeMap();
			readRules();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Heavy lifting
		parseInput();
	}

	public void parseInput() {
		inputStack = new Stack<String>();
		inputQueue = new LinkedList<String>();
		previousStates = new Stack<Integer>();
		boolean validInput = true;

		currentState = stateMap.get(startStateNum); // Retrieve the first state from the map
		previousStates.push(startStateNum);

		Scanner scanner = new Scanner(System.in);
		String temp = scanner.nextLine();
		scanner.close(); //this seems convoluted but its the only way I could think of reading in one line of code
		
		inputArr = temp.split("\\s+");
	
		
		for(String s: inputArr)
		{
			if(acceptableTokens.indexOf(s) < 0)
			{
				validInput = false;
				System.err.println("Input is faulty");
			}
				
		}
		
		if(validInput){
		scanner = new Scanner(temp);
		//I'll need to check for acceptable tokens but not right now, assume all input is correct
		while(scanner.hasNext())
		{
			inputQueue.add(scanner.next());
		}
		
		scanner.close();
		
		currentToken = "";
		String action = "";

		while (!inputQueue.isEmpty()) {
			
			currentToken = inputQueue.peek();
			
			action = currentState.getAction(currentToken); // Retrieve the
															// action for this
															// token, based on
															// the current state

			char firstChar = action.charAt(0);
			int stateOrRuleNum = 0;

			if (action.length() > 1) {
				String TESTTEMP = action.substring(1, action.length());
				stateOrRuleNum = Integer.parseInt(TESTTEMP);
			}

			if (firstChar == 'a')
				accept();
			else if (firstChar == 's')
				shift(stateOrRuleNum);
			else if (firstChar == 'r')
				reduce(stateOrRuleNum);
			else{
				System.err.println("Could not find applicable action indicator. Found: " + firstChar);
				break;
			}
		}
		}
		
		
	}

	public void makeMap() throws FileNotFoundException {
		stateMap = new HashMap<Integer, TableState>();

		Scanner scanner = new Scanner(new File("Grammar3_1_Output.csv"));
		String header = scanner.nextLine();
		String[] headerArr = header.split(",");
		
		acceptableTokens = new ArrayList<String>(Arrays.asList(headerArr));

		// System.out.println(header);

		int stateNum = startStateNum;
		while (scanner.hasNextLine()) {
			stateMap.put(stateNum, new TableState(headerArr, scanner.nextLine()));
			stateNum++;
		}
		scanner.close();
	}

	public void readRules() throws FileNotFoundException {
		ruleMap = new HashMap<Integer, TableRule>(); // map of rules using
														// rulenumber as the key

		Scanner scanner = new Scanner(new File("Grammar3_1.txt"));
		Scanner lineScanner;

		String LSide;
		Stack<String> RSide = new Stack<String>();
		int ruleNum = startRuleNum;

		while (scanner.hasNextLine()) {
			lineScanner = new Scanner(scanner.nextLine());
			LSide = lineScanner.next(); // first token
			lineScanner.next(); // Eat the ->
			RSide = new Stack<String>();
			while (lineScanner.hasNext()) { // scans entire right side and adds
											// each item to a stack for later
											// use
				RSide.push(lineScanner.next());
			}

			TableRule rule = new TableRule(LSide, RSide); // temporary rule
															// created from the
															// left token and
															// stack of the
															// right tokens
			ruleMap.put(ruleNum, rule); // puts the created rule into a map,
										// using the rule number as it's key
			ruleNum++;
		}
		scanner.close();

	}

	public void goTo(int state) {
		// not sure how to do this one yet
		previousState = currentState;
		currentState = stateMap.get(state);
		previousStates.push(state);
	}

	public void shift(int state) {
		// push the next string and shift states
		inputStack.push(inputQueue.remove());
		System.out.println(inputStack);
		previousState = currentState;
		currentState = stateMap.get(state);
		previousStates.push(state);
	}

	public void reduce(int ruleNumber) {
		// need to have the rules for this in order to know what reduces to what
		// reduce stack by the rules

		replaceStackForReduce(ruleNumber);/// POP THEN REPLACE
		// currentState = previousState; /// CHANGE STATE TO THE PREVIOUS ONE
		currentState = stateMap.get(previousStates.peek());
		System.out.println(inputStack);
		String action = currentState.getAction(inputStack.peek()); // Look at
																	// stack for
																	// NT to do
																	// a GoTo
																	// rather
																	// than
																	// input

		char firstChar = action.charAt(0);
		int stateOrRuleNum = 0;

		if (action.length() > 1) {
			stateOrRuleNum = Integer.parseInt(action.substring(1, action.length()));
		} else
			System.err.println("Looking for state number in action. Found: " + action);

		if (firstChar == 'g')
			goTo(stateOrRuleNum);
		else
			System.err.println("Looking for GoTo action indicator. Found: " + firstChar);

	}

	private void replaceStackForReduce(int ruleNumber) {
		TableRule rule = ruleMap.get(ruleNumber);
		Stack<String> RSide = rule.getRSide();
		while (!RSide.isEmpty()) {
			String ruleSTR = RSide.pop();
			String stackSTR = inputStack.pop();
			if (!ruleSTR.equals(stackSTR))
				System.err.println("Error in stack reduce. Looked for: " + ruleSTR + ", found: " + stackSTR);
			previousStates.pop();
		}

		// after the rule has been removed from the input stack, replace it with
		// the reduced form
		inputStack.push(rule.getLSide());

	}

	public void accept() {
		inputStack.push(inputQueue.remove());
		System.out.println("S'all good homie");
	}
}
