/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controle;

import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.CadastroMedico;
import persistencia.CadastroMedicoDAO;


@ManagedBean
@RequestScoped
public class CadastroMedicoMB {
    
    private CadastroMedico cadastroMedico;
    private CadastroMedicoDAO cadastroMedicoDao;
   
    private String filtro = "";
    
    public CadastroMedicoMB() throws SQLException {
        
        cadastroMedico = new CadastroMedico();
        cadastroMedicoDao = new CadastroMedicoDAO();        
    }

    public CadastroMedico getCadastroMedico() {
        return cadastroMedico;
    }

    public void setCadastroMedico(CadastroMedico cadastroMedico) {
        this.cadastroMedico = cadastroMedico;
    }

    public CadastroMedicoDAO getCadastroMedicoDao() {
        return cadastroMedicoDao;
    }

    public void setCadastroMedicoDao(CadastroMedicoDAO cadastroMedicoDao) {
        this.cadastroMedicoDao = cadastroMedicoDao;
    }
        
    public void salvar() throws SQLException {
       if(cadastroMedico.getIdCadastroMedico()== 0){
           cadastroMedicoDao.salvar(cadastroMedico);
       limpar();
       }        
       else{
           cadastroMedicoDao.alterar(cadastroMedico);
        limpar();
       }
    }
    public void limpar(){
        cadastroMedico = new CadastroMedico();
    }
        
    public void alterar() throws SQLException{
        cadastroMedicoDao.alterar(cadastroMedico);
        limpar();
    }
    
    public void excluir() throws SQLException{
        cadastroMedicoDao.excluir(cadastroMedico);
        limpar();
    }
    
    public void excluir(CadastroMedico obj) throws SQLException {
        cadastroMedicoDao.excluir(obj);
        limpar();
    }
    
    public void pesquisar() throws SQLException{
        cadastroMedico = cadastroMedicoDao.pesquisar(cadastroMedico);
    }
    
    public List<CadastroMedico> listar() throws SQLException {

        List<CadastroMedico> lista;
        lista = cadastroMedicoDao.listar(filtro);
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
