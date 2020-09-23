package je3.coreJavaAPIs.network;

import java.io.*;
import java.net.*;
import javax.net.*;
import javax.net.ssl.*;
import java.security.cert.*;

public class HttpClient {

    public static void main(String[] args) {
        try {
            if (args.length != 2) throw new IllegalArgumentException("Wrong number of args");

            OutputStream to_file = new FileOutputStream(args[1]);

            URI uri = new URI(args[0]);
            String protocol = uri.getScheme();
            String host = uri.getHost();
            int port = uri.getPort();
            String path = uri.getRawPath();
            String query = uri.getQuery();
            if (query != null && query.length() > 0) path += "?" + query;

            Socket socket;

            if (protocol.equals("http")) {
                if (port == -1) port = 80;
                socket = new Socket(host, port);
            } else if (protocol.equals("https")) {
                if (port == -1) port = 443;
                SocketFactory factory = SSLSocketFactory.getDefault();
                SSLSocket ssock = (SSLSocket) factory.createSocket(host, port);

                SSLSession session = ssock.getSession();
                X509Certificate cert = null;
                try {
                    cert = (X509Certificate) session.getPeerCertificates()[0];
                } catch (SSLPeerUnverifiedException e) {
                    System.err.println(session.getPeerHost() + " did not present a valid certificate");
                    System.exit(1);
                }
                System.out.println(session.getPeerHost() + " has presented a certificate belonging to:\t" +
                        "[" + cert.getSubjectDN() + "]\n" +
                        "The certificate was issued by: \t" +
                        "[" + cert.getIssuerDN() + "]");
                socket = ssock;
            } else {
                throw new IllegalArgumentException("URL must use http or https protocol");
            }

            // now we have the right kind of socket the code is the same.
            InputStream from_server = socket.getInputStream();
            PrintWriter to_server = new PrintWriter(socket.getOutputStream());

            to_server.print("GET " + path + " HTTP/1.0\r\n" +
                    "Host: " + host  + "\r\n" +
                    "Connection: close\r\n\r\n");
            to_server.flush();

            byte[] buffer = new byte[8 * 1024];
            int bytes_read;

            int numbytes = 0;
            while (true) {
                bytes_read = from_server.read(buffer, numbytes, buffer.length - numbytes);
                if (bytes_read == -1) break;
                numbytes += bytes_read;
                if (numbytes >= 4 * 1024) break;
            }

            // loop through looking for \r\n\r\n that marks the end of the headers
            int i = 0;
            while (i <= numbytes - 4) {
                if (buffer[i++] == 13 && buffer[i++] == 10 && buffer[i++] == 13 && buffer[i++] == 10) break;
            }

            if (i > numbytes - 4) {
                throw new IOException("End of headers not found in first " + numbytes + " bytes");
            }

            // convert bytes to a latin-1 string(omitting the final line)
            String headers = new String(buffer, 0, i - 2, "ISO-8859-1");
            System.out.print(headers);

            to_file.write(buffer, i, numbytes - i);

            while ((bytes_read = from_server.read(buffer)) != -1)
                to_file.write(buffer, 0, bytes_read);

            socket.close();
            to_file.close();
        }
        catch (Exception e) {
            System.err.println(e);
            System.err.println("Usage: java je3.coreJavaAPIs.network.HttpClient <URL? [<filename>]");

        }
    }
}
