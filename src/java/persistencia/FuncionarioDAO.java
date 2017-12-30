/*
 * To change this template, choose Tools | Templates
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
import modelo.Funcionario;

/**
 *
 * @author DYONATHAN
 */
public class FuncionarioDAO {

    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;
    private CargoFuncionarioDAO cargoFuncionarioDao;

    public FuncionarioDAO() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
        con = DriverManager.getConnection("jdbc:postgresql://localhost/clinicausm", "postgres", "postgres");
        cargoFuncionarioDao = new CargoFuncionarioDAO();
    }

    public void salvar(Funcionario funcionario) throws SQLException {
        //carrega o conector do PostgreSql é registra no gerenciador de dispositivos

        pstm = con.prepareStatement("insert into funcionario (idfuncionario,nome,cpf,data_nascimento,email,"
                + "cep,endereco,numero,complemento,bairro,cidade,estado,telefone,valorhora50,cargahoraria,"
                + "valorhora,tipo,usuario,senha,data_admissao,data_demissao,ativo,idtipofuncionario,valorhora100) values"
                + "(nextval('fun_seq'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        pstm.setString(1, funcionario.getNome());
        pstm.setString(2, funcionario.getCpf());
        pstm.setDate(3, new java.sql.Date(funcionario.getDatanasc().getTime()));
        pstm.setString(4, funcionario.getEmail());
        pstm.setInt(5, funcionario.getCep());
        pstm.setString(6, funcionario.getEndereco());
        pstm.setInt(7, funcionario.getNumero());
        pstm.setString(8, funcionario.getComplemento());
        pstm.setString(9, funcionario.getBairro());
        pstm.setString(10, funcionario.getCidade());
        pstm.setString(11, funcionario.getUf());
        pstm.setString(12, funcionario.getTelefone());
        pstm.setFloat(13, funcionario.getValorHora50());
        pstm.setInt(14, funcionario.getCargahoraria());
        pstm.setFloat(15, funcionario.getValorHora50());
        pstm.setString(16, funcionario.getTipo());
        pstm.setString(17, funcionario.getUsuario());
        pstm.setString(18, funcionario.getSenha());
        pstm.setDate(19, new java.sql.Date(funcionario.getDataAdmissao().getTime()));
        pstm.setDate(20, new java.sql.Date(funcionario.getDataDemissao().getTime()));
        pstm.setBoolean(21, funcionario.isAtivo());
        pstm.setInt(22,funcionario.getCargoFuncionario().getIdCargoFuncionario());
        pstm.setFloat(23, funcionario.getValorHora100());
        pstm.executeUpdate();

    }

    public void alterar(Funcionario funcionario) throws SQLException {
        pstm = con.prepareStatement(
                "update funcionario set nome=?,cpf=?,data_nascimento=?,email=?, "
                + " cep=?,endereco=?,numero=?,complemento=?,bairro=?,cidade=?,estado=?,telefone=?,"
                + "valorhora50=?,cargahoraria=?,valorhora=?,tipo=?,usuario=?,senha=?,data_admissao=?,"
                + "data_demissao=?,ativo=?,idtipofuncionario=?,valorhora100=? where idfuncionario=?");
        pstm.setString(1, funcionario.getNome());
        pstm.setString(2, funcionario.getCpf());
        pstm.setDate(3, new java.sql.Date(funcionario.getDatanasc().getTime()));
        pstm.setString(4, funcionario.getEmail());
        pstm.setInt(5, funcionario.getCep());
        pstm.setString(6, funcionario.getEndereco());
        pstm.setInt(7, funcionario.getNumero());
        pstm.setString(8, funcionario.getComplemento());
        pstm.setString(9, funcionario.getBairro());
        pstm.setString(10, funcionario.getCidade());
        pstm.setString(11, funcionario.getUf());
        pstm.setString(12, funcionario.getTelefone());
        pstm.setFloat(13, funcionario.getValorHora50());
        pstm.setInt(14, funcionario.getCargahoraria());
        pstm.setFloat(15, funcionario.getValorHora50());
        pstm.setString(16, funcionario.getTipo());
        pstm.setString(17, funcionario.getUsuario());
        pstm.setString(18, funcionario.getSenha());
        pstm.setDate(19, new java.sql.Date(funcionario.getDataAdmissao().getTime()));
        pstm.setDate(20, new java.sql.Date(funcionario.getDataDemissao().getTime()));
        pstm.setBoolean(21, funcionario.isAtivo());
        pstm.setInt(22,funcionario.getCargoFuncionario().getIdCargoFuncionario());
        pstm.setFloat(23, funcionario.getValorHora100());
        pstm.setInt(24, funcionario.getIdfuncionario());
        pstm.executeUpdate();

    }

    public void excluir(Funcionario funcionario) throws SQLException {
        pstm = con.prepareStatement(
                "delete from funcionario where idfuncionario=?");
        pstm.setInt(1, funcionario.getIdfuncionario());
        pstm.executeUpdate();

    }

    public Funcionario pesquisar(Funcionario funcionario) throws SQLException {
        Funcionario retorno = null;
        pstm = con.prepareStatement("select * from funcionario where idfuncionario=?");
        pstm.setInt(1, funcionario.getIdfuncionario());
        rs = pstm.executeQuery();
        if (rs.next()) {
            retorno = new Funcionario();
            retorno.setIdfuncionario(rs.getInt("idfuncionario"));
            retorno.setNome(rs.getString("nome"));
            retorno.setTelefone(rs.getString("telefone"));
            retorno.setDatanasc(rs.getDate("data_nascimento"));
            retorno.setCpf(rs.getString("cpf"));
            retorno.setEndereco(rs.getString("endereco"));
            retorno.setNumero(rs.getInt("numero"));
            retorno.setComplemento(rs.getString("complemento"));
            retorno.setBairro(rs.getString("bairro"));
            retorno.setCep(rs.getInt("cep"));
            retorno.setCidade(rs.getString("cidade"));
            retorno.setUf(rs.getString("estado"));
            retorno.setEmail(rs.getString("email"));
            retorno.setCargahoraria(rs.getInt("cargahoraria"));
            retorno.setValorHora(rs.getFloat("valorhora"));
            retorno.setValorhora50(rs.getFloat("valorhora50"));
            retorno.setValorhora100(rs.getFloat("valorhora100"));
            retorno.setUsuario(rs.getString("usuario"));
            retorno.setSenha(rs.getString("senha"));
            retorno.setDataAdmissao(rs.getDate("data_admissao"));
            retorno.setDataDemissao(rs.getDate("data_demissao"));
            retorno.setAtivo(rs.getBoolean("ativo"));
            retorno.setCargoFuncionario(cargoFuncionarioDao.getCargoFuncionarioPorCodigo(rs.getInt("idtipofuncionario")));
        }
        return retorno;
    }

    public Funcionario logar(Funcionario funcionario) throws SQLException {
        Funcionario retorno = null;
        pstm = con.prepareStatement("select * from funcionario where usuario=? and senha=?");
        pstm.setString(1, funcionario.getEmail());
        pstm.setString(2, funcionario.getSenha());
        rs = pstm.executeQuery();
        if (rs.next()) {
            retorno = new Funcionario();
            retorno.setIdfuncionario(rs.getInt("idfuncionario"));
            retorno.setNome(rs.getString("nome"));
            retorno.setTelefone(rs.getString("telefone"));
            retorno.setDatanasc(rs.getDate("data_nascimento"));
            retorno.setCpf(rs.getString("cpf"));
            retorno.setEndereco(rs.getString("endereco"));
            retorno.setNumero(rs.getInt("numero"));
            retorno.setComplemento(rs.getString("complemento"));
            retorno.setBairro(rs.getString("bairro"));
            retorno.setCep(rs.getInt("cep"));
            retorno.setCidade(rs.getString("cidade"));
            retorno.setUf(rs.getString("estado"));
            retorno.setEmail(rs.getString("email"));
            retorno.setCargahoraria(rs.getInt("cargahoraria"));
            retorno.setValorHora(rs.getFloat("valorhora"));
            retorno.setValorhora50(rs.getFloat("valorhora50"));
            retorno.setValorhora100(rs.getFloat("valorhora100"));
            retorno.setUsuario(rs.getString("usuario"));
            retorno.setSenha(rs.getString("senha"));
            retorno.setDataAdmissao(rs.getDate("data_admissao"));
            retorno.setDataDemissao(rs.getDate("data_demissao"));
            retorno.setAtivo(rs.getBoolean("ativo"));
            retorno.setCargoFuncionario(cargoFuncionarioDao.getCargoFuncionarioPorCodigo(rs.getInt("idtipofuncionario")));
        }
        return retorno;
    }

    public List<Funcionario> listar(String filtro) throws SQLException {
        List<Funcionario> retornos = new ArrayList<Funcionario>();
        Funcionario retorno = null;

        String sql = "";

        if (filtro.length() > 0) {
            sql = "select * from funcionario where nome ilike '" + filtro + "%'";
        } else {
            sql = "select * from funcionario";
        }
        sql += " order by nome ";
        pstm = con.prepareStatement(sql);
        rs = pstm.executeQuery();
        while (rs.next()) {

            retorno = new Funcionario();
            retorno.setIdfuncionario(rs.getInt("idfuncionario"));
            retorno.setNome(rs.getString("nome"));
            retorno.setTelefone(rs.getString("telefone"));
            retorno.setDatanasc(rs.getDate("data_nascimento"));
            retorno.setCpf(rs.getString("cpf"));
            retorno.setEndereco(rs.getString("endereco"));
            retorno.setNumero(rs.getInt("numero"));
            retorno.setComplemento(rs.getString("complemento"));
            retorno.setBairro(rs.getString("bairro"));
            retorno.setCep(rs.getInt("cep"));
            retorno.setCidade(rs.getString("cidade"));
            retorno.setUf(rs.getString("estado"));
            retorno.setEmail(rs.getString("email"));
            retorno.setCargahoraria(rs.getInt("cargahoraria"));
            retorno.setValorHora(rs.getFloat("valorhora"));
            retorno.setValorhora50(rs.getFloat("valorhora50"));
            retorno.setValorhora100(rs.getFloat("valorhora100"));
            retorno.setUsuario(rs.getString("usuario"));
            retorno.setSenha(rs.getString("senha"));
            retorno.setDataAdmissao(rs.getDate("data_admissao"));
            retorno.setDataDemissao(rs.getDate("data_demissao"));
            retorno.setAtivo(rs.getBoolean("ativo"));
            retorno.setCargoFuncionario(cargoFuncionarioDao.getCargoFuncionarioPorCodigo(rs.getInt("idtipofuncionario")));
            retornos.add(retorno);

        }
        return retornos;
    }

    public Funcionario getFuncionarioPorNome(String nome) throws SQLException {

        Funcionario retorno = null;

        pstm = con.prepareStatement("select * from funcionario where nome=?");
        pstm.setString(1, nome);
        rs = pstm.executeQuery();

        if (rs.next()) {

            retorno = new Funcionario();
            retorno.setIdfuncionario(rs.getInt("idfuncionario"));
            retorno.setNome(rs.getString("nome"));
            retorno.setTelefone(rs.getString("telefone"));
            retorno.setDatanasc(rs.getDate("data_nascimento"));
            retorno.setCpf(rs.getString("cpf"));
            retorno.setEndereco(rs.getString("endereco"));
            retorno.setNumero(rs.getInt("numero"));
            retorno.setComplemento(rs.getString("complemento"));
            retorno.setBairro(rs.getString("bairro"));
            retorno.setCep(rs.getInt("cep"));
            retorno.setCidade(rs.getString("cidade"));
            retorno.setUf(rs.getString("estado"));
            retorno.setEmail(rs.getString("email"));
            retorno.setCargahoraria(rs.getInt("cargahoraria"));
            retorno.setValorHora(rs.getFloat("valorhora"));
            retorno.setValorhora50(rs.getFloat("valorhora50"));
            retorno.setValorhora100(rs.getFloat("valorhora100"));
            retorno.setUsuario(rs.getString("usuario"));
            retorno.setSenha(rs.getString("senha"));
            retorno.setDataAdmissao(rs.getDate("data_admissao"));
            retorno.setDataDemissao(rs.getDate("data_demissao"));
            retorno.setAtivo(rs.getBoolean("ativo"));
            retorno.setCargoFuncionario(cargoFuncionarioDao.getCargoFuncionarioPorCodigo(rs.getInt("idtipofuncionario")));
        }
        return retorno;

    }

    public Funcionario getFuncionarioPorCodigo(int idFornecedor) throws SQLException {

        Funcionario retorno = null;

        pstm = con.prepareStatement("select * from fornecedor where idfornecedor=?");
        pstm.setInt(1, idFornecedor);
        rs = pstm.executeQuery();

        if (rs.next()) {

            retorno = new Funcionario();
            retorno.setIdfuncionario(rs.getInt("idfuncionario"));
            retorno.setNome(rs.getString("nome"));
            retorno.setTelefone(rs.getString("telefone"));
            retorno.setDatanasc(rs.getDate("data_nascimento"));
            retorno.setCpf(rs.getString("cpf"));
            retorno.setEndereco(rs.getString("endereco"));
            retorno.setNumero(rs.getInt("numero"));
            retorno.setComplemento(rs.getString("complemento"));
            retorno.setBairro(rs.getString("bairro"));
            retorno.setCep(rs.getInt("cep"));
            retorno.setCidade(rs.getString("cidade"));
            retorno.setUf(rs.getString("estado"));
            retorno.setEmail(rs.getString("email"));
            retorno.setCargahoraria(rs.getInt("cargahoraria"));
            retorno.setValorHora(rs.getFloat("valorhora"));
            retorno.setValorhora50(rs.getFloat("valorhora50"));
            retorno.setValorhora100(rs.getFloat("valorhora100"));
            retorno.setUsuario(rs.getString("usuario"));
            retorno.setSenha(rs.getString("senha"));
            retorno.setDataAdmissao(rs.getDate("data_admissao"));
            retorno.setDataDemissao(rs.getDate("data_demissao"));
            retorno.setAtivo(rs.getBoolean("ativo"));
            retorno.setCargoFuncionario(cargoFuncionarioDao.getCargoFuncionarioPorCodigo(rs.getInt("idtipofuncionario")));
        }
        return retorno;

    }
    
    public Funcionario pesquisarValorHora(Funcionario funcionario) throws SQLException { 
        Funcionario retorno = null;
        
        pstm = con.prepareStatement("select valorhora from funcionario where nome =?");
        pstm.setString(1, funcionario.getNome());
        rs = pstm.executeQuery();
        if (rs.next()){
            retorno = new Funcionario();
            retorno.setValorHora(rs.getFloat("valorhora"));
        }
        return retorno;
    }
    public Funcionario pesquisarValorHora50(Funcionario funcionario) throws SQLException { 
        Funcionario retorno = null;
        
        pstm = con.prepareStatement("select valorhora50 from funcionario where nome =?");
        pstm.setString(1, funcionario.getNome());
        rs = pstm.executeQuery();
        if (rs.next()){
            retorno = new Funcionario();
            retorno.setValorHora50(rs.getFloat("valorhora50"));
        }
        return retorno;
    }
    public Funcionario pesquisarValorHora100(Funcionario funcionario) throws SQLException { 
        Funcionario retorno = null;
        
        pstm = con.prepareStatement("select valorhora100 from funcionario where nome =?");
        pstm.setString(1, funcionario.getNome());
        rs = pstm.executeQuery();
        if (rs.next()){
            retorno = new Funcionario();
            retorno.setValorHora100(rs.getFloat("valorhora100"));
        }
        return retorno;
    }
    
    
}
