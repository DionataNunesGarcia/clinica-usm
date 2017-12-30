package conversores;


import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.Planos;
import persistencia.PlanosDAO;


@FacesConverter(forClass = Planos.class, value= "conversorPlanos")
public class ConversorPlano implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String nome) {
        Planos planos = null;
        try {
            PlanosDAO planosDao = new PlanosDAO();
            planos = planosDao.getPlanosPorNome(nome); 
        }
        catch (SQLException ex) {
            Logger.getLogger(ConversorPlano.class.getName()).log(Level.SEVERE, null, ex);
        }
        return planos;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Planos planos = (Planos) value;
        return planos.getNome();
    }

}
