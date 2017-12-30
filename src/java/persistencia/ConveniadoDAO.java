package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Conveniado;

public class ConveniadoDAO {

    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;
    private ClienteDAO clienteDao;

    public ConveniadoDAO() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
        con = DriverManager.getConnection("jdbc:postgresql://localhost/clinicausm", "postgres", "postgres");
        clienteDao = new ClienteDAO();
    }

    public void salvar(Conveniado conveniado) throws SQLException {
        //carrega o conector do PostgreSql é registra no gerenciador de dispositivos

        pstm = con.prepareStatement("insert into conveniado (idconveniado,nome,cpf,rg,sexo,telefone,"
                + "celular,observacao,data_nascimento,idcliente) values (nextval('conveniado_seq'),?,?,?,?,?,?,?,?,?)");
        pstm.setString(1, conveniado.getNome());
        pstm.setString(2, conveniado.getCpf());
        pstm.setString(3, conveniado.getRg());
        pstm.setString(4, conveniado.getSexo());
        pstm.setString(5, conveniado.getTelefone());
        pstm.setString(6, conveniado.getCelular());
        pstm.setString(7, conveniado.getObservacao());
        pstm.setDate(8, new java.sql.Date(conveniado.getData_nascimento().getTime()));
        pstm.setInt(9, conveniado.getCliente().getIdcliente());
        
        pstm.executeUpdate();
    }

    public void alterar(Conveniado conveniado) throws SQLException {
        pstm = con.prepareStatement("update conveniado set nome=?,cpf=?,rg=?,sexo=?,telefone=?,"
                + "celular=?,observacao=?,data_nascimento=?,idcliente=? where idconveniado=?");
        pstm.setString(1, conveniado.getNome());
        pstm.setString(2, conveniado.getCpf());
        pstm.setString(3, conveniado.getRg());
        pstm.setString(4, conveniado.getSexo());
        pstm.setString(5, conveniado.getTelefone());
        pstm.setString(6, conveniado.getCelular());
        pstm.setString(7, conveniado.getObservacao());
        pstm.setDate(8, new java.sql.Date(conveniado.getData_nascimento().getTime()));
        pstm.setInt(9, conveniado.getCliente().getIdcliente());
        pstm.setInt(10, conveniado.getIdconveniado());
        pstm.executeUpdate();

    }

    public void excluir(Conveniado conveniado) throws SQLException {
        pstm = con.prepareStatement(
                "delete from conveniado where idconveniado=?");
        pstm.setInt(1, conveniado.getIdconveniado());
        pstm.executeUpdate();
    }

    public Conveniado pesquisar(Conveniado conveniado) throws SQLException {
        Conveniado retorno = null;
        pstm = con.prepareStatement("select * from conveniado where idconveniado=?");
        pstm.setInt(1, conveniado.getIdconveniado());
        rs = pstm.executeQuery();
        if (rs.next()) {
            retorno = new Conveniado();
            retorno.setIdconveniado(rs.getInt("idconveniado"));
            retorno.setNome(rs.getString("nome"));
            retorno.setCpf(rs.getString("cpf"));
            retorno.setRg(rs.getString("rg"));
            retorno.setData_nascimento(rs.getDate("data_nascimento"));
            retorno.setSexo(rs.getString("sexo"));
            retorno.setTelefone(rs.getString("telefone"));
            retorno.setCelular(rs.getString("celular"));
            retorno.setObservacao(rs.getString("observacao"));

            return retorno;

        }
        return null;
    }

    public List<Conveniado> listar(String filtro) throws SQLException {
        List<Conveniado> retornos = new ArrayList<Conveniado>();
        Conveniado retorno = null;

        String sql = "";

        if (filtro.length() > 0) {
            sql = "select * from conveniado where nome ilike '" + filtro + "%'";
        } else {
            sql = "select * from conveniado";
        }
        sql += " order by nome";
        pstm = con.prepareStatement(sql);
        rs = pstm.executeQuery();
        while (rs.next()) {
            retorno = new Conveniado();
            retorno.setIdconveniado(rs.getInt("idconveniado"));
            retorno.setNome(rs.getString("nome"));
            retorno.setCpf(rs.getString("cpf"));
            retorno.setRg(rs.getString("rg"));
            retorno.setData_nascimento(rs.getDate("data_nascimento"));
            retorno.setSexo(rs.getString("sexo"));
            retorno.setTelefone(rs.getString("telefone"));
            retorno.setCelular(rs.getString("celular"));
            retorno.setObservacao(rs.getString("observacao"));

            retornos.add(retorno);
        }
        return retornos;
    }

    public Conveniado getConveniadoPorNome(String nome) throws SQLException {

        Conveniado retorno = null;

        pstm = con.prepareStatement("select * from conveniado where nome=?");
        pstm.setString(1, nome);
        rs = pstm.executeQuery();

        if (rs.next()) {

            retorno = new Conveniado();
            retorno.setIdconveniado(rs.getInt("idconveniado"));
            retorno.setNome(rs.getString("nome"));
            retorno.setCpf(rs.getString("cpf"));
            retorno.setRg(rs.getString("rg"));
            retorno.setData_nascimento(rs.getDate("data_nascimento"));
            retorno.setSexo(rs.getString("sexo"));
            retorno.setTelefone(rs.getString("telefone"));
            retorno.setCelular(rs.getString("celular"));
            retorno.setObservacao(rs.getString("observacao"));

            
        }
        return retorno;

    }
}
