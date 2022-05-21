package test;

import entities.Recurso;
import org.junit.jupiter.api.Test;
import services.RecursosBiblioteca;
import services.RecursosBibliotecaFactory;
import org.junit.Assert;
import java.util.List;

public class bibliotecaTest {

    RecursosBiblioteca biblioteca;
    public bibliotecaTest(){
        biblioteca = RecursosBibliotecaFactory.getInstance().getRecursosBiblioteca();
    }
    @Test
    public void deberiaConsultarSinFiltro(){
        List<Recurso> recursos = biblioteca.consultarRecursos();
        String recu = recursos.get(0).toString();
        int evaluar = Character.getNumericValue(recu.charAt(12));
        for (int i = 1; i < recursos.size(); i++){
            recu = recursos.get(i).toString();
            if(evaluar < Character.getNumericValue(recu.charAt(12))){
                evaluar = Character.getNumericValue(recu.charAt(12));
            }
        }
        Assert.assertEquals(recursos.size(), evaluar);

    }
    @Test
    public void deberiaConsultarPorTipo(){
        List<Recurso> recursos = biblioteca.consultarRecursosPorTipo(1);
        for (Recurso r: recursos){
            Assert.assertEquals("libro", r.getTipo().getNombre());
        }
        recursos = biblioteca.consultarRecursosPorTipo(4);
        for (Recurso r: recursos){
            Assert.assertEquals("equipo audiovisual", r.getTipo().getNombre());
        }
    }

    @Test
    public void deberiaConsultarPorCapacidad(){
        List<Recurso> recursos = biblioteca.consultarRecursosPorCapacidad(4);
        for(Recurso r: recursos){
            Assert.assertEquals(4, r.getCapacidad());
        }
    }

    @Test
    public void deberiaConsultarPorUbicacion(){
        List<Recurso> recursos = biblioteca.consultarRecursosPorUbicacion("Biblioteca");
        for(Recurso r: recursos){
            Assert.assertEquals("biblioteca", r.getUbicacion());
        }
    }
}
