package view;

import model.*;
import database.DatabaseHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class OrderFrame extends JFrame {
    private JComboBox<Menu> menuCombo;
    private JTextField quantityField;
    private JTextArea outputArea;
    private Order order = new Order();

    public OrderFrame(){
        setTitle("Aplikasi Pemesanan Makanan");
        setSize(400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        menuCombo = new JComboBox<>();
        quantityField = new JTextField(5);
        JButton addButton = new JButton("Tambah");
        JButton submitButton = new JButton("Pesan");
        outputArea = new JTextArea(10, 30);

        loadMenuItems();

        add(new JLabel("Pilih Makanan:"));
        add(menuCombo);
        add(new JLabel("Jumlah:"));
        add(quantityField);
        add(addButton);
        add(submitButton);
        add(new JScrollPane(outputArea));

        addButton.addActionListener(e -> {
            Menu selected = (Menu) menuCombo.getSelectedItem();
            int qty = Integer.parseInt(quantityField.getText());
            order.addItem(new OrderItem(selected, qty));
            outputArea.append(qty + " x " + selected.getName() + "\n");
        });

        submitButton.addActionListener(e -> {
            outputArea.append("Total: Rp " + order.getTotal() + "\nPesanan dikirim!\n");
        });
    }

    private void loadMenuItems(){
        try (Connection conn = DatabaseHelper.connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM menu");
            while (rs.next()) {
                Menu menu = new Menu(
                    rs.getInt("menu_id"),
                    rs.getString("name"),
                    rs.getDouble("price")
                );
                menuCombo.addItem(menu);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}