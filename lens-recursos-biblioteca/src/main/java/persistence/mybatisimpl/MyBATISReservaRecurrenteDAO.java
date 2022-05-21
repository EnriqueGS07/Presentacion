package persistence.mybatisimpl;

import com.google.inject.Inject;
import entities.ReservaRecurrente;
import persistence.ReservaRecurrenteDAO;
import persistence.mybatisimpl.mappers.ReservaRecurrenteMapper;

/**
 * Implementacion de la clase ReservaRecurrenteDAO
 * @author LENS
 * @version 1.0
 */
public class MyBATISReservaRecurrenteDAO implements ReservaRecurrenteDAO {
    @Inject
    private ReservaRecurrenteMapper reservaRecurrenteMapper;


    @Override
    public void save(ReservaRecurrente reservaRecurrente) {

    }

    @Override
    public ReservaRecurrente load(int id) {
        return null;
    }
}
