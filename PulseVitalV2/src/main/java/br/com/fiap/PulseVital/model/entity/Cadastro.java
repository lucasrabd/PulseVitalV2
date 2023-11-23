package br.com.fiap.PulseVital.model.entity;

import jakarta.validation.constraints.NotBlank;

public class Cadastro {
	
	private long id;
	@NotBlank
    private String nome;
    @NotBlank
    private String email;
    @NotBlank
    private String cep;
    @NotBlank
    private String senha;
    
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
