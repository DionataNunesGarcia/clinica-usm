package controle;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Dependentes;
import persistencia.DependentesDAO;

@ManagedBean
@ViewScoped
public class DependenteClienteMB {

    private Dependentes dependentes;
    private DependentesDAO dependentesDao;
    private String filtro = "";
    private String dependentesPorClie = "A";
    private List<String> lista;

    public DependenteClienteMB() throws SQLException {
        dependentesDao = new DependentesDAO();
        dependentes = new Dependentes();

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

    public String getDependentesPorClie() {
        return dependentesPorClie;
    }

    public void setDependentesPorClie(String dependentesPorClie) {
        this.dependentesPorClie = dependentesPorClie;
    }

    public List<String> getLista() {
        return lista;
    }

    public void setLista(List<String> lista) {
        this.lista = lista;
    }

    public void pesquisarPorClie() throws SQLException {
        dependentes = dependentesDao.pesquisarPorClie(dependentes);
    }

    public void metodoAjax() {
        lista = new ArrayList<String>();
        if (dependentesPorClie.equalsIgnoreCase("S")) {
            lista.add("dependentesPorClie S");
        } else {
            lista.add("dependentesPorClie N");
        }
    }

    public List<Dependentes> listarPorCliente() throws SQLException {
        List<Dependentes> lista;
        lista = dependentesDao.listar(filtro);
        return lista;

    }

//    public List<Dependentes> listarDepenPorClie() throws SQLException {
//        ArrayList<Dependentes> lista = new ArrayList();
//        lista.addAll(dependentesDao.listarDepenPorClie(filtro));
//        return lista;
//
//    }

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
