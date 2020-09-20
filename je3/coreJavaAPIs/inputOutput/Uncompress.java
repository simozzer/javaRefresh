package je3.coreJavaAPIs.inputOutput;


import java.io.*;
import java.util.zip.*;

public class Uncompress {

    public static void main(String[] args) throws IOException{
        if (args.length < 2) {
            System.err.println("Uncompress: <usage> java Uncompress <zipFilePath> <outPath>");
            System.exit(0);
        }
        String in = args[0];
        File inFile = new File(in);
        if (!inFile.exists()) {
            System.err.println("Uncompress: no such file " + in);
        }
        if (!inFile.canRead()) {
            System.err.println("Uncompress: cannot read " + in);
        }
        Uncompress(in,args[1]);
    }


    public static void Uncompress(String zipFilePath, String destDir) throws IOException {
        File dir = new File(destDir);
        // create output directory if it doesn't exist
        if(!dir.exists()) dir.mkdirs();
        FileInputStream fis;
        //buffer for read and write data to file
        byte[] buffer = new byte[1024];

        fis = new FileInputStream(zipFilePath);
        ZipInputStream zis = new ZipInputStream(fis);
        ZipEntry ze = zis.getNextEntry();
        while(ze != null){
            String fileName = ze.getName();
            File newFile = new File(destDir + File.separator + fileName);
            //create directories for sub directories in zip
            new File(newFile.getParent()).mkdirs();
            FileOutputStream fos = new FileOutputStream(newFile);
            int len;
            while ((len = zis.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            fos.close();
            //close this ZipEntry
            zis.closeEntry();
            ze = zis.getNextEntry();
        }
        //close last ZipEntry
        zis.closeEntry();
        zis.close();
        fis.close();

    }
}
