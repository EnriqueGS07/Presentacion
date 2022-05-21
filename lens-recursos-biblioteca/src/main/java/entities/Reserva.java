package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


/**
 * Clase de de reservas de recursos
 *
 * @author LENS
 * @version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
public class Reserva implements Serializable {
    private @Getter @Setter int id;
    private @Getter @Setter int idUsuario;
    private @Getter @Setter int idRecurso;
    private @Getter @Setter Usuario usuario;
    private @Getter @Setter Recurso recurso;
    private @Getter @Setter boolean recurrente;
    private @Getter @Setter String estado;
    private @Getter @Setter Timestamp solicitud;
    private @Getter @Setter Timestamp inicio;
    private @Getter @Setter Timestamp fin;
    private @Getter @Setter Time hIni;
    private @Getter @Setter Time hFin;
    private @Getter @Setter List<ReservaRecurrente> reservaRecurrentes;
    private @Getter @Setter List<Usuario> usuarios;
    private @Getter @Setter List<Recurso> recursos;

    /**
     * Funcion que ayuda a convertir el objeto a string
     * @return un String con la informacion del objeto
     */
    @Override
    public String toString(){
        return "Reserva" + "id=" + id + "inicio" + inicio.toString() + "}";
    }

}