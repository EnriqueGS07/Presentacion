package persistence;


import entities.TipoRecurso;

/**
 * Clase que se encarga de traer los datos del rtipo de recurso
 * @author LENS
 * @version 1.0
 */
public interface TipoRecursoDAO {

    /**
     * MEtodo para guardar un nuevo tipo de recurso
     * @param tipoRecurso tipo de recurso que se va a agregar
     */
    public void save(TipoRecurso tipoRecurso);

    /**
     * metodo para obtener un tipo de recurso
     * @param id id del tipo de recurso
     * @return tipo de recurso correpondiente
     */
    public TipoRecurso load(int id);
}
