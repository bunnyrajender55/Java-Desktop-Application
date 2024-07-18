package controller;

import model.Task;
import view.ToDoListView;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ToDoListController {
    private List<Task> tasks;
    private ToDoListView view;
    private static final String DATA_FILE = "tasks.dat";

    public ToDoListController(ToDoListView view) {
        this.view = view;
        tasks = loadTasks();
        view.setTaskList(tasks.stream().map(Task::toString).collect(Collectors.toList()));

        view.addAddTaskListener(e -> addTask());
        view.addUpdateTaskListener(e -> updateTask());
        view.addDeleteTaskListener(e -> deleteTask());
        view.addCompleteTaskListener(e -> completeTask());
        view.addViewAllTasksListener(e -> viewAllTasks());
        view.addViewCompletedTasksListener(e -> viewCompletedTasks());
        view.addViewPendingTasksListener(e -> viewPendingTasks());
    }

    private void addTask() {
        String description = view.getTaskDescription();
        if (!description.isEmpty()) {
            Task task = new Task(description);
            tasks.add(task);
            view.addTask(task.toString());
            saveTasks();
            view.clearTaskField();
        } else {
            JOptionPane.showMessageDialog(view, "Task description cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateTask() {
        int index = view.getSelectedTaskIndex();
        if (index >= 0) {
            String description = view.getTaskDescription();
            if (!description.isEmpty()) {
                Task task = tasks.get(index);
                task.setDescription(description);
                view.updateTask(index, task.toString());
                saveTasks();
                view.clearTaskField();
            } else {
                JOptionPane.showMessageDialog(view, "Task description cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(view, "No task selected.", "Selection Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteTask() {
        int index = view.getSelectedTaskIndex();
        if (index >= 0) {
            tasks.remove(index);
            view.deleteTask(index);
            saveTasks();
        } else {
            JOptionPane.showMessageDialog(view, "No task selected.", "Selection Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void completeTask() {
        int index = view.getSelectedTaskIndex();
        if (index >= 0) {
            Task task = tasks.get(index);
            task.setCompleted(!task.isCompleted());
            view.updateTask(index, task.toString());
            saveTasks();
        } else {
            JOptionPane.showMessageDialog(view, "No task selected.", "Selection Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewAllTasks() {
        view.setTaskList(tasks.stream().map(Task::toString).collect(Collectors.toList()));
    }

    private void viewCompletedTasks() {
        view.setTaskList(tasks.stream().filter(Task::isCompleted).map(Task::toString).collect(Collectors.toList()));
    }

    private void viewPendingTasks() {
        view.setTaskList(tasks.stream().filter(task -> !task.isCompleted()).map(Task::toString).collect(Collectors.toList()));
    }

    private void saveTasks() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            out.writeObject(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Task> loadTasks() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
            return (List<Task>) in.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ToDoListView view = new ToDoListView();
            new ToDoListController(view);
            view.setVisible(true);
        });
    }
}
