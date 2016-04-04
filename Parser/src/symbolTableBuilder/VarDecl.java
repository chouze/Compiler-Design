package symbolTableBuilder;

public class VarDecl extends java.util.LinkedList<VarDecl>
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
