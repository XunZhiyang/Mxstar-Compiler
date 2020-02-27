import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.InputStream;


public class Main {
    private static CharStream readCode() throws Exception{
        String inputFile = "test.m";
        InputStream is = new FileInputStream(inputFile);
        CharStream input = CharStreams.fromStream(is);
        return input;

    }
    private static ParseTree buildCST(CharStream input) throws Exception{
        MxstarLexer lexer = new MxstarLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        MxstarParser parser = new MxstarParser(tokens);
        ParseTree tree = parser.program();
        System.out.println(tree.toStringTree(parser));
        return tree;
    }
    public static void main(String[] args) {
        try{
            CharStream input = readCode();
            buildCST(input);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
    }
}
