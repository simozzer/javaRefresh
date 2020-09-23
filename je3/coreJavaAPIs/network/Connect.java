package je3.coreJavaAPIs.network;

import java.io.*;
import java.net.*;


public class Connect {

    public static void main(String[] args) {
        try {
            String hostname = args[0];
            int port = Integer.parseInt(args[1]);
            String message = "";
            if (args.length  > 2) for (int i=2; i< args.length;i++) message += args[i] + " ";

            Socket s = new Socket(hostname, port);

            PrintWriter out = new PrintWriter(s.getOutputStream());

            out.print(message + "\r\n");
            out.flush();

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            s.setSoTimeout(3000);

            try {
                String line;
                while((line = in.readLine()) != null)
                    System.out.println(line);
            }
            catch (SocketTimeoutException e) {
                System.err.println("No response from server");
            }
            out.close();
            in.close();
            s.close();
        }
        catch(IOException e) {
            System.err.println(e);
        }
        catch (NumberFormatException e) {
            System.err.println("port should be a number");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("wrong number of args");
        }
    }
}
