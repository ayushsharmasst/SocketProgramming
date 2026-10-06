import java.io.*;
import java.net.*;

public class SimpleHttpServerTcp {

    public static void main(String[] args) throws Exception {

        // 1. Create a TCP ServerSocket
        ServerSocket serverSocket = new ServerSocket();

        // 2.1 Bind the TCP Socket to an IPv4 Address
        InetAddress ipv4 = InetAddress.getByName("0.0.0.0");
        serverSocket.bind(new InetSocketAddress(ipv4, 8080));

        // 2.2 Bind TCP Socket to an IPv6 Address
//        InetAddress ipv6 = InetAddress.getByName("::");
//        serverSocket.bind(new InetSocketAddress(ipv6, 8080));


        System.out.println("IPv4 UDP socket is listening on:");
        System.out.println("http:/"+ ipv4.getHostAddress() + "8080");
        //System.out.println("http:/"+ ipv6.getHostAddress() + "8080");
        System.out.println();

        while (true) {

            // 3. Wait for a TCP connection
            Socket clientSocket = serverSocket.accept();

            System.out.println("Client connected:");
            System.out.println(clientSocket.getRemoteSocketAddress());

            // 4. Read the HTTP request
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream())
            );

            String line;

            System.out.println("\n----- HTTP REQUEST -----");

            while ((line = reader.readLine()) != null && !line.isEmpty()) {
                System.out.println(line);
            }

            // 5. Send a basic HTTP response
            PrintWriter writer = new PrintWriter(
                    clientSocket.getOutputStream()
            );

            String body = "Hello TCP Server";

            writer.println("HTTP/1.1 200 OK");
            writer.println("Content-Type: text/plain");
            writer.println("Content-Length: " + body.getBytes().length);
            writer.println();
            writer.print(body);
            writer.flush();

            // 6. Close this client connection
            clientSocket.close();

            System.out.println("------------------------\n");
        }
    }
}