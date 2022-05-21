package persistence.mybatisimpl;

import com.google.inject.Inject;
import entities.Recurso;
import entities.TipoRecurso;
import persistence.RecursoDAO;
import persistence.mybatisimpl.mappers.RecursoMapper;
import services.ExceptionRecursosBiblioteca;


import java.util.List;

/**
 * Implementacion de la clase recursoDAO
 * @author LENS
 * @version 1.0
 */
public class MyBATISRecursoDAO implements RecursoDAO {
    @Inject
    private RecursoMapper recursoMapper;

    @Override
    public void save(Recurso recurso) {
        recursoMapper.insertarRecurso(recurso);
    }

    @Override
    public Recurso load(int id) {
        return null;
    }

    @Override
    public List<Recurso> consultarRecursos() {
        return recursoMapper.consultarRecursos();
    }

    @Override
    public Recurso consultarRecursosPorId(int id) {
        return recursoMapper.consultarRecursoPorId(id);
    }

    @Override
    public List<Recurso> consultarRecursosPorTipo(int tipo) {
        return recursoMapper.consultarRecursosPorTipo(tipo);
    }

    @Override
    public List<Recurso> consultarRecursosPorCapacidad(int i) {
        return recursoMapper.consultarRecursosPorCapacidad(i);
    }

    @Override
    public List<Recurso> consultarRecursosPorUbicacion(String ubi) {
        return recursoMapper.consultarRecursosPorUbicacion(ubi);
    }

    @Override
    public List<Recurso> consultarRecursosPorTipoCapacidadUbicacion(int tipo, int capacidad, String ubicacion) {
        return recursoMapper.consultarRecursosTipoCapaUbi(tipo,capacidad,ubicacion);
    }

    @Override
    public List<Recurso> consultarRecursosPorTipoYCapacidad(int tipo, int capacidad) {
        return recursoMapper.consultarRecursosTipoCapa(tipo,capacidad);
    }

    @Override
    public List<Recurso> consultarRecursosPorTipoYUbicacion(int tipo, String ubicacion) {
        return recursoMapper.consultarRecursosTipoUbi(tipo,ubicacion);
    }

    @Override
    public List<Recurso> consultarRecursosPorUbicacionYCapacidad(String ubicacion, int capacidad) {
        return recursoMapper.consultarRecursosCapaUbi(capacidad, ubicacion);
    }

    @Override
    public void registrarRecurso(String nombre, String habilitado, String ubicacion, int ejemplar, TipoRecurso tipo, int capacidad) throws ExceptionRecursosBiblioteca{
        try{
            recursoMapper.registrarRecurso(nombre, habilitado, ubicacion, ejemplar, tipo, capacidad);
        }catch (Exception e){
            throw new ExceptionRecursosBiblioteca("error");
        }

    }

    @Override
    public Recurso consultarNombreRecurso(int id){
        return recursoMapper.consultarNombreRecurso(id);
    }

    /**
     * Metodo que cambia el estado de dañado a habilitado
     *
     * @param id id del recurso
     */
    @Override
    public void cambiarRecursoDanado(int id) {
        recursoMapper.cambiarRecursoDanado(id);
    }

    /**
     * Metodo que cambia el estado de habilitado a dañado
     *
     * @param id id del recurso
     */
    @Override
    public void cambiarRecursoHabilitado(int id) {
        recursoMapper.cambiarRecursoHabilitado(id);
    }
}
