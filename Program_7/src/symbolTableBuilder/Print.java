package symbolTableBuilder;

public class Print extends Statement
{
	Exp statementToPrint;

	public Print(Exp statementToPrint) 
	{
		this.statementToPrint = statementToPrint;
	}
}
