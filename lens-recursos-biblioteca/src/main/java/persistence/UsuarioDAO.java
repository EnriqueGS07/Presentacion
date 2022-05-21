package persistence;


import entities.Usuario;
import org.apache.ibatis.exceptions.PersistenceException;

public interface UsuarioDAO {

    /**
     * Metodo para buscar un usuario por su correo
     * @param correo correo del usuario que se desea buscar
     * @return usuario al cual le corresponde el correo
     */
    public Usuario buscarUsuario(String correo) throws PersistenceException;

    /**
     * Metodo para obtener un usuario por id
     * @param id id del usuario que se desea obtener
     * @return usuario correspondiente
     */
    Usuario load(int id);

    /**
     * Metodo para obtener un usuario por id
     * @param id id del usuario que se desea obtener
     * @return usuario correspondiente
     */
    public Usuario consultarUsuarioPorId(int id) throws  PersistenceException;
    /**
     * Metodo oara guardar un usuario
     * @param u usuario que se desea agregar
     */
    void save(Usuario u);
}
