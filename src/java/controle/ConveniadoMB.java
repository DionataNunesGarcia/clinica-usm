package controle;

import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;
import modelo.Cliente;
import modelo.Conveniado;
import persistencia.ClienteDAO;
import persistencia.ConveniadoDAO;

@ManagedBean
@RequestScoped
public class ConveniadoMB {

    private Conveniado conveniado;
    private ConveniadoDAO conveniadoDao;
    private String filtro = "";
    private List<Cliente> clientes;
    private ClienteDAO clienteDao;

    public ConveniadoMB() throws SQLException {
        conveniado = new Conveniado();
        conveniadoDao = new ConveniadoDAO();
        clienteDao = new ClienteDAO();
        clientes = clienteDao.listar("");
    }

    public Conveniado getConveniado() {
        return conveniado;
    }

    public void setConveniado(Conveniado conveniado) {
        this.conveniado = conveniado;
    }

    public ConveniadoDAO getConveniadoDao() {
        return conveniadoDao;
    }

    public void setConveniadoDao(ConveniadoDAO conveniadoDao) {
        this.conveniadoDao = conveniadoDao;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public ClienteDAO getClienteDao() {
        return clienteDao;
    }

    public void setClienteDao(ClienteDAO clienteDao) {
        this.clienteDao = clienteDao;
    }

    public void salvar() throws SQLException {
        if (conveniado.getIdconveniado() == 0) {
            conveniadoDao.salvar(conveniado);
            limpar();
        } else {
            conveniadoDao.alterar(conveniado);
            limpar();
        }

    }

    public void limpar() {
        conveniado = new Conveniado();
    }

    public void alterar() throws SQLException {
        conveniadoDao.alterar(conveniado);
        limpar();
    }

    public void excluir() throws SQLException {
        conveniadoDao.excluir(conveniado);
        limpar();
    }

    public void excluir(Conveniado obj) throws SQLException {
        conveniadoDao.excluir(obj);
        limpar();
    }

    public void pesquisar() throws SQLException {
        conveniado = conveniadoDao.pesquisar(conveniado);
    }

    public List<Conveniado> listar() throws SQLException {

        List<Conveniado> lista;
        lista = conveniadoDao.listar(filtro);
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
