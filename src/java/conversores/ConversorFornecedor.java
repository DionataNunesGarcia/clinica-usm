package conversores;


import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.Fornecedor;
import persistencia.FornecedorDAO;




@FacesConverter(forClass = Fornecedor.class, value = "conversorFornecedor")

public class ConversorFornecedor implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String razaoSocial) {
        Fornecedor fornecedor = null;

        try {
            FornecedorDAO fornecedorDao = new FornecedorDAO();
            fornecedor = fornecedorDao.getFornecedorPorRazaoSocial(razaoSocial);
        } catch (SQLException ex) {
            Logger.getLogger(ConversorFornecedor.class.getName()).log(Level.SEVERE, null, ex);
        }

        return fornecedor;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Fornecedor fornecedor = (Fornecedor) value;
        return fornecedor.getRazaoSocial();
    }
}