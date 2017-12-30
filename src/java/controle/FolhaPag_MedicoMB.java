package controle;

import java.sql.SQLException;
import java.util.List;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ManagedBean;
import modelo.CadastroMedico;
import modelo.FolhaPag_Medico;
import persistencia.CadastroMedicoDAO;
import persistencia.FolhaPag_MedicoDAO;


@ManagedBean
@RequestScoped

public class FolhaPag_MedicoMB {

    private FolhaPag_Medico folhaPag_Medico;
    private FolhaPag_MedicoDAO folhaPag_MedicoDao;
    private String filtro = "";
    private List<CadastroMedico> cadastroMedicos;
    private CadastroMedicoDAO cadastroMedicoDao;
    
   
    public FolhaPag_MedicoMB() throws SQLException {
        folhaPag_MedicoDao = new FolhaPag_MedicoDAO();
        folhaPag_Medico = new FolhaPag_Medico();
        cadastroMedicoDao = new CadastroMedicoDAO();
        cadastroMedicos = cadastroMedicoDao.listar("");
    }

    public FolhaPag_Medico getFolhaPag_Medico() {
        return folhaPag_Medico;
    }

    public void setFolhaPag_Medico(FolhaPag_Medico folhaPag_Medico) {
        this.folhaPag_Medico = folhaPag_Medico;
    }

    public FolhaPag_MedicoDAO getFolhaPag_MedicoDao() {
        return folhaPag_MedicoDao;
    }

    public void setFolhaPag_MedicoDao(FolhaPag_MedicoDAO folhaPag_MedicoDao) {
        this.folhaPag_MedicoDao = folhaPag_MedicoDao;
    }

    public List<CadastroMedico> getCadastroMedicos() {
        return cadastroMedicos;
    }

    public void setCadastroMedicos(List<CadastroMedico> cadastroMedicos) {
        this.cadastroMedicos = cadastroMedicos;
    }

    public CadastroMedicoDAO getCadastroMedicoDao() {
        return cadastroMedicoDao;
    }

    public void setCadastroMedicoDao(CadastroMedicoDAO cadastroMedicoDao) {
        this.cadastroMedicoDao = cadastroMedicoDao;
    }
    
    public void salvar() throws SQLException {
        if (folhaPag_Medico.getIdPagamentoMedico()== 0) {
            folhaPag_MedicoDao.salvar(folhaPag_Medico);
            limpar();
        } else {
            folhaPag_MedicoDao.alterar(folhaPag_Medico);
            limpar();
        }
    }

    public void limpar() {
        folhaPag_Medico = new FolhaPag_Medico();
    }

    public void alterar() throws SQLException {
        folhaPag_MedicoDao.alterar(folhaPag_Medico);
        limpar();
    }

    public void excluir() throws SQLException {
        folhaPag_MedicoDao.excluir(folhaPag_Medico);
        limpar();
    }

    public void excluir(FolhaPag_Medico obj) throws SQLException {
        folhaPag_MedicoDao.excluir(obj);
        limpar();
    }

    public void pesquisar() throws SQLException {
        folhaPag_Medico = folhaPag_MedicoDao.pesquisar(folhaPag_Medico);
    }

    public List<FolhaPag_Medico> listar() throws SQLException {
        List<FolhaPag_Medico> lista;
        lista = folhaPag_MedicoDao.listar(filtro);
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
    
    public void valorPagarMedico() throws SQLException{
        FolhaPag_Medico folhaPag_Medico1 = folhaPag_MedicoDao.pesquisarValorHoraMedico(folhaPag_Medico);
        
        float valorTotal = (folhaPag_Medico1.getValorHora()* folhaPag_Medico.getTotalHoras())+ folhaPag_Medico.getValorMais()-folhaPag_Medico.getValorDesconto();
        
        folhaPag_Medico.setValorTotal(valorTotal);
    }

}
