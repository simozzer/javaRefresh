package je3.coreJavaAPIs.inputOutput;

import java.io.*;


// TODO:: revisit
public class FileStats {

    public static void main(String[] args) throws IOException{
        showStats(args[0]);
    }

    public static void showStats(String filename) throws IOException {
        File f = new File(filename);
        if (!f.exists()) {
            System.err.println("FileStats: no such file " + filename);
            System.exit(0);
        }
        if (f.isDirectory()) {
            System.err.println("FileStats: is directory " + filename);
            System.exit(0);
        }
        if (!f.canRead()) {
            System.err.println("FileStats: cannot read " + filename);
            System.exit(0);
        }

        System.out.println("FileStats: " + filename);
        BufferedReader in = new BufferedReader(new FileReader(f.getName()));
        int lines = 0;
        int words = 0;
        int chars = 0;
        boolean isChar = false;
        String line = in.readLine();
        while (line != null) {
            lines++;
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (!Character.isAlphabetic(c)) {
                    if (isChar) {
                        words++;
                        isChar = false;
                    }
                }
                else {
                    if (!isChar) {
                        isChar = true;
                    }
                    chars++;
                }
            }
            line=in.readLine();
        }
        System.out.println("Lines: " + lines + ", Words: " + words + ", Chars: " + chars);
        in.close();
    }
}
