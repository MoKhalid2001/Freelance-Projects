
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class ManageUsers extends javax.swing.JFrame {
    private DefaultTableModel adminStaffModel;
    private DefaultTableModel customerModel;
    private Connection connection;
    /**
     * Creates new form ManageUsers
     */
    public ManageUsers(Connection connection) {
        initComponents();
        this.connection = connection;    
        adminStaffModel = new DefaultTableModel(new Object[]{"ID", "Username", "Password", "Email", "Phone", "Role"}, 0);
        customerModel = new DefaultTableModel(new Object[]{"ID", "Username", "Password", "Email", "Phone", "Name", "Total Price"}, 0);
        adminStaffTable.setModel(adminStaffModel);  // Table for Admin and Staff
        customerTable.setModel(customerModel);      // Table for Customers
        // Group the radio buttons
        roleButtonGroup = new ButtonGroup();
        roleButtonGroup.add(adminRadioButton);
        roleButtonGroup.add(customerRadioButton);
        
        displayAdminStaffUsers();
        displayCustomers();
        
        adminStaffTable.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            populateAdminStaffFields();
        }
    });

        customerTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                populateCustomerFields();
            }
});
    }

    // Method to get the selected role from the radio buttons
    private String getSelectedRole() {
        if (adminRadioButton.isSelected()) {
            return "Admin";
        } else {
            return "Customer";
        }
    }
    
    // Method to display users from the database in the table (same as before)
    private void displayAdminStaffUsers() {
    adminStaffModel.setRowCount(0);  // Clear existing rows

    String query = "SELECT id, username, password, email, phone, role FROM users WHERE role IN ('Admin', 'Staff')";
    try (PreparedStatement stmt = connection.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String email = rs.getString("email");
            String phone = rs.getString("phone");
            String role = rs.getString("role");

            // Add row to Admin/Staff table model
            adminStaffModel.addRow(new Object[]{id, username, password, email, phone, role});
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading Admin/Staff data.", "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}

    private void displayCustomers() {
    customerModel.setRowCount(0);  // Clear existing rows

    String query = "SELECT id, username, password, email, phone, name, total_price FROM customers";
    try (PreparedStatement stmt = connection.prepareStatement(query);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            int id = rs.getInt("id");
            String username = rs.getString("username");
            String password = rs.getString("password");
            String email = rs.getString("email");
            String phone = rs.getString("phone");
            String name = rs.getString("name");
            double totalPrice = rs.getDouble("total_price");

            // Add row to Customer table model
            customerModel.addRow(new Object[]{id, username, password, email, phone, name, totalPrice});
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error loading Customer data.", "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}

    // Methods to populate the form fields when a user is selected from the table
    private void populateAdminStaffFields() {
    int selectedRow = adminStaffTable.getSelectedRow();  // Get selected row index
    if (selectedRow != -1) {  // Ensure a row is selected
        int userId = (int) adminStaffTable.getValueAt(selectedRow, 0);  // Assuming ID is in the first column
        String username = adminStaffTable.getValueAt(selectedRow, 1).toString();
        String password = adminStaffTable.getValueAt(selectedRow, 2).toString();
        String email = adminStaffTable.getValueAt(selectedRow, 3).toString();
        String phone = adminStaffTable.getValueAt(selectedRow, 4).toString();
        String role = adminStaffTable.getValueAt(selectedRow, 5).toString();

        // Populate the form fields
        usernameTextField.setText(username);
        passwordTextField.setText(password);
        emailTextField.setText(email);
        phoneTextField.setText(phone);

        // Set the appropriate radio button for the role
        if (role.equalsIgnoreCase("Admin")) {
            adminRadioButton.setSelected(true);
        }
    }
}

    private void populateCustomerFields() {
    int selectedRow = customerTable.getSelectedRow();  // Get selected row index
    if (selectedRow != -1) {  // Ensure a row is selected
        int customerId = (int) customerTable.getValueAt(selectedRow, 0);  // Assuming ID is in the first column
        String username = customerTable.getValueAt(selectedRow, 1).toString();
        String password = customerTable.getValueAt(selectedRow, 2).toString();
        String email = customerTable.getValueAt(selectedRow, 3).toString();
        String phone = customerTable.getValueAt(selectedRow, 4).toString();
        String name = customerTable.getValueAt(selectedRow, 5).toString();
        String totalPrice = customerTable.getValueAt(selectedRow, 6).toString();

        // Populate the form fields
        usernameTextField.setText(username);
        passwordTextField.setText(password);
        emailTextField.setText(email);
        phoneTextField.setText(phone);
        nameTextField.setText(name);
        
        // Set the Customer radio button
        customerRadioButton.setSelected(true);
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

        roleButtonGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        passwordTextField = new javax.swing.JTextField();
        addUserButton = new javax.swing.JButton();
        updateUserButton = new javax.swing.JButton();
        deleteUserButton = new javax.swing.JButton();
        adminRadioButton = new javax.swing.JRadioButton();
        customerRadioButton = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        adminStaffTable = new javax.swing.JTable();
        backButton = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        usernameTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        phoneTextField = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        customerTable = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        nameTextField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(122, 178, 211));
        jPanel1.setForeground(new java.awt.Color(74, 98, 138));

        jLabel1.setFont(new java.awt.Font("Segoe UI Light", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(74, 98, 138));
        jLabel1.setText("Manage Users");

        jLabel2.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(74, 98, 138));
        jLabel2.setText("Username:");

        emailTextField.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        emailTextField.setForeground(new java.awt.Color(74, 98, 138));

        jLabel3.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(74, 98, 138));
        jLabel3.setText("Email:");

        passwordTextField.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        passwordTextField.setForeground(new java.awt.Color(74, 98, 138));

        addUserButton.setBackground(new java.awt.Color(122, 178, 211));
        addUserButton.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        addUserButton.setForeground(new java.awt.Color(74, 98, 138));
        addUserButton.setText("Add User");
        addUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUserButtonActionPerformed(evt);
            }
        });

        updateUserButton.setBackground(new java.awt.Color(122, 178, 211));
        updateUserButton.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        updateUserButton.setForeground(new java.awt.Color(74, 98, 138));
        updateUserButton.setText("Update User");
        updateUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateUserButtonActionPerformed(evt);
            }
        });

        deleteUserButton.setBackground(new java.awt.Color(122, 178, 211));
        deleteUserButton.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        deleteUserButton.setForeground(new java.awt.Color(74, 98, 138));
        deleteUserButton.setText("Delete User");
        deleteUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteUserButtonActionPerformed(evt);
            }
        });

        adminRadioButton.setBackground(new java.awt.Color(185, 229, 232));
        adminRadioButton.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        adminRadioButton.setForeground(new java.awt.Color(74, 98, 138));
        adminRadioButton.setText("Admin");

        customerRadioButton.setBackground(new java.awt.Color(185, 229, 232));
        customerRadioButton.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        customerRadioButton.setForeground(new java.awt.Color(74, 98, 138));
        customerRadioButton.setText("Customer");

        jLabel4.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(74, 98, 138));
        jLabel4.setText("Role:");

        adminStaffTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(adminStaffTable);

        backButton.setBackground(new java.awt.Color(122, 178, 211));
        backButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Images/back-button.png"))); // NOI18N
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(74, 98, 138));
        jLabel5.setText("Password:");

        usernameTextField.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        usernameTextField.setForeground(new java.awt.Color(74, 98, 138));

        jLabel6.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(74, 98, 138));
        jLabel6.setText("Phone:");

        phoneTextField.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        phoneTextField.setForeground(new java.awt.Color(74, 98, 138));

        customerTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(customerTable);

        jLabel7.setFont(new java.awt.Font("Segoe UI Light", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(74, 98, 138));
        jLabel7.setText("Customers");

        jLabel8.setFont(new java.awt.Font("Segoe UI Light", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(74, 98, 138));
        jLabel8.setText("Admin");

        jLabel9.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(74, 98, 138));
        jLabel9.setText("Name");

        nameTextField.setFont(new java.awt.Font("Segoe UI Light", 1, 18)); // NOI18N
        nameTextField.setForeground(new java.awt.Color(74, 98, 138));

        jLabel10.setFont(new java.awt.Font("Segoe UI Light", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(204, 0, 0));
        jLabel10.setText("for customers");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(adminRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(customerRadioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addComponent(backButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel6))
                                        .addGap(44, 44, 44))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(18, 18, 18)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(emailTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                                    .addComponent(phoneTextField)
                                    .addComponent(passwordTextField))
                                .addGap(46, 46, 46)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(updateUserButton)
                                    .addComponent(deleteUserButton)
                                    .addComponent(addUserButton)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(314, 314, 314))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(250, 250, 250)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(109, 109, 109)
                    .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(567, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(110, 110, 110)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(627, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel9)
                            .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(emailTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(addUserButton)))
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(phoneTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(updateUserButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(deleteUserButton))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(adminRadioButton)
                            .addComponent(customerRadioButton))
                        .addGap(59, 59, 59)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(backButton, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE))))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(70, 70, 70)
                    .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(494, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(294, 294, 294)
                    .addComponent(jLabel8)
                    .addContainerGap(319, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void addUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUserButtonActionPerformed
        // TODO add your handling code here:
        String username = usernameTextField.getText().trim();
    String password = passwordTextField.getText().trim();
    String email = emailTextField.getText().trim();
    String phone = phoneTextField.getText().trim();
    String role = getSelectedRole();  // Retrieve selected role (Admin, Staff, or Customer)

    // Validate that all fields are filled out
    if (username.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty()) {
        JOptionPane.showMessageDialog(this, "All fields must be filled out.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Validate phone number contains only digits
    if (!phone.matches("\\d+")) {
        JOptionPane.showMessageDialog(this, "Phone number must contain only numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Validate email format
    if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
        JOptionPane.showMessageDialog(this, "Please enter a valid email address.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    String query;
    if (role.equalsIgnoreCase("Customer")) {
        // Insert into customers table
        query = "INSERT INTO customers (username, password, email, phone, name, total_price) VALUES (?, ?, ?, ?, ?, 0.0)";
    } else {
        // Insert into users table
        query = "INSERT INTO users (username, password, email, phone, role) VALUES (?, ?, ?, ?, ?)";
    }

    try (PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setString(1, username);
        stmt.setString(2, password);
        stmt.setString(3, email);
        stmt.setString(4, phone);

        if (role.equalsIgnoreCase("Customer")) {
            stmt.setString(5, username);  // Set name as username in the customers table
        } else {
            stmt.setString(5, role);  // Set role in the users table
        }

        stmt.executeUpdate();
        JOptionPane.showMessageDialog(this, "User added successfully!");

        // Refresh the tables
        displayAdminStaffUsers();
        displayCustomers();
        
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error adding user to the database.", "Database Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_addUserButtonActionPerformed

    private void updateUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateUserButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = adminStaffTable.getSelectedRow();

        if (selectedRow != -1) {
            int userId = (int) adminStaffTable.getValueAt(selectedRow, 0);  // Assuming the first column contains the user ID
            String username = usernameTextField.getText().trim();
            String password = passwordTextField.getText().trim();
            String email = emailTextField.getText().trim();
            String phone = phoneTextField.getText().trim();
            String role = getSelectedRole();

            // Validate fields
            if (username.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields must be filled out.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!phone.matches("\\d+")) {
                JOptionPane.showMessageDialog(this, "Phone number must contain only numbers.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                JOptionPane.showMessageDialog(this, "Please enter a valid email address.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String query;
            if (role.equalsIgnoreCase("Customer")) {
                // Update customers table
                query = "UPDATE customers SET username = ?, password = ?, email = ?, phone = ?, name = ? WHERE id = ?";
            } else {
                // Update users table
                query = "UPDATE users SET username = ?, password = ?, email = ?, phone = ?, role = ? WHERE id = ?";
            }

            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, username);
                stmt.setString(2, password);
                stmt.setString(3, email);
                stmt.setString(4, phone);

                if (role.equalsIgnoreCase("Customer")) {
                    stmt.setString(5, username);  // Update name in the customers table
                } else {
                    stmt.setString(5, role);  // Update role in the users table
                }

                stmt.setInt(6, userId);
                stmt.executeUpdate();

                JOptionPane.showMessageDialog(this, "User updated successfully!");

                 // Refresh the tables
                displayAdminStaffUsers();
                displayCustomers();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error updating user in the database.", "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select a user to update.");
        }
    }//GEN-LAST:event_updateUserButtonActionPerformed

    private void deleteUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteUserButtonActionPerformed
        // TODO add your handling code here:
        int selectedRow = adminStaffTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a user to delete.", "Selection Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int userId = (int) adminStaffTable.getValueAt(selectedRow, 0);  // Get the user ID from the table
        String role = getSelectedRole();

        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this user?", "Delete User", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String query;
            if (role.equalsIgnoreCase("Customer")) {
                // Delete from customers table
                query = "DELETE FROM customers WHERE id = ?";
            } else {
                // Delete from users table
                query = "DELETE FROM users WHERE id = ?";
            }

            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, userId);
                stmt.executeUpdate();

                JOptionPane.showMessageDialog(this, "User deleted successfully!");

                 // Refresh the tables
                displayAdminStaffUsers();
                displayCustomers();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error deleting user from the database.", "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_deleteUserButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        this.dispose();
        new AdminDashboard(connection).setVisible(true);
    }//GEN-LAST:event_backButtonActionPerformed

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
            java.util.logging.Logger.getLogger(ManageUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageUsers.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
              //  new ManageUsers().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addUserButton;
    private javax.swing.JRadioButton adminRadioButton;
    private javax.swing.JTable adminStaffTable;
    private javax.swing.JButton backButton;
    private javax.swing.JRadioButton customerRadioButton;
    private javax.swing.JTable customerTable;
    private javax.swing.JButton deleteUserButton;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JTextField passwordTextField;
    private javax.swing.JTextField phoneTextField;
    private javax.swing.ButtonGroup roleButtonGroup;
    private javax.swing.JButton updateUserButton;
    private javax.swing.JTextField usernameTextField;
    // End of variables declaration//GEN-END:variables
}
