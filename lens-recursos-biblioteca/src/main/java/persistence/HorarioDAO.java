package persistence;

import entities.Horario;
import services.ExceptionRecursosBiblioteca;

import java.sql.Time;
import java.util.List;

/**
 * Clase que se encarga de traer los datos del horario
 * @author LENS
 * @version 1.0
 */
public interface HorarioDAO {

    /**
     * Metodo para guardar un nuevo horario
     * @param horario horario a guardar
     */
    public void save(Horario horario);

    /**
     * Metodo que obtiene los horarios
     * @param id id del recurso que se desean saber los horarios
     * @return lista de horarios del recurso correspondiente
     * @throws ExceptionRecursosBiblioteca error
     */
    public List<Horario> load(int id) throws ExceptionRecursosBiblioteca;

    /**
     * MEtodo para guardar un nuevo horario al recurso
     * @param hora_ini hora de inicio del horarip
     * @param hora_fin hora de din del horarip
     */
    void guardar(Time hora_ini, Time hora_fin);
}
