package semanticBuild;

/* 
 * An Equals Visitor can compare two Expressions for equality.
 * @author ()
 * @version (Mar 2016)
 */
public class Equals implements Visitor {

	public Object visit(Sum n) {
		return visit(n.left, n.right);
	}

	public Object visit(Difference n) {
		return visit(n.left, n.right);
	}

	public Object visit(Constant n) {
		return n.value;
	}

	public Object visit(Exp e1, Exp e2) {
		if (!e1.getClass().equals(e2.getClass()))
			return false;

		if (e1 instanceof Sum)
			return (Boolean) visit(e1.left, e2.left) && (Boolean) visit(e1.right, e2.right)
					|| (Boolean) visit(e1.left, e2.right) && (Boolean) visit(e1.right, e2.left);

		if (e1 instanceof Difference)
			return (Boolean) visit(e1.left, e2.left) && (Boolean) visit(e1.right, e2.right);
		
		return ((Constant)e1).value.equals(((Constant)e2).value);
	}
	
	public Object visit(Assign n){
		return visit(n.left, n.right);
	}
	
	
}
