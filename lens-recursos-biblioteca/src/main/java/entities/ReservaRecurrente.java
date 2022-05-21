package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Clase de reservas recurrentes de recursos
 *
 * @author LENS
 * @version 1.0
 */

@NoArgsConstructor
@AllArgsConstructor
public class ReservaRecurrente implements Serializable {
    private @Getter @Setter int id;
    private @Getter @Setter String periodo;
    private @Getter @Setter String fin;

    /**
     * Funcion que ayuda a convertir el objeto a string
     * @return un String con la informacion del objeto
     */
    @Override
    public String toString(){
        return "ReservaRe{" + "id=" + id + "periodo=" + periodo + "fin=" + fin + "}";
    }
}
