/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funcao;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ProfissionalSaude;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.Especialidade;
import view.ListagemEspecialidade;

/**
 *
 * @author Marcos
 */
public class ProfissionalSaudeDao {

    Connection conn;
    PreparedStatement st;
    ResultSet rs;

    public boolean conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/projetogehealth", "root", "DrHq@2707");
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Erro ao conectar: " + ex.getMessage());
            return false;
        }
    }

    public int salvar(ProfissionalSaude ps) {
        int status;
        try {
            st = conn.prepareStatement("INSERT INTO profissional_saude (nome, cpf, data_nascimento, "
                    + "registro_profissional, tipo, especialidade_id, email, telefone) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
            st.setString(1, ps.getNome());
            st.setString(2, ps.getCpf());
            st.setString(3, ps.getDataNascimento());
            st.setString(4, ps.getRegistroProfissional());
            st.setString(5, ps.getTipo());
            st.setInt(6, ps.getEspecialidade().getId());
            st.setString(7, ps.getEmail());
            st.setString(8, ps.getTelefone());
            status = st.executeUpdate();
            return status; // retorna 1 se sucesso
        } catch (SQLException ex) {
            System.out.println("Erro ao salvar: " + ex.getMessage());
            return ex.getErrorCode();
        }
    }

    public DefaultTableModel montarTabela() {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        modelo.addColumn("CPF");
        modelo.addColumn("Data Nascimento");
        modelo.addColumn("Registro Profissional");
        modelo.addColumn("Tipo Registro");
        modelo.addColumn("Especialidade");
        modelo.addColumn("E-mail");
        modelo.addColumn("Telefone");

        String sql = "SELECT P.id, P.nome, P.cpf, P.data_nascimento, P.registro_profissional,"
                    + "P.tipo, E.nome, P.email, P.telefone\n" +
"FROM profissional_saude AS P INNER JOIN especialidade AS E ON E.id = P.especialidade_id;";

        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                Object[] linha = {
                    rs.getInt("P.id"),
                    rs.getString("P.nome"),
                    rs.getString("P.cpf"),
                    rs.getString("P.data_nascimento"),
                    rs.getString("P.registro_profissional"),
                    rs.getString("P.tipo"),
                    rs.getString("E.nome"),
                    rs.getString("P.email"),
                    rs.getString("P.telefone")
                };
                modelo.addRow(linha);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao montar tabela: " + e.getMessage());
        }

        return modelo;
    }

    public ArrayList<ProfissionalSaude> listar() {
        ArrayList<ProfissionalSaude> lista = new ArrayList<>();
        try {
            st = conn.prepareStatement("SELECT P.id, P.nome, P.cpf, P.data_nascimento, P.registro_profissional,"
                    + "P.tipo, E.nome, P.email, P.telefone\n" +
"FROM profissional_saude AS P INNER JOIN especialidade AS E ON E.id = P.especialidade_id;");
            rs = st.executeQuery();

            while (rs.next()) {
                ProfissionalSaude ps = new ProfissionalSaude();
                ps.setId(rs.getInt("id"));
                ps.setNome(rs.getString("nome"));
                ps.setCpf(rs.getString("cpf"));
                ps.setDataNascimento(rs.getString("data_nascimento"));
                ps.setRegistroProfissional(rs.getString("registro_profissional"));
                ps.setTipo(rs.getString("tipo"));

                // Cria e seta o objeto Especialidade
                Especialidade especialidade = new Especialidade();
                especialidade.setNome(rs.getString("nome"));
                ps.setEspecialidade(especialidade);

                ps.setEmail(rs.getString("email"));
                ps.setTelefone(rs.getString("telefone"));
                lista.add(ps);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar profissionais: " + e.getMessage());
        }
        return lista;
    }

    public boolean excluir(int idProfissional) {
        String sql = "DELETE FROM profissional_saude WHERE id = ?";
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, idProfissional);
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao excluir profissional: " + e.getMessage());
            return false;
        }

    }

    public void desconectar() {
        try {
            conn.close();

        } catch (SQLException ex) {
            //Ficará vazio para evitar uma mensagem de erro desnecessária ao usuário
        }
    }

}
