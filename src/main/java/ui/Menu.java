package boardease.ui;

import boardease.dao.UserDao;
import boardease.model.User;
import java.util.Scanner;

public class Menu {
    private final UserDao userDao = new UserDao();
    private final Scanner scanner = new Scanner(System.in);
    private User currentUser = null;

    public void start() {
        boolean running = true;
        while (running) {
            if (currentUser == null) {
                running = showMainMenu();
            } else {
                showUserMenu();
            }
        }
        System.out.println("Thank you for using BoardEase. Goodbye!");
    }

    private boolean showMainMenu() {
        System.out.println("\n=== BOARDEASE MAIN MENU ===");
        System.out.println("1. Register Account");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Select an option: ");

        String choice = scanner.nextLine();
        switch (choice) {
            case "1" -> register();
            case "2" -> login();
            case "3" -> {
                return false;
            }
            default -> System.out.println("Invalid option. Please try again.");
        }
        return true;
    }

    private void register() {
        System.out.println("\n--- Register Account ---");
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();

        System.out.println("Select Role:");
        System.out.println("1. ADMIN");
        System.out.println("2. STAFF");
        System.out.println("3. TENANT");
        System.out.print("Choice: ");
        String roleChoice = scanner.nextLine().trim();

        String role = switch (roleChoice) {
            case "1" -> "ADMIN";
            case "2" -> "STAFF";
            case "3" -> "TENANT";
            default -> null;
        };

        if (role == null) {
            System.out.println("Invalid role selection. Registration canceled.");
            return;
        }

        boolean success = userDao.registerUser(username, password, role);
        if (success) {
            System.out.println("Account registered successfully with status 'PENDING'!");
        }
    }

    private void login() {
        System.out.println("\n--- Login ---");
        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Enter password: ");
        String password = scanner.nextLine().trim();

        User user = userDao.loginUser(username, password);
        if (user != null) {
            currentUser = user;
            System.out.println("\nLogin successful! Welcome, " + currentUser.username() + "!");
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    private void showUserMenu() {
        System.out.println("\n=== USER DASHBOARD ===");
        System.out.println("Logged in as: " + currentUser.username());
        System.out.println("Role: " + currentUser.roles());
        System.out.println("Status: " + currentUser.status());
        System.out.println("1. Logout");
        System.out.print("Select an option: ");

        String choice = scanner.nextLine();
        if ("1".equals(choice)) {
            System.out.println("Logging out...");
            currentUser = null;
        } else {
            System.out.println("Invalid option.");
        }
    }
}