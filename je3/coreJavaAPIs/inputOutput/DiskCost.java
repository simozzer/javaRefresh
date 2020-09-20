package je3.coreJavaAPIs.inputOutput;

import java.io.*;

public class DiskCost {

    public static void main(String[] args) {
        DiskCost d = new DiskCost(args[0]);
        System.out.println("total cost: " + d.totalSize());

    }

    public long totalSize = 0;

    public DiskCost(String dirName) {
        File f = new File(dirName);
        if (!f.isDirectory()) throw new IllegalArgumentException("DiskCost: not a directory " + dirName);

        totalSize = getFileSize(dirName);

    }

    public long totalSize() {
        return totalSize;
    }

    public long getFileSize(String fileName) {
        File f = new File(fileName);
        if (!f.exists()) throw new IllegalArgumentException("DiskCost: does not exist " + fileName);
        if (!f.canRead()) throw new IllegalArgumentException("DiskCost: cannot read " + fileName);
        if (f.isDirectory()) {
            long size = 0;
            File[] files = f.listFiles();
            for(int i=0; i < files.length; i++) {
                size += getFileSize(files[i].getPath());
            }
            return size;
        }
        else {
            return f.length();
        }
    }
}
