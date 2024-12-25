import java.util.HashMap;
import java.util.Scanner;

public class OnlineReservationSystem {
    private static HashMap<String, String> userDatabase = new HashMap<>();
    private static HashMap<Integer, String> reservations = new HashMap<>();
    private static int currentPNR = 1500;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeUsers();

        System.out.println("Welcome to the Online Reservation System");
        if (login(scanner)) {
            int choice;
            do {
                System.out.println("\n1. Make Reservation");
                System.out.println("2. Cancel Reservation");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> Reservation(scanner);
                    case 2 -> cancelReservation(scanner);
                    case 3 -> System.out.println("Thank you for using the system. Goodbye!");
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } while (choice != 3);
        } else {
            System.out.println("Login failed. Exiting the system.");
        }
    }

    private static void initializeUsers() {
        userDatabase.put("janu", "janu_7");
        userDatabase.put("padu", "padu_1");
        userDatabase.put("kumari", "kumari_1");
        userDatabase.put("nuthan", "nuthan_1");
    }

    private static boolean login(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();

        if (userDatabase.containsKey(username) && userDatabase.get(username).equals(password)) {
            System.out.println("Login successful. Welcome, " + username + "!");
            return true;
        } else {
            System.out.println("Invalid username or password.");
            return false;
        }
    }

    private static void Reservation(Scanner scanner) {
        scanner.nextLine(); 
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter train number: ");
        int trainNumber = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Enter class type (e.g., Sleeper, AC): ");
        String classType = scanner.nextLine();
        System.out.print("Enter date of journey (YYYY-MM-DD): ");
        String dateOfJourney = scanner.nextLine();
        System.out.print("Enter departure location: ");
        String from = scanner.nextLine();
        System.out.print("Enter destination: ");
        String to = scanner.nextLine();

        int pnr = currentPNR++;
        String reservationDetails = "Name: " + name + ", Train No: " + trainNumber + ", Class: " + classType +
                ", Date: " + dateOfJourney + ", From: " + from + ", To: " + to;
        reservations.put(pnr, reservationDetails);

        System.out.println("Reservation successful! Your PNR is " + pnr);
    }

    private static void cancelReservation(Scanner scanner) {
        System.out.print("Enter your PNR number: ");
        int pnr = scanner.nextInt();

        if (reservations.containsKey(pnr)) {
            System.out.println("Reservation found: " + reservations.get(pnr));
            System.out.print("Are you sure you want to cancel this reservation? (yes/no): ");
            String confirmation = scanner.next();

            if (confirmation.equalsIgnoreCase("yes")) {
                reservations.remove(pnr);
                System.out.println("Reservation canceled successfully.");
            } else {
                System.out.println("Cancellation aborted.");
            }
        } else {
            System.out.println("No reservation found with PNR: " + pnr);
        }
    }
}