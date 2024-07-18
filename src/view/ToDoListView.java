package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ToDoListView extends JFrame {
    private JTextField taskField;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton completeButton;
    private JButton viewAllButton;
    private JButton viewCompletedButton;
    private JButton viewPendingButton;
    private JList<String> taskList;
    private DefaultListModel<String> listModel;

    public ToDoListView() {
        setTitle("To-Do List Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        taskField = new JTextField();
        addButton = new JButton("Add Task");
        updateButton = new JButton("Update Task");
        deleteButton = new JButton("Delete Task");
        completeButton = new JButton("Complete Task");
        viewAllButton = new JButton("View All Tasks");
        viewCompletedButton = new JButton("View Completed Tasks");
        viewPendingButton = new JButton("View Pending Tasks");

        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.add(taskField, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(completeButton);

        JPanel viewPanel = new JPanel();
        viewPanel.setLayout(new GridLayout(1, 3));
        viewPanel.add(viewAllButton);
        viewPanel.add(viewCompletedButton);
        viewPanel.add(viewPendingButton);

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(taskList), BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        add(viewPanel, BorderLayout.PAGE_END);
    }

    public String getTaskDescription() {
        return taskField.getText();
    }

    public void setTaskDescription(String description) {
        taskField.setText(description);
    }

    public void addTask(String task) {
        listModel.addElement(task);
    }

    public void updateTask(int index, String task) {
        listModel.set(index, task);
    }

    public void deleteTask(int index) {
        listModel.remove(index);
    }

    public void clearTaskField() {
        taskField.setText("");
    }

    public int getSelectedTaskIndex() {
        return taskList.getSelectedIndex();
    }

    public void setTaskList(java.util.List<String> tasks) {
        listModel.clear();
        for (String task : tasks) {
            listModel.addElement(task);
        }
    }

    public void addAddTaskListener(ActionListener listener) {
        addButton.addActionListener(listener);
    }

    public void addUpdateTaskListener(ActionListener listener) {
        updateButton.addActionListener(listener);
    }

    public void addDeleteTaskListener(ActionListener listener) {
        deleteButton.addActionListener(listener);
    }

    public void addCompleteTaskListener(ActionListener listener) {
        completeButton.addActionListener(listener);
    }

    public void addViewAllTasksListener(ActionListener listener) {
        viewAllButton.addActionListener(listener);
    }

    public void addViewCompletedTasksListener(ActionListener listener) {
        viewCompletedButton.addActionListener(listener);
    }

    public void addViewPendingTasksListener(ActionListener listener) {
        viewPendingButton.addActionListener(listener);
    }
          }
