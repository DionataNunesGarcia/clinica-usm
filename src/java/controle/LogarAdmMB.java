/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.sql.SQLException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import modelo.Administrador;
import persistencia.AdministradorDAO;

/**
 *
 * @author douglas
 */
@ManagedBean
@SessionScoped
public class LogarAdmMB {

    private Administrador administrador;
    private AdministradorDAO administradorDao;
    private String mensagem;

    public LogarAdmMB() throws SQLException {
        
        administradorDao = new AdministradorDAO();
        administrador = new Administrador();
        mensagem = "";
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public AdministradorDAO getAdministradorDao() {
        return administradorDao;
    }

    public void setAdministradorDao(AdministradorDAO administradorDao) {
        this.administradorDao = administradorDao;
    } 
    
    public void limpar() {

        administrador = new Administrador();

    }
   
    
    public String login() throws SQLException {
        if (administradorDao.logar(this.administrador) == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            facesContext.addMessage("Form", new FacesMessage("ERRO!! Verifique as informações digitadas usuário e senha e faça nova conecção!"));
            limpar();
            return "login";
            
        } else {
            mensagem = "";
        }
        //Joga o usuário na sessão.
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession sessaoHttp = (HttpSession) facesContext.getExternalContext().getSession(true);
        sessaoHttp.setAttribute("usuarioLogado", this.administrador);         
        return "Home";
    }
    
}
