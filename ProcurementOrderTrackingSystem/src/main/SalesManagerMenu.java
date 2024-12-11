
package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class SalesManagerMenu extends javax.swing.JFrame {
    
    private String username;
    
    private DefaultTableModel itemList_tableModel, salesEntry_tableModel, salesReport_tableModel,
                              stockLevel_tableModel, purchaseOrder_tableModel;
    public SalesManagerMenu(String username) {
        initComponents();
        this.username = username;
        /******************* Item List **********************************/
        String[] itemListcolumnNames = {"Item Code", "Item Name", "Supplier ID"};
        itemList_tableModel = new DefaultTableModel(itemListcolumnNames, 0);
        itemListTable.setModel(itemList_tableModel);
        // Load items from file and populate the table
        List<String[]> itemList = FileHandler.loadItems();
        for (String[] item : itemList) {
            itemList_tableModel.addRow(item);  // Each item array is added as a row to the table
        }
        /******************* Item List **********************************/
        
        /******************* Sales Entry **********************************/
        String[] SalesEntrycolumnNames = {"Item", "Quantity", "Date"};
        salesEntry_tableModel = new DefaultTableModel(SalesEntrycolumnNames, 0);
        salesEntryTable.setModel(salesEntry_tableModel);
        // Load items from file and populate the table
        List<String[]> salesEntryList = FileHandler.loadSalesEnteries();
        for (String[] item : salesEntryList) {
            salesEntry_tableModel.addRow(item);  // Each item array is added as a row to the table
        }
        loadItemsForDropdown();
        /******************* Sales Entry **********************************/
        
        /******************* Sales Report **********************************/
        String[] SalesReportcolumnNames = {"Item", "Total Quantity Sold"};
        salesReport_tableModel = new DefaultTableModel(SalesReportcolumnNames, 0);
        salesReportTable.setModel(salesReport_tableModel); 
        populateSalesReport();
        /******************* Sales Report **********************************/
        
        /******************* Stock Level **********************************/
        String[] StockLevelcolumnNames = {"Item", "Current Stock", "Status"};
        stockLevel_tableModel = new DefaultTableModel(StockLevelcolumnNames, 0);
        stockLevelTable.setModel(stockLevel_tableModel);
        populateStockLevels();
        /******************* Stock Level **********************************/
        
        /******************* Purchase Order **********************************/
        String[] PurchaseOrdercolumnNames = {"PO ID", "PR ID", "Status"};
        purchaseOrder_tableModel = new DefaultTableModel(PurchaseOrdercolumnNames, 0);
        PurchaseOrderTable.setModel(purchaseOrder_tableModel);
        loadPurchaseOrders();
        /******************* Purchase Order **********************************/
    }
    
    // Load items into the dropdown from items.txt
    private void loadItemsForDropdown() {
        List<String[]> items = FileHandler.loadItems();
        for (String[] item : items) {
            itemComboBox.addItem(item[1]);  // Add only the item name to the dropdown
            itemComboBox1.addItem(item[1]);  // Add only the item name to the dropdown
        }
    }
    // Load sales data from file and populate the report
    private void populateSalesReport() {
        Map<String, Integer> salesSummary = loadSalesData();

        for (Map.Entry<String, Integer> entry : salesSummary.entrySet()) {
            String item = entry.getKey();
            Integer totalQuantity = entry.getValue();
            salesReport_tableModel.addRow(new Object[]{item, totalQuantity});
        }
    }
    // Load sales data from sales_entries.txt and summarize it
    private Map<String, Integer> loadSalesData() {
        Map<String, Integer> salesSummary = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader("sales_entries.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {  // Ensure data has the expected format: Item, Quantity, Date
                    String item = data[0];
                    int quantity = Integer.parseInt(data[1]);

                    // Summarize quantity for each item
                    salesSummary.put(item, salesSummary.getOrDefault(item, 0) + quantity);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading sales data.");
            e.printStackTrace();
        }

        return salesSummary;
    }
    
    // Load stock levels from file and populate the table
    private void populateStockLevels() {
        try (BufferedReader reader = new BufferedReader(new FileReader("stock_levels.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 2) {  // Ensure data has the expected format: Item, Stock
                    String item = data[0];
                    int stock = Integer.parseInt(data[1]);
                    String status = (stock < 5) ? "Low" : "Sufficient";  // Set threshold for low stock

                    // Add each item and its stock status to the table
                    stockLevel_tableModel.addRow(new Object[]{item, stock, status});
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading stock data.");
            e.printStackTrace();
        }
    }
    
    // Load purchase orders from file and populate the table
    private void loadPurchaseOrders() {
        try (BufferedReader reader = new BufferedReader(new FileReader("purchase_orders.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 3) {  // Ensure data has the expected format: PO ID, PR ID, Status
                    String poId = data[0];
                    String prId = data[1];
                    String status = data[6];
                    
                    // Add each purchase order to the table
                    purchaseOrder_tableModel.addRow(new Object[]{poId, prId, status});
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading purchase orders.");
            e.printStackTrace();
        }
    }
    
    // Add a new sales entry to the table
    private void addSalesEntry() {
        String selectedItem = (String) itemComboBox.getSelectedItem();
        String quantity = salesEntryQuantityField.getText();
        String date = salesEntryDateField.getText();

        if (!quantity.isEmpty() && !date.isEmpty()) {
            salesEntry_tableModel.addRow(new Object[]{selectedItem, quantity, date});
            salesEntryQuantityField.setText("");
            salesEntryDateField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Please enter both quantity and date.");
        }
    }
    
    // Edit the selected sales entry
    private void editSalesEntry() {
        int selectedRow = salesEntryTable.getSelectedRow();
        if (selectedRow >= 0) {
            String selectedItem = (String) itemComboBox.getSelectedItem();
            String quantity = salesEntryQuantityField.getText();
            String date = salesEntryDateField.getText();

            salesEntry_tableModel.setValueAt(selectedItem, selectedRow, 0);
            salesEntry_tableModel.setValueAt(quantity, selectedRow, 1);
            salesEntry_tableModel.setValueAt(date, selectedRow, 2);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to edit.");
        }
    }
    
    // Delete the selected sales entry
    private void deleteSalesEntry() {
        int selectedRow = salesEntryTable.getSelectedRow();
        if (selectedRow >= 0) {
            salesEntry_tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
        }
    }
    
    // Save all sales entries to a file
    private void saveSalesEntries() {
        // Collect all rows from the table and save to a file
        int rowCount = salesEntry_tableModel.getRowCount();
        try (PrintWriter writer = new PrintWriter(new FileWriter("sales_entries.txt", true))) {
            for (int i = 0; i < rowCount; i++) {
                String item = (String) salesEntry_tableModel.getValueAt(i, 0);
                String quantity = (String) salesEntry_tableModel.getValueAt(i, 1);
                String date = (String) salesEntry_tableModel.getValueAt(i, 2);
                writer.println(item + "," + quantity + "," + date);
            }
            JOptionPane.showMessageDialog(this, "Sales entries saved successfully.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saving sales entries.");
            e.printStackTrace();
        }
    }
    
    // Helper method to generate a unique Requisition ID
    private String generateRequisitionID() {
        return "R" + System.currentTimeMillis(); // Use current time as a unique ID
    }
    
    // Helper method to fetch the item code based on the item name
    private String fetchItemCode(String itemName) {
        try (BufferedReader reader = new BufferedReader(new FileReader("items.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // Assuming items.txt has "ItemCode,ItemName,SupplierID"
                if (parts[1].equalsIgnoreCase(itemName)) { // Match item name (case-insensitive)
                    return parts[0]; // Return the item code
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error fetching item code.");
            e.printStackTrace();
        }
        return null; // Return null if item code is not found
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        createPurchasequantityField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        itemListTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        itemComboBox = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        salesEntryQuantityField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        salesEntryDateField = new javax.swing.JTextField();
        saveButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        addButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        salesEntryTable = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        salesReportTable = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        stockLevelTable = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        submitButton = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        itemComboBox1 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        createPurchasequantityField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        createPurchasedateField = new javax.swing.JTextField();
        createPurchaseitemCodeField = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        PurchaseOrderTable = new javax.swing.JTable();
        backButton = new javax.swing.JButton();

        createPurchasequantityField1.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        createPurchasequantityField1.setForeground(new java.awt.Color(26, 26, 25));
        createPurchasequantityField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createPurchasequantityField1ActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(26, 26, 25));
        jLabel1.setText("Sales Manager Menu");

        jTabbedPane1.setForeground(new java.awt.Color(26, 26, 25));
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(26, 26, 25));
        jLabel2.setText("Item List");

        itemListTable.setForeground(new java.awt.Color(26, 26, 25));
        itemListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(itemListTable);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(247, 247, 247))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 679, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("View Items", jPanel2);

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(26, 26, 25));
        jLabel3.setText("Sales Entry");

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(26, 26, 25));
        jLabel4.setText("Select Item:");

        itemComboBox.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        itemComboBox.setForeground(new java.awt.Color(26, 26, 25));
        itemComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemComboBoxActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(26, 26, 25));
        jLabel5.setText("Sales Quantity:");

        salesEntryQuantityField.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        salesEntryQuantityField.setForeground(new java.awt.Color(26, 26, 25));
        salesEntryQuantityField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salesEntryQuantityFieldActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(26, 26, 25));
        jLabel6.setText("Sales Date (YYYY-MM-DD):");

        salesEntryDateField.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        salesEntryDateField.setForeground(new java.awt.Color(26, 26, 25));
        salesEntryDateField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salesEntryDateFieldActionPerformed(evt);
            }
        });

        saveButton.setBackground(new java.awt.Color(183, 224, 255));
        saveButton.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        saveButton.setForeground(new java.awt.Color(26, 26, 25));
        saveButton.setText("Save");
        saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveButtonActionPerformed(evt);
            }
        });

        deleteButton.setBackground(new java.awt.Color(183, 224, 255));
        deleteButton.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        deleteButton.setForeground(new java.awt.Color(26, 26, 25));
        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        editButton.setBackground(new java.awt.Color(183, 224, 255));
        editButton.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        editButton.setForeground(new java.awt.Color(26, 26, 25));
        editButton.setText("Edit");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        addButton.setBackground(new java.awt.Color(183, 224, 255));
        addButton.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        addButton.setForeground(new java.awt.Color(26, 26, 25));
        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        salesEntryTable.setForeground(new java.awt.Color(26, 26, 25));
        salesEntryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(salesEntryTable);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(208, 208, 208))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(itemComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(salesEntryQuantityField, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(salesEntryDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(addButton)
                        .addGap(18, 18, 18)
                        .addComponent(editButton)
                        .addGap(18, 18, 18)
                        .addComponent(deleteButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(saveButton)))
                .addContainerGap(171, Short.MAX_VALUE))
            .addComponent(jScrollPane2)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(itemComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(salesEntryQuantityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(salesEntryDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton)
                    .addComponent(editButton)
                    .addComponent(deleteButton)
                    .addComponent(saveButton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 477, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Daily Sales Entry", jPanel3);

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(26, 26, 25));
        jLabel7.setText("Sales Report");

        salesReportTable.setForeground(new java.awt.Color(26, 26, 25));
        salesReportTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(salesReportTable);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(193, 193, 193))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Sales Report", jPanel4);

        jLabel8.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(26, 26, 25));
        jLabel8.setText("Stock Level");

        stockLevelTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(stockLevelTable);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(190, 190, 190))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Check Stock Level", jPanel5);

        jLabel9.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(26, 26, 25));
        jLabel9.setText("Create Purchase Requisition");

        submitButton.setBackground(new java.awt.Color(183, 224, 255));
        submitButton.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        submitButton.setForeground(new java.awt.Color(26, 26, 25));
        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(26, 26, 25));
        jLabel10.setText("Select Item:");

        itemComboBox1.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        itemComboBox1.setForeground(new java.awt.Color(26, 26, 25));
        itemComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemComboBox1ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(26, 26, 25));
        jLabel11.setText("Quantity Needed:");

        createPurchasequantityField.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        createPurchasequantityField.setForeground(new java.awt.Color(26, 26, 25));
        createPurchasequantityField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createPurchasequantityFieldActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(26, 26, 25));
        jLabel12.setText("Sales Date (YYYY-MM-DD):");

        createPurchasedateField.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        createPurchasedateField.setForeground(new java.awt.Color(26, 26, 25));
        createPurchasedateField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createPurchasedateFieldActionPerformed(evt);
            }
        });

        createPurchaseitemCodeField.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        createPurchaseitemCodeField.setForeground(new java.awt.Color(26, 26, 25));
        createPurchaseitemCodeField.setEnabled(false);
        createPurchaseitemCodeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createPurchaseitemCodeFieldActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(26, 26, 25));
        jLabel14.setText("Item Code:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(173, 173, 173))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(itemComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(createPurchaseitemCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(58, Short.MAX_VALUE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(createPurchasequantityField, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(createPurchasedateField, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(31, 31, 31)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(itemComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(createPurchaseitemCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(createPurchasequantityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(createPurchasedateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68)
                .addComponent(submitButton)
                .addContainerGap(74, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Create Requisition", jPanel6);

        jLabel13.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(26, 26, 25));
        jLabel13.setText("Purchase Order List");

        PurchaseOrderTable.setForeground(new java.awt.Color(26, 26, 25));
        PurchaseOrderTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane5.setViewportView(PurchaseOrderTable);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(225, 225, 225)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 667, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 704, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("View Purchase Orders", jPanel7);

        backButton.setBackground(new java.awt.Color(242, 242, 242));
        backButton.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        backButton.setForeground(new java.awt.Color(231, 143, 129));
        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/back-button.png"))); // NOI18N
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 679, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(backButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(131, 131, 131))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(backButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 433, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemComboBoxActionPerformed

    private void salesEntryQuantityFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salesEntryQuantityFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_salesEntryQuantityFieldActionPerformed

    private void salesEntryDateFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salesEntryDateFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_salesEntryDateFieldActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        // TODO add your handling code here:
        saveSalesEntries();
    }//GEN-LAST:event_saveButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        deleteSalesEntry();
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        // TODO add your handling code here:
        editSalesEntry();
    }//GEN-LAST:event_editButtonActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:
        addSalesEntry();
    }//GEN-LAST:event_addButtonActionPerformed

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        // TODO add your handling code here:
        String itemName = (String) itemComboBox1.getSelectedItem();
        String quantity = createPurchasequantityField.getText();
        String date = createPurchasedateField.getText(); // Automatically use the current date
        String Role = "Sales"; // Manager type (Sales)

        // Generate a unique Requisition ID
        String requisitionID = generateRequisitionID();

        // Fetch the item code based on the selected item name
         String itemCode = fetchItemCode(itemName);
         
            if (!quantity.isEmpty() && !date.isEmpty() && itemCode != null) {
            try (PrintWriter writer = new PrintWriter(new FileWriter("requisitions.txt", true))) {
                // Write the requisition details to the file
                writer.println(requisitionID + "," + date + "," + quantity + "," + itemCode + "," + itemName + "," + username + "," + Role);
                JOptionPane.showMessageDialog(this, "Requisition submitted successfully.");

                // Clear input fields after submission
                createPurchasequantityField.setText("");
                createPurchasedateField.setText("");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error saving requisition.");
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please enter the quantity, date, and select an item.");
        }
    }//GEN-LAST:event_submitButtonActionPerformed

    private void itemComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemComboBox1ActionPerformed
        // TODO add your handling code here:
        String selectedItemName = (String) itemComboBox1.getSelectedItem();
        String itemCode = fetchItemCode(selectedItemName);
        createPurchaseitemCodeField.setText(itemCode != null ? itemCode : "Unknown"); // Update the text field
    }//GEN-LAST:event_itemComboBox1ActionPerformed

    private void createPurchasequantityFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createPurchasequantityFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_createPurchasequantityFieldActionPerformed

    private void createPurchasedateFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createPurchasedateFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_createPurchasedateFieldActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        dispose();  // Close ItemListScreen and return to SalesManagerMenu
        LoginScreen loginScreen = new LoginScreen(); // Instantiate SalesManagerMenu
        loginScreen.setVisible(true);
    }//GEN-LAST:event_backButtonActionPerformed

    private void createPurchasequantityField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createPurchasequantityField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_createPurchasequantityField1ActionPerformed

    private void createPurchaseitemCodeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createPurchaseitemCodeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_createPurchaseitemCodeFieldActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SalesManagerMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SalesManagerMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SalesManagerMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SalesManagerMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SalesManagerMenu(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable PurchaseOrderTable;
    private javax.swing.JButton addButton;
    private javax.swing.JButton backButton;
    private javax.swing.JTextField createPurchasedateField;
    private javax.swing.JTextField createPurchaseitemCodeField;
    private javax.swing.JTextField createPurchasequantityField;
    private javax.swing.JTextField createPurchasequantityField1;
    private javax.swing.JButton deleteButton;
    private javax.swing.JButton editButton;
    private javax.swing.JComboBox<String> itemComboBox;
    private javax.swing.JComboBox<String> itemComboBox1;
    private javax.swing.JTable itemListTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField salesEntryDateField;
    private javax.swing.JTextField salesEntryQuantityField;
    private javax.swing.JTable salesEntryTable;
    private javax.swing.JTable salesReportTable;
    private javax.swing.JButton saveButton;
    private javax.swing.JTable stockLevelTable;
    private javax.swing.JButton submitButton;
    // End of variables declaration//GEN-END:variables
}
