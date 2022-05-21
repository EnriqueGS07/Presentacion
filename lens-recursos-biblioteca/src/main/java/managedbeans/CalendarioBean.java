package managedbeans;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.*;
import javax.faces.context.FacesContext;

import entities.Recurso;
import entities.Usuario;
import lombok.Getter;
import lombok.Setter;
import com.google.inject.Inject;
import entities.Horario;
import entities.Reserva;
import org.primefaces.PrimeFaces;
import org.primefaces.component.submenu.UISubmenu;
import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleEvent;
import org.primefaces.model.ScheduleModel;
import services.ExceptionRecursosBiblioteca;
import services.RecursosBiblioteca;

/**
 * Bean correspondiente a la vista del consultar horarios de reservas
 *
 * @author LENS
 * @version 1.0
 */

@SuppressWarnings("deprecation")
@ManagedBean(name = "calendarioBean")
@ApplicationScoped
public class CalendarioBean extends BasePageBean {

    @Inject
    @Getter @Setter private RecursosBiblioteca recursosBiblioteca;
    @Getter @Setter private ScheduleModel eventModel = new DefaultScheduleModel();
    @Getter @Setter private DefaultScheduleEvent event = new DefaultScheduleEvent();
    @Getter @Setter private ScheduleEvent eventAux = new DefaultScheduleEvent();
    @Getter @Setter private List<Reserva> reservas;
    @Getter @Setter private Reserva reserva;
    @Getter @Setter public Recurso recurso;
    @Getter @Setter private ArrayList<String> recursos;
    @Getter @Setter private ArrayList<String> solicitudes;
    @Getter @Setter private String nombreR;
    @Getter @Setter private String solicitud;
    @Getter @Setter private int eventId = 0;

    /**
     * metodo que consulta las reservas
     *
     * @return lista de las reservas
     */
    public List<Reserva> consul() {
        reservas = recursosBiblioteca.consultarReservas();
        return reservas;
    }

    /**
     * metodo que carga los eventos al calendario
     *
     * @return modelo del calendario con los eventos cargados
     */
    public ScheduleModel consultar() {
        loadEvents();
        return eventModel;
    }

    /**
     * Funcion que obtiene la fecha de inicio de una reserva
     * @return Fecha de inicio de reserva
     */
    public Date getInicio(){
        return event.getStartDate();
    }

    /**
     * Funcion que obtiene la fecha de fin de una reserva
     * @return Fecha de fin de reserva
     */
    public Date getFin(){
        return event.getEndDate();
    }

    /**
     * Metodo que define que usuario esta consultamndo las reservas
     */
    public void consultarReservas(){

        if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user") != null){
            Usuario user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        }
    }

    /**
     * Metodo que cara las reservas de forma logica
     */
    public void loadEvents() {
        recursos = new ArrayList<String>();
        solicitudes = new ArrayList<String>();
        eventModel = new DefaultScheduleModel();

        if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user") != null){
            Usuario user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
            if(Objects.equals(user.getTipoUsuario(), "estudiante")){
                reservas = recursosBiblioteca.consultarReservasPorUsuario(user.getId());
            }else{
                reservas = recursosBiblioteca.consultarReservas();
            }
        }else{
            reservas = recursosBiblioteca.consultarReservas();
        }
        int pos = 0;
        for (Reserva h : reservas){
            event = new DefaultScheduleEvent(" " + h.getUsuario().getNombre(), h.getInicio(), h.getFin());
            recursos.add(recursosBiblioteca.consultarNombreRecurso(h.getIdRecurso()).getNombre());
            solicitudes.add(h.getSolicitud().toString());
            event.setDescription(recursosBiblioteca.consultarNombreRecurso(h.getIdRecurso()).getNombre());
            eventModel.addEvent(event);
            event.setId(String.valueOf(pos));
            pos ++;
        }
    }


    /**
     * Metodo que define que se hace cuando se selecciona un evento en especifico
     * @param selectEvent evento seleccionado
     */
    public void onEventSelect(SelectEvent selectEvent) {
        this.event = (DefaultScheduleEvent) selectEvent.getObject();
        this.eventId = Integer.parseInt(event.getId());
        this.nombreR = recursos.get(eventId);
        this.solicitud = solicitudes.get(eventId);
    }

    /**
     * Metodo que define que se hace cuando se mueve un evento en especifico
     * @param event evento a mover
     */
    public void onEventMove(ScheduleEntryMoveEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event moved", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
        PrimeFaces.current().dialog().showMessageDynamic(message);
        addMessage(message);
    }

    /**
     * Metodo que define que se hace cuando se k¿modifica un evento
     * @param event evento que se modifica
     */
    public void onEventResize(ScheduleEntryResizeEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Event resized", "Day delta:" + event.getDayDelta() + ", Minute delta:" + event.getMinuteDelta());
        PrimeFaces.current().dialog().showMessageDynamic(message);
    }

    /**
     * Metodo para mostrar un mensaje en la vista
     * @param message mensaje que se quiere mostrar
     */
    private void addMessage(FacesMessage message) {
        PrimeFaces.current().dialog().showMessageDynamic(message);
    }

}