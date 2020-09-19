package je3.coreJavaAPIs.inputOutput;

import java.io.*;

public class Head {

    public static void main(String[] args) throws IOException{
        for(int i = 0; i < args.length; i++) {
            showHead(args[i]);
        }
    }

    public static void showHead(String filename) throws IOException {
        File f = new File(filename);
        if (!f.exists()) {
            System.err.println("Head: no such file " + filename);
            System.exit(0);
        }
        if (f.isDirectory()) {
            System.err.println("Head: is directory " + filename);
            System.exit(0);
        }
        if (!f.canRead()) {
            System.err.println("Head: cannot read " + filename);
            System.exit(0);
        }

        System.out.println("Head: " + filename);
        BufferedReader in = new BufferedReader(new FileReader(f.getName()));
        int lineNo = 0;
        String line = in.readLine();
        while ((line != null) && (lineNo < 10)) {
            System.out.println(line);
            lineNo++;
            line=in.readLine();
        }
        System.out.println();
        in.close();
    }
}
