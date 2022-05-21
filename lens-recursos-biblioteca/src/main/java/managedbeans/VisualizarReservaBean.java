package managedbeans;


import com.google.inject.Inject;
import entities.Recurso;
import entities.Reserva;
import entities.Usuario;
import lombok.Getter;
import lombok.Setter;
import services.RecursosBiblioteca;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.sql.Time;
import java.util.List;

/**
 * Bean correspondiente a la vista de visualizar reservas
 * @author LENS
 * @version 1.0
 */
@SuppressWarnings("deprecation")
@ManagedBean(name = "visualizarReservaBean")
@SessionScoped
public class VisualizarReservaBean extends BasePageBean{

    @Inject
    private RecursosBiblioteca recursosBiblioteca;

    @Getter @Setter Recurso recurso;
    @Getter @Setter Time inicio = new Time(System.currentTimeMillis());
    @Getter @Setter Time fin;
    @Getter @Setter boolean recurrente;
    @Getter @Setter String estado;
    @Getter @Setter private Usuario user;
    @Getter @Setter private int userId;
    @Getter @Setter String solicitud;
    @Getter @Setter List<Reserva> reservas;
    @Getter @Setter Reserva reserva;

    /**
     * Asigna a reservas las activas del usuario
     */
    public void consAct(){
        user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        userId = user.getId();
        reservas = recursosBiblioteca.consultarReservasActivas(1);
    }

    /**
     * Asigna a reservas las canceladas del usuario
     */
    public void consCanc(){
        user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        userId = user.getId();
        reservas = recursosBiblioteca.consultarReservasCanceladas(user.getId());
    }

    /**
     * Asigna a reservas las pasadas del usuario
     */
    public void consPas(){
        user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        userId = user.getId();
        reservas = recursosBiblioteca.consultarReservasPasadas(user.getId());
    }

}
