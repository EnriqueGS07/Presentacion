package dominio;


public class TetrisException extends Exception{

    //ERRORES GENERALES/
    public final static String FUNCION_EN_CONSTRUCCION = "Opcion en construccion";
    public final static String ERROR_EN_PERSISTENCIA = "Error en persistencia";
    public final static String NO_SE_ENCUENTRA_ARCHIVO = "El archivo deseado no se ha encontrado";
    public final static String CLASE_NO_PERMITIDA = "Encontramos errores en la clase";
    public final static String ENTRADA_SALIDA = "Ha ocurrido un error inesperado en la entrada-salida";

    //GUARDAR/
    public final static String CLASE_NO_SERIALIZABLE = "La clase que se desea guardar esta marcada como no 'serializable'";
    public final static String GUARDAR = "Ha ocurrido un error al guardar, intenetlo mas tarde";

    public TetrisException(String message) {
        super(message);
    }
}