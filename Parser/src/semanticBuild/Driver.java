package semanticBuild;

/**
 * @author David Carlin
 * @author Clifford Black
 * @author Christopher Houze
 * Version 3/31/2016
 *
 */

public class Driver {

	public static void main(String[] args) 
	{
		Exp e1, e2, e3, e4, e5;
		
		Exp a = new Variable("a");
		Exp assign = new Assign(a, new Constant(17));
		Visitor intrp = new Eval();
		Visitor simp = new Simplify();
		
		
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

		Exp c = new Product(new Quotient(a, new Sum(b, new Constant(2))), new Constant (3));
		Visitor toStr = new ToString();
		System.out.println(toStr.visit(c));
		
		Exp d = new Mod(new Constant(2), new Constant(2));
		Exp e = (Exp) simp.visit(d);
		System.out.println("Mod 2 % 2 Simplifies to " + toStr.visit(e));
		
		Exp f = new Sum(new Constant(2), new Constant(3));
		Exp g = new Sum(new Constant(3), new Constant(3));
		Exp h = new Difference(f, g);
		
		System.out.print(toStr.visit(h));
		System.out.print(" Evaluates to ");
		System.out.println(intrp.visit(h));
		
		f = new Sum(new Constant(2), new Constant(3));
		g = new Sum(new Constant(3), new Constant(2));
		h = new Difference(f, g);
		
		System.out.print(toStr.visit(h));
		System.out.print(" Simplifies to ");
		h = (Exp) simp.visit(h);
		System.out.println(toStr.visit(h));
		
		
		e1 = new Sum (new Constant(3),new Constant(5));        		// 3 + 5
	    e2 = new Difference (new Constant(4),new Constant(2));      // 4 - 2
	    e3 = new Product (e1, e2);    								// (3+5) * (4-2)
	    System.out.print(toStr.visit(e3));
		System.out.print(" Evaluates to ");
		System.out.println(intrp.visit(e3));     					// should be 16
		
		
		e1 = new Sum (a, new Constant(3));         	// a + 3
	    e3 = new Sum (new Constant(3), a);         	// 3 + a
	    e4 = new Difference (e3, e1);        		// (3+a) - (a+3)           								


	    System.out.print(toStr.visit(e4));
		System.out.print(" Simplifies to ");
		e4 = (Exp) simp.visit(e4);
		System.out.println(toStr.visit(e4));
		
		e1 = new Sum (a, new Constant(3));         	// a + 3
	    e3 = new Sum (new Constant(3), a);         	// 3 + a
	    e4 = new Quotient (e3, e1);        		// (3+a) / (a+3)           								


	    System.out.print(toStr.visit(e4));
		System.out.print(" Simplifies to ");
		e4 = (Exp) simp.visit(e4);
		System.out.println(toStr.visit(e4));

		System.out.print(toStr.visit(e4));
		System.out.print(" Evaluates to ");
		System.out.println(intrp.visit(e4));
		

	}

}
