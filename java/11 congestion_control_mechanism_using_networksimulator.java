import java.util.Random;

public class CongestionControlSimulation {
    static final int MAX_WINDOW_SIZE = 10;
    static final int PACKET_LOSS_THRESHOLD = 0.1; // 10% packet loss threshold
    static final double PACKET_LOSS_PROBABILITY = 0.2; // 20% packet loss probability

    static Random random = new Random();

    public static void main(String[] args) {
        int windowSize = 1; // Initial window size
        int packetsSent = 0;
        int packetsAcked = 0;

        while (true) {
            // Send packets up to the current window size
            for (int i = 0; i < windowSize; i++) {
                if (random.nextDouble() > PACKET_LOSS_PROBABILITY) {
                    packetsSent++;
                    System.out.println("Packet " + packetsSent + " sent.");
                } else {
                    System.out.println("Packet " + (packetsSent + 1) + " lost.");
                }
            }

            // Simulate ACK reception
            for (int i = 0; i < windowSize; i++) {
                if (random.nextDouble() > PACKET_LOSS_THRESHOLD) {
                    packetsAcked++;
                    System.out.println("ACK received for packet " + packetsAcked);
                }
            }

            // Adjust window size based on ACKs
            if (packetsAcked >= windowSize) {
                windowSize *= 2; // Increase window size exponentially
            } else {
                windowSize = Math.max(1, windowSize / 2); // Decrease window size by half on loss
            }

            // Output current window size
            System.out.println("Current window size: " + windowSize);

            // Check if all packets are acknowledged
            if (packetsAcked >= MAX_WINDOW_SIZE) {
                System.out.println("All packets acknowledged.");
                break;
            }

            // Delay before sending next batch of packets
            try {
                Thread.sleep(1000); // Simulate delay (1 second)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
