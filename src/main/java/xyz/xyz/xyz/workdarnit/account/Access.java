package xyz.xyz.xyz.workdarnit.account;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class Access implements Runnable {

    protected ArrayList<Runnable> runnables = new ArrayList<>();
    protected String accessTypeString = "No Access \uD83D\uDD72",
            accessStatusString = "Access denied!!!",
            promptString = "enter command...",
            unrecognisedCommandString = "Unrecognized command";


    public void run() {
        runnables.forEach(Runnable::run);
    }

    protected void registeredRunnable(Consumer<Access> progLink,
                                      Supplier<String> commandSource,
                                      Consumer<String> commandResultStringConsumer,
                                      Runnable extraExtra,
                                      Executor executorForExtraExtra) {
        progLink.accept(this);
        Runnable runs = () -> {
            commandResultStringConsumer.accept(commandSource.get());
            executorForExtraExtra.execute(extraExtra);
        };
        runnables.add(runs);
    }

    public String accessTypeString() {
        return accessTypeString;
    }

    public String accessStatusString() {
        return accessStatusString;
    }

    public String promptString() {
        return promptString;
    }
    public String processCommandString(String command) {
        return unrecognisedCommandString + " \"" + command + "\"!";
    }
}
