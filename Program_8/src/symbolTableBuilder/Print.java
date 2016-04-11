package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class Print extends Statement
{
	Exp statementToPrint;

	public Print(Exp statementToPrint) 
	{
		this.statementToPrint = statementToPrint;
	}
	
	public String accept(Visitor v) 
	{
		return v.visit(this);
	}
}
