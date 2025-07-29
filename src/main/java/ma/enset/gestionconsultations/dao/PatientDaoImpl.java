package ma.enset.gestionconsultations.dao;

import ma.enset.gestionconsultations.entities.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDaoImpl implements IPatientDao {

    @Override
    public List<Patient> findALL() {
        Connection conn = DbConnexionSingleton.getConnection();
        List<Patient> patients = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM patient;");
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Patient p = new Patient();
                p.setId_patient(rs.getLong("id_patient"));
                p.setFirstName(rs.getString("first_name"));
                p.setLastName(rs.getString("last_name"));
                p.setDateOfBirth(rs.getDate("date_of_birth"));
                p.setGender(rs.getString("gender"));
                p.setPhoneNumber(rs.getString("phone_number"));
                p.setEmail(rs.getString("email"));
                p.setAddress(rs.getString("address"));
                patients.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return patients;
    }

    @Override
    public Patient findById(Long id) {
        Connection conn = DbConnexionSingleton.getConnection();
        Patient p = null;
        try {
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM patient WHERE id_patient = ?;");
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                p = new Patient();
                p.setId_patient(rs.getLong("id_patient"));
                p.setFirstName(rs.getString("first_name"));
                p.setLastName(rs.getString("last_name"));
                p.setDateOfBirth(rs.getDate("date_of_birth"));
                p.setGender(rs.getString("gender"));
                p.setPhoneNumber(rs.getString("phone_number"));
                p.setEmail(rs.getString("email"));
                p.setAddress(rs.getString("address"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return p;
    }

    @Override
    public void add(Patient patient) {
        Connection conn = DbConnexionSingleton.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(
                    "INSERT INTO patient (first_name, last_name, date_of_birth, gender, phone_number, email, address) VALUES (?, ?, ?, ?, ?, ?, ?)"
            );
            pstmt.setString(1, patient.getFirstName());
            pstmt.setString(2, patient.getLastName());
            pstmt.setDate(3, patient.getDateOfBirth());
            pstmt.setString(4, patient.getGender());
            pstmt.setString(5, patient.getPhoneNumber());
            pstmt.setString(6, patient.getEmail());
            pstmt.setString(7, patient.getAddress());
            pstmt.executeUpdate();
            System.out.println("Nouveau patient inséré avec succès !");
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void deleteById(Long id) {
        Connection conn = DbConnexionSingleton.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM patient WHERE id_patient = ?;");
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
            System.out.println("Patient supprimé avec succès !");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Patient patient) {
        Connection conn = DbConnexionSingleton.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(
                    "UPDATE patient SET first_name = ?, last_name = ?, date_of_birth = ?, gender = ?, phone_number = ?, email = ?, address = ? WHERE id_patient = ?;"
            );
            pstmt.setString(1, patient.getFirstName());
            pstmt.setString(2, patient.getLastName());
            pstmt.setDate(3, patient.getDateOfBirth());
            pstmt.setString(4, patient.getGender());
            pstmt.setString(5, patient.getPhoneNumber());
            pstmt.setString(6, patient.getEmail());
            pstmt.setString(7, patient.getAddress());
            pstmt.setLong(8, patient.getId_patient());
            pstmt.executeUpdate();
            System.out.println("Patient mis à jour avec succès !");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
