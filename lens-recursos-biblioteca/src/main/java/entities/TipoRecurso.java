package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Clase de Tipo de recurso de la biblioteca
 *Los posibles recursos son libro, sala de estudio y equipo de computo
 * @author LENS
 * @version 1.0
 */
@NoArgsConstructor
@AllArgsConstructor
public class TipoRecurso implements Serializable {
    private @Getter @Setter int id;
    private @Getter @Setter String nombre;


    /**
     * Funcion que ayuda a convertir el objeto a string
     * @return un String con la informacion del objeto
     */
    @Override
    public String toString(){
        return "Tipo{" + "idT=" + id + "nombreT=" + nombre + "}";
    }
}
