

package modelo;

import java.util.Objects;


public class CategoriaUsm {
    private int idcategoria;
    private String qtdPessoa;
    private float valor;

    public CategoriaUsm() {
    }

    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getQtdPessoa() {
        return qtdPessoa;
    }

    public void setQtdPessoa(String qtdPessoa) {
        this.qtdPessoa = qtdPessoa;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.idcategoria;
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
        final CategoriaUsm other = (CategoriaUsm) obj;
        if (!Objects.equals(this.qtdPessoa, other.qtdPessoa)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString(){
        return this.getQtdPessoa();
    }
}
