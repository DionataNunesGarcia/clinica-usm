
package modelo;

import java.util.Objects;



public class CargoFuncionario {
    private int idCargoFuncionario;
    private String cargo;
    private float valorHora;

    public CargoFuncionario() {
    }

    public int getIdCargoFuncionario() {
        return idCargoFuncionario;
    }

    public void setIdCargoFuncionario(int idCargoFuncionario) {
        this.idCargoFuncionario = idCargoFuncionario;
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.idCargoFuncionario;
        hash = 59 * hash + Objects.hashCode(this.cargo);
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
        final CargoFuncionario other = (CargoFuncionario) obj;
        if (this.idCargoFuncionario != other.idCargoFuncionario) {
            return false;
        }
        if (!Objects.equals(this.cargo, other.cargo)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CargoFuncionario{" + "cargo=" + cargo + '}';
    }
    
    
}
