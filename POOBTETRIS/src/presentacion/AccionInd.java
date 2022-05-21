package presentacion;

import dominio.*;
import dominio.Buffos.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class AccionInd extends Thread {

    //PANEL GAME
    PanelGameInd panelGame;

    //VELOCIDAD
    private int velTime;
    private int level;
    private int time;

    //Incremento
    private Timer timer;
    private int contTime;
    private boolean stop;
    private boolean running;

    //TABLERO
    private Tablero tablero;

    //BUFFOS
    private Buffo buffo;
    private char ultTecla = '-';

    /**
     * Constructor de la clase AccionInd
     * @param panel Panel que llama este metodo
     * @param tablero Tablero del Juego
     */
    public AccionInd(PanelGameInd panel, Tablero tablero) {
        this.panelGame = panel;
        this.velTime = 1200;
        this.level = 1;
        this.time = 0;
        this.contTime = 0;
        this.stop = false;
        this.tablero = tablero;
        this.running = true;
    }

    /**
     * Metodo que realiza una accion cada 10 segundos
     */
    public void aumentarTime() {
        timer = new Timer();
        timer.schedule(new contador(),0, 1000);
    }

    /**
     * Clase que incrementa Level
     */
    class contador extends TimerTask {
        public void run() {
            if(!stop) {
                if (contTime == 10) {
                    level += 1;
                    panelGame.updateLevel(level);
                    if (velTime > 0 ) velTime -= 40;
                    contTime = 0;
                }
                else {
                    contTime ++;
                }
            }
        }
    }

    /**
     * Metodo para terminar el juego
     */
    public void finishGame() {
        this.running = false;
    }

    /**
     * Metodo Run
     */
    @Override
    public void run() {
        try {
            while (running) {
                //Pausar el hilo
                synchronized (this) {
                    while (stop) {
                        this.wait();
                    }
                }
                //TIME
                Thread.sleep(velTime);
                tablero.refresque();
                tablero.agregarBuffo();
                time += 1;
                panelGame.updateTime(time);
                panelGame.updateScore(tablero.getScore());
                //tablero.printBuff();
                //tablero.print();
                //JUEGO
                tablero.abajo();
                if(!tablero.gameOver()){
                    if(buffo != null){
                        buffo.poder();
                    }
                    if(!tablero.isActive()){
                        tablero.agregarFicha();
                    }
                }else {
                    panelGame.stopGame();
                    panelGame.getPanelGame().gameOver();
                }
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Método que detiene o reanuda el movimiento
     * @param stop true si para el juego
     */
    public synchronized void stopGame(boolean stop){
        this.stop = stop;
        if (!stop) {
            this.notifyAll();
        }
    }

    /**
     * Metodo para usar el buffo
     */
    public void usarBuffo(){
        char tipo = tablero.usarBuffo();
        if(tipo == 't')buffo = new StopDiece(panelGame.getPanelGame());
        else if(tipo == 'd')buffo = new StopTime(panelGame.getPanelGame());
        else if(tipo == 's')buffo = new Slow(panelGame.getPanelGame());
        else buffo = new TimeX2(panelGame.getPanelGame());
        if(buffo instanceof StopDiece){
            ultTecla = '-';
        }
    }

    /**
     * Metodo para saber Ulima tecla
     * @param ult Ultima tecla
     */
    public void setUltTecla(char ult){
        this.ultTecla = ult;
    }

    /**
     * Metodo para cambiar Score del Tablero
     * @param score score
     */
    public void setScore(int score) {
        tablero.setScore(score);
    }

    /**
     * Metodo para cambiar Tiempo de la partida
     * @param time Tiempo
     */
    public void setTime(int time) {
        this.time = time;
    }

    public int getTime(){
        return  time;
    }

    public int getVelTime(){
        return  velTime;
    }

    /**
     * Metodo para setear el buffo
     * @param buffo nuevo buffo
     */
    public void setBuffo(Buffo buffo){
        this.buffo = buffo;
    }

    /**
     * Metodo para cambiar nivel del tablero
     * @param level nivel
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Metodo para cambiar velocidad del moviemiento
     * @param velTime velocidad del movimiento
     */
    public void setVelTime(int velTime) {
        this.velTime = velTime;
    }

    /**
     * Metodo para cambiar el contador que incrementa el tiempo
     * @param contTime valor ContTime
     */
    public void setContTime(int contTime) {
        this.contTime = contTime;
    }

    /**
     * Metodo para cambiar el numero de buffos usado en la partida
     * @param numBuffos numero de Buffos
     */
    public void setBuffos(int numBuffos) {
        this.tablero.setNumBuffos(numBuffos);
    }

    /**
     * Metodo para escribir puntajes de la partida
     * @param file Archivo a sobreescribir
     */
    public void escribirDatosPartida(PrintWriter file) {
        file.print("time " + time + '\n');
        file.print("level " + level + '\n');
        file.print("score " + tablero.getScore() + '\n');
        file.print("nombre " + panelGame.getName() + '\n');
        file.print("color " + panelGame.getColor() + '\n');
        file.print("CanBuffos " + tablero.getNumBuffos() + '\n');
        file.print("VelPartida " + velTime + " " + contTime + '\n');
        file.print("modoSpeed " + panelGame.getPanelGame().getMoodSpeed() + '\n');
    }

    public char getUltTecla(){
        return ultTecla;
    }

    public Tablero getTablero(){
        return tablero;
    }
}
