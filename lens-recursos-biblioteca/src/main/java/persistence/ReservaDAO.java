package persistence;


import entities.Recurso;
import entities.Reserva;
import entities.Usuario;
import org.bouncycastle.util.Times;

import java.sql.ResultSet;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.List;

/**
 * Clase que se encarga de traer los datos de la reserva
 * @author LENS
 * @version 1.0
 */
public interface ReservaDAO {

    public void save(Reserva reserva);


    public Reserva load(int id);

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

    public void reservarRecurso(int usuario, int recurso, Timestamp inicio, Timestamp fin, boolean recurrente, String estado,Time hIni,Time hFin, Timestamp solicitud);

    /**
     * Metodo para consuktar todas las reservas
     * @return lista de todas las reservas
     */
    public List<Reserva> consultarReservas();


    Reserva consultarReserva(int id);

    /**
     * Metodo para consultar las reservas activas
     * @param id id del recurso al cual se le desean consultar las reservas
     * @return lista de reservas activas
     */
    List<Reserva> consultarReservasActivas(int id);

    /**
     * Metodo para consultar las reservas canceladas
     * @param id id del recurso al cual se le desean consultar las reservas
     * @return lista de reservas canceladas
     */
    List<Reserva> consultarReservasCanceladas(int id);

    /**
     * Metodo para consultar las reservas pasadas, es decir las que ya vencieron
     * @param id id del recurso al cual se le desean consultar las reservas
     * @return lista de reservas pasadas
     */
    List<Reserva> consultarReservasPasadas(int id);

    /**
     * Metodo para consultar las reservas de un usuario especificp
     * @param id id del usuario al cual se le desean consultar las reservas
     * @return lista de reservas del usuario
     */
    List<Reserva> consultarReservasPorUsuario(int id);

    void cancelarReserva(int id);

    Reserva consultarReservaExtendida(int id);
}
