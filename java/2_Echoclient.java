import java.io.*;
import java.net.*;

public class EchoClient {
    public static void main(String[] args) {
        final String SERVER_ADDRESS = "localhost";
        final int SERVER_PORT = 12345;

        try {
            Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            System.out.println("Connected to server: " + socket);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

            String inputLine;
            while ((inputLine = userInput.readLine()) != null) {
                writer.println(inputLine); // Send user input to server

                if (inputLine.equals("exit")) {
                    break; // Exit loop if user types "exit"
                }

                String serverResponse = reader.readLine();
                System.out.println("Server says: " + serverResponse);
            }

            userInput.close();
            reader.close();
            writer.close();
            socket.close();
        } catch (IOException e) {
            System.err.println("Error in client: " + e.getMessage());
        }
    }
}

