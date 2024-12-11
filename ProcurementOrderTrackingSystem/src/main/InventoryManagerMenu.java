
package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class InventoryManagerMenu extends javax.swing.JFrame {

    private DefaultTableModel itemList_tableModel, supplierList_tableModel, stockLevelTableModel, 
                              stockUpdateTableModel, manageStockTableModel;
    private Map<String, String> nameToCodeMap; // Map to store itemName -> itemCode
    
    public InventoryManagerMenu() {
        initComponents();
        
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
        String[] supplierListColumnNames = {"Supplier ID", "Supplier Name"};
        supplierList_tableModel = new DefaultTableModel(supplierListColumnNames, 0);
        supplierListTable.setModel(supplierList_tableModel);
        // Load suppliers from file and populate the table
        List<String[]> supplierList = FileHandler.loadSuppliers();
        for (String[] supplier : supplierList) {
            supplierList_tableModel.addRow(supplier);  // Each supplier array is added as a row to the table
        }
        /******************* Supplier List **********************************/
        
        /******************* Stock Levels Table **********************************/
        String[] stockLevelColumnNames = {"Item Code", "Item Name", "Current Stock", "Status"};
        stockLevelTableModel = new DefaultTableModel(stockLevelColumnNames, 0);
        stockLevelTable.setModel(stockLevelTableModel);
        // Load stock levels from files
        loadViewStockLevels();
        /******************* Stock Levels Table **********************************/
        
        /******************* Stock Update Table **********************************/
        String[] stockUpdateColumnNames = {"Item Code", "Item Name", "Current Stock", "New Stock"};
        stockUpdateTableModel = new DefaultTableModel(stockUpdateColumnNames, 0);
        stockUpdateTable.setModel(stockUpdateTableModel);
        // Load stock levels from files
        loadUpdateStockLevels();
        /******************* Stock Update Table **********************************/
        
        /******************* Manage Stock Levels Table **********************************/
        String[] manageStockLevelColumnNames = {"Item Code", "Item Name", "Current Stock"};
        manageStockTableModel = new DefaultTableModel(manageStockLevelColumnNames, 0);
        manageStockTable.setModel(manageStockTableModel);
        // Load stock levels from files
        loadManageStockLevels();
        /******************* Stock Levels Table **********************************/
        
        populateItemNameDropdown(); // Manage Stocks dropdown
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        addItemButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        itemListTable = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        itemCodeField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        itemNameField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        supplierIdField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        editItemButton = new javax.swing.JButton();
        deleteItemButton = new javax.swing.JButton();
        modifyItemButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        supplierNameField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        supplierIdEntryField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        addSupplierButton = new javax.swing.JButton();
        editSupplierButton = new javax.swing.JButton();
        modifySupplierButton = new javax.swing.JButton();
        deleteSupplierButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        supplierListTable = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        stockLevelTable = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        stockUpdateTable = new javax.swing.JTable();
        updateStockLevelButton = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        itemNameComboBox = new javax.swing.JComboBox<>();
        stocksItemCodeField = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        manageStockField = new javax.swing.JTextField();
        addStockButton = new javax.swing.JButton();
        editStockButton = new javax.swing.JButton();
        deleteStockButton = new javax.swing.JButton();
        saveStockButton = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        manageStockTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(26, 26, 25));
        jLabel1.setText("Inventory Manager Menu");

        jTabbedPane1.setForeground(new java.awt.Color(26, 26, 25));
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(26, 26, 25));
        jLabel3.setText("Item Entry");

        addItemButton.setBackground(new java.awt.Color(183, 224, 255));
        addItemButton.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        addItemButton.setForeground(new java.awt.Color(26, 26, 25));
        addItemButton.setText("Add");
        addItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addItemButtonActionPerformed(evt);
            }
        });

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

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(26, 26, 25));
        jLabel5.setText("Item Code:");

        itemCodeField.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        itemCodeField.setForeground(new java.awt.Color(26, 26, 25));
        itemCodeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCodeFieldActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(26, 26, 25));
        jLabel6.setText("Item Name:");

        itemNameField.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        itemNameField.setForeground(new java.awt.Color(26, 26, 25));
        itemNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNameFieldActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(26, 26, 25));
        jLabel7.setText("Supplier ID");

        supplierIdField.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        supplierIdField.setForeground(new java.awt.Color(26, 26, 25));
        supplierIdField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplierIdFieldActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(153, 0, 0));
        jLabel8.setText("Code Pattern: ITMxxx ");

        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(153, 0, 0));
        jLabel9.setText("ID Pattern: SUPxxx ");

        editItemButton.setBackground(new java.awt.Color(183, 224, 255));
        editItemButton.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        editItemButton.setForeground(new java.awt.Color(26, 26, 25));
        editItemButton.setText("Edit");
        editItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editItemButtonActionPerformed(evt);
            }
        });

        deleteItemButton.setBackground(new java.awt.Color(183, 224, 255));
        deleteItemButton.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        deleteItemButton.setForeground(new java.awt.Color(26, 26, 25));
        deleteItemButton.setText("Delete");
        deleteItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteItemButtonActionPerformed(evt);
            }
        });

        modifyItemButton.setBackground(new java.awt.Color(183, 224, 255));
        modifyItemButton.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        modifyItemButton.setForeground(new java.awt.Color(26, 26, 25));
        modifyItemButton.setText("Modify");
        modifyItemButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyItemButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(218, 218, 218))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 683, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(itemCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(itemNameField)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(supplierIdField, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel9)
                        .addGap(89, 89, 89))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(113, 113, 113)
                        .addComponent(addItemButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(editItemButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(modifyItemButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deleteItemButton)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(supplierIdField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(itemCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel6)
                        .addComponent(itemNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5)))
                .addGap(1, 1, 1)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addItemButton)
                    .addComponent(editItemButton)
                    .addComponent(modifyItemButton)
                    .addComponent(deleteItemButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Item Entry", jPanel1);

        jLabel10.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(26, 26, 25));
        jLabel10.setText("Supplier Name:");

        supplierNameField.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        supplierNameField.setForeground(new java.awt.Color(26, 26, 25));
        supplierNameField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplierNameFieldActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(26, 26, 25));
        jLabel11.setText("Supplier ID");

        supplierIdEntryField.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        supplierIdEntryField.setForeground(new java.awt.Color(26, 26, 25));
        supplierIdEntryField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplierIdEntryFieldActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(153, 0, 0));
        jLabel12.setText("ID Pattern: SUPxxx ");

        jLabel4.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(26, 26, 25));
        jLabel4.setText("Supplier Entry");

        addSupplierButton.setBackground(new java.awt.Color(183, 224, 255));
        addSupplierButton.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        addSupplierButton.setForeground(new java.awt.Color(26, 26, 25));
        addSupplierButton.setText("Add");
        addSupplierButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSupplierButtonActionPerformed(evt);
            }
        });

        editSupplierButton.setBackground(new java.awt.Color(183, 224, 255));
        editSupplierButton.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        editSupplierButton.setForeground(new java.awt.Color(26, 26, 25));
        editSupplierButton.setText("Edit");
        editSupplierButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editSupplierButtonActionPerformed(evt);
            }
        });

        modifySupplierButton.setBackground(new java.awt.Color(183, 224, 255));
        modifySupplierButton.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        modifySupplierButton.setForeground(new java.awt.Color(26, 26, 25));
        modifySupplierButton.setText("Modify");
        modifySupplierButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifySupplierButtonActionPerformed(evt);
            }
        });

        deleteSupplierButton.setBackground(new java.awt.Color(183, 224, 255));
        deleteSupplierButton.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        deleteSupplierButton.setForeground(new java.awt.Color(26, 26, 25));
        deleteSupplierButton.setText("Delete");
        deleteSupplierButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteSupplierButtonActionPerformed(evt);
            }
        });

        supplierListTable.setForeground(new java.awt.Color(26, 26, 25));
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

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(235, 235, 235))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(supplierNameField, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                        .addGap(48, 48, 48)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(supplierIdEntryField, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel12))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(addSupplierButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(editSupplierButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(modifySupplierButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deleteSupplierButton)))
                .addContainerGap(96, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(supplierIdEntryField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(supplierNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(1, 1, 1)
                .addComponent(jLabel12)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addSupplierButton)
                    .addComponent(editSupplierButton)
                    .addComponent(modifySupplierButton)
                    .addComponent(deleteSupplierButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 482, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Supplier Entry", jPanel2);

        jLabel13.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(26, 26, 25));
        jLabel13.setText("View Stock Levels");

        stockLevelTable.setForeground(new java.awt.Color(26, 26, 25));
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
        jScrollPane3.setViewportView(stockLevelTable);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(257, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(212, 212, 212))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel3Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jScrollPane3)
                    .addContainerGap()))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addContainerGap(611, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                    .addContainerGap(132, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );

        jTabbedPane1.addTab("View Stock Levels", jPanel3);

        jLabel14.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(26, 26, 25));
        jLabel14.setText("Update All Stock Levels");

        stockUpdateTable.setForeground(new java.awt.Color(26, 26, 25));
        stockUpdateTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(stockUpdateTable);

        updateStockLevelButton.setBackground(new java.awt.Color(183, 224, 255));
        updateStockLevelButton.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        updateStockLevelButton.setForeground(new java.awt.Color(26, 26, 25));
        updateStockLevelButton.setText("Update Stock Levels");
        updateStockLevelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateStockLevelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(215, 215, 215)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(updateStockLevelButton))
                        .addGap(0, 205, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(updateStockLevelButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Update All Stock Levels", jPanel4);

        jLabel15.setFont(new java.awt.Font("Segoe UI Semibold", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(26, 26, 25));
        jLabel15.setText("Manage Stocks ");

        jLabel16.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(26, 26, 25));
        jLabel16.setText("Select Item:");

        itemNameComboBox.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        itemNameComboBox.setForeground(new java.awt.Color(26, 26, 25));
        itemNameComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemNameComboBoxActionPerformed(evt);
            }
        });

        stocksItemCodeField.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        stocksItemCodeField.setForeground(new java.awt.Color(26, 26, 25));
        stocksItemCodeField.setEnabled(false);
        stocksItemCodeField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stocksItemCodeFieldActionPerformed(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(26, 26, 25));
        jLabel17.setText("Item Code:");

        jLabel18.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(26, 26, 25));
        jLabel18.setText("Stock:");

        manageStockField.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        manageStockField.setForeground(new java.awt.Color(26, 26, 25));
        manageStockField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                manageStockFieldActionPerformed(evt);
            }
        });

        addStockButton.setBackground(new java.awt.Color(183, 224, 255));
        addStockButton.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        addStockButton.setForeground(new java.awt.Color(26, 26, 25));
        addStockButton.setText("Add");
        addStockButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addStockButtonActionPerformed(evt);
            }
        });

        editStockButton.setBackground(new java.awt.Color(183, 224, 255));
        editStockButton.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        editStockButton.setForeground(new java.awt.Color(26, 26, 25));
        editStockButton.setText("Edit");
        editStockButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editStockButtonActionPerformed(evt);
            }
        });

        deleteStockButton.setBackground(new java.awt.Color(183, 224, 255));
        deleteStockButton.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        deleteStockButton.setForeground(new java.awt.Color(26, 26, 25));
        deleteStockButton.setText("Delete");
        deleteStockButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteStockButtonActionPerformed(evt);
            }
        });

        saveStockButton.setBackground(new java.awt.Color(183, 224, 255));
        saveStockButton.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        saveStockButton.setForeground(new java.awt.Color(26, 26, 25));
        saveStockButton.setText("Save");
        saveStockButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveStockButtonActionPerformed(evt);
            }
        });

        manageStockTable.setForeground(new java.awt.Color(26, 26, 25));
        manageStockTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane5.setViewportView(manageStockTable);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(itemNameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stocksItemCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(116, 116, 116))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(254, 254, 254)
                        .addComponent(jLabel15))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel18)
                        .addGap(37, 37, 37)
                        .addComponent(manageStockField, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addComponent(addStockButton)
                        .addGap(18, 18, 18)
                        .addComponent(editStockButton)
                        .addGap(18, 18, 18)
                        .addComponent(deleteStockButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(saveStockButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane5)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel15)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel16)
                        .addComponent(itemNameComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17))
                    .addComponent(stocksItemCodeField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(manageStockField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addStockButton)
                        .addComponent(editStockButton)
                        .addComponent(deleteStockButton)
                        .addComponent(saveStockButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Manage Stocks", jPanel5);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(124, 124, 124)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void supplierIdEntryFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplierIdEntryFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_supplierIdEntryFieldActionPerformed

    private void supplierNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplierNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_supplierNameFieldActionPerformed

    private void modifyItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyItemButtonActionPerformed
        // TODO add your handling code here:
        saveItemEdits();
    }//GEN-LAST:event_modifyItemButtonActionPerformed

    private void deleteItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteItemButtonActionPerformed
        // TODO add your handling code here:
        deleteItem();
    }//GEN-LAST:event_deleteItemButtonActionPerformed

    private void editItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editItemButtonActionPerformed
        // TODO add your handling code here:
        populateFieldsFromItemsTable();
    }//GEN-LAST:event_editItemButtonActionPerformed

    private void supplierIdFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplierIdFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_supplierIdFieldActionPerformed

    private void itemNameFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNameFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemNameFieldActionPerformed

    private void itemCodeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCodeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemCodeFieldActionPerformed

    private void addItemButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addItemButtonActionPerformed
        // TODO add your handling code here:
        addItem();
    }//GEN-LAST:event_addItemButtonActionPerformed

    private void addSupplierButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSupplierButtonActionPerformed
        // TODO add your handling code here:
        addSupplier();
    }//GEN-LAST:event_addSupplierButtonActionPerformed

    private void editSupplierButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editSupplierButtonActionPerformed
        // TODO add your handling code here:
        populateFieldsFromSupplierTable();
    }//GEN-LAST:event_editSupplierButtonActionPerformed

    private void modifySupplierButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifySupplierButtonActionPerformed
        // TODO add your handling code here:
        saveSupplierEdits();
    }//GEN-LAST:event_modifySupplierButtonActionPerformed

    private void deleteSupplierButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteSupplierButtonActionPerformed
        // TODO add your handling code here:
        deleteSupplier();
    }//GEN-LAST:event_deleteSupplierButtonActionPerformed

    private void updateStockLevelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateStockLevelButtonActionPerformed
        // TODO add your handling code here:
        updateStockLevels();
    }//GEN-LAST:event_updateStockLevelButtonActionPerformed

    private void itemNameComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemNameComboBoxActionPerformed
        // TODO add your handling code here:
        onItemSelected();
    }//GEN-LAST:event_itemNameComboBoxActionPerformed

    private void stocksItemCodeFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stocksItemCodeFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stocksItemCodeFieldActionPerformed

    private void manageStockFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_manageStockFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_manageStockFieldActionPerformed

    private void addStockButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addStockButtonActionPerformed
        // TODO add your handling code here:
        addStock();
    }//GEN-LAST:event_addStockButtonActionPerformed

    private void editStockButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editStockButtonActionPerformed
        // TODO add your handling code here:
        editStock();
    }//GEN-LAST:event_editStockButtonActionPerformed

    private void deleteStockButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteStockButtonActionPerformed
        // TODO add your handling code here:
        deleteStock();
    }//GEN-LAST:event_deleteStockButtonActionPerformed

    private void saveStockButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveStockButtonActionPerformed
        // TODO add your handling code here:
        saveStockChanges();
    }//GEN-LAST:event_saveStockButtonActionPerformed
    
    // Add a new item to the table and save to file
    private void addItem() {
        String itemCode = itemCodeField.getText().trim();
        String itemName = itemNameField.getText().trim();
        String supplierId = supplierIdField.getText().trim();

        if (itemCode.isEmpty() || itemName.isEmpty() || supplierId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.");
            return;
        }
        
        // Check if the item code already exists
        try (BufferedReader reader = new BufferedReader(new FileReader("items.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 0 && data[0].equals(itemCode)) {
                    JOptionPane.showMessageDialog(this, "Item code already exists. Please use a unique code.");
                    return;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading items file.");
            e.printStackTrace();
            return;
        }
        
        // Check if the supplier ID exists
        boolean supplierExists = false;
        try (BufferedReader reader = new BufferedReader(new FileReader("suppliers.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 0 && data[0].equals(supplierId)) {
                    supplierExists = true;
                    break;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading suppliers file.");
            e.printStackTrace();
            return;
        }

        if (!supplierExists) {
            JOptionPane.showMessageDialog(this, "Supplier ID does not exist. Please provide a valid Supplier ID.");
            return;
        }
    
        // Add item to table
        itemList_tableModel.addRow(new String[]{itemCode, itemName, supplierId});

        // Save item to file
        try (PrintWriter writer = new PrintWriter(new FileWriter("items.txt", true))) {
            writer.println(itemCode + "," + itemName + "," + supplierId);
            JOptionPane.showMessageDialog(this, "Item added successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving item.");
            e.printStackTrace();
        }
        clearItemsFields();
    }

    // Clear the input fields
    private void clearItemsFields() {
        itemCodeField.setText("");
        itemNameField.setText("");
        supplierIdField.setText("");
    }
    
    // Clear the input fields
    private void clearSupplierFields() {
        supplierIdField.setText("");
        supplierNameField.setText("");
    }
    
    private void clearManageStocksFields() {
    itemNameComboBox.setSelectedIndex(-1); // Reset dropdown selection
    stocksItemCodeField.setText("");             // Clear item code field
    manageStockField.setText("");                // Clear stock input field
}
    
    private void populateFieldsFromItemsTable() {
        int selectedRow = itemListTable.getSelectedRow();
        if (selectedRow >= 0) {
            itemCodeField.setText((String) itemList_tableModel.getValueAt(selectedRow, 0));
            itemNameField.setText((String) itemList_tableModel.getValueAt(selectedRow, 1));
            supplierIdField.setText((String) itemList_tableModel.getValueAt(selectedRow, 2));
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to edit.");
        }
    }
    
    private void saveItemEdits() {
        int selectedRow = itemListTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row to edit.");
            return;
        }

        String newItemCode = itemCodeField.getText().trim();
        String newItemName = itemNameField.getText().trim();
        String newSupplierId = supplierIdField.getText().trim();

        if (newItemCode.isEmpty() || newItemName.isEmpty() || newSupplierId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.");
            return;
        }
        
         // Check if the item code already exists (excluding the current row)
        try (BufferedReader reader = new BufferedReader(new FileReader("items.txt"))) {
            String line;
            int rowIndex = 0;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 0 && data[0].equals(newItemCode) && rowIndex != selectedRow) {
                    JOptionPane.showMessageDialog(this, "Item code already exists. Please use a unique code.");
                    return;
                }
                rowIndex++;
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading items file.");
            e.printStackTrace();
            return;
        }
    
        // Check if the supplier ID exists
        boolean supplierExists = false;
        try (BufferedReader reader = new BufferedReader(new FileReader("suppliers.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 0 && data[0].equals(newSupplierId)) {
                    supplierExists = true;
                    break;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading suppliers file.");
            e.printStackTrace();
            return;
        }

        if (!supplierExists) {
            JOptionPane.showMessageDialog(this, "Supplier ID does not exist. Please provide a valid Supplier ID.");
            return;
        }

        // Update the table
        itemList_tableModel.setValueAt(newItemCode, selectedRow, 0);
        itemList_tableModel.setValueAt(newItemName, selectedRow, 1);
        itemList_tableModel.setValueAt(newSupplierId, selectedRow, 2);

        // Update the file
        try (BufferedReader reader = new BufferedReader(new FileReader("items.txt"))) {
            StringBuilder updatedContent = new StringBuilder();
            String line;
            int rowIndex = 0;

            while ((line = reader.readLine()) != null) {
                if (rowIndex == selectedRow) {
                    updatedContent.append(newItemCode).append(",").append(newItemName).append(",").append(newSupplierId).append("\n");
                } else {
                    updatedContent.append(line).append("\n");
                }
                rowIndex++;
            }

            // Save updated content back to the file
            try (PrintWriter writer = new PrintWriter(new FileWriter("items.txt"))) {
                writer.print(updatedContent.toString());
                JOptionPane.showMessageDialog(this, "Item updated successfully.");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error updating items file.");
            e.printStackTrace();
        }
    }
    
    private void deleteItem() {
        int selectedRow = itemListTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
            return;
        }

        // Confirm the deletion
        int confirmation = JOptionPane.showConfirmDialog(this, 
                "Are you sure you want to delete this item?", 
                "Confirm Deletion", 
                JOptionPane.YES_NO_OPTION);

        if (confirmation != JOptionPane.YES_OPTION) {
            return;
        }

        // Remove the row from the table
        itemList_tableModel.removeRow(selectedRow);

        // Update the file
        try (BufferedReader reader = new BufferedReader(new FileReader("items.txt"))) {
            StringBuilder updatedContent = new StringBuilder();
            String line;
            int rowIndex = 0;

            while ((line = reader.readLine()) != null) {
                if (rowIndex != selectedRow) { // Skip the selected row
                    updatedContent.append(line).append("\n");
                }
                rowIndex++;
            }

            // Save updated content back to the file
            try (PrintWriter writer = new PrintWriter(new FileWriter("items.txt"))) {
                writer.print(updatedContent.toString());
                JOptionPane.showMessageDialog(this, "Item deleted successfully.");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error updating items file.");
            e.printStackTrace();
        }
    }
    
    // Add a new supplier to the table and save to file
    private void addSupplier() {
        String supplierId = supplierIdEntryField.getText().trim();
        String supplierName = supplierNameField.getText().trim();

        if (supplierId.isEmpty() || supplierName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.");
            return;
        }

        // Check if the supplier ID already exists
        try (BufferedReader reader = new BufferedReader(new FileReader("suppliers.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 0 && data[0].equals(supplierId)) {
                    JOptionPane.showMessageDialog(this, "Supplier ID already exists. Please use a unique ID.");
                    return;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading suppliers file.");
            e.printStackTrace();
            return;
        }

        // Add supplier to table
        supplierList_tableModel.addRow(new String[]{supplierId, supplierName});

        // Save supplier to file
        try (PrintWriter writer = new PrintWriter(new FileWriter("suppliers.txt", true))) {
            writer.println(supplierId + "," + supplierName);
            JOptionPane.showMessageDialog(this, "Supplier added successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving supplier.");
            e.printStackTrace();
        }

        clearSupplierFields();
    }
    
    // Populate fields from selected row
    private void populateFieldsFromSupplierTable() {
        int selectedRow = supplierListTable.getSelectedRow();
        if (selectedRow >= 0) {
            supplierIdEntryField.setText((String) supplierList_tableModel.getValueAt(selectedRow, 0));
            supplierNameField.setText((String) supplierList_tableModel.getValueAt(selectedRow, 1));
        } else {
            JOptionPane.showMessageDialog(this, "Please select a row to edit.");
        }
    }
    
    // Save edits to selected supplier
    private void saveSupplierEdits() {
        int selectedRow = supplierListTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row to edit.");
            return;
        }

        String newSupplierId = supplierIdEntryField.getText().trim();
        String newSupplierName = supplierNameField.getText().trim();

        if (newSupplierId.isEmpty() || newSupplierName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields are required.");
            return;
        }

        // Check if the supplier ID already exists (excluding the current row)
        try (BufferedReader reader = new BufferedReader(new FileReader("suppliers.txt"))) {
            String line;
            int rowIndex = 0;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length > 0 && data[0].equals(newSupplierId) && rowIndex != selectedRow) {
                    JOptionPane.showMessageDialog(this, "Supplier ID already exists. Please use a unique ID.");
                    return;
                }
                rowIndex++;
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading suppliers file.");
            e.printStackTrace();
            return;
        }

        // Update the table
        supplierList_tableModel.setValueAt(newSupplierId, selectedRow, 0);
        supplierList_tableModel.setValueAt(newSupplierName, selectedRow, 1);

        // Update the file
        try (BufferedReader reader = new BufferedReader(new FileReader("suppliers.txt"))) {
            StringBuilder updatedContent = new StringBuilder();
            String line;
            int rowIndex = 0;

            while ((line = reader.readLine()) != null) {
                if (rowIndex == selectedRow) {
                    updatedContent.append(newSupplierId).append(",").append(newSupplierName).append("\n");
                } else {
                    updatedContent.append(line).append("\n");
                }
                rowIndex++;
            }

            // Save updated content back to the file
            try (PrintWriter writer = new PrintWriter(new FileWriter("suppliers.txt"))) {
                writer.print(updatedContent.toString());
                JOptionPane.showMessageDialog(this, "Supplier updated successfully.");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error updating suppliers file.");
            e.printStackTrace();
        }
    }
    
    // Delete a supplier from the table and file
    private void deleteSupplier() {
        int selectedRow = supplierListTable.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
            return;
        }

        // Confirm the deletion
        int confirmation = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this supplier?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION);

        if (confirmation != JOptionPane.YES_OPTION) {
            return;
        }

        // Remove the row from the table
        supplierList_tableModel.removeRow(selectedRow);

        // Update the file
        try (BufferedReader reader = new BufferedReader(new FileReader("suppliers.txt"))) {
            StringBuilder updatedContent = new StringBuilder();
            String line;
            int rowIndex = 0;

            while ((line = reader.readLine()) != null) {
                if (rowIndex != selectedRow) { // Skip the selected row
                    updatedContent.append(line).append("\n");
                }
                rowIndex++;
            }

            // Save updated content back to the file
            try (PrintWriter writer = new PrintWriter(new FileWriter("suppliers.txt"))) {
                writer.print(updatedContent.toString());
                JOptionPane.showMessageDialog(this, "Supplier deleted successfully.");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error updating suppliers file.");
            e.printStackTrace();
        }
    }
    
    // Load stock levels from files
    private void loadViewStockLevels() {
        // Load item codes and names from items.txt
        Map<String, String> nameToCodeMap = new HashMap<>(); // Map<ItemName, ItemCode>
        try (BufferedReader reader = new BufferedReader(new FileReader("items.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 2) {
                    String itemCode = data[0];
                    String itemName = data[1];
                    nameToCodeMap.put(itemName, itemCode); // Map ItemName to ItemCode
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading items file.");
            e.printStackTrace();
            return;
        }

        // Load stock levels from stock_levels.txt
        try (BufferedReader reader = new BufferedReader(new FileReader("stock_levels.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 2) {
                    String itemName = data[0]; // Item Name from stock_levels.txt
                    int currentStock = Integer.parseInt(data[1]);
                    String itemCode = nameToCodeMap.getOrDefault(itemName, "Unknown Code");
                    String status = (currentStock < 5) ? "Low" : "Sufficient";

                    // Add row to table
                    stockLevelTableModel.addRow(new Object[]{itemCode, itemName, currentStock, status});
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading stock levels file.");
            e.printStackTrace();
        }
    }
    
    // Load stock levels from files
    private void loadUpdateStockLevels() {
        // Load item names and codes from items.txt
        Map<String, String> itemCodeToName = new HashMap<>(); // Map<ItemName, ItemCode>
        try (BufferedReader reader = new BufferedReader(new FileReader("items.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 2) {
                    String itemCode = data[0];
                    String itemName = data[1];
                    itemCodeToName.put(itemName, itemCode); // Map ItemName to ItemCode
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading items file.");
            e.printStackTrace();
            return;
        }

        // Load stock levels from stock_levels.txt
        try (BufferedReader reader = new BufferedReader(new FileReader("stock_levels.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 2) {
                    String itemName = data[0]; // Item Name from stock_levels.txt
                    int currentStock = Integer.parseInt(data[1]);
                    String itemCode = itemCodeToName.getOrDefault(itemName, "Unknown Code");
                    String status = (currentStock < 5) ? "Low" : "Sufficient";

                    // Add row to table
                    stockUpdateTableModel.addRow(new Object[]{itemCode, itemName, currentStock, ""});
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading stock levels file.");
            e.printStackTrace();
        }
    }
    
    private void loadManageStockLevels() {
    // Clear existing rows in the table
    manageStockTableModel.setRowCount(0);

    // Map to store item names and their codes
    Map<String, String> nameToCodeMap = new HashMap<>(); // Map<ItemName, ItemCode>

    // Step 1: Load item codes and names from items.txt
    try (BufferedReader reader = new BufferedReader(new FileReader("items.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length >= 2) {
                String itemCode = data[0];
                String itemName = data[1];
                nameToCodeMap.put(itemName, itemCode); // Map ItemName to ItemCode
            }
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error reading items file.");
        e.printStackTrace();
        return;
    }

    // Step 2: Load stock levels from stock_levels.txt
    try (BufferedReader reader = new BufferedReader(new FileReader("stock_levels.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            if (data.length >= 2) {
                String itemName = data[0]; // Item Name from stock_levels.txt
                int currentStock = Integer.parseInt(data[1]); // Current Stock Level
                String itemCode = nameToCodeMap.getOrDefault(itemName, "Unknown Code");

                // Add row to the Manage Stock table
                manageStockTableModel.addRow(new Object[]{itemCode, itemName, currentStock});
            }
        }
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error reading stock levels file.");
        e.printStackTrace();
    }
}


    
    // Update stock levels based on user input
    private void updateStockLevels() {
        int rowCount = stockUpdateTableModel.getRowCount();

        StringBuilder updatedContent = new StringBuilder();

        for (int i = 0; i < rowCount; i++) {
            String itemCode = (String) stockUpdateTableModel.getValueAt(i, 0);
            String itemName = (String) stockUpdateTableModel.getValueAt(i, 1);
            int currentStock = (int) stockUpdateTableModel.getValueAt(i, 2);
            String newStockStr = (String) stockUpdateTableModel.getValueAt(i, 3);

            // Validate new stock input
            if (newStockStr == null || newStockStr.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter new stock for all items.");
                return;
            }

            int newStock;
            try {
                newStock = Integer.parseInt(newStockStr.trim());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid stock value for item: " + itemName);
                return;
            }

            // Append updated stock to content
            updatedContent.append(itemCode).append(",").append(newStock).append("\n");
        }

        // Save updated stock levels to file
        try (PrintWriter writer = new PrintWriter(new FileWriter("stock_levels.txt"))) {
            writer.print(updatedContent.toString());
            JOptionPane.showMessageDialog(this, "Stock levels updated successfully.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving stock levels.");
            e.printStackTrace();
        }
        
        // Refresh tables: 
        stockUpdateTableModel.setRowCount(0);
        stockLevelTableModel.setRowCount(0);
        loadUpdateStockLevels();
        loadViewStockLevels();
    }
    
    private void populateItemNameDropdown() {
        // Initialize the map
        nameToCodeMap = new HashMap<>();

        // Clear the dropdown
        itemNameComboBox.removeAllItems();

        // Read items.txt to populate dropdown and map
        try (BufferedReader reader = new BufferedReader(new FileReader("items.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 2) {
                    String itemCode = data[0];
                    String itemName = data[1];
                    nameToCodeMap.put(itemName, itemCode);
                    itemNameComboBox.addItem(itemName);
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error reading items file.");
            e.printStackTrace();
        }
    }
    
    private void onItemSelected() {
    String selectedItem = (String) itemNameComboBox.getSelectedItem();
    if (selectedItem != null && nameToCodeMap.containsKey(selectedItem)) {
        String itemCode = nameToCodeMap.get(selectedItem);
        stocksItemCodeField.setText(itemCode); // Set the item code
        }
    }
    
    private void addStock() {
    String itemName = (String) itemNameComboBox.getSelectedItem();
    String itemCode = stocksItemCodeField.getText().trim();
    String stockStr = manageStockField.getText().trim();

    if (itemName == null || itemName.isEmpty() || stockStr.isEmpty()) {
        JOptionPane.showMessageDialog(this, "All fields are required.");
        return;
    }

    int stock;
    try {
        stock = Integer.parseInt(stockStr);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid stock value. Please enter a valid number.");
        return;
    }

    // Check if the item already exists in the table
    for (int i = 0; i < manageStockTableModel.getRowCount(); i++) {
        if (manageStockTableModel.getValueAt(i, 0).equals(itemCode)) {
            JOptionPane.showMessageDialog(this, "This item already exists in the stock list.");
            return;
        }
    }

    // Add the new stock entry to the table
    manageStockTableModel.addRow(new Object[]{itemCode, itemName, stock});
    clearManageStocksFields();
    }
    
    private void editStock() {
    int selectedRow = manageStockTable.getSelectedRow();
    if (selectedRow < 0) {
        JOptionPane.showMessageDialog(this, "Please select a row to edit.");
        return;
    }

    String stockStr = manageStockField.getText().trim();
    if (stockStr.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter a stock value.");
        return;
    }

    int stock;
    try {
        stock = Integer.parseInt(stockStr);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid stock value. Please enter a valid number.");
        return;
    }

    // Update the stock value in the table
    manageStockTableModel.setValueAt(stock, selectedRow, 2);
    clearManageStocksFields();
}
    
    private void deleteStock() {
    int selectedRow = manageStockTable.getSelectedRow();
    if (selectedRow < 0) {
        JOptionPane.showMessageDialog(this, "Please select a row to delete.");
        return;
    }

    // Confirm the deletion
    int confirmation = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete this stock entry?", 
            "Confirm Deletion", 
            JOptionPane.YES_NO_OPTION);
    if (confirmation == JOptionPane.YES_OPTION) {
        // Remove the row from the table
        manageStockTableModel.removeRow(selectedRow);
    }
}

    private void saveStockChanges() {
    try (PrintWriter writer = new PrintWriter(new FileWriter("stock_levels.txt"))) {
        int rowCount = manageStockTableModel.getRowCount();

        for (int i = 0; i < rowCount; i++) {
            String itemName = (String) manageStockTableModel.getValueAt(i, 1); // Item Name
            int currentStock = (int) manageStockTableModel.getValueAt(i, 2);   // Current Stock

            writer.println(itemName + "," + currentStock);
        }

        JOptionPane.showMessageDialog(this, "Stock changes saved successfully.");
    } catch (IOException e) {
        JOptionPane.showMessageDialog(this, "Error saving stock changes.");
        e.printStackTrace();
    }
}



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
            java.util.logging.Logger.getLogger(InventoryManagerMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InventoryManagerMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InventoryManagerMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InventoryManagerMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InventoryManagerMenu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addItemButton;
    private javax.swing.JButton addStockButton;
    private javax.swing.JButton addSupplierButton;
    private javax.swing.JButton deleteItemButton;
    private javax.swing.JButton deleteStockButton;
    private javax.swing.JButton deleteSupplierButton;
    private javax.swing.JButton editItemButton;
    private javax.swing.JButton editStockButton;
    private javax.swing.JButton editSupplierButton;
    private javax.swing.JTextField itemCodeField;
    private javax.swing.JTable itemListTable;
    private javax.swing.JComboBox<String> itemNameComboBox;
    private javax.swing.JTextField itemNameField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
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
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField manageStockField;
    private javax.swing.JTable manageStockTable;
    private javax.swing.JButton modifyItemButton;
    private javax.swing.JButton modifySupplierButton;
    private javax.swing.JButton saveStockButton;
    private javax.swing.JTable stockLevelTable;
    private javax.swing.JTable stockUpdateTable;
    private javax.swing.JTextField stocksItemCodeField;
    private javax.swing.JTextField supplierIdEntryField;
    private javax.swing.JTextField supplierIdField;
    private javax.swing.JTable supplierListTable;
    private javax.swing.JTextField supplierNameField;
    private javax.swing.JButton updateStockLevelButton;
    // End of variables declaration//GEN-END:variables
}
