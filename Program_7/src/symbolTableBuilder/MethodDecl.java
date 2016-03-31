package symbolTableBuilder;

public class MethodDecl extends java.util.LinkedList
{
	Type type;
	Identifier methodName;
	FormalList parameters;
	VarDecl variables;
	Statement statements;
	Exp expReturn;
	
	public MethodDecl(Type type, Identifier methodName,
			FormalList parameters, VarDecl variables, Statement statements,
			Exp ex) 
	{
		this.type = type;
		this.methodName = methodName;
		this.parameters = parameters;
		this.variables = variables;
		this.statements = statements;
		this.expReturn = ex;
	}
}
