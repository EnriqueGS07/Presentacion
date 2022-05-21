package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * Clase de usuario de la biblioteca
 * los posibles usuarios son administrador y estudiante
 * @author LENS
 * @version 1.0
 */

@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements Serializable {
    private @Getter @Setter int id;
    private @Getter @Setter String tipoUsuario;
    private @Getter @Setter String nombre;
    private @Getter @Setter String programa;
    private @Getter @Setter String correo;
    private @Getter @Setter String contrasena;

    /**
     * Funcion que ayuda a convertir el objeto a string
     * @return un String con la informacion del objeto
     */
    @Override
    public String toString(){
        return "Usuario{" + "id=" + id + "tipoUsuario=" + tipoUsuario + "nombre=" + nombre + "programa=" + programa + "}";
    }
}
