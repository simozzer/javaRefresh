package je3.coreJavaAPIs.network;

import java.io.*;
import java.net.*;

public class GenericClient {

    public static void main(String[] args) throws IOException {
        try {
            if (args.length != 2) throw new IllegalArgumentException("Wrong number of args");
            String host = args[0];
            int port = Integer.parseInt(args[1]);
            Socket s = new Socket(host, port);

            final Reader from_server = new InputStreamReader(s.getInputStream());
            PrintWriter to_server = new PrintWriter(s.getOutputStream());

            BufferedReader from_user = new BufferedReader(new InputStreamReader(System.in));

            final PrintWriter to_user = new PrintWriter(System.out, true);

            to_user.println("Connected to " + s.getInetAddress() + ":" + s.getPort());

            Thread t = new Thread() {
                @Override
                public void run() {
                    char[] buffer = new char[1024];
                    int chars_read;
                    try {
                        while ((chars_read = from_server.read(buffer)) != -1) {
                            to_user.write(buffer, 0, chars_read);
                            to_user.flush();
                        }
                    } catch (IOException e) {
                        to_user.println(e);
                    }

                    to_user.println("Connection closed by server.");
                    System.exit(0);
                }
            };
            t.start();

            String line;
            while ((line = from_user.readLine()) != null) {
                to_server.print(line + "\r\n");
                to_server.flush();
            }
            s.close();
            to_user.println("Connection closed by client.");
            System.exit(0);
        }
        catch (Exception e) {
            System.err.println(e);
            System.err.println("Usage: java je3.coreJavaAPIs.network.GenericClient <host> <port>");
        }

    }
}
