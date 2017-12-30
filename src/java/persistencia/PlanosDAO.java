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
import modelo.Planos;

/**
 *
 * @author Administrador
 */
public class PlanosDAO {

    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;

    public PlanosDAO() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
        con = DriverManager.getConnection("jdbc:postgresql://localhost/clinicausm", "postgres", "postgres");
    }

    public void salvar(Planos planos) throws SQLException {
        //carrega o conector do PostgreSql é registra no gerenciador de dispositivos

        pstm = con.prepareStatement("insert into planos (idplano,nome,valor) values (nextval('plano_seq'),?,?)");
        pstm.setString(1, planos.getNome());
        pstm.setFloat(2, planos.getValor());
        
        pstm.executeUpdate();
    }


    public void alterar(Planos planos) throws SQLException {
        //carrega o conector do PostgreSql é registra no gerenciador de dispositivos

        pstm = con.prepareStatement("update planos set nome=?,valor=? where idplano=?");
        pstm.setString(1, planos.getNome());
        pstm.setFloat(2, planos.getValor());
        pstm.setInt(3, planos.getIdplano());
        pstm.executeUpdate();
    }

    public void excluir(Planos planos) throws SQLException {
        pstm = con.prepareStatement("delete from planos where idplano=?");
        pstm.setInt(1, planos.getIdplano());
        pstm.executeUpdate();
    }

    public Planos pesquisar(Planos planos) throws SQLException {
        Planos retorno = null;
        pstm = con.prepareStatement("select * from planos where idplano=?");
        pstm.setInt(1, planos.getIdplano());
        rs = pstm.executeQuery();
        if (rs.next()) {
            retorno = new Planos();
            retorno.setIdplano(rs.getInt("idplano"));
            retorno.setNome(rs.getString("nome"));
            retorno.setValor(rs.getFloat("valor"));
           
            return retorno;

        }
        return null;
    }

    public List<Planos> listar(String filtro) throws SQLException {
        List<Planos> retornos = new ArrayList<Planos>();
        Planos retorno = null;

        String sql = "";

        if (filtro.length() > 0) {
            sql = "select * from planos where nome ilike '" + filtro + "%'";
        } else {
            sql = "select * from planos";
        }
        sql += " order by nome";
        pstm = con.prepareStatement(sql);
        rs = pstm.executeQuery();
        while (rs.next()) {
            retorno = new Planos();
            retorno.setIdplano(rs.getInt("idplano"));
            retorno.setNome(rs.getString("nome"));
            retorno.setValor(rs.getFloat("valor"));
            
            retornos.add(retorno);
        }
        return retornos;
    }
    
    public Planos getPlanosPorNome(String nome) throws SQLException {
        Planos planos = new Planos();
        Planos retorno = null;
        
        pstm = con.prepareStatement("select * from planos where nome=?");
        pstm.setString(1, nome);
        rs = pstm.executeQuery();

        if (rs.next()) {

            retorno = new Planos();
            retorno.setIdplano(rs.getInt("idplano"));
            retorno.setNome(rs.getString("nome"));
            retorno.setValor(rs.getFloat("valor"));

        }
         return retorno;
        
    }
    
    public Planos getPlanosPorCodigo(int idplano) throws SQLException {
        
        Planos retorno = null;
        
        pstm = con.prepareStatement("select * from planos where idplano=?");
        pstm.setInt(1, idplano);
        rs = pstm.executeQuery();

        if (rs.next()) {

            retorno = new Planos();
            retorno.setIdplano(rs.getInt("idplano"));
            retorno.setNome(rs.getString("nome"));
            retorno.setValor(rs.getFloat("valor"));
        }
         return retorno;
    }
}
