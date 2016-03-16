import java.io.FileNotFoundException;
import java.io.IOException;

public class Driver {

	public static void main(String[] args) {
		TableCreator test;
		try {
			test = new TableCreator(args[0]);
			test.scanForNTs();
			test.readRules();
			test.createStates();
			test.findFOLs();
			test.createTable(args[0].split("\\.")[0] + "_Output.csv");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
