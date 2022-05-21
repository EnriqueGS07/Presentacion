package managedbeans;

import java.io.IOException;
import java.util.logging.Level;

import javax.faces.bean.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;


import com.google.inject.Inject;

import entities.Usuario;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.LoggerFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import services.ExceptionRecursosBiblioteca;
import services.RecursosBiblioteca;


/**
 * Bean correspondiente a la vista del login de la aplicacion
 * @author LENS
 * @version 1.0
 */

@SuppressWarnings("deprecation")
@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean extends BasePageBean{

    @Getter private static final Logger log = LoggerFactory.getLogger(RecursosBiblioteca.class);
    @Getter @Setter private String usuario;
    @Getter @Setter private String contrasena;
    @Getter @Setter public boolean logeado = false;
    @Getter @Setter private Usuario user;



    @Inject
    private RecursosBiblioteca rebi;

    /**
     * Metodo que retorna el id del usuario
     * @return entero que corresponde al id del usuario
     */
    public int getUserId(){
        return user.getId();
    }

    /**
     * Metodo que verifica las credecniales del usuario y le da ingreso a la aplicacion
     */
    public void login(){
        Subject usuarioActual = SecurityUtils.getSubject();
        UsernamePasswordToken uPToken = new UsernamePasswordToken(getUsuario(), getContrasena());
        try{
            user = rebi.buscarUsuario(usuario);
            if (user != null){
                usuarioActual.login(uPToken);
                usuarioActual.getSession().setAttribute("Correo", usuario);
                redirect();
                setLogeado(true);
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("user",user);
            }else{
                error("EL usuario no existe");
            }
        } catch (UnknownAccountException e) {
            String errorMensaje = "El usuario no esta registrado";
            error(errorMensaje);
            log.error(e.getMessage(), e);
        } catch (IncorrectCredentialsException e) {
            String errorMensaje = "La contraseña que ingreso es incorrecta";
            error(errorMensaje);
            log.error(e.getMessage(), e);
        } catch (LockedAccountException e) {
            String errorMensaje = "El usuario esta deshabilitado";
            error(errorMensaje);
            log.error(e.getMessage(), e);
        } catch (AuthenticationException e) {
            String errorMensaje = "Error inesperado";
            error(errorMensaje);
            log.error(e.getMessage(), e);
        } catch (ExceptionRecursosBiblioteca e) {
            String errorMensaje = "Error inesperado 2";
            error(errorMensaje);
            log.error(e.getMessage(), e);
        } finally {
            uPToken.clear();
        }
    }

    /**
     * Metodo que muesra un error en la pantalla del login
     * @param mensaje mensaje de error que se quiere mostrar
     */
    private void error(String mensaje) {
        FacesContext.getCurrentInstance().addMessage("Shiro", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Intente de nuevo: ", mensaje));
    }

    /**
     * Metodo para cerrar sesion en la aplicación
     */
    public void logOut() {
        setLogeado(false);
        SecurityUtils.getSubject().logout();
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("/recursosBiblioteca/login.xhtml");
        } catch (IOException e) {
            java.util.logging.Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    /**
     * Metodo que redirecciona al usuario dependiendo de sus credenciales
     */
    public void redirect(){
        try {
            Subject usuario = SecurityUtils.getSubject();
            if (usuario.hasRole("administrador")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/recursosBiblioteca/administrador.xhtml");
            } else if (usuario.hasRole("estudiante")) {
                FacesContext.getCurrentInstance().getExternalContext().redirect("/recursosBiblioteca/comunidad.xhtml");
            }
        }catch (IOException e){
            error("Error desconocido: " + e.getMessage());
            log.error(e.getMessage(), e);
        }
    }

}
