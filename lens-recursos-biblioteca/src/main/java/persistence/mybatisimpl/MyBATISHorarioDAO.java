package persistence.mybatisimpl;

import com.google.inject.Inject;
import entities.Horario;
import persistence.HorarioDAO;
import persistence.mybatisimpl.mappers.HorarioMapper;
import services.ExceptionRecursosBiblioteca;
import services.RecursosBiblioteca;

import java.sql.Time;
import java.util.List;

/**
 * Implementacion de la clase HorarioDAO
 * @author LENS
 * @version 1.0
 */
public class MyBATISHorarioDAO implements HorarioDAO {
    @Inject
    private HorarioMapper horarioMapper;

    List<Horario> horarios;

    @Override
    public void save(Horario horario) {

    }

    @Override
    public List<Horario> load(int id) throws ExceptionRecursosBiblioteca {
        horarios = horarioMapper.consultarHorario(id);
        if (horarios.size() == 0){
            throw new ExceptionRecursosBiblioteca("Este recurso no tiene horarios diponibles");
        }
        return horarios;
    }

    public void guardar(Time hora_ini, Time hora_fin){
        horarioMapper.guardar(hora_ini, hora_fin);
    }
}
