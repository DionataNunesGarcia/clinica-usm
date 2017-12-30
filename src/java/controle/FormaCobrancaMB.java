/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;
import modelo.Cliente;
import modelo.Convenio;
import modelo.FormaCobranca;
import persistencia.ClienteDAO;
import persistencia.ConvenioDAO;
import persistencia.FormaCobrancaDAO;

/**
 *
 * @author Administrador
 */
@ManagedBean
@RequestScoped
public class FormaCobrancaMB {

    private FormaCobranca formaCobranca;
    private FormaCobrancaDAO formaCobrancaDao;
    private String filtro = "";

    public FormaCobrancaMB() throws SQLException {
        formaCobrancaDao = new FormaCobrancaDAO();
        formaCobranca = new FormaCobranca();

    }

    public FormaCobranca getFormaCobranca() {
        return formaCobranca;
    }

    public void setFormaCobranca(FormaCobranca formaCobranca) {
        this.formaCobranca = formaCobranca;
    }

    public FormaCobrancaDAO getFormaCobrancaDao() {
        return formaCobrancaDao;
    }

    public void setFormaCobrancaDao(FormaCobrancaDAO formaCobrancaDao) {
        this.formaCobrancaDao = formaCobrancaDao;
    }

    public void salvar() throws SQLException {
        if (formaCobranca.getIdcobranca() == 0) {
            formaCobrancaDao.salvar(formaCobranca);
            limpar();
        } else {
            formaCobrancaDao.alterar(formaCobranca);
            limpar();
        }

    }

    public void limpar() {
        formaCobranca = new FormaCobranca();
    }

    public void alterar() throws SQLException {
        formaCobrancaDao.alterar(formaCobranca);
        limpar();
    }

    public void excluir() throws SQLException {
        formaCobrancaDao.excluir(formaCobranca);
        limpar();
    }

    public void excluir(FormaCobranca obj) throws SQLException {
        formaCobrancaDao.excluir(obj);
        limpar();
    }

    public void pesquisar() throws SQLException {
        formaCobranca = formaCobrancaDao.pesquisar(formaCobranca);
    }

    public List<FormaCobranca> listar() throws SQLException {

        List<FormaCobranca> lista;
        lista = formaCobrancaDao.listar(filtro);
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
