import java.io.FileNotFoundException;

public class Driver {

	public static void main(String[] args) {
		TableCreator test;
		try {
			test = new TableCreator("Grammar.txt");
			test.scanForNTs();
			test.readRules();
			test.createStates();
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}

}
