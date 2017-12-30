package conversores;


import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.Dependentes;
import persistencia.DependentesDAO;


@FacesConverter(forClass = Dependentes.class, value= "ConversorDependentes")
public class ConversorDependente implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String nome) {
        Dependentes dependentes = null;
        try {
            DependentesDAO dependentesDao = new DependentesDAO();
            dependentes = dependentesDao.getDependentesPorNome(nome); 
        }
        catch (SQLException ex) {
            Logger.getLogger(ConversorDependente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dependentes;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Dependentes dependentes = (Dependentes) value;
        return dependentes.getNome();
    }

}
