package persistence.mybatisimpl;

import com.google.inject.Inject;
import entities.Recurso;
import entities.Reserva;
import entities.Usuario;
import persistence.ReservaDAO;
import persistence.mybatisimpl.mappers.ReservaMapper;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;
import java.util.List;
/**
 * Implementacion de la clase reservaDAO
 * @author LENS
 * @version 1.0
 */

public class MyBATISReservaDAO implements ReservaDAO {
    @Inject
    private ReservaMapper reservaMapper;

    @Override
    public void save(Reserva reserva) {

    }

    @Override
    public Reserva load(int id) {
        return null;
    }

    @Override
    public void reservarRecurso(int usuario, int recurso, Timestamp inicio, Timestamp fin, boolean recurrente, String estado, Time hIni,Time hFin, Timestamp solicitud) {
        reservaMapper.reservarRecurso(usuario,recurso,inicio,fin,recurrente,estado,hIni,hFin,solicitud );
    }

    @Override
    public List<Reserva> consultarReservas(){
        return reservaMapper.consultarReservas();
    }

    @Override
    public Reserva consultarReserva(int id) {
        return reservaMapper.consultarReservaExtendida(id);
    }

    @Override
    public List<Reserva> consultarReservasActivas(int id) {
        return reservaMapper.consultarReservasActivas(id);
    }

    @Override
    public List<Reserva> consultarReservasCanceladas(int id) {
        return reservaMapper.consultarReservasCanceladas(id);
    }

    @Override
    public List<Reserva> consultarReservasPasadas(int id) {
        return reservaMapper.consultarReservasPasadas(id);
    }

    @Override
    public void cancelarReserva(int id) {
        reservaMapper.cancelarReserva(id);
    }

    @Override
    public List<Reserva> consultarReservasPorUsuario(int id){
        return reservaMapper.consultarReservasPorUsuario(id);
    }

    @Override
    public Reserva consultarReservaExtendida(int id) {
        return reservaMapper.consultarReservaExtendida(id);
    }
}
