import java.net.*;

public class SimpleHttpServerUdp {

    public static void main(String[] args) throws Exception {

        // 1. Create a UDP ServerSocket
        DatagramSocket udpSocket = new DatagramSocket(null);

        // 2.1 Bind TCP Socket to an IPv4 Address
        InetAddress ipv4 = InetAddress.getByName("192.168.2.11");
        udpSocket.bind(new InetSocketAddress(ipv4, 8080));

        // 2.2 Bind UDP Socket to an IPv6 Address
//        InetAddress ipv6 = InetAddress.getByName("::");
//        udpSocket.bind(new InetSocketAddress(ipv6, 8080));


        System.out.println("IPv4 UDP socket is listening on:");
        System.out.println("http:/"+ ipv4.getHostAddress() + "8080");
        //System.out.println("http:/"+ ipv6.getHostAddress() + "8080");
        System.out.println();

        while (true) {
            byte[] buffer = new byte[1024];

            DatagramPacket packet =
                    new DatagramPacket(
                            buffer,
                            buffer.length
                    );

            // Wait for the next UDP datagram
            udpSocket.receive(packet);

            String message =
                    new String(
                            packet.getData(),
                            0,
                            packet.getLength()
                    );

            System.out.println(
                    "Received: " + message
            );

            System.out.println(
                    "From: "
                            + packet.getAddress()
                            + ":"
                            + packet.getPort()
            );
        }
    }
}

// To send a message to the UDP socket, use "nc -u 127.0.0.1 8080" and then insert your command and send