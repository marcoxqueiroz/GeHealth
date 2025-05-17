package funcao;

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

public class EspecialidadeDao {

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

    public int salvar(Especialidade esp) {
        int status;
        try {
            st = conn.prepareStatement("INSERT INTO especialidade (nome) VALUES (?)");
            st.setString(1, esp.getNome());
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

        String sql = "SELECT id, nome FROM especialidade";

        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                Object[] linha = {
                    rs.getInt("id"),
                    rs.getString("nome")
                };
                modelo.addRow(linha);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao montar tabela: " + e.getMessage());
        }

        return modelo;
    }

    public ArrayList<Especialidade> listar() {
        ArrayList<Especialidade> lista = new ArrayList<>();
        try {
            st = conn.prepareStatement("SELECT id, nome FROM especialidade");
            rs = st.executeQuery();

            while (rs.next()) {
                Especialidade esp = new Especialidade();
                esp.setId(rs.getInt("id"));
                esp.setNome(rs.getString("nome"));
                lista.add(esp);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar especialidades: " + e.getMessage());
        }
        return lista;
    }
        public boolean excluir(int idEspecialidade) {
        String sql = "DELETE FROM especialidade WHERE id = ?";
        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, idEspecialidade);
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Erro ao excluir especialidade: " + e.getMessage());
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
