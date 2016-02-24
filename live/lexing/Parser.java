package parsing;

import lexing.lexer.*;
import lexing.node.*;

import java.io.*;

class Parser
{
	static Lexer lexer;
	static Object token;
	public static void main(String[] args)
	{
		lexer = new Lexer(new PushbackReader(new InputStreamReader(System.in), 1024));
		token = null;
		try{
			while(!(token instanceof EOF))
			{
				token = lexer.next();
				System.out.println(token.getClass() + ": " + token);
			}
		}catch(LexerException le) {System.err.println(le);}
		 catch(IOException ioe) {System.err.println(ioe);}
	}
	
	boolean eat(boolean equals) 
	{
		if(equals)
		{
			try {
				token = lexer.next();
				return true;
			} catch (LexerException e) 
			{
				e.printStackTrace();
				return false;
			} catch (IOException e) 
			{
				e.printStackTrace();
				return false;
			}
		}
		else
		{
			System.err.println("Incorrect");
			return false;
		}
	}
	
	void Program()
	{
		MainClass();
		while(token.getClass().equals("class lexing.node.TClas"))
		{
			ClassDecl();
		}
	}
	
	void MainClass()
	{
		eat(token.getClass().equals("class lexing.node.TClas"));
		eat(token.getClass().equals("class lexing.node.TIdentifier"));
		eat(token.getClass().equals("class lexing.node.TLeftBrace"));
		eat(token.getClass().equals("class lexing.node.TPublic"));
		eat(token.getClass().equals("class lexing.node.TStatic"));
		eat(token.getClass().equals("class lexing.node.TVoid"));
		eat(token.getClass().equals("class lexing.node.TMain"));
		eat(token.getClass().equals("class lexing.node.TLeftParen"));
		eat(token.getClass().equals("class lexing.node.TString"));
		eat(token.getClass().equals("class lexing.node.TLeftBracket"));
		eat(token.getClass().equals("class lexing.node.TRightBracket"));
		eat(token.getClass().equals("class lexing.node.TIdentifier"));
		eat(token.getClass().equals("class lexing.node.TRightParen"));
		eat(token.getClass().equals("class lexing.node.TLeftbrace"));
		eat(token.getClass().equals("class lexing.node.TClas"));
		Statement();
		eat(token.getClass().equals("class lexing.node.TRightBrace"));
		eat(token.getClass().equals("class lexing.node.TRightBrace"));		
	}
	
	void ClassDecl()
	{
		ClassDeclSpec();
		ClassDeclDef();
	}
	
	void ClassDeclSpec()
	{
		eat(token.getClass().equals("class lexing.node.TClas"));
		eat(token.getClass().equals("class lexing.node.TIdentifier"));
	}

	void ClassDeclDef()
	{
		if(token.getClass().equals("class lexing.node.TLeftBrace"))
		{
			eat(token.getClass().equals("class lexing.node.TLeftBrace"));

			while(eat((token.getClass().equals("class lexing.node.TInt"))) || 
					eat((token.getClass().equals("class lexing.node.TBoolean"))) || 
					eat((token.getClass().equals("class lexing.node.TIdentifier"))))
			{
				VarDecl();
			}
			while(eat(token.getClass().equals("class lexing.node.TPublic")))
			{
				MethodDecl();
			}

			eat(token.getClass().equals("class lexing.node.TRightBrace"));
		}

		else
		{
			eat(token.getClass().equals("class lexing.node.TExtends"));
			eat(token.getClass().equals("class lexing.node.TIdentifier"));
			eat(token.getClass().equals("class lexing.node.TLeftBrace"));

			while(eat((token.getClass().equals("class lexing.node.TInt"))) || 
					eat((token.getClass().equals("class lexing.node.TBoolean"))) || 
					eat((token.getClass().equals("class lexing.node.TIdentifier"))))
			{
				VarDecl();
			}
			while(eat(token.getClass().equals("class lexing.node.TPublic")))
			{
				MethodDecl();
			}

			eat(token.getClass().equals("class lexing.node.TRightBrace"));
		}
	}

	
}
