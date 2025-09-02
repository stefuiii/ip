package taro.ui;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * The {@code DialogBox} class is a custom control backed by FXML.
 * It displays a message with an image, aligned differently for
 * user input vs. Taro's responses.
 */
public class DialogBox extends HBox {
    @FXML
    private Label text;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String message, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        text.setText(message);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box so the image is on the left and text on the right.
     */
    private void flip() {
        setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(getChildren());
        FXCollections.reverse(tmp);
        getChildren().setAll(tmp);
    }

    /**
     * Factory method for user messages.
     */
    public static DialogBox getUserDialog(String message, Image img) {
        return new DialogBox(message, img);
    }

    /**
     * Factory method for Taro responses.
     */
    public static DialogBox getDukeDialog(String message, Image img) {
        var db = new DialogBox(message, img);
        db.flip();
        return db;
    }
}
