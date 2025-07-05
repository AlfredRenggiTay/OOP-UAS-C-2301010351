
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.text.NumberFormat;
import java.util.Locale;

public class ButikBaju extends javax.swing.JFrame {
    private DefaultTableModel tableModel;
    private final NumberFormat rupiahFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));

    public ButikBaju() {
        initComponents();
        try {
            tableModel = (DefaultTableModel) table.getModel();
            styleTable();
            loadData();
            txtTotalHarga.setEditable(false);
            getContentPane().setBackground(new Color(240, 248, 255));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error initializing form: " + e.getMessage());
        }
    }

    private void styleTable() {
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setBackground(new Color(70, 130, 180));
        table.getTableHeader().setForeground(Color.WHITE);
        table.setRowHeight(25);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
        lblNamaBaju = new javax.swing.JLabel();
        lblJenis = new javax.swing.JLabel();
        lblHarga = new javax.swing.JLabel();
        lblJumlahBaju = new javax.swing.JLabel();
        txtNamaBaju = new javax.swing.JTextField();
        txtJenis = new javax.swing.JTextField();
        txtHarga = new javax.swing.JTextField();
        txtJumlahBaju = new javax.swing.JTextField();
        txtTotalHarga = new javax.swing.JTextField();
        btnAddStok = new javax.swing.JButton();
        btnSoldOut = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnTotalkan = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pengelolaan Penjualan Butik Baju");

        lblNamaBaju.setFont(new java.awt.Font("Arial", 1, 14));
        lblNamaBaju.setText("Nama Baju:");
        lblJenis.setFont(new java.awt.Font("Arial", 1, 14));
        lblJenis.setText("Jenis:");
        lblHarga.setFont(new java.awt.Font("Arial", 1, 14));
        lblHarga.setText("Harga:");
        lblJumlahBaju.setFont(new java.awt.Font("Arial", 1, 14));
        lblJumlahBaju.setText("Jumlah Baju:");

        txtNamaBaju.setBackground(new java.awt.Color(240, 240, 240));
        txtNamaBaju.setFont(new java.awt.Font("Arial", 0, 14));
        txtJenis.setBackground(new java.awt.Color(240, 240, 240));
        txtJenis.setFont(new java.awt.Font("Arial", 0, 14));
        txtHarga.setBackground(new java.awt.Color(240, 240, 240));
        txtHarga.setFont(new java.awt.Font("Arial", 0, 14));
        txtJumlahBaju.setBackground(new java.awt.Color(240, 240, 240));
        txtJumlahBaju.setFont(new java.awt.Font("Arial", 0, 14));
        txtTotalHarga.setBackground(new java.awt.Color(240, 240, 240));
        txtTotalHarga.setFont(new java.awt.Font("Arial", 0, 14));

        btnAddStok.setFont(new java.awt.Font("Arial", 1, 14));
        btnAddStok.setText("Add Stok");
        btnAddStok.addActionListener(evt -> btnAddStokActionPerformed(evt));

        btnSoldOut.setFont(new java.awt.Font("Arial", 1, 14));
        btnSoldOut.setText("Sold Out");
        btnSoldOut.addActionListener(evt -> btnSoldOutActionPerformed(evt));

        btnEdit.setFont(new java.awt.Font("Arial", 1, 14));
        btnEdit.setText("Edit");
        btnEdit.addActionListener(evt -> btnEditActionPerformed(evt));

        btnTotalkan.setFont(new java.awt.Font("Arial", 1, 14));
        btnTotalkan.setText("Totalkan");
        btnTotalkan.addActionListener(evt -> btnTotalkanActionPerformed(evt));

        table.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Nama Baju", "Jenis", "Harga", "Jumlah Baju", "Total Harga"}
        ) {
            Class[] types = {Integer.class, String.class, String.class, Double.class, Integer.class, Double.class};
            boolean[] canEdit = {false, false, false, false, false, false};
            public Class getColumnClass(int columnIndex) { return types[columnIndex]; }
            public boolean isCellEditable(int rowIndex, int columnIndex) { return canEdit[columnIndex]; }
        });
        table.setGridColor(new java.awt.Color(204, 204, 204));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        txtHarga.addActionListener(e -> hitungTotalHarga());
        txtJumlahBaju.addActionListener(e -> hitungTotalHarga());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNamaBaju)
                            .addComponent(lblJenis)
                            .addComponent(lblHarga)
                            .addComponent(lblJumlahBaju))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNamaBaju, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtJenis, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtJumlahBaju, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnAddStok, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSoldOut, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTotalkan, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 660, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNamaBaju)
                    .addComponent(txtNamaBaju, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAddStok))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblJenis)
                    .addComponent(txtJenis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSoldOut))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblHarga)
                    .addComponent(txtHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEdit))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblJumlahBaju)
                    .addComponent(txtJumlahBaju, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalHarga, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTotalkan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }

    private void hitungTotalHarga() {
        try {
            double harga = Double.parseDouble(txtHarga.getText().isEmpty() ? "0" : txtHarga.getText());
            int jumlah = Integer.parseInt(txtJumlahBaju.getText().isEmpty() ? "0" : txtJumlahBaju.getText());
            double total = harga * jumlah;
            txtTotalHarga.setText(rupiahFormat.format(total).replace(",00", ""));
        } catch (NumberFormatException e) {
            txtTotalHarga.setText("Input tidak valid");
        }
    }

    private void btnAddStokActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            Connection conn = Koneksi.getConnection();
            String sql = "INSERT INTO baju (nama_baju, jenis, harga, jumlah_baju, total_harga) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, txtNamaBaju.getText());
            stmt.setString(2, txtJenis.getText());
            stmt.setDouble(3, Double.parseDouble(txtHarga.getText()));
            stmt.setInt(4, Integer.parseInt(txtJumlahBaju.getText()));
            stmt.setDouble(5, Double.parseDouble(txtTotalHarga.getText().replaceAll("[^\\d.]", "")));
            stmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan!");
            clearFields();
            loadData();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Harga dan Jumlah harus berupa angka!");
        }
    }

    private void btnSoldOutActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            try {
                Connection conn = Koneksi.getConnection();
                String sql = "DELETE FROM baju WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setInt(1, (Integer) tableModel.getValueAt(selectedRow, 0));
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus!");
                clearFields();
                loadData();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih data yang akan dihapus!");
        }
    }

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            try {
                Connection conn = Koneksi.getConnection();
                String sql = "UPDATE baju SET nama_baju = ?, jenis = ?, harga = ?, jumlah_baju = ?, total_harga = ? WHERE id = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, txtNamaBaju.getText());
                stmt.setString(2, txtJenis.getText());
                stmt.setDouble(3, Double.parseDouble(txtHarga.getText()));
                stmt.setInt(4, Integer.parseInt(txtJumlahBaju.getText()));
                stmt.setDouble(5, Double.parseDouble(txtTotalHarga.getText().replaceAll("[^\\d.]", "")));
                stmt.setInt(6, (Integer) tableModel.getValueAt(selectedRow, 0));
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data berhasil diupdate!");
                clearFields();
                loadData();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Error: Harga dan Jumlah harus berupa angka!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih data yang akan diupdate!");
        }
    }

    private void btnTotalkanActionPerformed(java.awt.event.ActionEvent evt) {
        try {
            // Ambil input dari pengguna
            double harga = Double.parseDouble(txtHarga.getText().isEmpty() ? "0" : txtHarga.getText());
            int jumlah = Integer.parseInt(txtJumlahBaju.getText().isEmpty() ? "0" : txtJumlahBaju.getText());
            double totalHarga = harga * jumlah;

            // Simpan ke tabel total_penjualan
            Connection conn = Koneksi.getConnection();
            String sqlInsert = "INSERT INTO total_penjualan (tanggal, total_jumlah, total_harga) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sqlInsert);
            java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
            pstmt.setDate(1, date);
            pstmt.setInt(2, jumlah); // Simpan jumlah dari input pengguna
            pstmt.setDouble(3, totalHarga); // Simpan total harga dari perhitungan
            pstmt.executeUpdate();

            // Tampilkan konfirmasi
            JOptionPane.showMessageDialog(this, "Total Jumlah Baju: " + jumlah + "\nTotal Harga: " + rupiahFormat.format(totalHarga).replace(",00", "") + "\nData tersimpan!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error: Harga dan Jumlah harus berupa angka!");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow >= 0) {
            txtNamaBaju.setText(tableModel.getValueAt(selectedRow, 1).toString());
            txtJenis.setText(tableModel.getValueAt(selectedRow, 2).toString());
            txtHarga.setText(tableModel.getValueAt(selectedRow, 3).toString());
            txtJumlahBaju.setText(tableModel.getValueAt(selectedRow, 4).toString());
            txtTotalHarga.setText(tableModel.getValueAt(selectedRow, 5).toString());
        }
    }

    private void loadData() {
        tableModel.setRowCount(0);
        try {
            Connection conn = Koneksi.getConnection();
            String sql = "SELECT id, nama_baju, jenis, harga, jumlah_baju, total_harga FROM baju";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("id"),
                    rs.getString("nama_baju"),
                    rs.getString("jenis"),
                    rs.getDouble("harga"),
                    rs.getInt("jumlah_baju"),
                    rs.getDouble("total_harga")
                };
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
        }
    }

    private void clearFields() {
        txtNamaBaju.setText("");
        txtJenis.setText("");
        txtHarga.setText("");
        txtJumlahBaju.setText("");
        txtTotalHarga.setText("");
    }

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ButikBaju.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        java.awt.EventQueue.invokeLater(() -> new ButikBaju().setVisible(true));
    }

    // Variables declaration
    private javax.swing.JButton btnSoldOut;
    private javax.swing.JButton btnAddStok;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnTotalkan;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblHarga;
    private javax.swing.JLabel lblJenis;
    private javax.swing.JLabel lblJumlahBaju;
    private javax.swing.JLabel lblNamaBaju;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtHarga;
    private javax.swing.JTextField txtJenis;
    private javax.swing.JTextField txtJumlahBaju;
    private javax.swing.JTextField txtNamaBaju;
    private javax.swing.JTextField txtTotalHarga;
    // End of variables declaration
}
