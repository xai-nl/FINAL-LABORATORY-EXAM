package ui;

import controller.TaskManager;
import model.Task;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class MainWindow extends JFrame {

    private TaskManager taskManager;
    private JTable table;
    private DefaultTableModel tableModel;
    private TaskForm taskForm;

    public MainWindow(TaskManager taskManager) {
        this.taskManager = taskManager;

        setTitle("To-Do List");
        setSize(700, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JButton addTaskBtn = new JButton("Add Task");
        addTaskBtn.addActionListener(e -> openForm());

        tableModel = new DefaultTableModel(
                new String[]{"Task ID", "Task Name", "Task Description", "Status"}, 0
        );
        table = new JTable(tableModel);

        add(addTaskBtn, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void openForm() {
        if (taskForm == null || !taskForm.isDisplayable()) {
            taskForm = new TaskForm(this, taskManager);
            taskForm.setVisible(true);
        }
    }

    public void refreshTable() {
        tableModel.setRowCount(0);
        for (Task task : taskManager.getTasks()) {
            tableModel.addRow(new Object[]{
                    task.getTaskId(),
                    task.getTaskName(),
                    task.getTaskDescription(),
                    task.getStatus()
            });
        }
    }
}
