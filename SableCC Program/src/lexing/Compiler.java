package lexing;
import lexing.lexer.*;
import lexing.node.*;
import lexing.parser.*;
import lexing.analysis.*;
import java.io.*;

class Compiler
{
	static Lexer lexer;
	static Object token;
	
	public static void main (String[] args)
	{
	
		try{
				Parser p = new Parser(new Lexer (new PushbackReader(new InputStreamReader(System.in), 1024)));
				
				Start tree = p.parse();
				tree.apply(new Translation());
			}
			
		catch (ParserException pe)
		{ System.err.println(pe); }
		catch (LexerException le)
		{ System.err.println(le); }
		catch (IOException ioe)
		{ System.err.println(ioe);}
		
	}
}

