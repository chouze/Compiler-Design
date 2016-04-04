package symbolTableBuilder;

public class Driver {

	public static void main(String[] args) {
		/*
		 class Test
		{
			public static void main ( String [] args )
			{
				//This is a comment
				int i = new AddNum().addNumber(10, 9);
			}
		}
		 */
		//Stuff for MC
		BuildST bst = new BuildST();
		//Identifier testMC = new Identifier("test");
		//Identifier argsMC = new Identifier("args");
		//VarDecl vMC = new VarDecl();
		//Statement sMC = new ;
				
		//Stuff for VD in MC
		
		//Stuff for Stm in MC	
		
		//MainClass Test = new MainClass()
		
		
		//////////////////////////////////////////////////////////
		//Create the integers to be added.
		IntegerLiteral i10 = new IntegerLiteral(10);
		IntegerLiteral i9 = new IntegerLiteral(9);
		//Create the Class Name.
		Identifier addNumClass = new Identifier("AddNum");
		//Create the Method Name.
		Identifier addNumberMethod = new Identifier("addNumber");
		//Create the ExpList needed as parameters for a Call.
		ExpRest expFollow = new ExpRest(i9);
		ExpList expList = new ExpList(i10, expFollow);
		NewObject addNumObj = new NewObject(addNumClass);
		//Create the Call.
		Call addNumberCall = new Call(addNumObj, addNumberMethod, expList);
		
		//Perform the highest order action: Call
		bst.visit(addNumberCall);//
		
		System.out.println(bst.symTabProg);
		

	}

}
