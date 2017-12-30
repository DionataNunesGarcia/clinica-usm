package controle;

import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import modelo.Fornecedor;
import persistencia.FornecedorDAO;


@ManagedBean
@RequestScoped

public class FornecedorMB {

    private Fornecedor fornecedor;
    private FornecedorDAO fornecedorDao;
    private String filtro = "";

    public FornecedorMB() throws SQLException {

        fornecedor = new Fornecedor();
        fornecedorDao = new FornecedorDAO();

    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public FornecedorDAO getFornecedorDao() {
        return fornecedorDao;
    }

    public void setFornecedorDao(FornecedorDAO fornecedorDao) {
        this.fornecedorDao = fornecedorDao;
    }

    public void salvar() throws SQLException {
        if (fornecedor.getIdfornecedor() == 0) {
            fornecedorDao.salvar(fornecedor);
            limpar();
        } else {
            fornecedorDao.alterar(fornecedor);
            limpar();
        }

    }

    public void limpar() {
        fornecedor = new Fornecedor();
    }

    public void alterar() throws SQLException {
        fornecedorDao.alterar(fornecedor);
        limpar();
    }

    public void excluir() throws SQLException {
        fornecedorDao.excluir(fornecedor);
        limpar();
    }

    public void excluir(Fornecedor obj) throws SQLException {
        fornecedorDao.excluir(obj);
        limpar();
    }

    public List<Fornecedor> listar() throws SQLException {

        List<Fornecedor> lista;
        lista = fornecedorDao.listar(filtro);
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
