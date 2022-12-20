package it.step.Controllers;

import it.step.Model.Gender;
import it.step.Model.Person;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.LocalDate;


public class EditDialogController {
    private Person personCopy;

    public TextField nameTextField;
    public TextField surnameTextField;
    public RadioButton maleRadioBtn;
    public RadioButton femaleRadioBtn;
    public DatePicker birthdatePicker;
    Gender gender;


    public void setPerson(Person person) {
        personCopy = person;
       nameTextField.setText(person.getName());
       surnameTextField.setText(person.getSurname());
       if(person.getGender() == Gender.MALE) {
           maleRadioBtn.setSelected(true);
       }else{
           femaleRadioBtn.setSelected(true);
       }
       birthdatePicker.setValue(person.getBirthdate());
    }

    public void onEdit(ActionEvent event) {

        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }
}
