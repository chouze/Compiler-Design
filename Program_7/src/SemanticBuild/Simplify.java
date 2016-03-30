package SemanticBuild;


/* 
 * A Simplify Visitor will simplify an Expression
 * @author ()
 * @version ()
 */
public class Simplify implements Visitor{

	public Exp visit(Sum n) {
		return new Sum((Exp)n.left.accept(this), (Exp)n.right.accept(this)) ;
	}

	public Exp visit(Difference n) {
		Equals eq = new Equals();
		Exp result =  new Difference((Exp)n.left.accept(this), (Exp)n.right.accept(this));
		
		if( (Boolean)eq.visit(result.left,result.right) )
			return new Constant(0);
		return result;
	}

	public Exp visit(Constant n) {
		return n;
	}
}
