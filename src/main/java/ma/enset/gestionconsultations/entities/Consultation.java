package ma.enset.gestionconsultations.entities;

import java.sql.Date;

public class Consultation {

    private Long id_consultation;
    private java.sql.Date date_consultation;
    private String description;
    private Patient patient;

    public Consultation() {
    }

    public Consultation( Date date, String description, Patient patient) {
        this.date_consultation = date;
        this.description = description;
        this.patient = patient;
    }

    public Long getId_consultation() {
        return id_consultation;
    }

    public void setId_consultation(Long id_consultation) {
        this.id_consultation = id_consultation;
    }

    public Date getDate_consultation() {
        return date_consultation;
    }

    public void setDate_consultation(Date date) {
        this.date_consultation = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }


}
