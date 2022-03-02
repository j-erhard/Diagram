package diagram.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Objects;

public class ControllerMenu extends Controller{


    public ControllerMenu() {
    }


    /**
     * Quitte l'application.
     */
    @FXML
    public void quitter() {
        Platform.exit();
        System.exit(0);
    }
}