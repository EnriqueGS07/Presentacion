package managedbeans;


import com.google.inject.Inject;
import entities.Recurso;
import entities.Usuario;
import lombok.Getter;
import lombok.Setter;
import services.RecursosBiblioteca;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Bean para la interfaz de usuario de la consulta de recursos
 *
 * @author LENS
 * @version 1.0
 */

@SuppressWarnings("deprecation")
@ManagedBean(name = "consultarRecursosBean")
@SessionScoped
public class ConsultarRecursosBean extends BasePageBean {

    @Inject
    private RecursosBiblioteca recursosBiblioteca;

    @Getter @Setter List<Recurso> recursos;
    @Getter @Setter Recurso recurso;

    @Getter @Setter int tipo = -1;
    @Getter @Setter int capacidad = -1;
    @Getter @Setter String ubicacion = "";

    /**
     * Retorna la lista de recursos del bean
     * @return lista de recursos del bean
     */
    public List<Recurso> getRecursos() {
        return recursos;
    }

    /**
     * Guarda en los recursos del bean, todos los recursos disponibles
     * @return lista de los recursos
     */
    public List<Recurso> filtrarLosRecursos(){
        //Si todos los elementos de filtro estan definidos buscamos por todas las caracteristicas
        if(tipo!=-1 && capacidad != -1 && !ubicacion.equals("")){
            recursos = recursosBiblioteca.consultarRecursosPorTipoCapacidadUbicacion(tipo,capacidad,ubicacion);
        }
        //Filtramos por tipo y capacidad
        else if(tipo != -1 && capacidad !=-1){
            recursos = recursosBiblioteca.consultarRecursosPorTipoYCapacidad(tipo,capacidad);
        }
        //Filtramos por tipo y ubicacion
        else if(tipo != -1 && !ubicacion.equals("")){
            recursos = recursosBiblioteca.consultarRecursosPorTipoYUbicacion(tipo,ubicacion);
        }
        //Filtramos por ubicacion y capacidad
        else if(!ubicacion.equals("") && capacidad!=-1){
            recursos = recursosBiblioteca.consultarRecursosPorUbicacionYCapacidad(ubicacion,capacidad);
        }
        //Filtramos por tipo
        else if (tipo != -1){
            recursos = recursosBiblioteca.consultarRecursosPorTipo(tipo);
        }
        //Filtramos por capacidad
        else if (capacidad != -1){
            recursos = recursosBiblioteca.consultarRecursosPorCapacidad(capacidad);
        }
        //Filtramos por ubicacion
        else if (!ubicacion.equals("")){
            recursos = recursosBiblioteca.consultarRecursosPorUbicacion(ubicacion);
        }
        //En caso de que no se incluya ningun filtro buscamos todos los recursos
        else {
            recursos = recursosBiblioteca.consultarRecursos();
        }
//        mostrar();
        return recursos;
    }

    /**
     * Metodo que redirecciona a la vista dependiendo de sus credencuiales
     */
    public void redirect(){
        try{
            if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user") == null){
                FacesContext.getCurrentInstance().getExternalContext().redirect("/recursosBiblioteca/login.xhtml");
            } else{
                Usuario user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
                if(Objects.equals(user.getTipoUsuario(), "estudiante")){
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/recursosBiblioteca/comunidad.xhtml");
                }else{
                    FacesContext.getCurrentInstance().getExternalContext().redirect("/recursosBiblioteca/administrador.xhtml");
                }
            }

        }catch (Exception e){
            System.out.println("F");
        }

    }

    /**
     * Metodo que revisa quien esta logeado
     */
    public boolean logeado(){
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user") != null;
    }

    /**
     * Metodo que revisa quien esta logeado un admin
     */
    public boolean admin(){
        if(FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user") != null){
            Usuario user = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("user");
            return Objects.equals(user.getTipoUsuario(), "administrador");
        }
        return false;
    }

    /**
     * Metodo que cambia el estado de un recurso de dañado a habilitado o de habilitado a dañado
     */
    public void cambiarEstado(){
        if(Objects.equals(recurso.getHabilitado(), "habilitado")){
            recursosBiblioteca.cambiarRecursoHabilitado(recurso.getId());
        }else if(Objects.equals(recurso.getHabilitado(), "dañado")){
            recursosBiblioteca.cambiarRecursoDanado(recurso.getId());
        }
    }
}
