package semanticBuild;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public interface Visitor 
{
	Object visit(Assign n);
	Object visit(Constant n);
	Object visit(Difference n);
	Object visit(Mod n);
	Object visit(Product n);
	Object visit(Quotient n);
	Object visit(Sum n);
	Object visit(Variable n);
	Object visit(Exp n);
}
