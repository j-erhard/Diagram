package diagram;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.application.Platform;


import diagram.view.View;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
//         Affichage de la vue
        View view = new View();
//         Création d'une scène sans initialiser la vue
        Scene scene = new Scene(view);
        primaryStage.setTitle("Diagrammes animés");
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.requestFocus();
//         stop le programme lorsque l'on quitte la fenêtre
        primaryStage.setOnCloseRequest(t -> {
            Platform.exit();
            System.exit(0);
        });
    }
}