package semanticBuild;

public class Driver {

	public static void main(String[] args) {
		Exp e1 = new Constant(3);
		Exp e2 = new Sum(e1, new Constant(2));
		Eval evaluator = new Eval();
		
		Difference diff = new Difference(e2, new Constant(1));
		System.out.println(evaluator.visit(diff));

		Simplify simple = new Simplify();
		Exp e3 = simple.visit(diff);
		Exp e4 = new Sum(new Constant(2), new Constant(3));
		Equals eq = new Equals();
		System.out.println("3+2 == 2+3: " + eq.visit(e2, e4));
		
		diff = new Difference(e2, e4);
		Exp e5 = simple.visit(diff);
		System.out.println(e5);
		
	}

}
