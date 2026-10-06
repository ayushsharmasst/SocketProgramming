import java.io.*;
import java.net.*;
import java.nio.file.*;

public class HTMLServerTCP {

    public static void main(String[] args) throws Exception {

        InetAddress ipv4 =
                InetAddress.getByName("0.0.0.0");

        ServerSocket serverSocket =
                new ServerSocket();

        serverSocket.bind(
                new InetSocketAddress(ipv4, 8080)
        );

        System.out.println(
                "Server listening on port 8080"
        );

        while (true) {

            Socket clientSocket =
                    serverSocket.accept();

            BufferedReader reader =
                    new BufferedReader(
                            new InputStreamReader(
                                    clientSocket.getInputStream()
                            )
                    );

            System.out.println("\n--- HTTP REQUEST ---");

            String line;

            while ((line = reader.readLine()) != null
                    && !line.isEmpty()) {

                System.out.println(line);
            }

            // Read HTML file
            byte[] body =
                    Files.readAllBytes(
                            Paths.get("index.html")
                    );

            OutputStream output =
                    clientSocket.getOutputStream();

            // HTTP Response Headers
            String headers =
                    "HTTP/1.1 200 OK\r\n" +
                            "Content-Type: text/html; charset=UTF-8\r\n" +
                            "Content-Length: " + body.length + "\r\n" +
                            "Connection: close\r\n" +
                            "\r\n";

            output.write(
                    headers.getBytes()
            );

            // Send the actual HTML file
            output.write(body);

            output.flush();

            clientSocket.close();
        }
    }
}