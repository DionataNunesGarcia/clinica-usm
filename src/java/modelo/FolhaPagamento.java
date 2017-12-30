

package modelo;

import java.util.Date;


public class FolhaPagamento {
    private int idFolhaPagamento;
    private float horasMes;
    private float horas50;
    private float horas100;
    private float horasFaltosas;
    private float valeTransporte;
    private float inss;
    private float insalubre;
    private float salarioBruto;
    private float adiantamento;
    private float outros;
    private float desconto;
    private float red;
    private float liquido;
    private String observacao;
    private Date mes;
    private Funcionario funcionario;
    private String cargo;
    private float valorHora;
    private float valorHora50;
    private float valorHora100;
    private float totalTrabalhadas;
    private float total50;
    private float total100;

    public FolhaPagamento() {
    }

    public int getIdFolhaPagamento() {
        return idFolhaPagamento;
    }

    public void setIdFolhaPagamento(int idFolhaPagamento) {
        this.idFolhaPagamento = idFolhaPagamento;
    }

    public float getHorasMes() {
        return horasMes;
    }

    public void setHorasMes(float horasMes) {
        this.horasMes = horasMes;
    }

    public float getHoras50() {
        return horas50;
    }

    public void setHoras50(float horas50) {
        this.horas50 = horas50;
    }

    public float getHoras100() {
        return horas100;
    }

    public void setHoras100(float horas100) {
        this.horas100 = horas100;
    }

    public float getHorasFaltosas() {
        return horasFaltosas;
    }

    public void setHorasFaltosas(float horasFaltosas) {
        this.horasFaltosas = horasFaltosas;
    }

    public float getValeTransporte() {
        return valeTransporte;
    }

    public void setValeTransporte(float valeTransporte) {
        this.valeTransporte = valeTransporte;
    }

    public float getInss() {
        return inss;
    }

    public void setInss(float inss) {
        this.inss = inss;
    }

    public float getInsalubre() {
        return insalubre;
    }

    public void setInsalubre(float insalubre) {
        this.insalubre = insalubre;
    }

    public float getSalarioBruto() {
        return salarioBruto;
    }

    public void setSalarioBruto(float salarioBruto) {
        this.salarioBruto = salarioBruto;
    }

    public float getAdiantamento() {
        return adiantamento;
    }

    public void setAdiantamento(float adiantamento) {
        this.adiantamento = adiantamento;
    }

    public float getOutros() {
        return outros;
    }

    public void setOutros(float outros) {
        this.outros = outros;
    }

    public float getDesconto() {
        return desconto;
    }

    public void setDesconto(float desconto) {
        this.desconto = desconto;
    }

    public float getRed() {
        return red;
    }

    public void setRed(float red) {
        this.red = red;
    }

    public float getLiquido() {
        return liquido;
    }

    public void setLiquido(float liquido) {
        this.liquido = liquido;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Date getMes() {
        return mes;
    }

    public void setMes(Date mes) {
        this.mes = mes;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public float getValorHora() {
        return valorHora;
    }

    public void setValorHora(float valorHora) {
        this.valorHora = valorHora;
    }

    public float getValorHora50() {
        return valorHora50;
    }

    public void setValorHora50(float valorHora50) {
        this.valorHora50 = valorHora50;
    }

    public float getValorHora100() {
        return valorHora100;
    }

    public void setValorHora100(float valorHora100) {
        this.valorHora100 = valorHora100;
    }

    public float getTotalTrabalhadas() {
        return totalTrabalhadas;
    }

    public void setTotalTrabalhadas(float totalTrabalhadas) {
        this.totalTrabalhadas = totalTrabalhadas;
    }

    public float getTotal50() {
        return total50;
    }

    public void setTotal50(float total50) {
        this.total50 = total50;
    }

    public float getTotal100() {
        return total100;
    }

    public void setTotal100(float total100) {
        this.total100 = total100;
    }
       
}
