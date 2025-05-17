package funcao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.Paciente;

public class PacienteDao {

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

    public int salvar(Paciente paciente) {
        int status;
        try {
            st = conn.prepareStatement("INSERT INTO paciente (nome, cpf, data_nascimento, "
                    + "email, telefone) VALUES(?, ?, ?, ?, ?)");
            st.setString(1, paciente.getNome());
            st.setString(2, paciente.getCpf());
            st.setString(3, paciente.getDataNascimento());
            st.setString(4, paciente.getEmail());
            st.setString(5, paciente.getTelefone());
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
        modelo.addColumn("E-mail");
        modelo.addColumn("Telefone");

        String sql = "SELECT * FROM paciente";

        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                Object[] linha = {
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getString("cpf"),
                    rs.getString("data_nascimento"),
                    rs.getString("email"),
                    rs.getString("telefone")
                };
                modelo.addRow(linha);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao montar tabela: " + e.getMessage());
        }

        return modelo;
    }

    public ArrayList<Paciente> listar() {
        ArrayList<Paciente> lista = new ArrayList<>();
        try {
            st = conn.prepareStatement("SELECT * FROM paciente");
            rs = st.executeQuery();

            while (rs.next()) {
                Paciente p = new Paciente();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setCpf(rs.getString("cpf"));
                p.setDataNascimento(rs.getString("data_nascimento"));
                p.setEmail(rs.getString("email"));
                p.setTelefone(rs.getString("telefone"));
                lista.add(p);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar ´pacientes: " + e.getMessage());
        }
        return lista;
    }

    public boolean excluir(int idPaciente) {
        String sql = "DELETE FROM paciente WHERE id = ?";
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, idPaciente);
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao excluir paciente: " + e.getMessage());
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
