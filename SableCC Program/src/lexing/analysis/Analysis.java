/* This file was generated by SableCC (http://www.sablecc.org/). */

package lexing.analysis;

import lexing.node.*;

public interface Analysis extends Switch
{
    Object getIn(Node node);
    void setIn(Node node, Object o);
    Object getOut(Node node);
    void setOut(Node node, Object o);

    void caseStart(Start node);
    void caseAProgram(AProgram node);
    void caseAMainclass(AMainclass node);
    void caseAClassdecl(AClassdecl node);
    void caseAClassdeclspec(AClassdeclspec node);
    void caseANoExtendsClassdecldeff(ANoExtendsClassdecldeff node);
    void caseAExtendsClassdecldeff(AExtendsClassdecldeff node);
    void caseAVardecl(AVardecl node);
    void caseAVardecltype(AVardecltype node);
    void caseAAssignVardecltypeassign(AAssignVardecltypeassign node);
    void caseAEpsilonVardecltypeassign(AEpsilonVardecltypeassign node);
    void caseAMultidecl(AMultidecl node);
    void caseAMethoddecl(AMethoddecl node);
    void caseAFormalrestFormallist(AFormalrestFormallist node);
    void caseAEpsilonFormallist(AEpsilonFormallist node);
    void caseAFormalrest(AFormalrest node);
    void caseAArrayType(AArrayType node);
    void caseABooleanType(ABooleanType node);
    void caseAIdentifierType(AIdentifierType node);
    void caseAArrayInttype(AArrayInttype node);
    void caseAEpsilonInttype(AEpsilonInttype node);
    void caseADefaultStatement(ADefaultStatement node);
    void caseAIfStatement(AIfStatement node);
    void caseADoStatement(ADoStatement node);
    void caseAWhileStatement(AWhileStatement node);
    void caseAForStatement(AForStatement node);
    void caseASwitchStatement(ASwitchStatement node);
    void caseAPrintlnStatement(APrintlnStatement node);
    void caseAAssignStatement(AAssignStatement node);
    void caseAFormalvarexpStatement(AFormalvarexpStatement node);
    void caseAAssignAssignNonterminal(AAssignAssignNonterminal node);
    void caseAArrayAssignNonterminal(AArrayAssignNonterminal node);
    void caseAInitializeInitializationstm(AInitializeInitializationstm node);
    void caseAAssignInitializationstm(AAssignInitializationstm node);
    void caseAArrayInitializationstm(AArrayInitializationstm node);
    void caseAAssignIncrementstm(AAssignIncrementstm node);
    void caseAArrayIncrementstm(AArrayIncrementstm node);
    void caseAElseifNonterminal(AElseifNonterminal node);
    void caseAFormalvarexp(AFormalvarexp node);
    void caseACaseCaselist(ACaseCaselist node);
    void caseADefaultCaselist(ADefaultCaselist node);
    void caseAExp(AExp node);
    void caseAAndElist(AAndElist node);
    void caseAEpsilonElist(AEpsilonElist node);
    void caseAAndNonterminal(AAndNonterminal node);
    void caseALessAlist(ALessAlist node);
    void caseAEpsilonAlist(AEpsilonAlist node);
    void caseALess(ALess node);
    void caseAPlusLlist(APlusLlist node);
    void caseAMinusLlist(AMinusLlist node);
    void caseAEpsilonLlist(AEpsilonLlist node);
    void caseANotNonterminalTerm(ANotNonterminalTerm node);
    void caseAMultTlist(AMultTlist node);
    void caseAEpsilonTlist(AEpsilonTlist node);
    void caseANotNotNonterminal(ANotNotNonterminal node);
    void caseAFactorNotNonterminal(AFactorNotNonterminal node);
    void caseADotDotarray(ADotDotarray node);
    void caseAArrayDotarray(AArrayDotarray node);
    void caseALengthMember(ALengthMember node);
    void caseAIdentifierMember(AIdentifierMember node);
    void caseAExpExplist(AExpExplist node);
    void caseAEpsilonExplist(AEpsilonExplist node);
    void caseAExprest(AExprest node);
    void caseANumFactor(ANumFactor node);
    void caseATrueFactor(ATrueFactor node);
    void caseAFalseFactor(AFalseFactor node);
    void caseAIdentifierFactor(AIdentifierFactor node);
    void caseAThisFactor(AThisFactor node);
    void caseANewFactor(ANewFactor node);
    void caseAExpFactor(AExpFactor node);
    void caseAArrayNewNonterminal(AArrayNewNonterminal node);
    void caseAIdentifierNewNonterminal(AIdentifierNewNonterminal node);

    void caseTDoubleSlashComment(TDoubleSlashComment node);
    void caseTSpace(TSpace node);
    void caseTClas(TClas node);
    void caseTPublic(TPublic node);
    void caseTStatic(TStatic node);
    void caseTVoid(TVoid node);
    void caseTMain(TMain node);
    void caseTString(TString node);
    void caseTExtends(TExtends node);
    void caseTReturn(TReturn node);
    void caseTInt(TInt node);
    void caseTPrintln(TPrintln node);
    void caseTBoolean(TBoolean node);
    void caseTIf(TIf node);
    void caseTDo(TDo node);
    void caseTWhile(TWhile node);
    void caseTFor(TFor node);
    void caseTSwitch(TSwitch node);
    void caseTElseif(TElseif node);
    void caseTCase(TCase node);
    void caseTDefault(TDefault node);
    void caseTTrue(TTrue node);
    void caseTFalse(TFalse node);
    void caseTNew(TNew node);
    void caseTThis(TThis node);
    void caseTLength(TLength node);
    void caseTEndIf(TEndIf node);
    void caseTAssign(TAssign node);
    void caseTLessThan(TLessThan node);
    void caseTCompare(TCompare node);
    void caseTAnd(TAnd node);
    void caseTOr(TOr node);
    void caseTNot(TNot node);
    void caseTPlus(TPlus node);
    void caseTMinus(TMinus node);
    void caseTMult(TMult node);
    void caseTDivide(TDivide node);
    void caseTMod(TMod node);
    void caseTLeftParen(TLeftParen node);
    void caseTRightParen(TRightParen node);
    void caseTLeftBracket(TLeftBracket node);
    void caseTRightBracket(TRightBracket node);
    void caseTLeftBrace(TLeftBrace node);
    void caseTRightBrace(TRightBrace node);
    void caseTLeftQuote(TLeftQuote node);
    void caseTRightQuote(TRightQuote node);
    void caseTDot(TDot node);
    void caseTComma(TComma node);
    void caseTColon(TColon node);
    void caseTSemi(TSemi node);
    void caseTIdentifier(TIdentifier node);
    void caseTIntNum(TIntNum node);
    void caseTMisc(TMisc node);
    void caseEOF(EOF node);
}
