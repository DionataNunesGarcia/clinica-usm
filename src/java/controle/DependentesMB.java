package controle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Cliente;
import modelo.Convenio;
import modelo.Dependentes;
import persistencia.ClienteDAO;
import persistencia.ConvenioDAO;
import persistencia.DependentesDAO;

@ManagedBean
@ViewScoped

public class DependentesMB {
    private Dependentes dependentes;
    private DependentesDAO dependentesDao;
    private String filtro = "";
    private String textFiltro = "";
    private List<Cliente> clientes;    
    private ClienteDAO clienteDao;
    private List<Convenio> convenio;    
    private ConvenioDAO convenioDao;
    private List<String> lista;
    private int idcliente;
    private List<Dependentes> dependentesLista;
    
    
    public DependentesMB() throws SQLException {
        dependentesDao = new DependentesDAO();
        dependentes = new Dependentes();
        clienteDao = new ClienteDAO();
        clientes = clienteDao.listar("");
        //dependentesLista = dependentesDao.listarPorCliente("",dependentes.getCliente().getIdcliente());
        dependentesLista = new ArrayList<>();
}

    public Dependentes getDependentes() {
        return dependentes;
    }

    public void setDependentes(Dependentes dependentes) {
        this.dependentes = dependentes;
    }

    public DependentesDAO getDependentesDao() {
        return dependentesDao;
    }

    public void setDependentesDao(DependentesDAO dependentesDao) {
        this.dependentesDao = dependentesDao;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }
    
    public List<Convenio> getConvenio() {
        return convenio;
    }

    public void setConvenio(List<Convenio> convenio) {
        this.convenio = convenio;
    }

    public ConvenioDAO getConvenioDAO() {
        return convenioDao;
    }

    public void setConvenioDAO(ConvenioDAO convenioDAO) {
        this.convenioDao = convenioDAO;
    }

    public ClienteDAO getClienteDao() {
        return clienteDao;
    }

    public void setClienteDao(ClienteDAO clienteDao) {
        this.clienteDao = clienteDao;
    }

    public List<String> getLista() {
        return lista;
    }

    public void setLista(List<String> lista) {
        this.lista = lista;
    }

    public List<Dependentes> getDependentesLista() {
        return dependentesLista;
    }
    
    public void metodoAjax() throws SQLException{
        List<Dependentes> listaPorClie;
        listaPorClie = dependentesDao.listarPorCliente(textFiltro, idcliente);
        dependentesLista = listaPorClie;       
    }
    
//    public List<Dependentes> listaPorClie() throws SQLException {
//        List<Dependentes> listaPorClie;
//        listaPorClie = dependentesDao.listarPorCliente(textFiltro, idcliente);
//        return listaPorClie;
//    }
    
    public void salvar() throws SQLException {
       if(dependentes.getIddependentes()== 0){
           dependentesDao.salvar(dependentes);
       limpar();
       }        
       else{
          dependentesDao.alterar(dependentes);
        limpar();
       }        
       
    }
    public void limpar(){
        dependentes = new Dependentes();
    }
        
    public void alterar() throws SQLException{
        dependentesDao.alterar(dependentes);
        limpar();
    }
    
    public void excluir() throws SQLException{
        dependentesDao.excluir(dependentes);
        limpar();
    }
    
    public void excluir(Dependentes obj) throws SQLException {
        dependentesDao.excluir(obj);
        limpar();
    }
    public void pesquisar() throws SQLException{
        dependentes = dependentesDao.pesquisar(dependentes);
    }
    
    public List<Dependentes> listar() throws SQLException {
        List<Dependentes> lista;
        lista = dependentesDao.listar(filtro);
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
     * @return the textFiltro
     */
    public String getTextFiltro() {
        return textFiltro;
    }

    /**
     * @param textFiltro the filtro to set
     */
    public void setTextFiltro(String textFiltro) {
        this.textFiltro = textFiltro;
    }
    
    
}
