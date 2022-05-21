package managedbeans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import com.google.inject.Injector;

/**
 * Clase de bean base para la creacion de otros beans  correspondientes a vistas de la aplicacion
 *
 * @author LENS
 * @version 1.0
 */
public abstract class BasePageBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Injector injector;

    /**
     * Injeccion de dependencia a servletContext
     * @return
     */
    public Injector getInjector() {
        if (injector == null) {
            ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
                    .getContext();
            injector = (Injector) servletContext.getAttribute(Injector.class.getName());
        }
        return injector;
    }

    /**
     * Metodo que definir el injector de la clase
     * @param injector injector que se desea poner
     */
    public void setInjector(Injector injector) {
        this.injector = injector;
    }

    @PostConstruct
    public void init() {
        getInjector().injectMembers(this);
    }
}