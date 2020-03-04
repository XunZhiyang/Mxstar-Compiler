// Generated from Mxstar.g4 by ANTLR 4.8

package Parser;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MxstarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, True=42, False=43, This=44, Null=45, Bool=46, 
		Int=47, Void=48, String=49, While=50, For=51, IntegerLiteral=52, StringLiteral=53, 
		Identifier=54, Whitespace=55, Newline=56, BlockComment=57, LineComment=58;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "T__12", "T__13", "T__14", "T__15", "T__16", 
			"T__17", "T__18", "T__19", "T__20", "T__21", "T__22", "T__23", "T__24", 
			"T__25", "T__26", "T__27", "T__28", "T__29", "T__30", "T__31", "T__32", 
			"T__33", "T__34", "T__35", "T__36", "T__37", "T__38", "T__39", "T__40", 
			"True", "False", "This", "Null", "Bool", "Int", "Void", "String", "While", 
			"For", "IntegerLiteral", "StringLiteral", "SChar", "EscapeSequence", 
			"Identifier", "Letter", "Digit", "Whitespace", "Newline", "BlockComment", 
			"LineComment"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'class'", "'{'", "'}'", "';'", "'('", "')'", "'='", "','", "'if'", 
			"'else'", "'return'", "'break'", "'continue'", "'.'", "'new'", "'['", 
			"']'", "'++'", "'--'", "'+'", "'-'", "'~'", "'!'", "'*'", "'/'", "'%'", 
			"'<<'", "'>>'", "'<'", "'>'", "'<='", "'>='", "'=='", "'!='", "'&'", 
			"'^'", "'|'", "'&&'", "'||'", "'?'", "':'", "'true'", "'false'", "'this'", 
			"'null'", "'bool'", "'int'", "'void'", "'string'", "'while'", "'for'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, "True", "False", "This", "Null", 
			"Bool", "Int", "Void", "String", "While", "For", "IntegerLiteral", "StringLiteral", 
			"Identifier", "Whitespace", "Newline", "BlockComment", "LineComment"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public MxstarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Mxstar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2<\u0174\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\4"+
		",\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62\4\63\t\63\4\64\t"+
		"\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4;\t;\4<\t<\4=\t="+
		"\4>\t>\4?\t?\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3"+
		"\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\20\3\21\3\21\3\22\3\22\3"+
		"\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\26\3\26\3\27\3\27\3\30\3\30\3"+
		"\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\34\3\35\3\35\3\35\3\36\3\36\3"+
		"\37\3\37\3 \3 \3 \3!\3!\3!\3\"\3\"\3\"\3#\3#\3#\3$\3$\3%\3%\3&\3&\3\'"+
		"\3\'\3\'\3(\3(\3(\3)\3)\3*\3*\3+\3+\3+\3+\3+\3,\3,\3,\3,\3,\3,\3-\3-\3"+
		"-\3-\3-\3.\3.\3.\3.\3.\3/\3/\3/\3/\3/\3\60\3\60\3\60\3\60\3\61\3\61\3"+
		"\61\3\61\3\61\3\62\3\62\3\62\3\62\3\62\3\62\3\62\3\63\3\63\3\63\3\63\3"+
		"\63\3\63\3\64\3\64\3\64\3\64\3\65\6\65\u012b\n\65\r\65\16\65\u012c\3\66"+
		"\3\66\7\66\u0131\n\66\f\66\16\66\u0134\13\66\3\66\3\66\3\67\3\67\5\67"+
		"\u013a\n\67\38\38\38\39\39\39\39\79\u0143\n9\f9\169\u0146\139\3:\3:\3"+
		";\3;\3<\6<\u014d\n<\r<\16<\u014e\3<\3<\3=\3=\5=\u0155\n=\3=\5=\u0158\n"+
		"=\3=\3=\3>\3>\3>\3>\7>\u0160\n>\f>\16>\u0163\13>\3>\3>\3>\3>\3>\3?\3?"+
		"\3?\3?\7?\u016e\n?\f?\16?\u0171\13?\3?\3?\3\u0161\2@\3\3\5\4\7\5\t\6\13"+
		"\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'"+
		"\25)\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'"+
		"M(O)Q*S+U,W-Y.[/]\60_\61a\62c\63e\64g\65i\66k\67m\2o\2q8s\2u\2w9y:{;}"+
		"<\3\2\b\3\2\62;\6\2\f\f\17\17$$^^\5\2$$^^pp\4\2C\\c|\4\2\13\13\"\"\4\2"+
		"\f\f\17\17\2\u017a\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2"+
		"!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3"+
		"\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2"+
		"\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E"+
		"\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2"+
		"\2\2\2S\3\2\2\2\2U\3\2\2\2\2W\3\2\2\2\2Y\3\2\2\2\2[\3\2\2\2\2]\3\2\2\2"+
		"\2_\3\2\2\2\2a\3\2\2\2\2c\3\2\2\2\2e\3\2\2\2\2g\3\2\2\2\2i\3\2\2\2\2k"+
		"\3\2\2\2\2q\3\2\2\2\2w\3\2\2\2\2y\3\2\2\2\2{\3\2\2\2\2}\3\2\2\2\3\177"+
		"\3\2\2\2\5\u0085\3\2\2\2\7\u0087\3\2\2\2\t\u0089\3\2\2\2\13\u008b\3\2"+
		"\2\2\r\u008d\3\2\2\2\17\u008f\3\2\2\2\21\u0091\3\2\2\2\23\u0093\3\2\2"+
		"\2\25\u0096\3\2\2\2\27\u009b\3\2\2\2\31\u00a2\3\2\2\2\33\u00a8\3\2\2\2"+
		"\35\u00b1\3\2\2\2\37\u00b3\3\2\2\2!\u00b7\3\2\2\2#\u00b9\3\2\2\2%\u00bb"+
		"\3\2\2\2\'\u00be\3\2\2\2)\u00c1\3\2\2\2+\u00c3\3\2\2\2-\u00c5\3\2\2\2"+
		"/\u00c7\3\2\2\2\61\u00c9\3\2\2\2\63\u00cb\3\2\2\2\65\u00cd\3\2\2\2\67"+
		"\u00cf\3\2\2\29\u00d2\3\2\2\2;\u00d5\3\2\2\2=\u00d7\3\2\2\2?\u00d9\3\2"+
		"\2\2A\u00dc\3\2\2\2C\u00df\3\2\2\2E\u00e2\3\2\2\2G\u00e5\3\2\2\2I\u00e7"+
		"\3\2\2\2K\u00e9\3\2\2\2M\u00eb\3\2\2\2O\u00ee\3\2\2\2Q\u00f1\3\2\2\2S"+
		"\u00f3\3\2\2\2U\u00f5\3\2\2\2W\u00fa\3\2\2\2Y\u0100\3\2\2\2[\u0105\3\2"+
		"\2\2]\u010a\3\2\2\2_\u010f\3\2\2\2a\u0113\3\2\2\2c\u0118\3\2\2\2e\u011f"+
		"\3\2\2\2g\u0125\3\2\2\2i\u012a\3\2\2\2k\u012e\3\2\2\2m\u0139\3\2\2\2o"+
		"\u013b\3\2\2\2q\u013e\3\2\2\2s\u0147\3\2\2\2u\u0149\3\2\2\2w\u014c\3\2"+
		"\2\2y\u0157\3\2\2\2{\u015b\3\2\2\2}\u0169\3\2\2\2\177\u0080\7e\2\2\u0080"+
		"\u0081\7n\2\2\u0081\u0082\7c\2\2\u0082\u0083\7u\2\2\u0083\u0084\7u\2\2"+
		"\u0084\4\3\2\2\2\u0085\u0086\7}\2\2\u0086\6\3\2\2\2\u0087\u0088\7\177"+
		"\2\2\u0088\b\3\2\2\2\u0089\u008a\7=\2\2\u008a\n\3\2\2\2\u008b\u008c\7"+
		"*\2\2\u008c\f\3\2\2\2\u008d\u008e\7+\2\2\u008e\16\3\2\2\2\u008f\u0090"+
		"\7?\2\2\u0090\20\3\2\2\2\u0091\u0092\7.\2\2\u0092\22\3\2\2\2\u0093\u0094"+
		"\7k\2\2\u0094\u0095\7h\2\2\u0095\24\3\2\2\2\u0096\u0097\7g\2\2\u0097\u0098"+
		"\7n\2\2\u0098\u0099\7u\2\2\u0099\u009a\7g\2\2\u009a\26\3\2\2\2\u009b\u009c"+
		"\7t\2\2\u009c\u009d\7g\2\2\u009d\u009e\7v\2\2\u009e\u009f\7w\2\2\u009f"+
		"\u00a0\7t\2\2\u00a0\u00a1\7p\2\2\u00a1\30\3\2\2\2\u00a2\u00a3\7d\2\2\u00a3"+
		"\u00a4\7t\2\2\u00a4\u00a5\7g\2\2\u00a5\u00a6\7c\2\2\u00a6\u00a7\7m\2\2"+
		"\u00a7\32\3\2\2\2\u00a8\u00a9\7e\2\2\u00a9\u00aa\7q\2\2\u00aa\u00ab\7"+
		"p\2\2\u00ab\u00ac\7v\2\2\u00ac\u00ad\7k\2\2\u00ad\u00ae\7p\2\2\u00ae\u00af"+
		"\7w\2\2\u00af\u00b0\7g\2\2\u00b0\34\3\2\2\2\u00b1\u00b2\7\60\2\2\u00b2"+
		"\36\3\2\2\2\u00b3\u00b4\7p\2\2\u00b4\u00b5\7g\2\2\u00b5\u00b6\7y\2\2\u00b6"+
		" \3\2\2\2\u00b7\u00b8\7]\2\2\u00b8\"\3\2\2\2\u00b9\u00ba\7_\2\2\u00ba"+
		"$\3\2\2\2\u00bb\u00bc\7-\2\2\u00bc\u00bd\7-\2\2\u00bd&\3\2\2\2\u00be\u00bf"+
		"\7/\2\2\u00bf\u00c0\7/\2\2\u00c0(\3\2\2\2\u00c1\u00c2\7-\2\2\u00c2*\3"+
		"\2\2\2\u00c3\u00c4\7/\2\2\u00c4,\3\2\2\2\u00c5\u00c6\7\u0080\2\2\u00c6"+
		".\3\2\2\2\u00c7\u00c8\7#\2\2\u00c8\60\3\2\2\2\u00c9\u00ca\7,\2\2\u00ca"+
		"\62\3\2\2\2\u00cb\u00cc\7\61\2\2\u00cc\64\3\2\2\2\u00cd\u00ce\7\'\2\2"+
		"\u00ce\66\3\2\2\2\u00cf\u00d0\7>\2\2\u00d0\u00d1\7>\2\2\u00d18\3\2\2\2"+
		"\u00d2\u00d3\7@\2\2\u00d3\u00d4\7@\2\2\u00d4:\3\2\2\2\u00d5\u00d6\7>\2"+
		"\2\u00d6<\3\2\2\2\u00d7\u00d8\7@\2\2\u00d8>\3\2\2\2\u00d9\u00da\7>\2\2"+
		"\u00da\u00db\7?\2\2\u00db@\3\2\2\2\u00dc\u00dd\7@\2\2\u00dd\u00de\7?\2"+
		"\2\u00deB\3\2\2\2\u00df\u00e0\7?\2\2\u00e0\u00e1\7?\2\2\u00e1D\3\2\2\2"+
		"\u00e2\u00e3\7#\2\2\u00e3\u00e4\7?\2\2\u00e4F\3\2\2\2\u00e5\u00e6\7(\2"+
		"\2\u00e6H\3\2\2\2\u00e7\u00e8\7`\2\2\u00e8J\3\2\2\2\u00e9\u00ea\7~\2\2"+
		"\u00eaL\3\2\2\2\u00eb\u00ec\7(\2\2\u00ec\u00ed\7(\2\2\u00edN\3\2\2\2\u00ee"+
		"\u00ef\7~\2\2\u00ef\u00f0\7~\2\2\u00f0P\3\2\2\2\u00f1\u00f2\7A\2\2\u00f2"+
		"R\3\2\2\2\u00f3\u00f4\7<\2\2\u00f4T\3\2\2\2\u00f5\u00f6\7v\2\2\u00f6\u00f7"+
		"\7t\2\2\u00f7\u00f8\7w\2\2\u00f8\u00f9\7g\2\2\u00f9V\3\2\2\2\u00fa\u00fb"+
		"\7h\2\2\u00fb\u00fc\7c\2\2\u00fc\u00fd\7n\2\2\u00fd\u00fe\7u\2\2\u00fe"+
		"\u00ff\7g\2\2\u00ffX\3\2\2\2\u0100\u0101\7v\2\2\u0101\u0102\7j\2\2\u0102"+
		"\u0103\7k\2\2\u0103\u0104\7u\2\2\u0104Z\3\2\2\2\u0105\u0106\7p\2\2\u0106"+
		"\u0107\7w\2\2\u0107\u0108\7n\2\2\u0108\u0109\7n\2\2\u0109\\\3\2\2\2\u010a"+
		"\u010b\7d\2\2\u010b\u010c\7q\2\2\u010c\u010d\7q\2\2\u010d\u010e\7n\2\2"+
		"\u010e^\3\2\2\2\u010f\u0110\7k\2\2\u0110\u0111\7p\2\2\u0111\u0112\7v\2"+
		"\2\u0112`\3\2\2\2\u0113\u0114\7x\2\2\u0114\u0115\7q\2\2\u0115\u0116\7"+
		"k\2\2\u0116\u0117\7f\2\2\u0117b\3\2\2\2\u0118\u0119\7u\2\2\u0119\u011a"+
		"\7v\2\2\u011a\u011b\7t\2\2\u011b\u011c\7k\2\2\u011c\u011d\7p\2\2\u011d"+
		"\u011e\7i\2\2\u011ed\3\2\2\2\u011f\u0120\7y\2\2\u0120\u0121\7j\2\2\u0121"+
		"\u0122\7k\2\2\u0122\u0123\7n\2\2\u0123\u0124\7g\2\2\u0124f\3\2\2\2\u0125"+
		"\u0126\7h\2\2\u0126\u0127\7q\2\2\u0127\u0128\7t\2\2\u0128h\3\2\2\2\u0129"+
		"\u012b\t\2\2\2\u012a\u0129\3\2\2\2\u012b\u012c\3\2\2\2\u012c\u012a\3\2"+
		"\2\2\u012c\u012d\3\2\2\2\u012dj\3\2\2\2\u012e\u0132\7$\2\2\u012f\u0131"+
		"\5m\67\2\u0130\u012f\3\2\2\2\u0131\u0134\3\2\2\2\u0132\u0130\3\2\2\2\u0132"+
		"\u0133\3\2\2\2\u0133\u0135\3\2\2\2\u0134\u0132\3\2\2\2\u0135\u0136\7$"+
		"\2\2\u0136l\3\2\2\2\u0137\u013a\n\3\2\2\u0138\u013a\5o8\2\u0139\u0137"+
		"\3\2\2\2\u0139\u0138\3\2\2\2\u013an\3\2\2\2\u013b\u013c\7^\2\2\u013c\u013d"+
		"\t\4\2\2\u013dp\3\2\2\2\u013e\u0144\5s:\2\u013f\u0143\5s:\2\u0140\u0143"+
		"\5u;\2\u0141\u0143\7a\2\2\u0142\u013f\3\2\2\2\u0142\u0140\3\2\2\2\u0142"+
		"\u0141\3\2\2\2\u0143\u0146\3\2\2\2\u0144\u0142\3\2\2\2\u0144\u0145\3\2"+
		"\2\2\u0145r\3\2\2\2\u0146\u0144\3\2\2\2\u0147\u0148\t\5\2\2\u0148t\3\2"+
		"\2\2\u0149\u014a\t\2\2\2\u014av\3\2\2\2\u014b\u014d\t\6\2\2\u014c\u014b"+
		"\3\2\2\2\u014d\u014e\3\2\2\2\u014e\u014c\3\2\2\2\u014e\u014f\3\2\2\2\u014f"+
		"\u0150\3\2\2\2\u0150\u0151\b<\2\2\u0151x\3\2\2\2\u0152\u0154\7\17\2\2"+
		"\u0153\u0155\7\f\2\2\u0154\u0153\3\2\2\2\u0154\u0155\3\2\2\2\u0155\u0158"+
		"\3\2\2\2\u0156\u0158\7\f\2\2\u0157\u0152\3\2\2\2\u0157\u0156\3\2\2\2\u0158"+
		"\u0159\3\2\2\2\u0159\u015a\b=\2\2\u015az\3\2\2\2\u015b\u015c\7\61\2\2"+
		"\u015c\u015d\7,\2\2\u015d\u0161\3\2\2\2\u015e\u0160\13\2\2\2\u015f\u015e"+
		"\3\2\2\2\u0160\u0163\3\2\2\2\u0161\u0162\3\2\2\2\u0161\u015f\3\2\2\2\u0162"+
		"\u0164\3\2\2\2\u0163\u0161\3\2\2\2\u0164\u0165\7,\2\2\u0165\u0166\7\61"+
		"\2\2\u0166\u0167\3\2\2\2\u0167\u0168\b>\2\2\u0168|\3\2\2\2\u0169\u016a"+
		"\7\61\2\2\u016a\u016b\7\61\2\2\u016b\u016f\3\2\2\2\u016c\u016e\n\7\2\2"+
		"\u016d\u016c\3\2\2\2\u016e\u0171\3\2\2\2\u016f\u016d\3\2\2\2\u016f\u0170"+
		"\3\2\2\2\u0170\u0172\3\2\2\2\u0171\u016f\3\2\2\2\u0172\u0173\b?\2\2\u0173"+
		"~\3\2\2\2\r\2\u012c\u0132\u0139\u0142\u0144\u014e\u0154\u0157\u0161\u016f"+
		"\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}