package services.impl;

import com.google.inject.Inject;
import entities.*;
import org.apache.ibatis.exceptions.PersistenceException;
import persistence.*;
import services.ExceptionRecursosBiblioteca;
import services.RecursosBiblioteca;

import javax.ejb.Singleton;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * Implementación de la clase RecursosBiblioteca
 * @author LENS
 * @version 1.0
 */

public class RecursosBibliotecaImpl implements RecursosBiblioteca {

    @Inject
    private HorarioDAO horarioDAO;

    @Inject
    private RecursoDAO recursoDAO;

    @Inject
    private ReservaDAO reservaDAO;

    @Inject
    private ReservaRecurrenteDAO reservaRecurrenteDAO;

    @Inject
    private TipoRecursoDAO tipoRecursoDAO;

    @Inject
    private UsuarioDAO usuarioDAO;

    @Override
    public Usuario buscarUsuario(String correo) throws ExceptionRecursosBiblioteca {
        try{
            return usuarioDAO.buscarUsuario(correo);
        }catch (PersistenceException e){
            throw new ExceptionRecursosBiblioteca("Error al buscar ese usuario: " + correo, e);
        }
    }

    @Override
    public List<Horario> consultarHorario(int id) throws ExceptionRecursosBiblioteca {
        try {
            return horarioDAO.load(id);
        } catch (Exception e){
            throw new ExceptionRecursosBiblioteca("Este recurso no tiene horarios diponibles");
        }

    }

    @Override
    public void registratHorario() {

    }

    @Override
    public void registrarHorario(Horario h) {
        horarioDAO.save(h);
    }

    @Override
    public Recurso consultarRecurso(int id) {
        return recursoDAO.load(id);
    }

    @Override
    public Recurso consultarRecursosPorId(int id) {
        return recursoDAO.consultarRecursosPorId(id);
    }

    @Override
    public void registrarRecurso(String nombre, String habilitado, String ubicacion, int ejemplar, TipoRecurso tipo, int capacidad) throws ExceptionRecursosBiblioteca{
        try{
            recursoDAO.registrarRecurso(nombre, habilitado, ubicacion, ejemplar, tipo, capacidad);
        }catch (Exception e){
            if (Objects.equals(nombre, "")){
                throw new ExceptionRecursosBiblioteca("Falta información");
            }else if(capacidad < 0 || capacidad > 15){
                throw new ExceptionRecursosBiblioteca("Valor de la capacidad invalido");
            }
            else{
                throw new ExceptionRecursosBiblioteca("El recurso ya existe");
            }
        }
    }


    @Override
    public List<Recurso> consultarRecursos() {
        return recursoDAO.consultarRecursos();
    }

    @Override
    public List<Recurso> consultarRecursosPorTipo(int tipo) {
        return recursoDAO.consultarRecursosPorTipo(tipo);
    }


    @Override
    public Usuario consultarUsuarioPorId(int id) {
        return usuarioDAO.consultarUsuarioPorId(id);
    }


    @Override
    public List<Recurso> consultarRecursosPorCapacidad(int i) {
        return recursoDAO.consultarRecursosPorCapacidad(i);
    }

    @Override
    public List<Recurso> consultarRecursosPorUbicacion(String ubi) {
        return recursoDAO.consultarRecursosPorUbicacion(ubi);
    }

    @Override
    public void reservarRecursos(int usuario, int recurso, Timestamp inicio, Timestamp fin, boolean recurrente, String estado,Time hIni,Time hFin, Timestamp solicitud)throws ExceptionRecursosBiblioteca{
        try{
            reservaDAO.reservarRecurso(usuario,recurso,inicio,fin,recurrente,estado, hIni,hFin,solicitud);
        }catch (Exception e){
            throw new ExceptionRecursosBiblioteca("Error al reservar");

        }

    }

    @Override
    public List<Recurso> consultarRecursosPorTipoCapacidadUbicacion(int tipo, int capacidad, String ubicacion) {
        return recursoDAO.consultarRecursosPorTipoCapacidadUbicacion(tipo,capacidad,ubicacion);
    }

    @Override
    public List<Recurso> consultarRecursosPorTipoYCapacidad(int tipo, int capacidad) {
        return recursoDAO.consultarRecursosPorTipoYCapacidad(tipo,capacidad);
    }

    @Override
    public List<Recurso> consultarRecursosPorTipoYUbicacion(int tipo, String ubicacion) {
        return recursoDAO.consultarRecursosPorTipoYUbicacion(tipo,ubicacion);
    }

    @Override
    public List<Recurso> consultarRecursosPorUbicacionYCapacidad(String ubicacion, int capacidad) {
        return recursoDAO.consultarRecursosPorUbicacionYCapacidad(ubicacion,capacidad);
    }

    @Override
    public List<Reserva> consultarReservas(){
        return reservaDAO.consultarReservas();
    }


    @Override
    public void cambiarRecursoDanado(int id) {
        recursoDAO.cambiarRecursoDanado(id);
    }

    @Override
    public void cambiarRecursoHabilitado(int id) {
        recursoDAO.cambiarRecursoHabilitado(id);
    }

    @Override
    public void cancelarReserva(int idReserva) {
        reservaDAO.cancelarReserva(idReserva);
    }

    @Override
    public ReservaInformacion consultarReservaExtendida(int id, Usuario user) {
        Reserva reservas = reservaDAO.consultarReservaExtendida(id);

        Recurso recurso = consultarRecursosPorId(reservas.getRecurso().getId());
        ReservaInformacion reserva = (new ReservaInformacion(reservas.getId(), recurso.getNombre(),
                reservas.getSolicitud(), reservas.getInicio(), reservas.getFin(), user.getNombre(),
                user.getPrograma(), reservas.isRecurrente(),reservas.getEstado()));
        return reserva;
    }

    @Override
    public Reserva consultarReservaPorId(int id) {
        return reservaDAO.consultarReserva(id);
    }

    @Override
    public List<Reserva> consultarReservasActivas(int id) {
        return reservaDAO.consultarReservasActivas(id);
    }

    @Override
    public List<Reserva> consultarReservasCanceladas(int id) {
        return reservaDAO.consultarReservasCanceladas(id);
    }

    @Override
    public List<Reserva> consultarReservasPasadas(int id) {
        return reservaDAO.consultarReservasPasadas(id);
    }

    @Override
    public Recurso consultarNombreRecurso(int id){
        return recursoDAO.consultarNombreRecurso(id);
    }

    @Override
    public List<Reserva> consultarReservasPorUsuario(int id){
        return reservaDAO.consultarReservasPorUsuario(id);
    }

    @Override
    public void agregarHorarios(String[] horarios){
        for(String h : horarios){
            List<String> lista;
            lista = Arrays.asList(h.split(":"));
            Time hora_ini = new Time(Integer.parseInt(lista.get(0)), Integer.parseInt(lista.get(1)), 0);
            Time hora_fin = new Time(Integer.parseInt(lista.get(0)) + 1, Integer.parseInt(lista.get(1)), 0);
            horarioDAO.guardar(hora_ini, hora_fin);
        }
    }
}
