package ma.enset.gestionconsultations.controllers;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.DatePicker;

import ma.enset.gestionconsultations.entities.Patient;

public class PatientController {

    @FXML
    private SimpleObjectProperty<TextField> nomField = new SimpleObjectProperty<>(this, "nomField");
    @FXML
    private TextField prenomField;
    @FXML
    private TextField telField;
    @FXML
    private SimpleObjectProperty<TextField> emailField = new SimpleObjectProperty<>(this, "emailField");
    @FXML
    private SimpleObjectProperty<TextField> addressField = new SimpleObjectProperty<>(this, "addressField");
    @FXML
    private DatePicker dateOfBirthField;
    @FXML
    private ComboBox<String> genderComboBox;
    @FXML
    private TableView<Patient> patientTable;
    @FXML
    private TableColumn<Patient, String> colNom, colPrenom, colTel, colEmail, colAddress, colDateOfBirth, colGender;

    private ObservableList<Patient> patientList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Set the column properties
        colNom.setCellValueFactory(cellData -> cellData.getValue().nomProperty());
        colPrenom.setCellValueFactory(cellData -> cellData.getValue().prenomProperty());
        colTel.setCellValueFactory(cellData -> cellData.getValue().telProperty());
        colEmail.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        colAddress.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        colDateOfBirth.setCellValueFactory(cellData -> cellData.getValue().dateOfBirthProperty());
        colGender.setCellValueFactory(cellData -> cellData.getValue().genderProperty());

        // Set the table's data
        patientTable.setItems(patientList);

        // Initialize ComboBox with gender options
        genderComboBox.setItems(FXCollections.observableArrayList("Male", "Female"));
    }

    @FXML
    private void onAjouterPatient() {
        // Get the entered values
        String nom = nomField.get().getText();
        String prenom = prenomField.getText();
        String tel = telField.getText();
        String email = emailField.get().getText();
        String address = addressField.get().getText();
        String gender = genderComboBox.getValue();
        String dateOfBirth = dateOfBirthField.getValue() != null ? dateOfBirthField.getValue().toString() : "";

        // Create a new Patient object
        Patient newPatient = new Patient(nom, prenom, tel, email, address, dateOfBirth, gender);

        // Add the new patient to the list
        patientList.add(newPatient);

        // Optionally, clear the form fields
        clearForm();
    }

    private void clearForm() {
        nomField.get().clear();
        prenomField.clear();
        telField.clear();
        emailField.get().clear();
        addressField.get().clear();
        genderComboBox.getSelectionModel().clearSelection();
        dateOfBirthField.setValue(null);
    }
}
