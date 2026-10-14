package boardease;

import boardease.dao.DatabaseConnection;
import boardease.ui.Menu;

public class Main {
    public static void main(String[] args) {
        // Initialize SQLite Database tables
        DatabaseConnection.initializeDatabase();

        // Launch Application UI
        Menu menu = new Menu();
        menu.start();
    }
}
