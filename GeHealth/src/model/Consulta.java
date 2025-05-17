package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;

public class Consulta {

    private int id;
    private Paciente paciente;
    private double valor;
    private String dataConsulta;
    private String observacao;
    private ProfissionalSaude profissionalSaude;
    private Especialidade especialidade;

    public Consulta() {
    }

    public Consulta(int id, Paciente paciente, double valor, String dataConsulta, String observacao, ProfissionalSaude profissionalSaude, Especialidade especialidade) {
        this.id = id;
        this.paciente = paciente;
        this.valor = valor;
        this.dataConsulta = dataConsulta;
        this.observacao = observacao;
        this.profissionalSaude = profissionalSaude;
        this.especialidade = especialidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public double getValor() {
        return valor;
    }

    public String getDataConsulta() {
        return dataConsulta;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public ProfissionalSaude getProfissionalSaude() {
        return profissionalSaude;
    }

    public void setProfissionalSaude(ProfissionalSaude profissionalSaude) {
        this.profissionalSaude = profissionalSaude;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public void setDataConsulta(String dataConsulta) {
            this.dataConsulta = dataConsulta;

    }


}
