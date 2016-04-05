package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class ClassDeclDeffSimple extends ClassDecl 
{
	Identifier className;
	VarDeclList fields;
	MethodDeclList methods;
	SymbolTable symTab;
	
	public ClassDeclDeffSimple(Identifier id, VarDeclList variableList, MethodDeclList methodList) 
	{
		this.className = id;
		this.fields = variableList;
		this.methods = methodList;
	}
	
	@Override
	public void accept(Visitor v) 
	{
		v.visit(this);
	}
}
