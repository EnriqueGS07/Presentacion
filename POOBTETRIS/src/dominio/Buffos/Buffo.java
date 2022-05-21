package dominio.Buffos;

import dominio.Tetris;
import presentacion.AccionInd;
import presentacion.PanelGame;

public abstract class Buffo{


    private static final char[] TIPOS = {'t', 'd', 's', 'x'};
    protected char tipo;
    protected int x;
    protected int y;
    protected PanelGame panelGame;

    /**
     * Metodo que retorna el tipo del Buffo
     * @return tipo del Buffo
     */
    public  abstract char getTipo();

    /**
     * Metodo que cambia posicion X del Buffo en el Tablero
     * @param x position X
     */
    public abstract void setPositionX(int x) ;

    /**
     * Metodo que cambia posicion Y del Buffo en el Tablero
     * @param y position Y
     */
    public abstract void setPositionY(int y) ;

    /**
     * Metodo que retorna posicion x del Buffo en el Tablero
     * @return position X
     */
    public abstract int getPositionX();

    /**
     * Metodo que retorna posicion Y del Buffo en el Tablero
     * @return position Y
     */
    public abstract int getPositionY();

    public abstract void poder() throws InterruptedException;
}