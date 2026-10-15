
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



public class Graph {
    private final Map<Integer, Friend> friends;
    private final Map<Integer, List<Integer>> adjacencyList;

    public Graph() {
        friends = new LinkedHashMap<>();
        adjacencyList = new LinkedHashMap<>();
    }

    // Add a friend to the graph
    public boolean addFriend(Friend friend) {
        if (friend == null || friends.containsKey(friend.getId())) {
            return false;
        }

        friends.put(friend.getId(), friend);
        adjacencyList.put(friend.getId(), new ArrayList<>());
        return true;
    }

    // Add an undirected friendship between two friends
    public boolean addFriendship(int id1, int id2) {
        if (id1 == id2
                || !friends.containsKey(id1)
                || !friends.containsKey(id2)) {
            return false;
        }

        if (adjacencyList.get(id1).contains(id2)) {
            return false;
        }

        adjacencyList.get(id1).add(id2);
        adjacencyList.get(id2).add(id1);
        return true;
    }

    // Get a friend by ID
    public Friend getFriend(int id) {
        return friends.get(id);
    }

    // Get all friends
    public Collection<Friend> getAllFriends() {
        return Collections.unmodifiableCollection(friends.values());
    }

    // Get adjacent friend IDs
    public List<Integer> getNeighbors(int id) {
        List<Integer> neighbors = adjacencyList.get(id);

        if (neighbors == null) {
            return Collections.emptyList();
        }

        return Collections.unmodifiableList(neighbors);
    }

    // Check whether a friend exists
    public boolean containsFriend(int id) {
        return friends.containsKey(id);
    }

    // Get the number of friends
    public int getFriendCount() {
        return friends.size();
    }

    // Get the number of unique friendships
    public int getFriendshipCount() {
        int total = 0;

        for (List<Integer> neighbors : adjacencyList.values()) {
            total += neighbors.size();
        }

        return total / 2;
    }

    // Display the graph
    public void displayGraph() {
        for (Map.Entry<Integer, List<Integer>> entry
                : adjacencyList.entrySet()) {

            Friend friend = friends.get(entry.getKey());

            System.out.print(friend.getName() + " (ID: "
                    + friend.getId() + ") -> ");

            List<Integer> neighbors = entry.getValue();

            for (int i = 0; i < neighbors.size(); i++) {
                Friend neighbor = friends.get(neighbors.get(i));

                System.out.print(neighbor.getName()
                        + " (ID: " + neighbor.getId() + ")");

                if (i < neighbors.size() - 1) {
                    System.out.print(", ");
                }
            }

            System.out.println();
        }
    }
}
