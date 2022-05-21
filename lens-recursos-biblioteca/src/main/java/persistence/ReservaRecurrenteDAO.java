package persistence;


import entities.ReservaRecurrente;

/**
 * Clase que se encarga de traer los datos de la reserva recurrente
 * @author LENS
 * @version 1.0
 */
public interface ReservaRecurrenteDAO {

    /**
     * Metodo para guardar una reserva recurrente
     * @param reservaRecurrente
     */
    public void save(ReservaRecurrente reservaRecurrente);

    /**
     * Metodo para obtener una reserva recurrente
     * @param id id de la reserva recurrente
     * @return
     */
    public ReservaRecurrente load(int id);
}
