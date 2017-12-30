
package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.CadastroMedico;


public class CadastroMedicoDAO {

    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;

    public CadastroMedicoDAO() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
        con = DriverManager.getConnection("jdbc:postgresql://localhost/clinicausm", "postgres", "postgres");
    }

    public void salvar(CadastroMedico cadastroMedico) throws SQLException {
        //carrega o conector do PostgreSql é registra no gerenciador de dispositivos

        pstm = con.prepareStatement("insert into tipomedico (idtipomedico,nome,valorhora) values(nextval('tipomedico_seq'),?,?)");
        pstm.setString(1, cadastroMedico.getNome());
        pstm.setFloat(2, cadastroMedico.getValorHora());

        pstm.executeUpdate();
    }

    public void alterar(CadastroMedico cadastroMedico) throws SQLException {
        //carrega o conector do PostgreSql é registra no gerenciador de dispositivos

        pstm = con.prepareStatement("update tipomedico set nome=?,valorhora=? where idtipomedico=?");
        pstm.setString(1, cadastroMedico.getNome());
        pstm.setFloat(2, cadastroMedico.getValorHora());
        pstm.setInt(3, cadastroMedico.getIdCadastroMedico());
        pstm.executeUpdate();
    }

    public void excluir(CadastroMedico cadastroMedico) throws SQLException {
        pstm = con.prepareStatement("delete from tipomedico where idtipomedico=?");
        pstm.setInt(1, cadastroMedico.getIdCadastroMedico());
        pstm.executeUpdate();
    }

    public CadastroMedico pesquisar(CadastroMedico cadastroMedico) throws SQLException {
        CadastroMedico retorno = null;
        pstm = con.prepareStatement("select * from tipomedico where idtipomedico=?");
        pstm.setInt(1, cadastroMedico.getIdCadastroMedico());
        rs = pstm.executeQuery();
        if (rs.next()) {
            retorno = new CadastroMedico();
            retorno.setIdCadastroMedico(rs.getInt("idtipomedico"));
            retorno.setNome(rs.getString("nome"));
            retorno.setValorHora(rs.getFloat("valorhora"));
           
            return retorno;

        }
        return null;
    }

    public List<CadastroMedico> listar(String filtro) throws SQLException {
        List<CadastroMedico> retornos = new ArrayList<CadastroMedico>();
        CadastroMedico retorno = null;

        String sql = "";

        if (filtro.length() > 0) {
            sql = "select * from tipomedico where nome ilike '" + filtro + "%'";
        } else {
            sql = "select * from tipomedico";
        }
        sql += " order by nome";
        pstm = con.prepareStatement(sql);
        rs = pstm.executeQuery();
        while (rs.next()) {
            retorno = new CadastroMedico();
            retorno.setIdCadastroMedico(rs.getInt("idtipomedico"));
            retorno.setNome(rs.getString("nome"));
            retorno.setValorHora(rs.getFloat("valorhora"));
            retornos.add(retorno);
        }
        return retornos;
    }

    public CadastroMedico getCadastroMedicoPorNome(String nome) throws SQLException {
        CadastroMedico cadastroMedico = new CadastroMedico();
        CadastroMedico retorno = null;

        pstm = con.prepareStatement("select * from tipomedico where nome=?");
        pstm.setString(1, nome);
        rs = pstm.executeQuery();

        if (rs.next()) {

            retorno = new CadastroMedico();
            retorno.setIdCadastroMedico(rs.getInt("idtipomedico"));
            retorno.setNome(rs.getString("nome"));
            retorno.setValorHora(rs.getFloat("valorhora"));

        }
        return retorno;

    }

    public CadastroMedico getCadastroMedicoPorCodigo(int idCadastroMedico) throws SQLException {

        CadastroMedico retorno = null;

        pstm = con.prepareStatement("select * from tipomedico where idtipomedico=?");
        pstm.setInt(1, idCadastroMedico);
        rs = pstm.executeQuery();

        if (rs.next()) {

            retorno = new CadastroMedico();
            retorno.setIdCadastroMedico(rs.getInt("idtipomedico"));
            retorno.setNome(rs.getString("nome"));
            retorno.setValorHora(rs.getFloat("valorhora"));
        }
        return retorno;
    }



}
