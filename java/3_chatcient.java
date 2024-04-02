import java.io.*;
import java.net.*;

public class ChatClient {
    public static void main(String[] args) {
        final String SERVER_ADDRESS = "localhost";
        final int SERVER_PORT = 12345;

        try {
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            System.out.println("Connected to server: " + socket);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            Thread receiveThread = new Thread(new ReceiveMessage(reader));
            receiveThread.start();

            String inputLine;
            while ((inputLine = userInput.readLine()) != null) {
                writer.println(inputLine); // Send user input to server
            }

            userInput.close();
            reader.close();
            writer.close();
            socket.close();
        } catch (IOException e) {
            System.err.println("Error in client: " + e.getMessage());
        }
    }

    private static class ReceiveMessage implements Runnable {
        private BufferedReader reader;

        public ReceiveMessage(BufferedReader reader) {
            this.reader = reader;
        }

        @Override
        public void run() {
            try {
                String serverResponse;
                while ((serverResponse = reader.readLine()) != null) {
                    System.out.println("Server says: " + serverResponse);
                }
            } catch (IOException e) {
                System.err.println("Error receiving message: " + e.getMessage());
            }
        }
    }
}
