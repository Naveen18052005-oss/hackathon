import java.io.*;
import java.net.*;

public class DNSClient {
    public static void main(String[] args) {
        DatagramSocket socket = null;

        try {
            // Create a DatagramSocket
            socket = new DatagramSocket();

            // Set up server address and port
            InetAddress serverAddress = InetAddress.getByName("8.8.8.8");
            int serverPort = 53;

            // Prepare DNS query message
            byte[] queryData = createDNSQuery("www.example.com");

            // Send DNS query message
            DatagramPacket queryPacket = new DatagramPacket(queryData, queryData.length, serverAddress, serverPort);
            socket.send(queryPacket);

            // Receive DNS response message
            byte[] responseData = new byte[1024];
            DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length);
            socket.receive(responsePacket);

            // Process and print DNS response
            String response = new String(responsePacket.getData(), 0, responsePacket.getLength());
            System.out.println("DNS Response: " + response);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }

    private static byte[] createDNSQuery(String domainName) {
        // DNS query format: <header><question>
        // For simplicity, creating a minimal DNS query for an A (IPv4 address) record

        // DNS header (12 bytes)
        byte[] header = {
                0x00, 0x00, // Identifier
                0x01, 0x00, // Flags: Standard Query
                0x00, 0x01, // Questions: 1
                0x00, 0x00, // Answer RRs: 0
                0x00, 0x00, // Authority RRs: 0
                0x00, 0x00  // Additional RRs: 0
        };

        // DNS question section: <QNAME><QTYPE><QCLASS>
        // QNAME: Domain name, QTYPE: Type of query (A record), QCLASS: Internet
        String[] domainParts = domainName.split("\\.");
        byte[] question = new byte[domainName.length() + 5]; // Maximum size estimation
        int offset = 0;
        for (String part : domainParts) {
            int length = part.length();
            question[offset++] = (byte) length; // Length of domain part
            for (int i = 0; i < length; i++) {
                question[offset++] = (byte) part.charAt(i); // Domain part characters
            }
        }
        question[offset++] = 0x00; // Null terminator
        question[offset++] = 0x00; // QTYPE: A record
        question[offset++] = 0x01;
        question[offset++] = 0x00; // QCLASS: Internet
        question[offset++] = 0x01;

        // Combine header and question sections
        byte[] query = new byte[header.length + question.length];
        System.arraycopy(header, 0, query, 0, header.length);
        System.arraycopy(question, 0, query, header.length, question.length);

        return query;
    }
}
