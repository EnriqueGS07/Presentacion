package services;

import com.google.inject.Injector;
import org.mybatis.guice.XMLMyBatisModule;
import persistence.*;
import persistence.mybatisimpl.*;
import services.impl.*;

import java.util.Optional;

import static com.google.inject.Guice.createInjector;

/**
 * Clase de injeccion de dependencias usando Google guice
 *
 * @author LENS
 * @version 1.0
 */
public class RecursosBibliotecaFactory {
    private static RecursosBibliotecaFactory instance = new RecursosBibliotecaFactory();

    private static Optional<Injector> optInjector;

    /**
     * Funcion que inicializa la injeccion de dependencias
     * @param env ambiente en el cual se va a trabajar esta instancia
     * @param pathResource ruta del archivo de configuracion para el ambiente
     * @return
     */
    private Injector myBatisInjector(String env, String pathResource) {
        return createInjector(new XMLMyBatisModule() {
            @Override
            protected void initialize() {
                setEnvironmentId(env);
                setClassPathResource(pathResource);
                bind(RecursosBiblioteca.class).to(RecursosBibliotecaImpl.class);
                bind(HorarioDAO.class).to(MyBATISHorarioDAO.class);
                bind(RecursoDAO.class).to(MyBATISRecursoDAO.class);
                bind(ReservaDAO.class).to(MyBATISReservaDAO.class);
                bind(ReservaRecurrenteDAO.class).to(MyBATISReservaRecurrenteDAO.class);
                bind(TipoRecursoDAO.class).to(MyBATISTipoRecursoDAO.class);
                bind(UsuarioDAO.class).to(MyBATISUsuarioDAO.class);
            }
        });
    }

    /**
     * Metodo que reinicia la instancia de el injector
     */
    private RecursosBibliotecaFactory(){
        optInjector = Optional.empty();
    }

    /**
     *MEtodo para obtener crear la isntancia de recursos biblioteca dependiendo del ambiente
     * @return instancia de RecursosBiblioteca
     */
    public RecursosBiblioteca getRecursosBiblioteca(){
        if (!optInjector.isPresent()) {
            optInjector = Optional.of(myBatisInjector("development","mybatis-config.xml"));
        }

        return optInjector.get().getInstance(RecursosBiblioteca.class);
    }


//    /**
//     *MEtodo para obtener crear la isntancia de recursos biblioteca dependiendo del ambiente
//     * @return instancia de RecursosBiblioteca
//     */
//    public RecursosBiblioteca getServiciosAlquilerTesting(){
//        if (!optInjector.isPresent()) {
//            optInjector = Optional.of(myBatisInjector("test","mybatis-config-h2.xml"));
//        }
//
//        return optInjector.get().getInstance(RecursosBiblioteca.class);
//    }

    /**
     * Metodo para obtener la instancia de RecursosBibliotecaFactory
     * @return instancia de RecursosBibliotecaFactory
     */
    public static RecursosBibliotecaFactory getInstance(){
        return instance;
    }
}
