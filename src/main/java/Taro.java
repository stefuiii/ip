import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;

public class Taro {
    private static final String LINE =
            "____________________________________________________________";

    private final Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Taro (String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        ui.showWelcome();

        while (sc.hasNextLine()) {
            String input = sc.nextLine();

            if ("bye".equals(input.trim())) {
                ui.showLine();
                ui.show("Bye. Hope to see you again soon!");
                ui.showLine();
                break;
            }

            if (input.equals("list")){
                ui.showLine();
                this.tasks.list();
                ui.showLine();
                continue;
            }

            if (input.startsWith("mark ") || input.startsWith("unmark ")) {
                // input mark/unmark as a specific format to change status of task
                String[] parts = input.split("\\s+");
                int idx = Integer.parseInt(parts[1]); // 1-based
                Task t = tasks.get(idx - 1);
                if (input.startsWith("mark ")) {
                    t.markAsDone();
                    ui.showLine();
                    ui.show(" Nice! I've marked this task as done:");
                    ui.show("   " + t);
                    ui.showLine();
                    storage.save(tasks);
                    continue;
                } else {
                    t.markAsUndone();
                    ui.showLine();
                    ui.show(" OK, I've marked this task as not done yet:");
                    ui.show("   " + t);
                    ui.showLine();
                    storage.save(tasks);
                    continue;
                }
            } else if (input.startsWith("delete")) {
                String[] parts = input.split("\\s+");
                int idx = Integer.parseInt(parts[1]); // 1-based

                Task removed = tasks.delete(idx);

                System.out.println(LINE);
                System.out.println(" Noted. I've removed this task:");
                System.out.println("   " + removed);
                System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                System.out.println(LINE);
                storage.save(tasks);
                continue;
            }

            try {
                Task t = Parser.parseCommand(input);
                tasks.add(t);
                storage.save(tasks);
                System.out.println("Got it. I've added this task:");
                System.out.println("   " + t);
            } catch (TaroException e) {
                System.out.println("    " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        //Input from user
        new Taro("data/duke.txt").run();
    }
}
