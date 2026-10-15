
import java.io.IOException;

public class Main {

    private static final String FRIENDS_FILE = "friends.txt";
    private static final String FRIENDSHIPS_FILE = "friendships.txt";

    public static void main(String[] args) {
        Graph graph = new Graph();
        InputHandler inputHandler = new InputHandler(graph);

        try {
            // Load friends and friendships from input files
            inputHandler.loadFriends(FRIENDS_FILE);
            inputHandler.loadFriendships(FRIENDSHIPS_FILE);

            System.out.println("==================================");
            System.out.println("  SOCIAL NETWORK FRIEND CIRCLE");
            System.out.println("==================================");

            System.out.println("\nTotal users: " + graph.getFriendCount());
            System.out.println("Total friendships: " + graph.getFriendshipCount());

            System.out.println("\n--- Social Network Graph ---");
            graph.displayGraph();

            // Detect friend circles
            FriendCircleDetector detector = new FriendCircleDetector(graph);

            System.out.println("\n--- Friend Circle Results ---");
            detector.displayFriendCircles();

            System.out.println("\nProgram completed successfully.");

        } catch (IOException e) {
            System.err.println("Error loading input files: " + e.getMessage());
            System.err.println(
                "Please check that friends.txt and friendships.txt "
                + "exist and contain valid data."
            );
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
}
