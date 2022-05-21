package presentacion;

import dominio.Tablero;
import dominio.Tetris;
import dominio.TetrisException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Objects;

public class PanelGameMaq{

    /* PANEL GAME/ GUI */
    private PanelGame panelGame;
    private TetrisGUI gui;

    /* MODO JUEGO MULTIJUGADOR */
    private JPanel menupanelGameMaq;
    private JButton botonPausa;
    private JButton botonConfig;

    /* DATOS GAME */
    private JLabel name1;
    private JLabel name2;
    private JLabel textScore1;
    private JLabel textScore2;
    private JLabel textTime1;
    private JLabel textTime2;
    private JLabel textLevel;
    public JPanel panel1;
    public JPanel panel2;

    /* TABLEROS */
    private Tablero tablero1;
    private Tablero tablero2;

    /* GAME */
    private AccionMaq game1;
    private AccionMaq game2;

    /* FICHAS */
    private ArrayList<Character> fichas;
    private ArrayList<Integer> tipos;

    /**
     * Constructor de la Clase Panel Game
     * @param gui Gui
     * @param panelGame PanelGame
     */
    public PanelGameMaq(TetrisGUI gui, PanelGame panelGame) {
        this.panelGame = panelGame;
        this.gui = gui;
        prepareElementos();
        prepareAcciones();
        prepareStartGame();
        accionStartGame();
    }

    /**
     * Constructor de la Clase Panel Game
     * @param gui Gui
     * @param panelGame PanelGame
     * @param datos Datos a cargar
     */
    public PanelGameMaq(TetrisGUI gui, PanelGame panelGame, ArrayList<String> datos) {
        this.panelGame = panelGame;
        this.gui = gui;
        prepareElementos();
        prepareAcciones();
        prepareStartGame();
        loadDatosGameJugador1(datos);
        loadDatosGameJugador2(datos);
        accionStartGame();
    }

    /**
     * Metodo para cargar el modo de juego en un arhivo txt
     */
    public void loadDatosGameJugador1(ArrayList<String> lines) {
        char[][] tableroT = new char[20][10];
        String fichaActiva = "";
        int fichaActivaT = 0;
        int posYact = 0;
        int posXact = 0;
        int cont = 0;
        for (String line: lines) {
            String[] splittedLine;
            splittedLine = line.trim().split(" ");
            if(cont >= 1 && cont <= 20) {
                for(int i=0; i<10; i++) {
                    tableroT[cont-1][i] =  splittedLine[i].charAt(0);
                }
            }
            if (cont == 21) {
                if (splittedLine[0].equals("buffoEscogido")) {
                    this.tablero1.getTetris().setBuffoEscogido(splittedLine[1].charAt(0));
                }
                if (splittedLine[0].equals("buffoActivo")) {
                    this.tablero1.getTetris().agregarBuffoEspecifico(splittedLine[1].charAt(0),Integer.parseInt(splittedLine[2]),Integer.parseInt(splittedLine[3]));
                }
            }
            if (cont == 22) game1.setTime(Integer.parseInt(splittedLine[1]));
            if (cont == 23) {
                game1.setLevel(Integer.parseInt(splittedLine[1]));
                updateLevel(Integer.parseInt(splittedLine[1]));
            }
            if (cont == 24) game1.setScore(Integer.parseInt(splittedLine[1]));
            if (cont == 25) setName1(splittedLine[1]);
            if (cont == 26) setColor1(splittedLine[1]);
            if (cont == 27) game1.setBuffos(Integer.parseInt(splittedLine[1]));
            if (cont == 28) {
                game1.setVelTime(Integer.parseInt(splittedLine[1]));
                game1.setContTime(Integer.parseInt(splittedLine[2]));
            }
            if (cont == 28) {
                game1.setVelTime(Integer.parseInt(splittedLine[1]));
                game1.setContTime(Integer.parseInt(splittedLine[2]));
            }
            if (cont == 29) {
                if (Objects.equals(splittedLine[1], "true")) panelGame.setMoodSpeed(true);
                if (Objects.equals(splittedLine[1], "false")) panelGame.setMoodSpeed(false);
            }
            if(cont == 30){
                fichaActiva = splittedLine[1];
                fichaActivaT = Integer.parseInt(splittedLine[2]);
                posXact = Integer.parseInt(splittedLine[3]);
                posYact = Integer.parseInt(splittedLine[4]);
            }
            cont ++;
        }
        this.tablero1.getTetris().setTablero(tableroT);
        this.tablero1.agregarFichaEspecifica(fichaActiva.charAt(0),fichaActivaT,posXact,posYact);
    }

    /**
     * Metodo para cargar el modo de juego en un arhivo txt
     */
    public void loadDatosGameJugador2(ArrayList<String> lines) {
        char[][] tableroT = new char[20][10];
        String fichaActiva = "";
        int fichaActivaT = 0;
        int posYact = 0;
        int posXact = 0;
        int cont = 0;
        for (String line: lines) {
            String[] splittedLine;
            splittedLine = line.trim().split(" ");
            if(cont >= 31 && cont <= 50) {
                for(int i=0; i<10; i++) {
                    tableroT[cont-31][i] =  splittedLine[i].charAt(0);
                }
            }
            if (cont == 51) {
                if (splittedLine[0].equals("buffoEscogido")) {
                    this.tablero2.getTetris().setBuffoEscogido(splittedLine[1].charAt(0));
                }
                if (splittedLine[0].equals("buffoActivo")) {
                    this.tablero2.getTetris().agregarBuffoEspecifico(splittedLine[1].charAt(0),Integer.parseInt(splittedLine[2]),Integer.parseInt(splittedLine[3]));
                }
            }
            if (cont == 52) game2.setTime(Integer.parseInt(splittedLine[1]));
            if (cont == 53) {
                game2.setLevel(Integer.parseInt(splittedLine[1]));
                updateLevel(Integer.parseInt(splittedLine[1]));
            }
            if (cont == 54) game2.setScore(Integer.parseInt(splittedLine[1]));
            if (cont == 55) setName2(splittedLine[1]);
            if (cont == 56) setColor2(splittedLine[1]);
            if (cont == 57) game2.setBuffos(Integer.parseInt(splittedLine[1]));
            if (cont == 58) {
                game2.setVelTime(Integer.parseInt(splittedLine[1]));
                game2.setContTime(Integer.parseInt(splittedLine[2]));
            }
            if (cont == 59) {
                if (Objects.equals(splittedLine[1], "true")) panelGame.setMoodSpeed(true);
                if (Objects.equals(splittedLine[1], "false")) panelGame.setMoodSpeed(false);
            }
            if(cont == 60){
                fichaActiva = splittedLine[1];
                fichaActivaT = Integer.parseInt(splittedLine[2]);
                posXact = Integer.parseInt(splittedLine[3]);
                posYact = Integer.parseInt(splittedLine[4]);
            }
            cont ++;
        }
        this.tablero2.getTetris().setTablero(tableroT);
        this.tablero2.agregarFichaEspecifica(fichaActiva.charAt(0),fichaActivaT,posXact,posYact);
    }

    /**
     * Metodo que prepara elementos para iniciar movimiento
     */
    public void prepareStartGame() {
        this.game1 = new AccionMaq(this,tablero1,1);
        this.game2 = new AccionMaq(this,tablero2,2);
        game1.setBuffos(gui.getCantidadBuffos());
        game2.setBuffos(gui.getCantidadBuffos());
        if(!gui.isMoodSpeed()) game1.setVelTime(gui.getVelInicial());
        if(!gui.isMoodSpeed()) game2.setVelTime(gui.getVelInicial());
    }

    /**
     * Metodo para iniciar el movimiento
     */
    public void accionStartGame() {
        game1.start();
        game2.start();
        if (gui.isMoodSpeed()) {
            game1.aumentarTime();
            game2.aumentarTime();
        }
    }

    /**
     * Prepara los elementos de Modo de juego ContraMaquina
     */
    public void prepareElementos() {
        //PANEL JUEGO CONTRA MAQUINA
        menupanelGameMaq = new JPanel();
        menupanelGameMaq.setLayout(null);
        gui.getMainPanel().add(menupanelGameMaq, "menuModoMaq");
        //BOTONES
        botonPausa = new JButton();
        botonConfig = new JButton();
        //CONFIGURACION
        botonConfig.setIcon(new ImageIcon("./images/configuracion.png"));
        botonConfig.setBounds(500, 10, 72, 65);
        botonConfig.setFocusPainted(false);
        botonConfig.setBorderPainted(false);
        botonConfig.setContentAreaFilled(false);
        botonConfig.setHorizontalTextPosition(JButton.CENTER);
        botonConfig.setVerticalTextPosition(JButton.BOTTOM);
        menupanelGameMaq.add(botonConfig);
        //PAUSA
        botonPausa.setIcon(new ImageIcon("./images/pause.png"));
        botonPausa.setBounds(10, 10, 72, 65);
        botonPausa.setFocusPainted(false);
        botonPausa.setBorderPainted(false);
        botonPausa.setContentAreaFilled(false);
        botonPausa.setHorizontalTextPosition(JButton.CENTER);
        botonPausa.setVerticalTextPosition(JButton.BOTTOM);
        menupanelGameMaq.add(botonPausa);
        //NOMBRE 1
        name1 = new JLabel();
        name1.setText(gui.dataNombres.get(1).getText());
        name1.setFont(gui.customFont(40));
        name1.setBounds(80, 70, 100, 50);
        name1.setForeground(Color.white);
        name1.setHorizontalTextPosition(SwingConstants.CENTER);
        name1.setVerticalTextPosition(SwingConstants.CENTER);
        menupanelGameMaq.add(name1);
        //NOMBRE 2
        name2 = new JLabel();
        name2.setText(gui.dataNombres.get(2).getText());
        name2.setFont(gui.customFont(40));
        name2.setBounds(455, 70, 100, 50);
        name2.setForeground(Color.white);
        name2.setHorizontalTextPosition(SwingConstants.CENTER);
        name2.setVerticalTextPosition(SwingConstants.CENTER);
        menupanelGameMaq.add(name2);
        //Level
        textLevel= new JLabel();
        textLevel.setText(1 + "");
        textLevel.setFont(gui.customFont(40));
        textLevel.setBounds(296, 140, 100, 50);
        textLevel.setForeground(Color.white);
        menupanelGameMaq.add(textLevel);
        //Time 1
        textTime1 = new JLabel();
        textTime1.setText(0 + "");
        textTime1.setFont(gui.customFont(40));
        textTime1.setBounds(296, 212, 100, 50);
        textTime1.setForeground(Color.white);
        menupanelGameMaq.add(textTime1);
        //Score 1
        textScore1 = new JLabel();
        textScore1.setText(0 + "");
        textScore1.setFont(gui.customFont(40));
        textScore1.setBounds(296, 274, 100, 50);
        textScore1.setForeground(Color.white);
        menupanelGameMaq.add(textScore1);
        //Time 2
        textTime2 = new JLabel();
        textTime2.setText(0 + "");
        textTime2.setFont(gui.customFont(40));
        textTime2.setBounds(296, 340, 100, 50);
        textTime2.setForeground(Color.white);
        menupanelGameMaq.add(textTime2);
        //Score 2
        textScore2 = new JLabel();
        textScore2.setText(0 + "");
        textScore2.setFont(gui.customFont(40));
        textScore2.setBounds(296, 410, 100, 50);
        textScore2.setForeground(Color.white);
        menupanelGameMaq.add(textScore2);
        //TABLERO
        int pos = (int) (Math.random() * 5);
        char tipo = Tetris.FORMAS[pos];
        int tipoT = (int) (Math.random() * 4);
        tablero1 = new Tablero(panelGame);
        tablero1.setBounds(18, 118, 215, 349);
        tablero1.agregarFichaEspecifica(tipo,tipoT);
        menupanelGameMaq.add(tablero1);
        tablero2 = new Tablero(panelGame);
        tablero2.setBounds(370, 118, 215, 349);
        tablero2.agregarFichaEspecifica(tipo,tipoT);
        menupanelGameMaq.add(tablero2);
        //FONDO MENU
        ImageIcon imagenFondo = new ImageIcon("./images/fondomodmul1.png");
        JLabel fondo = new JLabel();
        fondo.setBounds(0, 0, panelGame.WIDTH, panelGame.HEIGHT);
        fondo.setIcon(imagenFondo);
        menupanelGameMaq.add(fondo);
        //FONDOS
        panel1 = new JPanel();
        panel1.setBackground(gui.dataColores.get(1));
        panel1.setBounds(0,0,panelGame.WIDTH/2,panelGame.HEIGHT);
        panel2 = new JPanel();
        panel2.setBackground(gui.dataColores.get(2));
        panel2.setBounds(panelGame.WIDTH/2,0,panelGame.WIDTH/2,panelGame.HEIGHT);
        menupanelGameMaq.add(panel1);
        menupanelGameMaq.add(panel2);
        //FICHAS
        fichas = new ArrayList<>();
        for(int i=0;i<100;i++){
            pos = (int) (Math.random() * 5);
            tipo = Tetris.FORMAS[pos];
            fichas.add(tipo);
        }
        tipos = new ArrayList<>();
        for(int i=0;i<100;i++){
            tipoT = (int) (Math.random() * 4);
            tipos.add(tipoT);
        }
    }

    /**
     * Prepara las Acciones del Modo ContraMaquina
     */
    public void prepareAcciones() {
        MouseListener mouseListenerGameMaq = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource().equals(botonPausa)) {
                    botonPausa.setIcon(new ImageIcon("./images/pause2.png"));
                } else if (e.getSource().equals(botonConfig)) {
                    botonConfig.setIcon(new ImageIcon("./images/configuracion2.png"));
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getSource().equals(botonPausa)) {
                    botonPausa.setIcon(new ImageIcon("./images/pause.png"));
                    panelGame.pausa();

                } else if (e.getSource().equals(botonConfig)) {
                    botonConfig.setIcon(new ImageIcon("./images/configuracion.png"));
                    panelGame.configuracionGame();
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getSource().equals(botonPausa)) {
                    botonPausa.setIcon(new ImageIcon("./images/pause.png"));
                } else if (e.getSource().equals(botonConfig)) {
                    botonConfig.setIcon(new ImageIcon("./images/configuracion.png"));
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if (e.getSource().equals(botonPausa)) {
                    botonPausa.setIcon(new ImageIcon("./images/pause2.png"));
                } else if (e.getSource().equals(botonConfig)) {
                    botonConfig.setIcon(new ImageIcon("./images/configuracion2.png"));
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (e.getSource().equals(botonPausa)) {
                    botonPausa.setIcon(new ImageIcon("./images/pause.png"));
                } else if (e.getSource().equals(botonConfig)) {
                    botonConfig.setIcon(new ImageIcon("./images/configuracion.png"));
                }
            }
        };
        botonPausa.addMouseListener(mouseListenerGameMaq);
        botonConfig.addMouseListener(mouseListenerGameMaq);
    }

    /**
     * Metodo para para actualizar el Score del jugador 1
     */
    public void updateScore1(int score) {
        textScore1.setText(score + "");
    }

    /**
     * Metodo para para actualizar el Score del jugador 2
     */
    public void updateScore2(int score) {
        textScore2.setText(score + "");
    }

    /**
     * Metodo para para actualizar el Time del jugador 1
     */
    public void updateTime1(int time) {
        textTime1.setText(time + "");
    }

    /**
     * Metodo para para actualizar el Time del jugadir 2
     */
    public void updateTime2(int time) {
        textTime2.setText(time + "");
    }

    /**
     * Metodo para actualizar el nivel del juego
     * @param level nivel del juego
     */
    public void updateLevel(int level) {
        textLevel.setText(level + "");
    }

    /**
     * Metodo para saber que ficha debe visualizarse en el tablero
     * @param pos Posicion de la lista de fichas
     * @return tipo de ficha
     */
    public char getFicha(int pos){
        if(pos == 100){
            pos = 0;
        }
        return fichas.get(pos);
    }

    /**
     * Metodo para saber que tipo de ficha debe visualizarse en el tablero
     * @param pos Posicion de la lista de fichas
     * @return clase de ficha
     */
    public int getTipoFicha(int pos){
        return tipos.get(pos);
    }

    /**
     * Metodo para conocer nombre el jugador
     * @return nombre del jugador
     */
    public String getName1() {
        return name1.getText();
    }

    /**
     * Metodo para conocer nombre el jugador
     * @return nombre del jugador
     */
    public String getName2() {
        return name2.getText();
    }

    /**
     * Metodo que retorna los codigos rgb del Color de la partida
     * @return String que representa color
     */
    public String getColor1() {
        String color = "";
        color += panel1.getBackground().getRed();
        color += ",";
        color += panel1.getBackground().getGreen();
        color += ",";
        color += panel1.getBackground().getBlue();
        color += ",";
        color += panel1.getBackground().getAlpha();
        return color;
    }

    /**
     * Metodo que retorna los codigos rgb del Color de la partida
     * @return String que representa color
     */
    public String getColor2() {
        String color = "";
        color += panel2.getBackground().getRed();
        color += ",";
        color += panel2.getBackground().getGreen();
        color += ",";
        color += panel2.getBackground().getBlue();
        color += ",";
        color += panel2.getBackground().getAlpha();
        return color;
    }

    /**
     * Metodo que retrna el Panel Game
     * @return Panel Game
     */
    public PanelGame getPanelGame() {
        return this.panelGame;
    }

    /**
     * Metodo para cambiar nombre del jugador
     * @param name nombre
     */
    public void setName1(String name) {
        this.name1.setText(name);
    }

    /**
     * Metodo para cambiar nombre del jugador
     * @param name nombre
     */
    public void setName2(String name) {
        this.name2.setText(name);
    }

    /**
     * Metodo que cambia el color de la partida
     * @param strColor Color
     */
    public void setColor1(String strColor) {
        String[] c = strColor.split(",");
        panel1.setBackground(new Color(Integer.parseInt(c[0]),Integer.parseInt(c[1]),Integer.parseInt(c[2]),Integer.parseInt(c[3])));
    }

    /**
     * Metodo que cambia el color de la partida
     * @param strColor Color
     */
    public void setColor2(String strColor) {
        String[] c = strColor.split(",");
        panel2.setBackground(new Color(Integer.parseInt(c[0]),Integer.parseInt(c[1]),Integer.parseInt(c[2]),Integer.parseInt(c[3])));
    }

    /**
     * Metodo para guardar un archivo txt
     * @param file Archivo en el que se guardara
     * @throws TetrisException Clase Exception
     */
    public void guardar(File file) throws TetrisException{
        try{
            PrintWriter pw = new PrintWriter(new FileOutputStream(file.getAbsolutePath()));
            pw.print("modo " + gui.getModo() + '\n');
            tablero1.getTetris().writeTetris(pw);
            tablero1.getTetris().writeBuffo(pw);
            game1.escribirDatosPartida(pw);
            tablero1.getTetris().writeTetromino(pw);
            tablero2.getTetris().writeTetris(pw);
            tablero2.getTetris().writeBuffo(pw);
            game2.escribirDatosPartida(pw);
            tablero2.getTetris().writeTetromino(pw);
            pw.close();
        }catch (IOException e){
            throw new TetrisException(TetrisException.ENTRADA_SALIDA);
        }catch (Exception e) {
            throw new TetrisException(TetrisException.GUARDAR);
        }
    }

    /**
     * Metodo para retornar Time del Jugador
     * @return Time
     */
    public String getTime1() {
        return textTime1.getText();
    }

    /**
     * Metodo para retornar Score del Jugador
     * @return Score
     */
    public String getScore1() {
        return textScore1.getText();
    }

    /**
     * Metodo para retornar Time de la Maquina
     * @return Time
     */
    public String getTime2() {
        return textTime2.getText();
    }

    /**
     * Metodo para retornar Score de la Maquina
     * @return Score
     */
    public String getScore2() {
        return textScore2.getText();
    }

    /**
     * Metodo para retornar Level de la partida
     * @return Score
     */
    public String getLevel() {
        return textLevel.getText();
    }

    /**
     * Metodo para guardar la partida
     */
    public void guardarPartida() {
        gui.anadirPartida(getName1(),Integer.parseInt(getTime1()),Integer.parseInt(getScore1()),Integer.parseInt(getLevel()));
        gui.anadirPartida(getName2(),Integer.parseInt(getTime2()),Integer.parseInt(getScore2()),Integer.parseInt(getLevel()));
    }

    /**
     * Metodo para parar el juego cuando se termina
     */
    public void stopGame() {
        game1.stopGame(true);
        game2.stopGame(true);
    }

    /**
     * Metodo que retorna la clase de movimiento de Accion Maq 1
     * @return AccionMaq1
     */
    public AccionMaq getGameMaq1() {
        return game1;
    }

    /**
     * Metodo que retorna la clase de movimiento de Accion Maq 2
     * @return AccionMaq2
     */
    public AccionMaq getGameMaq2() {
        return game2;
    }

    /**
     * Metodo que retorna El tablero 1 del juego
     * @return Tablero 1
     */
    public Tablero getTablero1(){
        return tablero1;
    }

    /**
     * Metodo que retorna El tablero 2 del juego
     * @return Tablero 2
     */
    public Tablero getTablero2(){
        return tablero2;
    }

    /**
     * Metodo que retorna El Panel 1 del juego
     * @return Panel 1
     */
    public JPanel getPanel1(){
        return panel1;
    }

    /**
     * Metodo que retorna El Panel 2 del juego
     * @return Panel 2
     */
    public JPanel getPanel2(){
        return panel2;
    }

}
