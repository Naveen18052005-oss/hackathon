import java.util.Random;

// Represents a node in the network
class Node {
    private int id;

    public Node(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    // Simulate sending a packet from this node to another
    public double sendPacket(Node destination, String routingProtocol) {
        // Simulate delay based on routing protocol
        double averageDelay;
        if (routingProtocol.equals("AODV")) {
            // Simulate AODV routing delay
            averageDelay = 50; // milliseconds
        } else {
            // Simulate DSR routing delay
            averageDelay = 70; // milliseconds
        }

        // Simulate additional random delay
        Random random = new Random();
        double additionalDelay = random.nextDouble() * 20; // additional delay up to 20 milliseconds

        // Total delay is the sum of average delay and additional delay
        double totalDelay = averageDelay + additionalDelay;

        // Return the total delay
        return totalDelay;
    }
}

public class RoutingProtocolSimulation {
    public static void main(String[] args) {
        // Create nodes
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);

        // Simulate sending packets from node 1 to node 3 using AODV
        double totalDelayAODV = 0;
        for (int i = 0; i < 1000; i++) {
            totalDelayAODV += node1.sendPacket(node3, "AODV");
        }
        double averageDelayAODV = totalDelayAODV / 1000;
        System.out.println("Average end-to-end delay using AODV: " + averageDelayAODV + " milliseconds");

        // Simulate sending packets from node 1 to node 3 using DSR
        double totalDelayDSR = 0;
        for (int i = 0; i < 1000; i++) {
            totalDelayDSR += node1.sendPacket(node3, "DSR");
        }
        double averageDelayDSR = totalDelayDSR / 1000;
        System.out.println("Average end-to-end delay using DSR: " + averageDelayDSR + " milliseconds");
    }
}
