package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;


public class ClienteDAO {

    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;
    private ConvenioDAO convenioDao;
    private CategoriaDAO categoriaDao;
    private FormaCobrancaDAO formacobrancaDao;
    
    
    public ClienteDAO() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
        con = DriverManager.getConnection("jdbc:postgresql://localhost/clinicausm", "postgres", "postgres");
        convenioDao = new ConvenioDAO();
        categoriaDao = new CategoriaDAO();
        formacobrancaDao = new FormaCobrancaDAO();
    }

    public void salvar(Cliente cliente) throws SQLException {
        //carrega o conector do PostgreSql é registra no gerenciador de dispositivos

        pstm = con.prepareStatement("insert into cliente (idcliente,nome,endereco,bairro,cidade,estado,cep,"
                + "sexo,telefone,celular,cpf,rg,titular,status,email,numero,complemento,data_nascimento,"
                + "data_cobranca,idconvenio,idcobranca,idcategoria) values (nextval('cliente_seq'),?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        pstm.setString(1, cliente.getNome());
        pstm.setString(2, cliente.getEndereco());
        pstm.setString(3, cliente.getBairro());
        pstm.setString(4, cliente.getCidade());
        pstm.setString(5, cliente.getEstado());
        pstm.setString(6, cliente.getCep());
        pstm.setString(7, cliente.getSexo());
        pstm.setString(8, cliente.getTelefone());
        pstm.setString(9, cliente.getCelular());
        pstm.setString(10, cliente.getCpf());
        pstm.setString(11, cliente.getRg());
        pstm.setBoolean(12, cliente.isTitular());
        pstm.setString(13, cliente.getStatus());
        pstm.setString(14, cliente.getEmail());
        pstm.setInt(15, cliente.getNumero());
        pstm.setString(16, cliente.getComplemento());
        pstm.setDate(17, new java.sql.Date(cliente.getData_nascimento().getTime()));
        pstm.setDate(18, new java.sql.Date(cliente.getData_cobranca().getTime()));
        pstm.setInt(19, cliente.getConvenio().getIdconvenio());
        pstm.setInt(20, cliente.getFormaCobranca().getIdcobranca());
        pstm.setInt(21, cliente.getCategoriaUsm().getIdcategoria());
        pstm.executeUpdate();

    }

    public void alterar(Cliente cliente) throws SQLException {
        pstm = con.prepareStatement("update cliente set nome=?,endereco=?,bairro=?,cidade=?,estado=?,cep=?,"
                + "sexo=?,telefone=?,celular=?,cpf=?,rg=?,titular=?,status=?,email=?,numero=?,complemento=?,"
                + "data_nascimento=?,data_cobranca=?,idconvenio=?,idcobranca=?,idcategoria=? where idcliente=?");
        pstm.setString(1, cliente.getNome());
        pstm.setString(2, cliente.getEndereco());
        pstm.setString(3, cliente.getBairro());
        pstm.setString(4, cliente.getCidade());
        pstm.setString(5, cliente.getEstado());
        pstm.setString(6, cliente.getCep());
        pstm.setString(7, cliente.getSexo());
        pstm.setString(8, cliente.getTelefone());
        pstm.setString(9, cliente.getCelular());
        pstm.setString(10, cliente.getCpf());
        pstm.setString(11, cliente.getRg());
        pstm.setBoolean(12, cliente.isTitular());
        pstm.setString(13, cliente.getStatus());
        pstm.setString(14, cliente.getEmail());
        pstm.setInt(15, cliente.getNumero());
        pstm.setString(16, cliente.getComplemento());
        pstm.setDate(17, new java.sql.Date(cliente.getData_nascimento().getTime()));
        pstm.setDate(18, new java.sql.Date(cliente.getData_cobranca().getTime()));
        pstm.setInt(19, cliente.getConvenio().getIdconvenio());
        pstm.setInt(20, cliente.getFormaCobranca().getIdcobranca());
        pstm.setInt(21, cliente.getCategoriaUsm().getIdcategoria());
        pstm.setInt(22, cliente.getIdcliente());
        pstm.executeUpdate();

    }

    public void excluir(Cliente cliente) throws SQLException {
        pstm = con.prepareStatement(
                "delete from cliente where idcliente=?");
        pstm.setInt(1, cliente.getIdcliente());
        pstm.executeUpdate();

    }

    public List<Cliente> listar(String filtro) throws SQLException {
        List<Cliente> retornos = new ArrayList<Cliente>();
        Cliente retorno = null;

        String sql = "";

        if (filtro.length() > 0) {
            sql = "select * from cliente where nome ilike '" + filtro + "%'";
        } else {
            sql = "select * from cliente";
        }
        sql += " order by nome";
        pstm = con.prepareStatement(sql);
        rs = pstm.executeQuery();
        while (rs.next()) {

            retorno = new Cliente();
            retorno.setIdcliente(rs.getInt("idcliente"));
            retorno.setNome(rs.getString("nome"));
            retorno.setEndereco(rs.getString("endereco"));
            retorno.setBairro(rs.getString("bairro"));
            retorno.setData_nascimento(rs.getDate("data_nascimento"));
            retorno.setCidade(rs.getString("cidade"));
            retorno.setEstado(rs.getString("estado"));
            retorno.setCep(rs.getString("cep"));
            retorno.setSexo(rs.getString("sexo"));
            retorno.setTelefone(rs.getString("telefone"));
            retorno.setCelular(rs.getString("celular"));
            retorno.setCpf(rs.getString("cpf"));
            retorno.setRg(rs.getString("rg"));
            retorno.setTitular(rs.getBoolean("titular"));
            retorno.setData_cobranca(rs.getDate("data_cobranca"));
            retorno.setStatus(rs.getString("status"));
            retorno.setEmail(rs.getString("email"));
            retorno.setNumero(rs.getInt("numero"));
            retorno.setComplemento(rs.getString("complemento"));
            retorno.setConvenio(convenioDao.getConvenioPorCodigo(rs.getInt("idconvenio")));
            retorno.setFormaCobranca(formacobrancaDao.getFormaCobrancaPorCodigo(rs.getInt("idcobranca")));
            retorno.setCategoriaUsm(categoriaDao.getCategoriaPorCodigo(rs.getInt("idcategoria")));

            retornos.add(retorno);

        }
        return retornos;
    }
    public List<Cliente> listarFone(String filtroFone) throws SQLException {
        List<Cliente> retornos = new ArrayList<Cliente>();
        Cliente retorno = null;

        String sql = "";

        if (filtroFone.length() > 0) {
            sql = "select * from cliente where telefone ilike '" + filtroFone + "%'";
        } else {
            sql = "select * from cliente";
        }
        sql += " order by nome";
        pstm = con.prepareStatement(sql);
        rs = pstm.executeQuery();
        while (rs.next()) {

            retorno = new Cliente();
            retorno.setIdcliente(rs.getInt("idcliente"));
            retorno.setNome(rs.getString("nome"));
            retorno.setEndereco(rs.getString("endereco"));
            retorno.setBairro(rs.getString("bairro"));
            retorno.setData_nascimento(rs.getDate("data_nascimento"));
            retorno.setCidade(rs.getString("cidade"));
            retorno.setEstado(rs.getString("estado"));
            retorno.setCep(rs.getString("cep"));
            retorno.setSexo(rs.getString("sexo"));
            retorno.setTelefone(rs.getString("telefone"));
            retorno.setCelular(rs.getString("celular"));
            retorno.setCpf(rs.getString("cpf"));
            retorno.setRg(rs.getString("rg"));
            retorno.setTitular(rs.getBoolean("titular"));
            retorno.setData_cobranca(rs.getDate("data_cobranca"));
            retorno.setStatus(rs.getString("status"));
            retorno.setEmail(rs.getString("email"));
            retorno.setNumero(rs.getInt("numero"));
            retorno.setComplemento(rs.getString("complemento"));
            retorno.setConvenio(convenioDao.getConvenioPorCodigo(rs.getInt("idconvenio")));
            retorno.setFormaCobranca(formacobrancaDao.getFormaCobrancaPorCodigo(rs.getInt("idcobranca")));
            retorno.setCategoriaUsm(categoriaDao.getCategoriaPorCodigo(rs.getInt("idcategoria")));

            retornos.add(retorno);

        }
        return retornos;
    }
    public List<Cliente> listarCpf(String filtro) throws SQLException {
        List<Cliente> retornos = new ArrayList<Cliente>();
        Cliente retorno = null;

        String sql = "";

        if (filtro.length() > 0) {
            sql = "select * from cliente where cpf ilike '" + filtro + "%'";
        } else {
            sql = "select * from cliente";
        }
        sql += " order by nome";
        pstm = con.prepareStatement(sql);
        rs = pstm.executeQuery();
        while (rs.next()) {

            retorno = new Cliente();
            retorno.setIdcliente(rs.getInt("idcliente"));
            retorno.setNome(rs.getString("nome"));
            retorno.setEndereco(rs.getString("endereco"));
            retorno.setBairro(rs.getString("bairro"));
            retorno.setData_nascimento(rs.getDate("data_nascimento"));
            retorno.setCidade(rs.getString("cidade"));
            retorno.setEstado(rs.getString("estado"));
            retorno.setCep(rs.getString("cep"));
            retorno.setSexo(rs.getString("sexo"));
            retorno.setTelefone(rs.getString("telefone"));
            retorno.setCelular(rs.getString("celular"));
            retorno.setCpf(rs.getString("cpf"));
            retorno.setRg(rs.getString("rg"));
            retorno.setTitular(rs.getBoolean("titular"));
            retorno.setData_cobranca(rs.getDate("data_cobranca"));
            retorno.setStatus(rs.getString("status"));
            retorno.setEmail(rs.getString("email"));
            retorno.setNumero(rs.getInt("numero"));
            retorno.setComplemento(rs.getString("complemento"));
            retorno.setConvenio(convenioDao.getConvenioPorCodigo(rs.getInt("idconvenio")));
            retorno.setFormaCobranca(formacobrancaDao.getFormaCobrancaPorCodigo(rs.getInt("idcobranca")));
            retorno.setCategoriaUsm(categoriaDao.getCategoriaPorCodigo(rs.getInt("idcategoria")));

            retornos.add(retorno);

        }
        return retornos;
    }

    public Cliente getClientePorNome(String nome) throws SQLException {

        Cliente retorno = null;

        pstm = con.prepareStatement("select * from cliente where nome=?");
        pstm.setString(1, nome);
        rs = pstm.executeQuery();

        if (rs.next()) {

            retorno = new Cliente();
            retorno.setIdcliente(rs.getInt("idcliente"));
            retorno.setNome(rs.getString("nome"));
            retorno.setEndereco(rs.getString("endereco"));
            retorno.setBairro(rs.getString("bairro"));
            retorno.setData_nascimento(rs.getDate("data_nascimento"));
            retorno.setCidade(rs.getString("cidade"));
            retorno.setEstado(rs.getString("estado"));
            retorno.setCep(rs.getString("cep"));
            retorno.setSexo(rs.getString("sexo"));
            retorno.setTelefone(rs.getString("telefone"));
            retorno.setCelular(rs.getString("celular"));
            retorno.setCpf(rs.getString("cpf"));
            retorno.setRg(rs.getString("rg"));
            retorno.setTitular(rs.getBoolean("titular"));
            retorno.setData_cobranca(rs.getDate("data_cobranca"));
            retorno.setStatus(rs.getString("status"));
            retorno.setEmail(rs.getString("email"));
            retorno.setNumero(rs.getInt("numero"));
            retorno.setComplemento(rs.getString("complemento"));
            retorno.setConvenio(convenioDao.getConvenioPorCodigo(rs.getInt("idconvenio")));
            retorno.setFormaCobranca(formacobrancaDao.getFormaCobrancaPorCodigo(rs.getInt("idcobranca")));
            retorno.setCategoriaUsm(categoriaDao.getCategoriaPorCodigo(rs.getInt("idcategoria")));

        }
        return retorno;

    }

    public Cliente getClientePorCodigo(int idcliente) throws SQLException {

        Cliente retorno = null;

        pstm = con.prepareStatement("select * from cliente where idcliente=?");
        pstm.setInt(1, idcliente);
        rs = pstm.executeQuery();

        if (rs.next()) {
            retorno = new Cliente();
            retorno.setIdcliente(rs.getInt("idcliente"));
            retorno.setNome(rs.getString("nome"));
            retorno.setEndereco(rs.getString("endereco"));
            retorno.setBairro(rs.getString("bairro"));
            retorno.setData_nascimento(rs.getDate("data_nascimento"));
            retorno.setCidade(rs.getString("cidade"));
            retorno.setEstado(rs.getString("estado"));
            retorno.setCep(rs.getString("cep"));
            retorno.setSexo(rs.getString("sexo"));
            retorno.setTelefone(rs.getString("telefone"));
            retorno.setCelular(rs.getString("celular"));
            retorno.setCpf(rs.getString("cpf"));
            retorno.setRg(rs.getString("rg"));
            retorno.setTitular(rs.getBoolean("titular"));
            retorno.setData_cobranca(rs.getDate("data_cobranca"));
            retorno.setStatus(rs.getString("status"));
            retorno.setEmail(rs.getString("email"));
            retorno.setNumero(rs.getInt("numero"));
            retorno.setComplemento(rs.getString("complemento"));
            retorno.setConvenio(convenioDao.getConvenioPorCodigo(rs.getInt("idconvenio")));
            retorno.setFormaCobranca(formacobrancaDao.getFormaCobrancaPorCodigo(rs.getInt("idcobranca")));
            retorno.setCategoriaUsm(categoriaDao.getCategoriaPorCodigo(rs.getInt("idcategoria")));

        }
        return retorno;

    }
}
