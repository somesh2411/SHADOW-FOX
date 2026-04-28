import java.net.*;
import java.io.*;

public class ClientHandler implements Runnable {

    Socket socket;
    BufferedReader in;
    PrintWriter out;

    String name;
    String room = "general";

    public ClientHandler(Socket socket) throws Exception {

        this.socket = socket;

        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        out.println("Enter username:");
        name = in.readLine();

        RoomManager.addUser(name, this);
        RoomManager.joinRoom("general", this);

        RoomManager.broadcast(room, name + " joined " + room);
    }

    public void run() {
        try {
            String msg;

            while ((msg = in.readLine()) != null) {

                // JOIN ROOM
                if (msg.startsWith("/join ")) {
                    String newRoom = msg.substring(6);
                    RoomManager.joinRoom(newRoom, this);
                    out.println("Joined room: " + newRoom);
                    continue;
                }

                // PRIVATE MESSAGE
                if (msg.startsWith("/msg ")) {
                    String[] parts = msg.split(" ", 3);

                    if (parts.length < 3) continue;

                    String target = parts[1];
                    String message = parts[2];

                    ClientHandler user = RoomManager.getUser(target);

                    if (user != null) {
                        user.out.println("(Private) " + name + ": " + message);
                    } else {
                        out.println("User not found");
                    }
                    continue;
                }

                // EXIT
                if (msg.equalsIgnoreCase("exit")) {
                    socket.close();
                    break;
                }

                // NORMAL MESSAGE
                RoomManager.broadcast(room, name + ": " + msg);
            }

        } catch (Exception e) {
            System.out.println(name + " disconnected");
        }
    }
}