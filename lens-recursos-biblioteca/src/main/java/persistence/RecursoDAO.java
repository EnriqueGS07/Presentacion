package persistence;

import entities.Recurso;
import entities.TipoRecurso;
import org.apache.ibatis.annotations.Param;
import services.ExceptionRecursosBiblioteca;

import java.util.List;
/**
 * Clase que se encarga de traer los datos del recurso
 * @author LENS
 * @version 1.0
 */
public interface RecursoDAO {
    /**
     * Metodo para insertar un nuevo recurso
     * @param recurso recurso que se va a insertar
     */
    public void save(Recurso recurso);

    /**
     * Metodo para obtener un recurso especifico
     * @param id id del
     * @return
     */
    public Recurso load(int id);

    /**
     * Consultar los recursos disponibles en la plataforma
     * @return lista de los recursos en la plataforma
     */
    public List<Recurso> consultarRecursos();

    /**
     * Metodo para obtener un recurso especifico
     * @param id id del
     * @return
     */
    public Recurso consultarRecursosPorId(int id);

    /**
     * Consultar los recursos con cierto tipo
     * @param tipo tipo de los recursos que queremos listar
     * @return lista de los recursos con el tipo especificado
     */

    public List<Recurso> consultarRecursosPorTipo(int tipo);

    /**
     * Consultar los recursos con cierta capacidad
     * @param i capacidad de los recursos que queremos listar
     * @return lista de los recursos con la capacidad especificada
     */
    public List<Recurso> consultarRecursosPorCapacidad(int i);

    /**
     * Consultar los recursos por ubicación
     * @param ubi ubicacion de los recursos
     * @return lista con los recursos en la ubicacion dada
     */
    public List<Recurso> consultarRecursosPorUbicacion(String ubi);

    /**
     * Consulta los recursos con el tipo, capacidad, y ubicacion indicada
     * @param tipo tipo de los recursos
     * @param capacidad capacidad de los recursos
     * @param ubicacion ubicación de los recursos
     * @return Lista con los recursos que cumplan con el filtro dado
     */
    List<Recurso> consultarRecursosPorTipoCapacidadUbicacion(int tipo, int capacidad, String ubicacion);

    /**
     * Consulta los recursos por tipo y capacidad
     * @param tipo tipo de los recursos
     * @param capacidad capacidad de los recursos
     * @return lista de los recursos requeridos
     */
    List<Recurso> consultarRecursosPorTipoYCapacidad(int tipo, int capacidad);

    /**
     * Consulta los recursos por tipo y ubicacion
     * @param tipo tipo de los recursos y ubicacion
     * @param ubicacion ubicacion de los recursos
     * @return lista con los recursos requeridos
     */
    List<Recurso> consultarRecursosPorTipoYUbicacion(int tipo, String ubicacion);

    /**
     * Consulta los recursos por capacidad y ubicacion
     * @param capacidad capacidad de los recursos
     * @param ubicacion ubicacion de los recursos
     * @return lista con los recursos requeridos
     */
    List<Recurso> consultarRecursosPorUbicacionYCapacidad(String ubicacion, int capacidad);
    /**
     * Registrar un nuevo recurso
     * @param nombre el nombre del nuevo recurso
     * @param ubicacion la ubicación del nuevo recurso
     * @param tipo el tipo del nuevo recurso
     * @param capacidad la capacidad del nuevo recurso
     */
    void registrarRecurso(String nombre, String habilitado, String ubicacion, int ejemplar, TipoRecurso tipo, int capacidad) throws ExceptionRecursosBiblioteca;

    /**
     * Metodo para consultar eñ nombre de un recurso especifico
     * @param id id del recurso que se quiere consultar el nombre
     * @return nombre del recursp
     */
    Recurso consultarNombreRecurso(int id);

    /**
     * Metodo que cambia el estado de dañado a habilitado
     * @param id id del recurso
     */
    void cambiarRecursoDanado(int id);

    /**
     * Metodo que cambia el estado de habilitado a dañado
     * @param id id del recurso
     */
    void cambiarRecursoHabilitado(int id);
}
