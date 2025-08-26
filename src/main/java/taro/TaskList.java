package taro;

import taro.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;


    /**
     * Constructs an empty {@code TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a {@code TaskList} initialized with the given list of tasks.
     *
     * @param init an existing list of tasks to initialize this task list with
     */
    public TaskList(ArrayList<Task> init) {
        this.tasks = init;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the size of the task list
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Retrieves the task at the specified index (0-based).
     *
     * @param idx the index of the task to retrieve
     * @return the task at the given index
     */
    public Task get(int idx) {
        return tasks.get(idx);
    }

    /**
     * Adds a task to the task list.
     *
     * @param t the task to add
     * @return the same task that was added
     */
    public Task add(Task t) {
        tasks.add(t);
        return t;
    }

    /**
     * Deletes the task at the specified index (1-based).
     *
     * @param delIndex the 1-based index of the task to remove
     * @return the task that was removed
     * @throws IndexOutOfBoundsException if the index is invalid
     */
    public Task delete(int delIndex) {
        int i = delIndex - 1;
        return tasks.remove(i);
    }

    /**
     * Prints the tasks in the list to the standard output in a numbered format.
     * Each task is displayed on its own line.
     */
    public void list() {
        System.out.println("  Here are tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + tasks.get(i));
        }
    }

}
