

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;

public class Graph {

    private final Map<Integer, User> users;
    private final Map<Integer, List<Integer>> adjacencyList;

    public Graph() {
        users = new LinkedHashMap<>();
        adjacencyList = new LinkedHashMap<>();
    }

    // Add a user to the graph
    public boolean addUser(User user) {
        if (user == null || users.containsKey(user.getId())) {
            return false;
        }

        users.put(user.getId(), user);
        adjacencyList.put(user.getId(), new ArrayList<>());

        return true;
    }

    // Add an undirected friendship between two users
    public boolean addFriendship(int id1, int id2) {
        if (id1 == id2
                || !users.containsKey(id1)
                || !users.containsKey(id2)) {
            return false;
        }

        if (adjacencyList.get(id1).contains(id2)) {
            return false;
        }

        adjacencyList.get(id1).add(id2);
        adjacencyList.get(id2).add(id1);

        return true;
    }

    // Get a user by ID
    public User getUser(int id) {
        return users.get(id);
    }

    // Get all users
    public Collection<User> getAllUsers() {
        return Collections.unmodifiableCollection(users.values());
    }

    // Get adjacent user IDs
    public List<Integer> getNeighbors(int id) {
        List<Integer> neighbors = adjacencyList.get(id);

        if (neighbors == null) {
            return Collections.emptyList();
        }

        return Collections.unmodifiableList(neighbors);
    }

    // Check whether a user exists
    public boolean containsUser(int id) {
        return users.containsKey(id);
    }

    // Get the total number of users
    public int getUserCount() {
        return users.size();
    }

    // Get the total number of unique friendships
    public int getFriendshipCount() {
        int total = 0;

        for (List<Integer> neighbors : adjacencyList.values()) {
            total += neighbors.size();
        }

        return total / 2;
    }

    // Display the social network graph
    public void displayGraph() {
        for (Map.Entry<Integer, List<Integer>> entry
                : adjacencyList.entrySet()) {

            User user = users.get(entry.getKey());

            System.out.print(
                user.getName() + " (ID: " + user.getId() + ") -> "
            );

            List<Integer> neighbors = entry.getValue();

            if (neighbors.isEmpty()) {
                System.out.print("No friends");
            } else {
                for (int i = 0; i < neighbors.size(); i++) {
                    User neighbor = users.get(neighbors.get(i));

                    System.out.print(
                        neighbor.getName() + " (ID: "
                        + neighbor.getId() + ")"
                    );

                    if (i < neighbors.size() - 1) {
                        System.out.print(", ");
                    }
                }
            }

            System.out.println();
        }
    }
}