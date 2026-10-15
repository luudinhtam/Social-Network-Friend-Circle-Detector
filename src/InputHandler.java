
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class InputHandler {

    private final Graph graph;

    public InputHandler(Graph graph) {
        this.graph = graph;
    }

    // Read friends from friends.txt
    // Format: ID,Name
    public void loadFriends(String fileName) throws IOException {
        Set<Integer> loadedIds = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;
                line = line.trim();

                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                String[] parts = line.split(",", 2);

                if (parts.length != 2) {
                    throw new IOException(
                        "Invalid friend data at line " + lineNumber
                    );
                }

                try {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();

                    if (id < 0 || name.isEmpty()) {
                        throw new IOException(
                            "Invalid ID or name at line " + lineNumber
                        );
                    }

                    if (!loadedIds.add(id)) {
                        throw new IOException(
                            "Duplicate friend ID at line " + lineNumber
                        );
                    }

                    if (!graph.addFriend(new Friend(id, name))) {
                        throw new IOException(
                            "Could not add friend at line " + lineNumber
                        );
                    }

                } catch (NumberFormatException e) {
                    throw new IOException(
                        "Friend ID must be an integer at line " + lineNumber,
                        e
                    );
                }
            }
        }
    }

    // Read friendships from friendships.txt
    // Format: ID1,ID2
    public void loadFriendships(String fileName) throws IOException {
        Set<String> loadedEdges = new HashSet<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;
                line = line.trim();

                if (line.isEmpty() || line.startsWith("#")) {
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
                            "A friend cannot connect to themselves at line "
                            + lineNumber
                        );
                    }

                    if (!graph.containsFriend(id1)
                            || !graph.containsFriend(id2)) {
                        throw new IOException(
                            "Friend ID does not exist at line " + lineNumber
                        );
                    }

                    int minId = Math.min(id1, id2);
                    int maxId = Math.max(id1, id2);
                    String edgeKey = minId + "-" + maxId;

                    if (!loadedEdges.add(edgeKey)) {
                        throw new IOException(
                            "Duplicate friendship at line " + lineNumber
                        );
                    }

                    if (!graph.addFriendship(id1, id2)) {
                        throw new IOException(
                            "Could not add friendship at line " + lineNumber
                        );
                    }

                } catch (NumberFormatException e) {
                    throw new IOException(
                        "Friendship IDs must be integers at line " + lineNumber,
                        e
                    );
                }
            }
        }
    }
}
