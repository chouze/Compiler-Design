package semanticBuild;

/*
* An Eval will evaluate an Expression
*
* @author()
* @version()
*/

public class Eval implements Visitor{

	public Integer visit(Sum n) {
		return (Integer)n.left.accept(this) + (Integer)n.right.accept(this);
	}

	public Integer visit(Difference n) {
		return (Integer)n.left.accept(this) - (Integer)n.right.accept(this);
	}

	public Integer visit(Constant n) {
		return n.value;
	}
	

	
	
}
