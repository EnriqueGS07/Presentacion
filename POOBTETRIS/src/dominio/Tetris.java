package dominio;


import dominio.Buffos.*;
import dominio.TiposTetris.*;
import presentacion.PanelGame;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Timer;

public class Tetris {
    private char[][] tablero = new char[20][10];
    public static final char[] FORMAS = {'Z', 'L', 'C', 'T', 'I'};
    private Forma formaActiva = null;
    private int puntaje = 0;
    private boolean gameOver = false;
    //BUFFOS
    private Buffo buffoActivo = null;
    private Buffo buffoEscogido = null;
    private  PanelGame panelGame;

    /**
     * Constructor de la clase tetris
     */
    public Tetris(PanelGame panelGame) {
        prepareTablero();
        this.panelGame = panelGame;
    }
    /**
     * Constructor de la clase tetris
     */
    public Tetris() {
        prepareTablero();
    }

    /**
     * Metodo que prepara el Tablero
     */
    private void prepareTablero() {
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                tablero[i][j] = '0';
            }
        }
    }

    /**
     * Metodo que agrega una ficha al tablero
     */
    public void agregarForma() {
        int pos = (int) (Math.random() * 5);
        char tipo = FORMAS[pos];
        int tipoT = (int) (Math.random() * 4);
        Forma forma;
        if (tipoT == 1) forma = new Useless(tipo);
        else if (tipoT == 2) forma = new Winner(tipo);
        else if (tipoT == 3) forma = new Bomb(tipo);
        else forma = new Forma(tipo);
        forma.crearForma();
        if (puedeAgregar(forma.getAncho(), forma.getAlto())) {
            forma.setActivo(true);
            formaActiva = forma;
            char[][] matrizForma = forma.getForma();
            for (int i = 0; i < forma.getAlto(); i++) {
                for (int j = 0; j < forma.getAncho(); j++) {
                    tablero[i][j + 4] = matrizForma[i][j];
                }
            }
        }
    }

    /**
     * Metodo que agrega una ficha especifica al tablero
     */
    public void agregarFormaEspecifica(char tipo,int tipoT) {
        Forma forma;
        if (tipoT == 1) forma = new Useless(tipo);
        else if (tipoT == 2) forma = new Winner(tipo);
        else if (tipoT == 3) forma = new Bomb(tipo);
        else forma = new Forma(tipo);
        forma.crearForma();
        if (puedeAgregar(forma.getAncho(), forma.getAlto())) {
            forma.setActivo(true);
            formaActiva = forma;
            char[][] matrizForma = forma.getForma();
            for (int i = 0; i < forma.getAlto(); i++) {
                for (int j = 0; j < forma.getAncho(); j++) {
                    tablero[i][j + 4] = matrizForma[i][j];
                }
            }
        }
    }

    /**
     * Metodo que agrega una ficha especifica al tablero
     */
    public void agregarFichaEspecifica(char tipo,int tipoT, int x, int y) {
        Forma forma;
        if (tipoT == 1) forma = new Useless(tipo);
        else if (tipoT == 2) forma = new Winner(tipo);
        else if (tipoT == 3) forma = new Bomb(tipo);
        else forma = new Forma(tipo);
        forma.crearForma();
        if (puedeAgregar(forma.getAncho(), forma.getAlto())) {
            forma.setActivo(true);
            formaActiva = forma;
            char[][] matrizForma = forma.getForma();
            for (int i = 0; i < forma.getAlto(); i++) {
                for (int j = 0; j < forma.getAncho(); j++) {
                    tablero[i+y][j+x] = matrizForma[i][j];
                }
            }
        }
    }

    /**
     * Metodo que revisa si se puede agregar una ficha nueva al tablero
     *
     * @param necesitaEnx casillas que necesita en x para agregar
     * @param necesitaEny casillas que necesita en y para agregar
     * @return true si se puede agregar una ficha
     */
    public boolean puedeAgregar(int necesitaEnx, int necesitaEny) {
        boolean puede = true;
        for (int i = 0; i < necesitaEny; i++) {
            for (int j = 4; j < necesitaEnx + 4; j++) {
                if (tablero[i][j] != '0') {
                    puede = false;
                    gameOver = true;
                }
            }
        }
        return puede;
    }

    /**
     * Funcion que rota la ficha activa del tablero
     */
    public void rotarFicha() {
        int coorX = posicionXFichaActiva();
        int coorY = posicionYFichaActiva();
        if (puedeRotar()) {
            formaActiva.rotar();
            char[][] matrizForma = formaActiva.getForma();
            borrarFichaActiva();
            for (int i = 0; i < formaActiva.getAlto(); i++) {
                for (int j = 0; j < formaActiva.getAncho(); j++) {
                    tablero[i + coorY][j + coorX] = matrizForma[i][j];
                }
            }
        } else System.out.println("No se puede rotar");

    }

    /**
     * Funcion que dice si se puede rotar la ficha
     *
     * @return true si la ficha se puede rotar
     */
    public boolean puedeRotar() {
        boolean puede = true;
        if (formaActiva != null) {
            int necesitaEnX = formaActiva.getAlto();
            int necesitaEnY = formaActiva.getAncho();
            char rep = formaActiva.getRepr();
            for (int i = posicionYFichaActiva(); i < necesitaEnY + posicionYFichaActiva(); i++) {
                for (int j = posicionXFichaActiva(); j < necesitaEnX + posicionXFichaActiva(); j++) {
                    if (!(i <= 19 && j <= 9 && (tablero[i][j] == '0' || tablero[i][j] == rep))) {
                        puede = false;
                    }
                }
            }
            return puede;
        }
        return false;
    }

    /**
     * Metodo que borra la ficha activa de la matriz
     */
    private void borrarFichaActiva() {
        char reprA = formaActiva.getRepr();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                if (tablero[i][j] == reprA) {
                    tablero[i][j] = '0';
                }
            }
        }
    }

    /**
     * Funcion que retorna la primera posicion en x de la figura activa
     *
     * @return
     */
    private int posicionXFichaActiva() {
        int coorX = (int) Double.POSITIVE_INFINITY;
        char reprA = '-';
        if (formaActiva != null) reprA = formaActiva.getRepr();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                if (tablero[i][j] == reprA && j < coorX) {
                    coorX = j;
                }
            }
        }
        return coorX;
    }

    /**
     * Funcion que retorna la primera posicion en y de la figura activa
     *
     * @return
     */
    private int posicionYFichaActiva() {
        int coorY = -1;
        char reprA = '-';
        if (formaActiva != null) reprA = formaActiva.getRepr();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                if (tablero[i][j] == reprA) {
                    return i;
                }
            }
        }
        return coorY;
    }

    /**
     * Funcion que mueve a la derecha la ficha activa
     */
    public void derecha() {
        char reprA = '-';
        if (formaActiva != null) reprA = formaActiva.getRepr();
        ArrayList<int[]> coordenadas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                if (tablero[i][j] == reprA) {
                    coordenadas.add(new int[]{i, j + 1});
                    tablero[i][j] = '0';
                }
            }
        }
        if (puedeDerecha(coordenadas)) {
            for (int[] coo : coordenadas) {
                if (buffoActivo != null) desactivarBuffo(coo);
                tablero[coo[0]][coo[1]] = reprA;
            }
        } else {
            for (int[] coo : coordenadas) {
                tablero[coo[0]][coo[1] - 1] = reprA;
            }
        }
    }

    /**
     * Funcion que verifica si se puede mover a la derecha la ficha
     *
     * @return true si la ficha se puede mover a la derecha
     */
    private boolean puedeDerecha(ArrayList<int[]> coordenadas) {
        boolean puede = true;
        for (int[] coo : coordenadas) {
            if (coo[1] > 9) puede = false;
            else if (tablero[coo[0]][coo[1]] != '0' && tablero[coo[0]][coo[1]] != 't' && tablero[coo[0]][coo[1]] != 'd' && tablero[coo[0]][coo[1]] != 's' && tablero[coo[0]][coo[1]] != 'x') {
                puede = false;
            }
        }
        return puede;
    }

    /**
     * Funcion que mueve a la izquierda la ficha activa
     */
    public void izquierda() {
        char reprA = '-';
        if (formaActiva != null) reprA = formaActiva.getRepr();
        ArrayList<int[]> coordenadas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                if (tablero[i][j] == reprA) {
                    coordenadas.add(new int[]{i, j - 1});
                    tablero[i][j] = '0';
                }
            }
        }
        if (puedeIzquierda(coordenadas)) {
            for (int[] coo : coordenadas) {
                if (buffoActivo != null) desactivarBuffo(coo);
                tablero[coo[0]][coo[1]] = reprA;
            }
        } else {
            for (int[] coo : coordenadas) {
                tablero[coo[0]][coo[1] + 1] = reprA;
            }
        }
    }

    /**
     * Funcion que verifica si se puede mover a la derecha la ficha
     *
     * @return true si la ficha se puede mover a la derecha
     */
    private boolean puedeIzquierda(ArrayList<int[]> coordenadas) {
        boolean puede = true;
        for (int[] coo : coordenadas) {
            if (coo[1] < 0) puede = false;
            else if (tablero[coo[0]][coo[1]] != '0' && tablero[coo[0]][coo[1]] != 't' && tablero[coo[0]][coo[1]] != 'd' && tablero[coo[0]][coo[1]] != 's' && tablero[coo[0]][coo[1]] != 'x') {
                puede = false;
            }
        }
        return puede;
    }

    /**
     * Funcion que mueve la ficha activa hacia abajo
     */
    public void abajo() {
        if (puedeBajar()) {
            char reprA = '-';
            if (formaActiva != null) reprA = formaActiva.getRepr();
            ArrayList<int[]> coordenadas = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 10; j++) {
                    if (tablero[i][j] == reprA) {
                        coordenadas.add(new int[]{i + 1, j});
                        tablero[i][j] = '0';
                    }
                }
            }
            for (int[] coo : coordenadas) {
                if (buffoActivo != null) desactivarBuffo(coo);
                tablero[coo[0]][coo[1]] = reprA;

            }
        } else cambiarAInactivo();
        limpiarTablero();
    }

    /**
     * Funcion que cambia a estado 'a': abajo a la ficha que no puede bajar mas
     */
    private void cambiarAInactivo() {
        caeBomba();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                if (tablero[i][j] == '1') {
                    tablero[i][j] = 'q';
                    if (formaActiva instanceof Useless) {
                        tablero[i][j] = 'g';
                    }
                }
                if (tablero[i][j] == '2') {
                    tablero[i][j] = 'w';
                    if (formaActiva instanceof Useless) {
                        tablero[i][j] = 'h';
                    }
                }
                if (tablero[i][j] == '3') {
                    tablero[i][j] = 'e';
                    if (formaActiva instanceof Useless) {
                        tablero[i][j] = 'j';
                    }
                }
                if (tablero[i][j] == '4') {
                    tablero[i][j] = 'r';
                    if (formaActiva instanceof Useless) {
                        tablero[i][j] = 'k';
                    }
                }
                if (tablero[i][j] == '5') {
                    tablero[i][j] = 'y';
                    if (formaActiva instanceof Useless) {
                        tablero[i][j] = 'l';
                    }
                }
            }
        }
        formaActiva = null;
    }

    /**
     * Metodo que hace explotar una ficha bomba
     */
    public void caeBomba() {
        if (formaActiva instanceof Bomb) {
            ArrayList<int[]> coor = getCoorFichaActiva();
            for (int[] c : coor) {
                tablero[c[0]][c[1]] = '0';
                if (c[0] + 1 < 20) {
                    tablero[c[0] + 1][c[1]] = '0';
                }
                if (c[1] + 1 < 10) {
                    tablero[c[0]][c[1] + 1] = '0';
                }
                if (c[1] - 1 >= 0) {
                    tablero[c[0]][c[1] - 1] = '0';
                }
                if (c[0] - 1 >= 0) {
                    tablero[c[0] - 1][c[1]] = '0';
                }
                if (c[0] - 1 >= 0 && c[1] - 1 >= 0) {
                    tablero[c[0] - 1][c[1] - 1] = '0';
                }
                if (c[0] + 1 < 20 && c[1] + 1 < 10) {
                    tablero[c[0] + 1][c[1] + 1] = '0';
                }
                if (c[0] - 1 >= 0 && c[1] + 1 < 10) {
                    tablero[c[0] - 1][c[1] + 1] = '0';
                }
                if (c[0] + 1 < 20 && c[1] - 1 >= 0) {
                    tablero[c[0] + 1][c[1] - 1] = '0';
                }
            }
            puntaje += 40;
        }
    }

    /**
     * Metodo que revisa si se puede bajar la ficha activa
     *
     * @return true si la ficha puede bajar
     */

    public boolean puedeBajar() {
        char reprA = '-';
        if (formaActiva != null) reprA = formaActiva.getRepr();
        ArrayList<int[]> coordenadas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                if (tablero[i][j] == reprA) {
                    coordenadas.add(new int[]{i + 1, j});
                }
            }
        }
        for (int[] coo : coordenadas) {
            if (coo[0] >= 20) {
                return false;
            } else if (tablero[coo[0]][coo[1]] != '0' && tablero[coo[0]][coo[1]] != reprA && tablero[coo[0]][coo[1]] != 't' && tablero[coo[0]][coo[1]] != 'd' && tablero[coo[0]][coo[1]] != 's' && tablero[coo[0]][coo[1]] != 'x') {
                return false;
            }
        }
        return true;
    }

    /**
     * Funcion que baja la ficha hasta que se pueda
     */
    public void bajar() {
        while (puedeBajar()) {
            abajo();
        }
        cambiarAInactivo();
    }

    /**
     * Metodo que borra una linea dada
     *
     * @param row linea que se
     */
    public void borrarLinea(int row) {
        for (int i = row - 1; i > 0; i--) {
            for (int j = 0; j < 10; j++) {
                tablero[i + 1][j] = tablero[i][j];
            }
        }
        puntaje += 100;
    }


    /**
     * Metodo que revisa si una linea esta llena
     *
     * @param row linea a revisar
     * @return true si la linea esta llena
     */
    private boolean lineaLlena(char[] row) {
        boolean llena = true;
        for (char i : row) {
            if (i != 'q' && i != 'w' && i != 'e' && i != 'r' && i != 'y') {
                llena = false;
                break;
            }
        }
        return llena;
    }


    /**
     * Metodo que borra una linea si esta completa
     */
    public void limpiarTablero() {
        for (int i = 0; i < 20; i++) {
            if (lineaLlena(tablero[i])) {
                borrarLinea(i);
            }
        }
    }

    /**
     * Obtener la forma activa
     *
     * @return forma como objeto
     */
    public Forma getFormaActiva() {
        return formaActiva;
    }

    /**
     * Obtener la matriz del tablero
     *
     * @return matriz del tablero
     */
    public char[][] getTablero() {
        return tablero;
    }

    /**
     * Funcion que imprime la matriz del juego;
     */
    public void print() {
        for (char[] chars : tablero) {
            System.out.println(Arrays.toString(chars));
        }
        System.out.println("------------------------------------------------");
    }

    /**
     * Metodo que retorna si el juego se ha acabado
     *
     * @return true si el juego se acabo
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Metodo para poner un elemento en una posicion especifica deltablero
     *
     * @param y          posicion en y
     * @param x          posicion en x
     * @param newElement elemento a agregar
     */
    public void setTablero(int y, int x, char newElement) {
        tablero[y][x] = newElement;
    }


    /**
     * Metodo que busca las coordenadas de una ficha en el Tablero
     * @return Coordenadas
     */
    public ArrayList<int[]> getCoorFichaActiva() {
        char reprA = '-';
        if (formaActiva != null) reprA = formaActiva.getRepr();
        ArrayList<int[]> coordenadas = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 10; j++) {
                if (tablero[i][j] == reprA) {
                    coordenadas.add(new int[]{i, j});
                }
            }
        }
        return coordenadas;
    }

    /**
     * Metodo que deja jugar desde consola
     */
    public void jugar() {
        while (!gameOver) {
            if (formaActiva == null) {
                agregarForma();
                print();
            } else {
                abajo();
                print();
                Scanner entrada = new Scanner(System.in);
                String in = entrada.next();
                if (in.equals("s")) abajo();
                else if (in.equals("a")) izquierda();
                else if (in.equals("d")) derecha();
                else if (in.equals("w")) rotarFicha();
                else if (in.equals("x")) bajar();
                print();
            }
        }
    }

    /**
     * Funcion qe retorna el puntaje del tablero
     * @return puntaje
     */
    public int getPuntaje() {
        return puntaje;
    }

    //BUFFOS

    /**
     * Metodo que agrega un buffo a la matriz
     */
    public void agregarBuffo() {
        int tipo = (int) (Math.random() * 4);
        if(tipo == 0)buffoActivo = new StopDiece(panelGame);
        else if(tipo == 1)buffoActivo = new StopTime(panelGame);
        else if(tipo == 2)buffoActivo = new Slow(panelGame);
        else buffoActivo = new TimeX2(panelGame);
//        System.out.println(tipo);
        int posX = (int) (Math.random() * 10);
        int posY = (int) (Math.random() * 16) + 3;
        while (tablero[posY][posX] != '0') {
            posX = (int) (Math.random() * 10);
            posY = (int) (Math.random() * 16) + 3;
        }
        tablero[posY][posX] = buffoActivo.getTipo();
        buffoActivo.setPositionX(posX);
        buffoActivo.setPositionY(posY);
    }


    /**
     * Metodo que agrega un buffo especifico a la matriz
     */
    public void agregarBuffoEspecifico(char type, int x, int y){
        if(type == 't')buffoActivo = new StopDiece(panelGame);
        else if(type == 'd')buffoActivo = new StopTime(panelGame);
        else if(type == 's')buffoActivo = new Slow(panelGame);
        else buffoActivo = new TimeX2(panelGame);
        tablero[y][x] = buffoActivo.getTipo();
//        System.out.println(buffoActivo.getTipo());
        buffoActivo.setPositionX(x);
        buffoActivo.setPositionY(y);
    }

    /**
     * Metodo para escribir matriz en archivo txt
     */
    public void writeTetris(PrintWriter file) {
        for (int i = 0; i < 20; i++) {
            String c = "";
            for (int j=0; j<10; j++) {
                if(tablero[i][j] == formaActiva.getRepr()){
                    c += "0 ";
                }
                else {
                    c += tablero[i][j] + " ";
                }
            }
            file.print(c + '\n');
        }
    }

    /**
     * Metodo para escribir datos del Buffo actual en archivo txt
     */
    public void writeBuffo(PrintWriter file) {
        if (buffoEscogido != null) file.print("buffoEscogido " + getBuffoEscogido().getTipo() + '\n');
        else file.print("buffoActivo "+getBuffoActivo().getTipo()+ " "+getBuffoActivo().getPositionX()+" "+getBuffoActivo().getPositionY()+'\n');
    }

    /**
     * Metodo que escribe caracterizticas del Buffo Actual en la partida
     * @param file Archivo donde se escribe
     */
    public void writeTetromino(PrintWriter file) {
        file.print("FichaActiva "+ formaActiva.getTipo() +" "+getTipoTetromino()+" "+posicionXFichaActiva()+" "+posicionYFichaActiva()+'\n');
    }

    /**
     * Metodo para añadir un buffo especifico al tablero
     * @param type tipo del Buffo
     */
    public void setBuffoEscogido(char type) {
        if(type == 't')buffoEscogido = new StopDiece(panelGame);
        else if(type == 'd')buffoEscogido = new StopTime(panelGame);
        else if(type == 's')buffoEscogido = new Slow(panelGame);
        else buffoEscogido = new TimeX2(panelGame);
    }

    /**
     * Metodo para añadir un buffo especifico al tablero
     * @param type tipo del Buffo
     */
    public void setBuffoActivo(char type) {
        if(type == 't')buffoActivo = new StopDiece(panelGame);
        else if(type == 'd')buffoActivo = new StopTime(panelGame);
        else if(type == 's')buffoActivo = new Slow(panelGame);
        else buffoActivo = new TimeX2(panelGame);
    }

    /**
     * funcion que retorna el buffo en pantalla
     * @return buffo en el tablero
     */
    public Buffo getBuffoActivo(){
        return  buffoActivo;
    }

    /**
     * funcion que retorna el buffo en poder del jugador
     * @return buffo que tiene el jugador
     */
    public Buffo getBuffoEscogido(){
        return  buffoEscogido;
    }

    /**
     * Metodo que retorna el tipo del tetromino
     * @return Tipo
     */
    public int getTipoTetromino() {
        if(formaActiva instanceof Useless) return 1;
        if(formaActiva instanceof Winner) return 2;
        if(formaActiva instanceof Bomb) return 3;
        else return  0;
    }

    /**
     * Usar el buffo que se cogio del tablero
     * @return representacion del buffo en el tablero
     */
    public char usarBuffo(){
        char tipo = '-';
        if(buffoEscogido != null) {
            tipo = buffoEscogido.getTipo();
        }
        buffoEscogido = null;
//        System.out.println("se uso el buff");
        return tipo;
    }

    /**
     * Metodo que desactiva el buffo
     * @param coo posicion del puffo
     */
    public void desactivarBuffo(int[] coo){
        if(tablero[coo[0]][coo[1]] == buffoActivo.getTipo()){
            buffoEscogido = buffoActivo;
            buffoActivo = null;
//            System.out.println("Se desactivo");
        }
    }

    /**
     * Metodo que improime estado del buffo
     */
    public void impBuff(){
        if(buffoActivo != null)System.out.println("Activo =" + buffoActivo.getTipo());
        if(buffoEscogido != null)System.out.println("Escogido =" + buffoEscogido.getTipo());
    }

    /**
     * Metodo que cambia valor Score
     * @param score Valor Score
     */
    public void setPuntaje(int score) {
        this.puntaje = score;
    }

    /**
     * Metodo que cambia el tablero del Juego
     * @param tablero Tablero nuevo
     */
    public void setTablero(char[][] tablero) {
        this.tablero = tablero;
    }

    /**
     * Metodo main
     * @param args Argumentos
     */
    public static void main(String[] args) {
        Tetris t = new Tetris();
        t.jugar();
    }
}