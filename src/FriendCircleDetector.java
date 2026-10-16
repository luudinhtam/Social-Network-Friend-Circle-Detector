
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

    // Detect all friend circles using BFS
    public List<List<User>> detectFriendCircles() {
        List<List<User>> friendCircles = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();

        for (User user : graph.getAllUsers()) {
            if (!visited.contains(user.getId())) {
                List<User> circle = new ArrayList<>();
                Queue<Integer> queue = new LinkedList<>();

                queue.offer(user.getId());
                visited.add(user.getId());

                while (!queue.isEmpty()) {
                    int currentId = queue.poll();
                    User currentUser = graph.getUser(currentId);

                    circle.add(currentUser);

                    for (int neighborId : graph.getNeighbors(currentId)) {
                        if (!visited.contains(neighborId)) {
                            visited.add(neighborId);
                            queue.offer(neighborId);
                        }
                    }
                }

                friendCircles.add(circle);
            }
        }

        return friendCircles;
    }

    // Display all detected friend circles
    public void displayFriendCircles() {
        List<List<User>> circles = detectFriendCircles();

        System.out.println("\n===== FRIEND CIRCLE RESULTS =====");

        for (int i = 0; i < circles.size(); i++) {
            List<User> circle = circles.get(i);

            System.out.println("\nFriend Circle " + (i + 1) + ":");

            for (User user : circle) {
                System.out.println(
                    "- " + user.getName() + " (ID: " + user.getId() + ")"
                );
            }

            System.out.println("Total users: " + circle.size());
        }

        System.out.println("\nTotal friend circles: " + circles.size());
    }

    // Get the total number of friend circles
    public int getFriendCircleCount() {
        return detectFriendCircles().size();
    }
}