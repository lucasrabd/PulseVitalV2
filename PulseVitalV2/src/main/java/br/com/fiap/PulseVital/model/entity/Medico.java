package br.com.fiap.PulseVital.model.entity;

import java.util.List;

import jakarta.validation.constraints.NotBlank;

public class Medico extends Paciente{
	private long id;
	@NotBlank
	private String crm;
	@NotBlank
	private String nome;
	@NotBlank
	private String especialidade;
	@NotBlank
	private String endereco;
	@NotBlank
	private String telefone;
	@NotBlank
	private String email;
	
	private Hospital hospital;
	
	private List<Paciente> pacientes;
	
	public Medico(long id, @NotBlank String crm, @NotBlank String nome, @NotBlank String especialidade, @NotBlank String endereco, @NotBlank String telefone, @NotBlank
			String email) {
		super();
		this.id = id;
		this.crm = crm;
		this.nome = nome;
		this.especialidade = especialidade;
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;
	}
    
    
    
	public Medico() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(String especialidade) {
		this.especialidade = especialidade;
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
    
    
	public void associarHospital(Hospital hospital) {
        this.setHospital(hospital);
        hospital.adicionarMedico(this);
    }
	public void adicionarPaciente(Paciente paciente) {
        pacientes.add(paciente);
    }



	public Hospital getHospital() {
		return hospital;
	}



	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}
    
}
