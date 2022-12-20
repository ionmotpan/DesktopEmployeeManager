package it.step.Controllers;

import it.step.DB.DbUser;
import it.step.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class LoginController {
    public TextField usernameTextField;
    public TextField passwordTextField;

    List<User> users;
    DbUser db = new DbUser();
    boolean found = false;

    public void onLogin(ActionEvent event) throws IOException {

        if(!usernameTextField.getText().isEmpty() || !passwordTextField.getText().isEmpty()){

            users = db.read();
            for(User user : users) {
                if(usernameTextField.getText().equals(user.getUsername()) && passwordTextField.getText().equals(user.getPassword())) {
                    found = true;
                }
            }
            if(found) {
                Node node = (Node) event.getSource();
                Stage stage = (Stage) node.getScene().getWindow();
                stage.close();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
                AnchorPane pane = (AnchorPane) loader.load();
                Scene scene = new Scene(pane);
                Stage stage1 = new Stage();
                stage1.setTitle("Manager System");
                stage1.setScene(scene);
                stage1.show();
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Informatie incorecta");
                alert.show();
            }

        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Va rog introduceti date in cimpuri");
            alert.show();
        }

    }

    public void onRegistration(ActionEvent event) throws IOException {

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/registration.fxml"));
        AnchorPane pane = (AnchorPane) loader.load();
        Stage stage1 = new Stage();
        Scene scene = new Scene(pane);
        stage1.setScene(scene);
        stage1.setTitle("Registration");
        stage1.show();
    }
}
