
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Fornecedor;



public class FornecedorDAO {

    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;
       
    
    
    public FornecedorDAO() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
        con = DriverManager.getConnection("jdbc:postgresql://localhost/clinicausm", "postgres", "postgres");
    }

    public void salvar(Fornecedor fornecedor) throws SQLException {
        //carrega o conector do PostgreSql é registra no gerenciador de dispositivos

        pstm = con.prepareStatement("insert into fornecedor (idfornecedor,razaosocial,nomefantasia,fone,cnpj,"
                + "descricaoproduto,email,site,cep,endereco,numero,complemento,bairro,cidade,uf) values (nextval('fornecedor_seq'),?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
        pstm.setString(1, fornecedor.getRazaoSocial());
        pstm.setString(2, fornecedor.getNomeFantasia());
        pstm.setString(3, fornecedor.getFone());
        pstm.setString(4, fornecedor.getCnpj());
        pstm.setString(5, fornecedor.getDescricaoProduto());
        pstm.setString(6, fornecedor.getEmail());
        pstm.setString(7, fornecedor.getSite());
        pstm.setString(8, fornecedor.getCep());
        pstm.setString(9, fornecedor.getEndereco());
        pstm.setString(10, fornecedor.getNumero());
        pstm.setString(11, fornecedor.getComplemento());
        pstm.setString(12, fornecedor.getBairro());
        pstm.setString(13, fornecedor.getCidade());
        pstm.setString(14, fornecedor.getUf());        
        pstm.executeUpdate();

    }

    public void alterar(Fornecedor fornecedor) throws SQLException {
        pstm = con.prepareStatement("update fornecedor set razaosocial=?,nomefantasia=?,fone=?,"
                + "cnpj=?,descricaoproduto=?,email=?,site=?,cep=?,endereco=?,numero=?,complemento=?,bairro=?,cidade=?,uf=? where idfornecedor=?");
        pstm.setString(1, fornecedor.getRazaoSocial());
        pstm.setString(2, fornecedor.getNomeFantasia());
        pstm.setString(3, fornecedor.getFone());
        pstm.setString(4, fornecedor.getCnpj());
        pstm.setString(5, fornecedor.getDescricaoProduto());
        pstm.setString(6, fornecedor.getEmail());
        pstm.setString(7, fornecedor.getSite());
        pstm.setString(8, fornecedor.getCep());
        pstm.setString(9, fornecedor.getEndereco());
        pstm.setString(10, fornecedor.getNumero());
        pstm.setString(11, fornecedor.getComplemento());
        pstm.setString(12, fornecedor.getBairro());
        pstm.setString(13, fornecedor.getCidade());
        pstm.setString(14, fornecedor.getUf());
        pstm.setInt(15, fornecedor.getIdfornecedor());
        pstm.executeUpdate();

    }

    public void excluir(Fornecedor fornecedor) throws SQLException {
        pstm = con.prepareStatement(
                "delete from fornecedor where idfornecedor=?");
        pstm.setInt(1,fornecedor.getIdfornecedor());
        pstm.executeUpdate();

    }

    public List<Fornecedor> listar(String filtro) throws SQLException {
        List<Fornecedor> retornos = new ArrayList<Fornecedor>();
        Fornecedor retorno = null;

        String sql = "";

        if (filtro.length() > 0) {
            sql = "select * from fornecedor where razaosocial ilike '" + filtro + "%'";
        } else {
            sql = "select * from fornecedor";
        }
        sql += " order by razaosocial";
        pstm = con.prepareStatement(sql);
        rs = pstm.executeQuery();
        while (rs.next()) {

            retorno = new Fornecedor();
            retorno.setIdfornecedor(rs.getInt("idfornecedor"));
            retorno.setRazaoSocial(rs.getString("razaosocial"));
            retorno.setNomeFantasia(rs.getString("nomefantasia"));
            retorno.setFone(rs.getString("fone"));
            retorno.setCnpj(rs.getString("cnpj"));
            retorno.setDescricaoProduto(rs.getString("descricaoproduto"));            
            retorno.setEmail(rs.getString("email"));
            retorno.setSite(rs.getString("site"));
            retorno.setCep(rs.getString("cep"));
            retorno.setEndereco(rs.getString("endereco"));
            retorno.setNumero(rs.getString("numero"));
            retorno.setComplemento(rs.getString("complemento"));
            retorno.setBairro(rs.getString("bairro"));            
            retorno.setCidade(rs.getString("cidade"));
            retorno.setUf(rs.getString("uf"));
            
            retornos.add(retorno);

        }
        return retornos;
    }
    
    public Fornecedor getFornecedorPorRazaoSocial(String razaoSocial) throws SQLException {

        Fornecedor retorno = null;

        pstm = con.prepareStatement("select * from fornecedor where razaosocial=?");
        pstm.setString(1, razaoSocial);
        rs = pstm.executeQuery();

        if (rs.next()) {

            retorno = new Fornecedor();
            retorno.setIdfornecedor(rs.getInt("idfornecedor"));
            retorno.setRazaoSocial(rs.getString("razaosocial"));
            retorno.setNomeFantasia(rs.getString("nomefantasia"));
            retorno.setFone(rs.getString("fone"));
            retorno.setCnpj(rs.getString("cnpj"));
            retorno.setDescricaoProduto(rs.getString("descricaoproduto"));            
            retorno.setEmail(rs.getString("email"));
            retorno.setSite(rs.getString("site"));
            retorno.setCep(rs.getString("cep"));
            retorno.setEndereco(rs.getString("endereco"));
            retorno.setNumero(rs.getString("numero"));
            retorno.setComplemento(rs.getString("complemento"));
            retorno.setBairro(rs.getString("bairro"));            
            retorno.setCidade(rs.getString("cidade"));
            
        }
        return retorno;

    }
    
    public Fornecedor getFornecedorPorCodigo(int idFornecedor) throws SQLException {
      
        Fornecedor retorno = null;
        
        pstm = con.prepareStatement("select * from fornecedor where idfornecedor=?");
        pstm.setInt(1, idFornecedor);
        rs = pstm.executeQuery();

        if (rs.next()) {

            retorno = new Fornecedor();
            retorno.setIdfornecedor(rs.getInt("idfornecedor"));
            retorno.setRazaoSocial(rs.getString("razaosocial"));
            retorno.setNomeFantasia(rs.getString("nomefantasia"));
            retorno.setFone(rs.getString("fone"));
            retorno.setCnpj(rs.getString("cnpj"));
            retorno.setDescricaoProduto(rs.getString("descricaoproduto"));            
            retorno.setEmail(rs.getString("email"));
            retorno.setSite(rs.getString("site"));
            retorno.setCep(rs.getString("cep"));
            retorno.setEndereco(rs.getString("endereco"));
            retorno.setNumero(rs.getString("numero"));
            retorno.setComplemento(rs.getString("complemento"));
            retorno.setBairro(rs.getString("bairro"));            
            retorno.setCidade(rs.getString("cidade"));
            
           

        }
         return retorno;
        
        
    }
}
