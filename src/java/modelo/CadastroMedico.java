

package modelo;

import java.util.Objects;


public class CadastroMedico {
    private int idCadastroMedico;
    private String nome;
    private float valorHora;

    public CadastroMedico() {
    }

    public int getIdCadastroMedico() {
        return idCadastroMedico;
    }

    public void setIdCadastroMedico(int idCadastroMedico) {
        this.idCadastroMedico = idCadastroMedico;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public float getValorHora() {
        return valorHora;
    }

    public void setValorHora(float valorHora) {
        this.valorHora = valorHora;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.idCadastroMedico;
        hash = 53 * hash + Objects.hashCode(this.nome);
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
        final CadastroMedico other = (CadastroMedico) obj;
        if (this.idCadastroMedico != other.idCadastroMedico) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CadastroMedico{" + "nome=" + nome + '}';
    }
    
}
