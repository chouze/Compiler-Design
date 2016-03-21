import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

/**
 * 
 * @author Christopher Houze, Clifford Black, David Carlin
 *
 */
public class Parser {

	Map<Integer, TableState> stateMap;
	Map<Integer, TableRule> ruleMap;
	Stack<String> inputStack;
	Stack<Integer> previousStates;
	Queue<SimpleToken> inputQueue;
	TableState currentState;
	TableState previousState;
	SimpleToken currentToken;
	ArrayList<String> acceptableTokens;
	String[] inputArr;
	String tableName, grammarName;

	int startStateNum = 1;
	int startRuleNum = 0;

	/**
	 * Constructor
	 * @param tableName the name of the file containing the parsing table
	 * @param grammarName the name of the file specifying the grammar
	 */
	public Parser(String tableName, String grammarName) {
		this.tableName = tableName;
		this.grammarName = grammarName;
		// Prework
		try {
			makeMap();
			readRules();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}


	/**
	 * Start the parsing process
	 */
	public void parseInput() {
		inputStack = new Stack<String>();
		inputQueue = new LinkedList<SimpleToken>();
		previousStates = new Stack<Integer>();
		boolean validInput = true;

		currentState = stateMap.get(startStateNum); // Retrieve the first state from the map
		previousStates.push(startStateNum);

		Scanner scanner = new Scanner(System.in);
		String temp = scanner.nextLine();
		scanner.close(); 

		inputArr = temp.split("\\s+");


		if(validInput){
			scanner = new Scanner(temp);
			String tempString;
			SimpleToken tempToken;

			while(scanner.hasNext())
			{
				tempString = scanner.next();

				if(!acceptableTokens.contains(tempString)){
					if(tempString.matches("[-+]?(\\d*[.])?\\d+")){
						System.out.println("Changing: " + tempString + " to number");
						tempToken = new SimpleToken("number", tempString);
					}
					else {
						System.out.println("Changing: " + tempString + " to id");
						tempToken = new SimpleToken("id", tempString);
					}
				}
				else{
					tempToken = new SimpleToken(tempString);
				}
				inputQueue.add(tempToken);
			}

			scanner.close();


			String action = "";

			while (!inputQueue.isEmpty()) {

				currentToken = inputQueue.peek();

				// Retrieve the action for this token, based on the current state
				action = currentState.getAction(currentToken.tokenName); 
				int prev = previousStates.pop();

				//Diagnostic print statement
				System.out.println("Current state is: " + prev +  " action on token: " + currentToken.tokenName + " value " + currentToken.tokenValue + " is " + action);

				//Error Message
				if(action.length() < 1){
					try{
						//Allow the system to finished outputting any information before sending the error message
						Thread.sleep(10);
					}
					catch(InterruptedException e){
						//Do nothing
					}
					throw new ParsingException("Table does not contain information for the input: " 
							+ (currentToken.tokenName.equals(currentToken.tokenValue) ? currentToken.tokenValue : (currentToken.tokenName + ": " + currentToken.tokenValue))
							+ " \nat state: " + prev);
				}

				previousStates.push(prev);

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

	
	/**
	 * Read the parsing table and create a map to easily reference
	 * @throws FileNotFoundException if the parsing table couldn't be found
	 */
	public void makeMap() throws FileNotFoundException {
		stateMap = new HashMap<Integer, TableState>();

		Scanner scanner = new Scanner(new File(tableName));
		String header = scanner.nextLine();
		String[] headerArr = header.split(",");

		//Because the file is CSV, a comma character cannot be stored as a comma
		for(int i = 0; i < headerArr.length; i++){
			if(headerArr[i].equals("\"comma\"")){
				headerArr[i] = ",";
			}
		}

		acceptableTokens = new ArrayList<String>(Arrays.asList(headerArr));

		int stateNum = startStateNum;
		while (scanner.hasNextLine()) {
			stateMap.put(stateNum, new TableState(headerArr, scanner.nextLine()));
			stateNum++;
		}
		scanner.close();
	}

	
	/**
	 * Read the rules from the grammar file
	 * @throws FileNotFoundException if the grammar file couldn't be found
	 */
	public void readRules() throws FileNotFoundException {
		// map of rules using rulenumber as the key
		ruleMap = new HashMap<Integer, TableRule>(); 

		Scanner scanner = new Scanner(new File(grammarName));
		Scanner lineScanner;

		String LSide;
		Stack<String> RSide = new Stack<String>();
		int ruleNum = startRuleNum;

		while (scanner.hasNextLine()) {
			lineScanner = new Scanner(scanner.nextLine());
			LSide = lineScanner.next(); // first token
			lineScanner.next(); // Eat the ->
			RSide = new Stack<String>();
			
			// scans entire right side and adds each item to a stack for later use
			while (lineScanner.hasNext()) { 
				RSide.push(lineScanner.next());
			}
			
			// temporary rule created from the left token and stack of the right tokens
			TableRule rule = new TableRule(LSide, RSide); 
			
			// puts the created rule into a map, using the rule number as it's key
			ruleMap.put(ruleNum, rule); 
			ruleNum++;
		}
		scanner.close();

	}

	/**
	 * Performs the goto action
	 * @param state the new state
	 */
	public void goTo(int state) {
		previousState = currentState;
		currentState = stateMap.get(state);
		previousStates.push(state);
	}

	/**
	 * Performs the shift action
	 * @param state the new state
	 */
	public void shift(int state) {
		// push the next string and shift states
		inputStack.push(inputQueue.remove().tokenName);
		System.out.println(inputStack);
		previousState = currentState;
		currentState = stateMap.get(state);
		previousStates.push(state);
	}

	/**
	 * Performs the reduce action
	 * @param ruleNumber the rule to reduce on
	 */
	public void reduce(int ruleNumber) {
		// Pop then replace
		replaceStackForReduce(ruleNumber);
		
		// Change state to previous one
		currentState = stateMap.get(previousStates.peek());
		
		//Diagnostic print statement
		System.out.println(inputStack);
		
		// Look at stack for NT to do a GoTo rather than input
		String action = currentState.getAction(inputStack.peek()); 

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

	/**
	 * Reduces the tokens on the stack using the specified rule
	 * @param ruleNumber the rule to reduce on
	 */
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

		// after the rule has been removed from the input stack, replace it with the reduced form
		inputStack.push(rule.getLSide());

	}

	
	/**
	 * Accept the input
	 */
	public void accept() {
		inputStack.push(inputQueue.remove().tokenName);
		System.out.println("No syntax errors detected");
	}
}
