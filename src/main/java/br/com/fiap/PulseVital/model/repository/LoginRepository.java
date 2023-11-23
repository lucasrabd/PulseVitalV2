package br.com.fiap.PulseVital.model.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.PulseVital.model.entity.Login;

public class LoginRepository extends Repository {

    public static ArrayList<Login> findAll() {
        ArrayList<Login> logins = new ArrayList<Login>();

        String sql = "select * from gs_login";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Login login = new Login();
                login.setId(rs.getLong("id"));
                login.setEmail(rs.getString("email"));
                login.setSenha(rs.getString("senha"));
                logins.add(login);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return logins;
    }

    public static Login save(Login login) {
        String sql = "insert into gs_login (id, email, senha) values (null, ?, ?)";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            ps.setString(1, login.getEmail());
            ps.setString(2, login.getSenha());
            if (ps.executeUpdate() > 0) {
                ResultSet generatedKeys = ps.getGeneratedKeys();
                if (generatedKeys.next()) {
                    login.setId(generatedKeys.getLong(1));
                }
                return login;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao salvar: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }

    public static boolean delete(Long id) {
        String sql = "delete from gs_login where id = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setLong(1, id);
            if (ps.executeUpdate() > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;
    }

    public static Login update(Login login) {
        String sql = "update gs_login set email=?, senha=? where id=?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, login.getEmail());
            ps.setString(2, login.getSenha());
            ps.setLong(3, login.getId());
            if (ps.executeUpdate() > 0) {
                return login;
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }

    public static Login findById(Long id) {
        String sql = "select * from gs_login where id=?";
        Login login = new Login();
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                login.setId(rs.getLong("id"));
                login.setEmail(rs.getString("email"));
                login.setSenha(rs.getString("senha"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return login;
    }

    public static List<Login> findByEmail(String email) {
        List<Login> loginsEncontrados = new ArrayList<>();
        String sql = "SELECT * FROM gs_login WHERE email LIKE ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, "%" + email + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Login login = new Login();
                    login.setId(rs.getLong("id"));
                    login.setEmail(rs.getString("email"));
                    login.setSenha(rs.getString("senha"));
                    loginsEncontrados.add(login);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar por email: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return loginsEncontrados;
    }
}

