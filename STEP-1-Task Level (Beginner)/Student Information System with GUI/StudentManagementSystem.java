import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class StudentManagementSystem extends JFrame implements ActionListener {

    JTextField tfId, tfName, tfDept, tfMarks;
    JButton btnAdd, btnUpdate, btnDelete, btnClear;
    JTable table;
    DefaultTableModel model;

    public StudentManagementSystem() {

        setTitle("Student Information System");
        setSize(700, 500);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        JLabel l1 = new JLabel("ID:");
        l1.setBounds(30, 30, 100, 25);
        add(l1);

        tfId = new JTextField();
        tfId.setBounds(130, 30, 150, 25);
        add(tfId);

        JLabel l2 = new JLabel("Name:");
        l2.setBounds(30, 70, 100, 25);
        add(l2);

        tfName = new JTextField();
        tfName.setBounds(130, 70, 150, 25);
        add(tfName);

        JLabel l3 = new JLabel("Department:");
        l3.setBounds(30, 110, 100, 25);
        add(l3);

        tfDept = new JTextField();
        tfDept.setBounds(130, 110, 150, 25);
        add(tfDept);

        JLabel l4 = new JLabel("Marks:");
        l4.setBounds(30, 150, 100, 25);
        add(l4);

        tfMarks = new JTextField();
        tfMarks.setBounds(130, 150, 150, 25);
        add(tfMarks);

        btnAdd = new JButton("Add");
        btnAdd.setBounds(320, 30, 100, 30);
        add(btnAdd);

        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(320, 70, 100, 30);
        add(btnUpdate);

        btnDelete = new JButton("Delete");
        btnDelete.setBounds(320, 110, 100, 30);
        add(btnDelete);

        btnClear = new JButton("Clear");
        btnClear.setBounds(320, 150, 100, 30);
        add(btnClear);

        btnAdd.addActionListener(this);
        btnUpdate.addActionListener(this);
        btnDelete.addActionListener(this);
        btnClear.addActionListener(this);

        model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Department");
        model.addColumn("Marks");

        table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(30, 220, 620, 200);
        add(sp);

        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();

                tfId.setText(model.getValueAt(row, 0).toString());
                tfName.setText(model.getValueAt(row, 1).toString());
                tfDept.setText(model.getValueAt(row, 2).toString());
                tfMarks.setText(model.getValueAt(row, 3).toString());
            }
        });

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnAdd) {
            model.addRow(new Object[]{
                    tfId.getText(),
                    tfName.getText(),
                    tfDept.getText(),
                    tfMarks.getText()
            });
        }

        if (e.getSource() == btnUpdate) {
            int row = table.getSelectedRow();

            model.setValueAt(tfId.getText(), row, 0);
            model.setValueAt(tfName.getText(), row, 1);
            model.setValueAt(tfDept.getText(), row, 2);
            model.setValueAt(tfMarks.getText(), row, 3);
        }

        if (e.getSource() == btnDelete) {
            int row = table.getSelectedRow();
            model.removeRow(row);
        }

        if (e.getSource() == btnClear) {
            tfId.setText("");
            tfName.setText("");
            tfDept.setText("");
            tfMarks.setText("");
        }
    }

    public static void main(String[] args) {
        new StudentManagementSystem();
    }
}