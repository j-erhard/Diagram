package diagram.controller;

import diagram.model.AnimateDiagBar;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ControllerMenu extends Controller{


    public TextField fichier;
    public CheckBox sort;
    public Spinner nbbar;

    public ControllerMenu() {
    }

    public void initialize() {
        fichier.setText("outFile_prof");
    }


    /**
     * Quitte l'application.
     */
    @FXML
    public void quitter() {
        Platform.exit();
        System.exit(0);
    }

    public void generer(ActionEvent actionEvent) throws IOException {
        String trie = sort.isSelected() ? trie = "sort" : "nosort";
        AnimateDiagBar.main(new String[]{"/home/julien/Bureau/Diagram/src/diagram/model/" + fichier.getText(), nbbar.getValue().toString(), String.valueOf(trie)});
    }
}