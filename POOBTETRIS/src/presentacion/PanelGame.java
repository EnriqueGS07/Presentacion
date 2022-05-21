package presentacion;

import dominio.*;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class PanelGame {

    /* GUI */
    private TetrisGUI gui;

    /* TAMAÑO JUEGO */
    public int WIDTH = 600;
    public int HEIGHT = 500;
    private Border borde = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2);

    /* MENU PAUSA */
    private JPanel menuPausa;
    private JLabel logopause;
    private JButton salirGame;
    private JButton regresarGame;
    private JButton reiniciarGame;
    private JButton cargarGame;
    private JButton guardarGame;
    private ArrayList<JButton> botonesPausa;

    /* MENU CONFIGURACION */
    private JPanel menuConfig;
    private JButton backConfig;
    private JButton changeColor1;
    private JButton changeColor2;
    private JButton Instruccion;
    private JButton controles;
    private JCheckBox musica;
    private ArrayList<JButton> botonesConfigGame;

    /* MENU GAME OVER */
    private JPanel menuGameOver;
    private JButton gameOverReiniciar;
    private JButton gameOverSalir;
    private JLabel name1;
    private JLabel name2;
    private JLabel time1;
    private JLabel time2;
    private JLabel score1;
    private JLabel score2;
    private JLabel level1;
    private JLabel level2;
    private JLabel tituloGameOver;
    private ArrayList<JButton> botonesGameOver;

    /* PANEL NEW RECORD */
    private JPanel nuevoRecord;
    private JLabel nombreRecord;
    private JLabel timeRecord;
    private JLabel scoreRecord;
    private JLabel levelRecord;
    private JButton salirRecord;

    /* MODO JUEGO */
    private PanelGameInd panelGameInd;
    private PanelGameMul panelGameMul;
    private PanelGameMaq panelGameMaq;

    /* TECLAS */
    private KeyListener movimientosTableroInd;
    private KeyListener movimientosTableroMul;
    private KeyListener movimientosTableroMaq;

    /* LOAD GAME */
    ArrayList<String> lines;

    /**
     * Constructor clase Panel Game
     * @param gui Gui
     */
    public PanelGame(TetrisGUI gui) {
        this.gui = gui;
        prepareElementos();
        prepareAcciones();
        startGame();
    }

    /**
     * Constructor clase Panel Game
     * @param gui Gui
     * @param file Archivo a cargar
     * @throws TetrisException Clase Excepciones
     */
    public PanelGame(TetrisGUI gui, File file) throws TetrisException {
        this.gui = gui;
        abrir(file);
        prepareElementos();
        prepareAcciones();
        startGameLoad();
    }

    /**
     * Metodo para abrir un archivo txt
     * @param file archivo a abrir
     * @throws TetrisException clase exception
     */
    public void abrir(File file) throws TetrisException{
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            lines = new ArrayList<>();
            String line = bufferedReader.readLine();
            while (line != null) {
                lines.add(line);
                line = bufferedReader.readLine();
            }
            loadDatosGame();
            bufferedReader.close();
        } catch(IOException e) {
            throw new TetrisException(TetrisException.ERROR_EN_PERSISTENCIA);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo para cargar el modo de juego en un arhivo txt
     */
    public void loadDatosGame() {
        String[] splittedLine = lines.get(0).trim().split(" ");
        gui.setModo(splittedLine[1]);
        gui.dataColores.clear();
        gui.dataNombres.clear();
        JTextField a = new JTextField("Player");
        for (int i=0; i<5; i++) {
            gui.dataColores.add(Color.BLACK);
            gui.dataNombres.add(a);
        }
    }

    /**
     * Metodo para iniciar elementos del nuevo juego
     */
    public void startGameLoad() {
        if (Objects.equals(gui.getModo(), "I")) {
            panelGameInd = new PanelGameInd(gui,this, lines);
            gui.getCl().show(gui.getMainPanel(),"menuModoInd");
        }
        if (Objects.equals(gui.getModo(), "M")) {
            panelGameMul = new PanelGameMul(gui,this, lines);
            gui.getCl().show(gui.getMainPanel(),"menuModoMul");
        }
        if (Objects.equals(gui.getModo(), "C")) {
            panelGameMaq = new PanelGameMaq(gui,this, lines);
            gui.getCl().show(gui.getMainPanel(),"menuModoMaq");
        }
    }

    /**
     * Metodo para iniciar el juego
     */
    public void startGame() {
        if (Objects.equals(gui.getModo(), "I")) panelGameInd = new PanelGameInd(gui,this);
        if (Objects.equals(gui.getModo(), "M")) panelGameMul = new PanelGameMul(gui,this);
        if (Objects.equals(gui.getModo(), "C")) panelGameMaq = new PanelGameMaq(gui,this);
    }

    /**
     * Prepara Elementos de la clase
     */
    public void prepareElementos() {
        botonesPausa = new ArrayList<>();
        botonesConfigGame = new ArrayList<>();
        botonesGameOver = new ArrayList<>();
        gui.setFocusable(true);
        prepareElementosPausa();
        prepareElementosConfigGame();
        prepareElementosGameOver();
        prepareElementosNuevoRecord();
    }

    /**
     * Prepara Elementos de Pausa
     */
    public void prepareElementosPausa() {
        //PANEL PAUSA
        menuPausa = new JPanel();
        menuPausa.setLayout(null);
        gui.getMainPanel().add(menuPausa, "menuPausa");
        //REGRESAR GAME
        regresarGame = new JButton("CONTINUAR");
        regresarGame.setBackground(Color.BLUE);
        regresarGame.setBounds(WIDTH / 2 - 75, 180, 150, 70);
        regresarGame.setForeground(Color.black);
        regresarGame.setBorder(borde);
        menuPausa.add(regresarGame);
        botonesPausa.add(regresarGame);
        //REINICIAR GAME
        reiniciarGame = new JButton("REINICIAR");
        reiniciarGame.setForeground(Color.black);
        reiniciarGame.setBackground(Color.GREEN);
        reiniciarGame.setBounds(WIDTH / 2 - 75, 260, 150, 70);
        reiniciarGame.setBorder(borde);
        menuPausa.add(reiniciarGame);
        botonesPausa.add(reiniciarGame);
        //SALIR GAME
        salirGame = new JButton("SALIR");
        salirGame.setForeground(Color.black);
        salirGame.setBackground(Color.RED);
        salirGame.setBounds(WIDTH / 2 - 75, 340, 150, 70);
        salirGame.setBorder(borde);
        menuPausa.add(salirGame);
        botonesPausa.add(salirGame);
        //LOAD GAME
        cargarGame = new JButton("Load");
        cargarGame.setIcon(new ImageIcon("./images/save.png"));
        cargarGame.setBounds(10,(HEIGHT+35)-120,90,65);
        cargarGame.setForeground(Color.white);
        cargarGame.setFocusPainted(false);
        cargarGame.setBorderPainted(false);
        cargarGame.setContentAreaFilled(false);
        cargarGame.setHorizontalTextPosition(JButton.CENTER);
        cargarGame.setVerticalTextPosition(JButton.BOTTOM);
        menuPausa.add(cargarGame);
        botonesPausa.add(cargarGame);
        //SAVE GAME
        guardarGame = new JButton("Save");
        guardarGame.setIcon(new ImageIcon("./images/load.png"));
        guardarGame.setBounds(WIDTH-100,(HEIGHT+35)-120,90,65);
        guardarGame.setForeground(Color.white);
        guardarGame.setFocusPainted(false);
        guardarGame.setBorderPainted(false);
        guardarGame.setContentAreaFilled(false);
        guardarGame.setHorizontalTextPosition(JButton.CENTER);
        guardarGame.setVerticalTextPosition(JButton.BOTTOM);
        menuPausa.add(guardarGame);
        botonesPausa.add(guardarGame);
        //TITULO
        logopause = new JLabel();
        logopause.setIcon(new ImageIcon("./images/logopause.png"));
        logopause.setBounds((WIDTH / 2) - 155, 30, 300, 140);
        logopause.setHorizontalAlignment(JLabel.CENTER);
        logopause.setVerticalAlignment(JLabel.CENTER);
        menuPausa.add(logopause);
        //FONDO MENU
        ImageIcon imagenFondo;
        if (gui.getModo() == "I") imagenFondo = new ImageIcon("./images/fondomodindpausa.jpg");
        else imagenFondo = new ImageIcon("./images/fondomodmulpausa.jpg");
        JLabel fondo = new JLabel();
        fondo.setBounds(0, 0, 600, 500);
        fondo.setIcon(imagenFondo);
        menuPausa.add(fondo);
        menuPausa.setBackground(Color.BLACK);
    }

    /**
     * Prepara Elementos de Game Over
     */
    public void prepareElementosGameOver() {
        //PANEL GAME OVER
        menuGameOver = new JPanel();
        menuGameOver.setLayout(null);
        gui.getMainPanel().add(menuGameOver, "menuGameOver");
        //TITULO
        tituloGameOver = new JLabel();
        tituloGameOver.setIcon(new ImageIcon("./images/gameOver.gif"));
        tituloGameOver.setBounds((WIDTH/2)-150,35,300,160);
        tituloGameOver.setHorizontalAlignment(JLabel.CENTER);
        tituloGameOver.setVerticalAlignment(JLabel.CENTER);
        menuGameOver.add(tituloGameOver);
        //NAME 1
        name1 = new JLabel();
        name1.setText("Player 1");
        name1.setFont(gui.customFont(40));
        name1.setBounds((WIDTH/2)-200,180,300,80);
        name1.setVerticalTextPosition(SwingConstants.CENTER);
        name1.setHorizontalTextPosition(SwingConstants.CENTER);
        name1.setForeground(Color.white);
        menuGameOver.add(name1);
        //NAME 2
        if (!Objects.equals(gui.getModo(), "I")) {
            name2 = new JLabel();
            name2.setText("Player 2");
            name2.setFont(gui.customFont(40));
            name2.setBounds((WIDTH/2)-200,210,300,80);
            name2.setVerticalTextPosition(SwingConstants.CENTER);
            name2.setHorizontalTextPosition(SwingConstants.CENTER);
            name2.setForeground(Color.white);
            menuGameOver.add(name2);
        }
        //TIME 1
        time1 = new JLabel();
        time1.setText("Time 1");
        time1.setFont(gui.customFont(30));
        time1.setBounds((WIDTH/2)-75,180,300,80);
        time1.setVerticalTextPosition(SwingConstants.CENTER);
        time1.setHorizontalTextPosition(SwingConstants.CENTER);
        time1.setForeground(Color.white);
        menuGameOver.add(time1);
        //TIME 2
        if (!Objects.equals(gui.getModo(), "I")) {
            time2 = new JLabel();
            time2.setText("Time 2");
            time2.setFont(gui.customFont(40));
            time2.setBounds((WIDTH/2)-75,210,300,80);
            time2.setVerticalTextPosition(SwingConstants.CENTER);
            time2.setHorizontalTextPosition(SwingConstants.CENTER);
            time2.setForeground(Color.white);
            menuGameOver.add(time2);
        }
        //SCORE 1
        score1 = new JLabel();
        score1.setText("Score 1");
        score1.setFont(gui.customFont(30));
        score1.setBounds((WIDTH/2)+15,180,300,80);
        score1.setVerticalTextPosition(SwingConstants.CENTER);
        score1.setHorizontalTextPosition(SwingConstants.CENTER);
        score1.setForeground(Color.white);
        menuGameOver.add(score1);
        //SCORE 2
        if (!Objects.equals(gui.getModo(), "I")) {
            score2 = new JLabel();
            score2.setText("Score 2");
            score2.setFont(gui.customFont(30));
            score2.setBounds((WIDTH/2)+15,210,300,80);
            score2.setVerticalTextPosition(SwingConstants.CENTER);
            score2.setHorizontalTextPosition(SwingConstants.CENTER);
            score2.setForeground(Color.white);
            menuGameOver.add(score2);
        }
        //LEVEL 1
        level1 = new JLabel();
        level1.setText("Level 1");
        level1.setFont(gui.customFont(30));
        level1.setBounds((WIDTH/2)+120,180,300,80);
        level1.setVerticalTextPosition(SwingConstants.CENTER);
        level1.setHorizontalTextPosition(SwingConstants.CENTER);
        level1.setForeground(Color.white);
        menuGameOver.add(level1);
        //LEVEL 2
        if (!Objects.equals(gui.getModo(), "I")) {
            level2 = new JLabel();
            level2.setText("Level 2");
            level2.setFont(gui.customFont(40));
            level2.setBounds((WIDTH/2)+120,210,300,80);
            level2.setVerticalTextPosition(SwingConstants.CENTER);
            level2.setHorizontalTextPosition(SwingConstants.CENTER);
            level2.setForeground(Color.white);
            menuGameOver.add(level2);
        }
        //REINICIAR
        gameOverReiniciar = new JButton(" REINICIAR ");
        gameOverReiniciar.setBackground(Color.red);
        gameOverReiniciar.setBounds((WIDTH+12)/2-220,300,160,70);
        gameOverReiniciar.setBorder(new RoundedBorder(25));
        gameOverReiniciar.setOpaque(false);
        gameOverReiniciar.setForeground(Color.green);
        gameOverReiniciar.setHorizontalTextPosition(SwingConstants.CENTER);
        gameOverReiniciar.setVerticalTextPosition(SwingConstants.CENTER);
        menuGameOver.add(gameOverReiniciar);
        botonesGameOver.add(gameOverReiniciar);
        //SALIR
        gameOverSalir = new JButton(" SALIR ");
        gameOverSalir.setBackground(Color.green);
        gameOverSalir.setBounds((WIDTH+12)/2+30,300,160,70);
        gameOverSalir.setBorder(new RoundedBorder(25));
        gameOverSalir.setOpaque(false);
        gameOverSalir.setForeground(Color.red);
        gameOverSalir.setHorizontalTextPosition(SwingConstants.CENTER);
        gameOverSalir.setVerticalTextPosition(SwingConstants.CENTER);
        menuGameOver.add(gameOverSalir);
        botonesGameOver.add(gameOverSalir);
        //FONDO MENU
        ImageIcon imagenFondo;
        if (Objects.equals(gui.getModo(), "I"))  imagenFondo = new ImageIcon("./images/fondodata1.gif");
        else imagenFondo = new ImageIcon("./images/fondodata2.gif");
        JLabel fondo = new JLabel();
        fondo.setBounds(0,0,WIDTH, HEIGHT);
        fondo.setIcon(imagenFondo);
        menuGameOver.add(fondo);
    }

    /**
     * Prepara Elementos de Configuracion
     */
    private void prepareElementosConfigGame(){
        //MENU CONFIGURACION
        menuConfig = new JPanel();
        menuConfig.setLayout(null);
        gui.getMainPanel().add(menuConfig, "menuConfigGame");
        //VOLVER
        backConfig = new JButton();
        backConfig.setIcon(new ImageIcon("./images/back.png"));
        backConfig.setBounds(20,10,100,40);
        backConfig.setFocusPainted(false);
        backConfig.setBorderPainted(false);
        backConfig.setContentAreaFilled(false);
        menuConfig.add(backConfig);
        botonesConfigGame.add(backConfig);
        //CAMBIAR COLOR FONDO
        changeColor1 = new JButton("Color Fondo");
        changeColor1.setBorder(borde);
        changeColor1.setBounds((WIDTH+12)/2-75,80,150,50);
        if (Objects.equals(gui.getModo(), "I")) changeColor1.setBackground(gui.dataColores.get(0));
        if (Objects.equals(gui.getModo(), "M")) changeColor1.setBackground(gui.dataColores.get(1));
        if (Objects.equals(gui.getModo(), "C")) changeColor1.setBackground(gui.dataColores.get(3));
        menuConfig.add(changeColor1);
        botonesConfigGame.add(changeColor1);
        if (Objects.equals(gui.getModo(), "M") || Objects.equals(gui.getModo(), "C")) {
            //Color 1
            changeColor1.setBounds(WIDTH / 2 - 180, 80, 150, 50);
            changeColor1.setText("Cambiar color del fondo 1");
            //Color2
            changeColor2 = new JButton("Cambiar color del fondo 2");
            changeColor2.setBorder(borde);
            changeColor2.setBounds(WIDTH / 2 + 30, 80, 150, 50);
            if (Objects.equals(gui.getModo(), "M")) changeColor2.setBackground(gui.dataColores.get(2));
            if (Objects.equals(gui.getModo(), "C")) changeColor2.setBackground(gui.dataColores.get(4));
            menuConfig.add(changeColor2);
            botonesConfigGame.add(changeColor2);
        }
        //CONTROLES
        controles = new JButton("Controles");
        controles.setBackground(Color.red);
        controles.setBorder(borde);
        controles.setBounds((WIDTH+12)/2-75,160,150,50);
        menuConfig.add(controles);
        botonesConfigGame.add(controles);
        //INSTRUCCIONES
        Instruccion = new JButton("Instrucciones");
        Instruccion.setBackground(Color.blue);
        Instruccion.setBorder(borde);
        Instruccion.setBounds((WIDTH+12)/2-75,240,150,50);
        menuConfig.add(Instruccion);
        botonesConfigGame.add(Instruccion);
        //MUSICA
        musica = new JCheckBox("Musica");
        musica.setBounds((WIDTH+12)/2-75,320,150,50);
        musica.setBackground(Color.green);
        musica.setBorder(borde);
        musica.setSelected(true);
        menuConfig.add(musica);
        //FONDO MENU
        ImageIcon imagenFondo = new ImageIcon("./images/fondomodmulpausa.jpg");
        JLabel fondo = new JLabel();
        fondo.setBounds(0,0,WIDTH, HEIGHT);
        fondo.setIcon(imagenFondo);
        menuConfig.add(fondo);
    }

    /**
     * Prepara Elementos de Nuevo Record
     */
    private void prepareElementosNuevoRecord() {
        //PANEL HISTORIAL
        nuevoRecord = new JPanel();
        nuevoRecord.setLayout(null);
        gui.getMainPanel().add(nuevoRecord,"MenuNuevoRecord");
        //VOLVER
        salirRecord = new JButton();
        salirRecord.setIcon(new ImageIcon("./images/salir.png"));
        salirRecord.setBounds(((WIDTH+12)/2)-50,((HEIGHT+35))-170,100,55);
        salirRecord.setFocusPainted(false);
        salirRecord.setBorderPainted(false);
        salirRecord.setContentAreaFilled(false);
        nuevoRecord.add(salirRecord);
        //NAME
        nombreRecord = new JLabel();
        nombreRecord.setText("Player 1");
        nombreRecord.setFont(gui.customFont(40));
        nombreRecord.setBounds((WIDTH/2)-200,200,300,80);
        nombreRecord.setVerticalTextPosition(SwingConstants.CENTER);
        nombreRecord.setHorizontalTextPosition(SwingConstants.CENTER);
        nombreRecord.setForeground(Color.white);
        nuevoRecord.add(nombreRecord);
        //TIME
        timeRecord = new JLabel();
        timeRecord.setText("Time 1");
        timeRecord.setFont(gui.customFont(30));
        timeRecord.setBounds((WIDTH/2)-75,200,300,80);
        timeRecord.setVerticalTextPosition(SwingConstants.CENTER);
        timeRecord.setHorizontalTextPosition(SwingConstants.CENTER);
        timeRecord.setForeground(Color.white);
        nuevoRecord.add(timeRecord);
        //SCORE
        scoreRecord = new JLabel();
        scoreRecord.setText("Score 1");
        scoreRecord.setFont(gui.customFont(30));
        scoreRecord.setBounds((WIDTH/2)+15,200,300,80);
        scoreRecord.setVerticalTextPosition(SwingConstants.CENTER);
        scoreRecord.setHorizontalTextPosition(SwingConstants.CENTER);
        scoreRecord.setForeground(Color.white);
        nuevoRecord.add(scoreRecord);
        //LEVEL
        levelRecord = new JLabel();
        levelRecord .setText("Level 1");
        levelRecord .setFont(gui.customFont(30));
        levelRecord .setBounds((WIDTH/2)+120,200,300,80);
        levelRecord .setVerticalTextPosition(SwingConstants.CENTER);
        levelRecord .setHorizontalTextPosition(SwingConstants.CENTER);
        levelRecord .setForeground(Color.white);
        nuevoRecord.add(levelRecord );
        //FONDO MENU
        ImageIcon imagenFondo = new ImageIcon("./images/nuevoRecord.gif");
        JLabel fondo = new JLabel();
        fondo.setBounds(0,0,WIDTH, HEIGHT);
        fondo.setIcon(imagenFondo);
        nuevoRecord.add(fondo);
    }

    /**
     * Prepara Acciones de la clase
     */
    public void prepareAcciones() {
        prepareAccionesPausa();
        prepareAccionesGameOver();
        prepareAccionesNuevoRecord();
        if ( Objects.equals(gui.getModo(), "I")) {
            prepareAccionesConfiguracionGame1();
            prepareAccionesTableroInd();
        }
        else if (Objects.equals(gui.getModo(), "M")) {
            prepareAccionesConfiguracionGame2();
            prepareAccionesTableroMul();
        }
        else {
            prepareAccionesConfiguracionGame2();
            prepareAccionesTableroMaq();
        }
    }

    /**
     * Prepara Acciones de Pausa
     */
    public void prepareAccionesPausa() {
        MouseListener mouseListenerPausa = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getSource().equals(regresarGame)) {
                    regresarGame.setBounds(WIDTH / 2 - 75, 180, 150, 70);
                } else if (e.getSource().equals(salirGame)) {
                    salirGame.setBounds(WIDTH / 2 - 75, 340, 150, 70);
                }else if (e.getSource().equals(reiniciarGame)) {
                    reiniciarGame.setBounds(WIDTH / 2 - 75, 260, 150, 70);
                }else if (e.getSource().equals(cargarGame)) {
                    cargarGame.setIcon(new ImageIcon("./images/save.png"));
                    cargarGame.setBounds(10,(HEIGHT+35)-120,90,65);
                }else if (e.getSource().equals(guardarGame)) {
                    guardarGame.setIcon(new ImageIcon("./images/load.png"));
                    guardarGame.setBounds(WIDTH-100,(HEIGHT+35)-120,90,65);
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getSource().equals(regresarGame)) {
                    regresarGame.setBounds(WIDTH / 2 - 75, 180, 150, 70);
                    regresarMenu();
                } else if (e.getSource().equals(salirGame)) {
                    salirGame.setBounds(WIDTH / 2 - 75, 340, 150, 70);
                    volverMenu();
                }else if (e.getSource().equals(reiniciarGame)) {
                    reiniciarGame.setBounds(WIDTH / 2 - 75, 260, 150, 70);
                    reiniciarGame();
                }else if (e.getSource().equals(cargarGame)) {
                    cargarGame.setIcon(new ImageIcon("./images/save.png"));
                    cargarGame.setBounds(10,(HEIGHT+35)-120,90,65);
                    //CARGAR
                }else if (e.getSource().equals(guardarGame)) {
                    guardarGame.setIcon(new ImageIcon("./images/load.png"));
                    guardarGame.setBounds(WIDTH-100,(HEIGHT+35)-120,90,65);
                    opcionGuardar();
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getSource().equals(regresarGame)) {
                    regresarGame.setBounds(WIDTH / 2 - 75, 180, 150, 70);
                } else if (e.getSource().equals(salirGame)) {
                    salirGame.setBounds(WIDTH / 2 - 75, 340, 150, 70);
                }else if (e.getSource().equals(reiniciarGame)) {
                    reiniciarGame.setBounds(WIDTH / 2 - 75, 260, 150, 70);
                }else if (e.getSource().equals(cargarGame)) {
                    cargarGame.setIcon(new ImageIcon("./images/save.png"));
                    cargarGame.setBounds(10,(HEIGHT+35)-120,90,65);
                }else if (e.getSource().equals(guardarGame)) {
                    guardarGame.setIcon(new ImageIcon("./images/load.png"));
                    guardarGame.setBounds(WIDTH-100,(HEIGHT+35)-120,90,65);
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if (e.getSource().equals(regresarGame)) {
                    regresarGame.setBounds(WIDTH / 2 - 85, 170, 170, 90);
                } else if (e.getSource().equals(salirGame)) {
                    salirGame.setBounds(WIDTH / 2 - 85, 330, 170, 90);
                }else if (e.getSource().equals(reiniciarGame)) {
                    reiniciarGame.setBounds(WIDTH / 2 - 85, 250, 170, 90);
                }else if (e.getSource().equals(cargarGame)) {
                    cargarGame.setIcon(new ImageIcon("./images/save2.png"));
                    cargarGame.setBounds(5,(HEIGHT+35)-128,100,80);
                }else if (e.getSource().equals(guardarGame)) {
                    guardarGame.setIcon(new ImageIcon("./images/load2.png"));
                    guardarGame.setBounds(WIDTH-105,(HEIGHT+35)-128,100,80);
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if (e.getSource().equals(regresarGame)) {
                    regresarGame.setBounds(WIDTH / 2 - 75, 180, 150, 70);
                } else if (e.getSource().equals(salirGame)) {
                    salirGame.setBounds(WIDTH / 2 - 75, 340, 150, 70);
                }else if (e.getSource().equals(reiniciarGame)) {
                    reiniciarGame.setBounds(WIDTH / 2 - 75, 260, 150, 70);
                }else if (e.getSource().equals(cargarGame)) {
                    cargarGame.setIcon(new ImageIcon("./images/save.png"));
                    cargarGame.setBounds(10,(HEIGHT+35)-120,90,65);
                }else if (e.getSource().equals(guardarGame)) {
                    guardarGame.setIcon(new ImageIcon("./images/load.png"));
                    guardarGame.setBounds(WIDTH-100,(HEIGHT+35)-120,90,65);
                }
            }
        };
        for (JButton boton : botonesPausa) {
            boton.addMouseListener(mouseListenerPausa);
        }
    }

    /**
     * Prepara Acciones de Game Over
     */
    private void prepareAccionesGameOver() {
        MouseListener mouseListenerMenuGameOver = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource().equals(gameOverReiniciar)){
                    gameOverReiniciar.setBounds((WIDTH+12)/2-220,300,160,70);
                }
                else if(e.getSource().equals(gameOverSalir)){
                    gameOverSalir.setBounds((WIDTH+12)/2+30,300,160,70);
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getSource().equals(gameOverReiniciar)){
                    gameOverReiniciar.setBounds((WIDTH+12)/2-220,300,160,70);
                    reiniciarGame();
                }
                else if(e.getSource().equals(gameOverSalir)){
                    gameOverSalir.setBounds((WIDTH+12)/2+30,300,160,70);
                    volverMenu();
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getSource().equals(gameOverReiniciar)){
                    gameOverReiniciar.setBounds((WIDTH+12)/2-220,300,160,70);
                }
                else if(e.getSource().equals(gameOverSalir)){
                    gameOverSalir.setBounds((WIDTH+12)/2+30,300,160,70);
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if(e.getSource().equals(gameOverReiniciar)){
                    gameOverReiniciar.setBounds((WIDTH+12)/2-230,290,180,90);
                }
                else if(e.getSource().equals(gameOverSalir)){
                    gameOverSalir.setBounds((WIDTH+12)/2+20,290,180,90);
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if(e.getSource().equals(gameOverReiniciar)){
                    gameOverReiniciar.setBounds((WIDTH+12)/2-220,300,160,70);
                }
                else if(e.getSource().equals(gameOverSalir)){
                    gameOverSalir.setBounds((WIDTH+12)/2+30,300,160,70);
                }
            }
        };
        for (JButton boton: botonesGameOver) {
            boton.addMouseListener(mouseListenerMenuGameOver);
        }
    }

    /**
     * Prepara Acciones de Configuracion1
     */
    private void prepareAccionesConfiguracionGame1(){
        MouseListener mouseListenerConfiguracion = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource().equals(backConfig)){
                    backConfig.setIcon(new ImageIcon("./images/back2.png"));
                }
                if(e.getSource().equals(musica)){
                    controlMusica();
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getSource().equals(backConfig)){
                    backConfig.setIcon(new ImageIcon("./images/back.png"));
                    regresarMenu();
                }
                if(e.getSource().equals(changeColor1)){
                    changeColor1.setBounds((WIDTH+12)/2-75,80,150,50);
                    cambiarColorFondo("0");
                }
                if(e.getSource().equals(controles)){
                    controles.setBounds((WIDTH+12)/2-75,160,150,50);
                    gui.abrirControles();
                }
                if(e.getSource().equals(Instruccion)){
                    Instruccion.setBounds((WIDTH+12)/2-75,240,150,50);
                    gui.abrirInstruccion();
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getSource().equals(backConfig)){
                    backConfig.setIcon(new ImageIcon("./images/back.png"));
                }
                if(e.getSource().equals(changeColor1)){
                    changeColor1.setBounds((WIDTH+12)/2-75,80,150,50);
                }
                if(e.getSource().equals(controles)){
                    controles.setBounds((WIDTH+12)/2-75,160,150,50);
                }
                if(e.getSource().equals(Instruccion)){
                    Instruccion.setBounds((WIDTH+12)/2-75,240,150,50);
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if(e.getSource().equals(backConfig)){
                    backConfig.setIcon(new ImageIcon("./images/back2.png"));
                }
                if(e.getSource().equals(changeColor1)){
                    changeColor1.setBounds((WIDTH+12)/2-85,70,170,70);
                }
                if(e.getSource().equals(controles)){
                    controles.setBounds((WIDTH+12)/2-85,150,170,70);
                }
                if(e.getSource().equals(Instruccion)){
                    Instruccion.setBounds((WIDTH+12)/2-85,230,170,70);
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if(e.getSource().equals(backConfig)){
                    backConfig.setIcon(new ImageIcon("./images/back.png"));
                }
                if(e.getSource().equals(changeColor1)){
                    changeColor1.setBounds((WIDTH+12)/2-75,80,150,50);
                }
                if(e.getSource().equals(controles)){
                    controles.setBounds((WIDTH+12)/2-75,160,150,50);
                }
                if(e.getSource().equals(Instruccion)){
                    Instruccion.setBounds((WIDTH+12)/2-75,240,150,50);
                }
            }
        };
        for (JButton boton: botonesConfigGame) {
            boton.addMouseListener(mouseListenerConfiguracion);
        }
        musica.addMouseListener(mouseListenerConfiguracion);
    }

    /**
     * Prepara Acciones de Configuracion2
     */
    private void prepareAccionesConfiguracionGame2(){
        MouseListener mouseListenerConfiguracionGame = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource().equals(backConfig)){
                    backConfig.setIcon(new ImageIcon("./images/back2.png"));
                }
                if(e.getSource().equals(musica)){
                    controlMusica();
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getSource().equals(backConfig)){
                    backConfig.setIcon(new ImageIcon("./images/back.png"));
                    regresarMenu();
                }
                if(e.getSource().equals(changeColor1)){
                    changeColor1.setBounds(WIDTH / 2 - 180, 80, 150, 50);
                    cambiarColorFondo("1");
                }
                if(e.getSource().equals(changeColor2)){
                    changeColor2.setBounds(WIDTH / 2 + 30, 80, 150, 50);
                    cambiarColorFondo("2");
                }
                if(e.getSource().equals(controles)){
                    controles.setBounds((WIDTH+12)/2-75,160,150,50);
                    gui.abrirControles();
                }
                if(e.getSource().equals(Instruccion)){
                    Instruccion.setBounds((WIDTH+12)/2-75,240,150,50);
                    gui.abrirInstruccion();
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getSource().equals(backConfig)){
                    backConfig.setIcon(new ImageIcon("./images/back.png"));
                }
                if(e.getSource().equals(changeColor1)){
                    changeColor1.setBounds(WIDTH / 2 - 180, 80, 150, 50);
                }
                if(e.getSource().equals(changeColor2)){
                    changeColor2.setBounds(WIDTH / 2 + 30, 80, 150, 50);
                }
                if(e.getSource().equals(controles)){
                    controles.setBounds((WIDTH+12)/2-75,160,150,50);
                }
                if(e.getSource().equals(Instruccion)){
                    Instruccion.setBounds((WIDTH+12)/2-75,240,150,50);
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if(e.getSource().equals(backConfig)){
                    backConfig.setIcon(new ImageIcon("./images/back2.png"));
                }
                if(e.getSource().equals(changeColor1)){
                    changeColor1.setBounds(WIDTH / 2 - 190, 70, 170, 70);
                }
                if(e.getSource().equals(changeColor2)){
                    changeColor2.setBounds(WIDTH / 2 + 20, 70, 170, 70);
                }
                if(e.getSource().equals(controles)){
                    controles.setBounds((WIDTH+12)/2-85,150,170,70);
                }
                if(e.getSource().equals(Instruccion)){
                    Instruccion.setBounds((WIDTH+12)/2-85,230,170,70);
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if(e.getSource().equals(backConfig)){
                    backConfig.setIcon(new ImageIcon("./images/back.png"));
                }
                if(e.getSource().equals(changeColor1)){
                    changeColor1.setBounds(WIDTH / 2 - 180, 80, 150, 50);
                }
                if(e.getSource().equals(changeColor2)){
                    changeColor2.setBounds(WIDTH / 2 + 30, 80, 150, 50);
                }
                if(e.getSource().equals(controles)){
                    controles.setBounds((WIDTH+12)/2-75,160,150,50);
                }
                if(e.getSource().equals(Instruccion)){
                    Instruccion.setBounds((WIDTH+12)/2-75,240,150,50);
                }
            }
        };
        for (JButton boton: botonesConfigGame) {
            boton.addMouseListener(mouseListenerConfiguracionGame);
        }
        musica.addMouseListener(mouseListenerConfiguracionGame);
    }

    /**
     * Prepara Acciones de Nuevo Record
     */
    public void prepareAccionesNuevoRecord() {
        MouseListener mouseListenerRecord = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource().equals(salirRecord)){
                    gameOverPanel();
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getSource().equals(salirRecord)){
                    salirRecord.setIcon(new ImageIcon("./images/salir.png"));
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getSource().equals(salirRecord)){
                    salirRecord.setIcon(new ImageIcon("./images/salir.png"));
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if(e.getSource().equals(salirRecord)){
                    salirRecord.setIcon(new ImageIcon("./images/salir2.png"));
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if(e.getSource().equals(salirRecord)){
                    salirRecord.setIcon(new ImageIcon("./images/salir.png"));
                }
            }
        };
        salirRecord.addMouseListener(mouseListenerRecord);
    }

    /**
     * Prepara Acciones de Teclas Modo Individual
     */
    private void prepareAccionesTableroInd() {
        movimientosTableroInd = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyChar() == 'w') {
                    panelGameInd.getTablero().rotar();
                } else if (e.getKeyChar() == 's') {
                    panelGameInd.getTablero().abajo();
                    panelGameInd.getGameInd().setUltTecla('s');
                } else if (e.getKeyChar() == 'd') {
                    panelGameInd.getTablero().derecha();
                } else if (e.getKeyChar() == 'a') {
                    panelGameInd.getTablero().izquierda();
                } else if (e.getKeyChar() == 'x') {
                    panelGameInd.getTablero().bajar();
                }  else if (e.getKeyChar() == '.'){
                panelGameInd.getGameInd().usarBuffo();
                }
            }
        };
        gui.requestFocus();
        gui.addKeyListener(movimientosTableroInd);
    }

    /**
     * Prepara Acciones de Teclas Modo Multijugador
     */
    private void prepareAccionesTableroMul() {
        movimientosTableroMul = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == 'w'){
                    panelGameMul.getTablero1().rotar();
                }else if(e.getKeyChar() == 's') {
                    panelGameMul.getGameMul1().setUltTecla('s');
                    panelGameMul.getTablero1().abajo();
                }else if(e.getKeyChar() == 'd') {
                    panelGameMul.getTablero1().derecha();
                }else if(e.getKeyChar() == 'a') {
                    panelGameMul.getTablero1().izquierda();
                }else if(e.getKeyChar() == 'x') {
                    panelGameMul.getTablero1().bajar();
                } else if (e.getKeyChar() == '.'){
                    panelGameMul.getGameMul1().usarBuffo();
                }if(e.getKeyCode()== KeyEvent.VK_UP ){
                    panelGameMul.getTablero2().rotar();
                }else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                    panelGameMul.getTablero2().abajo();
                    panelGameMul.getGameMul2().setUltTecla('s');
                }else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    panelGameMul.getTablero2().derecha();
                }else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                    panelGameMul.getTablero2().izquierda();
                }else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                    panelGameMul.getTablero2().bajar();
                }  else if (e.getKeyCode() == KeyEvent.VK_CONTROL){
                    panelGameMul.getGameMul2().usarBuffo();
                }
            }
        };
        gui.requestFocus();
        gui.addKeyListener(movimientosTableroMul);
    }

    /**
     * Prepara Acciones de Teclas Modo Maquina
     */
    private void prepareAccionesTableroMaq() {
        movimientosTableroMaq = new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyReleased(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar() == 'w'){
                    panelGameMaq.getTablero1().rotar();
                }else if(e.getKeyChar() == 's') {
                    panelGameMaq.getGameMaq1().setUltTecla('s');
                    panelGameMaq.getTablero1().abajo();
                }else if(e.getKeyChar() == 'd') {
                    panelGameMaq.getTablero1().derecha();
                }else if(e.getKeyChar() == 'a') {
                    panelGameMaq.getTablero1().izquierda();
                }else if(e.getKeyChar() == 'x') {
                    panelGameMaq.getTablero1().bajar();
                } else if (e.getKeyChar() == '.'){
                    panelGameMaq.getGameMaq1().usarBuffo();
                }if(e.getKeyCode()== KeyEvent.VK_UP ){
                    panelGameMaq.getTablero2().rotar();
                }else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                    panelGameMaq.getTablero2().abajo();
                    panelGameMaq.getGameMaq2().setUltTecla('s');
                }else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    panelGameMaq.getTablero2().derecha();
                }else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                    panelGameMaq.getTablero2().izquierda();
                }else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                    panelGameMaq.getTablero2().bajar();
                }  else if (e.getKeyCode() == KeyEvent.VK_CONTROL){
                    panelGameMaq.getGameMaq2().usarBuffo();
                }
            }
        };
        gui.requestFocus();
        gui.addKeyListener(movimientosTableroMaq);
    }

    /**
     * Metodo que se muestra cuando hay un nuevo record en el juego
     */
    private void nuevoRecord() {
        gui.getCl().show(gui.getMainPanel(),"MenuNuevoRecord");
    }

    /**
     * Metodo para abrir menu de pausa
     */
    public void pausa(){
        if (Objects.equals(gui.getModo(), "I")) panelGameInd.getGameInd().stopGame(true);
        if (Objects.equals(gui.getModo(), "M")) panelGameMul.getGameMul1().stopGame(true);
        if (Objects.equals(gui.getModo(), "M")) panelGameMul.getGameMul2().stopGame(true);
        if (Objects.equals(gui.getModo(), "C")) panelGameMaq.getGameMaq1().stopGame(true);
        if (Objects.equals(gui.getModo(), "C")) panelGameMaq.getGameMaq2().stopGame(true);
        gui.getCl().show(gui.getMainPanel(), "menuPausa");
    }

    /**
     * Metodo para regresar al juego
     */
    private void regresarMenu() {
        if(Objects.equals(gui.getModo(), "I")) {
            panelGameInd.getGameInd().stopGame(false);
            gui.getCl().show(gui.getMainPanel(),"menuModoInd");
        }
        else if (Objects.equals(gui.getModo(), "M")) {
            panelGameMul.getGameMul1().stopGame(false);
            panelGameMul.getGameMul2().stopGame(false);
            gui.getCl().show(gui.getMainPanel(),"menuModoMul");
        }
        else if (Objects.equals(gui.getModo(), "C")) {
            panelGameMaq.getGameMaq1().stopGame(false);
            panelGameMaq.getGameMaq2().stopGame(false);
            gui.getCl().show(gui.getMainPanel(),"menuModoMaq");
        }
    }

    /**
     * Metodo para abrir menu configuracion del juego
     */
    protected void configuracionGame() {
        if (Objects.equals(gui.getModo(), "I")) panelGameInd.getGameInd().stopGame(true);
        if (Objects.equals(gui.getModo(), "M")) panelGameMul.getGameMul1().stopGame(true);
        if (Objects.equals(gui.getModo(), "M")) panelGameMul.getGameMul2().stopGame(true);
        if (Objects.equals(gui.getModo(), "C")) panelGameMaq.getGameMaq1().stopGame(true);
        if (Objects.equals(gui.getModo(), "C")) panelGameMaq.getGameMaq2().stopGame(true);
        gui.getCl().show(gui.getMainPanel(),"menuConfigGame");
    }

    /**
     * Metodo para volver al Menu Principal
     */
    private void volverMenu() {
        guardarDatosPartida();
        gui.salirGame();
    }

    /**
     * Metodo para reiniciar el juego
     */
    private void reiniciarGame() {
        guardarDatosPartida();
        gui.reiniciarGame();
    }

    /**
     * Metodo para evluar si hubo un nuevo Record
     */
    public void gameOver() {
        if (Objects.equals(gui.getModo(), "I")) {
            if (gui.getMejorPuntaje() < Integer.parseInt(panelGameInd.getTime())) {
                updateNuevoRecord(panelGameInd.getName(),panelGameInd.getTime(),panelGameInd.getScore(),panelGameInd.getLevel());
                nuevoRecord();
            }
            else {
                gameOverPanel();
            }
        }
        else if (Objects.equals(gui.getModo(), "M")) {
            System.out.println("GAME OVER MULTI");
            if (gui.getMejorPuntaje() < Integer.parseInt(panelGameMul.getTime1())) {
                updateNuevoRecord(panelGameMul.getName1(),panelGameMul.getTime1(),panelGameMul.getScore1(),panelGameMul.getLevel());
                nuevoRecord();
            }
            else if (gui.getMejorPuntaje() < Integer.parseInt(panelGameMul.getTime2())) {
                updateNuevoRecord(panelGameMul.getName2(),panelGameMul.getTime2(),panelGameMul.getScore2(),panelGameMul.getLevel());
                nuevoRecord();
            }
            else gameOverPanel();
        }
        else if (Objects.equals(gui.getModo(), "C")) {
            System.out.println("GAME OVER MAQ");
            if (gui.getMejorPuntaje() < Integer.parseInt(panelGameMaq.getTime1())) {
                updateNuevoRecord(panelGameMaq.getName1(),panelGameMaq.getTime1(),panelGameMaq.getScore1(),panelGameMaq.getLevel());
                nuevoRecord();
            }
            else if (gui.getMejorPuntaje() < Integer.parseInt(panelGameMaq.getTime2())) {
                updateNuevoRecord(panelGameMaq.getName2(),panelGameMaq.getTime2(),panelGameMaq.getScore2(),panelGameMaq.getLevel());
                nuevoRecord();
            }
            else gameOverPanel();
        }
    }

    /**
     * Metodo para abrir panel game Over
     */
    public void gameOverPanel() {
        updateGameOver();
        gui.getCl().show(gui.getMainPanel(), "menuGameOver");
    }

    /**
     * Metodo que permite elegir el color a cambiar
     */
    private void cambiarColorFondo(String num){
        Color color;
        if (Objects.equals(this.gui.getModo(), "I")) {
            color = JColorChooser.showDialog(menuConfig, "Elija un color para el fondo", Color.BLACK);
            changeColor1.setBackground(color);
            panelGameInd.getMenupanelGameInd().setBackground(color);
        }
        else if (Objects.equals(num, "1") && Objects.equals(this.gui.getModo(), "M")) {
            color = JColorChooser.showDialog(menuConfig, "Elija un color para el fondo", Color.BLACK);
            changeColor1.setBackground(color);
            panelGameMul.getPanel1().setBackground(color);
        }
        else if (Objects.equals(num, "1") && Objects.equals(this.gui.getModo(), "C")) {
            color = JColorChooser.showDialog(menuConfig, "Elija un color para el fondo", Color.BLACK);
            changeColor1.setBackground(color);
            panelGameMaq.getPanel1().setBackground(color);
        }
        else if (Objects.equals(num, "2") &&  Objects.equals(this.gui.getModo(), "M")) {
            color = JColorChooser.showDialog(menuConfig, "Elija un color para el fondo", Color.BLACK);
            changeColor2.setBackground(color);
            panelGameMul.getPanel2().setBackground(color);
        }
        else if (Objects.equals(num, "2") &&  Objects.equals(this.gui.getModo(), "C")) {
            color = JColorChooser.showDialog(menuConfig, "Elija un color para el fondo", Color.BLACK);
            changeColor2.setBackground(color);
            panelGameMaq.getPanel2().setBackground(color);
        }
    }

    /**
     * Metodo que detiene o reanuda la musica
     */
    private void controlMusica() {
        if (musica.isSelected()) {
            gui.clip.start();
            gui.clip.loop(Clip.LOOP_CONTINUOUSLY);
            gui.getMusica().setSelected(true);
        } else {
            gui.clip.stop();
            gui.getMusica().setSelected(false);
        }
    }

    /**
     * Metodo que guardar un archivo formato txt
     */
    private void opcionGuardar(){
        JFileChooser chooser = new JFileChooser();
        int archivoVal = chooser.showDialog(null,"Exportar");
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        if (archivoVal == JFileChooser.APPROVE_OPTION)
        {
            try {
                if (Objects.equals(gui.getModo(), "I")) panelGameInd.guardar(chooser.getSelectedFile());
                if (Objects.equals(gui.getModo(), "M")) panelGameMul.guardar(chooser.getSelectedFile());
                if (Objects.equals(gui.getModo(), "C")) panelGameMaq.guardar(chooser.getSelectedFile());
            }
            catch (TetrisException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR GUARDANDO", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Metodo para retornar en que modo de velocidad esta la partida
     * @return Modo de la partida
     */
    public String getMoodSpeed() {
        if (gui.isMoodSpeed()) return "true";
        else return "false";
    }

    /**
     * Metodo para cambiar modo Speed de la partida
     * @param bol booleano
     */
    public void setMoodSpeed(boolean bol) {
        this.gui.setMoodSpeed(bol);
    }

    /**
     * Metodo para acutalizar menu GameOver
     */
    public void updateGameOver() {
        if (Objects.equals(gui.getModo(), "I")) {
            System.out.println("GAME OVER IND");
            this.name1.setText(panelGameInd.getName());
            this.time1.setText(panelGameInd.getTime());
            this.score1.setText(panelGameInd.getScore());
            this.level1.setText(panelGameInd.getLevel());
        }
        else if (Objects.equals(gui.getModo(), "M")) {
            System.out.println("GAME OVER MUL");
            this.level1.setText(panelGameMul.getLevel());
            this.level2.setText(panelGameMul.getLevel());
            if (Integer.parseInt(panelGameMul.getTime1()) > Integer.parseInt(panelGameMul.getTime2())) {
                this.name1.setText("1."+panelGameMul.getName1());
                this.time1.setText(panelGameMul.getTime1());
                this.score1.setText(panelGameMul.getScore1());
                this.name2.setText("2."+panelGameMul.getName2());
                this.time2.setText(panelGameMul.getTime2());
                this.score2.setText(panelGameMul.getScore2());
            }
            else if (Integer.parseInt(panelGameMul.getTime1()) < Integer.parseInt(panelGameMul.getTime2())) {
                this.name1.setText("1."+panelGameMul.getName2());
                this.time1.setText(panelGameMul.getTime2());
                this.score1.setText(panelGameMul.getScore2());
                this.name2.setText("2."+panelGameMul.getName1());
                this.time2.setText(panelGameMul.getTime1());
                this.score2.setText(panelGameMul.getScore1());
            }
            else {
                this.name1.setText("1."+panelGameMul.getName1());
                this.time1.setText(panelGameMul.getTime1());
                this.score1.setText(panelGameMul.getScore1());
                this.name2.setText("1."+panelGameMul.getName2());
                this.time2.setText(panelGameMul.getTime2());
                this.score2.setText(panelGameMul.getScore2());
            }
        }
        else if (Objects.equals(gui.getModo(), "C")) {
            System.out.println("GAME OVER MAQ");
            this.level1.setText(panelGameMaq.getLevel());
            this.level2.setText(panelGameMaq.getLevel());
            if (Integer.parseInt(panelGameMaq.getTime1()) > Integer.parseInt(panelGameMaq.getTime2())) {
                this.name1.setText("1."+panelGameMaq.getName1());
                this.time1.setText(panelGameMaq.getTime1());
                this.score1.setText(panelGameMaq.getScore1());
                this.name2.setText("2."+panelGameMaq.getName2());
                this.time2.setText(panelGameMaq.getTime2());
                this.score2.setText(panelGameMaq.getScore2());
            }
            else if (Integer.parseInt(panelGameMaq.getTime1()) < Integer.parseInt(panelGameMaq.getTime2())) {
                this.name1.setText("1."+panelGameMaq.getName2());
                this.time1.setText(panelGameMaq.getTime2());
                this.score1.setText(panelGameMaq.getScore2());
                this.name2.setText("2."+panelGameMaq.getName1());
                this.time2.setText(panelGameMaq.getTime1());
                this.score2.setText(panelGameMaq.getScore1());
            }
            else {
                this.name1.setText("1."+panelGameMaq.getName1());
                this.time1.setText(panelGameMaq.getTime1());
                this.score1.setText(panelGameMaq.getScore1());
                this.name2.setText("1."+panelGameMaq.getName2());
                this.time2.setText(panelGameMaq.getTime2());
                this.score2.setText(panelGameMaq.getScore2());
            }

        }
    }

    /**
     * Metodo para guardar los datos de la partida.
     */
    private void guardarDatosPartida() {
        if (Objects.equals(gui.getModo(), "I")) panelGameInd.guardarPartida();
        if (Objects.equals(gui.getModo(), "M")) panelGameMul.guardarPartida();
        if (Objects.equals(gui.getModo(), "C")) panelGameMaq.guardarPartida();
    }

    /**
     * Metodo para actualizar puntajes de Nuevo Record
     * @param n nombre
     * @param t tiempo
     * @param s score
     * @param l level
     */
    private void updateNuevoRecord(String n, String t, String s, String l) {
        nombreRecord.setText(n);
        timeRecord.setText(t);
        scoreRecord.setText(s);
        levelRecord.setText(l);
    }

    /**
     * Metodo que retorna Los movimientos de la partida
     * @return KeyListener de la partida
     */
    public KeyListener getMovimientosTablero() {
        if (Objects.equals(gui.getModo(), "I")) return movimientosTableroInd;
        else if (Objects.equals(gui.getModo(), "M")) return movimientosTableroMul;
        else return movimientosTableroMaq;
    }

    /**
     * Metodo para cambiar color boton Configuracion
     * @param color Color nuevo
     */
    public void setChangeColor1(Color color) {
        changeColor1.setBackground(color);
    }

    /**
     * Metodo para cambiar color boton Configuracion
     * @param color Color nuevo
     */
    public void setChangeColor2(Color color) {
        changeColor2.setBackground(color);
    }

    public String getModo(){
        return gui.getModo();
    }

    public AccionInd getAccionInd(){
        return panelGameInd.getGameInd();
    }

    public AccionMul getAccionMul1(){
        return panelGameMul.getGameMul1();
    }

    public AccionMul getAccionMul2(){
        return panelGameMul.getGameMul2();
    }

    public AccionMaq getAccionMaq1(){
        return panelGameMaq.getGameMaq1();
    }

    public AccionMaq getAccionMaq2(){
        return panelGameMaq.getGameMaq2();
    }
}
