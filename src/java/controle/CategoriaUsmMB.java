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
import modelo.CategoriaUsm;
import modelo.Convenio;
import modelo.Planos;
import persistencia.CategoriaDAO;
import persistencia.ConvenioDAO;
import persistencia.PlanosDAO;

@ManagedBean
@RequestScoped
public class CategoriaUsmMB {

    private CategoriaUsm categoriaUsm;
    private CategoriaDAO categoriaDao;
    private Planos planos;
    private PlanosDAO planosDao;
    private List<Convenio> convenio;
    private ConvenioDAO convenioDao;
    private String filtro = "";

    public CategoriaUsmMB() throws SQLException {

        categoriaUsm = new CategoriaUsm();
        categoriaDao = new CategoriaDAO();
    }

    public CategoriaUsm getCategoriaUsm() {
        return categoriaUsm;
    }

    public void setCategoriaUsm(CategoriaUsm categoriaUsm) {
        this.categoriaUsm = categoriaUsm;
    }

    public CategoriaDAO getCategoriaDao() {
        return categoriaDao;
    }

    public void setCategoriaDao(CategoriaDAO categoriaDao) {
        this.categoriaDao = categoriaDao;
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
        if (categoriaUsm.getIdcategoria() == 0) {
            categoriaDao.salvar(categoriaUsm);
            limpar();
        } else {
            categoriaDao.alterar(categoriaUsm);
            limpar();
        }
    }

    public void limpar() {
        categoriaUsm = new CategoriaUsm();
    }

    public void alterar() throws SQLException {
        categoriaDao.alterar(categoriaUsm);
        limpar();
    }

    public void excluir() throws SQLException {
        categoriaDao.excluir(categoriaUsm);
        limpar();
    }

    public void excluir(CategoriaUsm obj) throws SQLException {
        categoriaDao.excluir(obj);
        limpar();
    }

    public List<CategoriaUsm> listar() throws SQLException {

        List<CategoriaUsm> lista;
        lista = categoriaDao.listar(filtro);
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
