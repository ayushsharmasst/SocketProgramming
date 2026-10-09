import java.io.*;
import java.net.*;

public class TcpIPv4Server {

    public static void main(String[] args) throws Exception {

        // 1. Create TCP Server Socket
        ServerSocket serverSocket = new ServerSocket();

        // 2. Bind to IPv4
        InetAddress ipv4 =
                InetAddress.getByName("0.0.0.0");

        serverSocket.bind(
                new InetSocketAddress(ipv4, 8080)
        );

        System.out.println(
                "Server listening on port 8080..."
        );

        // 3. Wait for client
        Socket clientSocket =
                serverSocket.accept();

        System.out.println(
                "Client connected: "
                        + clientSocket.getRemoteSocketAddress()
        );

        // Read messages from client
        BufferedReader clientReader =
                new BufferedReader(
                        new InputStreamReader(
                                clientSocket.getInputStream()
                        )
                );

        // Send messages to client
        PrintWriter clientWriter =
                new PrintWriter(
                        clientSocket.getOutputStream(),
                        true
                );

        // Read input from server terminal
        BufferedReader terminalReader =
                new BufferedReader(
                        new InputStreamReader(System.in)
                );

        while (true) {

            // Wait for message from client
            String clientMessage =
                    clientReader.readLine();

            if (clientMessage == null) {
                break;
            }

            System.out.println(
                    "Client: " + clientMessage
            );

            if (clientMessage.equalsIgnoreCase("exit")) {
                break;
            }

            // Take input from server terminal
            System.out.print("Server: ");

            String serverMessage =
                    terminalReader.readLine();

            // Send to client
            clientWriter.println(serverMessage);

            if (serverMessage.equalsIgnoreCase("exit")) {
                break;
            }
        }

        clientSocket.close();
        serverSocket.close();
    }
}