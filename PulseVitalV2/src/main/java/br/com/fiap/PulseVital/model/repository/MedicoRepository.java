package br.com.fiap.PulseVital.model.repository;


	import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.fiap.PulseVital.model.entity.Medico;
	
public class MedicoRepository extends Repository {
	public static ArrayList<Medico> findAll() {
        ArrayList<Medico> medicos = new ArrayList<Medico>();

        String sql = "select * from gs_medico";
        try (PreparedStatement ps = getConnection().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Medico medico = new Medico();
                medico.setId(rs.getLong("id"));
                medico.setCrm(rs.getString("crm"));
                medico.setNome(rs.getString("nome"));
                medico.setEspecialidade(rs.getString("especialidade"));
                medico.setEndereco(rs.getString("endereco"));
                medico.setTelefone(rs.getString("telefone"));
                medico.setEmail(rs.getString("email"));
                medicos.add(medico);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        } finally {
            closeConnection();
        }
        return medicos;
    }



    
	
	public static Medico save(Medico Medico) {
		String sql = "insert into gs_medico"
				+ "(id, crm, nome, especialidade, endereco, telefone, email)"
				+ " values(null, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = getConnection().prepareStatement(sql);
			ps.setString(1, Medico.getCrm());
			ps.setString(2, Medico.getNome());
			ps.setString(3, Medico.getEspecialidade());
			ps.setString(4, Medico.getEndereco());
			ps.setString(5, Medico.getTelefone());
			ps.setString(6, Medico.getEmail());						
			if (ps.executeUpdate() > 0) {
				return Medico;
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
			String sql = "delete from gs_medico where id = ?";
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
		
		public static Medico update(Medico medico) {
		    String sql = "update gs_medico "
		            + "set crm=?, nome=?, especialidade=?, endereco=?, telefone=?, email=? "
		            + "where id=?";

		    try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
		        ps.setString(1, medico.getCrm());
		        ps.setString(2, medico.getNome());
		        ps.setString(3, medico.getEspecialidade());
		        ps.setString(4, medico.getEndereco());
		        ps.setString(5, medico.getTelefone());
		        ps.setString(6, medico.getEmail());
		        ps.setLong(7, medico.getId());

		        if (ps.executeUpdate() > 0) {
		            return medico;
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


		
		public static Medico findById(Long id) {
			String sql = "select * from gs_medico where id=?";
			Medico medico = new Medico();
			try {
				PreparedStatement ps = getConnection().prepareStatement(sql);
				ps.setLong(1, id);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {					               
	                medico.setId(rs.getLong("id"));
	                medico.setCrm(rs.getString("crm"));
	                medico.setNome(rs.getString("nome"));
	                medico.setEspecialidade(rs.getString("especialidade"));
	                medico.setEndereco(rs.getString("endereco"));
	                medico.setTelefone(rs.getString("telefone"));
	                medico.setEmail(rs.getString("email"));
				} else {
					return null;
				}
			} catch (SQLException e) {
				System.out.println("Erro ao listar: " + e.getMessage());
			} finally {
				closeConnection();
			}
			return medico;
		}
		

		public static List<Medico> findByNome(String nome) {
	        List<Medico> medicosEncontrados = new ArrayList<>();
	        String sql = "SELECT * FROM gs_medico WHERE nome LIKE ?";
	        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
	            ps.setString(1, "%" + nome + "%");
	            try (ResultSet rs = ps.executeQuery()) {
	                while (rs.next()) {
	                	Medico medico = new Medico();
	                    medico.setId(rs.getLong("id"));
	                    medico.setCrm(rs.getString("crm"));
	                    medico.setNome(rs.getString("nome"));
	                    medico.setEspecialidade(rs.getString("especialidade"));
	                    medico.setEndereco(rs.getString("endereco"));
	                    medico.setTelefone(rs.getString("telefone"));
	                    medico.setEmail(rs.getString("email"));
	                    medicosEncontrados.add(medico);
	                }
	            }
	        } catch (SQLException e) {
	            System.out.println("Erro ao buscar por nome: " + e.getMessage());
	        } finally {
	            closeConnection();
	        }
	        return medicosEncontrados;
	    }
		
		public static List<Medico> findByCrm(String crm) {
		    List<Medico> medicosEncontrados = new ArrayList<>();
		    String sql = "SELECT * FROM gs_medico WHERE crm = ?";
		    try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
		        ps.setString(1, crm);
		        try (ResultSet rs = ps.executeQuery()) {
		            while (rs.next()) {
		            	Medico medico = new Medico();
	                    medico.setId(rs.getLong("id"));
	                    medico.setCrm(rs.getString("crm"));
	                    medico.setNome(rs.getString("nome"));
	                    medico.setEspecialidade(rs.getString("especialidade"));
	                    medico.setEndereco(rs.getString("endereco"));
	                    medico.setTelefone(rs.getString("telefone"));
	                    medico.setEmail(rs.getString("email"));
	                    medicosEncontrados.add(medico);
		            }
		        }
		    } catch (SQLException e) {
		        System.out.println("Erro ao buscar por sexo: " + e.getMessage());
		    } finally {
		        closeConnection();
		    }
		    return medicosEncontrados;
		}
		}
	
	

