package persistence.mybatisimpl.mappers;

import org.apache.ibatis.annotations.Param;
import entities.Usuario;

/**
 * Clase correspondinete al usuarioMapper.xml, conecta el xml con java
 * @author LENS
 * @version 1.0
 */
public interface UsuarioMapper {

    /**
     * Metodo para buscar un usuario por su correo
     * @param correo correo del usuario que se desea buscar
     * @return usuario al cual le corresponde el correo
     */
    public Usuario buscarUsuarioxCorreo(@Param ("Correo") String correo);

    public Usuario consultarUsuarioPorId(@Param("id") int id);

}
