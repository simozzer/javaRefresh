package je3.coreJavaAPIs.inputOutput;

import java.io.*;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Collections;

public class DirList {

    protected enum SortType{ NAME, SIZE, DATE};




    private SortType sortBy;
    private String startDir;
    private DateFormat dateFormatter = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);
    public DirList(String dirName, SortType sort) {
        startDir = dirName;
        sortBy = sort;
        listFiles(dirName);
    }

    public void listFiles(String dirName)  {
        File d = new File(dirName);
        if (!d.exists()) fail("does not exist", dirName);
        if (!d.canRead()) fail("cannot read", dirName);
        if (!d.isDirectory()) fail("not a directory", dirName);
        File[] entries = d.listFiles();
        ArrayList list = new ArrayList();
        for(int i = 0; i< entries.length; i++) {
            if (entries[i].isDirectory()) {
                listFiles(entries[i].getPath());
            } else {
                list.add(entries[i]);
            }
        }

        switch(sortBy) {
            case SIZE:
                list.sort(new Comparator() {
                    @Override
                    public int compare(Object o1, Object o2) {
                        File f1 = (File)o1;
                        File f2 = (File)o2;
                        return - Long.compare(f1.length(),f2.length());
                    }
                });
                break;
            case DATE:
                list.sort(new Comparator() {
                    @Override
                    public int compare(Object o1, Object o2) {
                        File f1 = (File)o1;
                        File f2 = (File)o2;
                        return -Long.compare(f1.lastModified(),f2.lastModified());
                    }
                });
                break;
            default:
                list.sort(new Comparator() {
                    @Override
                    public int compare(Object o1, Object o2) {
                        File f1 = (File)o1;
                        File f2 = (File)o2;
                        return f1.getName().compareTo(f2.getName());
                    }
                });

        }
        for (int j=0; j < list.size(); j++) {
            File x = (File)list.get(j);
            System.out.println(x.getPath() + " " + x.length() + " " + dateFormatter.format(new java.util.Date(x.lastModified())));
        }

    }

    private void fail(String message, String path) {
        System.err.println("DirList: " + message + " " + path);
        System.exit(0);
    }

    public static class Test{

        public static  void main(String[] args) {
            if (args.length < 1) usage();
            String dirName = args[0];
            SortType sort = SortType.NAME;
            if (args.length > 1) {
                String opt = args[1];
                if (opt.equals("-d")) sort = SortType.DATE;
                else if (opt.equals("-s")) sort = SortType.SIZE;
                else usage();
            }
            DirList d = new DirList( dirName, sort );
        }

        static void usage() {
            System.err.println("DirList: <usage>  java DirList <filename> ?[-s/d]");
            System.exit(0);
        }
    }


}
