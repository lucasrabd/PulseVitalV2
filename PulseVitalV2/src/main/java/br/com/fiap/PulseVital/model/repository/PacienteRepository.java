package br.com.fiap.PulseVital.model.repository;

import br.com.fiap.PulseVital.model.entity.Paciente;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PacienteRepository extends Repository {

    public List<Paciente> getAllPacientes() {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM PACIENTE_MK1";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Paciente paciente = new Paciente();
                paciente.setId(rs.getLong("id"));
                paciente.setNome(rs.getString("nome"));
                paciente.setCpf(rs.getString("cpf"));
                paciente.setAlergia(rs.getString("alergia"));
                paciente.setTipoSanguineo(rs.getString("tipo_sanguineo"));
                paciente.setRemedioFrequente(rs.getString("remedio_frequente"));
                paciente.setDataNascimento(rs.getDate("dt_nascimento"));
                paciente.setSexo(rs.getString("sexo"));
                paciente.setPeso(rs.getDouble("peso"));
                paciente.setTelefoneEmergencia(rs.getString("tel_emergencia"));
                // Defina outros atributos conforme sua estrutura de banco de dados
                pacientes.add(paciente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pacientes;
    }

    public Paciente getPacienteById(Long id) {
        Paciente paciente = null;
        String sql = "SELECT * FROM PACIENTE_MK1 WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    paciente = new Paciente();
                    paciente.setId(rs.getLong("id"));
                    paciente.setNome(rs.getString("nome"));
                    paciente.setCpf(rs.getString("cpf"));
                    paciente.setAlergia(rs.getString("alergia"));
                    paciente.setTipoSanguineo(rs.getString("tipo_sanguineo"));
                    paciente.setRemedioFrequente(rs.getString("remedio_frequente"));
                    paciente.setDataNascimento(rs.getDate("dt_nascimento"));
                    paciente.setSexo(rs.getString("sexo"));
                    paciente.setPeso(rs.getDouble("peso"));
                    paciente.setTelefoneEmergencia(rs.getString("tel_emergencia"));
                    // Defina outros atributos conforme sua estrutura de banco de dados
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paciente;
    }

    public Paciente getPacienteByCpf(String cpf) {
        Paciente paciente = null;
        String sql = "SELECT * FROM PACIENTE_MK1 WHERE cpf = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, cpf);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    paciente = new Paciente();
                    paciente.setId(rs.getLong("id"));
                    paciente.setNome(rs.getString("nome"));
                    paciente.setCpf(rs.getString("cpf"));
                    paciente.setAlergia(rs.getString("alergia"));
                    paciente.setTipoSanguineo(rs.getString("tipo_sanguineo"));
                    paciente.setRemedioFrequente(rs.getString("remedio_frequente"));
                    paciente.setDataNascimento(rs.getDate("dt_nascimento"));
                    paciente.setSexo(rs.getString("sexo"));
                    paciente.setPeso(rs.getDouble("peso"));
                    paciente.setTelefoneEmergencia(rs.getString("tel_emergencia"));
                    // Defina outros atributos conforme sua estrutura de banco de dados
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return paciente;
    }

    public Paciente save(Paciente paciente) {
        String sql = "INSERT INTO PACIENTE_MK1 (nome, cpf, alergia, tipo_sanguineo, remedio_frequente, dt_nascimento, sexo, doencas, peso, tel_emergencia) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, paciente.getNome());
            ps.setString(2, paciente.getCpf());
            ps.setString(3, paciente.getAlergia());
            ps.setString(4, paciente.getTipoSanguineo());
            ps.setString(5, paciente.getRemedioFrequente());
            ps.setDate(6, (Date) paciente.getDataNascimento());
            ps.setString(7, paciente.getSexo());
            ps.setDouble(9, paciente.getPeso());
            ps.setString(10, paciente.getTelefoneEmergencia());
            // Defina outros atributos conforme sua estrutura de banco de dados

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    paciente.setId(generatedKeys.getLong(1));
                    return paciente;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deletePaciente(Long id) {
        String sql = "DELETE FROM PACIENTE_MK1 WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setLong(1, id);
            int deletedRows = ps.executeUpdate();
            return deletedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
