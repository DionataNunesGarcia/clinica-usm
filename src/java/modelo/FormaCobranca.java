/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package modelo;

import java.util.Objects;

/**
 *
 * @author Administrador
 */
public class FormaCobranca {
    private int idcobranca;
    private String tipo;
    private String cobrador;
    

    public FormaCobranca() {
    }

    public int getIdcobranca() {
        return idcobranca;
    }

    public void setIdcobranca(int idcobranca) {
        this.idcobranca = idcobranca;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCobrador() {
        return cobrador;
    }

    public void setCobrador(String cobrador) {
        this.cobrador = cobrador;
    }

   
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.idcobranca;
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
        final FormaCobranca other = (FormaCobranca) obj;
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString(){
        return this.getTipo();
    }
}
