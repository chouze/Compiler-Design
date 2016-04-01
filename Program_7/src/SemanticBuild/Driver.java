package semanticBuild;

public class Driver {

	public static void main(String[] args) 
	{
		Exp a = new Variable("a");
		Exp assign = new Assign(a, new Constant(17));
		Visitor intrp = new Eval();
		
		
		try { intrp.visit(a); }
		catch (Exception e)
		{
			System.err.println(e);
		}
		
		intrp.visit(assign);
		System.out.println("assign visited");
		
		intrp.visit(a);
		System.out.println("a visited");
		
		
		Exp b = new Variable("b");		
		Exp e = new Quotient(a, new Sum(b, new Constant(2)));
		Visitor toStr = new ToString();
		System.out.println(toStr.visit(e));
		
	}

}
