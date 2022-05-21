package managedbeans;

import com.google.inject.Inject;
import entities.Horario;
import lombok.Getter;
import lombok.Setter;
import services.ExceptionRecursosBiblioteca;
import services.RecursosBiblioteca;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.sql.Time;
import java.util.List;


/**
 * Bean correspondiente a la vista del consultar horarios de reservas
 *
 * @author LENS
 * @version 1.0
 */

@SuppressWarnings("deprecation")
@ManagedBean(name = "consultarHorariosBean")
@SessionScoped
public class ConsultarHorariosBean extends BasePageBean {

    @Getter @Setter Time hora_ini;
    @Getter @Setter Time hora_fin;
    @Getter @Setter int id;
    @Getter @Setter List<Horario> horarios;
    @Getter @Setter Horario horario;


    @Inject
    private RecursosBiblioteca recursosBiblioteca;

    public void loadHorarios(int id) {
        try {
            horarios = recursosBiblioteca.consultarHorario(id);
            redirect();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error ", e.getMessage()));
        }
    }

    public List<Horario> consultarHorario(int id) throws ExceptionRecursosBiblioteca {
        horarios = recursosBiblioteca.consultarHorario(id);
//        try {
//            horarios = recursosBiblioteca.consultarHorario(id);
//            return horarios;
//        } catch (Exception e) {
//            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error ",e.getMessage()));
//        }
        return horarios;
    }

    private void redirect(){
        try{
            FacesContext.getCurrentInstance().getExternalContext().redirect("/recursosBiblioteca/consultarHorarios.xhtml");
        }catch (Exception e){
            System.out.println("no fue posible");
        }

    }
}
