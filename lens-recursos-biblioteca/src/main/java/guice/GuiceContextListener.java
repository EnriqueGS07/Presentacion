package guice;

import com.google.inject.Guice;
import com.google.inject.Injector;

import org.mybatis.guice.XMLMyBatisModule;
import org.mybatis.guice.datasource.helper.JdbcHelper;

import persistence.*;
import persistence.mybatisimpl.*;
import services.RecursosBiblioteca;
import services.impl.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
/**
 * Clase de injeccion de dependencias usando Google guice
 *
 * @author LENS
 * @version 1.0
 */


public class GuiceContextListener implements ServletContextListener {


    /**
     * Metodo que destruye la injeccion de depndencias
     * @param servletContextEvent
     */
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.removeAttribute(Injector.class.getName());
    }

    /**
     * Funcion que inicializa la injeccion de dependencias
     * @param servletContextEvent servletContextEvent
     */
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Injector injector = Guice.createInjector(new XMLMyBatisModule() {
            @Override
            protected void initialize() {
                install(JdbcHelper.PostgreSQL);
                setEnvironmentId("development");
                setClassPathResource("mybatis-config.xml");

                // TODO Add service class associated to Stub implementation
                bind(RecursosBiblioteca.class).to(RecursosBibliotecaImpl.class);
                bind(HorarioDAO.class).to(MyBATISHorarioDAO.class);
                bind(RecursoDAO.class).to(MyBATISRecursoDAO.class);
                bind(ReservaDAO.class).to(MyBATISReservaDAO.class);
                bind(ReservaRecurrenteDAO.class).to(MyBATISReservaRecurrenteDAO.class);
                bind(TipoRecursoDAO.class).to(MyBATISTipoRecursoDAO.class);
                bind(UsuarioDAO.class).to(MyBATISUsuarioDAO.class);
            }
        });

        ServletContext servletContext = servletContextEvent.getServletContext();
        servletContext.setAttribute(Injector.class.getName(), injector);
    }
}
