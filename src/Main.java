
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Graph graph = new Graph();
        Scanner scanner = new Scanner(System.in);

        // Load data from files
        try {
            FileManager.loadUsers(graph);
            FileManager.loadFriendships(graph);

            System.out.println("Data loaded successfully.");
            System.out.println("Users: " + graph.getUserCount());
            System.out.println(
                "Friendships: " + graph.getFriendshipCount()
            );

        } catch (IOException e) {
            System.out.println("Error loading data: " + e.getMessage());
            scanner.close();
            return;
        }

        FriendCircleDetector detector =
                new FriendCircleDetector(graph);

        boolean running = true;

        while (running) {
            displayMenu();
            System.out.print("Enter your choice: ");

            String input = scanner.nextLine().trim();

            int choice;

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(
                    "Invalid input. Please enter a number."
                );
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.println(
                        "Total users: " + graph.getUserCount()
                    );
                    break;

                case 2:
                    System.out.println(
                        "Total friendships: "
                        + graph.getFriendshipCount()
                    );
                    break;

                case 3:
                    System.out.println("\n===== SOCIAL NETWORK GRAPH =====");
                    graph.displayGraph();
                    break;

                case 4:
                    detector.displayFriendCircles();
                    break;

                case 5:
                    saveData(graph);
                    break;

                case 0:
                    running = false;
                    System.out.println("Exiting program...");
                    break;

                default:
                    System.out.println(
                        "Invalid choice. Please try again."
                    );
            }
        }

        scanner.close();
    }

    // Display the main menu
    private static void displayMenu() {
        System.out.println("\n==================================");
        System.out.println("  FRIEND CIRCLE DETECTOR");
        System.out.println("==================================");
        System.out.println("1. View Total Users");
        System.out.println("2. View Total Friendships");
        System.out.println("3. View Social Network Graph");
        System.out.println("4. Detect Friend Circles");
        System.out.println("5. Save Data to Files");
        System.out.println("0. Exit");
        System.out.println("==================================");
    }

    // Save users and friendships
    private static void saveData(Graph graph) {
        try {
            FileManager.saveAll(graph);
            System.out.println("Data saved successfully.");
            System.out.println(
                "Users saved to: " + FileManager.USERS_FILE
            );
            System.out.println(
                "Friendships saved to: "
                + FileManager.FRIENDSHIPS_FILE
            );

        } catch (IOException e) {
            System.out.println(
                "Error saving data: " + e.getMessage()
            );
        }
    }
}
