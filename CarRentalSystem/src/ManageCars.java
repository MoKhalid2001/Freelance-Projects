
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ManageCars extends javax.swing.JFrame {
    private DefaultTableModel model;
    private Connection connection;
    /**
     * Creates new form ManageCars
     * @param connection
     */
    public ManageCars(Connection connection) {
        initComponents();
        model = new DefaultTableModel(new Object[]{"ID", "Make", "Model", "Price", "Available"}, 0);
        carsTable.setModel(model);
        this.connection = connection;
        
        carsTable.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            carsTableMouseClicked(evt);  // Call the custom method when a row is clicked
            }
        });
        
        displayCars();
    }
    
    // Method to display cars from the database in the table
    private void displayCars() {
    model.setRowCount(0);  // Clear existing rows

    String query = "SELECT id, make, model, price, available FROM cars";  // Include available in the query
    try (PreparedStatement stmt = connection.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            int id = rs.getInt("id");  // Fetch car ID
            String make = rs.getString("make");
            String modelStr = rs.getString("model");
            double price = rs.getDouble("price");
            int available = rs.getInt("available");  // Fetch available count

            // Add a row to the table with ID as the first column, and available count as the last column
            model.addRow(new Object[]{id, make, modelStr, price, available});
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading car data.", "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        makeTextField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        modelTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        priceTextField = new javax.swing.JTextField();
        addCarButton = new javax.swing.JButton();
        updateCarButton = new javax.swing.JButton();
        deleteCarButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        carsTable = new javax.swing.JTable();
        backButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        availableTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(122, 178, 211));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(74, 98, 138));
        jLabel1.setText("Manage Cars");

        makeTextField.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        makeTextField.setForeground(new java.awt.Color(74, 98, 138));

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(74, 98, 138));
        jLabel2.setText("Make: ");

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(74, 98, 138));
        jLabel3.setText("Model:");

        modelTextField.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        modelTextField.setForeground(new java.awt.Color(74, 98, 138));

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(74, 98, 138));
        jLabel4.setText("Price:");

        priceTextField.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        priceTextField.setForeground(new java.awt.Color(74, 98, 138));

        addCarButton.setBackground(new java.awt.Color(122, 178, 211));
        addCarButton.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        addCarButton.setForeground(new java.awt.Color(74, 98, 138));
        addCarButton.setText("Add Car");
        addCarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCarButtonActionPerformed(evt);
            }
        });

        updateCarButton.setBackground(new java.awt.Color(122, 178, 211));
        updateCarButton.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        updateCarButton.setForeground(new java.awt.Color(74, 98, 138));
        updateCarButton.setText("Update Car");
        updateCarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateCarButtonActionPerformed(evt);
            }
        });

        deleteCarButton.setBackground(new java.awt.Color(122, 178, 211));
        deleteCarButton.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        deleteCarButton.setForeground(new java.awt.Color(74, 98, 138));
        deleteCarButton.setText("Delete Car");
        deleteCarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteCarButtonActionPerformed(evt);
            }
        });

        carsTable.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        carsTable.setForeground(new java.awt.Color(74, 98, 138));
        carsTable.setModel(new javax.swing.table.DefaultTableModel(
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
        carsTable.setSelectionForeground(new java.awt.Color(74, 98, 138));
        carsTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                carsTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(carsTable);

        backButton.setBackground(new java.awt.Color(122, 178, 211));
        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/back-button.png"))); // NOI18N
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(74, 98, 138));
        jLabel5.setText("Amount:");

        availableTextField.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        availableTextField.setForeground(new java.awt.Color(74, 98, 138));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(makeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(modelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(availableTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(priceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(57, 57, 57)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(addCarButton)
                                    .addComponent(updateCarButton))
                                .addGap(6, 6, 6))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(deleteCarButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(backButton))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(206, 206, 206)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(makeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(addCarButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(modelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(updateCarButton)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(priceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(deleteCarButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(availableTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(backButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addCarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCarButtonActionPerformed
        // Get text from input fields and trim any extra spaces
   String make = makeTextField.getText().trim();
    String model = modelTextField.getText().trim();
    String priceText = priceTextField.getText().trim();
    String availableText = availableTextField.getText().trim();  // New field for available count

    // Validate that all fields are filled out
    if (make.isEmpty() || model.isEmpty() || priceText.isEmpty() || availableText.isEmpty()) {
        JOptionPane.showMessageDialog(this, "All fields must be filled out.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    try {
        double price = Double.parseDouble(priceText);
        int available = Integer.parseInt(availableText);  // Parse available count as integer

        // Insert into cars table
        String query = "INSERT INTO cars (make, model, price, available) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, make);
            stmt.setString(2, model);
            stmt.setDouble(3, price);
            stmt.setInt(4, available);  // Set available count
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Car added successfully!");

            // Refresh the table to show the new car entry
            displayCars();
        }
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Please enter a valid number for price and available count.", "Input Error", JOptionPane.ERROR_MESSAGE);
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error adding car to the database.", "Database Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_addCarButtonActionPerformed
   
    private void updateCarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateCarButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = carsTable.getSelectedRow();

    if (selectedRow != -1) {
        int carId = (int) carsTable.getValueAt(selectedRow, 0);  // Assuming the first column contains the car ID
        String make = makeTextField.getText().trim();
        String model = modelTextField.getText().trim();
        String priceText = priceTextField.getText().trim();
        String availableText = availableTextField.getText().trim();  // New field for available count

        // Validate that all fields are filled out
        if (make.isEmpty() || model.isEmpty() || priceText.isEmpty() || availableText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled out.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            double price = Double.parseDouble(priceText);
            int available = Integer.parseInt(availableText);  // Parse available count as integer

            String query = "UPDATE cars SET make = ?, model = ?, price = ?, available = ? WHERE id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, make);
                stmt.setString(2, model);
                stmt.setDouble(3, price);
                stmt.setInt(4, available);  // Update available count
                stmt.setInt(5, carId);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Car updated successfully!");

                // Refresh the table
                displayCars();
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for price and available count.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating car in the database.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Please select a car to update.");
    }
    }//GEN-LAST:event_updateCarButtonActionPerformed

    private void deleteCarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteCarButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = carsTable.getSelectedRow();

        if (selectedRow != -1) {
            int carId = (int) carsTable.getValueAt(selectedRow, 0);  // Assuming the first column contains the car ID

            String query = "DELETE FROM cars WHERE id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, carId);
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Car deleted successfully!");

                // Refresh the table
                displayCars();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a car to delete.");
        }
    }//GEN-LAST:event_deleteCarButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new AdminDashboard(connection).setVisible(true);
    }//GEN-LAST:event_backButtonActionPerformed

    private void carsTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_carsTableMouseClicked
        // TODO add your handling code here:
        int selectedRow = carsTable.getSelectedRow();  // Get the index of the selected row
        if (selectedRow != -1) {  // Ensure a row is selected
                // Retrieve data from the selected row in the table model
                makeTextField.setText(model.getValueAt(selectedRow, 1).toString());  // Make
                modelTextField.setText(model.getValueAt(selectedRow, 2).toString()); // Model
                priceTextField.setText(model.getValueAt(selectedRow, 3).toString()); // Price
                availableTextField.setText(model.getValueAt(selectedRow, 4).toString()); // Available count
            }
    }//GEN-LAST:event_carsTableMouseClicked

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
            java.util.logging.Logger.getLogger(ManageCars.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageCars.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageCars.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageCars.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addCarButton;
    private javax.swing.JTextField availableTextField;
    private javax.swing.JButton backButton;
    private javax.swing.JTable carsTable;
    private javax.swing.JButton deleteCarButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField makeTextField;
    private javax.swing.JTextField modelTextField;
    private javax.swing.JTextField priceTextField;
    private javax.swing.JButton updateCarButton;
    // End of variables declaration//GEN-END:variables
}
