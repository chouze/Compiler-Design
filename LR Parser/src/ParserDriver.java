import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class ParserDriver {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		//Parser parser = new Parser();
		ArrayList<TableState> states = new ArrayList<TableState>();
		
		Scanner scanner = new Scanner(new File("Grammar3_1_Output.csv"));
		String header = scanner.nextLine();
		System.out.println(header);
		while (scanner.hasNextLine()) {
			//System.out.println(scanner.nextLine());
			states.add(new TableState(header, scanner.nextLine()));
		}
		scanner.close();
		//for(TableState state: states){
		//	System.out.println(state);
		//}
	}

}
