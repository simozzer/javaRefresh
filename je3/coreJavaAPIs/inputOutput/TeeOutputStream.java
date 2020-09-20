package je3.coreJavaAPIs.inputOutput;

import java.io.*;

public class TeeOutputStream extends OutputStream {

    private final OutputStream outstream1, outstream2;

    public TeeOutputStream(OutputStream out1, OutputStream out2) {
        super();
        outstream1 = out1;
        outstream2 = out2;
    }

    @Override
    public void write(int b) throws IOException {
        outstream1.write(b);
        outstream2.write(b);
    }

    public static void main(String[] args) throws IOException {

        if (args.length < 2 ) {
            System.err.println("TeeOutputStream: <usage> java TeeOutputStream <sourcefile> <targetfile>");
            System.exit(0);
        }

        File inFile = new File(args[0]);
        if (!inFile.exists()) throw new IllegalArgumentException("TeeOutputStream: no such file " + args[0]);
        if (!inFile.canRead()) throw new IllegalArgumentException("TeeOutputStream: cannot read " + args[0]);

        File outFile = new File(args[1]);
        if (outFile.exists()) throw new IllegalArgumentException("TeeOutputStream: output file exists " + args[1]);
        FileOutputStream fos = new FileOutputStream(args[1]);
        PrintStream ps = new PrintStream(System.out);

        byte[] buffer = new byte[4096];
        int bytes_read = 0;
        FileInputStream fis = new FileInputStream(args[0]);

        TeeOutputStream tos = new TeeOutputStream(ps,fos);
        while ((bytes_read = fis.read(buffer)) != -1) {
           tos.write(buffer,0,bytes_read);
        }
        fis.close();
        ps.close();
        tos.close();

    }


}
