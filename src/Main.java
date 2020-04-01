import AST.ASTNode;
import Frontend.ASTBuilder;
import Frontend.ClassScanner;
import Frontend.FunctionScanner;
import Frontend.SymbolTableBuilder;
import Parser.MxstarErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.InputStream;

import Parser.MxstarLexer;
import Parser.MxstarParser;

import Symbol.GlobalScope;

public class Main {
    private static CharStream readCode() throws Exception{
//        String inputFile = "code.mx";
        String inputFile = "test\\test3.m";
        InputStream is = new FileInputStream(inputFile);
        return CharStreams.fromStream(is);
    }

    private static ParseTree buildCST(CharStream input) {
        MxstarLexer lexer = new MxstarLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MxstarParser parser = new MxstarParser(tokens);
        parser.addErrorListener(new MxstarErrorListener());
//        ParseTree tree = parser.program();
//        System.out.println(tree.toStringTree(parser));
        return parser.program();
    }

    private static void semanticAnalysis(ASTNode ast) {
        GlobalScope globalScope = new GlobalScope();
        ast.accept(new ClassScanner(globalScope));
        ast.accept(new FunctionScanner(globalScope));
        ast.accept(new SymbolTableBuilder(globalScope));
    }

    private static ASTNode buildAST(ParseTree tree) {
        ASTBuilder builder = new ASTBuilder();
        return builder.visit(tree);
    }

    public static void main(String[] args) {
        try{
            CharStream input = readCode();
            ParseTree tree = buildCST(input);
            ASTNode ast = buildAST(tree);
            semanticAnalysis(ast);

        } catch (Exception e) {
            e.printStackTrace();
            java.lang.System.exit(1);
//            System.err.println(e.getMessage());
        }
    }
}
