/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static jdk.nashorn.internal.objects.NativeDate.getTime;
import modelo.ContasPagar;


/**
 *
 * @author Administrador
 */
public class ContasPagarDAO {

    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;
    private FornecedorDAO fornecedorDao;

    public ContasPagarDAO() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
        con = DriverManager.getConnection("jdbc:postgresql://localhost/clinicausm", "postgres", "postgres");
        fornecedorDao = new FornecedorDAO();
    }

    public void salvar(ContasPagar contasPagar) throws SQLException {
        //carrega o conector do PostgreSql é registra no gerenciador de dispositivos

        pstm = con.prepareStatement("insert into contaspagar (idcontaspagar,descricao,dataemissao,"
                + "datavencimento,documento,observacao,juros,valor,idfornecedor) values"
                + "(nextval('contaspagar_seq'),?,?,?,?,?,?,?,?)");
        pstm.setString(1, contasPagar.getDescricao());        
        pstm.setDate(2, new java.sql.Date (contasPagar.getDataEmissao().getTime()));
        pstm.setDate(3, new java.sql.Date (contasPagar.getDataVencimento().getTime()));
        pstm.setString(4, contasPagar.getDocumento());
        pstm.setString(5, contasPagar.getObservacao());
        pstm.setFloat(6, contasPagar.getValor());
        pstm.setFloat(7, contasPagar.getJuros());
        pstm.setInt(8, contasPagar.getFornecedor().getIdfornecedor());
        pstm.executeUpdate();
    }

    public void alterar(ContasPagar contasPagar) throws SQLException {
        //carrega o conector do PostgreSql é registra no gerenciador de dispositivos

        pstm = con.prepareStatement("update contaspagar set descricao=?,dataemissao=?,"
                + "datavencimento=?,documento=?,observacao=?,juros=?,valor=?,idfornecedor=?"
                + " where idcontaspagar=?");
        pstm.setString(1, contasPagar.getDescricao());        
        pstm.setDate(2, new java.sql.Date (contasPagar.getDataEmissao().getTime()));
        pstm.setDate(3, new java.sql.Date (contasPagar.getDataVencimento().getTime()));
        pstm.setString(4, contasPagar.getDocumento());
        pstm.setString(5, contasPagar.getObservacao());
        pstm.setFloat(6, contasPagar.getValor());
        pstm.setFloat(7, contasPagar.getJuros());
        pstm.setInt(8, contasPagar.getFornecedor().getIdfornecedor());
        pstm.setInt(9, contasPagar.getIdContasPagar());

        pstm.executeUpdate();
    }

    public void excluir(ContasPagar contasPagar) throws SQLException {
        pstm = con.prepareStatement("delete from contaspagar where idcontaspagar=?");
        pstm.setInt(1, contasPagar.getIdContasPagar());
        pstm.executeUpdate();
    }

    public ContasPagar pesquisar(ContasPagar contasPagar) throws SQLException {
        ContasPagar retorno = null;
        pstm = con.prepareStatement("select * from contaspagar where idcontaspagar=?");
        pstm.setInt(1, contasPagar.getIdContasPagar());
        rs = pstm.executeQuery();
        if (rs.next()) {
            retorno = new ContasPagar();
            retorno.setIdContasPagar(rs.getInt("idcontaspagar"));
            retorno.setDescricao(rs.getString("descricao"));
            retorno.setDocumento(rs.getString("documento"));
            retorno.setDataEmissao(rs.getDate("dataemissao"));
            retorno.setDataVencimento(rs.getDate("datavencimento"));            
            retorno.setValor(rs.getFloat("valor"));
            retorno.setJuros(rs.getFloat("juros"));            
            retorno.setObservacao(rs.getString("observacao"));

            return retorno;

        }
        return null;
    }

    public List<ContasPagar> listar(String filtro) throws SQLException {
        List<ContasPagar> retornos = new ArrayList<ContasPagar>();
        ContasPagar retorno = null;

        String sql = "";

        if (filtro.length() > 0) {
            sql = "select * from contaspagar where descricao ilike '" + filtro + "%'";
        } else {
            sql = "select * from contaspagar";
        }
        sql += " order by descricao";
        pstm = con.prepareStatement(sql);
        rs = pstm.executeQuery();
        while (rs.next()) {
            retorno = new ContasPagar();
            retorno.setIdContasPagar(rs.getInt("idcontaspagar"));
            retorno.setDescricao(rs.getString("descricao"));
            retorno.setDocumento(rs.getString("documento"));
            retorno.setDataEmissao(rs.getDate("dataemissao"));
            retorno.setDataVencimento(rs.getDate("datavencimento"));            
            retorno.setValor(rs.getFloat("valor"));
            retorno.setJuros(rs.getFloat("juros"));  
            retorno.setObservacao(rs.getString("observacao"));
            retorno.setFornecedor(fornecedorDao.getFornecedorPorCodigo(rs.getInt("idfornecedor")));
            retornos.add(retorno);
        }
        return retornos;
    }

    public ContasPagar getContasPagarPorDescricao(String descricao) throws SQLException {
        ContasPagar contasPagar = new ContasPagar();
        ContasPagar retorno = null;

        pstm = con.prepareStatement("select * from contaspagar where descricao=?");
        pstm.setString(1, descricao);
        rs = pstm.executeQuery();

        if (rs.next()) {

            retorno = new ContasPagar();
            retorno.setIdContasPagar(rs.getInt("idcontaspagar"));
            retorno.setDescricao(rs.getString("descricao"));
            retorno.setDocumento(rs.getString("documento"));
            retorno.setDataEmissao(rs.getDate("dataemissao"));
            retorno.setDataVencimento(rs.getDate("datavencimento"));            
            retorno.setValor(rs.getFloat("valor"));
            retorno.setJuros(rs.getFloat("juros"));  
            retorno.setObservacao(rs.getString("observacao"));
            retorno.setFornecedor(fornecedorDao.getFornecedorPorCodigo(rs.getInt("idfornecedor")));
        }
        return retorno;

    }

    public ContasPagar getPlanosPorCodigo(int idcontaspagar) throws SQLException {

        ContasPagar retorno = null;

        pstm = con.prepareStatement("select * from contaspagar where idcontaspagar=?");
        pstm.setInt(1, idcontaspagar);
        rs = pstm.executeQuery();

        if (rs.next()) {

            retorno = new ContasPagar();
            retorno.setIdContasPagar(rs.getInt("idcontaspagar"));
            retorno.setDescricao(rs.getString("descricao"));
            retorno.setDocumento(rs.getString("documento"));
            retorno.setDataEmissao(rs.getDate("dataemissao"));
            retorno.setDataVencimento(rs.getDate("datavencimento"));            
            retorno.setValor(rs.getFloat("valor"));
            retorno.setJuros(rs.getFloat("juros")); 
            retorno.setObservacao(rs.getString("observacao"));
            retorno.setFornecedor(fornecedorDao.getFornecedorPorCodigo(rs.getInt("idfornecedor")));
        }
        return retorno;
    }
}
