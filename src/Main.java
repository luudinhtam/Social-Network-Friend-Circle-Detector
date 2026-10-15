
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static final String FRIENDS_FILE = "friends.txt";
    private static final String FRIENDSHIPS_FILE = "friendships.txt";

    public static void main(String[] args) {
        Graph graph = new Graph();
        InputHandler inputHandler = new InputHandler(graph);

        try {
            inputHandler.loadFriends(FRIENDS_FILE);
            inputHandler.loadFriendships(FRIENDSHIPS_FILE);
        } catch (IOException e) {
            System.err.println("Error loading input files: " + e.getMessage());
            return;
        }

        FriendCircleDetector detector = new FriendCircleDetector(graph);
        int choice = 0;
        try (Scanner scanner = new Scanner(System.in)) {
            do {
                displayMenu();

                System.out.print("Enter your choice: ");

                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input! Please enter a number.\n");
                    scanner.nextLine();
                    continue;
                }

                choice = scanner.nextInt();
                scanner.nextLine();

                System.out.println();

                switch (choice) {
                    case 1:
                        viewTotalUsers(graph);
                        break;

                    case 2:
                        viewTotalFriendships(graph);
                        break;

                    case 3:
                        viewSocialNetworkGraph(graph);
                        break;

                    case 4:
                        viewFriendCircleResults(detector);
                        break;

                    case 0:
                        System.out.println("Exiting program. Goodbye!");
                        break;

                    default:
                        System.out.println(
                            "Invalid choice! Please select 0-4."
                        );
                }

            } while (choice != 0);
        }
    }

    private static void displayMenu() {
        System.out.println("==========================================");
        System.out.println("     SOCIAL NETWORK FRIEND CIRCLE");
        System.out.println("==========================================");
        System.out.println("1. View Total Users");
        System.out.println("2. View Total Friendships");
        System.out.println("3. View Social Network Graph");
        System.out.println("4. View Friend Circle Results");
        System.out.println("0. Exit");
        System.out.println("==========================================");
    }

    private static void viewTotalUsers(Graph graph) {
        System.out.println("---------- TOTAL USERS ----------");
        System.out.println("Total users: " + graph.getFriendCount());
    }

    private static void viewTotalFriendships(Graph graph) {
        System.out.println("------- TOTAL FRIENDSHIPS -------");
        System.out.println(
            "Total friendships: " + graph.getFriendshipCount()
        );
    }

    private static void viewSocialNetworkGraph(Graph graph) {
        System.out.println("------- SOCIAL NETWORK GRAPH -------");
        graph.displayGraph();
    }

    private static void viewFriendCircleResults(
            FriendCircleDetector detector) {
        System.out.println("------- FRIEND CIRCLE RESULTS -------");
        detector.displayFriendCircles();
    }
}
