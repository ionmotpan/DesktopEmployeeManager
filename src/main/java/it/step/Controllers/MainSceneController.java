package it.step.Controllers;

import it.step.DB.DbEmployee;
import it.step.Model.Gender;
import it.step.Model.Person;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.imageio.IIOException;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainSceneController implements Initializable {
    public TableColumn<Person, Integer> idCol;
    public TableColumn<Person, String> nameCol;
    public TableColumn<Person, String> surnameCol;
    public TableColumn<Person, Gender> genderCol;
    public TableColumn<Person, LocalDate> birthdateCol;
    public TableView<Person> table;
    Gender gender;
    public DbEmployee db = new DbEmployee();

    public void onAdd(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/add.fxml"));
        AnchorPane parent = loader.load();
        Scene scene = new Scene(parent);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Add Employee");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();

        AddDialogController dialogController = loader.getController();
        String name = dialogController.nameTextField.getText();
        String surname = dialogController.surnameTextField.getText();
        if(dialogController.maleRadioBtn.isSelected()) gender = Gender.MALE;
        if(dialogController.femaleRadioBtn.isSelected()) gender = Gender.FEMALE;
        LocalDate birthdate = dialogController.birthdatePicker.getValue();
        db.create(new Person(1, name, surname,gender, birthdate));
        table.getItems().add(new Person(1, name, surname,gender, birthdate));
    }

    public void onEdit(ActionEvent event) throws IOException {
        int idx = table.getSelectionModel().getSelectedIndex();
        if(idx != -1){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/edit.fxml"));
            AnchorPane parent = loader.load();
            EditDialogController controller = loader.getController();
            controller.setPerson(table.getItems().get(idx));

            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Edit Employee");
            stage.showAndWait();

            String name = controller.nameTextField.getText();
            if(!name.isEmpty()) {
                table.getItems().get(idx).setName(name);
            }
            String surname = controller.surnameTextField.getText();
            if(!surname.isEmpty()){
                table.getItems().get(idx).setSurname(surname);
            }
            if(controller.maleRadioBtn.isSelected()) gender = Gender.MALE;
            if(controller.femaleRadioBtn.isSelected()) gender = Gender.FEMALE;
            if(gender!= null) {
                table.getItems().get(idx).setGender(gender);
            }
            LocalDate birthdate = controller.birthdatePicker.getValue();
            if(birthdate != null) {
                table.getItems().get(idx).setBirthdate(birthdate);
            }

        }
        table.refresh();
    }

    public void onDelete(ActionEvent event) {
        int idx = table.getSelectionModel().getSelectedIndex();
        if(idx != -1) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText("This is a header: Please Confirm");
            alert.setContentText("Are you sure you want to delete?");
            Optional<ButtonType> result =  alert.showAndWait();
            if(result.isPresent()) {
                if(result.get() == ButtonType.OK) {
                    table.getItems().remove(idx);
                }
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select something");
            alert.setHeaderText("Selection required");
            alert.setContentText("Please select something");
            alert.show();
        }

    }
    public void onClose(ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Person> list = FXCollections.observableArrayList();
        List<Person> people = db.read();
        for(Person p : people) {
            list.add(p);
        }
        table.setItems(list);

        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        birthdateCol.setCellValueFactory(new PropertyValueFactory<>("birthdate"));
    }
}



























