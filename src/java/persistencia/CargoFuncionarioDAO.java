package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.CargoFuncionario;

/**
 *
 * @author Administrador
 */
public class CargoFuncionarioDAO {

    private Connection con;
    private PreparedStatement pstm;
    private ResultSet rs;

    public CargoFuncionarioDAO() throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
        con = DriverManager.getConnection("jdbc:postgresql://localhost/clinicausm", "postgres", "postgres");
    }

    public void salvar(CargoFuncionario cargoFuncionario) throws SQLException {
        //carrega o conector do PostgreSql é registra no gerenciador de dispositivos

        pstm = con.prepareStatement("insert into tipofuncionario (idtipofuncionario,cargo,valorhora) values (nextval('cobranca_seq'),?,?)");
        pstm.setString(1, cargoFuncionario.getCargo());
        pstm.setFloat(2, cargoFuncionario.getValorHora());

        pstm.executeUpdate();
    }

    public void alterar(CargoFuncionario cargoFuncionario) throws SQLException {
        //carrega o conector do PostgreSql é registra no gerenciador de dispositivos

        pstm = con.prepareStatement("update tipofuncionario set cargo=?,valorhora=? where idtipofuncionario=?");
        pstm.setString(1, cargoFuncionario.getCargo());
        pstm.setFloat(2, cargoFuncionario.getValorHora());
        pstm.setInt(3, cargoFuncionario.getIdCargoFuncionario());
        pstm.executeUpdate();
    }

    public void excluir(CargoFuncionario cargoFuncionario) throws SQLException {
        pstm = con.prepareStatement(
                "delete from tipofuncionario where idtipofuncionario=?");
        pstm.setInt(1, cargoFuncionario.getIdCargoFuncionario());
        pstm.executeUpdate();
    }

    public CargoFuncionario pesquisar(CargoFuncionario cargoFuncionario) throws SQLException {
        CargoFuncionario retorno = null;
        pstm = con.prepareStatement("select * from tipofuncionario where idtipofuncionario=?");
        pstm.setInt(1, cargoFuncionario.getIdCargoFuncionario());
        rs = pstm.executeQuery();
        if (rs.next()) {
            retorno = new CargoFuncionario();
            retorno.setIdCargoFuncionario(rs.getInt("idtipofuncionario"));
            retorno.setCargo(rs.getString("cargo"));
            retorno.setValorHora(rs.getFloat("valorhora"));

            return retorno;

        }
        return null;
    }

    public List<CargoFuncionario> listar(String filtro) throws SQLException {
        List<CargoFuncionario> retornos = new ArrayList<CargoFuncionario>();
        CargoFuncionario retorno = null;

        String sql = "";

        if (filtro.length() > 0) {
            sql = "select * from tipofuncionario where cargo ilike '" + filtro + "%'";
        } else {
            sql = "select * from tipofuncionario";
        }
        sql += " order by cargo";
        pstm = con.prepareStatement(sql);
        rs = pstm.executeQuery();
        while (rs.next()) {
            retorno = new CargoFuncionario();
            retorno.setIdCargoFuncionario(rs.getInt("idtipofuncionario"));
            retorno.setCargo(rs.getString("cargo"));
            retorno.setValorHora(rs.getFloat("valorhora"));

            retornos.add(retorno);
        }
        return retornos;
    }

    public CargoFuncionario getCargoFuncionarioPorTipo(String tipo) throws SQLException {
        CargoFuncionario cargoFuncionario = new CargoFuncionario();
        CargoFuncionario retorno = null;

        pstm = con.prepareStatement("select * from tipofuncionario where cargo=?");
        pstm.setString(1, tipo);
        rs = pstm.executeQuery();

        if (rs.next()) {

            retorno = new CargoFuncionario();
            retorno.setIdCargoFuncionario(rs.getInt("idtipofuncionario"));
            retorno.setCargo(rs.getString("cargo"));
            retorno.setValorHora(rs.getFloat("valorhora"));

        }
        return retorno;

    }

    public CargoFuncionario getCargoFuncionarioPorCodigo(int idCargoFuncionario) throws SQLException {
        CargoFuncionario cargoFuncionario = new CargoFuncionario();
        CargoFuncionario retorno = null;

        pstm = con.prepareStatement("select * from tipofuncionario where idtipofuncionario=?");
        pstm.setInt(1, idCargoFuncionario);
        rs = pstm.executeQuery();

        if (rs.next()) {

            retorno = new CargoFuncionario();
            retorno.setIdCargoFuncionario(rs.getInt("idtipofuncionario"));
            retorno.setCargo(rs.getString("cargo"));
            retorno.setValorHora(rs.getFloat("valorhora"));

        }
        return retorno;

    }
}
