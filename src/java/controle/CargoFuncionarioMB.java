package controle;

import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;
import modelo.CargoFuncionario;
import persistencia.CargoFuncionarioDAO;



@ManagedBean
@RequestScoped
public class CargoFuncionarioMB {

    private CargoFuncionario cargoFuncionario;
    private CargoFuncionarioDAO cargoFuncionarioDao;
    private String filtro = "";

    public CargoFuncionarioMB() throws SQLException {
        cargoFuncionarioDao = new CargoFuncionarioDAO();
        cargoFuncionario = new CargoFuncionario();

    }

    public CargoFuncionario getCargoFuncionario() {
        return cargoFuncionario;
    }

    public void setCargoFuncionario(CargoFuncionario cargoFuncionario) {
        this.cargoFuncionario = cargoFuncionario;
    }

    public CargoFuncionarioDAO getCargoFuncionarioDao() {
        return cargoFuncionarioDao;
    }

    public void setCargoFuncionarioDao(CargoFuncionarioDAO cargoFuncionarioDao) {
        this.cargoFuncionarioDao = cargoFuncionarioDao;
    }

    

    public void salvar() throws SQLException {
        if (cargoFuncionario.getIdCargoFuncionario() == 0) {
            cargoFuncionarioDao.salvar(cargoFuncionario);
            limpar();
        } else {
            cargoFuncionarioDao.alterar(cargoFuncionario);
            limpar();
        }

    }

    public void limpar() {
        cargoFuncionario = new CargoFuncionario();
    }

    public void alterar() throws SQLException {
        cargoFuncionarioDao.alterar(cargoFuncionario);
        limpar();
    }

    public void excluir() throws SQLException {
        cargoFuncionarioDao.excluir(cargoFuncionario);
        limpar();
    }

    public void excluir(CargoFuncionario obj) throws SQLException {
        cargoFuncionarioDao.excluir(obj);
        limpar();
    }

    public void pesquisar() throws SQLException {
        cargoFuncionario = cargoFuncionarioDao.pesquisar(cargoFuncionario);
    }

    public List<CargoFuncionario> listar() throws SQLException {

        List<CargoFuncionario> lista;
        lista = cargoFuncionarioDao.listar(filtro);
        return lista;

    }

    /**
     * @return the filtro
     */
    public String getFiltro() {
        return filtro;
    }

    /**
     * @param filtro the filtro to set
     */
    public void setFiltro(String filtro) {
        this.filtro = filtro;
    }
}
