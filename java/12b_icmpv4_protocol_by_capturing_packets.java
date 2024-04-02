import java.io.IOException;
import java.net.*;

public class ICMPv4PacketCapture {
    public static void main(String[] args) {
        try {
            // Create a DatagramSocket to listen for ICMP packets
            DatagramSocket socket = new DatagramSocket(0, InetAddress.getByName("0.0.0.0"));
            socket.setSoTimeout(1000); // Set socket timeout to 1 second

            // Continuously listen for ICMP packets
            while (true) {
                try {
                    // Receive packet
                    byte[] buffer = new byte[1024];
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    socket.receive(packet);

                    // Print packet details
                    InetAddress senderAddress = packet.getAddress();
                    System.out.println("Received ICMP packet from: " + senderAddress.getHostAddress());
                    System.out.println("Packet data: " + new String(packet.getData(), 0, packet.getLength()));

                } catch (SocketTimeoutException e) {
                    // Timeout occurred, continue listening
                }
            }

        } catch (SocketException | UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
