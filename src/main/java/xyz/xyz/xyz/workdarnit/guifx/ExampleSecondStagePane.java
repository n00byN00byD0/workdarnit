package xyz.xyz.xyz.workdarnit.guifx;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import xyz.xyz.xyz.workdarnit.account.Access;

import java.util.function.Consumer;
import java.util.function.Function;

public class ExampleSecondStagePane extends BorderPane implements Consumer<Access> {

    public static final String LOADING_STRING = "Please wait LOADING...";
    private Label accessTypeLabel;
    private String accessStatusString;
    private String promptString;
    private Access currentAccess = new Access() {
        @Override
        public void run() {}
    };

    public ExampleSecondStagePane(Function<Pane, Scene> widerWorld, Consumer<Stage> tweakMe) {
        Stage newStage = new Stage();
        Scene scene = widerWorld.apply(this);
        newStage.setTitle(LOADING_STRING);
        newStage.setScene(scene);
        tweakMe.accept(newStage);
        Platform.runLater(newStage::show);
    }

    @Override
    public void accept(Access access) {

    }

    public String getAccessStatusString() {
        return accessStatusString;
    }
}
