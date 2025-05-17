package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class ProfissionalSaude {

    private int id;
    private String nome;
    private String cpf;
    private String dataNascimento;
    private String registroProfissional;
    private String tipo;
    private Especialidade especialidade;
    private String email;
    private String telefone;

    public ProfissionalSaude() {
    }

    public ProfissionalSaude(int id, String nome, String cpf, String dataNascimento, String registroProfissional, String tipo, Especialidade especialidade, String email, String telefone) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.registroProfissional = registroProfissional;
        this.tipo = tipo;
        this.especialidade = especialidade;
        this.email = email;
        this.telefone = telefone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getRegistroProfissional() {
        return registroProfissional;
    }

    public void setRegistroProfissional(String registroProfissional) {
        this.registroProfissional = registroProfissional;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return this.nome; // Mostra o nome no JComboBox
    }
}
