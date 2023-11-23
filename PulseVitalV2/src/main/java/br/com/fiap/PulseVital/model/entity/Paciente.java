package br.com.fiap.PulseVital.model.entity;

import java.util.Date;

public class Paciente {
    private long id;
    private String nome;
    private String cpf;
    private String alergia;
    private String tipoSanguineo;
    private String remedioFrequente;
    private Date dataNascimento;
    private String sexo;
    private double peso;
    private String telefoneEmergencia;

    public Paciente() {
    }

    public Paciente(long id, String nome, String cpf, String alergia, String tipoSanguineo, String remedioFrequente,
                    Date dataNascimento, String sexo, double peso, String telefoneEmergencia) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.alergia = alergia;
        this.tipoSanguineo = tipoSanguineo;
        this.remedioFrequente = remedioFrequente;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.peso = peso;
        this.telefoneEmergencia = telefoneEmergencia;
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getAlergia() {
        return alergia;
    }

    public void setAlergia(String alergia) {
        this.alergia = alergia;
    }

    public String getTipoSanguineo() {
        return tipoSanguineo;
    }

    public void setTipoSanguineo(String tipoSanguineo) {
        this.tipoSanguineo = tipoSanguineo;
    }

    public String getRemedioFrequente() {
        return remedioFrequente;
    }

    public void setRemedioFrequente(String remedioFrequente) {
        this.remedioFrequente = remedioFrequente;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getTelefoneEmergencia() {
        return telefoneEmergencia;
    }

    public void setTelefoneEmergencia(String telefoneEmergencia) {
        this.telefoneEmergencia = telefoneEmergencia;
    }
}
