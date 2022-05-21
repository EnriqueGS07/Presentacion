package dominio.Test;

import dominio.Buffos.Buffo;
import dominio.TiposTetris.*;
import dominio.Tetris;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;


public class TetrisTest {
    Tetris tetris;
    Forma forma;
    char tipo;

    @BeforeEach
    void setUp() {
        tetris = new Tetris();
        int pos = (int) (Math.random() * 5) ;
        tipo = Tetris.FORMAS[pos];
        forma = new Forma(tipo);
    }

    @Test
    void deberiaAgregar(){
        tetris.agregarForma();
        char[][] tablero = tetris.getTablero();
        char repr = tetris.getFormaActiva().getRepr();
        if (repr != '4'){
            assertEquals(tablero[0][4], repr);
        }else assertEquals(tablero[0][5],repr);

    }

    @Test
    void deberiaBajar(){
        tetris.agregarForma();
        Forma formaBe = tetris.getFormaActiva();
        tetris.bajar();
        Forma formaAf = tetris.getFormaActiva();
        assertNotNull(formaBe);
        assertNull(formaAf);
    }

    @Test
    void deberiaIrDerecha(){
        tetris.agregarForma();
        tetris.derecha();
        char[][] tablero = tetris.getTablero();
        char repr = tetris.getFormaActiva().getRepr();
        if (repr != '4'){
            assertEquals(tablero[0][5], repr);
            assertEquals(tablero[0][4],'0');
        }else {
            assertEquals(tablero[0][6],repr);
            assertEquals(tablero[0][5],'0');
        }
    }

    @Test
    void deberiaIrIzqueirda(){
        tetris.agregarForma();
        tetris.izquierda();
        char[][] tablero = tetris.getTablero();
        char repr = tetris.getFormaActiva().getRepr();
        if (repr != '4' && repr != '3'&& repr != '1'){
            assertEquals(tablero[0][3], repr);
            assertEquals(tablero[0][4],'0');
        }else if(repr == '4') {
            assertEquals(tablero[0][4],repr);
            assertEquals(tablero[0][5],'0');
        }else {
            assertEquals(tablero[0][3], repr);
            assertEquals(tablero[0][4], repr);
            assertEquals(tablero[0][5],'0');
        }
    }

    @Test
    void deberiaRotar(){
        char[][] matriz1 = forma.getForma();
        char[][] matriz2 = forma.getForma();
        if(tipo != 'C'){
            HashMap<String,char[][]> estados = forma.getEstados();
            assertEquals(matriz1, estados.get(tipo + "1"));
            assertEquals(matriz2, estados.get(tipo + "2"));
        }
    }

    @Test
    void deberiaBorrar(){
        boolean borro = true;
        for(int i=0;i<10;i++){
            tetris.setTablero(19,i,'I');
        }
        tetris.limpiarTablero();
        for (int i=0;i<19;i++){
            for (int j=0;j<10;j++){
                if (tetris.getTablero()[i][j] == 'I'){
                    borro = false;
                }
            }
        }
        assertTrue(borro);
    }
    @Test
    void deberiaPerder(){
        for (int i=0;i<10;i++) {
            tetris.agregarForma();
        }
        assertTrue(tetris.isGameOver());
    }

    @Test
    void deberiaExplotar(){
        char[][]tablero = tetris.getTablero();
        tetris.setTablero(19,5,'I');
        tetris.setTablero(19,4,'I');
        tetris.setTablero(19,6,'I');
        tetris.agregarFormaEspecifica('L',3);
        tetris.bajar();
        for (int i=0;i<19;i++){
            for (int j=0;j<10;j++){
                assertEquals(tablero[i][j],'0');
            }
        }
    }

    @Test
    void deberiaSerUseless(){
        char[][]tablero = tetris.getTablero();
        tetris.agregarFormaEspecifica('L',1);
        tetris.bajar();
        tetris.agregarFormaEspecifica('I',1);
        tetris.bajar();
        tetris.agregarFormaEspecifica('T',1);
        tetris.bajar();
        tetris.agregarFormaEspecifica('Z',1);
        tetris.bajar();
        tetris.agregarFormaEspecifica('C',1);
        tetris.bajar();
        boolean esUseless = true;
        for (int i=0;i<19;i++){
            for (int j=0;j<10;j++){
                if (tablero[i][j] != 'g' && tablero[i][j] != 'h' && tablero[i][j] != 'j' && tablero[i][j] != 'k' && tablero[i][j] != 'l' && tablero[i][j] != '0') {
                    esUseless = false;
                }
            }
        }
        assertTrue(esUseless);
    }

    @Test
    void deberiaCogerBuffo(){
        tetris.agregarBuffoEspecifico('T',4,0);
        Buffo buffA = tetris.getBuffoActivo();
        assertNotNull(buffA);
        tetris.desactivarBuffo(new int[]{0, 4});
        Buffo buffB = tetris.getBuffoEscogido();
        assertEquals(buffB,buffA);
    }

    @Test
    void deberiaUsarBuffo(){
        tetris.agregarBuffoEspecifico('T',4,0);
        Buffo buffA = tetris.getBuffoActivo();
        assertNotNull(buffA);
        tetris.desactivarBuffo(new int[]{4, 0});
        Buffo buffB = tetris.getBuffoEscogido();
        assertNull(buffB);
        tetris.usarBuffo();
        Buffo buffC = tetris.getBuffoEscogido();
        assertNull(buffC);
    }
}