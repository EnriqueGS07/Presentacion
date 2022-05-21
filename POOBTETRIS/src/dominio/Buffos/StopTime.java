package dominio.Buffos;

import presentacion.PanelGame;

public class StopTime extends Buffo{

    public StopTime(PanelGame panelGame){
        super();
        tipo = 't';
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
            Thread.sleep(3000);
            panelGame.getAccionInd().setBuffo(null);
            panelGame.getAccionInd().setTime(panelGame.getAccionInd().getTime() + 3);
            System.out.println(tipo);
        }
}
