/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funcao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.Consulta;
import model.Especialidade;
import model.Paciente;
import model.ProfissionalSaude;

/**
 *
 * @author Marcos
 */
public class ConsultaDao {

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

    public int salvar(Consulta c) {
        int status;
        try {
            st = conn.prepareStatement("INSERT INTO consulta (paciente_id, profissional_saude_id, valor,"
                    + "data_consulta, observacao, especialidade_id) VALUES(?, ?, ?, ?, ?, ?)");
            st.setInt(1, c.getPaciente().getId());
            st.setInt(2, c.getProfissionalSaude().getId());
            st.setDouble(3, c.getValor());
            st.setString(4, c.getDataConsulta());
            st.setString(5, c.getObservacao());
            st.setInt(6, c.getEspecialidade().getId());

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
        modelo.addColumn("Paciente");
        modelo.addColumn("Médico");
        modelo.addColumn("Valor");
        modelo.addColumn("Data Consulta");
        modelo.addColumn("Observação");
        modelo.addColumn("Especialidade");

        String sql = "SELECT C.id, paciente.nome AS nome_paciente ,"
 + " P.nome AS nome_profissional, C.valor, C.data_consulta, C.observacao, E.nome AS especialidade\n" +
"FROM consulta AS C\n" +
"INNER JOIN profissional_saude AS P ON P.id = C.profissional_saude_id\n" +
"INNER JOIN paciente ON paciente.id = C.paciente_id\n" +
"INNER JOIN especialidade AS E ON E.id = C.especialidade_id;";

        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                Object[] linha = {
                    rs.getInt("id"),
                    rs.getString("nome_paciente"),
                    rs.getString("nome_profissional"),
                    rs.getDouble("valor"),
                    rs.getString("data_consulta"),
                    rs.getString("observacao"),
                    rs.getString("especialidade")
                };
                modelo.addRow(linha);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao montar tabela: " + e.getMessage());
        }

        return modelo;
    }

    public ArrayList<Consulta> listar() {
        ArrayList<Consulta> lista = new ArrayList<>();
        try {
            st = conn.prepareStatement("SELECT C.id, paciente.nome AS nome_paciente ,"
 + " P.nome AS nome_profissional, C.valor, C.data_consulta, C.observacao, E.nome AS especialidade\n" +
"FROM consulta AS C\n" +
"INNER JOIN profissional_saude AS P ON P.id = C.profissional_saude_id\n" +
"INNER JOIN paciente ON paciente.id = C.paciente_id\n" +
"INNER JOIN especialidade AS E ON E.id = C.especialidade_id;");
            rs = st.executeQuery();

            while (rs.next()) {
                Consulta c = new Consulta();

                c.setId(rs.getInt("id"));

                // Cria e seta o objeto Paciente
                Paciente paciente = new Paciente();
                paciente.setNome(rs.getString("nome_paciente"));
                c.setPaciente(paciente);

                // Cria e seta o objeto ProfissionalSaude
                ProfissionalSaude profissional = new ProfissionalSaude();
                profissional.setNome(rs.getString("nome_profissional"));
                c.setProfissionalSaude(profissional);

                // Cria e seta o objeto Especialidade
                Especialidade especialidade = new Especialidade();
                especialidade.setNome(rs.getString("especialidade"));
                c.setEspecialidade(especialidade);

                c.setValor(rs.getDouble("valor"));
                c.setDataConsulta(rs.getString("data_consulta"));
                c.setObservacao(rs.getString("observacao"));

                lista.add(c);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar consultas: " + e.getMessage());
        }
        return lista;
    }

    public boolean excluir(int idConsulta) {
        String sql = "DELETE FROM consulta WHERE id = ?";
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, idConsulta);
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao excluir consulta: " + e.getMessage());
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
