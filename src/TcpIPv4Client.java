import java.io.*;
import java.net.*;

public class TcpIPv4Client {

    public static void main(String[] args) throws Exception {

        // Server IP
        InetAddress serverIp =
                InetAddress.getByName("127.0.0.1");

        // 1. Create TCP socket
        Socket socket = new Socket();

        // 2. Connect to server
        socket.connect(
                new InetSocketAddress(
                        serverIp,
                        8080
                )
        );

        System.out.println(
                "Connected to server"
        );

        // Read messages from server
        BufferedReader serverReader =
                new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()
                        )
                );

        // Send messages to server
        PrintWriter serverWriter =
                new PrintWriter(
                        socket.getOutputStream(),
                        true
                );

        // Read input from client terminal
        BufferedReader terminalReader =
                new BufferedReader(
                        new InputStreamReader(System.in)
                );

        while (true) {

            // Take input from client
            System.out.print("Client: ");

            String clientMessage =
                    terminalReader.readLine();

            // Send to server
            serverWriter.println(clientMessage);

            if (clientMessage.equalsIgnoreCase("exit")) {
                break;
            }

            // Wait for server response
            String serverMessage =
                    serverReader.readLine();

            if (serverMessage == null) {
                break;
            }

            System.out.println(
                    "Server: " + serverMessage
            );

            if (serverMessage.equalsIgnoreCase("exit")) {
                break;
            }
        }

        socket.close();
    }
}