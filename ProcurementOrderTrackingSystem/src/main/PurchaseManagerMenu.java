
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
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class PurchaseManagerMenu extends javax.swing.JFrame {
    
    private String username;
    
    private DefaultTableModel itemList_tableModel, supplierList_tableModel, requisitionList_tableModel,
                              purchaseOrder_tableModel;
            
    public PurchaseManagerMenu(String username) {
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
        
        /******************* Supplier List **********************************/
        String[] supplierListcolumnNames = {"Supplier Code", "Supplier Name"};
        supplierList_tableModel = new DefaultTableModel(supplierListcolumnNames, 0);
        supplierListTable.setModel(supplierList_tableModel);
        loadSuppliers();
        /******************* Supplier List **********************************/
        
        /******************* Requisition List **********************************/
        String[] requisitionListcolumnNames = {
        "Requisition ID", "Requisition Date", "Quantity", "Item Code", "Item Name", "Username", "Role"};
        requisitionList_tableModel = new DefaultTableModel(requisitionListcolumnNames, 0);
        requisitionListTable.setModel(requisitionList_tableModel); 
        loadRequisitions();
        loadItemsForDropdown();
        /******************* Requisition List **********************************/
        
        /******************* Purchase Order **********************************/
        String[] purchaseOrdercolumnNames = {"PO ID", "PR ID", "Item Code", "Quantity", "Supplier ID", "Delivery Date", "Status"};
        purchaseOrder_tableModel = new DefaultTableModel(purchaseOrdercolumnNames, 0);
        purchaseOrderTable.setModel(purchaseOrder_tableModel);
        loadPurchaseOrders();
        /******************* Purchase Order **********************************/
    }
    
    // Load requisitions from file and populate the table
        private void loadRequisitions() {
        requisitionList_tableModel.setRowCount(0);  // Clear existing rows
        try (BufferedReader reader = new BufferedReader(new FileReader("requisitions.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                // Ensure the line has the expected format with all seven fields
                if (data.length == 7) {
                    String requisitionID = data[0];
                    String requisitionDate = data[1];
                    String requisitionQuantity = data[2];
                    String itemCode = data[3];
                    String itemName = data[4];
                    String username = data[5];
                    String role = data[6];

                    // Add the requisition to the table
                    requisitionList_tableModel.addRow(new Object[]{
                        requisitionID, requisitionDate, requisitionQuantity, itemCode, itemName, username, role
                    });
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading requisitions.");
            e.printStackTrace();
        }
    }
    
    // Load items into the dropdown from items.txt
    private void loadItemsForDropdown() {
        List<String[]> items = FileHandler.loadItems();
        for (String[] item : items) {
            itemComboBox.addItem(item[1]);  // Add only the item name to the dropdown
        }
    }
    
    // Load purchase orders from file and populate the table
    private void loadPurchaseOrders() {
        purchaseOrder_tableModel.setRowCount(0);  // Clear existing rows
        try (BufferedReader reader = new BufferedReader(new FileReader("purchase_orders.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 7) {  // Ensure data has the expected format
                    purchaseOrder_tableModel.addRow(data);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading purchase orders.");
            e.printStackTrace();
        }
    }
    
    // Load suppliers from file and populate the table
    private void loadSuppliers() {
        try (BufferedReader reader = new BufferedReader(new FileReader("suppliers.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 2) {  // Ensure data has the expected format: Supplier Code, Supplier Name
                    String supplierCode = data[0];
                    String supplierName = data[1];
                    
                    // Add each supplier to the table
                    supplierList_tableModel.addRow(new Object[]{supplierCode, supplierName});
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading suppliers.");
            e.printStackTrace();
        }
    }
    
    private void loadSelectedPOForEditing() {
        int selectedRow = purchaseOrderTable.getSelectedRow();
        if (selectedRow >= 0) {
            poIdField.setText((String) purchaseOrder_tableModel.getValueAt(selectedRow, 0));
            prIdField.setText((String) purchaseOrder_tableModel.getValueAt(selectedRow, 1));
            purchaseOrderitemCodeField.setText((String) purchaseOrder_tableModel.getValueAt(selectedRow, 2));
            purchaseOrderquantityField.setText((String) purchaseOrder_tableModel.getValueAt(selectedRow, 3));
            purchaseOrdersupplierIdField.setText((String) purchaseOrder_tableModel.getValueAt(selectedRow, 4));
            purchaseOrderDateField.setText((String) purchaseOrder_tableModel.getValueAt(selectedRow, 5));
            statusComboBox.setSelectedItem(purchaseOrder_tableModel.getValueAt(selectedRow, 6));
        } else {
            JOptionPane.showMessageDialog(this, "Please select a PO to edit.");
        }
    }
    
    private void saveEditedPO() {
        int selectedRow = purchaseOrderTable.getSelectedRow();
        if (selectedRow >= 0) {
            purchaseOrder_tableModel.setValueAt(poIdField.getText(), selectedRow, 0);
            purchaseOrder_tableModel.setValueAt(prIdField.getText(), selectedRow, 1);
            purchaseOrder_tableModel.setValueAt(purchaseOrderitemCodeField.getText(), selectedRow, 2);
            purchaseOrder_tableModel.setValueAt(purchaseOrderquantityField.getText(), selectedRow, 3);
            purchaseOrder_tableModel.setValueAt(purchaseOrdersupplierIdField.getText(), selectedRow, 4);
            purchaseOrder_tableModel.setValueAt(purchaseOrderDateField.getText(), selectedRow, 5);
            purchaseOrder_tableModel.setValueAt(statusComboBox.getSelectedItem(), selectedRow, 6);
            savePurchaseOrders();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a PO to save changes.");
        }
    }
    
    private void deleteSelectedPO() {
        int selectedRow = purchaseOrderTable.getSelectedRow();
        if (selectedRow >= 0) {
            purchaseOrder_tableModel.removeRow(selectedRow);
            savePurchaseOrders();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a PO to delete.");
        }
    }
    private void savePurchaseOrders() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("purchase_orders.txt"))) {
            for (int i = 0; i < purchaseOrder_tableModel.getRowCount(); i++) {
                String[] rowData = new String[purchaseOrder_tableModel.getColumnCount()];
                for (int j = 0; j < purchaseOrder_tableModel.getColumnCount(); j++) {
                    rowData[j] = (String) purchaseOrder_tableModel.getValueAt(i, j);
                }
                writer.println(String.join(",", rowData));
            }
            JOptionPane.showMessageDialog(this, "Purchase orders saved successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving purchase orders.");
            e.printStackTrace();
        }
    }
    
    private String fetchSupplierId(String itemCode) {
        try {
            // Read items.txt line by line
            List<String> lines = java.nio.file.Files.readAllLines(java.nio.file.Paths.get("items.txt"));

            for (String line : lines) {
                // Split each line by a delimiter (assuming comma-separated values)
                String[] parts = line.split(",");

                // Check if the first column matches the Item Code
                if (parts[0].trim().equals(itemCode)) {
                    // Return the third column as Supplier ID
                    return parts[2].trim();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error reading items.txt: " + e.getMessage());
        }

        // Return null if no match is found
        return null;
    }
    
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

        jLabel2 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        itemListTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        supplierListTable = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        requisitionListTable = new javax.swing.JTable();
        createPOButton2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        submitButton = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        itemComboBox = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        createPurchaseitemCodeField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        createPurchasequantityField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        createPurchasedateField = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        poIdField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        prIdField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        purchaseOrderitemCodeField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        purchaseOrderquantityField = new javax.swing.JTextField();
        purchaseOrdersupplierIdField = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        statusComboBox = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        purchaseOrderDateField = new javax.swing.JTextField();
        purchaseOrdereditButton = new javax.swing.JButton();
        purchaseOrderdeleteButton = new javax.swing.JButton();
        purchaseOrdersaveButton = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        purchaseOrderTable = new javax.swing.JTable();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(26, 26, 25));
        jLabel2.setText("Purchase Manager Menu");

        jTabbedPane1.setForeground(new java.awt.Color(26, 26, 25));
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(26, 26, 25));
        jLabel3.setText("Item List");

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
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(273, 273, 273))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 131, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("View Items", jPanel2);

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(26, 26, 25));
        jLabel1.setText("Supplier List");

        supplierListTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(supplierListTable);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 686, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(203, 203, 203))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("View Suppliers", jPanel3);

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(26, 26, 25));
        jLabel4.setText("Requisition List");

        requisitionListTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(requisitionListTable);

        createPOButton2.setBackground(new java.awt.Color(183, 224, 255));
        createPOButton2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        createPOButton2.setForeground(new java.awt.Color(26, 26, 25));
        createPOButton2.setText("Add Purchase Order");
        createPOButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createPOButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane4)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGap(0, 185, Short.MAX_VALUE)
                        .addComponent(createPOButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(161, 161, 161))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(214, 214, 214))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(createPOButton2)
                .addContainerGap(62, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("View Requisition", jPanel4);

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

        itemComboBox.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        itemComboBox.setForeground(new java.awt.Color(26, 26, 25));
        itemComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemComboBoxActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(26, 26, 25));
        jLabel16.setText("Item Code:");

        createPurchaseitemCodeField.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        createPurchaseitemCodeField.setForeground(new java.awt.Color(26, 26, 25));
        createPurchaseitemCodeField.setEnabled(false);
        createPurchaseitemCodeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createPurchaseitemCodeFieldActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(173, 173, 173))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(itemComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(createPurchaseitemCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(createPurchasequantityField, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(submitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addComponent(jLabel12)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(createPurchasedateField, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 83, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel9)
                .addGap(36, 36, 36)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(itemComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16)
                    .addComponent(createPurchaseitemCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(createPurchasequantityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(createPurchasedateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(66, 66, 66)
                .addComponent(submitButton)
                .addContainerGap(306, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Create Requisition", jPanel5);

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(26, 26, 25));
        jLabel5.setText("PO ID:");

        poIdField.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        poIdField.setForeground(new java.awt.Color(26, 26, 25));
        poIdField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                poIdFieldActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(26, 26, 25));
        jLabel6.setText("PR ID:");

        prIdField.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        prIdField.setForeground(new java.awt.Color(26, 26, 25));
        prIdField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prIdFieldActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(26, 26, 25));
        jLabel7.setText("Item Code:");

        purchaseOrderitemCodeField.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        purchaseOrderitemCodeField.setForeground(new java.awt.Color(26, 26, 25));
        purchaseOrderitemCodeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                purchaseOrderitemCodeFieldActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(26, 26, 25));
        jLabel8.setText("Quantity:");

        jLabel13.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(26, 26, 25));
        jLabel13.setText("Supplier ID:");

        purchaseOrderquantityField.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        purchaseOrderquantityField.setForeground(new java.awt.Color(26, 26, 25));
        purchaseOrderquantityField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                purchaseOrderquantityFieldActionPerformed(evt);
            }
        });

        purchaseOrdersupplierIdField.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        purchaseOrdersupplierIdField.setForeground(new java.awt.Color(26, 26, 25));
        purchaseOrdersupplierIdField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                purchaseOrdersupplierIdFieldActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(26, 26, 25));
        jLabel14.setText("Status:");

        statusComboBox.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        statusComboBox.setForeground(new java.awt.Color(26, 26, 25));
        statusComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pending", "Approved", "Rejected" }));
        statusComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusComboBoxActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(26, 26, 25));
        jLabel15.setText("Delivery Date (YYYY-MM-DD):");

        purchaseOrderDateField.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        purchaseOrderDateField.setForeground(new java.awt.Color(26, 26, 25));
        purchaseOrderDateField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                purchaseOrderDateFieldActionPerformed(evt);
            }
        });

        purchaseOrdereditButton.setBackground(new java.awt.Color(183, 224, 255));
        purchaseOrdereditButton.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        purchaseOrdereditButton.setForeground(new java.awt.Color(26, 26, 25));
        purchaseOrdereditButton.setText("Edit");
        purchaseOrdereditButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                purchaseOrdereditButtonActionPerformed(evt);
            }
        });

        purchaseOrderdeleteButton.setBackground(new java.awt.Color(183, 224, 255));
        purchaseOrderdeleteButton.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        purchaseOrderdeleteButton.setForeground(new java.awt.Color(26, 26, 25));
        purchaseOrderdeleteButton.setText("Delete");
        purchaseOrderdeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                purchaseOrderdeleteButtonActionPerformed(evt);
            }
        });

        purchaseOrdersaveButton.setBackground(new java.awt.Color(183, 224, 255));
        purchaseOrdersaveButton.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        purchaseOrdersaveButton.setForeground(new java.awt.Color(26, 26, 25));
        purchaseOrdersaveButton.setText("Save");
        purchaseOrdersaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                purchaseOrdersaveButtonActionPerformed(evt);
            }
        });

        purchaseOrderTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(purchaseOrderTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(poIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(4, 4, 4)
                                .addComponent(purchaseOrderquantityField, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(prIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(39, 39, 39)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(purchaseOrderitemCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(purchaseOrdersupplierIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(98, 98, 98))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(purchaseOrderDateField, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(114, 114, 114))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(195, 195, 195)
                .addComponent(purchaseOrdereditButton)
                .addGap(18, 18, 18)
                .addComponent(purchaseOrderdeleteButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(purchaseOrdersaveButton)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(poIdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(prIdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(purchaseOrderitemCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(purchaseOrderquantityField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(purchaseOrdersupplierIdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(statusComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(purchaseOrderDateField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(purchaseOrdereditButton)
                    .addComponent(purchaseOrderdeleteButton)
                    .addComponent(purchaseOrdersaveButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Manage Purchase Orders", jPanel1);

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
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backButton)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(backButton))
                .addGap(10, 10, 10)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        // TODO add your handling code here:
        String itemName = (String) itemComboBox.getSelectedItem();
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

    private void createPOButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createPOButton2ActionPerformed
        // TODO add your handling code here:
        // Check if a row is selected
    int selectedRow = requisitionListTable.getSelectedRow();
    if (selectedRow >= 0) {
        // Retrieve values from the selected row
        String prId = (String) requisitionList_tableModel.getValueAt(selectedRow, 0);  // Requisition ID
        String itemCode = (String) requisitionList_tableModel.getValueAt(selectedRow, 1); // Item Code
        String quantity = (String) requisitionList_tableModel.getValueAt(selectedRow, 2); // Quantity
        String date = (String) requisitionList_tableModel.getValueAt(selectedRow, 3);    // Date
        
        // Fetch Supplier ID from items.txt
        String supplierId = fetchSupplierId(itemCode);
        if (supplierId == null) {
            JOptionPane.showMessageDialog(this, "Supplier ID not found for Item Code: " + itemCode);
            return;
        }

        // Open the PurchaseOrderApprovalScreen with all necessary details
        try {
            PurchaseOrderApprovalScreen poScreen = new PurchaseOrderApprovalScreen(prId, itemCode, quantity, date, supplierId, username);
            dispose();
            poScreen.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();  // Debug: Print stack trace to console
            JOptionPane.showMessageDialog(this, "Error opening Purchase Order Approval screen: " + e.getMessage());
        }
    } else {
        // Display an error message if no row is selected
        JOptionPane.showMessageDialog(this, "Please select a requisition to create a PO.");
    }
    }//GEN-LAST:event_createPOButton2ActionPerformed

    private void poIdFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_poIdFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_poIdFieldActionPerformed

    private void prIdFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prIdFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_prIdFieldActionPerformed

    private void purchaseOrderitemCodeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purchaseOrderitemCodeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_purchaseOrderitemCodeFieldActionPerformed

    private void purchaseOrderquantityFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purchaseOrderquantityFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_purchaseOrderquantityFieldActionPerformed

    private void purchaseOrdersupplierIdFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purchaseOrdersupplierIdFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_purchaseOrdersupplierIdFieldActionPerformed

    private void statusComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusComboBoxActionPerformed

    private void purchaseOrderDateFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purchaseOrderDateFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_purchaseOrderDateFieldActionPerformed

    private void purchaseOrdereditButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purchaseOrdereditButtonActionPerformed
        // TODO add your handling code here:
        loadSelectedPOForEditing();
    }//GEN-LAST:event_purchaseOrdereditButtonActionPerformed

    private void purchaseOrderdeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purchaseOrderdeleteButtonActionPerformed
        // TODO add your handling code here:
        deleteSelectedPO();
    }//GEN-LAST:event_purchaseOrderdeleteButtonActionPerformed

    private void purchaseOrdersaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_purchaseOrdersaveButtonActionPerformed
        // TODO add your handling code here:
        saveEditedPO();
    }//GEN-LAST:event_purchaseOrdersaveButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        dispose();  // Close ItemListScreen and return to SalesManagerMenu
        LoginScreen loginScreen = new LoginScreen(); // Instantiate SalesManagerMenu
        loginScreen.setVisible(true);
    }//GEN-LAST:event_backButtonActionPerformed

    private void itemComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemComboBoxActionPerformed
        // TODO add your handling code here:
        String selectedItemName = (String) itemComboBox.getSelectedItem();
        String itemCode = fetchItemCode(selectedItemName);
        createPurchaseitemCodeField.setText(itemCode != null ? itemCode : "Unknown"); // Update the text field
    }//GEN-LAST:event_itemComboBoxActionPerformed

    private void createPurchaseitemCodeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createPurchaseitemCodeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_createPurchaseitemCodeFieldActionPerformed

    private void createPurchasequantityFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createPurchasequantityFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_createPurchasequantityFieldActionPerformed

    private void createPurchasedateFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createPurchasedateFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_createPurchasedateFieldActionPerformed

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
            java.util.logging.Logger.getLogger(PurchaseManagerMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PurchaseManagerMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PurchaseManagerMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PurchaseManagerMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PurchaseManagerMenu(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JButton createPOButton2;
    private javax.swing.JTextField createPurchasedateField;
    private javax.swing.JTextField createPurchaseitemCodeField;
    private javax.swing.JTextField createPurchasequantityField;
    private javax.swing.JComboBox<String> itemComboBox;
    private javax.swing.JTable itemListTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField poIdField;
    private javax.swing.JTextField prIdField;
    private javax.swing.JTextField purchaseOrderDateField;
    private javax.swing.JTable purchaseOrderTable;
    private javax.swing.JButton purchaseOrderdeleteButton;
    private javax.swing.JButton purchaseOrdereditButton;
    private javax.swing.JTextField purchaseOrderitemCodeField;
    private javax.swing.JTextField purchaseOrderquantityField;
    private javax.swing.JButton purchaseOrdersaveButton;
    private javax.swing.JTextField purchaseOrdersupplierIdField;
    private javax.swing.JTable requisitionListTable;
    private javax.swing.JComboBox<String> statusComboBox;
    private javax.swing.JButton submitButton;
    private javax.swing.JTable supplierListTable;
    // End of variables declaration//GEN-END:variables
}
