package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class MethodDecl
{
	Type type;
	Identifier methodName;
	FormalList parameters;
	VarDeclList variables;
	Statement statement;
	Exp expReturn;
	SymbolTable symTab;
	
	public MethodDecl(Type type, Identifier methodName,
			FormalList parameters, VarDeclList variables, Statement statements,
			Exp ex) 
	{
		this.type = type;
		this.methodName = methodName;
		this.parameters = parameters;
		this.variables = variables;
		this.statement = statements;
		this.expReturn = ex;
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
