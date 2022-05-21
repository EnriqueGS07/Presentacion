package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
public class ReservaInformacion implements Serializable{
    private @Getter @Setter int idReserva;
    private @Getter @Setter String nombreRecurso;
    private @Getter @Setter Timestamp solicitud;
    private @Getter @Setter Timestamp inicio;
    private @Getter @Setter Timestamp fin;
    private @Getter @Setter String usuario;
    private @Getter @Setter String programa;
    private @Getter @Setter boolean recurrente;
    private @Getter @Setter String estado;


    @Override
    public String toString(){
        return "ReservaInformación{ " + "id=" + idReserva + ", nombre recurso=" + nombreRecurso + ", solicitud=" + solicitud.toString()
                + ", inicio=" + inicio.toString() + ", fin=" + fin.toString() + ", usuario=" + usuario + ", programa=" + programa + ", recurrente=" + recurrente + "}";}

}
