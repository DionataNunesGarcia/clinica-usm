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
import modelo.Convenio;
import modelo.Planos;
import persistencia.ConvenioDAO;
import persistencia.PlanosDAO;

@ManagedBean
@RequestScoped
public class PlanosMB {
    
    private Planos planos;
    private  PlanosDAO planosDao;
    private List<Convenio> convenio;    
    private ConvenioDAO convenioDao;
    private String filtro = "";
    
    public PlanosMB() throws SQLException {
        
        planos = new Planos();
        planosDao = new PlanosDAO();        
    }

    public Planos getPlanos() {
        return planos;
    }

    public void setPlanos(Planos planos) {
        this.planos = planos;
    }

    public PlanosDAO getPlanosDao() {
        return planosDao;
    }

    public void setPlanosDao(PlanosDAO planosDao) {
        this.planosDao = planosDao;
    }

    public List<Convenio> getConvenio() {
        return convenio;
    }

    public void setConvenio(List<Convenio> convenio) {
        this.convenio = convenio;
    }

    public ConvenioDAO getConvenioDao() {
        return convenioDao;
    }

    public void setConvenioDao(ConvenioDAO convenioDao) {
        this.convenioDao = convenioDao;
    }
    
    public void salvar() throws SQLException {
       if(planos.getIdplano() == 0){
           planosDao.salvar(planos);
       limpar();
       }        
       else{
           planosDao.alterar(planos);
        limpar();
       }
    }
    public void limpar(){
        planos = new Planos();
    }
        
    public void alterar() throws SQLException{
        planosDao.alterar(planos);
        limpar();
    }
    
    public void excluir() throws SQLException{
        planosDao.excluir(planos);
        limpar();
    }
    
    public void excluir(Planos obj) throws SQLException {
        planosDao.excluir(obj);
        limpar();
    }
    
    public void pesquisar() throws SQLException{
        planos = planosDao.pesquisar(planos);
    }
    
    public List<Planos> listar() throws SQLException {

        List<Planos> lista;
        lista = planosDao.listar(filtro);
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
