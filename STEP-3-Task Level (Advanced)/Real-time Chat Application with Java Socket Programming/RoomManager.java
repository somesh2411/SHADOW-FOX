import java.util.*;

public class RoomManager {

    static Map<String, List<ClientHandler>> rooms = new HashMap<>();
    static Map<String, ClientHandler> users = new HashMap<>();

    public static void addUser(String name, ClientHandler ch) {
        users.put(name, ch);
    }

    public static ClientHandler getUser(String name) {
        return users.get(name);
    }

    public static void joinRoom(String room, ClientHandler ch) {

        rooms.putIfAbsent(room, new ArrayList<>());
        rooms.get(room).add(ch);

        ch.room = room;
    }

    public static void broadcast(String room, String message) {

        List<ClientHandler> list = rooms.get(room);
        if (list != null) {
            for (ClientHandler c : list) {
                c.out.println(message);
            }
        }
    }
}