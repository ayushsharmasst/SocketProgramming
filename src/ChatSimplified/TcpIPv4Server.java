package ChatSimplified;

import java.net.*;
import java.util.*;
import java.io.*;

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

        // 3. Wait for a client
        Socket clientSocket =
                serverSocket.accept();

        System.out.println(
                "Client connected: "
                        + clientSocket.getRemoteSocketAddress()
        );

        // 4. Read messages coming from client
        Scanner clientInput =
                new Scanner(
                        clientSocket.getInputStream()
                );

        // 5. Send messages to client
//        PrintStream clientOutput =
//                new PrintStream(
//                        clientSocket.getOutputStream()
//                );

        // 6. Read messages typed in server terminal
        Scanner terminalInput =
                new Scanner(System.in);

        while (true) {

            // Wait for client message
            String clientMessage =
                    clientInput.nextLine();

            System.out.println(
                    "Client: " + clientMessage
            );

            if (clientMessage.equalsIgnoreCase("exit")) {
                break;
            }

            // Take message from server terminal
            System.out.print("Server: ");

            String serverMessage =
                    terminalInput.nextLine();

            // Send message to client
            clientSocket.getOutputStream().write(serverMessage.getBytes());

            if (serverMessage.equalsIgnoreCase("exit")) {
                break;
            }
        }

        clientSocket.close();
        serverSocket.close();
    }
}
