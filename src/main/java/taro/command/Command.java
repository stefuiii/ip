package taro.command;

import taro.Storage;
import taro.TaroException;
import taro.TaskList;
import taro.Ui;

public interface Command {
    boolean execute(TaskList tasks, Ui ui, Storage storage) throws TaroException;
}
