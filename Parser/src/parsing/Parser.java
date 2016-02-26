package parsing;

import lexing.lexer.*;
import lexing.node.*;

import java.io.*;

/*
 * need to work on: 
 * 		statement -> else/elseif
 * 		statement -> System.out.prinln	
 * 		InitializationStm
 *  	IncrementStm		
 *  	Not --> Exp DotArray* 
 *  	need TColon token
 *  	need TLessThan token
 *  	need TLength token
 */
class Parser {
	static Lexer lexer;
	static Object token;

	public static void main(String[] args) {
		lexer = new Lexer(new PushbackReader(new InputStreamReader(System.in), 1024));
		token = null;
		try {
			while (!(token instanceof EOF)) {
				token = lexer.next();
				System.out.println(token.getClass() + ": " + token);
			}
		} catch (LexerException le) {
			System.err.println(le);
		} catch (IOException ioe) {
			System.err.println(ioe);
		}
	}

	boolean eat(boolean equals) {
		if (equals) {
			try {
				token = lexer.next();
				return true;
			} catch (LexerException e) {
				e.printStackTrace();
				return false;
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		} else {
			System.err.println("Incorrect");
			return false;
		}
	}

	boolean isToken(String tokenType) {
		return token.getClass().equals("class lexing.node." + tokenType);
	}

	void Program() {
		MainClass();
		while (isToken("TClas")) {
			ClassDecl();
		}
	}

	void MainClass() {
		eat(isToken("TClas"));
		eat(isToken("TIdentifier"));
		eat(isToken("TLeftBrace"));
		eat(isToken("TPublic"));
		eat(isToken("TStatic"));
		eat(isToken("TVoid"));
		eat(isToken("TMain"));
		eat(isToken("TLeftParen"));
		eat(isToken("TString"));
		eat(isToken("TLeftBracket"));
		eat(isToken("TRightBracket"));
		eat(isToken("TIdentifier"));
		eat(isToken("TRightParen"));
		eat(isToken("TLeftbrace"));
		eat(isToken("TClas"));
		Statement();
		eat(isToken("TRightBrace"));
		eat(isToken("TRightBrace"));
	}

	void ClassDecl() {
		ClassDeclSpec();
		ClassDeclDef();
	}

	void ClassDeclSpec() {
		eat(isToken("TClas"));
		eat(isToken("TIdentifier"));
	}

	void ClassDeclDef() {
		if (isToken("TLeftBrace")) {
			eat(isToken("TLeftBrace"));

			while (isToken("TInt") || isToken("TBoolean") || isToken("TIdentifier")) {
				VarDecl();
			}
			while (eat(isToken("TPublic"))) {
				MethodDecl();
			}

			eat(isToken("TRightBrace"));
		}

		else {
			eat(isToken("TExtends"));
			eat(isToken("TIdentifier"));
			eat(isToken("TLeftBrace"));

			while (isToken("TInt") || isToken("TBoolean") || isToken("TIdentifier")) {
				VarDecl();
			}
			while (isToken("TPublic")) {
				MethodDecl();
			}

			eat(isToken("TRightBrace"));
		}
	}

	void VarDecl() {
		Type();
		VarDeclType();
		while (isToken("TComma"))
			MultiDecl();
		eat(isToken("TSemi"));
	}

	void VarDeclType() {
		eat(isToken("TIdentifier"));
	}

	void VarDeclTypeAssign() {
		if (isToken("TAssign")) {
			eat(isToken("TAssign"));
			Exp();
		}
	}

	void MultiDecl() {
		eat(isToken("TComma"));
		eat(isToken("TIdentifier"));
	}

	void MethodDecl() {
		eat(isToken("TPublic"));
		Type();
		eat(isToken("TIdentifier"));
		eat(isToken("TLeftParen"));
		FormalList();
		eat(isToken("TRightParen"));
		eat(isToken("TLeftBrace"));
		while (isToken("TInt") || isToken("TBoolean") || isToken("TIdentifier"))
			VarDecl();

		while (isToken("TLeftBrace") || isToken("TIf") || isToken("TDo") || isToken("TWhile") || isToken("TFor")
				|| isToken("TSwitch") || isToken("RESERVED FOR SYSTEM.OUT.PRINTLN") || isToken("TIdentifier")
				|| isToken("TLeftParen")) // CHECK SYSTEM.OUT.PRINTLN!!!!!!
			Statement();

	}

	void FormalList() {
		if (isToken("TInt") || isToken("TBoolean") || isToken("TIdentifier")) {
			Type();
			eat(isToken("TIdentifier"));
			while (isToken("TComma"))
				FormalRest();
		}
	}

	void FormalRest() {
		eat(isToken("TComma"));
		Type();
		eat(isToken("TIdentifier"));
	}

	void Type() {
		if (isToken("TInt")) {
			eat(isToken("TInt"));
			IntType();
		} else if (isToken("TBoolean"))
			eat(isToken("TBoolean"));
		else if (isToken("TIdentifier"))
			eat(isToken("TIdentifier"));
		else
			System.err.println("error");
	}

	void IntType() {
		if (isToken("TLeftParen")) {
			eat(isToken("TLeftBracket"));
			eat(isToken("TRightBracket"));
		}
	}

	void Statement() {
		if (isToken("TLeftBrace")) { // {Statement*}
			eat(isToken("TLeftBrace"));
			while (isToken("TLeftBrace") || isToken("TIf") || isToken("TDo") || isToken("TWhile") || isToken("TFor")
					|| isToken("TSwitch") || isToken("RESERVED FOR SYSTEM.OUT.PRINTLN") || isToken("TIdentifier")
					|| isToken("TLeftParen")) // CHECK SYSTEM.OUT.PRINTLN!!!!!!
				Statement();
			eat(isToken("TRightBrace"));
		}

		else if (isToken("TIf")) { // if statement
			eat(isToken("TIf"));
			eat(isToken("TLeftParen"));
			Exp();
			eat(isToken("TRightPern"));
			Statement();

			while (isToken("TElse")) { // I don't like the way this is, but it's
										// the only way I can think of doing it.
										// Please halp
				eat(isToken("TElse"));
				if (isToken("TIf")) {
					ElseIf();
				} else {
					Else();
					break;// break out of the loop after running an Else so as not to do elseif or else again
				}
			}

		} else if (

		isToken("TDo")) {
			eat(isToken("TDo"));
			eat(isToken("TLeftBrace"));
			Statement();
			eat(isToken("TRightBrace"));
			eat(isToken("TWhile"));
			eat(isToken("TLeftParen"));
			Exp();
			eat(isToken("TRightParen"));
			eat(isToken("TSemi"));

		} else if (isToken("TWhile")) {
			eat(isToken("TWhile"));
			eat(isToken("TLeftParen"));
			Exp();
			eat(isToken("TRightParen"));
			Statement();
		} else if (isToken("TFor")) {
			eat(isToken("TFor"));
			eat(isToken("TLeftParen"));
			InitializationStm();
			eat(isToken("TSemi"));
			Exp();
			eat(isToken("TSemi"));
			IncrementStm();
			eat(isToken("TRightPern"));
			Statement();
		} else if (isToken("TSwitch")) {
			eat(isToken("TSwitch"));
			eat(isToken("TLeftParen"));
			eat(isToken("TIdentifier"));
			eat(isToken("TRightPern"));
			eat(isToken("TLeftBrace"));
			CaseList();
			eat(isToken("TRightBrace"));
		} else if (isToken("TSYSTEM.OUT.PRINTLN")) { // system.out.println
			eat(isToken("System.our.ptintln token"));
			eat(isToken("TLeftParen"));
			Exp();
			eat(isToken("TRightPern"));
			eat(isToken("TSemi"));

		} else if (isToken("TIdentifier")) {
			eat(isToken("TIdentifier"));
			Assign();
		} else if (isToken("TLeftParen")) {
			eat(isToken("TLeftParen"));
			Type();
			eat(isToken("TIdentifier"));
			eat(isToken("TAssign"));
			Exp();
			eat(isToken("TRightParen"));
			while (isToken("TComma"))
				FormalVarExp();
			eat(isToken("TSemi"));

		} else
			System.err.println("error");
	}

	void ElseIf() {
		eat(isToken("TIf"));
		eat(isToken("TLeftParen"));
		Exp();
		eat(isToken("TRightParen"));
		Statement();
	}

	void Else() {
		Statement();
	}

	void Assign() {
		if (isToken("TAssign")) {
			eat(isToken("TAssign"));
			Exp();
		} else if (isToken("TLeftBracket")) {
			eat(isToken("TLeftBracket"));
			Exp();
			eat(isToken("TRightBracket"));
			eat(isToken("TAssign"));
			Exp();
		} else
			System.err.println("error");
	}

	void InitializationStm()
	{
		if(isToken("TInt") || isToken("TBoolean")){ //Type id where Type is int or boolean
			Type();
			eat(isToken("TIdentifier"));
		}
		if(isToken("TIdentifier")) { // this one is SUUUPER tough, cause identifier token can indicate id or id[] or id[Exp] = Exp or id = Exp
			
		}

	}

	void IncrementStm() {

	}

	void FormalVarExp() {
		eat(isToken("TComma"));
		eat(isToken("TLeftParen"));
		Type();
		eat(isToken("TIdentifier"));
		eat(isToken("TAssign"));
		Exp();
		eat(isToken("TRightPern"));
	}

	void CaseList() {
		if (isToken("TCase")) {
			eat(isToken("TCase"));
			eat(isToken("TColon"));
			Exp();
			Statement();
			CaseList();
		} else if (isToken("TDefault")) {
			eat(isToken("TDefault"));
			eat(isToken("TColon"));
			Statement();
		}
	}

	void Exp() {
		And();
		Elist();
	}

	void Elist() {
		if (isToken("TAnd")) {
			eat(isToken("TAnd"));
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
			eat(isToken("TLessThan"));
			Less();
			Alist();
		}
	}

	void Less() {
		Less();
		Llist();
	}

	void Llist() {
		if (isToken("TPlus")) {
			eat(isToken("TPlus"));
			Term();
			Llist();
		} else if (isToken("TMinus")) {
			eat(isToken("TMinus"));
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
			eat(isToken("TMult"));
			Not();
			Tlist();
		}
	}

	void Not() {
		if (isToken("TNot")) {
			eat(isToken("TNot"));
			Not();
		} else if (isToken("TNumber") || isToken("TTrue") || isToken("TFalse") || isToken("TIdentifier")
				|| isToken("TThis") || isToken("TNew") || isToken("TLeftParen")) {
			Factor();
			while (isToken("TDot") || isToken("LeftBracket"))
				DotArray();
		} else if (false) // Not --> Exp DotArray
		{
			///////////////////
		} else
			System.err.println("Error");
	}

	void DotArray() {
		if (isToken("TDot")) {
			eat(isToken("TDot"));
			Member();
		} else if (isToken("TLeftBracket")) {
			eat(isToken("TLeftBracket"));
			Exp();
			eat(isToken("TRightBracket"));
		}
	}

	void Member() {
		if (isToken("TLength")) {
			eat(isToken("TLength"));
		} else if (isToken("TIdentifier")) {
			eat(isToken("TIdentifier"));
			eat(isToken("TLeftParen"));
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
		eat(isToken("TComma"));
		Exp();
	}

	void Factor() {
		if (isToken("TNumber"))
			eat(isToken("TNumber"));
		else if (isToken("TTrue"))
			eat(isToken("TTrue"));
		else if (isToken("TFalse"))
			eat(isToken("TFalse"));
		else if (isToken("TIdentifier"))
			eat(isToken("TIdentifier"));
		else if (isToken("TThis"))
			eat(isToken("TThis"));
		else if (isToken("TNew")) {
			eat(isToken("TNew"));
			New();
		} else if (isToken("TLeftParen")) {
			eat(isToken("TLeftParen"));
			Exp();
			eat(isToken("TRightParen"));
		} else
			System.err.println("Error");
	}

	void New() {
		if (isToken("TInt")) {
			eat(isToken("TInt"));
			eat(isToken("TLeftBracket"));
			Exp();
			eat(isToken("TRightBracket"));
		} else if (isToken("TIdentifier")) {
			eat(isToken("TIdentifier"));
			eat(isToken("TLeftParen"));
			eat(isToken("TRightParen"));
		}
	}
}
