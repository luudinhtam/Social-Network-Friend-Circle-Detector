
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
    public List<List<Friend>> findFriendCircles() {
        List<List<Friend>> friendCircles = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();

        for (Friend friend : graph.getAllFriends()) {
            int friendId = friend.getId();

            if (!visited.contains(friendId)) {
                List<Friend> circle = bfs(friendId, visited);
                friendCircles.add(circle);
            }
        }

        return friendCircles;
    }

    // BFS to find all friends in the same circle
    private List<Friend> bfs(int startId, Set<Integer> visited) {
        List<Friend> circle = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(startId);
        visited.add(startId);

        while (!queue.isEmpty()) {
            int currentId = queue.poll();
            Friend currentFriend = graph.getFriend(currentId);

            if (currentFriend != null) {
                circle.add(currentFriend);
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
        List<List<Friend>> friendCircles = findFriendCircles();

        System.out.println("Total Friend Circles: " + friendCircles.size());

        for (int i = 0; i < friendCircles.size(); i++) {
            List<Friend> circle = friendCircles.get(i);

            System.out.println("\nFriend Circle " + (i + 1) + ":");

            for (Friend friend : circle) {
                System.out.println(
                    "ID: " + friend.getId()
                    + ", Name: " + friend.getName()
                );
            }

            System.out.println("Number of friends: " + circle.size());
        }
    }
}
