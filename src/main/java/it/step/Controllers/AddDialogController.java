package it.step.Controllers;

import it.step.Model.Gender;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class AddDialogController implements Initializable {
    public TextField nameTextField;
    public TextField surnameTextField;
    public RadioButton maleRadioBtn;
    public RadioButton femaleRadioBtn;
    public DatePicker birthdatePicker;
    Gender gender;

    public void onAdd(ActionEvent event) {
        String name = nameTextField.getText();
        String surname = surnameTextField.getText();
        if(maleRadioBtn.isSelected()) gender = Gender.MALE;
        if(femaleRadioBtn.isSelected()) gender = Gender.FEMALE;
        LocalDate birthdate = birthdatePicker.getValue();
        if(!name.isEmpty() && !surname.isEmpty() && gender != null && birthdate != null){
            Node node = (Node)event.getSource();
            Stage stage =(Stage) node.getScene().getWindow();
            stage.close();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onMaleSelect(ActionEvent event) {
        this.maleRadioBtn.setSelected(false);
    }
    public void onFemaleSelect(ActionEvent event) {
        this.femaleRadioBtn.setSelected(false);
    }
}
