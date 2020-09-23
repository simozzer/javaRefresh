package je3.coreJavaAPIs.network;

import java.net.*;
import java.io.*;
import java.util.Date;

public class GetURLInfo {

    public static void printInfo(URL url) throws IOException {
        URLConnection c = url.openConnection();
        c.connect();

        System.out.println("  Content type " + c.getContentType());
        System.out.println("  Content encoding " + c.getContentEncoding());
        System.out.println("  Content length " + c.getContentLength());
        System.out.println("  Date " + new Date(c.getDate()));
        System.out.println("  Last modified " + c.getLastModified());
        System.out.println("  Expiration " + c.getExpiration());

        if (c instanceof HttpURLConnection) {
            HttpURLConnection h = (HttpURLConnection)c;
            System.out.println("  Request method " + h.getRequestMethod());
            System.out.println("  Response encoding " + h.getResponseMessage());
            System.out.println("  Response code " + h.getResponseCode());
        }
    }

    public static void main(String[] args) {
        try {
            printInfo(new URL(args[0]));
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
