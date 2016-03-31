package symbolTableBuilder;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PushbackReader;

import parsing.ParsingException;
import lexing.lexer.Lexer;
import lexing.lexer.LexerException;
import lexing.node.Token;


public class Parser 
{
	private static final String tand = "TAnd", tassign = "TAssign", tboolean = "TBoolean", tcase = "TCase", tclas = "TClas", tcolon = "TColon", 
			tcomma = "TComma", tcompare = "TCompare", tdefault = "TDefault", tdivide = "TDivide", tdo = "TDo", tdot = "TDot", 
			tdscomment = "TDoubleSlashComment", telse = "TElse", textends = "TExtends", tfalse = "TFalse", tfor = "TFor", tid = "TIdentifier",
			tif = "TIf", tint = "TInt", tintnum = "TIntNum", tleftbrace = "TLeftBrace", tleftbracket = "TLeftBracket", 
			tleftparen = "TLeftParen", tleftquote = "TLeftQuote",tlength = "TLength", tlessthan = "TLessThan", tmain = "TMain", 
			tminus = "TMinus", tmisc = "TMisc", tmod = "TMod", tmult = "TMult", tnew = "TNew", tnot = "TNot", tor = "TOr", tplus = "TPlus", 
			tprintln = "TPrintln", tpublic = "TPublic", treturn = "TReturn", trightbrace = "TRightBrace", trightbracket = "TRightBracket", 
			trightparen = "TRightParen", trightquote = "TRightQuote", tsemi = "TSemi", tspace = "TSpace", tstatic = "TStatic", 
			tstring = "TString", tswitch = "TSwitch", tthis = "TThis", ttrue = "TTrue", tvoid = "TVoid", twhile = "TWhile";
	
	Lexer lexer;
	Token token, lastToken, nextToken;
	
	/**
	 * Constructor 
	 */
	public Parser()
	{
		lexer = new Lexer(new PushbackReader(new InputStreamReader(System.in), 1024));
		token = null;
		try{
			do{
				token = lexer.next();
			}while (isToken(tspace) || isToken(tdscomment));

			program();
			

		}catch(LexerException le) {System.err.println(le);}
		catch(IOException ioe) {System.err.println(ioe);}
	}
	
	/**
	 * Consume the token if it matches the given name, and get the next token from the lexer.
	 * If the token does not match, throw an exception.
	 * @param name the name of the token to consume.
	 * @return true of the token was successfully consumed.
	 */
	boolean eat(String name) {

		try {
			if (isToken(name)) {
				System.out.println("Eat token: " + token.getClass().getName() + " " + token.toString());
				if (nextToken != null){
					lastToken = token;
					token = nextToken;
					nextToken = null;
					return true;
				}
				else{
					do{
						token = lexer.next();

					}while (isToken(tspace) || isToken(tdscomment));
					return true;
				}

			} else {
				throw new ParsingException(token, name);
			}

		} catch (LexerException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}
	
	boolean isToken(String tokenType) 
	{
		return token.getClass().getName().equals("lexing.node." + tokenType);
	}
	
	//find example of Program
	public Program program()
	{
		MainClass mainclass = mainClass();
		ClassDeclList otherClasses = new ClassDeclList();
		while(isToken(tclas))
		{
			otherClasses.add(ClassDecl());
		}
		
		if(token != null)
		{
			error(tclas);
		}
		
	}
	
}
