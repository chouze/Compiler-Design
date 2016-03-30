package SemanticBuild;

public interface Visitor {
	Object visit(Sum n);
	Object visit(Difference n);
	Object visit(Constant n);
	//Object visit(Exp e1, Exp e2);
	
}
