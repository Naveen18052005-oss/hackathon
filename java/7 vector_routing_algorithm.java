import java.util.Scanner;

public class DistanceVectorRouter {
    private int[][] costMatrix;
    private int numRouters;

    public DistanceVectorRouter(int numRouters) {
        this.numRouters = numRouters;
        costMatrix = new int[numRouters][numRouters];
    }

    public void inputCostMatrix(Scanner scanner) {
        System.out.println("Enter the cost matrix for the network topology:");
        for (int i = 0; i < numRouters; i++) {
            for (int j = 0; j < numRouters; j++) {
                costMatrix[i][j] = scanner.nextInt();
            }
        }
    }

    public void computeShortestPath() {
        // Implement distance vector routing algorithm
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

        DistanceVectorRouter router = new DistanceVectorRouter(numRouters);
        router.inputCostMatrix(scanner);
        router.computeShortestPath();
        router.displayRoutingTables();

        System.out.print("Enter the source router: ");
        int source = scanner.nextInt();
        System.out.print("Enter the destination router: ");
        int destination = scanner.nextInt();
        router.sendPacket(source, destination);

        scanner.close();
    }
}
