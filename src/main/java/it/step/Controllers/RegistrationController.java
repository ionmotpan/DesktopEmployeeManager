package it.step.Controllers;

import it.step.DB.DbUser;
import it.step.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrationController {

    public TextField firstNameField;
    public TextField lastNameField;
    public TextField userNameField;
    public PasswordField passwordField;
    public CheckBox activeCheckBox;

    DbUser db = new DbUser();
    boolean active;

    public void onLogin(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        Stage stage =(Stage) node.getScene().getWindow();
        stage.close();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        AnchorPane pane = (AnchorPane)loader.load();
        Stage stage1 = new Stage();
        Scene scene = new Scene(pane);
        stage1.setScene(scene);
        stage1.setTitle("Login");
        stage1.show();
    }

    public void onRegistration(ActionEvent event) throws IOException {
        if(!firstNameField.getText().isEmpty() || !lastNameField.getText().isEmpty() || !userNameField.getText().isEmpty() || !passwordField.getText().isEmpty()) {

            if(activeCheckBox.isSelected()){
                active = true;
            }else{
                active = false;
            }

            db.create(new User(firstNameField.getText(), lastNameField.getText(), userNameField.getText(), passwordField.getText(), active));

            Node node = (Node) event.getSource();
            Stage stage = (Stage) node.getScene().getWindow();
            stage.close();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            AnchorPane pane = (AnchorPane) loader.load();
            Scene scene = new Scene(pane);
            Stage stage1 = new Stage();
            stage1.setScene(scene);
            stage1.setTitle("Login");
            stage1.show();
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Va rog completati cimpurile");
            alert.show();
        }
    }

}
