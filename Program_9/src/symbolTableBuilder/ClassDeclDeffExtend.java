package symbolTableBuilder;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class ClassDeclDeffExtend extends ClassDecl 
{
	Identifier className;
	Identifier extendedClass;
	VarDeclList variableList;
	MethodDeclList methodList;
	SymbolTable symTab;
	
	public ClassDeclDeffExtend(Identifier className, Identifier extendedClass, VarDeclList variableList,
			MethodDeclList methodList) 
	{
		this.className = className;
		this.extendedClass = extendedClass;
		this.variableList = variableList;
		this.methodList = methodList;
	}
	
	@Override
	public Object accept(Visitor v) 
	{
		return v.visit(this);
	}
}
