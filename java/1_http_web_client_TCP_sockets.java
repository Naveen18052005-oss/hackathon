import java.io.*;
import java.net.*;

public class SimpleWebClient {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java SimpleWebClient <URL>");
            return;
        }

        String urlString = args[0];
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String inputLine;

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Writing the response to a HTML file
                String fileName = "downloaded_page.html";
                FileWriter fileWriter = new FileWriter(fileName);
                fileWriter.write(response.toString());
                fileWriter.close();

                System.out.println("Web page content has been downloaded and saved as " + fileName);
            } else {
                System.err.println("Error: HTTP response code " + responseCode);
            }
            connection.disconnect();
        } catch (MalformedURLException e) {
            System.err.println("Invalid URL: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error accessing URL: " + e.getMessage());
        }
    }
}
