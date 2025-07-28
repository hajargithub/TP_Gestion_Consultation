package ma.enset.gestionconsultations;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root= FXMLLoader.load(getClass().getResource("views/PatientView.fxml"));
        Scene scene=new Scene(root,650,600);
        stage.setScene(scene);
        stage.setTitle("Gestion des consultations ");
        stage.show();
    }
}
