import java.io.*;
import java.net.*;

public class ChatServer {
    public static void main(String[] args) {
        final int PORT = 12345;

        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Chat Server is listening on port " + PORT);

            Socket clientSocket1 = serverSocket.accept();
            System.out.println("Client 1 connected: " + clientSocket1);

            Socket clientSocket2 = serverSocket.accept();
            System.out.println("Client 2 connected: " + clientSocket2);

            // Start threads to handle communication with clients
            Thread clientThread1 = new Thread(new ClientHandler(clientSocket1, clientSocket2));
            clientThread1.start();

            Thread clientThread2 = new Thread(new ClientHandler(clientSocket2, clientSocket1));
            clientThread2.start();

        } catch (IOException e) {
            System.err.println("Error in server: " + e.getMessage());
        }
    }

    private static class ClientHandler implements Runnable {
        private Socket clientSocket;
        private Socket otherClientSocket;

        public ClientHandler(Socket clientSocket, Socket otherClientSocket) {
            this.clientSocket = clientSocket;
            this.otherClientSocket = otherClientSocket;
        }

        @Override
        public void run() {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter writer = new PrintWriter(otherClientSocket.getOutputStream(), true);

                String inputLine;
                while ((inputLine = reader.readLine()) != null) {
                    System.out.println("Received from client: " + inputLine);
                    writer.println(inputLine); // Send message to other client
                }

                reader.close();
                writer.close();
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error handling client: " + e.getMessage());
            }
        }
    }
}

