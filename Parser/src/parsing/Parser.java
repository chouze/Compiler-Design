package parsing;

import lexing.lexer.*;
import lexing.node.Token;
import java.io.*;

/**
 * 
 * @author Christopher Houze, David Carlin, Clifford Black
 *
 */
public class Parser{
	Lexer lexer;
	Token token, lastToken, nextToken;
	
	/**
	 * constants representing the names of our tokens
	 */
	private static final String tand = "TAnd", tassign = "TAssign", tboolean = "TBoolean", tcase = "TCase", tclas = "TClas", tcolon = "TColon", 
			tcomma = "TComma", tcompare = "TCompare", tdefault = "TDefault", tdivide = "TDivide", tdo = "TDo", tdot = "TDot", 
			tdscomment = "TDoubleSlashComment", telse = "TElse", textends = "TExtends", tfalse = "TFalse", tfor = "TFor", tid = "TIdentifier",
			tif = "TIf", tint = "TInt", tintnum = "TIntNum", tleftbrace = "TLeftBrace", tleftbracket = "TLeftBracket", 
			tleftparen = "TLeftParen", tleftquote = "TLeftQuote",tlength = "TLength", tlessthan = "TLessThan", tmain = "TMain", 
			tminus = "TMinus", tmisc = "TMisc", tmod = "TMod", tmult = "TMult", tnew = "TNew", tnot = "TNot", tor = "TOr", tplus = "TPlus", 
			tprintln = "TPrintln", tpublic = "TPublic", treturn = "TReturn", trightbrace = "TRightBrace", trightbracket = "TRightBracket", 
			trightparen = "TRightParen", trightquote = "TRightQuote", tsemi = "TSemi", tspace = "TSpace", tstatic = "TStatic", 
			tstring = "TString", tswitch = "TSwitch", tthis = "TThis", ttrue = "TTrue", tvoid = "TVoid", twhile = "TWhile";

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

			Program();
			

		}catch(LexerException le) {System.err.println(le);}
		catch(IOException ioe) {System.err.println(ioe);}
	}

	/**
	 * Get the next token from the lexer.
	 * @param tokenType the name of the token that is expected.
	 * @return whether the next token matches the name of what was expected.
	 */
	boolean peek(String tokenType)
	{
		try{
			do{
				nextToken = lexer.next();
			}while (isToken(nextToken, tspace) || isToken(nextToken, tdscomment));
		}
		catch (LexerException | IOException e) {
			e.printStackTrace();
		}
		return nextToken.getClass().getName().equals("lexing.node." + tokenType);
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

	
	/**
	 * Check if the current working token matches the given token type.
	 * @param tokenType the name of the token we are looking for.
	 * @return whether the token names match.
	 */
	boolean isToken(String tokenType) {
		return token.getClass().getName().equals("lexing.node." + tokenType);
	}

	/**
	 * Check if the given token matches the given token type.
	 * @param t the token to check.
	 * @param tokenType the name of the token we are looking for.
	 * @return whether the token names match.
	 */
	boolean isToken(Token t, String tokenType){
		return t.getClass().getName().equals("lexing.node." + tokenType);
	}

	
	/**
	 * 
	 * 
	 * From this point on, the code should be a direct translation from our grammar
	 * 
	 * 
	 */
	
	
	void Program(){
		MainClass();
		while (isToken(tclas)) {
			ClassDecl();
		}
	}

	void MainClass() {
		eat(tclas);
		eat(tid);
		eat(tleftbrace);
		eat(tpublic);
		eat(tstatic);
		eat(tvoid);
		eat(tmain);
		eat(tleftparen);
		eat(tstring);
		eat(tleftbracket);
		eat(trightbracket);
		eat(tid);
		eat(trightparen);
		eat(tleftbrace);
		while (isToken(tint) || isToken(tboolean) || isToken(tid)) {
			VarDecl();
		}
		Statement();
		eat(trightbrace);
		eat(trightbrace);
	}

	void ClassDecl() {
		ClassDeclSpec();
		ClassDeclDef();
	}

	void ClassDeclSpec() {
		eat(tclas);
		eat(tid);
	}

	void ClassDeclDef() {
		if (isToken(tleftbrace)) {
			eat(tleftbrace);

			while (isToken(tint) || isToken(tboolean) || isToken(tid)) {
				VarDecl();
			}
			while (isToken(tpublic)) {
				MethodDecl();
			}

			eat(trightbrace);
		}

		else {
			eat(textends);
			eat(tid);
			eat(tleftbrace);

			while (isToken(tint) || isToken(tboolean) || isToken(tid)) {
				VarDecl();
			}
			while (eat(tpublic)) {

				MethodDecl();
			}

			eat(trightbrace);
		}
	}

	void VarDecl() {
		Type();
		VarDeclType();
		eat(tsemi);
	}

	void VarDeclType() {
		eat(tid);
		VarDeclTypeAssign();
	}

	void VarDeclTypeAssign() {
		if (isToken(tassign)){
			eat(tassign);
			Exp();
		}

		if (isToken(tcomma)){
			MultiDecl();
		}
	}

	void MultiDecl() {
		eat(tcomma);
		eat(tid);
		VarDeclTypeAssign();
	}

	void MethodDecl() {
		eat(tpublic);
		Type();
		eat(tid);
		eat(tleftparen);
		FormalList();
		eat(trightparen);
		eat(tleftbrace);
		while (isToken(tint) || isToken(tboolean) || (isToken(tid) && peek(tid))){
			VarDecl();
		}


		while (isToken(tleftbrace) || isToken(tif) || isToken(tdo) || isToken(twhile) || isToken(tfor)
				|| isToken(tswitch) || isToken(tprintln) || isToken(tid)
				|| isToken(tleftparen))
			Statement();
		eat(treturn);
		Exp();
		eat(tsemi);
		eat(trightbrace);

	}

	void FormalList() {
		if (isToken(tint) || isToken(tboolean) || isToken(tid)) {
			Type();
			eat(tid);
			while (isToken(tcomma))
				FormalRest();
		}
	}

	void FormalRest() {
		eat(tcomma);
		Type();
		eat(tid);
	}

	void Type() {
		if (isToken(tint)) {
			eat(tint);
			IntType();
		} else if (isToken(tboolean))
			eat(tboolean);
		else if (isToken(tid))
			eat(tid);
		else
			throw new ParsingException(token);
	}

	void IntType() {
		if (isToken(tleftparen)) {
			eat(tleftbracket);
			eat(trightbracket);
		}
	}

	void Statement() {
		if (isToken(tleftbrace)) { // {Statement*}
			eat(tleftbrace);
			while (isToken(tleftbrace) || isToken(tif) || isToken(tdo) || isToken(twhile) || isToken(tfor)
					|| isToken(tswitch) || isToken(tprintln) || isToken(tid)
					|| isToken(tleftparen))
				Statement();
			eat(trightbrace);
		}
		else if (isToken(tif)) {
			eat(tif);
			eat(tleftparen);
			Exp();
			eat(trightparen);
			Statement();

			while (isToken(telse)) { 
				ElseIf();

			}

		} else if (isToken(tdo)) {
			eat(tdo);
			eat(tleftbrace);
			Statement();
			eat(trightbrace);
			eat(twhile);
			eat(tleftparen);
			Exp();
			eat(trightparen);
			eat(tsemi);

		} else if (isToken(twhile)) {
			eat(twhile);
			eat(tleftparen);
			Exp();
			eat(trightparen);
			Statement();
		} else if (isToken(tfor)) {
			eat(tfor);
			eat(tleftparen);
			InitializationStm();
			eat(tsemi);
			Exp();
			eat(tsemi);
			IncrementStm();
			eat(trightparen);
			Statement();
		} else if (isToken(tswitch)) {
			eat(tswitch);
			eat(tleftparen);
			eat(tid);
			eat(trightparen);
			eat(tleftbrace);
			CaseList();
			eat(trightbrace);
		} else if (isToken(tprintln)) {
			eat(tprintln);
			eat(tleftparen);
			if(!isToken(trightparen))
			{
				Exp();
			}
			eat(trightparen);
			eat(tsemi);

		} else if (isToken(tid)) {
			eat(tid);
			Assign();
			eat(tsemi);
		} else if (isToken(tleftparen)) {
			eat(tleftparen);
			Type();
			eat(tid);
			eat(tassign);
			Exp();
			eat(trightparen);
			while (isToken(tcomma))
				FormalVarExp();
			eat(tsemi);

		} 
		else if(isToken(trightbrace)){
			//Don't do anything, don't throw an exception
		}
		else{
			throw new ParsingException(token, "statment");
		}


	}

	void ElseIf() {
		eat(telse);
		eat(tleftparen);
		Exp();
		eat(trightparen);
		Statement();
	}

	void Else() {
		Statement();
	}

	void Assign() {
		if (isToken(tassign)) {
			eat(tassign);
			Exp();
		} else if (isToken(tleftbracket)) {
			eat(tleftbracket);
			Exp();
			eat(trightbracket);
			eat(tassign);
			Exp();
		} else
			throw new ParsingException(token, "assign");
	}

	void InitializationStm()
	{

		if(isToken(tid)) 
		{ 
			if(peek(tleftbracket))
			{
				eat(tid);
				eat(tleftbracket);
				Exp();
				eat(trightbracket);
				eat(tassign);
				Exp();
			}
			else
			{
				eat(tid);
				eat(tassign);
				Exp();
			}

		}
	}

	void IncrementStm() 
	{
		if(isToken(tid)) 
		{ 
			if(peek(tleftbracket))
			{
				eat(tid);
				eat(tleftbracket);
				Exp();
				eat(trightbracket);
				eat(tassign);
				Exp();
			}
			else
			{
				eat(tid);
				eat(tassign);
				Exp();
			}
		}

	}

	void FormalVarExp() {
		eat(tcomma);
		eat(tleftparen);
		Type();
		eat(tid);
		eat(tassign);
		Exp();
		eat(trightparen);
	}

	void CaseList() {
		if (isToken(tcase)) {
			eat(tcase);
			Exp();
			eat(tcolon);
			Statement();
			CaseList();
		} else if (isToken(tdefault)) {
			eat(tdefault);
			eat(tcolon);
			Statement();
		}
	}

	void Exp() {
		And();
		Elist();
	}

	void Elist() {
		if (isToken(tand)) {
			eat(tand);
			And();
			Elist();
		}
	}

	void And() {
		Less();
		Alist();
	}

	void Alist() {
		if (isToken(tlessthan)) {
			eat(tlessthan);
			Less();
			Alist();
		}
	}

	void Less() {
		Term();
		Llist();
	}

	void Llist() {
		if (isToken(tplus)) {
			eat(tplus);
			Term();
			Llist();
		} else if (isToken(tminus)) {
			eat(tminus);
			Term();
			Llist();
		}
	}

	void Term() {
		Not();
		Tlist();
	}

	void Tlist() {
		if (isToken(tmult)) {
			eat(tmult);
			Not();
			Tlist();
		}
	}

	void Not() {
		if (isToken(tnot)) {
			eat(tnot);
			Not();
		}
		else
		{
			Factor();
			while (isToken(tdot) || isToken(tleftbracket)){
				DotArray();
			}


		}
	}

	void DotArray() {
		if (isToken(tdot)) {
			eat(tdot);
			Member();
			
		} else if (isToken(tleftbracket)) {
			eat(tleftbracket);
			Exp();
			eat(trightbracket);
		}
		
	}

	void Member() {
		if (isToken(tlength)) {
			eat(tlength);
		} else if (isToken(tid)) {
			eat(tid);
			eat(tleftparen);
			ExpList();
			eat(trightparen);
		}
	}

	void ExpList() {
		if (isToken(tintnum) || isToken(ttrue) || isToken(tfalse) || isToken(tid) || isToken(tthis)
				|| isToken(tnew) || isToken(tleftparen) || isToken(tnot)) {
			Exp();
			while (isToken(tcomma)) {
				ExpRest();
			}
		}
	}

	void ExpRest() {
		eat(tcomma);
		Exp();
	}

	void Factor() {
		if (isToken(tintnum))
			eat(tintnum);
		else if (isToken(ttrue))
			eat(ttrue);
		else if (isToken(tfalse))
			eat(tfalse);
		else if (isToken(tid))
			eat(tid);
		else if (isToken(tthis))
			eat(tthis);
		else if (isToken(tnew)) {
			eat(tnew);
			New();
		} else if (isToken(tleftparen)) {
			eat(tleftparen);
			Exp();
			eat(trightparen);
		}
		else
		{
			throw new ParsingException(token, "factor");
		}
	}

	void New() {
		if (isToken(tint)) {
			eat(tint);
			eat(tleftbracket);
			Exp();
			eat(trightbracket);
		} else if (isToken(tid)) {
			eat(tid);
			eat(tleftparen);
			eat(trightparen);
		}
	}
}
