package br.com.fiap.PulseVital.model.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.PulseVital.model.entity.Cadastro;

public class CadastroRepository extends Repository {

    public static ArrayList<Cadastro> findAll() {
        ArrayList<Cadastro> cadastros = new ArrayList<Cadastro>();

        String sql = "select * from gs_cadastro";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cadastro cadastro = new Cadastro();
                cadastro.setId(rs.getLong("id"));
                cadastro.setNome(rs.getString("nome"));
                cadastro.setEmail(rs.getString("email"));
                cadastro.setCep(rs.getString("cep"));
                cadastro.setSenha(rs.getString("senha"));
                cadastros.add(cadastro);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return cadastros;
    }

    public static Cadastro save(Cadastro cadastro) {
        String sql = "insert into gs_cadastro"
                + "(id, nome, email, cep, senha)"
                + " values(null, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, cadastro.getNome());
            ps.setString(2, cadastro.getEmail());
            ps.setString(3, cadastro.getCep());
            ps.setString(4, cadastro.getSenha());
            if (ps.executeUpdate() > 0) {
                return cadastro;
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
        String sql = "delete from gs_cadastro where id = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao excluir: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return false;
    }

    public static Cadastro update(Cadastro cadastro) {
        String sql = "update gs_cadastro "
                + "set nome=?, email=?, cep=?, senha=? "
                + "where id=?";

        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, cadastro.getNome());
            ps.setString(2, cadastro.getEmail());
            ps.setString(3, cadastro.getCep());
            ps.setString(4, cadastro.getSenha());
            ps.setLong(5, cadastro.getId());

            return ps.executeUpdate() > 0 ? cadastro : null;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        } finally {
            closeConnection();
        }

        return null;
    }

    public static Cadastro findById(Long id) {
        String sql = "select * from gs_cadastro where id=?";
        Cadastro cadastro = new Cadastro();
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cadastro.setId(rs.getLong("id"));
                cadastro.setNome(rs.getString("nome"));
                cadastro.setEmail(rs.getString("email"));
                cadastro.setCep(rs.getString("cep"));
                cadastro.setSenha(rs.getString("senha"));
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return cadastro;
    }

    public static List<Cadastro> findByNome(String nome) {
        List<Cadastro> cadastrosEncontrados = new ArrayList<>();
        String sql = "SELECT * FROM gs_cadastro WHERE nome LIKE ?";
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, "%" + nome + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Cadastro cadastro = new Cadastro();
                    cadastro.setId(rs.getLong("id"));
                    cadastro.setNome(rs.getString("nome"));
                    cadastro.setEmail(rs.getString("email"));
                    cadastro.setCep(rs.getString("cep"));
                    cadastro.setSenha(rs.getString("senha"));
                    cadastrosEncontrados.add(cadastro);
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao buscar por nome: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return cadastrosEncontrados;
    }

public static Cadastro findByEmail(String email) {
    String sql = "SELECT * FROM gs_cadastro WHERE email=?";
    Cadastro cadastroEncontrado = null;

    try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
        ps.setString(1, email);

        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                cadastroEncontrado = new Cadastro();
                cadastroEncontrado.setId(rs.getLong("id"));
                cadastroEncontrado.setNome(rs.getString("nome"));
                cadastroEncontrado.setEmail(rs.getString("email"));
                cadastroEncontrado.setCep(rs.getString("cep"));
                cadastroEncontrado.setSenha(rs.getString("senha"));
                // Adicione outros campos conforme necessário
            }
        }
    } catch (SQLException e) {
        System.out.println("Erro ao buscar por email: " + e.getMessage());
    } finally {
        closeConnection();
    }

    return cadastroEncontrado;
}

}
