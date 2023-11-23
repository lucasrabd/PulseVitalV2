package br.com.fiap.PulseVital.model.entity;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public class Hospital extends Medico{
	private long id;
	@NotBlank
	private String nome;
	@NotBlank
	private String endereco;
	@NotBlank
	private String telefone;
	@NotBlank
	private String email;
	@NotBlank
	private String especialidades;
	
	
	private List<Medico> medicos;
	
	public Hospital() {} 
	
	public Hospital(long id, @NotBlank String nome, @NotBlank String endereco, @NotBlank String telefone, @NotBlank String email, @NotBlank String especialidades) {
		super();
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;
		this.especialidades = especialidades;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(String especialidades) {
		this.especialidades = especialidades;
	}
	
	public void adicionarMedico(Medico medico) {
        medicos.add(medico);
    }
    
    
}
