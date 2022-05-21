package presentacion;

import dominio.Tablero;
import dominio.Tetris;
import dominio.TetrisException;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

public class AccionMul extends Thread {

    //PANEL GAME
    PanelGameMul panelGame;
    private int numTablero;

    //VELOCIDAD
    private int velTime;
    private int level;
    private int time;

    //Incremento
    private Timer timer;
    private boolean stop;
    private int contTime;
    private boolean running;

    //Tablero
    private Tablero tablero;

    //Buffos
    private char buffo;
    private char ultTecla = '-';

    //Fichas
    private int contador = 0;

    /**
     * Constructor de la clase AccionMul
     * @param panel PanelGameMul
     * @param tablero Tablero del Juego
     * @param numTablero Numero de Tablero
     */
    public AccionMul(PanelGameMul panel, Tablero tablero, int numTablero) {
        this.panelGame = panel;
        this.numTablero = numTablero;
        this.velTime = 1000;
        this.contTime = 0;
        this.level = 1;
        this.time = 0;
        this.stop = false;
        this.tablero = tablero;
        running = true;
    }

    /**
     * Metodo que realiza una accion cada 10 segundos
     */
    public void aumentarTime() {
        timer = new Timer();
        timer.schedule(new contador(),0, 1000);
    }

    /**
     * Metodo que incrementa Level
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
                //tablero.printBuff();
                //tablero.print();
                if (numTablero == 1) {
                    panelGame.updateTime1(time);
                    panelGame.updateScore1(tablero.getScore());
                }
                if (numTablero == 2) {
                    panelGame.updateTime2(time);
                    panelGame.updateScore2(tablero.getScore());
                }
                //JUEGO
                tablero.abajo();
                if(!tablero.gameOver()){
                    if(buffo == 't'){
                        Thread.sleep(3000);
                        buffo = '-';
                        time +=3;
                    }
                    else if(buffo == 'd'){
                        while(ultTecla != 's'){
                            stopGame(true);
                        }
                        stopGame(false);
                        buffo = '-';
                    }
                    else if(buffo == 's'){
                        int tiempo = time;
                        while(tiempo != time+3){
                            Thread.sleep(velTime*2);
                            tablero.abajo();
                            tiempo ++;
                        }
                        time += tiempo-time;
                        buffo = '-';

                    }
                    else if(buffo == 'x'){
                        int tiempo = time;
                        while(tiempo != time+6){
                            Thread.sleep(velTime/2);
                            tablero.abajo();
                            tiempo ++;
                        }
                        time += tiempo-time;
                        buffo = '-';
                    }
                    if(!tablero.isActive()){
                        tablero.agregarFichaEspecifica(getFicha(),getTipoFicha());
                        contador +=1;
                    }
                }else {
                    System.out.println("TERMINO MUL " + numTablero + "");
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
        buffo = tablero.usarBuffo();
        if(buffo == 'd'){
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
     * Metodo que Retorna la parte logica del Juego
     */
    public Tetris getTetris() {
        return tablero.getTetris();
    }

    /**
     * Metodo que retorna la ficha que debe agregarse
     * @return char
     */
    public char getFicha(){
        if(contador == 100){
            contador = 0;
        }
        return panelGame.getFicha(contador);
    }

    /**
     * Metodo para conocer el tipo de la ficha actual
     * @return tipo ficha
     */
    public int getTipoFicha(){
        return panelGame.getTipoFicha(contador);
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
        if (numTablero == 1 ) {
            file.print("nombre " + panelGame.getName1() + '\n');
            file.print("color " + panelGame.getColor1() + '\n');
        }
        if (numTablero == 2 ) {
            file.print("nombre " + panelGame.getName2() + '\n');
            file.print("color " + panelGame.getColor2() + '\n');
        }
        file.print("CanBuffos " + tablero.getNumBuffos() + '\n');
        file.print("VelPartida " + velTime + " " + contTime + '\n');
        file.print("modoSpeed " + panelGame.getPanelGame().getMoodSpeed() + '\n');
    }

}
