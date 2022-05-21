package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Clase de horario de reservas o de recursos de la biblioteca
 *
 * @author LENS
 * @version 1.0
 */

@NoArgsConstructor
@AllArgsConstructor
public class Horario implements Serializable {

    private @Getter @Setter int id;
    private @Getter @Setter Recurso idRecurso;
    private @Getter @Setter String estado;
    private @Getter @Setter Time hora_ini;
    private @Getter @Setter Time hora_fin;

    /**
     * Funcion que ayuda a convertir el objeto a string
     * @return un String con la informacion del objeto
     */
    @Override
    public String toString(){
        return "Horario{" + "id=" + id + " idRecurso=" + idRecurso + " estado=" + estado+ " hora inicio=" + hora_ini + " hora fin=" + hora_fin + "}";
    }
}
