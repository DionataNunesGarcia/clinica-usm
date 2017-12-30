

package modelo;

import java.util.Date;
import java.util.Objects;




public class Dependentes {
    private int iddependentes;
    private String nome;
    private String cpf;
    private Date data_nascimento;
    private String sexo;
    private Cliente cliente;
    private Convenio convenio;

    public int getIddependentes() {
        return iddependentes;
    }

    public void setIddependentes(int iddependentes) {
        this.iddependentes = iddependentes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getData_nascimento() {
        return data_nascimento;
    }

    public void setData_nascimento(Date data_nascimento) {
        this.data_nascimento = data_nascimento;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Convenio getConvenio() {
        return convenio;
    }

    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.iddependentes;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dependentes other = (Dependentes) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }
    
    
    
    @Override
    public String toString(){
        return this.getNome();
    }
            
}
