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
import modelo.CadastroMedico;
import modelo.FolhaPag_Medico;



/**
 *
 * @author Administrador
 */
public class FolhaPag_MedicoDAO {

    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;
    private CadastroMedicoDAO cadastroMedicoDao;

    public FolhaPag_MedicoDAO() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
        con = DriverManager.getConnection("jdbc:postgresql://localhost/clinicausm", "postgres", "postgres");
        cadastroMedicoDao = new CadastroMedicoDAO();
    }

    public void salvar(FolhaPag_Medico folhaPag_Medico) throws SQLException {
        //carrega o conector do PostgreSql é registra no gerenciador de dispositivos

        pstm = con.prepareStatement("insert into folhapag_medico (idfolhapag_medico,totalhoras,mes,valortotal,"
                + "idtipomedico,observacao,valordesconto,valormais) values(nextval('folhamed_seq'),?,?,?,?,?,?,?)");
        pstm.setFloat(1, folhaPag_Medico.getTotalHoras());        
        pstm.setDate(2, new java.sql.Date(folhaPag_Medico.getMes().getTime()));
        pstm.setFloat(3, folhaPag_Medico.getValorTotal());
        pstm.setInt(4, folhaPag_Medico.getCadastroMedico().getIdCadastroMedico());
        pstm.setString(5, folhaPag_Medico.getObservacao());
        pstm.setFloat(6, folhaPag_Medico.getValorDesconto());
        pstm.setFloat(7, folhaPag_Medico.getValorMais());
        pstm.executeUpdate();
    }

    public void alterar(FolhaPag_Medico folhaPag_Medico) throws SQLException {
        //carrega o conector do PostgreSql é registra no gerenciador de dispositivos

        pstm = con.prepareStatement("update folhapag_medico set totalhoras=?,mes=?,valortotal=?,idtipomedico=?,"
                + "observacao=?,valordesconto=?,valormais=? where idfolhapag_medico=?");
        pstm.setFloat(1, folhaPag_Medico.getTotalHoras());        
        pstm.setDate(2, new java.sql.Date(folhaPag_Medico.getMes().getTime()));
        pstm.setFloat(3, folhaPag_Medico.getValorTotal());
        pstm.setInt(4, folhaPag_Medico.getCadastroMedico().getIdCadastroMedico());
        pstm.setString(5, folhaPag_Medico.getObservacao());
        pstm.setFloat(6, folhaPag_Medico.getValorDesconto());
        pstm.setFloat(7, folhaPag_Medico.getValorMais());
        pstm.setInt(8, folhaPag_Medico.getIdPagamentoMedico());
        pstm.executeUpdate();
    }

    public void excluir(FolhaPag_Medico folhaPag_Medico) throws SQLException {
        pstm = con.prepareStatement("delete from folhapag_medico where idfolhapag_medico=?");
        pstm.setInt(1, folhaPag_Medico.getIdPagamentoMedico());
        pstm.executeUpdate();
    }

    public FolhaPag_Medico pesquisar(FolhaPag_Medico folhaPag_Medico) throws SQLException {
        FolhaPag_Medico retorno = null;
        pstm = con.prepareStatement("select * from folhapag_medico where idfolhapag_medico=?");
        pstm.setInt(1, folhaPag_Medico.getIdPagamentoMedico());
        rs = pstm.executeQuery();
        if (rs.next()) {
            retorno = new FolhaPag_Medico();
            retorno.setIdPagamentoMedico(rs.getInt("idfolhapag_medico"));
            retorno.setTotalHoras(rs.getFloat("totalhoras"));
            retorno.setMes(rs.getDate("mes"));
            retorno.setValorTotal(rs.getFloat("valortotal"));
            
            return retorno;

        }
        return null;
    }

    public List<FolhaPag_Medico> listar(String filtro) throws SQLException {
        List<FolhaPag_Medico> retornos = new ArrayList<FolhaPag_Medico>();
        FolhaPag_Medico retorno = null;

        String sql = "";

        if (filtro.length() > 0) {
            sql = "select * from folhapag_medico where mes ilike '" + filtro + "%'";
        } else {
            sql = "select * from folhapag_medico";
        }
        sql += " order by mes";
        pstm = con.prepareStatement(sql);
        rs = pstm.executeQuery();
        while (rs.next()) {
            retorno = new FolhaPag_Medico();
            retorno.setIdPagamentoMedico(rs.getInt("idfolhapag_medico"));
            retorno.setTotalHoras(rs.getFloat("totalhoras"));
            retorno.setMes(rs.getDate("mes"));
            retorno.setValorTotal(rs.getFloat("valortotal"));
            retorno.setObservacao(rs.getString("observacao"));
            retorno.setValorDesconto(rs.getFloat("valordesconto"));
            retorno.setValorMais(rs.getFloat("valormais"));
            retorno.setCadastroMedico(cadastroMedicoDao.getCadastroMedicoPorCodigo(rs.getInt("idtipomedico")));
            retornos.add(retorno);
        }
        return retornos;
    }

    public FolhaPag_Medico getFolhaPag_MedicoPorMes(String descricao) throws SQLException {
        FolhaPag_Medico contasPagar = new FolhaPag_Medico();
        FolhaPag_Medico retorno = null;

        pstm = con.prepareStatement("select * from folhapagamento where mes=?");
        pstm.setString(1, descricao);
        rs = pstm.executeQuery();

        if (rs.next()) {

            retorno = new FolhaPag_Medico();
            retorno.setIdPagamentoMedico(rs.getInt("idfolhapag_medico"));
            retorno.setTotalHoras(rs.getFloat("totalhoras"));
            retorno.setMes(rs.getDate("mes"));
            retorno.setValorTotal(rs.getFloat("valortotal"));
            retorno.setValorDesconto(rs.getFloat("valordesconto"));
            retorno.setValorMais(rs.getFloat("valormais"));
            retorno.setCadastroMedico(cadastroMedicoDao.getCadastroMedicoPorCodigo(rs.getInt("idtipomedico")));

        }
        return retorno;

    }

    public FolhaPag_Medico getFolhaPag_MedicoPorCodigo(int idFolhaPagamento) throws SQLException {

        FolhaPag_Medico retorno = null;

        pstm = con.prepareStatement("select * from folhapagamento where idfolhapagamento=?");
        pstm.setInt(1, idFolhaPagamento);
        rs = pstm.executeQuery();

        if (rs.next()) {

            retorno = new FolhaPag_Medico();
            retorno.setIdPagamentoMedico(rs.getInt("idfolhapag_medico"));
            retorno.setTotalHoras(rs.getFloat("totalhoras"));
            retorno.setMes(rs.getDate("mes"));
            retorno.setValorTotal(rs.getFloat("valortotal"));
            retorno.setValorDesconto(rs.getFloat("valordesconto"));
            retorno.setValorMais(rs.getFloat("valormais"));
            retorno.setCadastroMedico(cadastroMedicoDao.getCadastroMedicoPorCodigo(rs.getInt("idtipomedico")));
            
        }
        return retorno;
    }
    
    public FolhaPag_Medico pesquisarValorHoraMedico(FolhaPag_Medico folhaPag_Medico) throws SQLException {
        FolhaPag_Medico retorno = null;

        pstm = con.prepareStatement("select valorhora from tipomedico where nome=?");
        pstm.setString(1, folhaPag_Medico.getCadastroMedico().getNome());
        rs = pstm.executeQuery();
        if (rs.next()) {
            retorno = new FolhaPag_Medico();
            retorno.setValorHora(rs.getFloat("valorhora"));
        }
        return retorno;
    }



}
