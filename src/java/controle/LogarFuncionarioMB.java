package controle;

import java.sql.SQLException;
import javax.faces.bean.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import modelo.Funcionario;
import persistencia.FuncionarioDAO;

@ManagedBean
@SessionScoped
public class LogarFuncionarioMB {

    private Funcionario funcionario;
    private FuncionarioDAO funcionarioDao;
    private String mensagem;

    public LogarFuncionarioMB() throws SQLException {
        funcionario = new Funcionario();
        funcionarioDao = new FuncionarioDAO();
        mensagem = "";
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
    
    
    
    
    
    
    
    

   /* public String logar() throws SQLException {

        Funcionario usuarioLogar = funcionarioDao.logar(funcionario);

        FacesContext facesContext = FacesContext.getCurrentInstance();

        if (usuarioLogar != null) {
            facesContext.addMessage("loginForm", new FacesMessage("Seja bem vindo " + usuarioLogar.getNome()));
            if (usuarioLogar.getTipo().equals("administrador")) {
                return "menuAdmin";
            } else {
                if (usuarioLogar.getTipo().equals("usuario")) {
                    return "menuUsuario";
                }
                
            }

            facesContext.addMessage("loginForm", new FacesMessage("ERRO!! Verifique as informações digitadas login e senha e faça nova conecção!"));
            return "PaginaInicio";
        }
    }
      public String logar() throws SQLException {
         if (funcionarioDao.logar(this.funcionario) == null) {
         FacesContext facesContext = FacesContext.getCurrentInstance();
         facesContext.addMessage("loginForm", new FacesMessage("ERRO!! Verifique as informações digitadas usuário e senha e faça nova conecção!"));
         return "index";

         } else {
         mensagem = "";
         }
         //Joga o usuário na sessão.
         FacesContext facesContext = FacesContext.getCurrentInstance();
         HttpSession sessaoHttp = (HttpSession) facesContext.getExternalContext().getSession(true);
         sessaoHttp.setAttribute("usuarioLogado", this.funcionario);
         if (funcionario != null) {
         facesContext.addMessage("loginForm", new FacesMessage("Seja bem vindo " + funcionario.getUsuario()));
         if (funcionario.getTipo().equals("administrador")) {
         return "menu";
         } else {
         if (funcionario.getTipo().equals("usuario")) {
         return "menuUsuario";
         } else {
         if (funcionario.getTipo().equals("cliente")) {
         return "menuUsuario";
         }
         }
         }

            
         }
         return null;

         }*/
    }
