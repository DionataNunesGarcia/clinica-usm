package conversores;


import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import modelo.Funcionario;
import persistencia.FuncionarioDAO;




@FacesConverter(forClass = Funcionario.class, value = "conversorFuncionario")

public class ConversorFuncionario implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String nome) {
        Funcionario funcionario = null;

        try {
            FuncionarioDAO funcionarioDao = new FuncionarioDAO();
            funcionario = funcionarioDao.getFuncionarioPorNome(nome);
        } catch (SQLException ex) {
            Logger.getLogger(ConversorFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }

        return funcionario;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        Funcionario funcionario = (Funcionario) value;
        return funcionario.getNome();
    }
}