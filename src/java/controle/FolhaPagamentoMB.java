package controle;

import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;
import modelo.FolhaPagamento;
import modelo.Funcionario;
import persistencia.FolhaPagamentoDAO;
import persistencia.FuncionarioDAO;

@ManagedBean
@RequestScoped

public class FolhaPagamentoMB {

    private FolhaPagamento folhaPagamento;
    private FolhaPagamentoDAO folhaPagamentoDao;
    private String filtro = "";
    private List<Funcionario> funcionarios;
    private FuncionarioDAO funcionarioDao;
    private Funcionario funcionario;
   
    public FolhaPagamentoMB() throws SQLException {
        folhaPagamentoDao = new FolhaPagamentoDAO();
        folhaPagamento = new FolhaPagamento();
        funcionarioDao = new FuncionarioDAO();
        funcionarios = funcionarioDao.listar("");
    }

    public FolhaPagamento getFolhaPagamento() {
        return folhaPagamento;
    }

    public void setFolhaPagamento(FolhaPagamento folhaPagamento) {
        this.folhaPagamento = folhaPagamento;
    }

    public FolhaPagamentoDAO getFolhaPagamentoDao() {
        return folhaPagamentoDao;
    }

    public void setFolhaPagamentoDao(FolhaPagamentoDAO folhaPagamentoDao) {
        this.folhaPagamentoDao = folhaPagamentoDao;
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

    public void salvar() throws SQLException {
        if (folhaPagamento.getIdFolhaPagamento() == 0) {
            folhaPagamentoDao.salvar(folhaPagamento);
            limpar();
        } else {
            folhaPagamentoDao.alterar(folhaPagamento);
            limpar();
        }
    }

    public void limpar() {
        folhaPagamento = new FolhaPagamento();
    }

    public void alterar() throws SQLException {
        folhaPagamentoDao.alterar(folhaPagamento);
        limpar();
    }

    public void excluir() throws SQLException {
        folhaPagamentoDao.excluir(folhaPagamento);
        limpar();
    }

    public void excluir(FolhaPagamento obj) throws SQLException {
        folhaPagamentoDao.excluir(obj);
        limpar();
    }

    public void pesquisar() throws SQLException {
        folhaPagamento = folhaPagamentoDao.pesquisar(folhaPagamento);
    }

    public List<FolhaPagamento> listar() throws SQLException {
        List<FolhaPagamento> lista;
        lista = folhaPagamentoDao.listar(filtro);
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
    
    public void valorPagar() throws SQLException{
        FolhaPagamento folhaPagamento1 = folhaPagamentoDao.pesquisarValorHora(folhaPagamento);
        FolhaPagamento folhaPagamento2 = folhaPagamentoDao.pesquisarValorHora50(folhaPagamento);
        FolhaPagamento folhaPagamento3 = folhaPagamentoDao.pesquisarValorHora100(folhaPagamento);
        
        float valorSalario = (folhaPagamento1.getValorHora() * folhaPagamento.getHorasMes());
        float valorHoras50 = (folhaPagamento2.getValorHora50() * folhaPagamento.getHoras50());
        float valorHoras100 = (folhaPagamento3.getValorHora100() * folhaPagamento.getHoras100());
        float valorHorasFal = (folhaPagamento.getHorasFaltosas() *  folhaPagamento1.getValorHora());
        float valorBruto = valorSalario + valorHoras50 + valorHoras100 + folhaPagamento.getValeTransporte()+ folhaPagamento.getInsalubre();
        float valorDescontos = valorHorasFal + folhaPagamento.getInss()+ folhaPagamento.getAdiantamento();
        float valorLiquido = valorBruto - valorDescontos;
                
        folhaPagamento.setTotalTrabalhadas(valorSalario);
        folhaPagamento.setTotal50(valorHoras50);
        folhaPagamento.setTotal100(valorHoras100);
        folhaPagamento.setLiquido(valorLiquido);
        folhaPagamento.setSalarioBruto(valorBruto);
        folhaPagamento.setDesconto(valorDescontos);        
    }

}