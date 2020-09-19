package je3.coreJavaAPIs.inputOutput;

import java.io.*;
import java.util.ArrayList;

public class Tail {

    public static void main(String[] args) throws IOException{
        for(int i = 0; i < args.length; i++) {
            showTail(args[i]);
        }
    }

    public static void showTail(String filename) throws IOException {
        File f = new File(filename);
        if (!f.exists()) {
            System.err.println("Tail: no such file " + filename);
            System.exit(0);
        }
        if (f.isDirectory()) {
            System.err.println("Tail: is directory " + filename);
            System.exit(0);
        }
        if (!f.canRead()) {
            System.err.println("Tail: cannot read " + filename);
            System.exit(0);
        }

        System.out.println("Tail: " + filename);
        BufferedReader in = new BufferedReader(new FileReader(f.getName()));
        int lineNo = 0;
        String line = in.readLine();
        ArrayList lines = new ArrayList();
        while (line != null) {
            lines.add(line);
            if (lines.size() > 10) {
                lines.remove(0);
            }
            line=in.readLine();
        }

        for (int i=0; i < lines.size(); i++) {
            System.out.println(lines.get(i));
        }
        System.out.println();
        in.close();
    }
}
