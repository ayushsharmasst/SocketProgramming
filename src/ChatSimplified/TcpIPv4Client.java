package ChatSimplified;

import java.net.*;
import java.util.*;
import java.io.*;

public class TcpIPv4Client {

    public static void main(String[] args) throws Exception {

        // Server IPv4 address
        InetAddress serverIp =
                InetAddress.getByName("127.0.0.1");

        // 1. Create TCP Socket
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

        // 3. Read messages coming from server
        Scanner serverInput =
                new Scanner(
                        socket.getInputStream()
                );

        // 4. Send messages to server
//        PrintStream serverOutput =
//                new PrintStream(
//                        socket.getOutputStream()
//                );

        // 5. Read input from client terminal
        Scanner terminalInput =
                new Scanner(System.in);

        while (true) {

            // Take message from client terminal
            System.out.print("Client: ");

            String clientMessage =
                    terminalInput.nextLine();

            // Send message to server
            socket.getOutputStream().write(clientMessage.getBytes());

            if (clientMessage.equalsIgnoreCase("exit")) {
                break;
            }

            // Wait for server response
            String serverMessage =
                    serverInput.nextLine();

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
