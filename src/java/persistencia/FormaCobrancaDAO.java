/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.FormaCobranca;

/**
 *
 * @author Administrador
 */
public class FormaCobrancaDAO {
    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;

    public FormaCobrancaDAO() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
        con = DriverManager.getConnection("jdbc:postgresql://localhost/clinicausm", "postgres", "postgres");
    }

    public void salvar(FormaCobranca formaCobranca) throws SQLException {
        //carrega o conector do PostgreSql é registra no gerenciador de dispositivos

        pstm = con.prepareStatement("insert into forma_cobranca (idcobranca,tipo,cobrador) values (nextval('cobranca_seq'),?,?)");
        pstm.setString(1, formaCobranca.getTipo());
        pstm.setString(2, formaCobranca.getCobrador());
               
        pstm.executeUpdate();
    }

    public void alterar(FormaCobranca formaCobranca) throws SQLException {
        //carrega o conector do PostgreSql é registra no gerenciador de dispositivos

        pstm = con.prepareStatement("update forma_cobranca set tipo=?,cobrador=? where idcobranca=?");
        pstm.setString(1, formaCobranca.getTipo());
        pstm.setString(2, formaCobranca.getCobrador());
        pstm.setInt(3, formaCobranca.getIdcobranca());
        pstm.executeUpdate();
    }

    public void excluir(FormaCobranca formaCobranca) throws SQLException {
        pstm = con.prepareStatement(
                "delete from forma_cobranca where idcobranca=?");
        pstm.setInt(1, formaCobranca.getIdcobranca());
        pstm.executeUpdate();
    }

    public FormaCobranca pesquisar(FormaCobranca formaCobranca) throws SQLException {
        FormaCobranca retorno = null;
        pstm = con.prepareStatement("select * from forma_cobranca where idcobranca=?");
        pstm.setInt(1, formaCobranca.getIdcobranca());
        rs = pstm.executeQuery();
        if (rs.next()) {
            retorno = new FormaCobranca();
            retorno.setIdcobranca(rs.getInt("idcobranca"));
            retorno.setTipo(rs.getString("tipo"));
            retorno.setCobrador(rs.getString("cobrador"));
            
            return retorno;

        }
        return null;
    }

    public List<FormaCobranca> listar(String filtro) throws SQLException {
        List<FormaCobranca> retornos = new ArrayList<FormaCobranca>();
        FormaCobranca retorno = null;

        String sql = "";

        if (filtro.length() > 0) {
            sql = "select * from forma_cobranca where tipo ilike '" + filtro + "%'";
        } else {
            sql = "select * from forma_cobranca";
        }
        sql += " order by tipo";
        pstm = con.prepareStatement(sql);
        rs = pstm.executeQuery();
        while (rs.next()) {
            retorno = new FormaCobranca();
            retorno.setIdcobranca(rs.getInt("idcobranca"));
            retorno.setTipo(rs.getString("tipo"));
            retorno.setCobrador(rs.getString("cobrador"));
           
            retornos.add(retorno);
        }
        return retornos;
    }
    
    public FormaCobranca getFormaCobrancaPorTipo(String tipo) throws SQLException {
        FormaCobranca formaCobranca = new FormaCobranca();
        FormaCobranca retorno = null;

        pstm = con.prepareStatement("select * from forma_cobranca where tipo=?");
        pstm.setString(1, tipo);
        rs = pstm.executeQuery();

        if (rs.next()) {

            retorno = new FormaCobranca();
            retorno.setIdcobranca(rs.getInt("idcobranca"));
            retorno.setTipo(rs.getString("tipo"));
            retorno.setCobrador(rs.getString("cobrador"));
            

        }
        return retorno;

    }
    
    public FormaCobranca getFormaCobrancaPorCodigo(int idcobranca) throws SQLException {
        FormaCobranca formaCobranca = new FormaCobranca();
        FormaCobranca retorno = null;

        pstm = con.prepareStatement("select * from forma_cobranca where idcobranca=?");
        pstm.setInt(1, idcobranca);
        rs = pstm.executeQuery();

        if (rs.next()) {

            retorno = new FormaCobranca();
            retorno.setIdcobranca(rs.getInt("idcobranca"));
            retorno.setTipo(rs.getString("tipo"));
            retorno.setCobrador(rs.getString("cobrador"));
            

        }
        return retorno;

    }
}
