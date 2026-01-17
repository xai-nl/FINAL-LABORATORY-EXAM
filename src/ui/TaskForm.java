package ui;

import controller.TaskManager;
import model.Task;
import ui.MainWindow;
import javax.swing.*;
import java.awt.*;

public class TaskForm extends JFrame {

    private JTextField txtId, txtName;
    private JTextArea txtDesc;
    private JComboBox<String> cmbStatus;

    public TaskForm(MainWindow parent, TaskManager manager) {
        setTitle("Add Task");
        setSize(400, 300);
        setLayout(new GridLayout(5, 2));
        setLocationRelativeTo(parent);

        txtId = new JTextField(String.valueOf(manager.generateTaskId()));
        txtId.setEditable(false);

        txtName = new JTextField();
        txtDesc = new JTextArea();

        cmbStatus = new JComboBox<>(new String[]{
                "NOT STARTED", "ONGOING", "DONE"
        });

        JButton btnSave = new JButton("Save Task");

        btnSave.addActionListener(e -> {
            if (txtName.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Task Name is required");
                return;
            }

            Task task = new Task(
                    Integer.parseInt(txtId.getText()),
                    txtName.getText(),
                    txtDesc.getText(),
                    cmbStatus.getSelectedItem().toString()
            );

            manager.addTask(task);
            parent.refreshTable();
            dispose();
        });

        add(new JLabel("Task ID:"));
        add(txtId);
        add(new JLabel("Task Name:"));
        add(txtName);
        add(new JLabel("Description:"));
        add(new JScrollPane(txtDesc));
        add(new JLabel("Status:"));
        add(cmbStatus);
        add(new JLabel());
        add(btnSave);
    }
}
