package modelo;

import java.util.Date;


public class FolhaPag_Medico {
    private int idPagamentoMedico;
    private float totalHoras;
    private Date mes;
    private CadastroMedico cadastroMedico;
    private float valorDesconto;
    private float valorMais;
    private float valorTotal;
    private String observacao;
    private float valorHora;
    

    public FolhaPag_Medico() {
    }

    public int getIdPagamentoMedico() {
        return idPagamentoMedico;
    }

    public void setIdPagamentoMedico(int idPagamentoMedico) {
        this.idPagamentoMedico = idPagamentoMedico;
    }

    public float getTotalHoras() {
        return totalHoras;
    }

    public void setTotalHoras(float totalHoras) {
        this.totalHoras = totalHoras;
    }

    public Date getMes() {
        return mes;
    }

    public void setMes(Date mes) {
        this.mes = mes;
    }

    public CadastroMedico getCadastroMedico() {
        return cadastroMedico;
    }

    public void setCadastroMedico(CadastroMedico cadastroMedico) {
        this.cadastroMedico = cadastroMedico;
    }

    public float getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public float getValorHora() {
        return valorHora;
    }

    public void setValorHora(float valorHora) {
        this.valorHora = valorHora;
    }

    public float getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(float valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public float getValorMais() {
        return valorMais;
    }

    public void setValorMais(float valorMais) {
        this.valorMais = valorMais;
    }
    
}
