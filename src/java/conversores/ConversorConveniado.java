package conversores;


import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.Conveniado;
import persistencia.ConveniadoDAO;



@FacesConverter(forClass = Conveniado.class, value = "conversorConveniado")

public class ConversorConveniado implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String nome) {
        Conveniado conveniado = null;

        try {
            ConveniadoDAO conveniadoDao = new ConveniadoDAO();
            conveniado = conveniadoDao.getConveniadoPorNome(nome);
        } catch (SQLException ex) {
            Logger.getLogger(ConversorConveniado.class.getName()).log(Level.SEVERE, null, ex);
        }

        return conveniado;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Conveniado conveniado = (Conveniado) value;
        return conveniado.getNome();
    }
}