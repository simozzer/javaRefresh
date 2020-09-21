package je3.coreJavaAPIs.threads;


import java.io.*;

public class Exercise1 extends Thread {


    public static void main(String[] args) {
        Object res1 = "res1";
        long ms = System.currentTimeMillis();
        Exercise1[] threads = new Exercise1[args.length];
        for(int i=0; i < args.length; i++) threads[i] = new Exercise1(args[i]);
        for(int i=0; i < args.length; i++) {
            synchronized (res1) {
                threads[i].start();
            }
            try {
                threads[i].join();
            }
        }

        /*
        try {
            for(int i=0; i <args.length; i++) {
                threads[i].join();
            }
        }
        catch(InterruptedException e) {};
         */

        System.out.println("Duration: " + String.valueOf(System.currentTimeMillis() - ms));
    }


    String filename;
    public Exercise1(String filename) {
        this.filename = filename;
    }

    @Override
    public void run() {
        File f = new File(filename);
        if (!f.exists())  throw new IllegalArgumentException("LineCounterThread: file does not exist " + filename);
        if (!f.canRead()) throw new IllegalArgumentException("LineCounterThread: cannot read " + filename);
        try {
            InputStreamReader in = new FileReader(f);
            LineNumberReader lineReader = new LineNumberReader(in);
            String line = lineReader.readLine();
            while (line  != null) {
                line = lineReader.readLine();
            }
            System.out.println("LineCounterThread: " + f + " has " + lineReader.getLineNumber() + " lines.");
            lineReader.close();;
            in.close();;
        }
        catch (IOException e) {
            System.err.println("LineCounterThread: IOException " + e.getMessage());
            System.exit(0);
        }
    }
}
