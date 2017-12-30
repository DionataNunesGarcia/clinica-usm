package conversores;


import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.Cliente;
import persistencia.ClienteDAO;



@FacesConverter(forClass = Cliente.class, value = "conversorCliente")

public class ConversorCliente implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String nome) {
        Cliente cliente = null;

        try {
            ClienteDAO clienteDao = new ClienteDAO();
            cliente = clienteDao.getClientePorNome(nome);
        } catch (SQLException ex) {
            Logger.getLogger(ConversorCliente.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cliente;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Cliente cliente = (Cliente) value;
        return cliente.getNome();
    }
}