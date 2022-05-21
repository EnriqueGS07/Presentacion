package services;

/**
 * Clase de horario de excepciones de la aplicación
 * @author LENS
 * @version 1.0
 */
public class ExceptionRecursosBiblioteca extends Exception{

    /**
     * Metodo que crea el mensaje de eerror
     * @param mensaje mensaje de error
     * @param e excecion
     */
    public ExceptionRecursosBiblioteca(String mensaje, Exception e){
        super(mensaje, e);
    }

    /**
     * Metodo que crea el mensaje de eerror
     * @param mensaje mensaje de error
     */
    public ExceptionRecursosBiblioteca(String mensaje){
        super(mensaje);
    }
}
