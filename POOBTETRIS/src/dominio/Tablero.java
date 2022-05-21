package dominio;

import dominio.TiposTetris.*;
import presentacion.PanelGame;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

public class Tablero extends JPanel {
    //Tamaño Tablero
    private final int ancho = 10;
    private final int alto = 20;
    private int x;
    private int y;
    private int numBuffos;
    private JPanel[][] cuadricula = new JPanel[alto][ancho];
    private Tetris juego;
    private JLabel[][] labelsBuffos = new JLabel[alto][ancho];
    private PanelGame panelGame;

    /**
     * Constructor de la cklase tablero
     */
    public Tablero(PanelGame panelGame) {
        this.setBackground(Color.white);
        this.setLayout(new GridLayout(alto,ancho,1,1));
        this.x = 238/10;
        this.y = 410/20;
        this.numBuffos = 100;
        this.setBackground(Color.black);
        this.juego = new Tetris(panelGame);
        hacerCuadricula();
        this.panelGame = panelGame;
    }

    /**
     * Metodo que hace la cuadricula del tablero
     */
    public void hacerCuadricula(){
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                JPanel panel = new JPanel();
                add(panel);
                cuadricula[i][j] = panel;
                panel.setBackground(Color.gray);
                JLabel label = new JLabel();
                label.setBounds(cuadricula[i][j].getX(),cuadricula[i][j].getX(),22, 22);
                labelsBuffos[i][j] = label;
                cuadricula[i][j].add(labelsBuffos[i][j]);
            }
        }
    }

    /**
     * Metodo que agrega una ficha al tablero
     */
    public void agregarFicha(){
        juego.agregarForma();
        refresque();
    }

    /**
     * Metodo que agrega una ficha especifica
     * @param tipo Tetromino
     * @param tipoT Tipo de Tetromino
     */
    public void agregarFichaEspecifica(char tipo,int tipoT){
        juego.agregarFormaEspecifica(tipo,tipoT);
        refresque();
    }

    /**
     * Metodo que agrega una ficha especifica
     * @param tipo Tetromino
     * @param tipoT Tipo de Tetromino
     * @param x PositionX
     * @param y Position Y
     */
    public void agregarFichaEspecifica(char tipo,int tipoT, int x, int y){
        juego.agregarFichaEspecifica(tipo,tipoT,x,y);
        refresque();
    }

    /**
     * Metodo que rota la ficha activa del tablero
     */
    public void rotar(){
        juego.rotarFicha();
        refresque();
    }

    /**
     * Metodo que manda a la derecha la ficha activa del tablero
     */
    public void derecha(){
        juego.derecha();
        refresque();
    }

    /**
     * Metodo que manda a la izquuierda la ficha activa del tablero
     */
    public void izquierda(){
        juego.izquierda();
        refresque();
    }

    /**
     * Metodo que baja la ficha activa del tablero
     */
    public void abajo(){
        juego.abajo();
        refresque();
    }

    /**
     * Metodo que baja la ficha activa del tablero hasta el fondo
     */
    public void bajar() {
        while (juego.puedeBajar()){
            abajo();
        }
    }

    /**
     * Metodo para imprimir estado del juego
     */
    public void print(){
        juego.print();
    }

    /**
     * Metodo que refresca los graficos del tablero
     */
    public void refresque(){
        //BORDES
        Border bordeBomb = BorderFactory.createLineBorder(Color.RED, 2);
        Border bordeUseless = BorderFactory.createLineBorder(new Color(138,149,151), 2);
        Border bordeWinner = BorderFactory.createLineBorder(new Color(206,150,2), 2);
        Border bordeClassic = BorderFactory.createLineBorder(Color.black, 2);
        char[][] tablero = juego.getTablero();
        ArrayList<int[]> posAct= juego.getCoorFichaActiva();
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
//                cuadricula[i][j].setLayout(new GridLayout(1,1));
                if(tablero[i][j] == '0')cuadricula[i][j].setBackground(Color.gray);cuadricula[i][j].setBorder(null);labelsBuffos[i][j].setIcon(null);
                if (juego.getFormaActiva() instanceof Bomb){
                    for (int[] c:posAct){
                        cuadricula[c[0]][c[1]].setBorder(bordeBomb);
                    }
                }
                if (juego.getFormaActiva() instanceof Useless){
                    for (int[] c:posAct){
                        cuadricula[c[0]][c[1]].setBorder(bordeUseless);
                    }
                }
                 if (juego.getFormaActiva() instanceof Winner){
                    for (int[] c:posAct){
                        cuadricula[c[0]][c[1]].setBorder(bordeWinner);
                    }
                }
                if (!(juego.getFormaActiva() instanceof Winner) && !(juego.getFormaActiva() instanceof Bomb) && !(juego.getFormaActiva() instanceof Useless)){
                    for (int[] c:posAct){
                        cuadricula[c[0]][c[1]].setBorder(bordeClassic);
                    }
                }
                //FICHAS
                if (tablero[i][j] == '1' || tablero[i][j] == 'q' || tablero[i][j] == 'g' )cuadricula[i][j].setBackground(Color.green);
                if (tablero[i][j] == '2'|| tablero[i][j] == 'w'|| tablero[i][j] == 'h' )cuadricula[i][j].setBackground(Color.orange);
                if (tablero[i][j] == '3'|| tablero[i][j] == 'e'|| tablero[i][j] == 'j' )cuadricula[i][j].setBackground(Color.yellow);
                if (tablero[i][j] == '4'|| tablero[i][j] == 'r'|| tablero[i][j] == 'k' )cuadricula[i][j].setBackground(Color.magenta);
                if (tablero[i][j] == '5'|| tablero[i][j] == 'y'|| tablero[i][j] == 'l' )cuadricula[i][j].setBackground(Color.cyan);
                if(tablero[i][j] == 'g' || tablero[i][j] == 'h'||tablero[i][j] == 'j'||tablero[i][j] == 'k'||tablero[i][j] == 'l')cuadricula[i][j].setBorder(bordeUseless);
                if(tablero[i][j] == 'A'|| tablero[i][j] == 'B'|| tablero[i][j] == 'C'|| tablero[i][j] == 'D'|| tablero[i][j] == 'E')cuadricula[i][j].setBorder(bordeBomb);
                //BUFFOS
                ImageIcon imagenFondoT = new ImageIcon("./images/stopTime.png");
                ImageIcon imagenFondoD = new ImageIcon("./images/stopDiece.png");
                ImageIcon imagenFondoS = new ImageIcon("./images/slow.png");
                ImageIcon imagenFondoX = new ImageIcon("./images/2x.png");
                if (tablero[i][j] == 't')labelsBuffos[i][j].setIcon(imagenFondoT);
                if (tablero[i][j] == 'd')labelsBuffos[i][j].setIcon(imagenFondoD);
                if (tablero[i][j] == 's')labelsBuffos[i][j].setIcon(imagenFondoS);
                if (tablero[i][j] == 'x')labelsBuffos[i][j].setIcon(imagenFondoX);
            }
        }
    }

    /**
     * Metodo que revisa si el juego se acabo
     * @return true si se acabo el juego
     */
    public boolean gameOver(){
        return juego.isGameOver();
    }


    /**
     * Metodo que dice si el tablero tiene una ficha activa
     * @return true si tiene una ficha activa
     */
    public boolean isActive(){
        return juego.getFormaActiva() != null;
    }

    /**
     * Metodo que agrega un buffo al tablero
     */
    public void agregarBuffo(){
        if(juego.getBuffoEscogido() == null && juego.getBuffoActivo() == null && this.numBuffos > 0){
            juego.agregarBuffo();
            this.numBuffos -= 1;
        }
    }

    /**
     * Metodo para usar el buffo
     */
    public char usarBuffo(){
        return juego.usarBuffo();
    }

    /**
     * Metodo para imprimir estado de los Buffos
     */
    public void  printBuff(){
        juego.impBuff();
    }

    /**
     * Metodo que retorna clade Tetris
     * @return Clase Tetris
     */
    public Tetris getTetris() {
        return juego;
    }

    /**
     * Metodo que cambiar valor Score
     * @param score Score
     */
    public void setScore(int score) {
        this.juego.setPuntaje(score);
    }

    /**
     * Metodo que cambiar el numero de Buffos
     * @param numBuffos Numero de Buffos
     */
    public void setNumBuffos(int numBuffos) {
        this.numBuffos = numBuffos;
    }

    /**
     * Metodo que retorna el numero de Buffos
     * @return Numero de Buffos
     */
    public int getNumBuffos() {
        return this.numBuffos;
    }

    /**
     * Funcion que da el puntaje del juego
     * @return puntaje
     */
    public int getScore(){
        return  juego.getPuntaje();
    }

}

