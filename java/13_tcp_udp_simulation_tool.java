import java.io.*;
import java.net.*;

public class TCPUDPPerformanceSimulation {
    static final int NUM_PACKETS = 1000;
    static final int PACKET_SIZE = 1024;

    public static void main(String[] args) {
        // Simulate TCP performance
        simulateTCP();

        // Simulate UDP performance
        simulateUDP();
    }

    private static void simulateTCP() {
        try {
            // Open TCP connection
            Socket tcpSocket = new Socket("localhost", 8080);
            OutputStream outputStream = tcpSocket.getOutputStream();

            long startTime = System.currentTimeMillis();

            // Send packets over TCP
            for (int i = 0; i < NUM_PACKETS; i++) {
                byte[] data = new byte[PACKET_SIZE];
                outputStream.write(data);
            }

            long endTime = System.currentTimeMillis();
            long tcpTime = endTime - startTime;
            double tcpThroughput = (double) NUM_PACKETS * PACKET_SIZE / (tcpTime / 1000.0);

            System.out.println("TCP Performance:");
            System.out.println("Time taken: " + tcpTime + " ms");
            System.out.println("Throughput: " + tcpThroughput + " bytes/second");

            // Close TCP connection
            tcpSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void simulateUDP() {
        try {
            DatagramSocket udpSocket = new DatagramSocket();

            long startTime = System.currentTimeMillis();

            // Send packets over UDP
            for (int i = 0; i < NUM_PACKETS; i++) {
                byte[] data = new byte[PACKET_SIZE];
                DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getLocalHost(), 8081);
                udpSocket.send(packet);
            }

            long endTime = System.currentTimeMillis();
            long udpTime = endTime - startTime;
            double udpThroughput = (double) NUM_PACKETS * PACKET_SIZE / (udpTime / 1000.0);

            System.out.println("\nUDP Performance:");
            System.out.println("Time taken: " + udpTime + " ms");
            System.out.println("Throughput: " + udpThroughput + " bytes/second");

            udpSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
