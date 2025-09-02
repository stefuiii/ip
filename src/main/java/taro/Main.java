package taro;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import taro.corecomp.Taro;
import taro.ui.DialogBox;


/**
 * The {@code Main} class initializes and launches the JavaFX GUI
 * for the Taro chatbot. It sets up the layout, handles user input,
 * and displays dialog boxes for both the user and Taro.
 */
public class Main extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private AnchorPane mainLayout;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/images.png"));
    private Image taroImage = new Image(this.getClass().getResourceAsStream("/images/images.png"));

    private Taro taro = new Taro("data/taro.txt");

    /**
     * Handles user input by reading the text from the input field,
     * generating a response from Taro, and displaying both
     * user and Taro dialog boxes in the container.
     */
    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        userInput = new TextField();
        sendButton = new Button("Send");
        mainLayout = new AnchorPane();

        stage.setTitle("Taro");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setContent(dialogContainer);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);

        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(scrollPane, 40.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);

        sendButton.setOnMouseClicked(event -> handleUserInput());
        userInput.setOnAction(event -> handleUserInput());

        dialogContainer.heightProperty().addListener(observable -> scrollPane.setVvalue(1.0));


        dialogContainer.getChildren().add(
                DialogBox.getDukeDialog(taro.getUi().showWelcome(), taroImage)
        );

        Scene scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Handles user input by reading the text from the input field,
     * generating a response from Taro, and displaying both
     * user and Taro dialog boxes in the container.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        String taroText = taro.getResponse(userText);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getDukeDialog(taroText, taroImage)
        );

        userInput.clear();
    }

    /**
     * The entry point of the program. Launches the JavaFX application.
     *
     * @param args command line arguments passed to the application
     */
    public static void main(String[] args) {
        launch(args);
    }
}
