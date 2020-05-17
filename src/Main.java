import AST.ASTNode;
import AST.ProgramNode;
import Backend.IRBuilder;
import Backend.IROptimizer;
import Backend.IRPrinter;
//import Backend.IROptimizer;
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

import java.io.*;

import Parser.MxstarLexer;
import Parser.MxstarParser;

import Symbol.GlobalScope;

public class Main {
    private static void print(String pathname, String content) throws IOException {
        File file =new File(pathname);
        FileWriter fileWriter = new FileWriter(file.getName());
        BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
        bufferWriter.write(content);
        bufferWriter.close();
    }

    private static CharStream readCode() throws Exception {
        String inputFile = "code.mx";
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

    private static GlobalScope analyzeSemantics(ProgramNode ast) {
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

    private static Module buildIR(ProgramNode ast, GlobalScope globalScope) throws Exception {
        IRBuilder builder = new IRBuilder(globalScope);
        builder.visit(ast);
        Module module = builder.getModule();

        IRPrinter printer = new IRPrinter();
        printer.visit(module);
        String generatedIR = printer.getIR(true);

        print("code.ll", generatedIR);

        return module;
    }

    private static void optimize(Module module) throws Exception {
        IROptimizer optimizer = new IROptimizer(module);
        optimizer.optimize();

        IRPrinter printer = new IRPrinter();
        printer.visit(module);
        String generatedIR = printer.getIR(true);

        print("code_opt.ll", generatedIR);
    }

    public static void main(String[] args) {
        try{
            CharStream input = readCode();
            ParseTree tree = buildCST(input);
            ProgramNode ast = (ProgramNode) buildAST(tree);
            GlobalScope globalScope = analyzeSemantics(ast);
            Module module = buildIR(ast, globalScope);
//            optimize(module);
        } catch (Exception e) {
            e.printStackTrace();
            java.lang.System.exit(1);
        }
    }
}