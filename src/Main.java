import AST.ASTNode;
import Backend.IRBuilder;
import Frontend.ASTBuilder;
import Frontend.ClassScanner;
import Frontend.FunctionScanner;
import Frontend.SemanticAnalyzer;
import IR.Module;
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
        String inputFile = "code.mx";
//        String inputFile = "test\\test3.m";
        InputStream is = new FileInputStream(inputFile);
        return CharStreams.fromStream(is);
    }

    private static ParseTree buildCST(CharStream input) {
        MxstarLexer lexer = new MxstarLexer(input);
        lexer.addErrorListener(new MxstarErrorListener());
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MxstarParser parser = new MxstarParser(tokens);
        parser.addErrorListener(new MxstarErrorListener());
        return parser.program();
    }

    private static GlobalScope analyzeSemantics(ASTNode ast) {
        GlobalScope globalScope = new GlobalScope();
        ast.accept(new ClassScanner(globalScope));
        ast.accept(new FunctionScanner(globalScope));
        ast.accept(new SemanticAnalyzer(globalScope));
        return globalScope;
    }

    private static ASTNode buildAST(ParseTree tree) {
        ASTBuilder builder = new ASTBuilder();
        return builder.visit(tree);
    }

    private static Module buildIR(GlobalScope globalScope) {
        Module module = new IRBuilder(globalScope).getModule();
        return module;
    }

    public static void main(String[] args) {
        try{
            CharStream input = readCode();
            ParseTree tree = buildCST(input);
            ASTNode ast = buildAST(tree);
            GlobalScope globalScope = analyzeSemantics(ast);
            buildIR(globalScope);

        } catch (Exception e) {
            e.printStackTrace();
            java.lang.System.exit(1);
        }
    }
}