package ma.enset.gestionconsultations.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import ma.enset.gestionconsultations.dao.IPatientDao;
import ma.enset.gestionconsultations.dao.PatientDaoImpl;
import ma.enset.gestionconsultations.entities.Consultation;
import ma.enset.gestionconsultations.entities.Patient;

import java.sql.Date;

public class ConsultationController {

    @FXML private ComboBox<Patient> patientComboBox;
    @FXML private DatePicker dateConsultationPicker;
    @FXML private TextArea descriptionArea;

    @FXML private TableView<Consultation> consultationTable;
    @FXML private TableColumn<Consultation, String> colPatient;
    @FXML private TableColumn<Consultation, String> colDate;
    @FXML private TableColumn<Consultation, String> colDescription;

    private final ObservableList<Consultation> consultationList = FXCollections.observableArrayList();
    private final ObservableList<Patient> patientList = FXCollections.observableArrayList();
    private final IPatientDao patientDao = new PatientDaoImpl();

    @FXML
    public void initialize() {


        // ✅ Charger les vrais patients
        patientList.setAll(patientDao.findALL());
        patientComboBox.setItems(patientList);

        colPatient.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(
                cellData.getValue().getPatient().getFirstName() + " " + cellData.getValue().getPatient().getLastName()
        ));
        colDate.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(
                cellData.getValue().getDate_consultation().toString()
        ));
        colDescription.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(
                cellData.getValue().getDescription()
        ));

        consultationTable.setItems(consultationList);
    }

    @FXML
    private void onAjouterConsultation() {
        Patient patient = patientComboBox.getValue();
        Date date = dateConsultationPicker.getValue() != null ? Date.valueOf(dateConsultationPicker.getValue()) : null;
        String description = descriptionArea.getText();

        if (patient == null || date == null || description.isEmpty()) {
            showAlert("Veuillez remplir tous les champs.");
            return;
        }
        Consultation consultation = new Consultation(date, description, patient);
        consultationList.add(consultation);

        clearForm();
    }

    private void clearForm() {
        patientComboBox.getSelectionModel().clearSelection();
        dateConsultationPicker.setValue(null);
        descriptionArea.clear();
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Erreur");
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
