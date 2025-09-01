package taro.command;

import taro.Storage;
import taro.TaroException;
import taro.TaskList;
import taro.Ui;

/**
 * An interface to identify different types of command
 */
public interface Command {
    boolean execute(TaskList tasks, Ui ui, Storage storage) throws TaroException;
}
