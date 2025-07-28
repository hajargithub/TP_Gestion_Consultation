package ma.enset.gestionconsultations.dao;

import ma.enset.gestionconsultations.entities.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDaoImpl implements IPatientDao{

    @Override
    public List<Patient> findALL() {
        Connection conn = DbConnexionSingleton.getConnection();
        List<Patient> patients = new ArrayList<Patient>();
        try {
            PreparedStatement pstmt=conn.prepareStatement("SELECT * From patients;");
            ResultSet rs=pstmt.executeQuery();
            while (rs.next()) {
                Patient p=new Patient();
                p.setId(rs.getInt("id"));
                p.setFirstName(rs.getString("first_name"));
                p.setLastName(rs.getString("last_name"));
                p.setEmail(rs.getString("email"));
                p.setAddress(rs.getString("address"));
                patients.add(p);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return List.of( );
    }

    @Override
    public Patient findById(Long id) {
        Connection conn = DbConnexionSingleton.getConnection();
        Patient p=new Patient();
        try {
            PreparedStatement pstmt=conn.prepareStatement("SELECT * From patients where id=?;");
            pstmt.setLong(1, id);
            ResultSet rs=pstmt.executeQuery();
            if (rs.next()) {
                p.setId(rs.getLong("id"));
                p.setFirstName(rs.getString("first_name"));
                p.setLastName(rs.getString("last_name"));
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
        Patient p=new Patient();
        try {
            PreparedStatement pstmt=conn.prepareStatement("INSERT INTO patients ( first_name, last_name, date_of_birth, gender, phone_number, email, address) " +
                    "VALUES ( ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1,patient.getFirstName());
            pstmt.setString(2, patient.getLastName());
            pstmt.setDate(3,  patient.getDateOfBirth());
            pstmt.setString(4, patient.getGender());
            pstmt.setString(5, patient.getPhoneNumber());
            pstmt.setString(6, patient.getEmail());
            pstmt.setString(7, patient.getAddress());
            pstmt.executeUpdate();
            pstmt.executeUpdate();
            System.out.println("Nouveau patient inséré avec succès !");

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void deleteById(Long id) {

    }


    @Override
    public void update(Patient patient) {

    }
}
