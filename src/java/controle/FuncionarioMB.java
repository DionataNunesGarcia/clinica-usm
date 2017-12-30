
package controle;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.CargoFuncionario;
import modelo.Funcionario;
import persistencia.CargoFuncionarioDAO;
import persistencia.FuncionarioDAO;

@ManagedBean
@RequestScoped
public class FuncionarioMB implements Serializable {

    private Funcionario funcionario;
    private FuncionarioDAO funcionarioDao;
    private String filtro = "";
    private String mensagem;
    private List<CargoFuncionario> cargofuncionarios;
    private CargoFuncionarioDAO cargofuncionarioDao;

    public FuncionarioMB() throws SQLException {
        funcionarioDao = new FuncionarioDAO();
        funcionario = new Funcionario();
        mensagem = "";
        cargofuncionarioDao = new CargoFuncionarioDAO();
        cargofuncionarios = cargofuncionarioDao.listar("");
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }
    
    public FuncionarioDAO getFuncionarioDao() {
        return funcionarioDao;
    }

    public void setFuncionarioDao(FuncionarioDAO funcionarioDao) {
        this.funcionarioDao = funcionarioDao;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public List<CargoFuncionario> getCargofuncionarios() {
        return cargofuncionarios;
    }

    public void setCargofuncionarios(List<CargoFuncionario> cargofuncionarios) {
        this.cargofuncionarios = cargofuncionarios;
    }

    public CargoFuncionarioDAO getCargofuncionarioDao() {
        return cargofuncionarioDao;
    }

    public void setCargofuncionarioDao(CargoFuncionarioDAO cargofuncionarioDao) {
        this.cargofuncionarioDao = cargofuncionarioDao;
    }
        
    public void salvar() throws SQLException {
        if (funcionario.getIdfuncionario() == 0) {
            funcionarioDao.salvar(funcionario);
            limpar();
        } else {
            funcionarioDao.alterar(funcionario);
            limpar();
        }
    }
   
    public void limpar() {
        funcionario = new Funcionario();
    }

    public void alterar() throws SQLException {
        funcionarioDao.alterar(funcionario);
        limpar();
    }

    public void excluir() throws SQLException {
        funcionarioDao.excluir(funcionario);
        limpar();
    }

    public void excluir(Funcionario obj) throws SQLException {
        funcionarioDao.excluir(obj);
        limpar();
    }

    public void pesquisar() throws SQLException {
        funcionario = funcionarioDao.pesquisar(funcionario);
    }

    public List<Funcionario> listar() throws SQLException {

        List<Funcionario> lista;
        lista = funcionarioDao.listar(filtro);
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
