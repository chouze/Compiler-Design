package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class VarDecl
{
	Type type;
	VarDeclType variableType;
	
	public VarDecl(Type type, VarDeclType variableType) 
	{
		this.type = type;
		this.variableType = variableType;
	}
	
	public VarDecl(){
		//for 0 occurrences
	}
	
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
