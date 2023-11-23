package br.com.fiap.PulseVital.model.repository;


	import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.PulseVital.model.entity.Hospital;

	
public class HospitalRepository extends Repository {
	    public static ArrayList<Hospital> findAll() {
	        ArrayList<Hospital> hospitais = new ArrayList<>();

	        String sql = "select * from gs_hospital";
	        try (PreparedStatement ps = getConnection().prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {

	            while (rs.next()) {
	                Hospital hospital = new Hospital();
	                hospital.setId(rs.getLong("id"));
	                hospital.setNome(rs.getString("nome"));
	                hospital.setTelefone(rs.getString("telefone"));
	                hospital.setEndereco(rs.getString("endereco"));
	                hospital.setEmail(rs.getString("email"));
	                hospitais.add(hospital);
	            }

	        } catch (SQLException e) {
	            System.out.println("Erro ao listar: " + e.getMessage());
	        } finally {
	            closeConnection();
	        }
	        return hospitais;
	    }
	
    
	    public static Hospital save(Hospital hospital) {
	        String sql = "insert into gs_hospital"
	                + "(nome, telefone, endereco, email)"
	                + " values(?, ?, ?, ?)";
	        try {
	            PreparedStatement ps = getConnection().prepareStatement(sql);
	            ps.setString(1, hospital.getNome());
	            ps.setString(2, hospital.getTelefone());
	            ps.setString(3, hospital.getEndereco());
	            ps.setString(4, hospital.getEmail());

	            if (ps.executeUpdate() > 0) {
	                return hospital;
	            } else {
	                return null;
	            }
	        } catch (SQLException e) {
	            // Logar ou lançar uma exceção dependendo do contexto
	            System.out.println("Erro ao salvar: " + e.getMessage());
	        } finally {
	            closeConnection();
	        }
	        return null; // Pode querer lançar uma exceção aqui dependendo do contexto
	    }
	    
	
	        public static boolean delete(Long id) {
	            String sql = "delete from gs_hospital where id = ?";
	            try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
	                ps.setLong(1, id);
	                return ps.executeUpdate() > 0;
	            } catch (SQLException e) {
	                System.out.println("Erro ao excluir: " + e.getMessage());
	            } finally {
	                closeConnection();
	            }
	            return false;
	        }
		
		public static Hospital update(Hospital hospital) {
	        String sql = "update gs_hospital "
	                + "set nome=?, telefone=?, endereco=?, email=? "
	                + "where id=?";

	        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
	            ps.setString(1, hospital.getNome());
	            ps.setString(2, hospital.getTelefone());
	            ps.setString(3, hospital.getEndereco());
	            ps.setString(4, hospital.getEmail());
	            ps.setLong(5, hospital.getId());

	            return ps.executeUpdate() > 0 ? hospital : null;
	        } catch (SQLException e) {
	            System.out.println("Erro ao atualizar: " + e.getMessage());
	        } finally {
	            closeConnection();
	        }

	        return null;
	    }



		
		public static Hospital findById(Long id) {
			String sql = "select * from gs_Hospital where id=?";
			Hospital hospital = new Hospital();
			try {
				PreparedStatement ps = getConnection().prepareStatement(sql);
				ps.setLong(1, id);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {					                             
	                hospital.setId(rs.getLong("id"));
	                hospital.setNome(rs.getString("nome"));
	                hospital.setTelefone(rs.getString("telefone"));
	                hospital.setEndereco(rs.getString("endereco"));
	                hospital.setEmail(rs.getString("email"));
				} else {
					return null;
				}
			} catch (SQLException e) {
				System.out.println("Erro ao listar: " + e.getMessage());
			} finally {
				closeConnection();
			}
			return hospital;
		}
		

		public static List<Hospital> findByNome(String nome) {
	        List<Hospital> hospitalEncontrados = new ArrayList<>();
	        String sql = "SELECT * FROM gs_hospital WHERE nome LIKE ?";
	        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
	            ps.setString(1, "%" + nome + "%");
	            try (ResultSet rs = ps.executeQuery()) {
	                while (rs.next()) {
	                    Hospital hospital = new Hospital();
	                    hospital.setId(rs.getLong("id"));
	                    hospital.setNome(rs.getString("nome"));
	                    hospital.setTelefone(rs.getString("telefone"));
	                    hospital.setEndereco(rs.getString("endereco"));
	                    hospital.setEmail(rs.getString("email"));
	                    hospitalEncontrados.add(hospital);
	                }
	            }
	        } catch (SQLException e) {
	            System.out.println("Erro ao buscar por nome: " + e.getMessage());
	        } finally {
	            closeConnection();
	        }
	        return hospitalEncontrados;
	    }
	}