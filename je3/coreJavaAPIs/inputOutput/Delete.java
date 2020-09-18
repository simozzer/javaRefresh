package je3.coreJavaAPIs.inputOutput;

import java.io.*;

public class Delete {

    public static void delete(String filename) {
        File f = new File(filename);

        if (!f.exists()) fail("Delete: no such file or directory: " + filename);
        if (!f.canWrite()) fail("Delete: write protected: " + filename);
        if (f.isDirectory()) {
            String[] files = f.list();
            if (files.length >0) {
                fail("Delete: directory not empty");
            }
        }

        boolean deleted = f.delete();

        if (!deleted) fail("Delete: deletion failed");
    }

    protected static void fail(String msg) throws IllegalArgumentException {
        throw new IllegalArgumentException(msg);
    }
}
