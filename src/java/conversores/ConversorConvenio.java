package conversores;


import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.Convenio;
import persistencia.ConvenioDAO;



@FacesConverter(forClass = Convenio.class, value = "conversorConvenio")

public class ConversorConvenio implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String nome) {
        Convenio convenio = null;

        try {
            ConvenioDAO convenioDao = new ConvenioDAO();
            convenio = convenioDao.getConvenioPorNome(nome);
        } catch (SQLException ex) {
            Logger.getLogger(ConversorConvenio.class.getName()).log(Level.SEVERE, null, ex);
        }

        return convenio;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Convenio convenio = (Convenio) value;
        return convenio.getNome();
    }
}