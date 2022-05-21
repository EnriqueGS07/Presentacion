package persistence.mybatisimpl;

import com.google.inject.Inject;
import entities.TipoRecurso;
import persistence.TipoRecursoDAO;
import persistence.mybatisimpl.mappers.TipoRecursoMapper;

/**
 * Implementacion de la clase TipoRecursoDAO
 * @author LENS
 * @version 1.0
 */
public class MyBATISTipoRecursoDAO implements TipoRecursoDAO {
    @Inject
    private TipoRecursoMapper tipoRecursoMapper;

    @Override
    public void save(TipoRecurso tipoRecurso) {

    }

    @Override
    public TipoRecurso load(int id) {
        return null;
    }
}
