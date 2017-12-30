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
import modelo.FolhaPagamento;
import modelo.Funcionario;

/**
 *
 * @author Administrador
 */
public class FolhaPagamentoDAO {

    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;
    private FuncionarioDAO funcionarioDao;

    public FolhaPagamentoDAO() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
        con = DriverManager.getConnection("jdbc:postgresql://localhost/clinicausm", "postgres", "postgres");
        funcionarioDao = new FuncionarioDAO();
    }

    public void salvar(FolhaPagamento folhaPagamento) throws SQLException {
        //carrega o conector do PostgreSql é registra no gerenciador de dispositivos

        pstm = con.prepareStatement("insert into folhapagamento (idfolhapagamento,horasmes,horas50,horas100,"
                + "horasfaltosas,valetransporte,inss,insalubre,salariobruto,adiantamento,outros,desconto,red,"
                + "liquido,observacao,mes,idfuncionario,cargo) values(nextval('folha_seq'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        pstm.setFloat(1, folhaPagamento.getHorasMes());
        pstm.setFloat(2, folhaPagamento.getHoras50());
        pstm.setFloat(3, folhaPagamento.getHoras100());
        pstm.setFloat(4, folhaPagamento.getHorasFaltosas());
        pstm.setFloat(5, folhaPagamento.getValeTransporte());
        pstm.setFloat(6, folhaPagamento.getInss());
        pstm.setFloat(7, folhaPagamento.getInsalubre());
        pstm.setFloat(8, folhaPagamento.getSalarioBruto());
        pstm.setFloat(9, folhaPagamento.getAdiantamento());
        pstm.setFloat(10, folhaPagamento.getOutros());
        pstm.setFloat(11, folhaPagamento.getDesconto());
        pstm.setFloat(12, folhaPagamento.getRed());
        pstm.setFloat(13, folhaPagamento.getLiquido());
        pstm.setString(14, folhaPagamento.getObservacao());
        pstm.setDate(15, new java.sql.Date(folhaPagamento.getMes().getTime()));
        pstm.setInt(16, folhaPagamento.getFuncionario().getIdfuncionario());
        pstm.setString(17, folhaPagamento.getCargo());

        pstm.executeUpdate();
    }

    public void alterar(FolhaPagamento folhaPagamento) throws SQLException {
        //carrega o conector do PostgreSql é registra no gerenciador de dispositivos

        pstm = con.prepareStatement("update folhapagamento set horasmes=?,horas50=?,horas100=?,"
                + "horasfaltosas=?,valetransporte=?,inss=?,insalubre=?,salariobruto=?,adiantamento=?,outros=?,"
                + "desconto=?,red=?,liquido=?,observacao=?,mes=?,idfuncionario=?,cargo=? where idfolhapagamento=?");
        pstm.setFloat(1, folhaPagamento.getHorasMes());
        pstm.setFloat(2, folhaPagamento.getHoras50());
        pstm.setFloat(3, folhaPagamento.getHoras100());
        pstm.setFloat(4, folhaPagamento.getHorasFaltosas());
        pstm.setFloat(5, folhaPagamento.getValeTransporte());
        pstm.setFloat(6, folhaPagamento.getInss());
        pstm.setFloat(7, folhaPagamento.getInsalubre());
        pstm.setFloat(8, folhaPagamento.getSalarioBruto());
        pstm.setFloat(9, folhaPagamento.getAdiantamento());
        pstm.setFloat(10, folhaPagamento.getOutros());
        pstm.setFloat(11, folhaPagamento.getDesconto());
        pstm.setFloat(12, folhaPagamento.getRed());
        pstm.setFloat(13, folhaPagamento.getLiquido());
        pstm.setString(14, folhaPagamento.getObservacao());
        pstm.setDate(15, new java.sql.Date(folhaPagamento.getMes().getTime()));
        pstm.setInt(16, folhaPagamento.getFuncionario().getIdfuncionario());
        pstm.setString(17, folhaPagamento.getCargo());
        pstm.setInt(18, folhaPagamento.getIdFolhaPagamento());
        pstm.executeUpdate();
    }

    public void excluir(FolhaPagamento folhaPagamento) throws SQLException {
        pstm = con.prepareStatement("delete from folhapagamento where idfolhapagamento=?");
        pstm.setInt(1, folhaPagamento.getIdFolhaPagamento());
        pstm.executeUpdate();
    }

    public FolhaPagamento pesquisar(FolhaPagamento folhaPagamento) throws SQLException {
        FolhaPagamento retorno = null;
        pstm = con.prepareStatement("select * from folhapagamento where idfolhapagamento=?");
        pstm.setInt(1, folhaPagamento.getIdFolhaPagamento());
        rs = pstm.executeQuery();
        if (rs.next()) {
            retorno = new FolhaPagamento();
            retorno.setIdFolhaPagamento(rs.getInt("idfolhapagamento"));
            retorno.setHorasMes(rs.getFloat("horasmes"));
            retorno.setHoras50(rs.getFloat("horas50"));
            retorno.setHoras100(rs.getFloat("horas100"));
            retorno.setHorasFaltosas(rs.getFloat("horasfaltosas"));
            retorno.setValeTransporte(rs.getFloat("valetransporte"));
            retorno.setInss(rs.getFloat("inss"));
            retorno.setInsalubre(rs.getFloat("insalubre"));
            retorno.setSalarioBruto(rs.getFloat("salariobruto"));
            retorno.setAdiantamento(rs.getFloat("adiantamento"));
            retorno.setOutros(rs.getFloat("outros"));
            retorno.setDesconto(rs.getFloat("desconto"));
            retorno.setRed(rs.getFloat("red"));
            retorno.setLiquido(rs.getFloat("liquido"));
            retorno.setObservacao(rs.getString("observacao"));
            retorno.setMes(rs.getDate("mes"));
            retorno.setCargo(rs.getString("cargo"));
            retorno.setFuncionario(funcionarioDao.getFuncionarioPorCodigo(rs.getInt("idfuncionario")));
            return retorno;

        }
        return null;
    }

    public List<FolhaPagamento> listar(String filtro) throws SQLException {
        List<FolhaPagamento> retornos = new ArrayList<FolhaPagamento>();
        FolhaPagamento retorno = null;

        String sql = "";

        if (filtro.length() > 0) {
            sql = "select * from folhapagamento where mes ilike '" + filtro + "%'";
        } else {
            sql = "select * from folhapagamento";
        }
        sql += " order by mes";
        pstm = con.prepareStatement(sql);
        rs = pstm.executeQuery();
        while (rs.next()) {
            retorno = new FolhaPagamento();
            retorno.setIdFolhaPagamento(rs.getInt("idfolhapagamento"));
            retorno.setHorasMes(rs.getFloat("horasmes"));
            retorno.setHoras50(rs.getFloat("horas50"));
            retorno.setHoras100(rs.getFloat("horas100"));
            retorno.setHorasFaltosas(rs.getFloat("horasfaltosas"));
            retorno.setValeTransporte(rs.getFloat("valetransporte"));
            retorno.setInss(rs.getFloat("inss"));
            retorno.setInsalubre(rs.getFloat("insalubre"));
            retorno.setSalarioBruto(rs.getFloat("salariobruto"));
            retorno.setAdiantamento(rs.getFloat("adiantamento"));
            retorno.setOutros(rs.getFloat("outros"));
            retorno.setDesconto(rs.getFloat("desconto"));
            retorno.setRed(rs.getFloat("red"));
            retorno.setLiquido(rs.getFloat("liquido"));
            retorno.setObservacao(rs.getString("observacao"));
            retorno.setMes(rs.getDate("mes"));
            retorno.setCargo(rs.getString("cargo"));
            retorno.setFuncionario(funcionarioDao.getFuncionarioPorCodigo(rs.getInt("idfuncionario")));
            retornos.add(retorno);
        }
        return retornos;
    }

    public FolhaPagamento getFolhaPagamentoPorMes(String descricao) throws SQLException {
        FolhaPagamento contasPagar = new FolhaPagamento();
        FolhaPagamento retorno = null;

        pstm = con.prepareStatement("select * from folhapagamento where mes=?");
        pstm.setString(1, descricao);
        rs = pstm.executeQuery();

        if (rs.next()) {

            retorno = new FolhaPagamento();
            retorno.setIdFolhaPagamento(rs.getInt("idfolhapagamento"));
            retorno.setHorasMes(rs.getFloat("horasmes"));
            retorno.setHoras50(rs.getFloat("horas50"));
            retorno.setHoras100(rs.getFloat("horas100"));
            retorno.setHorasFaltosas(rs.getFloat("horasfaltosas"));
            retorno.setValeTransporte(rs.getFloat("valetransporte"));
            retorno.setInss(rs.getFloat("inss"));
            retorno.setInsalubre(rs.getFloat("insalubre"));
            retorno.setSalarioBruto(rs.getFloat("salariobruto"));
            retorno.setAdiantamento(rs.getFloat("adiantamento"));
            retorno.setOutros(rs.getFloat("outros"));
            retorno.setDesconto(rs.getFloat("desconto"));
            retorno.setRed(rs.getFloat("red"));
            retorno.setLiquido(rs.getFloat("liquido"));
            retorno.setObservacao(rs.getString("observacao"));
            retorno.setMes(rs.getDate("mes"));
            retorno.setCargo(rs.getString("cargo"));
            retorno.setFuncionario(funcionarioDao.getFuncionarioPorCodigo(rs.getInt("idfuncionario")));
        }
        return retorno;

    }

    public FolhaPagamento getPlanosPorCodigo(int idFolhaPagamento) throws SQLException {

        FolhaPagamento retorno = null;

        pstm = con.prepareStatement("select * from folhapagamento where idfolhapagamento=?");
        pstm.setInt(1, idFolhaPagamento);
        rs = pstm.executeQuery();

        if (rs.next()) {

            retorno = new FolhaPagamento();
            retorno.setIdFolhaPagamento(rs.getInt("idfolhapagamento"));
            retorno.setHorasMes(rs.getFloat("horasmes"));
            retorno.setHoras50(rs.getFloat("horas50"));
            retorno.setHoras100(rs.getFloat("horas100"));
            retorno.setHorasFaltosas(rs.getFloat("horasfaltosas"));
            retorno.setValeTransporte(rs.getFloat("valetransporte"));
            retorno.setInss(rs.getFloat("inss"));
            retorno.setInsalubre(rs.getFloat("insalubre"));
            retorno.setSalarioBruto(rs.getFloat("salariobruto"));
            retorno.setAdiantamento(rs.getFloat("adiantamento"));
            retorno.setOutros(rs.getFloat("outros"));
            retorno.setDesconto(rs.getFloat("desconto"));
            retorno.setRed(rs.getFloat("red"));
            retorno.setLiquido(rs.getFloat("liquido"));
            retorno.setObservacao(rs.getString("observacao"));
            retorno.setMes(rs.getDate("mes"));
            retorno.setCargo(rs.getString("cargo"));
            retorno.setFuncionario(funcionarioDao.getFuncionarioPorCodigo(rs.getInt("idfuncionario")));
        }
        return retorno;
    }

    public FolhaPagamento pesquisarValorHora(FolhaPagamento folhaPagamento) throws SQLException {
        FolhaPagamento retorno = null;

        pstm = con.prepareStatement("select valorhora from tipofuncionario where cargo =?");
        pstm.setString(1, folhaPagamento.getCargo());
        rs = pstm.executeQuery();
        if (rs.next()) {
            retorno = new FolhaPagamento();
            retorno.setValorHora(rs.getFloat("valorhora"));
        }
        return retorno;
    }

    public FolhaPagamento pesquisarValorHora50(FolhaPagamento folhaPagamento) throws SQLException {
        FolhaPagamento retorno = null;

        pstm = con.prepareStatement("select valorhora50 from valor50pcento where cargofun =?");
        pstm.setString(1, folhaPagamento.getCargo());
        rs = pstm.executeQuery();
        if (rs.next()) {
            retorno = new FolhaPagamento();
            retorno.setValorHora50(rs.getFloat("valorhora50"));
        }
        return retorno;
    }

    public FolhaPagamento pesquisarValorHora100(FolhaPagamento folhaPagamento) throws SQLException {
        FolhaPagamento retorno = null;

        pstm = con.prepareStatement("select valorhora100 from valor100pcento where cargofun =?");
        pstm.setString(1, folhaPagamento.getCargo());
        rs = pstm.executeQuery();
        if (rs.next()) {
            retorno = new FolhaPagamento();
            retorno.setValorHora100(rs.getFloat("valorhora100"));
        }
        return retorno;
    }

}
