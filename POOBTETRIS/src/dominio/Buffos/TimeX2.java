package dominio.Buffos;

import presentacion.PanelGame;

public class TimeX2 extends Buffo{

    public TimeX2(PanelGame panelGame){
        super();
        tipo = 'x';
        this.panelGame = panelGame;
    }
    /**
     * Metodo que retorna el tipo del Buffo
     * @return tipo del Buffo
     */
    public  char getTipo(){
        return tipo;
    }

    /**
     * Metodo que cambia posicion X del Buffo en el Tablero
     * @param x position X
     */
    public void setPositionX(int x) {
        this.x = x;
    }

    /**
     * Metodo que cambia posicion Y del Buffo en el Tablero
     * @param y position Y
     */
    public void setPositionY(int y) {
        this.y = y;
    }

    /**
     * Metodo que retorna posicion x del Buffo en el Tablero
     * @return position X
     */
    public int getPositionX() {
        return this.x;
    }

    /**
     * Metodo que retorna posicion Y del Buffo en el Tablero
     * @return position Y
     */
    public int getPositionY() {
        return this.y;
    }

    public void poder() throws InterruptedException {

            int tiempo = panelGame.getAccionInd().getTime();
            while(tiempo != panelGame.getAccionInd().getTime() +3){
                Thread.sleep(panelGame.getAccionInd().getVelTime()/2);
                panelGame.getAccionInd().getTablero().abajo();
                tiempo ++;
            }
            panelGame.getAccionInd().setTime(tiempo-panelGame.getAccionInd().getTime());
            panelGame.getAccionInd().setBuffo(null);
            System.out.println(tipo);
    }
}
