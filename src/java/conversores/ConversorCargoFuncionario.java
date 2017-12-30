package conversores;


import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.CargoFuncionario;
import persistencia.CargoFuncionarioDAO;



@FacesConverter(forClass = CargoFuncionario.class, value = "conversorCargoFuncionario")

public class ConversorCargoFuncionario implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String tipo) {
        CargoFuncionario cargoFuncionario = null;

        try {
            CargoFuncionarioDAO cargoFuncionarioDao = new CargoFuncionarioDAO();
            cargoFuncionario = cargoFuncionarioDao.getCargoFuncionarioPorTipo(tipo);
        } catch (SQLException ex) {
            Logger.getLogger(ConversorCargoFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cargoFuncionario;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        CargoFuncionario cargoFuncionario = (CargoFuncionario) value;
        return cargoFuncionario.getCargo();
    }
}