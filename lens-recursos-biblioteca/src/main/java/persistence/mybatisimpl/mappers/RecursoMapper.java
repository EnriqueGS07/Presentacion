package persistence.mybatisimpl.mappers;

import entities.Recurso;
import entities.TipoRecurso;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Clase correspondinete al recursoMapper.xml, conecta el xml con java
 * @author LENS
 * @version 1.0
 */
public interface RecursoMapper {

    /**
     * Metodo para insertar un nuevo recurso
     * @param recurso recurso que se va a insertar
     */
    public void insertarRecurso(Recurso recurso);

    /**
     * Consular un recurso por ID
     * @param id id del recurso que queremos buscar
     * @return recurso con el id especificado
     */
    Recurso consultarRecursoPorId(@Param("id") int id);

    /**
     * Registrar un nuevo recurso
     * @param nombre el nombre del nuevo recurso
     * @param ubicacion la ubicación del nuevo recurso
     * @param tipo el tipo del nuevo recurso
     * @param capacidad la capacidad del nuevo recurso
     */
     void registrarRecurso(@Param("name") String nombre,
                           @Param("enable") String habilitado,
                           @Param("location") String ubicacion,
                           @Param("item") int ejemplar,
                           @Param("type") TipoRecurso tipo,
                           @Param("capacity") int capacidad);

    /**
     * Consultar los recursos disponibles en la plataforma
     * @return lista de los recursos en la plataforma
     */
    List<Recurso> consultarRecursos();

    /**
     * Consultar los recursos con cierto tipo
     * @param tipo tipo de los recursos que queremos listar
     * @return lista de los recursos con el tipo especificado
     */
    List<Recurso> consultarRecursosPorTipo(@Param("tipo") int tipo);

    /**
     * Consultar los recursos con cierta capacidad
     * @param i capacidad de los recursos que queremos listar
     * @return lista de los recursos con la capacidad especificada
     */
    List<Recurso> consultarRecursosPorCapacidad(@Param("cap") int i);

    /**
     * Consultar los recursos por ubicación
     * @param ubi ubicacion de los recursos
     * @return lista con los recursos en la ubicacion dada
     */
    List<Recurso> consultarRecursosPorUbicacion(@Param("ubi")String ubi);

    /**
     * Consulta los recursos con el tipo, capacidad, y ubicacion indicada
     * @param tipo tipo de los recursos
     * @param capacidad capacidad de los recursos
     * @param ubicacion ubicación de los recursos
     * @return Lista con los recursos que cumplan con el filtro dado
     */
    List<Recurso> consultarRecursosTipoCapaUbi(@Param("tipo")int tipo, @Param("cap")int capacidad, @Param("ubi")String ubicacion);

    /**
     * Consulta los recursos por tipo y ubicacion
     * @param tipo tipo de los recursos y ubicacion
     * @param ubi ubicacion de los recursos
     * @return lista con los recursos requeridos
     */
    List<Recurso> consultarRecursosTipoUbi(@Param("tipo")int tipo, @Param("ubi") String ubi);

    /**
     * Consulta los recursos por tipo y capacidad
     * @param tipo tipo de los recursos
     * @param cap capacidad de los recursos
     * @return lista de los recursos requeridos
     */
    List<Recurso> consultarRecursosTipoCapa(@Param("tipo")int tipo,@Param("cap") int cap);

    /**
     * Consulta los recursos por capacidad y ubicacion
     * @param cap capacidad de los recursos
     * @param ubi ubicacion de los recursos
     * @return lista con los recursos requeridos
     */
    List<Recurso> consultarRecursosCapaUbi(@Param("cap")int cap, @Param("ubi") String ubi);

    /**
     * Metodo para consultar eñ nombre de un recurso especifico
     * @param id id del recurso que se quiere consultar el nombre
     * @return nombre del recursp
     */
    Recurso consultarNombreRecurso(@Param("id") int id);

    /**
     * Metodo que cambia el estado de dañado a habilitado
     * @param id id del recurso
     */
    void cambiarRecursoDanado(@Param("idRecurso")int id);

    /**
     * Metodo que cambia el estado de habilitado a dañado
     * @param id id del recurso
     */
    void cambiarRecursoHabilitado(@Param("idRecurso")int id);
}
