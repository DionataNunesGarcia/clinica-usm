package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Convenio;

public class ConvenioDAO {

    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;
    private PlanosDAO planosDao;

    public ConvenioDAO() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
        con = DriverManager.getConnection("jdbc:postgresql://localhost/clinicausm", "postgres", "postgres");
        planosDao = new PlanosDAO();
    }

    public void salvar(Convenio convenio) throws SQLException {
        //carrega o conector do PostgreSql é registra no gerenciador de dispositivos

        pstm = con.prepareStatement("insert into convenio (idconvenio,nome,observacao,responsavel,cargo,agencia,telefone,email,data_vencimento,idplano) values (nextval('convenio_seq'),?,?,?,?,?,?,?,?,?)");
        pstm.setString(1, convenio.getNome());
        pstm.setString(2, convenio.getObservacao());
        pstm.setString(3, convenio.getResponsavel());
        pstm.setString(4, convenio.getCargo());
        pstm.setString(5, convenio.getAgencia());
        pstm.setString(6, convenio.getTelefone());
        pstm.setString(7, convenio.getEmail());
        pstm.setDate(8, new java.sql.Date(convenio.getData_vencimento().getTime()));
        pstm.setInt(9, convenio.getPlanos().getIdplano());
        pstm.executeUpdate();

    }

    public void alterar(Convenio convenio) throws SQLException {
        pstm = con.prepareStatement("update convenio set nome=?,observacao=?,responsavel=?,cargo=?,agencia=?,telefone=?,email=?,data_vencimento=?,idplano=? where idconvenio=?");
        pstm.setString(1, convenio.getNome());
        pstm.setString(2, convenio.getObservacao());
        pstm.setString(3, convenio.getResponsavel());
        pstm.setString(4, convenio.getCargo());
        pstm.setString(5, convenio.getAgencia());
        pstm.setString(6, convenio.getTelefone());
        pstm.setString(7, convenio.getEmail());
        pstm.setDate(8, new java.sql.Date(convenio.getData_vencimento().getTime()));
        pstm.setInt(9, convenio.getPlanos().getIdplano());
        pstm.setInt(10, convenio.getIdconvenio());
        pstm.executeUpdate();

    }

    public void excluir(Convenio convenio) throws SQLException {
        pstm = con.prepareStatement("delete from convenio where idconvenio=?");
        pstm.setInt(1, convenio.getIdconvenio());
        pstm.executeUpdate();
    }

    public Convenio pesquisar(Convenio convenio) throws SQLException {
        Convenio retorno = null;
        pstm = con.prepareStatement("select * from convenio where idconvenio=?");
        pstm.setInt(1, convenio.getIdconvenio());
        rs = pstm.executeQuery();
        if (rs.next()) {
            retorno = new Convenio();
            retorno.setIdconvenio(rs.getInt("idconvenio"));
            retorno.setNome(rs.getString("nome"));
            retorno.setData_vencimento(rs.getDate("data_vencimento"));
            retorno.setObservacao(rs.getString("observacao"));
            retorno.setResponsavel(rs.getString("responsavel"));
            retorno.setCargo(rs.getString("cargo"));
            retorno.setAgencia(rs.getString("agencia"));
            retorno.setTelefone(rs.getString("telefone"));
            retorno.setEmail(rs.getString("email"));
            return retorno;
        }
        return null;
    }

    public List<Convenio> listar(String filtro) throws SQLException {
        List<Convenio> retornos = new ArrayList<Convenio>();
        Convenio retorno = null;

        String sql = "";

        if (filtro.length() > 0) {
            sql = "select * from convenio where nome ilike '" + filtro + "%'";
        } else {
            sql = "select * from convenio";
        }
        sql += " order by nome";
        pstm = con.prepareStatement(sql);
        rs = pstm.executeQuery();
        while (rs.next()) {
            retorno = new Convenio();
            retorno.setIdconvenio(rs.getInt("idconvenio"));
            retorno.setNome(rs.getString("nome"));
            retorno.setData_vencimento(rs.getDate("data_vencimento"));
            retorno.setObservacao(rs.getString("observacao"));
            retorno.setResponsavel(rs.getString("responsavel"));
            retorno.setCargo(rs.getString("cargo"));
            retorno.setAgencia(rs.getString("agencia"));
            retorno.setTelefone(rs.getString("telefone"));
            retorno.setEmail(rs.getString("email"));
            retorno.setPlanos(planosDao.getPlanosPorCodigo(rs.getInt("idplano")));
            retornos.add(retorno);
        }
        return retornos;
    }

    public Convenio getConvenioPorNome(String nome) throws SQLException {
        Convenio convenio = new Convenio();
        Convenio retorno = null;

        pstm = con.prepareStatement("select * from convenio where nome=?");
        pstm.setString(1, nome);
        rs = pstm.executeQuery();

        if (rs.next()) {
            retorno = new Convenio();
            retorno.setIdconvenio(rs.getInt("idconvenio"));
            retorno.setNome(rs.getString("nome"));
            retorno.setData_vencimento(rs.getDate("data_vencimento"));
            retorno.setObservacao(rs.getString("observacao"));
            retorno.setResponsavel(rs.getString("responsavel"));
            retorno.setCargo(rs.getString("cargo"));
            retorno.setAgencia(rs.getString("agencia"));
            retorno.setTelefone(rs.getString("telefone"));
            retorno.setEmail(rs.getString("email"));
            retorno.setPlanos(planosDao.getPlanosPorCodigo(rs.getInt("idplano")));
        }
        return retorno;
    }

    public Convenio getConvenioPorCodigo(int idconvenio) throws SQLException {
        Convenio convenio = new Convenio();
        pstm = con.prepareStatement("select * from convenio where idconvenio=?");
        pstm.setInt(1, idconvenio);
        rs = pstm.executeQuery();
        if (rs.next()) {
            convenio.setIdconvenio(rs.getInt("idconvenio"));
            convenio.setNome(rs.getString("nome"));
            convenio.setObservacao(rs.getString("observacao"));
            convenio.setResponsavel(rs.getString("responsavel"));
            convenio.setCargo(rs.getString("cargo"));
            convenio.setAgencia(rs.getString("agencia"));
            convenio.setTelefone(rs.getString("telefone"));
            convenio.setEmail(rs.getString("email"));
            convenio.setData_vencimento(rs.getDate("data_vencimento"));
            convenio.setPlanos(planosDao.getPlanosPorCodigo(rs.getInt("idplano")));
        }
        return convenio;
    }
    
    public List<Convenio> getConvenio () throws SQLException {
        List<Convenio> convenios = new ArrayList<Convenio>();
        pstm = con.prepareStatement("select * from order by nome");
        rs = pstm.executeQuery();
        while (rs.next()){
            Convenio convenio = new Convenio();
            convenio.setIdconvenio(rs.getInt("idconvenio"));
            convenio.setNome(rs.getString("nome"));
            convenio.setObservacao(rs.getString("observacao"));
            convenio.setResponsavel(rs.getString("responsavel"));
            convenio.setCargo(rs.getString("cargo"));
            convenio.setAgencia(rs.getString("agencia"));
            convenio.setTelefone(rs.getString("telefone"));
            convenio.setEmail(rs.getString("email"));
            convenio.setData_vencimento(rs.getDate("data_vencimento"));
            convenio.setPlanos(planosDao.getPlanosPorCodigo(rs.getInt("idplano")));
        }
        return convenios;
    }
}
