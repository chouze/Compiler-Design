package symbolTableBuilder;

public class MethodDecl
{
	Type type;
	Identifier methodName;
	FormalList parameters;
	VarDeclList variables;
	StatementList statements;
	Exp expReturn;
	
	public MethodDecl(Type type, Identifier methodName,
			FormalList parameters, VarDeclList variables, StatementList statements,
			Exp ex) 
	{
		this.type = type;
		this.methodName = methodName;
		this.parameters = parameters;
		this.variables = variables;
		this.statements = statements;
		this.expReturn = ex;
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
