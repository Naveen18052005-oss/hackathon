import java.util.*;

public class LinkStateRouting {
    private int numRouters;
    private int[][] costMatrix;
    private Map<Integer, Map<Integer, Integer>> routingTables;

    public LinkStateRouting(int numRouters) {
        this.numRouters = numRouters;
        costMatrix = new int[numRouters][numRouters];
        routingTables = new HashMap<>();
    }

    public void inputCostMatrix(Scanner scanner) {
        System.out.println("Enter the cost matrix for the network topology:");
        for (int i = 0; i < numRouters; i++) {
            for (int j = 0; j < numRouters; j++) {
                costMatrix[i][j] = scanner.nextInt();
            }
        }
    }

    public void computeShortestPaths() {
        // Implement Link State Routing Algorithm
        // Update routing tables of each router
    }

    public void displayRoutingTables() {
        // Display routing tables of all routers
    }

    public void sendPacket(int source, int destination) {
        // Illustrate path taken for sending packets from source to destination
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the number of routers in the network: ");
        int numRouters = scanner.nextInt();

        LinkStateRouting router = new LinkStateRouting(numRouters);
        router.inputCostMatrix(scanner);
        router.computeShortestPaths();
        router.displayRoutingTables();

        System.out.print("Enter the source router: ");
        int source = scanner.nextInt();
        System.out.print("Enter the destination router: ");
        int destination = scanner.nextInt();
        router.sendPacket(source, destination);

        scanner.close();
    }
}
