package controle;

import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import modelo.CategoriaUsm;
import modelo.Cliente;
import modelo.Convenio;
import modelo.FormaCobranca;
import persistencia.CategoriaDAO;
import persistencia.ClienteDAO;
import persistencia.ConvenioDAO;
import persistencia.FormaCobrancaDAO;

@ManagedBean
@RequestScoped
public class ClienteMB {

    private Cliente cliente;
    private ClienteDAO clienteDao;
    private List<Convenio> convenios;
    private ConvenioDAO convenioDao;
    private List<FormaCobranca> formaCobrancas;
    private FormaCobrancaDAO formaCobrancaDao;
    private List<CategoriaUsm> categorias;
    private CategoriaDAO categoriaDao;
    private String filtro = "";
    private String filtroFone = "";
    private String filtroCpf = "";

    public ClienteMB() throws SQLException {
        clienteDao = new ClienteDAO();
        cliente = new Cliente();
        convenioDao = new ConvenioDAO();
        convenios = convenioDao.listar("");
        formaCobrancaDao = new FormaCobrancaDAO();
        formaCobrancas = formaCobrancaDao.listar("");
        formaCobrancaDao = new FormaCobrancaDAO();
        formaCobrancas = formaCobrancaDao.listar("");
        categoriaDao = new CategoriaDAO();
        categorias = categoriaDao.listar("");
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public ClienteDAO getClienteDao() {
        return clienteDao;
    }

    public void setClienteDao(ClienteDAO clienteDao) {
        this.clienteDao = clienteDao;
    }

    public List<Convenio> getConvenios() {
        return convenios;
    }

    public void setConvenios(List<Convenio> convenios) {
        this.convenios = convenios;
    }

    public ConvenioDAO getConvenioDao() {
        return convenioDao;
    }

    public void setConvenioDao(ConvenioDAO convenioDao) {
        this.convenioDao = convenioDao;
    }

    public List<FormaCobranca> getFormaCobrancas() {
        return formaCobrancas;
    }

    public void setFormaCobrancas(List<FormaCobranca> formaCobrancas) {
        this.formaCobrancas = formaCobrancas;
    }

    public FormaCobrancaDAO getFormaCobrancaDao() {
        return formaCobrancaDao;
    }

    public void setFormaCobrancaDao(FormaCobrancaDAO formaCobrancaDao) {
        this.formaCobrancaDao = formaCobrancaDao;
    }

    public List<CategoriaUsm> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<CategoriaUsm> categorias) {
        this.categorias = categorias;
    }

    public CategoriaDAO getCategoriaDao() {
        return categoriaDao;
    }

    public void setCategoriaDao(CategoriaDAO categoriaDao) {
        this.categoriaDao = categoriaDao;
    }
    
    public void salvar() throws SQLException {
        if (cliente.getIdcliente() == 0) {
            clienteDao.salvar(cliente);
            limpar();
        } else {
            clienteDao.alterar(cliente);
            limpar();
        }
    }

    public void limpar() {
        cliente = new Cliente();
    }

    public void alterar() throws SQLException {
        clienteDao.alterar(cliente);
        limpar();
    }

    public void excluir() throws SQLException {
        clienteDao.excluir(cliente);
        limpar();
    }

    public void excluir(Cliente obj) throws SQLException {
        clienteDao.excluir(obj);
        limpar();
    }

    public List<Cliente> listar() throws SQLException {
        List<Cliente> lista;
        lista = clienteDao.listar(filtro);
        return lista;
    }
    public List<Cliente> listarFone() throws SQLException {
        List<Cliente> lista;
        lista = clienteDao.listarFone(filtro);
        return lista;
    }
    public List<Cliente> listarCpf() throws SQLException {
        List<Cliente> lista;
        lista = clienteDao.listarCpf(filtro);
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
    
    /**
     * @return the filtroFone
     */
    public String getFiltroFone() {
        return filtroFone;
    }

    /**
     * @param filtroFone the filtroFone to set
     */
    public void setFiltroFone(String filtroFone) {
        this.filtroFone = filtroFone;
    }

    /*public void salvarCovenio() throws SQLException {
        
                
     //Pega o usuário da sessão.
     FacesContext facesContext = FacesContext.getCurrentInstance();  
     HttpSession sessaoHttp = (HttpSession) facesContext.getExternalContext().getSession(true);            
     if (((Cliente) sessaoHttp.getAttribute("usuarioLogado")) != null) {  
     this.convenio.setCliente((Cliente) sessaoHttp.getAttribute("usuarioLogado"));  
     }          
           
     //locacao.setCodCliente();
     locacao.setCliente(logarClieMB.getCliente());
     locacaoDao.salvar(locacao);
        

     }*/
}
