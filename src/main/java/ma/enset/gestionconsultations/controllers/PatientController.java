package ma.enset.gestionconsultations.controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import ma.enset.gestionconsultations.dao.IPatientDao;
import ma.enset.gestionconsultations.dao.PatientDaoImpl;
import ma.enset.gestionconsultations.entities.Patient;

import java.io.IOException;
import java.sql.Date;

public class PatientController {

    @FXML private TextField nomField;
    @FXML private TextField prenomField;
    @FXML private TextField telField;
    @FXML private TextField emailField;
    @FXML private TextField addressField;
    @FXML private DatePicker dateOfBirthField;
    @FXML private ComboBox<String> genderComboBox;

    @FXML private TableView<Patient> patientTable;
    @FXML private TableColumn<Patient, String> colNom;
    @FXML private TableColumn<Patient, String> colPrenom;
    @FXML private TableColumn<Patient, String> colTel;
    @FXML private TableColumn<Patient, String> colEmail;
    @FXML private TableColumn<Patient, String> colAddress;
    @FXML private TableColumn<Patient, String> colDateOfBirth;
    @FXML private TableColumn<Patient, String> colGender;

    private final ObservableList<Patient> patientList = FXCollections.observableArrayList();
    private final IPatientDao patientDao = new PatientDaoImpl();

    @FXML
    public void initialize() {
        colNom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));
        colPrenom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));
        colTel.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
        colEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
        colAddress.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
        colDateOfBirth.setCellValueFactory(cellData -> new SimpleStringProperty(
                cellData.getValue().getDateOfBirth() != null ? cellData.getValue().getDateOfBirth().toString() : ""
        ));
        colGender.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGender()));

        genderComboBox.setItems(FXCollections.observableArrayList("Male", "Female"));

        // 🟢 Charge les patients depuis la base
        patientList.addAll(patientDao.findALL());
        patientTable.setItems(patientList);
    }
//    @FXML
//    public void initialize() {
//        colNom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName()));
//        colPrenom.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLastName()));
//        colTel.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPhoneNumber()));
//        colEmail.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getEmail()));
//        colAddress.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getAddress()));
//        colDateOfBirth.setCellValueFactory(cellData -> new SimpleStringProperty(
//                cellData.getValue().getDateOfBirth() != null ? cellData.getValue().getDateOfBirth().toString() : ""
//        ));
//        colGender.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getGender()));
//
//        patientTable.setItems(patientList);
//
//        genderComboBox.setItems(FXCollections.observableArrayList("Male", "Female"));
//    }

    @FXML
    private void onAjouterPatient() {
        // Validation rapide
        if (nomField.getText().isEmpty() || prenomField.getText().isEmpty() || dateOfBirthField.getValue() == null) {
            showAlert("Champs obligatoires manquants", "Nom, prénom et date de naissance sont requis.");
            return;
        }

        String nom = nomField.getText();
        String prenom = prenomField.getText();
        String tel = telField.getText();
        String email = emailField.getText();
        String address = addressField.getText();
        String gender = genderComboBox.getValue();
        Date dateOfBirth = Date.valueOf(dateOfBirthField.getValue());
        Patient newPatient = new Patient(nom, prenom, dateOfBirth, gender, tel, email, address);

        // 🟢 Sauvegarde en base
        patientDao.add(newPatient);

        // 🟢 Recharge la liste
        patientList.setAll(patientDao.findALL());

        clearForm();
    }

    private void clearForm() {
        nomField.clear();
        prenomField.clear();
        telField.clear();
        emailField.clear();
        addressField.clear();
        genderComboBox.getSelectionModel().clearSelection();
        dateOfBirthField.setValue(null);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

}
