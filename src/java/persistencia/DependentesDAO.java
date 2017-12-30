package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Dependentes;

public class DependentesDAO {

    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;
    private ClienteDAO clienteDao;

    public DependentesDAO() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
        con = DriverManager.getConnection("jdbc:postgresql://localhost/clinicausm", "postgres", "postgres");
        clienteDao = new ClienteDAO();
    }

    public void salvar(Dependentes dependentes) throws SQLException {
        //carrega o conector do PostgreSql é registra no gerenciador de dispositivos

        pstm = con.prepareStatement("insert into dependentes (iddependentes,nome,cpf,sexo,data_nascimento,id_cliente) values (nextval('dependente_seq'),?,?,?,?,?)");
        pstm.setString(1, dependentes.getNome());
        pstm.setString(2, dependentes.getCpf());
        pstm.setString(3, dependentes.getSexo());
        pstm.setDate(4, new java.sql.Date(dependentes.getData_nascimento().getTime()));
        pstm.setInt(5, dependentes.getCliente().getIdcliente());

        pstm.executeUpdate();
    }

    public void alterar(Dependentes dependentes) throws SQLException {
        pstm = con.prepareStatement("update dependentes set nome=?,cpf=?,sexo=?,data_nascimento=?,id_cliente=? where iddependentes=?");
        pstm.setString(1, dependentes.getNome());
        pstm.setString(2, dependentes.getCpf());
        pstm.setString(3, dependentes.getSexo());
        pstm.setDate(4, new java.sql.Date(dependentes.getData_nascimento().getTime()));
        pstm.setInt(5, dependentes.getCliente().getIdcliente());
        pstm.setInt(6, dependentes.getIddependentes());

        pstm.executeUpdate();

    }

    public void excluir(Dependentes dependentes) throws SQLException {
        pstm = con.prepareStatement(
                "delete from dependentes where iddependentes=?");
        pstm.setInt(1, dependentes.getIddependentes());
        pstm.executeUpdate();
    }

    public Dependentes pesquisar(Dependentes dependentes) throws SQLException {
        Dependentes retorno = null;
        pstm = con.prepareStatement("select * from dependentes where iddependentes=?");
        pstm.setInt(1, dependentes.getIddependentes());
        rs = pstm.executeQuery();
        if (rs.next()) {
            retorno = new Dependentes();
            retorno.setIddependentes(rs.getInt("iddependentes"));
            retorno.setNome(rs.getString("nome"));
            retorno.setCpf(rs.getString("cpf"));
            retorno.setData_nascimento(rs.getDate("data_nascimento"));
            retorno.setSexo(rs.getString("sexo"));

            return retorno;

        }
        return null;
    }

    public Dependentes pesquisarPorClie(Dependentes dependentes) throws SQLException {
        Dependentes retorno = null;
        pstm = con.prepareStatement("select iddependentes, nome, cpf, data_nascimento,sexo from dependentes where id_cliente =? ");
        pstm.setInt(1, dependentes.getCliente().getIdcliente());
        rs = pstm.executeQuery();
        if (rs.next()) {
            retorno = new Dependentes();
            retorno.setIddependentes(rs.getInt("iddependentes"));
            retorno.setNome(rs.getString("nome"));
            retorno.setCpf(rs.getString("cpf"));
            retorno.setData_nascimento(rs.getDate("data_nascimento"));
            retorno.setSexo(rs.getString("sexo"));

            return retorno;

        }
        return null;
    }    
    
    public List<Dependentes> listar(String filtro) throws SQLException {
        List<Dependentes> retornos = new ArrayList<Dependentes>();
        Dependentes retorno = null;

        String sql = "";

        if (filtro.length() > 0) {
            sql = "select * from dependentes where nome ilike '" + filtro + "%'";
        } else {
            sql = "select * from dependentes";
        }
        sql += " order by id_cliente";
        pstm = con.prepareStatement(sql);
        rs = pstm.executeQuery();
        while (rs.next()) {
            retorno = new Dependentes();
            retorno.setIddependentes(rs.getInt("iddependentes"));
            retorno.setNome(rs.getString("nome"));
            retorno.setCpf(rs.getString("cpf"));
            retorno.setSexo(rs.getString("sexo"));
            retorno.setData_nascimento(rs.getDate("data_nascimento"));
            retorno.setCliente(clienteDao.getClientePorCodigo(rs.getInt("id_cliente")));

            retornos.add(retorno);
        }
        return retornos;
    }
    
    //Quando tu chamar esse metodo tu vai passsar o id do cliente que tu quer os dependentes
    //e algum filtro, ele vai te retornar um List<Dependentes> e é só tu popular um controle
    //na tela ou fazer um foreach e mandar escrever os dados.
    //Não testei esse codigo, abri direto no Sublime e alterei
    public List<Dependentes> listarPorCliente(String textFiltro, int id_cliente) throws SQLException {
        List<Dependentes> retornos = new ArrayList<Dependentes>();
        Dependentes retorno = null;

        String sql = "";

        if (textFiltro.length() > 0) {
            sql = "select iddependentes, nome, cpf, data_nascimento,sexo from dependentes where  id_cliente = " + id_cliente + " AND nome ILIKE('" + textFiltro + "')";
        } else {
            sql = "select * from dependentes where  id_cliente = " + id_cliente;
        }
        sql += " order by nome";
        pstm = con.prepareStatement(sql);
        rs = pstm.executeQuery();
        while (rs.next()) {
            retorno = new Dependentes();
            retorno.setIddependentes(rs.getInt("iddependentes"));
            retorno.setNome(rs.getString("nome"));
            retorno.setCpf(rs.getString("cpf"));
            retorno.setData_nascimento(rs.getDate("data_nascimento"));
            retorno.setSexo(rs.getString("sexo"));
            retorno.setCliente(clienteDao.getClientePorCodigo(rs.getInt("id_cliente")));
            retornos.add(retorno);
        }
        return retornos;
    }

    public Dependentes getDependentesPorNome(String nome) throws SQLException {
        Dependentes dependentes = new Dependentes();
        Dependentes retorno = null;

        pstm = con.prepareStatement("select * from dependentes where nome=?");
        pstm.setString(1, nome);
        rs = pstm.executeQuery();

        if (rs.next()) {

            retorno = new Dependentes();
            retorno.setIddependentes(rs.getInt("iddependentes"));
            retorno.setNome(rs.getString("nome"));
            retorno.setCpf(rs.getString("cpf"));
            retorno.setData_nascimento(rs.getDate("data_nascimento"));
            retorno.setSexo(rs.getString("sexo"));
            retorno.setCliente(clienteDao.getClientePorCodigo(rs.getInt("id_cliente")));
        }
        return retorno;

    }

}
