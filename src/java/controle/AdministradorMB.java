
package controle;

import java.sql.SQLException;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.Administrador;
import persistencia.AdministradorDAO;

@ManagedBean
@RequestScoped

public class AdministradorMB {

    private Administrador administrador;
    private AdministradorDAO administradorDao;
    private String filtro = "";

    public AdministradorMB() throws SQLException {

        administradorDao = new AdministradorDAO();
        administrador = new Administrador();

    }

    public Administrador getAdministrador() {

        return administrador;

    }

    public void setAdministrador(Administrador administrador) {

        this.administrador = administrador;

    }

    public String salvar() throws SQLException {
        if(administrador.getIdadm() == 0){
            administradorDao.salvar(administrador);
        limpar();
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage("Form", new FacesMessage("Novo administrador cadastrado com sucesso!"));

        return "CadastroAdministrador";

        }else{
            administradorDao.alterar(administrador);
        limpar();
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage("Form", new FacesMessage("Administrador alterado com sucesso!!"));
        
        return "CadastroAdministrador";
        }
            
        
    }

    public void pesquisar() throws SQLException {

        administrador = administradorDao.pesquisar(administrador);

    }

    public List<Administrador> listar() throws SQLException {

        List<Administrador> lista;
        lista = administradorDao.listar(filtro);
        return lista;

    }

     public String editarAdm(Object o){
        administrador = (Administrador) o;
        return "CadastroAdministrador";
    }
    
    
    public String alterar() throws SQLException {

        administradorDao.alterar(administrador);
        limpar();
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage("Form", new FacesMessage("Administrador alterado com sucesso!!"));
        
        return "CadastroAdministrador";

    }

    public void excluir() throws SQLException {

        administradorDao.excluir(administrador);

    }

    public void excluir(Administrador obj) throws SQLException {

        administradorDao.excluir(obj);
        limpar();

    }

    public void limpar() {

        administrador = new Administrador();

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

