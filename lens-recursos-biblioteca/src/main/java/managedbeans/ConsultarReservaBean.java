package managedbeans;

import com.google.inject.Inject;
import entities.Reserva;
import entities.ReservaInformacion;
import entities.Usuario;
import services.RecursosBiblioteca;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@ManagedBean(name = "consultarReservaBean")
@SessionScoped
public class ConsultarReservaBean extends BasePageBean {
    @Inject
    private RecursosBiblioteca recursosBiblioteca;

    private List<ReservaInformacion> reservas;
    private ReservaInformacion reserva;
    private int id;
    private Usuario user;

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public void consultaExt(int idReserva) {
        user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
        //id = user.getId();
        reserva = recursosBiblioteca.consultarReservaExtendida(idReserva, user);
    }

    public void consultaLoad(int idReserva) throws IOException {
        consultaExt(idReserva);
        FacesContext.getCurrentInstance().getExternalContext().redirect("/recursosBiblioteca/consultarReserva.xhtml");
    }

    public List<ReservaInformacion> getReservas(){return reservas;}

    public void setReservas(List<ReservaInformacion> reservas){this.reservas = reservas;}

    public ReservaInformacion getReserva(){return reserva;}

    public void setReserva(ReservaInformacion reserva){this.reserva = reserva;}

    public void cancelarReserva(int id_reserva){
        recursosBiblioteca.cancelarReserva(id_reserva);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Correcto", "La reserva fue cancelada exitosamente"));
    }

    public boolean esCancelable(){
        return Objects.equals(reserva.getEstado(), "activo");
    }
}
