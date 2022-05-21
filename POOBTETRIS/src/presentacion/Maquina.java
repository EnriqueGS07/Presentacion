package presentacion;

import dominio.Tetris;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Maquina{

    private Robot robot;
    private int delay;
    private static String[] actions = {"arriba","abajo","derecha","izquierda","rotar"};
    private Tetris tablero;

    /**
     * Contrtuctor de la clase maquina
     */
    public Maquina(Tetris tablero) {
        try {
            robot = new Robot();
            this.tablero = tablero;
        } catch (AWTException ex) {
            Logger.getLogger(Maquina.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodo para realizar accion Abajo
     */
    public void abajo() {
        robot.keyPress(KeyEvent.VK_DOWN);
        robot.delay(delay);
        robot.keyRelease(KeyEvent.VK_DOWN);
        robot.delay(delay);
    }

    /**
     * Metodo para realizar accion bajar
     */
    public void bajar() {
        robot.keyPress(KeyEvent.VK_SPACE);
        robot.delay(delay);
        robot.keyRelease(KeyEvent.VK_SPACE);
        robot.delay(delay);
    }

    /**
     * Metodo para realizar accion Izquierda
     */
    public void izquierda() {
        robot.keyPress(KeyEvent.VK_LEFT);
        robot.delay(delay);
        robot.keyRelease(KeyEvent.VK_LEFT);
        robot.delay(delay);
    }

    /**
     * Metodo para realizar accion Derecha
     */
    public void derecha() {
        robot.keyPress(KeyEvent.VK_RIGHT);
        robot.delay(delay);
        robot.keyRelease(KeyEvent.VK_RIGHT);
        robot.delay(delay);
    }

    /**
     * Metodo para realizar accion Rotar
     */
    public void rotar() {
        robot.keyPress(KeyEvent.VK_UP);
        robot.delay(delay);
        robot.keyRelease(KeyEvent.VK_UP);
        robot.delay(delay);
    }

    /**
     * Metodo para realizar accion Usar Buffo
     */
    public void ctrl() {
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.delay(delay);
        robot.keyRelease(KeyEvent.VK_SHIFT);
        robot.delay(delay);
    }

    /**
     * Metodo para retornar valor delay
     * @return valor Delay
     */
    public int getDelay() {
        return delay;
    }

    /**
     * Metodo para cambiar valor delay
     * @param delay Delay
     */
    public void setDelay(int delay) {
        this.delay = delay;
    }

    /**
     * Metodo para hacer acciones aleatorias
     */
    public void randomAction(){
        int action = (int) (Math.random() * 6);
        if(action == 1){
            izquierda();
        }
        if(action == 2){
            derecha();
        }
        else if(action == 3){
            abajo();
        }
        else if(action == 4){
            bajar();
          }else if(action == 5){
            rotar();
        }
        else if(action == 6){
            ctrl();
        }
    }

    /**
     * Metodo para iniciar matriz Tablero
     */
    public void intAction(){
        char[][] matriz = tablero.getTablero();
    }

}