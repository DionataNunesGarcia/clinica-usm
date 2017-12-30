package conversores;


import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.CategoriaUsm;
import persistencia.CategoriaDAO;




@FacesConverter(forClass = CategoriaUsm.class, value= "conversorCategoriaUsm")
public class ConversorCategoriaUsm implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String qtdPessoa) {
        CategoriaUsm categoriaUsm = null;
        try {
            CategoriaDAO categoriaDao = new CategoriaDAO();
            categoriaUsm = categoriaDao.getCategoriaPorQtdPessoa(qtdPessoa); 
        }
        catch (SQLException ex) {
            Logger.getLogger(ConversorCategoriaUsm.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categoriaUsm;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        CategoriaUsm categoriaUsm = (CategoriaUsm) value;
        return categoriaUsm.getQtdPessoa();
    }

}
