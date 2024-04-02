import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class DNSClient {
    private static final int SERVER_PORT = 9876;
    private static final String SERVER_ADDRESS = "localhost";
    private static final Map<String, String> dnsRecords = new HashMap<>();

    static {
        // Populate DNS records (domain -> IP address)
        dnsRecords.put("example.com", "192.0.2.1");
        dnsRecords.put("google.com", "172.217.168.78");
        dnsRecords.put("yahoo.com", "98.138.219.232");
        // Add more DNS records as needed
    }

    public static void main(String[] args) {
        try {
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            // Create UDP socket
            DatagramSocket socket = new DatagramSocket();

            // Get user input for domain name
            System.out.print("Enter domain name: ");
            String domain = userInput.readLine();

            // Send DNS query to server
            byte[] sendData = domain.getBytes();
            InetAddress serverAddress = InetAddress.getByName(SERVER_ADDRESS);
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, SERVER_PORT);
            socket.send(sendPacket);

            // Receive DNS response from server
            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            socket.receive(receivePacket);

            // Process DNS response
            String ipAddress = new String(receivePacket.getData(), 0, receivePacket.getLength());
            if (ipAddress.equals("NOT FOUND")) {
                System.out.println("Domain not resolved.");
            } else {
                System.out.println("IP Address for " + domain + ": " + ipAddress);
            }

            // Close the socket
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
