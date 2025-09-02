package taro.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * The {@code DialogBox} class represents a custom JavaFX control
 * for displaying a single dialog entry consisting of text and an image.
 * <p>
 * It is used to differentiate between the user's input messages and
 * Taro's responses by flipping the alignment of the components.
 */
public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    /**
     * Constructs a {@code DialogBox} with the given message text and image.
     *
     * @param message the text to display inside the dialog box
     * @param img the image to display alongside the text
     */
    public DialogBox(String message, Image img) {
        text = new Label(message);
        displayPicture = new ImageView(img);

        displayPicture.setFitWidth(50.0);
        displayPicture.setFitHeight(50.0);

        this.setAlignment(Pos.TOP_RIGHT);
        this.getChildren().addAll(text, displayPicture);
    }

    /**
     * Flips the dialog box such that the {@link ImageView} is placed on the left
     * and the text is placed on the right, used for differentiating Taro's dialog.
     */
    private void flip() {
        this.setAlignment(Pos.TOP_LEFT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * Creates a dialog box styled for user messages.
     *
     * @param s the text message from the user
     * @param i the image to represent the user
     * @return a {@code DialogBox} aligned to the right
     */
    public static DialogBox getUserDialog(String s, Image i) {
        return new DialogBox(s, i);
    }

    /**
     * Creates a dialog box styled for Taro's responses.
     * The box is flipped so that the image appears on the left.
     *
     * @param s the response text from Taro
     * @param i the image to represent Taro
     * @return a {@code DialogBox} aligned to the left
     */
    public static DialogBox getDukeDialog(String s, Image i) {
        var db = new DialogBox(s, i);
        db.flip();
        return db;
    }
}
