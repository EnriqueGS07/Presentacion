package persistence.mybatisimpl.mappers;

import entities.Horario;
import org.apache.ibatis.annotations.Param;
import services.ExceptionRecursosBiblioteca;

import java.sql.Time;
import java.util.List;
/**
 * Clase correspondinete al horarioMapper.xml, conecta el xml con java
 * @author LENS
 * @version 1.0
 */
public interface HorarioMapper {
    /**
     * Clase para consultar horarios por recurso
     * @param id_recurso id del recurso al cual se le van a consultar los horarios
     * @return lista de horarios del recurso
     */
    List<Horario> consultarHorario(@Param("element") int id_recurso);

    /**
     * MEtodo para guardar un nuevo horario al recurso
     * @param hora_ini hora de inicio del horarip
     * @param hora_fin hora de din del horarip
     */
    void guardar(
            @Param("hora_ini") Time hora_ini,
            @Param("hora_fin") Time hora_fin
    );
}
