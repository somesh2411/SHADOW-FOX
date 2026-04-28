import java.net.*;

public class Server {

    public static void main(String[] args) throws Exception {

        ServerSocket server = new ServerSocket(5000);
        System.out.println("Server started...");

        while (true) {
            Socket socket = server.accept();

            ClientHandler ch = new ClientHandler(socket);
            new Thread(ch).start();
        }
    }
}