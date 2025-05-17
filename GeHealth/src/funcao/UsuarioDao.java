package funcao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;

public class UsuarioDao {

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

    public Usuario validarUsuario(Usuario u) throws SQLException {
        st = conn.prepareStatement("SELECT * FROM Usuario WHERE usuario = ? AND senha = ?");
        st.setString(1, u.getUsuario());
        st.setString(2, u.getSenha());

        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        rs = st.executeQuery();

        while (rs.next()) {
            Usuario user = new Usuario();
            user.setUsuario(rs.getString("usuario"));
            user.setSenha(rs.getString("senha"));
            user.setNivel(rs.getString("nivel"));

            listaUsuarios.add(user);

            if (!listaUsuarios.isEmpty()) {
                return listaUsuarios.get(0);
            }
        }
        return null;
    }

    public void desconectar() {
        try {
            conn.close();

        } catch (SQLException ex) {
            //Ficará vazio para evitar uma mensagem de erro desnecessária ao usuário
        }
    }
}
