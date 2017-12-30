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
import modelo.ContasReceber;

/**
 *
 * @author Administrador
 */
public class ContasReceberDAO {

    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;
    private ClienteDAO clienteDao;

    public ContasReceberDAO() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
        con = DriverManager.getConnection("jdbc:postgresql://localhost/clinicausm", "postgres", "postgres");
        clienteDao = new ClienteDAO();
    }

    public void salvar(ContasReceber contasReceber) throws SQLException {
        //carrega o conector do PostgreSql é registra no gerenciador de dispositivos

        pstm = con.prepareStatement("insert into contasreceber (idcontasreceber,descricao,documento,dataemissao,"
                + "datavencimento,valor,juros,idcliente) values (nextval('contasreceber_seq'),?,?,?,?,?,?,?)");
        pstm.setString(1, contasReceber.getDescricao());
        pstm.setString(2, contasReceber.getDocumento());
        pstm.setDate(3, new java.sql.Date ( contasReceber.getDataEmissao().getTime()));
        pstm.setDate(4, new java.sql.Date ( contasReceber.getDataVencimento().getTime()));
        pstm.setFloat(5, contasReceber.getValor());
        pstm.setFloat(6, contasReceber.getJuros());
        pstm.setInt(7, contasReceber.getCliente().getIdcliente());
        pstm.executeUpdate();
    }

    public void alterar(ContasReceber contasReceber) throws SQLException {
        //carrega o conector do PostgreSql é registra no gerenciador de dispositivos

        pstm = con.prepareStatement("update contasreceber set descricao=?,documento=?,dataemissao=?,"
                + "datavencimento=?,valor=?,juros=?,idcliente=? where idcontasreceber=?");
        pstm.setString(1, contasReceber.getDescricao());
        pstm.setString(2, contasReceber.getDocumento());
        pstm.setDate(3, new java.sql.Date ( contasReceber.getDataEmissao().getTime()));
        pstm.setDate(4, new java.sql.Date ( contasReceber.getDataVencimento().getTime()));
        pstm.setFloat(5, contasReceber.getValor());
        pstm.setFloat(6, contasReceber.getJuros());
        pstm.setInt(7, contasReceber.getCliente().getIdcliente());
        pstm.setInt(8, contasReceber.getIdContasReceber());

        pstm.executeUpdate();
    }

    public void excluir(ContasReceber contasReceber) throws SQLException {
        pstm = con.prepareStatement("delete from contasreceber where idcontasreceber=?");
        pstm.setInt(1, contasReceber.getIdContasReceber());
        pstm.executeUpdate();
    }

    public ContasReceber pesquisar(ContasReceber contasReceber) throws SQLException {
        ContasReceber retorno = null;
        pstm = con.prepareStatement("select * from contasreceber where idcontasreceber=?");
        pstm.setInt(1, contasReceber.getIdContasReceber());
        rs = pstm.executeQuery();
        if (rs.next()) {
            retorno = new ContasReceber();
            retorno.setIdContasReceber(rs.getInt("idcontasreceber"));
            retorno.setDescricao(rs.getString("descricao"));
            retorno.setDocumento(rs.getString("documento"));
            retorno.setDataEmissao(rs.getDate("dataemissao"));
            retorno.setDataVencimento(rs.getDate("datavencimento"));            
            retorno.setValor(rs.getFloat("valor"));
            retorno.setJuros(rs.getFloat("juros"));            

            return retorno;

        }
        return null;
    }

    public List<ContasReceber> listar(String filtro) throws SQLException {
        List<ContasReceber> retornos = new ArrayList<ContasReceber>();
        ContasReceber retorno = null;

        String sql = "";

        if (filtro.length() > 0) {
            sql = "select * from contasreceber where descricao ilike '" + filtro + "%'";
        } else {
            sql = "select * from contasreceber";
        }
        sql += " order by descricao";
        pstm = con.prepareStatement(sql);
        rs = pstm.executeQuery();
        while (rs.next()) {
            retorno = new ContasReceber();
            retorno.setIdContasReceber(rs.getInt("idcontasreceber"));
            retorno.setDescricao(rs.getString("descricao"));
            retorno.setDocumento(rs.getString("documento"));
            retorno.setDataEmissao(rs.getDate("dataemissao"));
            retorno.setDataVencimento(rs.getDate("datavencimento"));            
            retorno.setValor(rs.getFloat("valor"));
            retorno.setJuros(rs.getFloat("juros"));
            retorno.setCliente(clienteDao.getClientePorCodigo(rs.getInt("idcliente")));
            retornos.add(retorno);
        }
        return retornos;
    }

    public ContasReceber getPlanosPorNome(String descricao) throws SQLException {
        ContasReceber contasReceber = new ContasReceber();
        ContasReceber retorno = null;

        pstm = con.prepareStatement("select * from contasreceber where descricao=?");
        pstm.setString(1, descricao);
        rs = pstm.executeQuery();

        if (rs.next()) {

            retorno = new ContasReceber();
            retorno.setIdContasReceber(rs.getInt("idcontasreceber"));
            retorno.setDescricao(rs.getString("descricao"));
            retorno.setDocumento(rs.getString("documento"));
            retorno.setDataEmissao(rs.getDate("dataemissao"));
            retorno.setDataVencimento(rs.getDate("datavencimento"));            
            retorno.setValor(rs.getFloat("valor"));
            retorno.setJuros(rs.getFloat("juros"));
            retorno.setCliente(clienteDao.getClientePorCodigo(rs.getInt("idcliente")));

        }
        return retorno;

    }

    public ContasReceber getPlanosPorCodigo(int idcontasreceber) throws SQLException {

        ContasReceber retorno = null;

        pstm = con.prepareStatement("select * from contasreceber where idcontasreceber=?");
        pstm.setInt(1, idcontasreceber);
        rs = pstm.executeQuery();

        if (rs.next()) {

            retorno = new ContasReceber();
            retorno.setIdContasReceber(rs.getInt("idcontasreceber"));
            retorno.setDescricao(rs.getString("descricao"));
            retorno.setDocumento(rs.getString("documento"));
            retorno.setDataEmissao(rs.getDate("dataemissao"));
            retorno.setDataVencimento(rs.getDate("datavencimento"));            
            retorno.setValor(rs.getFloat("valor"));
            retorno.setJuros(rs.getFloat("juros"));
            retorno.setCliente(clienteDao.getClientePorCodigo(rs.getInt("idcliente")));
       
        }
        return retorno;
    }
    
    public List listarTotal() throws SQLException {
        List retornos = new ArrayList();      
        String sql = "select valor from contasreceber";
        pstm = con.prepareStatement(sql);
        rs = pstm.executeQuery();
        while (rs.next()) {            
            ContasReceber total = new ContasReceber();
            
            total.setValor(rs.getFloat("valor"));
            
        } 
        return retornos;
   }
}
