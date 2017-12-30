package controle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;
import modelo.Cliente;
import modelo.ContasReceber;
import persistencia.ClienteDAO;
import persistencia.ContasReceberDAO;

@ManagedBean
@RequestScoped

public final class ContasReceberMB {

    private ContasReceber contasReceber;
    private ContasReceberDAO contasReceberDao;
    private String filtro = "";
    private List<Cliente> clientes;
    private List<ContasReceber> contasRecebers;
    private ClienteDAO clienteDao;
    private float totalReceber;
    

    public ContasReceberMB() throws SQLException {
        contasReceberDao = new ContasReceberDAO();
        contasReceber = new ContasReceber();
        clienteDao = new ClienteDAO();
        clientes = clienteDao.listar("");
        contasRecebers = listarTotal();
        this.totalReceber = 0f;
    }
    
    public List<ContasReceber> listarTotal() throws SQLException {
        ArrayList<ContasReceber> lista = new ArrayList();
        lista.addAll(contasReceberDao.listarTotal());
        
        for (ContasReceber cont : lista) {
            this.totalReceber += cont.getValor();
        }        
        return lista;
    }
    
    public ContasReceber getContasReceber() {
        return contasReceber;
    }

    public void setContasReceber(ContasReceber contasReceber) {
        this.contasReceber = contasReceber;
    }

    public ContasReceberDAO getContasReceberDao() {
        return contasReceberDao;
    }

    public void setContasReceberDao(ContasReceberDAO contasReceberDao) {
        this.contasReceberDao = contasReceberDao;
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

    public List<ContasReceber> getContasRecebers() {
        return contasRecebers;
    }

    public void setContasRecebers(List<ContasReceber> contasRecebers) {
        this.contasRecebers = contasRecebers;
    }

    public float getTotalReceber() {
        return totalReceber;
    }

    public void setTotalReceber(float totalReceber) {
        this.totalReceber = totalReceber;
    }
    
    public void salvar() throws SQLException {
        if (contasReceber.getIdContasReceber() == 0) {
            contasReceberDao.salvar(contasReceber);
            limpar();
        } else {
            contasReceberDao.alterar(contasReceber);
            limpar();
        }

    }

    public void limpar() {
        contasReceber = new ContasReceber();
    }

    public void alterar() throws SQLException {
        contasReceberDao.alterar(contasReceber);
        limpar();
    }

    public void excluir() throws SQLException {
        contasReceberDao.excluir(contasReceber);
        limpar();
    }

    public void excluir(ContasReceber obj) throws SQLException {
        contasReceberDao.excluir(obj);
        limpar();
    }

    public void pesquisar() throws SQLException {
        contasReceber = contasReceberDao.pesquisar(contasReceber);
    }

    public List<ContasReceber> listar() throws SQLException {
        List<ContasReceber> lista;
        lista = contasReceberDao.listar(filtro);
        
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
