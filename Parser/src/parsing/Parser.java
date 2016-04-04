package parsing;

import lexing.lexer.*;
import lexing.node.Token;
import semanticBuild.*;
import symbolTableBuilder.*;
import symbolTableBuilder.Assign;
import symbolTableBuilder.Exp;

import java.io.*;


/**
 * 
 * @author Christopher Houze, David Carlin, Clifford Black
 *
 */
public class Parser implements symbolTableBuilder.Visitor{
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

			//Program();
			

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
	 

	/*
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
	*/
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
		VarDeclTypeAssign varAssign = VarDeclTypeAssign();
		
		return new VarDeclType(varName, varAssign);
	}

	VarDeclTypeAssign VarDeclTypeAssign() 
	{
			eat(tassign);
			Exp exp = Exp();
			
			return new VarDeclTypeAssign(exp);
	}

/*
	void MultiDecl() {
		eat(tcomma);
		eat(tid);
		VarDeclTypeAssign();
	}

*/

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
		eat(treturn);
		Exp exp = Exp();
		eat(tsemi);
		eat(trightbrace);
		
		return new MethodDecl(type, methodName, parameters, varList, statementList, exp);

	}

	FormalList FormalList() 
	{
		FormalRestList restList = new FormalRestList();
		
		Type type = null;
		Identifier paramName = null;
		
		if (isToken(tint) || isToken(tboolean) || isToken(tid)) {
			type = Type();
			paramName = new Identifier(token.getText());
			eat(tid);
			while (isToken(tcomma))
				restList.add(FormalRest());
		}
		
		return new FormalList(type, paramName, restList);
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

	/*
	void IntType() {
		if (isToken(tleftparen)) {
			eat(tleftbracket);
			eat(trightbracket);
		}
	}
	
	*/

	Statement Statement() 
	{		
		if (isToken(tleftbrace)) { // {Statement*}
			eat(tleftbrace);
			while (isToken(tleftbrace) || isToken(tif) || isToken(tdo) || isToken(twhile) || isToken(tfor)
					|| isToken(tswitch) || isToken(tprintln) || isToken(tid)
					|| isToken(tleftparen))
				Statement();
			eat(trightbrace);
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
			CaseList caseList = CaseList();
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
			Assign assign = Assign();
			eat(tsemi);

			return assign;
		} 
		/*
		else if (isToken(tleftparen)) 
		{
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
		*/
		else if(isToken(trightbrace))
		{
			//Don't do anything, don't throw an exception
		}
		else
		{
			throw new ParsingException(token, "statment");
		}
		
		return null;
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

	Assign Assign() 
	{
		if (isToken(tassign)) 
		{
			eat(tassign);
			Exp exp = Exp();
			return new AssignSimple(exp);
		} 
		else if (isToken(tleftbracket)) 
		{
			eat(tleftbracket);
			Exp exp1 = Exp();
			eat(trightbracket);
			eat(tassign);
			Exp exp2 = Exp();
			
			return new AssignArray(exp1, exp2);
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
		
		return null;
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
		
		return null;

	}

	/*
	void FormalVarExp() {
		eat(tcomma);
		eat(tleftparen);
		Type();
		eat(tid);
		eat(tassign);
		Exp();
		eat(trightparen);
	}
	*/

	CaseList CaseList() {
		if (isToken(tcase)) {
			eat(tcase);
			Exp exp = Exp();
			eat(tcolon);
			Statement stmt = Statement();
			CaseList caseList = CaseList();
			
			return new CaseListCase(exp, stmt, caseList);
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
/*
	Exp Exp() 
	{
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
	
	*/
	
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
		
		return null;
	}

	ExpRest ExpRest() 
	{
		eat(tcomma);
		Exp exp = Exp();
		
		return new ExpRest(exp);
	}
	
	Exp Exp()
	{
		if(isToken(ttrue))
		{
			eat(ttrue);
			return new True();
		}
		else if(isToken(tfalse))
		{	
			eat(tfalse);
			return new False();
		}
		else if(isToken(tintnum))
		{
			eat(tintnum);
			return new IntegerLiteral(Integer.parseInt(token.getText()));
		}
		else if(isToken(tid))
		{
			eat(tid);
			return new IdentifierExp(token.getText());
		}
		else if(isToken(tthis))
		{
			eat(tthis);
			return new This();
		}
		else if(isToken(tnew))
		{
			eat(tnew);
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
				Identifier newObject = new Identifier(token.getText());
				eat(tid);
				eat(tleftparen);
				eat(trightparen);
				
				return new NewObject(newObject);
			}
		}
		else
		{
			Exp e1 = Exp();
			if(isToken(tand))
			{
				eat(tand);
				Exp e2 = Exp();
				return new And(e1, e2);
			}
			else if(isToken(tlessthan))
			{
				eat(tlessthan);
				Exp e2 = Exp();
				return new LessThan(e1, e2);
			}
			else if(isToken(tplus))
			{
				eat(tplus);
				Exp e2 = Exp();
				return new Plus(e1, e2);
			}
			else if(isToken(tminus))
			{
				eat(tminus);
				Exp e2 = Exp();
				return new Minus(e1, e2);
			}
			else if(isToken(tmult))
			{
				eat(tmult);
				Exp e2 = Exp();
				return new Times(e1, e2);
			}
			else if(isToken(tnot))
			{
				eat(tnot);
				return new Not(e1);	
			}
			else if(isToken(tdot))
			{
				eat(tdot);
				if(isToken(tid))
				{
					Identifier object = new Identifier(token.getText());
					eat(tid);
					eat(tleftparen);
					ExpList expList = ExpList();
					eat(trightparen);
					return new Call(e1,object, expList);
				}
				else if(isToken(tlength))
				{
					eat(tlength);
					return new ArrayLength(e1);
				}
			}
			else if(isToken(tleftbracket))
			{
				eat(tleftbracket);
				Exp e2 = Exp();
				eat(trightbracket);
				return new ArrayLookUp(e1, e2);
			}
			
		}
		
		throw new ParsingException(token, "expression");
	
	}

	@Override
	public void visit(symbolTableBuilder.Program n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(symbolTableBuilder.MainClass n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ClassDeclDeffSimple n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ClassDeclDeffExtend n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ClassDeclList n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(symbolTableBuilder.VarDecl n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(symbolTableBuilder.VarDeclType n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(symbolTableBuilder.VarDeclTypeAssign n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(VarDeclList n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(symbolTableBuilder.MethodDecl n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(MethodDeclList n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(symbolTableBuilder.FormalList n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(symbolTableBuilder.FormalRest n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IntArrayType n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(BooleanType n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IntegerType n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IdentifierType n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(symbolTableBuilder.Statement n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Block n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(If n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Do n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(While n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(For n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Switch n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Print n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(AssignSimple n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(AssignArray n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(AssignMultiple n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(InitializeSimple n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(InitializeArray n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IncrementSimple n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IncrementArray n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(symbolTableBuilder.ElseIf n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(CaseListCase n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(CaseListDefault n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(symbolTableBuilder.ExpList n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(symbolTableBuilder.ExpRest n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(And n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(LessThan n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Plus n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Minus n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Times n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ArrayLookUp n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ArrayLength n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Call n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IntegerLiteral n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(True n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(False n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(IdentifierExp n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(This n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(NewArray n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(NewObject n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Not n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(Identifier n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(symbolTableBuilder.Exp n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(symbolTableBuilder.Type n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(StatementList n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(symbolTableBuilder.InitializationStm n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(symbolTableBuilder.IncrementStm n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(symbolTableBuilder.CaseList n) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(FormalRestList formalRestList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ElseIfList elseIfList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ExpRestList expRestList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visit(ClassDeclSpec classDeclSpec) {
		// TODO Auto-generated method stub
		
	}

}
