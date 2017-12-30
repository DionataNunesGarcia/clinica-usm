package controle;

import java.io.Serializable;  
import java.util.ArrayList;  
import java.util.List;  
import javax.el.ValueExpression;  
import javax.faces.bean.ManagedBean;  
import javax.faces.bean.ViewScoped;  
import javax.faces.component.html.HtmlInputText;  
import javax.faces.component.html.HtmlPanelGrid;  
import javax.faces.context.FacesContext;  
  
@ManagedBean  
@ViewScoped  
public class Binding implements Serializable{  
  
    private List<String> arr = new ArrayList<String>();  
    private HtmlPanelGrid htmlPanelGrid = new HtmlPanelGrid();  
  
    public List<String> getArr() {  
        return arr;  
    }  
  
    public void setArr(List<String> arr) {  
        this.arr = arr;  
    }  
  
    public HtmlPanelGrid getHtmlPanelGrid() {  
        return htmlPanelGrid;  
    }  
  
    public void setHtmlPanelGrid(HtmlPanelGrid htmlPanelGrid) {  
        this.htmlPanelGrid = htmlPanelGrid;  
    }  
  
    public void adiciona() {  
        HtmlInputText htmlInputText = new HtmlInputText();  
        htmlInputText.setValueExpression("value", createValueExpression("#{binding.arr[" + (arr.size()) + "]}", String.class));  
        arr.add("");          
        this.getHtmlPanelGrid().getChildren().add(htmlInputText);  
    }  
  
    public void imprime() {  
        for (String string : arr) {  
            System.out.println(string);  
        }  
    }  
      
    public ValueExpression createValueExpression(String elExpression, Class<?> elClass) {  
        return FacesContext.getCurrentInstance().getApplication().getExpressionFactory().createValueExpression(FacesContext.getCurrentInstance().getELContext(), elExpression, elClass);  
    }  
}  