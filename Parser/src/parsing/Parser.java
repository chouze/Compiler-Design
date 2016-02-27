package parsing;

import lexing.lexer.*;
import lexing.node.Token;
import java.io.*;

/*
 * need to work on: 
 * 		statement -> else/elseif
 * 		statement -> System.out.prinln	
 * 		InitializationStm
 *  	IncrementStm		
 *  	Not --> Exp DotArray* 
 */
public class Parser{
	Lexer lexer;
	Token token;

	public Parser()
	{
		lexer = new Lexer(new PushbackReader(new InputStreamReader(System.in), 1024));
		token = null;
		try{
			do{
				token = lexer.next();
			}while (isToken("TSpace")|| isToken("TDoubleSlashComment"));
			Program();

		}catch(LexerException le) {System.err.println(le);}
		catch(IOException ioe) {System.err.println(ioe);}
	}

	boolean peek(String tokenType)
	{
		try {
			return lexer.peek().getClass().getName().equals("lexing.node." + tokenType);
		} catch (LexerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	boolean eat(String name) {
		try {
			if (isToken(name)) {
				System.out.println("Eat token: " + token.getClass().getName() + " " + token.toString() + " om nom nom");


				do{
					token = lexer.next();
				}while (isToken("TSpace")|| isToken("TDoubleSlashComment"));

				return true;
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
		return t.getClass().getName().equals(tokenType);
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
			while (eat("TPublic")) {
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
			while (isToken("TPublic")) {
				MethodDecl();
			}

			eat("TRightBrace");
		}
	}

	//NEED TO LOOK HERE
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
		else if (isToken("TComma")){
			MultiDecl();
		}
	}

	void MultiDecl() {
		eat("TComma");
		eat("TIdentifier");
		VarDeclTypeAssign();
	}

	void MethodDecl() {
		Type();
		eat("TIdentifier");
		eat("TLeftParen");
		FormalList();
		eat("TRightParen");
		eat("TLeftBrace");
		while (isToken("TInt") || isToken("TBoolean") || isToken("TIdentifier"))
			VarDecl();

		while (isToken("TLeftBrace") || isToken("TIf") || isToken("TDo") || isToken("TWhile") || isToken("TFor")
				|| isToken("TSwitch") || isToken("TPrintln") || isToken("TIdentifier")
				|| isToken("TLeftParen"))
			Statement();

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
		else if(isToken("TRightBrace")){
			eat("TRightBrace");
		}
		else if (isToken("TIf")) { // if statement
			eat("TIf");
			eat("TLeftParen");
			Exp();
			eat("TRightPern");
			Statement();

			while (isToken("TElse")) { // I don't like the way this is, but it's
				// the only way I can think of doing it.
				// Please halp
				eat("TElse");
				if (isToken("TIf")) {
					ElseIf();
				} else {
					Else();
					break;// break out of the loop after running an Else so as not to do elseif or else again
				}
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

		} else
			throw new ParsingException(token, "statment");
	}

	void ElseIf() {
		eat("TIf");
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
		}
		if(isToken("TIdentifier")) { // this one is SUUUPER tough, cause identifier token can indicate id or id[] or id[Exp] = Exp or id = Exp

		}
	}

	void IncrementStm() {

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
			eat("TColon");
			Exp();
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
		} else if (isToken("TIntNum") || isToken("TTrue") || isToken("TFalse") || isToken("TIdentifier")
				|| isToken("TThis") || isToken("TNew") || isToken("TLeftParen")) {
			Factor();
			while (isToken("TDot") || isToken("LeftBracket"))
				DotArray();
		} else if (false) // Not --> Exp DotArray
		{
			///////////////////
		} else
			throw new ParsingException(token, "not");
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
		}
	}

	void ExpList() {
		if (isToken("TNumber") || isToken("TTrue") || isToken("TFalse") || isToken("TIdentifier") || isToken("TThis")
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
		} else
			throw new ParsingException(token, "factor");
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