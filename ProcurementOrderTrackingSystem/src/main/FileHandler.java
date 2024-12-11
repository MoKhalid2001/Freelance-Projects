
package main;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    // Define file names for storing different data
    private static final String USER_FILE = "user_credentials.txt";
    private static final String ITEM_FILE = "items.txt";
    private static final String SALES_ENTERIES_FILE = "sales_entries.txt";
    private static final String SUPPLIER_FILE = "suppliers.txt";
    private static final String PR_FILE = "purchase_requisitions.txt";
    private static final String PO_FILE = "purchase_orders.txt";

    // Save user data to a file
    public static void saveUser(String name, String username, String password, String role) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE, true))) {
            writer.write(name + "," + username + "," + password + "," + role);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load all users from the file
    public static List<String[]> loadUsers() {
        List<String[]> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                users.add(line.split(","));  // Split line into name, username, password, and role
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    // Placeholder method for saving item details to a file
    public static void saveItem(String itemCode, String itemName, String supplierId) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ITEM_FILE, true))) {
            writer.write(itemCode + "," + itemName + "," + supplierId);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Placeholder method for loading all items from the file
    public static List<String[]> loadItems() {
        List<String[]> items = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ITEM_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                items.add(line.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }
    
    // Placeholder method for loading all items from the file
    public static List<String[]> loadSalesEnteries() {
        List<String[]> items = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(SALES_ENTERIES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                items.add(line.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return items;
    }

    // Placeholder method for saving supplier details to a file
    public static void saveSupplier(String supplierCode, String supplierName, String itemId) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SUPPLIER_FILE, true))) {
            writer.write(supplierCode + "," + supplierName + "," + itemId);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Placeholder method for loading all suppliers from the file
    public static List<String[]> loadSuppliers() {
        List<String[]> suppliers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(SUPPLIER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                suppliers.add(line.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return suppliers;
    }

    // Placeholder method for saving purchase requisition (PR) details to a file
    public static void savePR(String prId, String itemCode, String quantity, String requiredDate, String smUsername) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PR_FILE, true))) {
            writer.write(prId + "," + itemCode + "," + quantity + "," + requiredDate + "," + smUsername);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Placeholder method for loading all purchase requisitions (PRs) from the file
    public static List<String[]> loadPRs() {
        List<String[]> prs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PR_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                prs.add(line.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prs;
    }

    // Placeholder method for saving purchase order (PO) details to a file
    public static void savePO(String poId, String prId, String pmUsername, String status) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(PO_FILE, true))) {
            writer.write(poId + "," + prId + "," + pmUsername + "," + status);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Placeholder method for loading all purchase orders (POs) from the file
    public static List<String[]> loadPOs() {
        List<String[]> pos = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PO_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                pos.add(line.split(","));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pos;
    }
}
