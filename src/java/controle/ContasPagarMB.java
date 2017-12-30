package controle;

import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;
import modelo.ContasPagar;
import modelo.Fornecedor;
import modelo.Funcionario;
import persistencia.ContasPagarDAO;
import persistencia.FornecedorDAO;
import persistencia.FuncionarioDAO;

@ManagedBean
@RequestScoped

public class ContasPagarMB {

    private ContasPagar contasPagar;
    private ContasPagarDAO contasPagarDao;
    private String filtro = "";
    private List<Funcionario> funcionarios;
    private FuncionarioDAO funcionarioDao;
    private List<Fornecedor> fornecedores;
    private FornecedorDAO fornecedorDao;
    private String escolhe;
    private String fornecedor1;
    private String funcionario1;
    private String mostrar;
    private String idFornecedor;
    private String idFuncionario;

    public ContasPagarMB() throws SQLException {
        contasPagarDao = new ContasPagarDAO();
        contasPagar = new ContasPagar();
        funcionarioDao = new FuncionarioDAO();
        funcionarios = funcionarioDao.listar("");
        fornecedorDao = new FornecedorDAO();
        fornecedores = fornecedorDao.listar("");
    }

    public ContasPagar getContasPagar() {
        return contasPagar;
    }

    public void setContasPagar(ContasPagar contasPagar) {
        this.contasPagar = contasPagar;
    }

    public ContasPagarDAO getContasPagarDao() {
        return contasPagarDao;
    }

    public void setContasPagarDao(ContasPagarDAO contasPagarDao) {
        this.contasPagarDao = contasPagarDao;
    }

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public FuncionarioDAO getFuncionarioDao() {
        return funcionarioDao;
    }

    public void setFuncionarioDao(FuncionarioDAO funcionarioDao) {
        this.funcionarioDao = funcionarioDao;
    }

    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }

    public FornecedorDAO getFornecedorDao() {
        return fornecedorDao;
    }

    public void setFornecedorDao(FornecedorDAO fornecedorDao) {
        this.fornecedorDao = fornecedorDao;
    }

    public String getEscolhe() {
        return escolhe;
    }

    public void setEscolhe(String escolhe) {
        this.escolhe = escolhe;
    }

    public String getFornecedor1() {
        return fornecedor1;
    }

    public void setFornecedor1(String fornecedor1) {
        this.fornecedor1 = fornecedor1;
    }

    public String getFuncionario1() {
        return funcionario1;
    }

    public void setFuncionario1(String funcionario1) {
        this.funcionario1 = funcionario1;
    }

    public String getMostrar() {
        return mostrar;
    }

    public void setMostrar(String mostrar) {
        this.mostrar = mostrar;
    }

    public String getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(String idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public String getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(String idFuncionario) {
        this.idFuncionario = idFuncionario;
    }
    
    
    public void salvar() throws SQLException {
        if (contasPagar.getIdContasPagar() == 0) {
            contasPagarDao.salvar(contasPagar);
            limpar();
        } else {
            contasPagarDao.alterar(contasPagar);
            limpar();
        }

    }

    public void limpar() {
        contasPagar = new ContasPagar();
    }

    public void alterar() throws SQLException {
        contasPagarDao.alterar(contasPagar);
        limpar();
    }

    public void excluir() throws SQLException {
        contasPagarDao.excluir(contasPagar);
        limpar();
    }

    public void excluir(ContasPagar obj) throws SQLException {
        contasPagarDao.excluir(obj);
        limpar();
    }

    public void pesquisar() throws SQLException {
        contasPagar = contasPagarDao.pesquisar(contasPagar);
    }

    public List<ContasPagar> listar() throws SQLException {
        List<ContasPagar> lista;
        lista = contasPagarDao.listar(filtro);
        return lista;
    }

    public void metodoAjax() {
        if (escolhe == fornecedor1) {
            mostrar = idFornecedor;
        } else {
            if (escolhe == funcionario1) {
                mostrar = idFuncionario;
            }
        }

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
