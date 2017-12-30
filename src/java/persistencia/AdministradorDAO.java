package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Administrador;


/**
 *
 * @author Aluno
 */
public class AdministradorDAO {

    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;

    public AdministradorDAO() throws SQLException {

        //Carrega o conector do PostgreSQL e registra no gerenciador de dispositivos.
        DriverManager.registerDriver(new org.postgresql.Driver());

        con = DriverManager.getConnection("jdbc:postgresql://localhost/clinicausm", "postgres", "postgres");

    }

    public void salvar(Administrador administrador) throws SQLException {
        pstm = con.prepareStatement("insert into administrador(idadmin, usuario, senha) values (nextval('seq_admin'), ?,?)");
        pstm.setString(1, administrador.getUsuario());
        pstm.setString(2, administrador.getSenha());        
        pstm.executeUpdate();
    }

    public Administrador pesquisar(Administrador administrador) throws SQLException {
        Administrador retorno = null;
        pstm = con.prepareStatement("select * from administrador where idadmin = ?");
        pstm.setInt(1, administrador.getIdadm());
        rs = pstm.executeQuery();
        if (rs.next()) {
            
            retorno = new Administrador();
            retorno.setIdadm(rs.getInt("id"));
            retorno.setUsuario(rs.getString("usuario"));
            retorno.setSenha(rs.getString("senha"));
                                    
            //con.close();
            return retorno;
            
        }
        return null;
        
    }
    
    public Administrador logar(Administrador administrador) throws SQLException {
        Administrador retorno = null;
        pstm = con.prepareStatement("select * from administrador where usuario = ? and senha = ? limit 1");
        pstm.setString(1, administrador.getUsuario());
        pstm.setString(2, administrador.getSenha());
        rs = pstm.executeQuery();
        if (rs.next()) {
            
            retorno = new Administrador();            
            retorno.setUsuario(rs.getString("usuario"));
            retorno.setSenha(rs.getString("senha"));
            
            
            //con.close();
            return retorno;
             
            
        }
        return null;
        
    }
    
    public List<Administrador> listar(String filtro) throws SQLException {
        List<Administrador> retornos = new ArrayList<Administrador>();
        Administrador retorno = null;
        String sql = "";
        
        if (filtro.length() > 0){
            sql = "select * from administrador where usuario ilike '"+filtro+"%'";
        } else {
            sql = "select * from administrador";
        }
        
         sql += " order by usuario ";
        
        pstm = con.prepareStatement(sql);
        rs = pstm.executeQuery();
        while (rs.next()) {
            
            retorno = new Administrador();
            retorno.setIdadm(rs.getInt("idadmin"));
            retorno.setUsuario(rs.getString("usuario"));
            retorno.setSenha(rs.getString("senha"));
            
            retornos.add(retorno);
            
        }
        //con.close();
        return retornos;
        
    }

    public void alterar(Administrador administrador) throws SQLException {
        pstm = con.prepareStatement("update administrador set usuario=?, senha=? where idadmin=?");
        pstm.setString(1, administrador.getUsuario());
        pstm.setString(2, administrador.getSenha());
        pstm.setInt(3, administrador.getIdadm()); 
        pstm.executeUpdate();
        
       //con.close();

    }

    public void excluir(Administrador administrador) throws SQLException {
        pstm = con.prepareStatement("delete from administrador where idadmin=?");
        pstm.setInt(1, administrador.getIdadm());
        pstm.executeUpdate();
        
       //con.close();

    }
}
