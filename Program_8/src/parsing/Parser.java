package parsing;

import lexing.lexer.*;
import lexing.node.Token;
import symbolTableBuilder.*;
import symbolTableBuilder.Assign;
import symbolTableBuilder.Exp;

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
	 * @throws FileNotFoundException 
	 */
	public Parser(String file) throws FileNotFoundException
	{
		lexer = new Lexer(new PushbackReader(new FileReader(file), 1024));
		token = null;
		try{
			do{
				token = lexer.next();
			}while (isToken(tspace) || isToken(tdscomment));
	

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
			//	System.out.println("Eat token: " + token.getClass().getName() + " " + token.toString());
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

	public Program Parse(){
		return Program();
	}
	/**
	 * 
	 * 
	 * From this point on, the code should be a direct translation from our grammar
	 * 
	 * 
	 */
	
	
	Program Program()
	{
		MainClass mc = MainClass();
		ClassDeclList cd = new ClassDeclList();
		while (isToken(tclas)) 
		{
			cd.add(ClassDecl());
		}
		Program p = new Program(mc, cd);
		return p;
	}

	 MainClass MainClass() 
	 {
		eat(tclas);
		Identifier className = new Identifier(token.getText());
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
		Identifier args = new Identifier(token.getText());
		eat(tid);
		eat(trightparen);
		eat(tleftbrace);
		VarDeclList v = new VarDeclList();
		while (isToken(tint) || isToken(tboolean) || isToken(tid)) {
			v.add(VarDecl());
		}
		Statement s = Statement();
		MainClass n = new MainClass(className, args, v, s);
		eat(trightbrace);
		eat(trightbrace);
		return n;
	 }

	 ClassDecl ClassDecl() {
		 boolean extendBool = false;
		 Identifier extendName = null;
		 VarDeclList varList = new VarDeclList();
		 MethodDeclList methList = new MethodDeclList();

		 eat(tclas);
		 Identifier className = new Identifier(token.getText());
		 eat(tid);

		 if(isToken(textends))
		 {
			 eat(textends);
			 extendName = new Identifier(token.getText());
			 extendBool = true;
		 }

		 eat(tleftbrace);

		 while (isToken(tint) || isToken(tboolean) || isToken(tid)) {
			 varList.add(VarDecl());
		 }
		 while (isToken(tpublic)) {
			 methList.add(MethodDecl());
		 }

		 eat(trightbrace);



		 if(extendBool)
			 return new ClassDeclDeffExtend(className, extendName, varList, methList);
		 
		 return new ClassDeclDeffSimple(className, varList, methList);
	 }
	 
	VarDecl VarDecl() 
	{
		Type type = Type();
		VarDeclType varType = VarDeclType();
		eat(tsemi);
		
		return new VarDecl(type, varType);
	}

	VarDeclType VarDeclType() 
	{
		Identifier varName = new Identifier(token.getText());
		eat(tid);
		
		if(isToken(tassign))
		{
			eat(tassign);
			Exp exp = Exp();
			
			return new VarDeclType(varName, exp);
		}
		
		return new VarDeclType(varName);
	}

	MethodDecl MethodDecl() 
	{
		VarDeclList varList = new VarDeclList();
		StatementList statementList = new StatementList();
		
		eat(tpublic);
		Type type = Type();
		Identifier methodName = new Identifier(token.getText());
		eat(tid);
		eat(tleftparen);
		FormalList parameters = FormalList();
		eat(trightparen);
		eat(tleftbrace);
		while (isToken(tint) || isToken(tboolean) || (isToken(tid) && peek(tid))){
			varList.add(VarDecl());
		}


		while (isToken(tleftbrace) || isToken(tif) || isToken(tdo) || isToken(twhile) || isToken(tfor)
				|| isToken(tswitch) || isToken(tprintln) || isToken(tid)
				|| isToken(tleftparen))
			statementList.add(Statement());
		Block block = new Block(statementList);
		eat(treturn);
		Exp exp = Exp();
		eat(tsemi);
		eat(trightbrace);
		
		return new MethodDecl(type, methodName, parameters, varList, block, exp);

	}

	FormalList FormalList() 
	{
		FormalRestList restList = new FormalRestList();
		
		Type type = null;
		Identifier paramName = null;
		
		if (isToken(tint) || isToken(tboolean) || isToken(tid)) 
		{
			type = Type();
			paramName = new Identifier(token.getText());
			eat(tid);
			while (isToken(tcomma))
				restList.add(FormalRest());
			
			return new FormalList(type, paramName, restList);
		}
		
		return new FormalList();
		
		
	}

	FormalRest FormalRest() 
	{
		eat(tcomma);
		Type type = Type();
		Identifier name = new Identifier(token.getText());
		eat(tid);
		
		return new FormalRest(type, name);
	}

	Type Type() 
	{
		Type type = null;
		
		if (isToken(tint)) 
		{
			eat(tint);
			
			if(isToken(tleftparen))
				type = new IntArrayType();
			else
				type = new IntegerType();
		} 
		else if (isToken(tboolean))
		{
			type = new BooleanType();
			eat(tboolean);
		}
		else if (isToken(tid))
		{
			type = new IdentifierType(token.getText());
			eat(tid);
		}
		else
			throw new ParsingException(token);
		
		return type;
	}

	Statement Statement() 
	{ StatementList stmtList = new StatementList();
	
		if (isToken(tleftbrace)) { // {Statement*}
			eat(tleftbrace);
			while (isToken(tleftbrace) || isToken(tif) || isToken(tdo) || isToken(twhile) || isToken(tfor)
					|| isToken(tswitch) || isToken(tprintln) || isToken(tid)
					|| isToken(tleftparen))
				stmtList.add(Statement());
			eat(trightbrace);
			
			Block block = new Block(stmtList);
			return block;
		}
		else if (isToken(tif)) 
		{
			ElseIfList elseIfList = new ElseIfList();
			eat(tif);
			eat(tleftparen);
			Exp exp = Exp();
			eat(trightparen);
			Statement stmt = Statement();

			while (isToken(telse)) 
			{ 
				elseIfList.add(ElseIf());
			}
			
			return new If(exp, stmt, elseIfList);

		}
		else if (isToken(tdo)) 
		{
			eat(tdo);
			eat(tleftbrace);
			Statement stmt = Statement();
			eat(trightbrace);
			eat(twhile);
			eat(tleftparen);
			Exp exp = Exp();
			eat(trightparen);
			eat(tsemi);
			
			return new Do(stmt, exp);
		} 
		else if (isToken(twhile)) 
		{
			eat(twhile);
			eat(tleftparen);
			Exp exp = Exp();
			eat(trightparen);
			Statement stmt = Statement();
			
			return new While(exp, stmt);
		} 
		else if (isToken(tfor)) 
		{
			eat(tfor);
			eat(tleftparen);
			InitializationStm initial = InitializationStm();
			eat(tsemi);
			Exp exp = Exp();
			eat(tsemi);
			IncrementStm increment = IncrementStm();
			eat(trightparen);
			Statement stmt = Statement();
			
			return new For(initial, exp, increment, stmt);
		} 
		else if (isToken(tswitch)) 
		{
			eat(tswitch);
			eat(tleftparen);
			Identifier variable = new Identifier(token.getText());
			eat(tid);
			eat(trightparen);
			eat(tleftbrace);
			CaseList caseList = CaseList(variable);
			eat(trightbrace);
			
			return new Switch(variable, caseList);
		} 
		else if (isToken(tprintln)) 
		{
			Exp exp = null;
			eat(tprintln);
			eat(tleftparen);
			if(!isToken(trightparen))
			{
				exp = Exp();
			}
			eat(trightparen);
			eat(tsemi);
			
			return new Print(exp);
		} 
		else if (isToken(tid)) 
		{
			Identifier variable = new Identifier(token.getText());
			eat(tid);
			Assign assign = Assign(variable);
			eat(tsemi);

			return assign;
		} 
		
		else if(isToken(trightbrace))
		{
			//Don't do anything, don't throw an exception
		}
		
		throw new ParsingException(token, "stmt");
	}

	ElseIf ElseIf() 
	{
		eat(telse);
		eat(tleftparen);
		Exp exp = Exp();
		eat(trightparen);
		Statement stmt = Statement();
		
		return new ElseIf(exp, stmt);
	}

	/*
	void Else() {
		Statement();
	}
	*/

	Assign Assign(Identifier id) 
	{
		if (isToken(tassign)) 
		{
			eat(tassign);
			Exp exp = Exp();
			return new AssignSimple(id, exp);
		} 
		else if (isToken(tleftbracket)) 
		{
			eat(tleftbracket);
			Exp exp1 = Exp();
			eat(trightbracket);
			eat(tassign);
			Exp exp2 = Exp();
			
			return new AssignArray(id, exp1, exp2);
		} 
		else
			throw new ParsingException(token, "assign");
		
	}

	InitializationStm InitializationStm()
	{

		if(isToken(tid)) 
		{ 
			Identifier varName = new Identifier(token.getText());
			
			if(peek(tleftbracket))
			{
				eat(tid);
				eat(tleftbracket);
				Exp exp1 = Exp();
				eat(trightbracket);
				eat(tassign);
				Exp exp2 = Exp();
				
				return new InitializeArray(varName, exp1, exp2);
			}
			else
			{
				eat(tid);
				eat(tassign);
				Exp exp = Exp();
				return new InitializeSimple(varName, exp);
				
			}

		}

		throw new ParsingException(token, "left bracket or id");
	}

	IncrementStm IncrementStm() 
	{
		if(isToken(tid)) 
		{ 
			Identifier varName = new Identifier(token.getText());
			
			if(peek(tleftbracket))
			{
				eat(tid);
				eat(tleftbracket);
				Exp exp1 = Exp();
				eat(trightbracket);
				eat(tassign);
				Exp exp2 = Exp();
				
				return new IncrementArray(varName, exp1, exp2);
			}
			else
			{
				eat(tid);
				eat(tassign);
				Exp exp = Exp();
				
				return new IncrementSimple(varName, exp);
			}
		}
		
		throw new ParsingException(token, "left bracket or id");

	}

	CaseList CaseList(Identifier id) {
		if (isToken(tcase)) {
			eat(tcase);
			Exp exp = Exp();
			eat(tcolon);
			Statement stmt = Statement();
			CaseList caseList = CaseList(id);
			
			return new CaseListCase(id, exp, stmt, caseList);
		} 
		else if (isToken(tdefault)) 
		{
			eat(tdefault);
			eat(tcolon);
			Statement stmt = Statement();
			return new CaseListDefault(stmt);
		}
		else
		{
			throw new ParsingException(token, "case or default");
		}
	}

	Exp Exp() 
	{
		And and = And();
		Elist elist = Elist();
		return new Exp(and, elist);
	}

	Elist Elist() 
	{
		if (isToken(tand)) {
			eat(tand);
			And and = And();
			Elist elist = Elist();
			return new Elist(and, elist);
		}
		
		return new Elist();
	}

	And And() 
	{
		Less less = Less();
		Alist alist = Alist();
		return new And(less, alist);
	}

	Alist Alist() {
		if (isToken(tlessthan)) 
		{
			eat(tlessthan);
			Less less = Less();
			Alist alist = Alist();
			
			return new Alist(less, alist);
		}
		
		return new Alist();
	}

	Less Less() 
	{
		Term term = Term();
		Llist llist = Llist();
		
		return new Less(term, llist);
	}

	Llist Llist() 
	{
		if (isToken(tplus)) 
		{
			eat(tplus);
			Term term = Term();
			Llist llist = Llist();
			
			return new LlistSum(term, llist);
			
		} else if (isToken(tminus)) 
		{
			eat(tminus);
			Term term = Term();
			Llist llist = Llist();
			
			return new LlistDifference(term, llist);
		}
		
		return new Llist();
	}

	Term Term() 
	{
		Not not = Not();
		Tlist tlist = Tlist();
		
		return new Term(not, tlist);
	}

	Tlist Tlist() 
	{
		if (isToken(tmult)) 
		{
			eat(tmult);
			Not not = Not();
			Tlist tlist = Tlist();
			
			return new Tlist(not, tlist);
		}
		
		return new Tlist();
	}

	Not Not() 
	{
		DotArrayList dotList = new DotArrayList();
		if (isToken(tnot)) 
		{
			eat(tnot);
			return new NotSimple(Not());
		}
		else
		{
			Factor factor = Factor();
			while (isToken(tdot) || isToken(tleftbracket)){
				dotList.add(DotArray());
			}
			
			return new NotFactor(factor, dotList);
		}
	}

	DotArray DotArray() 
	{
		if (isToken(tdot)) 
		{
			eat(tdot);
			Member member = Member();
			return new DotArrayMember(member);
			
		} else if(isToken(tleftbracket)) 
		{
			eat(tleftbracket);
			Exp exp = Exp();
			eat(trightbracket);
			
			return new DotArrayArray(exp);
		}
		
		throw new ParsingException(token, "dot or left bracket");
		
	}

	Member Member() {
		if (isToken(tlength)) 
		{
			eat(tlength);
			return new MemberLength();
		} 
		else if (isToken(tid)) 
		{
			Identifier methodName = new Identifier(token.getText());
			eat(tid);
			eat(tleftparen);
			ExpList expList = ExpList();
			eat(trightparen);
			
			return new MemberId(methodName, expList);
		}
		
		throw new ParsingException(token, "length or identifier");
	}

	
	Factor Factor() {
		if (isToken(tintnum))
		{
			IntegerLiteral intLit = new IntegerLiteral(Integer.parseInt((token.getText())));
			eat(tintnum);
			return intLit;
		}
		else if (isToken(ttrue))
		{
			eat(ttrue);
			return new True();
		}
		else if (isToken(tfalse))
		{
			eat(tfalse);
			return new False();
		}
		else if (isToken(tid))
		{
			IdentifierExp id = new IdentifierExp(token.getText());
			eat(tid);
			return id;
		}
		else if (isToken(tthis))
		{
			eat(tthis);
			return new This();
		}
		else if (isToken(tnew)) 
		{
			eat(tnew);
			New newObject = New();
			return new FactorNew(newObject);
		} 
		else if (isToken(tleftparen)) 
		{
			eat(tleftparen);
			Exp exp = Exp();
			eat(trightparen);
			return exp;
		}
		else
		{
			throw new ParsingException(token, "factor");
		}
	}

	New New() 
	{
		if (isToken(tint)) 
		{
			eat(tint);
			eat(tleftbracket);
			Exp exp = Exp();
			eat(trightbracket);
			return new NewArray(exp);
		} 
		else if (isToken(tid)) 
		{
			Identifier id = new Identifier(token.getText());
			eat(tid);
			eat(tleftparen);
			eat(trightparen);
			return new NewObject(id);
		}
		
		throw new ParsingException(token, "int or id");
	}
	
	
	ExpList ExpList() 
	{
		ExpRestList restList = new ExpRestList();
		Exp exp = null;
		
		if (isToken(tintnum) || isToken(ttrue) || isToken(tfalse) || isToken(tid) || isToken(tthis)
				|| isToken(tnew) || isToken(tleftparen) || isToken(tnot)) 
		{
			exp = Exp();
			while (isToken(tcomma)) 
			{
				restList.add(ExpRest());
			}
			
			return new ExpList(exp, restList);
		}
		
		throw new ParsingException(token, "wanted exp start");
	}

	ExpRest ExpRest() 
	{
		eat(tcomma);
		Exp exp = Exp();
		
		return new ExpRest(exp);
	}
}
