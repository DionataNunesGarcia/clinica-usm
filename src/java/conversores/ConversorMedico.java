package conversores;


import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.CadastroMedico;
import persistencia.CadastroMedicoDAO;


@FacesConverter(forClass = CadastroMedico.class, value= "conversorMedico")
public class ConversorMedico implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String nome) {
        CadastroMedico cadastroMedico = null;
        try {
            CadastroMedicoDAO cadastroMedicoDao = new CadastroMedicoDAO();
            cadastroMedico = cadastroMedicoDao.getCadastroMedicoPorNome(nome); 
        }
        catch (SQLException ex) {
            Logger.getLogger(ConversorMedico.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cadastroMedico;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        CadastroMedico cadastroMedico = (CadastroMedico) value;
        return cadastroMedico.getNome();
    }

}
