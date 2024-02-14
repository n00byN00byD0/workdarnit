package xyz.xyz.xyz.workdarnit;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClientFxApp extends Application {
    @Override
    public void start(Stage stage) {
        Insets mainPadding = new Insets(8);
        BorderPane mainPane = new BorderPane();
        FlowPane topPane = new FlowPane();
        topPane.setAlignment(Pos.CENTER);
        topPane.setPadding(mainPadding);
        Label instructionLabel = new Label("Please enter your name and password:");
        topPane.getChildren().add(instructionLabel);
        mainPane.setTop(topPane);

        VBox centerPane = new VBox();
        centerPane.setSpacing(4);
        centerPane.setPadding(mainPadding);
        TextField nameField = new TextField();
        nameField.setPromptText("name...");
        PasswordField pwdField = new PasswordField();
        pwdField.setPromptText("password...");
        Button doneButton = new Button("Done");
        FlowPane floatItRight = new FlowPane(doneButton);
        floatItRight.setAlignment(Pos.BOTTOM_RIGHT);
        centerPane.getChildren().addAll(nameField, pwdField, floatItRight);
        mainPane.setCenter(centerPane);


        Scene scene = new Scene(mainPane);
        stage.setTitle("Hello and Welcome!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}