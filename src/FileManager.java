
import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class FileManager {

    // File names
    public static final String USERS_FILE = "users.txt";
    public static final String FRIENDSHIPS_FILE = "friendships.txt";

    // =========================================
    // LOAD USERS
    // =========================================
    public static void loadUsers(Graph graph) throws IOException {
        loadUsers(graph, USERS_FILE);
    }

    public static void loadUsers(Graph graph, String fileName)
            throws IOException {

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(fileName))) {

            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();

                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",", 2);

                if (parts.length != 2) {
                    throw new IOException(
                        "Invalid user data at line " + lineNumber
                    );
                }

                try {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();

                    if (name.isEmpty()) {
                        throw new IOException(
                            "Empty user name at line " + lineNumber
                        );
                    }

                    if (!graph.addUser(new User(id, name))) {
                        throw new IOException(
                            "Duplicate user ID: " + id
                        );
                    }

                } catch (NumberFormatException e) {
                    throw new IOException(
                        "Invalid user ID at line " + lineNumber
                    );
                }
            }
        }
    }

    // =========================================
    // LOAD FRIENDSHIPS
    // =========================================
    public static void loadFriendships(Graph graph)
            throws IOException {
        loadFriendships(graph, FRIENDSHIPS_FILE);
    }

    public static void loadFriendships(Graph graph, String fileName)
            throws IOException {

        Set<String> friendships = new HashSet<>();

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(fileName))) {

            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();

                if (line.isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");

                if (parts.length != 2) {
                    throw new IOException(
                        "Invalid friendship data at line " + lineNumber
                    );
                }

                try {
                    int id1 = Integer.parseInt(parts[0].trim());
                    int id2 = Integer.parseInt(parts[1].trim());

                    if (id1 == id2) {
                        throw new IOException(
                            "Self friendship at line " + lineNumber
                        );
                    }

                    if (!graph.containsUser(id1)
                            || !graph.containsUser(id2)) {
                        throw new IOException(
                            "Unknown user ID at line " + lineNumber
                        );
                    }

                    int smallerId = Math.min(id1, id2);
                    int largerId = Math.max(id1, id2);
                    String key = smallerId + "-" + largerId;

                    if (!friendships.add(key)) {
                        throw new IOException(
                            "Duplicate friendship at line " + lineNumber
                        );
                    }

                    if (!graph.addFriendship(id1, id2)) {
                        throw new IOException(
                            "Could not add friendship at line "
                            + lineNumber
                        );
                    }

                } catch (NumberFormatException e) {
                    throw new IOException(
                        "Invalid user ID at line " + lineNumber
                    );
                }
            }
        }
    }

    // =========================================
    // SAVE USERS
    // =========================================
    public static void saveUsers(Graph graph) throws IOException {
        saveUsers(graph, USERS_FILE);
    }

    public static void saveUsers(Graph graph, String fileName)
            throws IOException {

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(fileName))) {

            for (User user : graph.getAllUsers()) {
                writer.write(user.getId() + "," + user.getName());
                writer.newLine();
            }
        }
    }

    // =========================================
    // SAVE FRIENDSHIPS
    // =========================================
    public static void saveFriendships(Graph graph)
            throws IOException {
        saveFriendships(graph, FRIENDSHIPS_FILE);
    }

    public static void saveFriendships(Graph graph, String fileName)
            throws IOException {

        try (BufferedWriter writer =
                     new BufferedWriter(new FileWriter(fileName))) {

            Set<String> written = new HashSet<>();

            for (User user : graph.getAllUsers()) {
                int id1 = user.getId();

                for (int id2 : graph.getNeighbors(id1)) {
                    int smallerId = Math.min(id1, id2);
                    int largerId = Math.max(id1, id2);
                    String key = smallerId + "-" + largerId;

                    // Save each undirected friendship only once
                    if (written.add(key)) {
                        writer.write(smallerId + "," + largerId);
                        writer.newLine();
                    }
                }
            }
        }
    }

    // =========================================
    // SAVE ALL DATA
    // =========================================
    public static void saveAll(Graph graph) throws IOException {
        saveUsers(graph);
        saveFriendships(graph);
    }
}
