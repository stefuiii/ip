package taro;

import taro.command.Command;

public class Taro {

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

        ui.showWelcome();

        boolean isEnd = false;

        while (!isEnd) {
            String input = ui.readCommand();

            try {
                Command c = Parser.parseCommand(input);
                isEnd = c.execute(tasks, ui, storage);
            } catch (TaroException e) {
                System.out.println("    " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        //Input from user
        new Taro("data/taro.txt").run();
    }
}
