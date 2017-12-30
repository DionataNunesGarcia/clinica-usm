package conversores;


import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.FormaCobranca;
import persistencia.FormaCobrancaDAO;



@FacesConverter(forClass = FormaCobranca.class, value = "conversorFormaCobranca")

public class ConversorFormaCobranca implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String tipo) {
        FormaCobranca formaCobranca = null;

        try {
            FormaCobrancaDAO formaCobrancaDao = new FormaCobrancaDAO();
            formaCobranca = formaCobrancaDao.getFormaCobrancaPorTipo(tipo);
        } catch (SQLException ex) {
            Logger.getLogger(ConversorFormaCobranca.class.getName()).log(Level.SEVERE, null, ex);
        }

        return formaCobranca;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        FormaCobranca formaCobranca = (FormaCobranca) value;
        return formaCobranca.getTipo();
    }
}