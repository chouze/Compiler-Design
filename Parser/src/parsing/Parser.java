/**
 * @author Christopher Houze, David Carlin, Clifford Black
 */
package parsing;

import lexing.lexer.*;
import lexing.node.Token;
import java.io.*;



/*
 * need to work on: 

		ADDED COMMENT HERE

 *  	Not --> Exp DotArray* 
 */
public class Parser{
	Lexer lexer;
	Token token, lastToken, nextToken;

	public Parser()
	{
		lexer = new Lexer(new PushbackReader(new InputStreamReader(System.in), 1024));
		token = null;
		try{
			do{
				token = lexer.next();
			}while (isToken("TSpace") || isToken("TDoubleSlashComment"));

			Program();

		}catch(LexerException le) {System.err.println(le);}
		catch(IOException ioe) {System.err.println(ioe);}
	}

	boolean peek(String tokenType)
	{
		try{
			do{
				nextToken = lexer.next();
			}while (isToken(nextToken, "TSpace") || isToken(nextToken, "TDoubleSlashComment"));
		}
		catch (LexerException | IOException e) {
			e.printStackTrace();
		}
		return nextToken.getClass().getName().equals("lexing.node." + tokenType);
	}

	void unEat(){
		if(lastToken != null){
			nextToken = token;
			token = lastToken;
			lastToken = null;
		}

	}

	boolean eat(String name) {

		try {
			if (isToken(name)) {
				System.out.println("Eat token: " + token.getClass().getName() + " " + token.toString() + " om nom nom");
				if (nextToken != null){
					lastToken = token;
					token = nextToken;
					nextToken = null;
					return true;
				}
				else{
					do{
						token = lexer.next();

					}while (isToken("TSpace") || isToken("TDoubleSlashComment"));
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


	boolean isToken(String tokenType) {
		return token.getClass().getName().equals("lexing.node." + tokenType);
	}

	boolean isToken(Token t, String tokenType){
		return t.getClass().getName().equals("lexing.node." + tokenType);
	}

	void Program(){
		MainClass();
		while (isToken("TClas")) {
			ClassDecl();
		}
	}

	void MainClass() {
		eat("TClas");
		eat("TIdentifier");
		eat("TLeftBrace");
		eat("TPublic");
		eat("TStatic");
		eat("TVoid");
		eat("TMain");
		eat("TLeftParen");
		eat("TString");
		eat("TLeftBracket");
		eat("TRightBracket");
		eat("TIdentifier");
		eat("TRightParen");
		eat("TLeftBrace");
		while (isToken("TInt") || isToken("TBoolean") || isToken("TIdentifier")) {
			VarDecl();
		}
		Statement();
		eat("TRightBrace");
		eat("TRightBrace");
	}

	void ClassDecl() {
		ClassDeclSpec();
		ClassDeclDef();
	}

	void ClassDeclSpec() {
		eat("TClas");
		eat("TIdentifier");
	}

	void ClassDeclDef() {
		if (isToken("TLeftBrace")) {
			eat("TLeftBrace");

			while (isToken("TInt") || isToken("TBoolean") || isToken("TIdentifier")) {
				VarDecl();
			}
			while (isToken("TPublic")) {
				MethodDecl();
			}

			eat("TRightBrace");
		}

		else {
			eat("TExtends");
			eat("TIdentifier");
			eat("TLeftBrace");

			while (isToken("TInt") || isToken("TBoolean") || isToken("TIdentifier")) {
				VarDecl();
			}
			while (eat("TPublic")) {

				MethodDecl();
			}

			eat("TRightBrace");
		}
	}

	void VarDecl() {
		Type();
		VarDeclType();
		eat("TSemi");
	}

	void VarDeclType() {
		eat("TIdentifier");
		VarDeclTypeAssign();
	}

	void VarDeclTypeAssign() {
		if (isToken("TAssign")){
			eat("TAssign");
			Exp();
		}

		if (isToken("TComma")){
			MultiDecl();
		}
	}

	void MultiDecl() {
		eat("TComma");
		eat("TIdentifier");
		VarDeclTypeAssign();
	}

	void MethodDecl() {
		eat("TPublic");
		Type();
		eat("TIdentifier");
		eat("TLeftParen");
		FormalList();
		eat("TRightParen");
		eat("TLeftBrace");
		while (isToken("TInt") || isToken("TBoolean") || (isToken("TIdentifier") && peek("TIdentifier"))){
			VarDecl();
		}


		while (isToken("TLeftBrace") || isToken("TIf") || isToken("TDo") || isToken("TWhile") || isToken("TFor")
				|| isToken("TSwitch") || isToken("TPrintln") || isToken("TIdentifier")
				|| isToken("TLeftParen"))
			Statement();
		eat("TReturn");
		Exp();
		eat("TSemi");
		eat("TRightBrace");

	}

	void FormalList() {
		if (isToken("TInt") || isToken("TBoolean") || isToken("TIdentifier")) {
			Type();
			eat("TIdentifier");
			while (isToken("TComma"))
				FormalRest();
		}
	}

	void FormalRest() {
		eat("TComma");
		Type();
		eat("TIdentifier");
	}

	void Type() {
		if (isToken("TInt")) {
			eat("TInt");
			IntType();
		} else if (isToken("TBoolean"))
			eat("TBoolean");
		else if (isToken("TIdentifier"))
			eat("TIdentifier");
		else
			throw new ParsingException(token);
	}

	void IntType() {
		if (isToken("TLeftParen")) {
			eat("TLeftBracket");
			eat("TRightBracket");
		}
	}

	void Statement() {
		if (isToken("TLeftBrace")) { // {Statement*}
			eat("TLeftBrace");
			while (isToken("TLeftBrace") || isToken("TIf") || isToken("TDo") || isToken("TWhile") || isToken("TFor")
					|| isToken("TSwitch") || isToken("TPrintln") || isToken("TIdentifier")
					|| isToken("TLeftParen"))
				Statement();
			eat("TRightBrace");
		}
		else if (isToken("TIf")) { // if statement
			eat("TIf");
			eat("TLeftParen");
			Exp();
			eat("TRightParen");
			Statement();

			while (isToken("TElse")) { 
				ElseIf();

			}

		} else if (isToken("TDo")) {
			eat("TDo");
			eat("TLeftBrace");
			Statement();
			eat("TRightBrace");
			eat("TWhile");
			eat("TLeftParen");
			Exp();
			eat("TRightParen");
			eat("TSemi");

		} else if (isToken("TWhile")) {
			eat("TWhile");
			eat("TLeftParen");
			Exp();
			eat("TRightParen");
			Statement();
		} else if (isToken("TFor")) {
			eat("TFor");
			eat("TLeftParen");
			InitializationStm();
			eat("TSemi");
			Exp();
			eat("TSemi");
			IncrementStm();
			eat("TRightParen");
			Statement();
		} else if (isToken("TSwitch")) {
			eat("TSwitch");
			eat("TLeftParen");
			eat("TIdentifier");
			eat("TRightParen");
			eat("TLeftBrace");
			CaseList();
			eat("TRightBrace");
		} else if (isToken("TPrintln")) {
			eat("TPrintln");
			eat("TLeftParen");
			Exp();
			eat("TRightParen");
			eat("TSemi");

		} else if (isToken("TIdentifier")) {
			eat("TIdentifier");
			Assign();
			eat("TSemi");
		} else if (isToken("TLeftParen")) {
			eat("TLeftParen");
			Type();
			eat("TIdentifier");
			eat("TAssign");
			Exp();
			eat("TRightParen");
			while (isToken("TComma"))
				FormalVarExp();
			eat("TSemi");

		} 
		else if(isToken("TRightBrace")){
			//Don't do anything
		}
		else{
			throw new ParsingException(token, "statment");
		}


	}

	void ElseIf() {
		eat("TElse");
		eat("TLeftParen");
		Exp();
		eat("TRightParen");
		Statement();
	}

	void Else() {
		Statement();
	}

	void Assign() {
		if (isToken("TAssign")) {
			eat("TAssign");
			Exp();
		} else if (isToken("TLeftBracket")) {
			eat("TLeftBracket");
			Exp();
			eat("TRightBracket");
			eat("TAssign");
			Exp();
		} else
			throw new ParsingException(token, "assign");
	}

	void InitializationStm()
	{
		if(isToken("TInt") || isToken("TBoolean")){ //Type id where Type is int or boolean
			Type();
			eat("TIdentifier");
			eat("TAssign");
			Exp();
		}
		else if(isToken("TIdentifier")) 
		{ 
			if(peek("TLeftBracket"))
			{
				eat("TIdentifier");
				eat("TLeftBracket");
				Exp();
				eat("TRightBracket");
				eat("TAssign");
				Exp();
				// this one is SUUUPER tough, cause identifier token can indicate id or id[] or id[Exp] = Exp or id = Exp
			}
			else
			{
				eat("TIdentifier");
				eat("TAssign");
				Exp();
			}

		}
	}

	void IncrementStm() 
	{
		if(isToken("TIdentifier")) 
		{ 
			if(peek("TLeftBracket"))
			{
				eat("TIdentifier");
				eat("TLeftBracket");
				Exp();
				eat("TRightBracket");
				eat("TAssign");
				Exp();
				// this one is SUUUPER tough, cause identifier token can indicate id or id[] or id[Exp] = Exp or id = Exp
			}
			else
			{
				eat("TIdentifier");
				eat("TAssign");
				Exp();
			}
		}

	}

	void FormalVarExp() {
		eat("TComma");
		eat("TLeftParen");
		Type();
		eat("TIdentifier");
		eat("TAssign");
		Exp();
		eat("TRightParen");
	}

	void CaseList() {
		if (isToken("TCase")) {
			eat("TCase");
			Exp();
			eat("TColon");
			Statement();
			CaseList();
		} else if (isToken("TDefault")) {
			eat("TDefault");
			eat("TColon");
			Statement();
		}
	}

	void Exp() {
			And();
			Elist();
	}

	void Elist() {
		if (isToken("TAnd")) {
			eat("TAnd");
			And();
			Elist();
		}
	}

	void And() {
		Less();
		Alist();
	}

	void Alist() {
		if (isToken("TLessThan")) {
			eat("TLessThan");
			Less();
			Alist();
		}
	}

	void Less() {
		Term();
		Llist();
	}

	void Llist() {
		if (isToken("TPlus")) {
			eat("TPlus");
			Term();
			Llist();
		} else if (isToken("TMinus")) {
			eat("TMinus");
			Term();
			Llist();
		}
	}

	void Term() {
		Not();
		Tlist();
	}

	void Tlist() {
		if (isToken("TMult")) {
			eat("TMult");
			Not();
			Tlist();
		}
	}

	void Not() {
		if (isToken("TNot")) {
			eat("TNot");
			Not();
		}
		else
		{
			Factor();
			while (isToken("TDot") || isToken("TLeftBracket")){
				DotArray();
			}


		}

		/*else if (isToken("TIntNum") || isToken("TTrue") || isToken("TFalse") || isToken("TIdentifier")
				|| isToken("TThis") || isToken("TNew") || isToken("TLeftParen")) {
			Factor();
			while (isToken("TDot") || isToken("LeftBracket"))
				DotArray();
		}else{

			//throw new ParsingException(token, "not");
		}*/

	}

	void DotArray() {
		if (isToken("TDot")) {
			eat("TDot");
			Member();
		} else if (isToken("TLeftBracket")) {
			eat("TLeftBracket");
			Exp();
			eat("TRightBracket");
		}
	}

	void Member() {
		if (isToken("TLength")) {
			eat("TLength");
		} else if (isToken("TIdentifier")) {
			eat("TIdentifier");
			eat("TLeftParen");
			ExpList();
			eat("TRightParen");
		}
	}

	void ExpList() {
		if (isToken("TIntNum") || isToken("TTrue") || isToken("TFalse") || isToken("TIdentifier") || isToken("TThis")
				|| isToken("TNew") || isToken("TLeftParen") || isToken("TNot")) {
			Exp();
			while (isToken("TComma")) {
				ExpRest();
			}
		}
	}

	void ExpRest() {
		eat("TComma");
		Exp();
	}

	void Factor() {
		if (isToken("TIntNum"))
			eat("TIntNum");
		else if (isToken("TTrue"))
			eat("TTrue");
		else if (isToken("TFalse"))
			eat("TFalse");
		else if (isToken("TIdentifier"))
			eat("TIdentifier");
		else if (isToken("TThis"))
			eat("TThis");
		else if (isToken("TNew")) {
			eat("TNew");
			New();
		} else if (isToken("TLeftParen")) {
			eat("TLeftParen");
			Exp();
			eat("TRightParen");
		}
	}

	void New() {
		if (isToken("TInt")) {
			eat("TInt");
			eat("TLeftBracket");
			Exp();
			eat("TRightBracket");
		} else if (isToken("TIdentifier")) {
			eat("TIdentifier");
			eat("TLeftParen");
			eat("TRightParen");
		}
	}
}
