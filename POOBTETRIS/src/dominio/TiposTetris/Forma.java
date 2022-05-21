package dominio.TiposTetris;

import java.util.Arrays;
import java.util.HashMap;

public class Forma {
    private char tipo;
    private char[][] forma;
    private boolean activo;
    private char repr;
    private int ancho;
    private int alto;
    private HashMap<String, char[][]> estados = new HashMap<String, char[][]>();

    /**
     * Constructor de la clase forma
     * @param tipo tipo de la forma
     */
    public Forma(char tipo){
        this.tipo = tipo;
    }

    /**
     * Metodo para crear un tetromino
     * @return matriz de tetromino
     */
    public char[][] crearForma(){
        if (tipo == 'Z'){
            crearZ();
        }
        else if (tipo == 'L'){
            crearL();
        }
        else if (tipo == 'C'){
            crearC();
        }
        else if (tipo == 'T'){
            crearT();
        }
        else if (tipo == 'I'){
            crearI();
        }
        return forma;
    }


    /**
     * Metodo que crea la z
     */
    private void crearZ(){
        forma = new char[][]{
                {'1', '1', '0'},
                {'0', '1', '1'},
        };
        estados.put("Z1",forma);
        repr = '1';
    }
    /**
     * Metodo que crea la L
     */
    private void crearL(){
        forma = new char[][]{
                {'2', '0'},
                {'2', '0'},
                {'2', '2'}};
        estados.put("L1",forma);
        repr = '2';
    }
    /**
     * Metodo que crea la C
     */
    private void crearC(){
        forma = new char[][]{
                {'3', '3'},
                {'3', '3'}};
        estados.put("C1",forma);
        repr = '3';
    }
    /**
     * Metodo que crea la T
     */
    private void crearT(){
        forma = new char[][]{
                {'0', '4', '0'},
                {'4', '4', '4'},
        };
        estados.put("T1",forma);
        repr = '4';
    }
    /**
     * Metodo que crea la I
     */
    private void crearI(){
        forma = new char[][]{
                {'5'},
                {'5'},
                {'5'},
                {'5'}};
        estados.put("I1",forma);
        repr = '5';
    }

    /**
     * Metodo para rotar una ficha
     */
    public void rotar(){
        if(tipo == 'Z'){
            rotarZ();
        }
        else if(tipo == 'L'){
            rotarL();
        }
        else if (tipo == 'C')forma = estados.get("C1");
        else if (tipo == 'T'){
            rotarT();
        }
        else if(tipo == 'I'){
            rotarI();
        }
    }

    /**
     * Metodo que rota la Z
     */
    public void rotarZ(){
        if(Arrays.deepEquals(forma, estados.get("Z1"))){
            forma = new char[][]{
                    {'0', '1'},
                    {'1', '1'},
                    {'1', '0'}};
            estados.put("Z2", forma);
        }else forma = estados.get("Z1");
    }

    /**
     * Metodo que rota la L
     */
    public void rotarL(){
        if(Arrays.deepEquals(forma, estados.get("L1"))){
            forma = new char[][]{
                    {'2','2','2'},
                    {'2','0','0'}};
            estados.put("L2", forma);
        }else if(Arrays.deepEquals(forma, estados.get("L2"))){
            forma = new char[][]{
                    {'2', '2'},
                    {'0', '2'},
                    {'0', '2'}};
            estados.put("L3",forma);
        }else if(Arrays.deepEquals(forma, estados.get("L3"))){
            forma = new char[][]{
                    {'0','0','2'},
                    {'2','2','2'}};
            estados.put("L4", forma);
        }else forma = estados.get("L1");
    }

    /**
     * Metodo que rota la forma T
     */
    public void rotarT(){
        if(Arrays.deepEquals(forma, estados.get("T1"))){
            forma = new char[][]{
                    {'4', '0'},
                    {'4', '4'},
                    {'4', '0'}};
            estados.put("T2", forma);
        }else if(Arrays.deepEquals(forma, estados.get("T2"))){
            forma = new char[][]{
                    {'4','4','4'},
                    {'0','4','0'}};
            estados.put("T3",forma);
        }else if(Arrays.deepEquals(forma, estados.get("T3"))){
            forma = new char[][]{
                    {'0', '4'},
                    {'4', '4'},
                    {'0', '4'}};
            estados.put("T4", forma);
        }else forma = estados.get("T1");
    }

    /**
     * Metodo que rota la I
     */
    public void rotarI(){
        if(Arrays.deepEquals(forma, estados.get("I1"))){
            forma = new char[][]{
                    {'5','5','5','5'}};
            estados.put("I2", forma);
        }else forma = estados.get("I1");
    }

    /**
     * Metodo que retorna la matriz de la forma
     * @return matriz de la forma
     */
    public char[][] getForma() {
        return forma;
    }

    /**
     *Metodo que activa o desactiva una ficha
     * @param activo true si se quiere activar la ficha
     */
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    /**
     * metodo que da la representacion de la figura en la matriz
     * @return representacion de la figura en la matriz
     */
    public char getRepr() {
        return repr;
    }

    /**
     * Metodo que da el ancho de la matriz de la figura
     * @return que da el ancho de la matriz de la figura
     */
    public int getAncho() {
        return forma[0].length;
    }

    /**
     * Metodo que da el alto de la matriz de la figura
     * @return que da el alto de la matriz de la figura
     */
    public int getAlto() {
        return forma.length;
    }

    /**
     * Metodo que retorna el tipo de Tetromino
     * @return tipo de Tetromino
     */
    public char getTipo() {
        return this.tipo;
    }

    /**
     * Metodo que da los estados de las figuras
     * @return diccionario de los posibles estados
     */
    public HashMap<String, char[][]> getEstados() {
        return estados;
    }
}