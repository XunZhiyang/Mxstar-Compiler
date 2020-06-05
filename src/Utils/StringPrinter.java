package Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class StringPrinter {
    public static void print(String pathname, String content) {
        try {
            File file = new File(pathname);
            FileWriter fileWriter = new FileWriter(file.getName());
            BufferedWriter bufferWriter = new BufferedWriter(fileWriter);
            bufferWriter.write(content);
            bufferWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
            java.lang.System.exit(1);
        }
    }
}
