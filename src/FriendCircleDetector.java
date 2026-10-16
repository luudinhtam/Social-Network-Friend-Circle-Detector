
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class FriendCircleDetector {

    private final Graph graph;

    public FriendCircleDetector(Graph graph) {
        this.graph = graph;
    }

    // Find all connected components using BFS
    public List<List<User>> findFriendCircles() {
        List<List<User>> friendCircles = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();

        for (User user : graph.getAllUsers()) {
            int userId = user.getId();

            if (!visited.contains(userId)) {
                List<User> circle = bfs(userId, visited);
                friendCircles.add(circle);
            }
        }

        return friendCircles;
    }

    // BFS to find all users in the same friend circle
    private List<User> bfs(int startId, Set<Integer> visited) {
        List<User> circle = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(startId);
        visited.add(startId);

        while (!queue.isEmpty()) {
            int currentId = queue.poll();
            User currentUser = graph.getUser(currentId);

            if (currentUser != null) {
                circle.add(currentUser);
            }

            for (int neighborId : graph.getNeighbors(currentId)) {
                if (!visited.contains(neighborId)) {
                    visited.add(neighborId);
                    queue.offer(neighborId);
                }
            }
        }

        return circle;
    }

    // Count the total number of friend circles
    public int countFriendCircles() {
        return findFriendCircles().size();
    }

    // Display all friend circles
    public void displayFriendCircles() {
        List<List<User>> friendCircles = findFriendCircles();

        System.out.println("Total Friend Circles: " + friendCircles.size());

        for (int i = 0; i < friendCircles.size(); i++) {
            List<User> circle = friendCircles.get(i);

            System.out.println("\nFriend Circle " + (i + 1) + ":");

            for (User user : circle) {
                System.out.println(
                    "ID: " + user.getId()
                    + ", Name: " + user.getName()
                );
            }

            System.out.println("Number of users: " + circle.size());
        }
    }
}
