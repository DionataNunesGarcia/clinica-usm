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

public class ConvenioMB {

    private Convenio convenio;
    private ConvenioDAO convenioDao;
    private List<Planos> planos;
    private PlanosDAO planosDao;
    private String filtro = "";

    public ConvenioMB() throws SQLException {

        convenio = new Convenio();
        convenioDao = new ConvenioDAO();
        planosDao = new PlanosDAO();
        planos = planosDao.listar("");
    }

    public Convenio getConvenio() {
        return convenio;
    }

    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }

    public ConvenioDAO getConvenioDao() {
        return convenioDao;
    }

    public void setConvenioDao(ConvenioDAO convenioDao) {
        this.convenioDao = convenioDao;
    }

    public List<Planos> getPlanos() {
        return planos;
    }

    public void setPlanos(List<Planos> planos) {
        this.planos = planos;
    }

    public PlanosDAO getPlanosDao() {
        return planosDao;
    }

    public void setPlanosDao(PlanosDAO planosDao) {
        this.planosDao = planosDao;
    }

    public void salvar() throws SQLException {
        if (convenio.getIdconvenio() == 0) {
            convenioDao.salvar(convenio);
            limpar();
        } else {
            convenioDao.alterar(convenio);
            limpar();
        }

    }

    public void limpar() {
        convenio = new Convenio();
    }

    public void alterar() throws SQLException {
        convenioDao.alterar(convenio);
        limpar();
    }

    public void excluir() throws SQLException {
        convenioDao.excluir(convenio);
        limpar();
    }

    public void excluir(Convenio obj) throws SQLException {
        convenioDao.excluir(obj);
        limpar();
    }

    public void pesquisar() throws SQLException {
        convenio = convenioDao.pesquisar(convenio);
    }

    public List<Convenio> listar() throws SQLException {

        List<Convenio> lista;
        lista = convenioDao.listar(filtro);
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
