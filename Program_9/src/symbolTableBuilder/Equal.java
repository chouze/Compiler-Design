package symbolTableBuilder;

public class Equal implements Visitor {
	
	@Override
	public Object visit(Program n) {
		
		return null;
	}

	@Override
	public Object visit(MainClass n) {
		
		return null;
	}

	@Override
	public Object visit(ClassDecl n) {
		
		return null;
	}

	@Override
	public Object visit(ClassDeclDeffSimple n) {
		
		return null;
	}

	@Override
	public Object visit(ClassDeclDeffExtend n) {
		
		return null;
	}

	@Override
	public Object visit(ClassDeclList n) {
		
		return null;
	}

	@Override
	public Object visit(VarDecl n) {
		
		return null;
	}

	@Override
	public Object visit(VarDeclType n, Type t) {
		
		return null;
	}

	@Override
	public Object visit(VarDeclList n) {
		
		return null;
	}

	@Override
	public Object visit(MethodDecl n) {
		
		return null;
	}

	@Override
	public Object visit(MethodDeclList n) {
		
		return null;
	}

	@Override
	public Object visit(FormalList n) {
		
		return null;
	}

	@Override
	public Object visit(FormalRest n) {
		
		return null;
	}

	@Override
	public Object visit(Statement n) {
		
		return null;
	}

	@Override
	public Object visit(Block n) {
		
		return null;
	}

	@Override
	public Object visit(If n) {
		
		return null;
	}

	@Override
	public Object visit(ElseIf n) {
		
		return null;
	}

	@Override
	public Object visit(Do n) {
		
		return null;
	}

	@Override
	public Object visit(While n) {
		
		return null;
	}

	@Override
	public Object visit(For n) {
		
		return null;
	}

	@Override
	public Object visit(Switch n) {
		
		return null;
	}

	@Override
	public Object visit(CaseListCase n) {
		
		return null;
	}

	@Override
	public Object visit(CaseListDefault n) {
		
		return null;
	}

	@Override
	public Object visit(Print n) {
		
		return null;
	}

	@Override
	public Object visit(InitializeSimple n) {
		
		return null;
	}

	@Override
	public Object visit(InitializeArray n) {
		
		return null;
	}

	@Override
	public Object visit(IncrementSimple n) {
		
		return null;
	}

	@Override
	public Object visit(IncrementArray n) {
		
		return null;
	}

	@Override
	public Object visit(ExpList n, Identifier methodId) {
		
		return null;
	}

	@Override
	public Object visit(ExpRest n) {
		
		return null;
	}

	@Override
	public Object visit(StatementList n) {
		
		return null;
	}

	@Override
	public Object visit(InitializationStm n) {
		
		return null;
	}

	@Override
	public Object visit(IncrementStm n) {
		
		return null;
	}

	@Override
	public Object visit(CaseList n) {
		
		return null;
	}

	@Override
	public Object visit(FormalRestList formalRestList) {
		
		return null;
	}

	@Override
	public Object visit(ElseIfList elseIfList) {
		
		return null;
	}

	@Override
	public Object visit(ExpRestList expRestList, Identifier id) {
		
		return null;
	}

	@Override
	public Object visit(DotArrayList dotArrayList) {
		
		return null;
	}

	@Override
	public Object visit(DotArray n) {
		
		return null;
	}

	@Override
	public Object visit(DotArrayArray n) {
		
		return null;
	}

	@Override
	public Object visit(Member n) {
		
		return null;
	}

	@Override
	public Object visit(MemberLength n) {
		
		return null;
	}

	@Override
	public Object visit(Identifier n) {
		
		return null;
	}

	@Override
	public Object visit(Type n) {
		
		return null;
	}

	@Override
	public Object visit(IntArrayType n) {
		
		return null;
	}

	@Override
	public Object visit(BooleanType n) {
		
		return null;
	}

	@Override
	public Object visit(IntegerType n) {
		
		return null;
	}

	@Override
	public Object visit(IdentifierType n) {
		
		return null;
	}

	@Override
	public Object visit(AssignSimple n) {
		
		return null;
	}

	@Override
	public Object visit(AssignArray n) {
		
		return null;
	}

	@Override
	public Object visit(MemberId n) {
		
		return null;
	}

	@Override
	public Object visit(Exp n) {
		
		return null;
	}

	@Override
	public Object visit(Elist n) {
		
		return null;
	}

	@Override
	public Object visit(And n) {
		
		return null;
	}

	@Override
	public Object visit(Alist n) {
		
		return null;
	}

	@Override
	public Object visit(Less n) {
		
		return null;
	}

	@Override
	public Object visit(Llist n) {
		
		return null;
	}

	@Override
	public Object visit(LlistDifference n) {
		
		return null;
	}

	@Override
	public Object visit(LlistSum n) {
		
		return null;
	}

	@Override
	public Object visit(Term n) {
		
		return null;
	}

	@Override
	public Object visit(Tlist n) {
		
		return null;
	}

	@Override
	public Object visit(Not n) {
		
		return null;
	}

	@Override
	public Object visit(NotFactor n) {
		
		return null;
	}

	@Override
	public Object visit(NotSimple n) {
		
		return null;
	}

	@Override
	public Object visit(DotArrayMember n) {
		
		return null;
	}

	@Override
	public Object visit(Factor n) {
		
		return null;
	}

	@Override
	public Object visit(IntegerLiteral n) {
		
		return null;
	}

	@Override
	public Object visit(True n) {
		
		return null;
	}

	@Override
	public Object visit(False n) {
		
		return null;
	}

	@Override
	public Object visit(IdentifierExp n) {
		
		return null;
	}

	@Override
	public Object visit(This n) {
		
		return null;
	}

	@Override
	public Object visit(FactorNew n) {
		
		return null;
	}

	@Override
	public Object visit(NewArray n) {
		
		return null;
	}

	@Override
	public Object visit(NewObject n) {
		
		return null;
	}

	@Override
	public Object visit(New n) {
		
		return null;
	}
	
	@Override
	public Object visit(Object o1, Object o2) {
		if(o1 == null){
			return false;
		}
		if(o2 == null){
			return false;
		}
		
		if (!o1.getClass().getName().equals(o2.getClass().getName())){
			return false;
		}
		
		if(o1 instanceof Alist){
			Alist a1 = (Alist) o1;
			Alist a2 = (Alist) o2;
			
			return (boolean)visit(a1.alist, a2.alist) && (boolean)visit(a1.less, a2.less);
		}
		
		if(o1 instanceof And){
			And a1 = (And) o1;
			And a2 = (And) o2;
			
			return (boolean)visit(a1.alist, a2.alist) && (boolean)visit(a1.less, a2.less);
		}
		
		if(o1 instanceof AssignArray){
			AssignArray a1 = (AssignArray) o1;
			AssignArray a2 = (AssignArray) o2;
			
			return (boolean)visit(a1.id, a2.id) && (boolean)visit(a1.index, a2.index) && (boolean)visit(a1.value, a2.value);
		}
		
		if(o1 instanceof AssignSimple){
			AssignSimple a1 = (AssignSimple) o1;
			AssignSimple a2 = (AssignSimple) o2;
			
			return (boolean)visit(a1.id, a2.id) && (boolean)visit(a1.assignment, a2.assignment);
		}
		
		if(o1 instanceof Block){
			Block b1 = (Block) o1;
			Block b2 = (Block) o2;
			
			return (boolean)visit(b1.sl, b2.sl) ;
		}
		
		/**
		 *Probably not needed
		 */
		if(o1 instanceof BooleanType){
			return true;
		}
		
		if(o1 instanceof CaseListCase){
			CaseListCase b1 = (CaseListCase) o1;
			CaseListCase b2 = (CaseListCase) o2;
			
			return (boolean)visit(b1.id, b2.id) && (boolean)visit(b1.caseExp, b2.caseExp) && (boolean)visit(b1.s, b2.s) && (boolean)visit(b1.caseList, b2.caseList);
		}
		
		if(o1 instanceof CaseListDefault){
			CaseListDefault b1 = (CaseListDefault) o1;
			CaseListDefault b2 = (CaseListDefault) o2;
			
			return (boolean)visit(b1.s, b2.s) ;
		}
		
		
		if(o1 instanceof ClassDeclDeffExtend){
			ClassDeclDeffExtend b1 = (ClassDeclDeffExtend) o1;
			ClassDeclDeffExtend b2 = (ClassDeclDeffExtend) o2;
			
			return (boolean)visit(b1.className, b2.className) && (boolean)visit(b1.extendedClass, b2.extendedClass) && (boolean)visit(b1.variableList, b2.variableList) && (boolean)visit(b1.methodList, b2.methodList);
		}
		
		if(o1 instanceof ClassDeclDeffSimple){
			ClassDeclDeffSimple b1 = (ClassDeclDeffSimple) o1;
			ClassDeclDeffSimple b2 = (ClassDeclDeffSimple) o2;
			
			return (boolean)visit(b1.className, b2.className) && (boolean)visit(b1.fields, b2.fields) && (boolean)visit(b1.methods, b2.methods);
		}
		
		if(o1 instanceof ClassDeclList){
			ClassDeclList b1 = (ClassDeclList) o1;
			ClassDeclList b2 = (ClassDeclList) o2;
			
			if(b1.size() != b2.size()){
				return false;
			}
			
			for(int i = 0; i < b1.size(); i++){
				if(!(boolean)visit(b1.get(i), b2.get(i))){
					return false;
				}
			}
			
			return true;
		}
		
		if(o1 instanceof Do){
			Do b1 = (Do) o1;
			Do b2 = (Do) o2;
			
			return (boolean)visit(b1.s, b2.s) && (boolean)visit(b1.condition, b2.condition);
		}
		
		if(o1 instanceof DotArrayArray){
			DotArrayArray b1 = (DotArrayArray) o1;
			DotArrayArray b2 = (DotArrayArray) o2;
			
			return (boolean)visit(b1.exp, b2.exp);
		}
		
		if(o1 instanceof DotArrayList){
			DotArrayList b1 = (DotArrayList) o1;
			DotArrayList b2 = (DotArrayList) o2;
			
			if(b1.size() != b2.size()){
				return false;
			}
			
			for(int i = 0; i < b1.size(); i++){
				if(!(boolean)visit(b1.get(i), b2.get(i))){
					return false;
				}
			}
			
			return true;
		}
		
		if(o1 instanceof DotArrayMember){
			DotArrayMember b1 = (DotArrayMember) o1;
			DotArrayMember b2 = (DotArrayMember) o2;
			
			return (boolean)visit(b1.member, b2.member);
		}
		
		if(o1 instanceof Elist){
			Elist b1 = (Elist) o1;
			Elist b2 = (Elist) o2;
			
			return (boolean)visit(b1.and, b2.and) && (boolean)visit(b1.elist, b2.elist);
		}
		
		if(o1 instanceof ElseIf){
			ElseIf b1 = (ElseIf) o1;
			ElseIf b2 = (ElseIf) o2;
			
			return (boolean)visit(b1.condition, b2.condition) && (boolean)visit(b1.s, b2.s);
		}
		
		
		if(o1 instanceof ElseIfList){
			ElseIfList b1 = (ElseIfList) o1;
			ElseIfList b2 = (ElseIfList) o2;
			
			if(b1.size() != b2.size()){
				return false;
			}
			
			for(int i = 0; i < b1.size(); i++){
				if(!(boolean)visit(b1.get(i), b2.get(i))){
					return false;
				}
			}
			
			return true;
		}
		
		
		if(o1 instanceof Exp){
			Exp b1 = (Exp) o1;
			Exp b2 = (Exp) o2;
			
			return (boolean)visit(b1.and, b2.and) && (boolean)visit(b1.elist, b2.elist);
		}
		
		if(o1 instanceof ExpList){
			ExpList b1 = (ExpList) o1;
			ExpList b2 = (ExpList) o2;
			
			return (boolean)visit(b1.e, b2.e) && (boolean)visit(b1.multipleExp, b2.multipleExp);
		}
		
		if(o1 instanceof ExpRest){
			ExpRest b1 = (ExpRest) o1;
			ExpRest b2 = (ExpRest) o2;
			
			if(!(boolean)visit(b1.e, b2.e)){
				return false;
			}
			
			if(b1.size() != b2.size()){
				return false;
			}
			
			for(int i = 0; i < b1.size(); i++){
				if(!(boolean)visit(b1.get(i), b2.get(i))){
					return false;
				}
			}
			return true;
		}
		
		if(o1 instanceof ExpRestList){
			ExpRestList b1 = (ExpRestList) o1;
			ExpRestList b2 = (ExpRestList) o2;
			
			if(b1.size() != b2.size()){
				return false;
			}
			
			for(int i = 0; i < b1.size(); i++){
				if(!(boolean)visit(b1.get(i), b2.get(i))){
					return false;
				}
			}
			
			return true;
		}
		
		if(o1 instanceof FactorNew){
			FactorNew b1 = (FactorNew) o1;
			FactorNew b2 = (FactorNew) o2;
			
			return (boolean)visit(b1.newObject, b2.newObject);
		}
		
		if(o1 instanceof False){
			return true;
		}
		
		if(o1 instanceof For){
			For b1 = (For) o1;
			For b2 = (For) o2;
			
			return (boolean)visit(b1.initialize, b2.initialize) && (boolean)visit(b1.e, b2.e) && (boolean)visit(b1.increment, b2.increment) && (boolean)visit(b1.s, b2.s);
		}
		
		if(o1 instanceof FormalList){
			FormalList b1 = (FormalList) o1;
			FormalList b2 = (FormalList) o2;
			
			return (boolean)visit(b1.type, b2.type) && (boolean)visit(b1.parameterName, b2.parameterName) && (boolean)visit(b1.moreParams, b2.moreParams);
		}
		
		if(o1 instanceof FormalRest){
			FormalRest b1 = (FormalRest) o1;
			FormalRest b2 = (FormalRest) o2;
			
			if(!((boolean)visit(b1.type, b2.type) && (boolean)visit(b1.paramName, b2.paramName))){
				return false;
			}
			
			if(b1.size() != b2.size()){
				return false;
			}
			
			for(int i = 0; i < b1.size(); i++){
				if(!(boolean)visit(b1.get(i), b2.get(i))){
					return false;
				}
			}
			
			return true;
		}
		
		if(o1 instanceof FormalRestList){
			FormalRestList b1 = (FormalRestList) o1;
			FormalRestList b2 = (FormalRestList) o2;
			
			if(b1.size() != b2.size()){
				return false;
			}
			
			for(int i = 0; i < b1.size(); i++){
				if(!(boolean)visit(b1.get(i), b2.get(i))){
					return false;
				}
			}
			
			return true;
		}
		
		if(o1 instanceof Identifier){
			Identifier b1 = (Identifier) o1;
			Identifier b2 = (Identifier) o2;
			return b1.name.equals(b2.name);
		}
		
		if(o1 instanceof IdentifierExp){
			IdentifierExp b1 = (IdentifierExp) o1;
			IdentifierExp b2 = (IdentifierExp) o2;
			return b1.s.equals(b2.s);
		}
		
		
		if(o1 instanceof If){
			If b1 = (If) o1;
			If b2 = (If) o2;
			
			return (boolean)visit(b1.condition, b2.condition) && (boolean)visit(b1.s, b2.s) && (boolean)visit(b1.elseIf, b2.elseIf);
		}
		
		
		if(o1 instanceof IncrementArray){
			IncrementArray b1 = (IncrementArray) o1;
			IncrementArray b2 = (IncrementArray) o2;
			
			return (boolean)visit(b1.id, b2.id) && (boolean)visit(b1.arrayExp, b2.arrayExp) && (boolean)visit(b1.assignExp, b2.assignExp);
		}
		
		
		if(o1 instanceof IncrementSimple){
			IncrementSimple b1 = (IncrementSimple) o1;
			IncrementSimple b2 = (IncrementSimple) o2;
			
			return (boolean)visit(b1.id, b2.id) && (boolean)visit(b1.assignExp, b2.assignExp);
		}
		
		if(o1 instanceof InitializeArray){
			InitializeArray b1 = (InitializeArray) o1;
			InitializeArray b2 = (InitializeArray) o2;
			
			return (boolean)visit(b1.id, b2.id) && (boolean)visit(b1.assignExp, b2.assignExp) && (boolean)visit(b1.arrayExp, b2.arrayExp);
		}
		
		if(o1 instanceof InitializeSimple){
			InitializeSimple b1 = (InitializeSimple) o1;
			InitializeSimple b2 = (InitializeSimple) o2;
			
			return (boolean)visit(b1.id, b2.id) && (boolean)visit(b1.assignExp, b2.assignExp);
		}
		
		if(o1 instanceof IntArrayType){
			return true;
		}
		
		if(o1 instanceof IntegerLiteral){
			IntegerLiteral b1 = (IntegerLiteral) o1;
			IntegerLiteral b2 = (IntegerLiteral) o2;
			return b1.i == b2.i;
		}
		
		if(o1 instanceof IntegerType){
			return true;
		}
		
		if(o1 instanceof Less){
			Less b1 = (Less) o1;
			Less b2 = (Less) o2;
			
			return (boolean)visit(b1.term, b2.term) && (boolean)visit(b1.llist, b2.llist);
		}
		
		if(o1 instanceof Llist){
			Llist b1 = (Llist) o1;
			Llist b2 = (Llist) o2;
			
			return (boolean)visit(b1.l, b2.l) && (boolean)visit(b1.t, b2.t);
		}
		
		if(o1 instanceof LlistDifference){
			LlistDifference b1 = (LlistDifference) o1;
			LlistDifference b2 = (LlistDifference) o2;
			
			return (boolean)visit(b1.llist, b2.llist) && (boolean)visit(b1.term, b2.term);
		}
		
		if(o1 instanceof LlistSum){
			LlistSum b1 = (LlistSum) o1;
			LlistSum b2 = (LlistSum) o2;
			
			return (boolean)visit(b1.llist, b2.llist) && (boolean)visit(b1.term, b2.term);
		}
		
		if(o1 instanceof MainClass){
			MainClass b1 = (MainClass) o1;
			MainClass b2 = (MainClass) o2;
			
			return (boolean)visit(b1.className, b2.className) && (boolean)visit(b1.args, b2.args) && (boolean)visit(b1.v, b2.v) && (boolean)visit(b1.stmt, b2.stmt);
		}
		
		if(o1 instanceof MemberId){
			MemberId b1 = (MemberId) o1;
			MemberId b2 = (MemberId) o2;
			
			return (boolean)visit(b1.id, b2.id) && (boolean)visit(b1.expList, b2.expList);
		}
		
		if(o1 instanceof MemberLength){
			MemberLength b1 = (MemberLength) o1;
			MemberLength b2 = (MemberLength) o2;
			
			return (boolean)visit(b1.length, b2.length);
		}
		
		if(o1 instanceof MethodDecl){
			MethodDecl b1 = (MethodDecl) o1;
			MethodDecl b2 = (MethodDecl) o2;
			
			return (boolean)visit(b1.type, b2.type) && (boolean)visit(b1.methodName, b2.methodName) && (boolean)visit(b1.parameters, b2.parameters) && (boolean)visit(b1.variables, b2.variables) && (boolean)visit(b1.statement, b2.statement) && (boolean)visit(b1.expReturn, b2.expReturn);
		}
		
		if(o1 instanceof MethodDeclList){
			MethodDeclList b1 = (MethodDeclList) o1;
			MethodDeclList b2 = (MethodDeclList) o2;
			
			if(b1.size() != b2.size()){
				return false;
			}
			
			for(int i = 0; i < b1.size(); i++){
				if(!(boolean)visit(b1.get(i), b2.get(i))){
					return false;
				}
			}
			
			return true;
		}
		
		if(o1 instanceof NewArray){
			NewArray b1 = (NewArray) o1;
			NewArray b2 = (NewArray) o2;
			
			return (boolean)visit(b1.e, b2.e);
		}
		
		if(o1 instanceof NewObject){
			NewObject b1 = (NewObject) o1;
			NewObject b2 = (NewObject) o2;
			
			return (boolean)visit(b1.id, b2.id);
		}
		
		
		if(o1 instanceof NotFactor){
			NotFactor b1 = (NotFactor) o1;
			NotFactor b2 = (NotFactor) o2;
			
			return (boolean)visit(b1.factor, b2.factor) && (boolean)visit(b1.dotList, b2.dotList);
		}
		
		if(o1 instanceof NotSimple){
			NotSimple b1 = (NotSimple) o1;
			NotSimple b2 = (NotSimple) o2;
			
			return (boolean)visit(b1.not, b2.not);
		}
		
		if(o1 instanceof Print){
			Print b1 = (Print) o1;
			Print b2 = (Print) o2;
			
			return (boolean)visit(b1.statementToPrint, b2.statementToPrint);
		}
		
		if(o1 instanceof Program){
			Program b1 = (Program) o1;
			Program b2 = (Program) o2;
			
			return (boolean)visit(b1.mainClass, b2.mainClass) && (boolean)visit(b1.classDecls, b2.classDecls);
		}
		
		if(o1 instanceof StatementList){
			StatementList b1 = (StatementList) o1;
			StatementList b2 = (StatementList) o2;
			
			if(b1.size() != b2.size()){
				return false;
			}
			
			for(int i = 0; i < b1.size(); i++){
				if(!(boolean)visit(b1.get(i), b2.get(i))){
					return false;
				}
			}
			
			return true;
		}
		
		if(o1 instanceof Switch){
			Switch b1 = (Switch) o1;
			Switch b2 = (Switch) o2;
			
			return (boolean)visit(b1.id, b2.id) && (boolean)visit(b1.caseDefault, b2.caseDefault);
		}

		if(o1 instanceof Term){
			Term b1 = (Term) o1;
			Term b2 = (Term) o2;
			
			return (boolean)visit(b1.not, b2.not) && (boolean)visit(b1.tlist, b2.tlist);
		}
		
		if(o1 instanceof This){
			return true;
		}
		
		if(o1 instanceof Tlist){
			Tlist b1 = (Tlist) o1;
			Tlist b2 = (Tlist) o2;
			
			return (boolean)visit(b1.not, b2.not) && (boolean)visit(b1.tlist, b2.tlist);
		}
		
		if(o1 instanceof True){
			return true;
		}
		
		if(o1 instanceof VarDecl){
			VarDecl b1 = (VarDecl) o1;
			VarDecl b2 = (VarDecl) o2;
			
			return (boolean)visit(b1.type, b2.type) && (boolean)visit(b1.variableType, b2.variableType);
		}
		
		if(o1 instanceof VarDeclList){
			VarDeclList b1 = (VarDeclList) o1;
			VarDeclList b2 = (VarDeclList) o2;
			
			if(b1.size() != b2.size()){
				return false;
			}
			
			for(int i = 0; i < b1.size(); i++){
				if(!(boolean)visit(b1.get(i), b2.get(i))){
					return false;
				}
			}
			
			return true;
		}
		
		if(o1 instanceof VarDeclType){
			VarDeclType b1 = (VarDeclType) o1;
			VarDeclType b2 = (VarDeclType) o2;
			
			return (boolean)visit(b1.variableName, b2.variableName) && (boolean)visit(b1.exp, b2.exp);
		}
		

		if(o1 instanceof While){
			While b1 = (While) o1;
			While b2 = (While) o2;
			
			return (boolean)visit(b1.condition, b2.condition) && (boolean)visit(b1.s, b2.s);
		}
		
		//If the object was not recognized at all
		System.err.println("Could not check equality on object " + o1.toString() + " " + o2.toString() + " types" + o1.getClass().getName());
		try{
			Thread.sleep(10);
		}
		catch(InterruptedException e){
			//do nothing
		}
		return false;
	}

}
