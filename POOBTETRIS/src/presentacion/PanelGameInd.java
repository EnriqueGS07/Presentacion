package presentacion;

import dominio.*;

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

public class PanelGameInd{

    /* GUI */
    private TetrisGUI gui;

    /* PANELGAME */
    private PanelGame panelGame;

    /* MODO JUEGO INDIVIDUAL */
    private JPanel menupanelGameInd;
    private JButton botonPausa;
    private JButton botonConfig;

    /* DATOS GAME */
    private JLabel name;
    private JLabel textScore;
    private JLabel textTime;
    private JLabel textLevel;

    /* TABLERO */
    private Tablero tablero;

    /* MOVIMIENTO */
    private AccionInd game;

    /**
     * Constructor Clase PanelGameInd
     * @param gui Gui
     * @param panelGame PanelGame
     */
    public PanelGameInd(TetrisGUI gui, PanelGame panelGame) {
        this.panelGame = panelGame;
        this.gui = gui;
        prepareElementos();
        prepareAcciones();
        prepareStartGame();
        accionStartGame();
    }

    /**
     * Constructor Clase PanelGameInd
     * @param gui Gui
     * @param panelGame PanelGame
     * @param datos Datos a cargar
     */
    public PanelGameInd(TetrisGUI gui, PanelGame panelGame, ArrayList<String> datos) {
        this.panelGame = panelGame;
        this.gui = gui;
        prepareElementos();
        prepareAcciones();
        prepareStartGame();
        loadDatosGame(datos);
        accionStartGame();
    }

    /**
     * Metodo que prepara elementos para iniciar movimiento
     */
    public void prepareStartGame() {
        game = new AccionInd(this,tablero);
        game.setBuffos(gui.getCantidadBuffos());
        if(!gui.isMoodSpeed()) game.setVelTime(gui.getVelInicial());
    }

    /**
     * Metodo para iniciar el movimiento
     */
    public void accionStartGame() {
        game.start();
        if (gui.isMoodSpeed()) game.aumentarTime();
    }

    /**
     * Metodo para cargar el modo de juego en un arhivo txt
     */
    public void loadDatosGame(ArrayList<String> lines) {
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
                    this.tablero.getTetris().setBuffoEscogido(splittedLine[1].charAt(0));
                }
                if (splittedLine[0].equals("buffoActivo")) {
                    this.tablero.getTetris().agregarBuffoEspecifico(splittedLine[1].charAt(0),Integer.parseInt(splittedLine[2]),Integer.parseInt(splittedLine[3]));
                }
            }
            if (cont == 22) game.setTime(Integer.parseInt(splittedLine[1]));
            if (cont == 23) {
                game.setLevel(Integer.parseInt(splittedLine[1]));
                updateLevel(Integer.parseInt(splittedLine[1]));
            }
            if (cont == 24) game.setScore(Integer.parseInt(splittedLine[1]));
            if (cont == 25) setName(splittedLine[1]);
            if (cont == 26) setColor(splittedLine[1]);
            if (cont == 27) game.setBuffos(Integer.parseInt(splittedLine[1]));
            if (cont == 28) {
                game.setVelTime(Integer.parseInt(splittedLine[1]));
                game.setContTime(Integer.parseInt(splittedLine[2]));
            }
            if (cont == 28) {
                game.setVelTime(Integer.parseInt(splittedLine[1]));
                game.setContTime(Integer.parseInt(splittedLine[2]));
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
        this.tablero.getTetris().setTablero(tableroT);
        this.tablero.agregarFichaEspecifica(fichaActiva.charAt(0),fichaActivaT,posXact,posYact);
    }

    /**
     * Prepara los elementos del Modo Individual
     */
    public void prepareElementos() {
        //PANEL JUEGO INDIVIDUAL
        menupanelGameInd = new JPanel();
        menupanelGameInd.setLayout(null);
        gui.getMainPanel().add(menupanelGameInd, "menuModoInd");
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
        menupanelGameInd.add(botonConfig);
        //PAUSA
        botonPausa.setIcon(new ImageIcon("./images/pause.png"));
        botonPausa.setBounds(10, 10, 72, 65);
        botonPausa.setFocusPainted(false);
        botonPausa.setBorderPainted(false);
        botonPausa.setContentAreaFilled(false);
        botonPausa.setHorizontalTextPosition(JButton.CENTER);
        botonPausa.setVerticalTextPosition(JButton.BOTTOM);
        menupanelGameInd.add(botonPausa);
        //Nombre
        name= new JLabel();
        name.setText(gui.dataNombres.get(0).getText());
        name.setFont(gui.customFont(40));
        name.setBounds(80, 102, 100, 50);
        name.setForeground(Color.white);
        menupanelGameInd.add(name);
        //Time
        textTime = new JLabel();
        textTime.setText(0 + "");
        textTime.setFont(gui.customFont(40));
        textTime.setBounds(75, 200, 100, 50);
        textTime.setForeground(Color.white);
        menupanelGameInd.add(textTime);
        //Level
        textLevel = new JLabel();
        textLevel.setText(1 + "");
        textLevel.setFont(gui.customFont(40));
        textLevel.setBounds(75, 295, 100, 50);
        textLevel.setForeground(Color.white);
        menupanelGameInd.add(textLevel);
        //Score
        textScore = new JLabel();
        textScore.setText(0 + "");
        textScore.setFont(gui.customFont(40));
        textScore.setBounds(75, 392, 100, 50);
        textScore.setForeground(Color.white);
        menupanelGameInd.add(textScore);
        //TABLERO
        tablero = new Tablero(panelGame);
        tablero.setBounds(225, 58, 240, 410);
        tablero.agregarFicha();
        menupanelGameInd.add(tablero);
        //COLOR
        menupanelGameInd.setBackground(gui.dataColores.get(0));
        //FONDO MENU
        ImageIcon imagenFondo = new ImageIcon("./images/fondomodind1.png");
        JLabel fondo = new JLabel();
        fondo.setBounds(0, 0, panelGame.WIDTH, panelGame.HEIGHT);
        fondo.setIcon(imagenFondo);
        menupanelGameInd.add(fondo);
    }

    /**
     * Prepara las Acciones modo de juego Individual
     */
    public void prepareAcciones() {
        MouseListener mouseListenerGameInd = new MouseListener() {
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
        botonPausa.addMouseListener(mouseListenerGameInd);
        botonConfig.addMouseListener(mouseListenerGameInd);
    }

    /**
     * Metodo para para actualizar el Score
     */
    public void updateScore(int score) {
        textScore.setText(score + "");
    }

    /**
     * Metodo para para actualizar el Score
     */
    public void updateTime(int time) {
        textTime.setText(time + "");
    }

    /**
     * Metodo para actualizar velocidad
     * @param level nivel del juego
     */
    public void updateLevel(int level) {
        textLevel.setText(level + "");
    }

    /**
     * Metodo para conocer nombre el jugador
     * @return nombre del jugador
     */
    public String getName() {
        return name.getText();
    }

    /**
     * Metodo para cambiar nombre del jugador
     * @param name nombre
     */
    public void setName(String name) {
        this.name.setText(name);
    }

    /**
     * Metodo que retorna los codigos rgb del Color de la partida
     * @return String que representa color
     */
    public String getColor() {
        String color = "";
        color += menupanelGameInd.getBackground().getRed();
        color += ",";
        color += menupanelGameInd.getBackground().getGreen();
        color += ",";
        color += menupanelGameInd.getBackground().getBlue();
        color += ",";
        color += menupanelGameInd.getBackground().getAlpha();
        return color;

    }

    /**
     * Metodo que cambia el color de la partida
     * @param strColor Color
     */
    public void setColor(String strColor) {
        String[] c = strColor.split(",");
        menupanelGameInd.setBackground(new Color(Integer.parseInt(c[0]),Integer.parseInt(c[1]),Integer.parseInt(c[2]),Integer.parseInt(c[3])));
        panelGame.setChangeColor1(new Color(Integer.parseInt(c[0]),Integer.parseInt(c[1]),Integer.parseInt(c[2]),Integer.parseInt(c[3])));
    }

    /**
     * Metodo que retrna el Panel Game
     * @return Panel Game
     */
    public PanelGame getPanelGame() {
        return this.panelGame;
    }

    /**
     * Metodo para guardar un archivo txt
     * @param file Archivo donde se va guardar
     * @throws TetrisException Excepciones
     */
    public void guardar(File file) throws TetrisException{
        try{
            PrintWriter pw = new PrintWriter(new FileOutputStream(file.getAbsolutePath()));
            pw.print("modo " + gui.getModo() + '\n');
            tablero.getTetris().writeTetris(pw);
            tablero.getTetris().writeBuffo(pw);
            game.escribirDatosPartida(pw);
            tablero.getTetris().writeTetromino(pw);
            pw.close();
        }catch (IOException e){
            throw new TetrisException(TetrisException.ENTRADA_SALIDA);
        }catch (Exception e) {
            throw new TetrisException(TetrisException.GUARDAR);
        }
    }

    /**
     * Metodo que retorn el Tiempo del Jugador
     * @return Time
     */
    public String getTime() {
        return textTime.getText();
    }

    /**
     * Metodo que retorna el Score del Jugador
     * @return Score
     */
    public String getScore() {
        return textScore.getText();
    }

    /**
     * Metodo que retorna el level del Jugador
     * @return Level
     */
    public String getLevel() {
        return textLevel.getText();
    }

    /**
     * Metodo que guarda los datos de la partida
     */
    public void guardarPartida() {
        gui.anadirPartida(getName(),Integer.parseInt(getTime()),Integer.parseInt(getScore()),Integer.parseInt(getLevel()));
    }

    /**
     * Metodo para para el juego cuando se termina
     */
    public void stopGame() {
        game.stopGame(true);
    }

    /**
     * Metodo que retorna la clase de movimiento de Accion Ind
     * @return AccionInd
     */
    public AccionInd getGameInd() {
        return game;
    }

    /**
     * Metodo que retorna el MenuPanelGameInd
     * @return MenuPanelGameInd
     */
    public JPanel getMenupanelGameInd() {
        return menupanelGameInd;
    }

    /**
     * Metodo que retorna El tablero del juego
     * @return Tablero
     */
    public Tablero getTablero(){
        return tablero;
    }


}


