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
import modelo.CategoriaUsm;
import modelo.Planos;

/**
 *
 * @author Administrador
 */
public class CategoriaDAO {

    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;

    public CategoriaDAO() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
        con = DriverManager.getConnection("jdbc:postgresql://localhost/clinicausm", "postgres", "postgres");
    }

    public void salvar(CategoriaUsm categoriaUsm) throws SQLException {
        //carrega o conector do PostgreSql é registra no gerenciador de dispositivos

        pstm = con.prepareStatement("insert into categoriausm (idcategoria,qtd_pessoa,valor) values (nextval('categoriausm_seq'),?,?)");
        pstm.setString(1, categoriaUsm.getQtdPessoa());
        pstm.setFloat(2, categoriaUsm.getValor());

        pstm.executeUpdate();
    }

    public void alterar(CategoriaUsm categoriaUsm) throws SQLException {
        //carrega o conector do PostgreSql é registra no gerenciador de dispositivos

        pstm = con.prepareStatement("update categoriausm set qtd_pessoa=?,valor=? where idcategoria=?");
        pstm.setString(1, categoriaUsm.getQtdPessoa());
        pstm.setFloat(2, categoriaUsm.getValor());
        pstm.setInt(3, categoriaUsm.getIdcategoria());
        pstm.executeUpdate();
    }

    public void excluir(CategoriaUsm categoriaUsm) throws SQLException {
        pstm = con.prepareStatement("delete from categoriausm where idcategoria=?");
        pstm.setInt(1, categoriaUsm.getIdcategoria());
        pstm.executeUpdate();
    }

    public Planos pesquisar(CategoriaUsm categoriaUsm) throws SQLException {
        Planos retorno = null;
        pstm = con.prepareStatement("select * from categoriausm where idcategoria=?");
        pstm.setInt(1, categoriaUsm.getIdcategoria());
        rs = pstm.executeQuery();
        if (rs.next()) {
            retorno = new Planos();
            retorno.setIdplano(rs.getInt("idcategoria"));
            retorno.setNome(rs.getString("qtd_pessoa"));
            retorno.setValor(rs.getFloat("valor"));

            return retorno;

        }
        return null;
    }

    public List<CategoriaUsm> listar(String filtro) throws SQLException {
        List<CategoriaUsm> retornos = new ArrayList<CategoriaUsm>();
        CategoriaUsm retorno = null;

        String sql = "";

        if (filtro.length() > 0) {
            sql = "select * from categoriausm where qtd_pessoa ilike '" + filtro + "%'";
        } else {
            sql = "select * from categoriausm";
        }
        sql += " order by idcategoria";
        pstm = con.prepareStatement(sql);
        rs = pstm.executeQuery();
        while (rs.next()) {
            retorno = new CategoriaUsm();
            retorno.setIdcategoria(rs.getInt("idcategoria"));
            retorno.setQtdPessoa(rs.getString("qtd_pessoa"));
            retorno.setValor(rs.getFloat("valor"));

            retornos.add(retorno);
        }
        return retornos;
    }

    public CategoriaUsm getCategoriaPorQtdPessoa(String qtdPessoa) throws SQLException {
        CategoriaUsm categoriaUsm = new CategoriaUsm();
        CategoriaUsm retorno = null;

        pstm = con.prepareStatement("select * from categoriausm where qtd_pessoa=?");
        pstm.setString(1, qtdPessoa);
        rs = pstm.executeQuery();

        if (rs.next()) {

            retorno = new CategoriaUsm();
            retorno.setIdcategoria(rs.getInt("idcategoria"));
            retorno.setQtdPessoa(rs.getString("qtd_pessoa"));
            retorno.setValor(rs.getFloat("valor"));

        }
        return retorno;

    }

    public CategoriaUsm getCategoriaPorCodigo(int idcategoria) throws SQLException {

        CategoriaUsm retorno = null;

        pstm = con.prepareStatement("select * from categoriausm where idcategoria=?");
        pstm.setInt(1, idcategoria);
        rs = pstm.executeQuery();

        if (rs.next()) {

            retorno = new CategoriaUsm();
            retorno.setIdcategoria(rs.getInt("idcategoria"));
            retorno.setQtdPessoa(rs.getString("qtd_pessoa"));
            retorno.setValor(rs.getFloat("valor"));
        }
        return retorno;
    }
}
