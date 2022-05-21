package security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import lombok.Getter;
import lombok.Setter;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

/**
 * Clase que se encarga de la seguridad de la aplicación
 * @author LENS
 * @version 1.0
 */
public class OnlyNotAutenticate extends AccessControlFilter{

    @Getter @Setter private String welcomeUrl;
    /**
     * Metodo que revisa las credenciales del usuario
     * @param servletRequest servletRequest
     * @param servletResponse servletResponse
     * @param o Objeto
     * @return retorna si el usuario puede acceder al objeto
     * @throws Exception error
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        Subject usuario = getSubject(servletRequest, servletResponse);
        return !usuario.isAuthenticated();
    }

    /**
     * Metodo que revisa las credenciales del usuario
     * @param servletRequest servletRequest
     * @param servletResponse servletResponse
     * @return retorna si el usuario NO puede acceder al objeto
     * @throws Exception error
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        welcomeUrl = "/recursosBiblioteca/login.xhtml";
        WebUtils.issueRedirect(servletRequest, servletResponse, welcomeUrl);
        return false;
    }

}
