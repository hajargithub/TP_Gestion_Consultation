package ma.enset.gestionconsultations.dao;

import ma.enset.gestionconsultations.entities.Consultation;
import ma.enset.gestionconsultations.entities.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ConsultationDao implements IConsultationDao {

    private final Connection connection;

    public ConsultationDao() {
        this.connection = DbConnexionSingleton.getConnection();
    }

    @Override
    public List<Consultation> findALL() {
        List<Consultation> consultations = new ArrayList<>();
        String sql = "SELECT c.*, p.* FROM consultation c JOIN patient p ON c.patient_id = p.id_patient";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Patient patient = new Patient();
                patient.setId_patient(rs.getLong("id_patient"));
                patient.setFirstName(rs.getString("first_name"));
                patient.setLastName(rs.getString("last_name"));
                patient.setDateOfBirth(rs.getDate("date_of_birth"));
                patient.setGender(rs.getString("gender"));
                patient.setPhoneNumber(rs.getString("phone_number"));
                patient.setEmail(rs.getString("email"));
                patient.setAddress(rs.getString("address"));

                Consultation consultation = new Consultation();
                consultation.setId_consultation(rs.getLong("id_consultation"));
                consultation.setDate_consultation(rs.getDate("date_consultation"));
                consultation.setDescription(rs.getString("description"));
                consultation.setPatient(patient);

                consultations.add(consultation);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return consultations;
    }

    @Override
    public Consultation findById(Long id) throws SQLException {
        String sql = "SELECT c.*, p.* FROM consultation c JOIN patient p ON c.patient_id = p.id_patient WHERE id_consultation = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Patient patient = new Patient();
                patient.setId_patient(rs.getLong("id_patient"));
                patient.setFirstName(rs.getString("first_name"));
                patient.setLastName(rs.getString("last_name"));
                patient.setDateOfBirth(rs.getDate("date_of_birth"));
                patient.setGender(rs.getString("gender"));
                patient.setPhoneNumber(rs.getString("phone_number"));
                patient.setEmail(rs.getString("email"));
                patient.setAddress(rs.getString("address"));

                Consultation consultation = new Consultation();
                consultation.setId_consultation(rs.getLong("id_consultation"));
                consultation.setDate_consultation(rs.getDate("date_consultation"));
                consultation.setDescription(rs.getString("description"));
                consultation.setPatient(patient);

                return consultation;
            }
        }

        return null;
    }

    @Override
    public void add(Consultation consultation) {
        String sql = "INSERT INTO consultation (date_consultation, description, patient_id) VALUES (?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDate(1, consultation.getDate_consultation());
            ps.setString(2, consultation.getDescription());
            ps.setLong(3, consultation.getPatient().getId_patient());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(Long id) {
        String sql = "DELETE FROM consultation WHERE id_consultation = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Consultation consultation) throws SQLException {
        String sql = "UPDATE consultation SET date_consultation = ?, description = ?, patient_id = ? WHERE id_consultation = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setDate(1, consultation.getDate_consultation());
            ps.setString(2, consultation.getDescription());
            ps.setLong(3, consultation.getPatient().getId_patient());
            ps.setLong(4, consultation.getId_consultation());
            ps.executeUpdate();
        }
    }
}
