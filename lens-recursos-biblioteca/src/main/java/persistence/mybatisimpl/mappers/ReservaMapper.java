package persistence.mybatisimpl.mappers;

import entities.Recurso;
import entities.Reserva;
import entities.Usuario;
import org.apache.ibatis.annotations.Param;

import java.sql.Time;
import java.util.List;
import java.sql.Timestamp;
import java.util.List;

/**
 * Clase correspondinete al reservaMapper.xml, conecta el xml con java
 * @author LENS
 * @version 1.0
 */

public interface ReservaMapper {

    /**
     * Metodo para reservar un recurso especifico
     * @param usuario id del usuario que realizo la reserva
     * @param recurso id del recurso a reservar
     * @param inicio inicio de la reserva
     * @param fin fin de la reserva
     * @param recurrente indica si la reserva es recurrente
     * @param estado estado de la reserva
     * @param solicitud fecha que se realizo la solicitud de resevra
     */
    void reservarRecurso(@Param("id_usuario") int usuario,
                         @Param("id_recurso") int recurso,
                         @Param("inicio") Timestamp inicio,
                         @Param("fin") Timestamp fin,
                         @Param("recurrente") boolean recurrente,
                         @Param("estado") String estado,
                         @Param("h_ini") Time hIni,
                         @Param("h_fin") Time hFin,
                         @Param("solicitud") Timestamp solicitud );

    /**
     * Metodo para consuktar todas las reservas
     * @return lista de todas las reservas
     */
    List<Reserva> consultarReservas();

    /**
     * Metodo para consultar las reservas activas
     * @param id id del recurso al cual se le desean consultar las reservas
     * @return lista de reservas activas
     */
    List<Reserva> consultarReservasActivas(@Param("id") int id);

    /**
     * Metodo para consultar las reservas canceladas
     * @param id id del recurso al cual se le desean consultar las reservas
     * @return lista de reservas canceladas
     */
    List<Reserva> consultarReservasCanceladas(@Param("id") int id);

    /**
     * Metodo para consultar las reservas pasadas, es decir las que ya vencieron
     * @param id id del recurso al cual se le desean consultar las reservas
     * @return lista de reservas pasadas
     */
    List<Reserva> consultarReservasPasadas(@Param("id") int id);

    void cancelarReserva(@Param("idReserva")int id);

    /**
     * Metodo para consultar las reservas de un usuario especificp
     * @param id id del usuario al cual se le desean consultar las reservas
     * @return lista de reservas del usuario
     */
    List<Reserva> consultarReservasPorUsuario(@Param("id") int id);

    Reserva consultarReservaExtendida(@Param("id") int id);

}
