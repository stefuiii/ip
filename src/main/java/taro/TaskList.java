package taro;

import taro.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> init) {
        this.tasks = init;
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int idx) {
        return tasks.get(idx);
    }

    public Task add(Task t) {
        tasks.add(t);
        return t;
    }

    public Task delete(int delIndex) {
        int i = delIndex - 1;
        return tasks.remove(i);
    }

    public void list() {
        System.out.println("  Here are tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + tasks.get(i));
        }
    }

}
