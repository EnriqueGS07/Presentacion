package presentacion;

import dominio.*;
import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.*;

public class TetrisGUI extends JFrame {

    /* TAMAÑO JUEGO */
    private static final int WIDTH = 600;
    private static final int HEIGHT = 500;

    /* VENTANA PRINCIPAL */
    private CardLayout cl;
    private Container mainPanel;

    /* OTHER */
    private RoundedBorder borderRed;
    private Border borde;
    private String modo;

    /* PANEL MENU */
    private JPanel menuPanel;
    private JButton newGame;
    private JButton loadGame;
    private JButton highScores;
    private JButton getOut;
    private JButton configuracion;
    private JLabel Icono;
    private JFileChooser fileChooser;
    private ArrayList<JButton> botonesMenu;

    /* PANEL MENU NEW GAME*/
    private JPanel menuNewGame;
    private JButton individual;
    private JButton multijugador;
    private JButton contraMaquina;
    private JButton volverMenu;
    private ArrayList<JButton> botonesNewGame;

    /* PANEL MENU GET OUT*/
    private JPanel menuGetOut;
    private JButton getOutAceptar;
    private JButton getOutCancelar;
    private JLabel tituloSairGame;
    private ArrayList<JButton> salirGame;

    /* PANEL CONFIGURACION */
    private JPanel menuConfig;
    private JButton atrasConfig;
    private JButton controles;
    private JButton cambiarColor;
    private JButton instrucciones;
    private JCheckBox musica;
    public Clip clip;
    private ArrayList<JCheckBox> botonesmusica;
    private ArrayList<JButton> botonesConfi;

    /* PANEL CONTROLES */
    private JPanel infoControles;
    private JButton atrasConfig2;
    private JLabel textoControles;
    private ArrayList<JButton> botonesControles;

    /* PANEL INSTRUCCIONES */
    private JPanel infoInstruccion;
    private JButton atrasConfig3;
    private JLabel textoInstruccion;
    private ArrayList<JButton> botonesInstruccion;

    /* PANEL MENU DATOS INDIVIDUAL*/
    private JPanel menuDatos;
    private JLabel tituloIcon;
    private JLabel textoDigite;
    private JButton siguienteDatos;
    private JButton volverDatos;
    private JButton colorInd;
    private JTextField campoNombre;
    private ArrayList<JLabel> labelDatos;
    private ArrayList<JButton> botonesDatos;

    /* PANEL MENU DATOS MULTIJUGADOR*/
    private JPanel menuDatosMulti;
    private JLabel tituloIconMulti;
    private JLabel textoDigiteMulti1;
    private JLabel textoDigiteMulti2;
    private JButton siguienteDatosMulti;
    private JButton volverDatosMulti;
    private JButton colorMul1;
    private JButton colorMul2;
    private JTextField campoNombreMulti1;
    private JTextField campoNombreMulti2;
    private ArrayList<JLabel> labelDatosMulti;
    private ArrayList<JButton> botonesDatosMulti;

    /* PANEL MENU DATOS CONTRA MAQUINA*/
    private JPanel menuDatosMaqui;
    private JLabel tituloIconMaqui;
    private JLabel textoDigiteMaqui1;
    private JLabel textoDigiteMaqui2;
    private JButton siguienteDatosMaqui;
    private JButton volverDatosMaqui;
    private JButton colorMaq1;
    private JButton colorMaq2;
    private JTextField campoNombreMaqui1;
    private JTextField campoNombreMaqui2;
    private ArrayList<JLabel> labelDatosMaqui;
    private ArrayList<JButton> botonesDatosMaqui;

    /* PANEL MENU PUNTAJES*/
    private JPanel panelPuntajes;
    private JLabel puntajes;
    private JButton atrasPuntajes;
    private JButton historial;
    private ArrayList<JButton> botonesHighScores;
    private HashMap<String,ArrayList<String>> datosPuntajes;

    /* PANEL MENU GAME DATA */
    private JPanel menuVelocidad;
    private JLabel tituloConfirmar;
    private JLabel tituloVelocidad;
    private JLabel tituloBuffos;
    private JButton continueGame;
    private JButton velSpeed;
    private JButton velSlow;
    private JButton volverGameData;
    private JTextField velInicial;
    private JTextField numBuffos;
    private ArrayList<JButton> botonesSpeed;

    /* HISTORIAL */
    private JPanel panelHistorial;
    private JButton volverHistorial;
    private JList<String> listaJugadores;
    private DefaultListModel<String> modelo;
    private JScrollPane scrollLista;
    private ArrayList<JButton> botonesHistorial;
    private JLabel tituloHistorial;
    private JButton verHistorial;

    /* PANEL USER */
    private JPanel panelUser;
    private JLabel tituloUser;
    private JLabel nombreUser;
    private JButton volverUser;
    private JList<String> listaUser;
    private DefaultListModel<String> modeloUser;
    private JScrollPane scrollListaUser;
    private ArrayList<JButton> botonesUser;

    /* PANEL MODO MAQUINA */
    private JPanel menuModoMaq;
    private JButton modoMaqPri;
    private JButton modoMaqExp;
    private JLabel tituloModoMaq;
    private String modoMaq;

    /* DATOS NOMBRES*/
    public ArrayList<JTextField> dataNombres;

    /* DATOS COLORS*/
    public ArrayList<Color> dataColores;

    /* MOOD SPEED */
    private boolean moodSpeed;

    /* PANEL GAME */
    private PanelGame panelGame;

    /* HISTORIAL */
    HashMap<String,HashMap<Integer,ArrayList<String>>> dataUsers;

    /**
     * Constructor clase Tetris
     */
    public TetrisGUI() {
        prepareElementos();
        prepareAcciones();
    }

    /**
     * Prepara los elementos de la clase
     */
    private void prepareElementos() {
        //AJUSTES PANTALLA
        this.setTitle("POOBTETRIS");
        this.setSize(new Dimension(WIDTH+12,HEIGHT+35));
        this.setLocationRelativeTo(null);
        this.setIconImage((new ImageIcon("./images/TetrisLogo.png")).getImage());
        //PANTALLA PRINCIPAL
        cl = new CardLayout();
        mainPanel = new JPanel(cl);
        add(mainPanel);
        //OTHER
        botonesMenu = new ArrayList<>();
        botonesNewGame = new ArrayList<>();
        labelDatos = new ArrayList<>();
        botonesDatos = new ArrayList<>();
        botonesConfi = new ArrayList<>();
        botonesHighScores = new ArrayList<>();
        botonesControles = new ArrayList<>();
        botonesInstruccion = new ArrayList<>();
        botonesDatosMulti = new ArrayList<>();
        labelDatosMulti = new ArrayList<>();
        botonesDatosMaqui = new ArrayList<>();
        labelDatosMaqui = new ArrayList<>();
        botonesmusica = new ArrayList<>();
        dataNombres = new ArrayList<>();
        dataColores = new ArrayList<>();
        salirGame = new ArrayList<>();
        botonesSpeed = new ArrayList<>();
        datosPuntajes = new HashMap<>();
        botonesHistorial = new ArrayList<>();
        dataUsers = new HashMap<>();
        botonesUser = new ArrayList<>();
        //BORDES
        borde = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2);
        borderRed = new RoundedBorder(22);
        //ELEMENTOS
        prepareElementosMenu();
        prepareElementosGetOut();
        prepareElementosNewGame();
        prepareElementosConfiguracion();
        prepareElementosControles();
        prepareElementosInstruccion();
        prepareElemenotosPuntajes();
        prepareElementosDatosInd();
        prepareElementosDatosMulti();
        prepareElementosDatosMaqui();
        prepareElementosGameData();
        prepareElementosHistorial();
        prepareElementosUser();
        loadRegisteredUsers();
        changePuntajesPanel();
        prepareElementosModoMaquina();
    }

    /**
     * Prepara los elementos del Menu de Inicio
     */
    private void prepareElementosMenu() {
        //PANEL MENU
        menuPanel = new JPanel();
        mainPanel.add(menuPanel,"Menu");
        menuPanel.setLayout(null);
        //ICONO MENU
        Icono = new JLabel();
        Icono.setIcon(new ImageIcon("./images/TetrisLogo2.png"));
        Icono.setBounds((getWidth()/2)-155,40,300,140);
        Icono.setHorizontalAlignment(JLabel.CENTER);
        Icono.setVerticalAlignment(JLabel.CENTER);
        menuPanel.add(Icono);
        //LOAD
        fileChooser = new JFileChooser();
        //NEW GAME
        newGame = new JButton("New Game");
        newGame.setBounds(this.getWidth()/2-68,190,130,40);
        newGame.setBackground(Color.yellow);
        newGame.setBorder(borderRed);
        newGame.setOpaque(false);
        newGame.setForeground(Color.yellow);
        menuPanel.add(newGame);
        botonesMenu.add(newGame);
        //HIGHSCORES
        highScores = new JButton("High Scores");
        highScores.setBackground(Color.blue);
        highScores.setBounds(this.getWidth()/2-68,260,130,40);
        highScores.setBorder(borderRed);
        highScores.setOpaque(false);
        highScores.setForeground(Color.BLUE);
        menuPanel.add(highScores);
        botonesMenu.add(highScores);
        //GET OUT
        getOut = new JButton("Get Out");
        getOut.setBackground(Color.red);
        getOut.setBounds(this.getWidth()/2-68,330,130,40);
        getOut.setBorder(borderRed);
        getOut.setOpaque(false);
        getOut.setForeground(Color.RED);
        menuPanel.add(getOut);
        botonesMenu.add(getOut);
        //LOAD GAME
        loadGame = new JButton();
        loadGame.setIcon(new ImageIcon("./images/save.png"));
        loadGame.setBounds(10,this.getHeight()-120,60,65);
        loadGame.setFocusPainted(false);
        loadGame.setBorderPainted(false);
        loadGame.setContentAreaFilled(false);
        menuPanel.add(loadGame);
        botonesMenu.add(loadGame);
        //CONFIGURACION
        configuracion = new JButton();
        configuracion.setIcon(new ImageIcon("./images/configuracion.png"));
        configuracion.setBounds(515,10,72,65);
        configuracion.setFocusPainted(false);
        configuracion.setBorderPainted(false);
        configuracion.setContentAreaFilled(false);
        menuPanel.add(configuracion);
        botonesMenu.add(configuracion);
        //FONDO MENU
        ImageIcon imagenFondo = new ImageIcon("./images/fondogif2.gif");
        JLabel fondo = new JLabel();
        fondo.setBounds(0,0,WIDTH, HEIGHT);
        fondo.setIcon(imagenFondo);
        menuPanel.add(fondo);
    }

    /**
     * Prepara los elementos del Menu New Game
     */
    public void prepareElementosNewGame(){
        //PANEL NEW GAME
        menuNewGame = new JPanel();
        menuNewGame.setLayout(null);
        mainPanel.add(menuNewGame, "menuModo");
        //NEW GAME
        individual = new JButton("INDIVIDUAL");
        individual.setBackground(Color.yellow);
        individual.setBounds(this.getWidth()/2-80,60,160,110);
        individual.setBorder(borde);
        menuNewGame.add(individual);
        botonesNewGame.add(individual);
        //MULTIJUGADOR
        multijugador = new JButton("MULTIJUGADOR");
        multijugador.setBackground(Color.red);
        multijugador.setBounds(this.getWidth()/2-80,180,160,110);
        multijugador.setBorder(borde);
        menuNewGame.add(multijugador);
        botonesNewGame.add(multijugador);
        //CONTRA MAQUINA
        contraMaquina = new JButton("CONTRA MAQUINA");
        contraMaquina.setBackground(Color.blue);
        contraMaquina.setBounds(this.getWidth()/2-80,300,160,110);
        contraMaquina.setBorder(borde);
        menuNewGame.add(contraMaquina);
        botonesNewGame.add(contraMaquina);
        //VOLVER
        volverMenu = new JButton();
        volverMenu.setIcon(new ImageIcon("./images/back.png"));
        volverMenu.setBounds(20,10,100,40);
        volverMenu.setFocusPainted(false);
        volverMenu.setBorderPainted(false);
        volverMenu.setContentAreaFilled(false);
        menuNewGame.add(volverMenu);
        botonesNewGame.add(volverMenu);
        //FONDO MENU
        ImageIcon imagenFondo = new ImageIcon("./images/fondogif2.gif");
        JLabel fondo = new JLabel();
        fondo.setBounds(0,0,WIDTH, HEIGHT);
        fondo.setIcon(imagenFondo);
        menuNewGame.add(fondo);
    }

    /**
     * Prepara los elementos del Menu Get Out
     */
    public void prepareElementosGetOut(){
        //PANEL Get Out
        menuGetOut = new JPanel();
        menuGetOut.setLayout(null);
        mainPanel.add(menuGetOut, "menuGetOut");
        //SALIR
        getOutAceptar = new JButton(" SALIR ");
        getOutAceptar.setBackground(Color.red);
        getOutAceptar.setBounds(this.getWidth()/2-220,220,160,70);
        getOutAceptar.setBorder(borderRed);
        getOutAceptar.setOpaque(false);
        getOutAceptar.setForeground(Color.red);
        getOutAceptar.setHorizontalTextPosition(SwingConstants.CENTER);
        getOutAceptar.setVerticalTextPosition(SwingConstants.CENTER);
        menuGetOut.add(getOutAceptar);
        salirGame.add(getOutAceptar);
        //CANCELAR
        getOutCancelar = new JButton("CANCELAR");
        getOutCancelar.setBackground(Color.green);
        getOutCancelar.setBounds(this.getWidth()/2+30,220,160,70);
        getOutCancelar.setBorder(borderRed);
        getOutCancelar.setOpaque(false);
        getOutCancelar.setForeground(Color.green);
        getOutCancelar.setHorizontalTextPosition(SwingConstants.CENTER);
        getOutCancelar.setVerticalTextPosition(SwingConstants.CENTER);
        menuGetOut.add(getOutCancelar);
        salirGame.add(getOutCancelar);
        //TEXTO
        tituloSairGame = new JLabel();
        tituloSairGame.setText("           ¿DESEAS SALIR DEL JUEGO?");
        tituloSairGame.setBorder(borde);
        tituloSairGame.setBounds(this.getWidth()/2 - 125,100,250,50);
        tituloSairGame.setForeground(Color.white);
        menuGetOut.add(tituloSairGame);
        //FONDO MENU
        ImageIcon imagenFondo = new ImageIcon("./images/fondogif2.gif");
        JLabel fondo = new JLabel();
        fondo.setBounds(0,0,WIDTH, HEIGHT);
        fondo.setIcon(imagenFondo);
        menuGetOut.add(fondo);
    }

    /**
     * Prepara los elementos del Menu Configuracion
     */
    private void prepareElementosConfiguracion(){
        //MENU CONFIGURACION
        menuConfig = new JPanel();
        menuConfig.setLayout(null);
        mainPanel.add(menuConfig, "Config");
        //VOLVER
        atrasConfig = new JButton();
        atrasConfig.setIcon(new ImageIcon("./images/back.png"));
        atrasConfig.setBounds(20,10,100,40);
        atrasConfig.setFocusPainted(false);
        atrasConfig.setBorderPainted(false);
        atrasConfig.setContentAreaFilled(false);
        menuConfig.add(atrasConfig);
        botonesConfi.add(atrasConfig);
        //CAMBIAR COLOR FONDO
        cambiarColor = new JButton("Color Fondo");
        cambiarColor.setBackground(Color.yellow);
        cambiarColor.setBorder(borde);
        cambiarColor.setBounds(this.getWidth()/2-75,80,150,50);
        menuConfig.add(cambiarColor);
        botonesConfi.add(cambiarColor);
        //CONTROLES
        controles = new JButton("Controles");
        controles.setBackground(Color.red);
        controles.setBorder(borde);
        controles.setBounds(this.getWidth()/2-75,160,150,50);
        menuConfig.add(controles);
        botonesConfi.add(controles);
        //INSTRUCCIONES
        instrucciones = new JButton("Instrucciones");
        instrucciones.setBackground(Color.blue);
        instrucciones.setBorder(borde);
        instrucciones.setBounds(this.getWidth()/2-75,240,150,50);
        menuConfig.add(instrucciones);
        botonesConfi.add(instrucciones);
        //MUSICA
        musica = new JCheckBox("Musica");
        musica.setSelected(false);
        musica();
        controlMusica();
        musica.setBounds(this.getWidth()/2-75,320,150,50);
        musica.setBackground(Color.green);
        musica.setBorder(borde);
        menuConfig.add(musica);
        botonesmusica.add(musica);
        //FONDO MENU
        ImageIcon imagenFondo = new ImageIcon("./images/fondogif2.gif");
        JLabel fondo = new JLabel();
        fondo.setBounds(0,0,WIDTH, HEIGHT);
        fondo.setIcon(imagenFondo);
        menuConfig.add(fondo);
    }

    /**
     * Prepara los elementos del Menu Controles
     */
    private void prepareElementosControles() {
        //INFO CONTROLES
        infoControles = new JPanel();
        infoControles.setLayout(null);
        mainPanel.add(infoControles, "infoControles");
        //VOLVER
        atrasConfig2 = new JButton();
        atrasConfig2.setIcon(new ImageIcon("./images/back.png"));
        atrasConfig2.setBounds(20,10,100,40);
        atrasConfig2.setBorderPainted(false);
        atrasConfig2.setFocusPainted(false);
        atrasConfig2.setContentAreaFilled(false);
        infoControles.add(atrasConfig2);
        botonesControles.add(atrasConfig2);
        //PANTALLA
        ImageIcon imagenControles = new ImageIcon("./images/controles.png");
        textoControles = new JLabel();
        textoControles.setBounds(81,0,WIDTH, HEIGHT);
        textoControles.setIcon(imagenControles);
        infoControles.setBackground(Color.gray);
        infoControles.add(textoControles);
    }

    /**
     * Prepara los elementos del Menu Instrucciones
     */
    private void prepareElementosInstruccion() {
        //INFO CONTROLES
        infoInstruccion = new JPanel();
        infoInstruccion.setLayout(null);
        mainPanel.add(infoInstruccion, "infoInstruccion");
        //VOLVER
        atrasConfig3 = new JButton();
        atrasConfig3.setIcon(new ImageIcon("./images/back.png"));
        atrasConfig3.setBounds(20,10,100,40);
        atrasConfig3.setBorderPainted(false);
        atrasConfig3.setFocusPainted(false);
        atrasConfig3.setContentAreaFilled(false);
        infoInstruccion.add(atrasConfig3);
        botonesInstruccion.add(atrasConfig3);
        //PANTALLA
        ImageIcon imagenControles = new ImageIcon("./images/fondoInstruccion.png");
        textoInstruccion = new JLabel();
        textoInstruccion.setBounds(0,0,WIDTH, HEIGHT);
        textoInstruccion.setIcon(imagenControles);
        infoInstruccion.setBackground(Color.gray);
        infoInstruccion.add(textoInstruccion);
    }

    /**
     * Prepara los elementos del Menu Puntajes
     */
    private void prepareElemenotosPuntajes(){
        //MENU PUNTAJES
        panelPuntajes = new JPanel();
        panelPuntajes.setLayout(null);
        mainPanel.add(panelPuntajes, "Puntajes");
        //INFO PUNTAJES
        puntajes = new JLabel();
        panelPuntajes.add(puntajes);
        puntajes.setBounds(this.getWidth()/2-310,this.getHeight()/2-250,this.getWidth(),this.getHeight());
        panelPuntajes.setBackground(Color.black);
        puntajes.setForeground(Color.white);
        puntajes.setVerticalAlignment(SwingConstants.CENTER);
        puntajes.setHorizontalAlignment(SwingConstants.CENTER);
        puntajes.setVerticalTextPosition(SwingConstants.CENTER);
        puntajes.setHorizontalTextPosition(SwingConstants.CENTER);
        puntajes.setFont(customFont(30));
        //VOLVER
        atrasPuntajes = new JButton();
        atrasPuntajes.setIcon(new ImageIcon("./images/back.png"));
        atrasPuntajes.setBounds(20,10,100,40);
        atrasPuntajes.setFocusPainted(false);
        atrasPuntajes.setBorderPainted(false);
        atrasPuntajes.setContentAreaFilled(false);
        panelPuntajes.add(atrasPuntajes);
        botonesHighScores.add(atrasPuntajes);
        //HISTORIAL
        historial = new JButton();
        historial.setIcon(new ImageIcon("./images/record.png"));
        historial.setBounds(this.getWidth()-230,this.getHeight()-150,115,45);
        historial.setFocusPainted(false);
        historial.setBorderPainted(false);
        historial.setContentAreaFilled(false);
        panelPuntajes.add(historial);
        botonesHighScores.add(historial);
        //FONDO
        ImageIcon imagenFondo = new ImageIcon("./images/fondoPuntajes2f.gif");
        JLabel fondo = new JLabel();
        fondo.setBounds(0,0,WIDTH, HEIGHT);
        fondo.setIcon(imagenFondo);
        panelPuntajes.add(fondo);
    }

    /**
     * Prepara los elementos del Menu Datos Individual
     */
    private void prepareElementosDatosInd() {
        //PANEL
        menuDatos = new JPanel();
        menuDatos.setLayout(null);
        mainPanel.add(menuDatos,"MenuDatos");
        //TITULO
        tituloIcon = new JLabel();
        tituloIcon.setIcon(new ImageIcon("./images/modoInd.gif"));
        tituloIcon.setBounds((getWidth()/2)-155,35,300,140);
        tituloIcon.setHorizontalAlignment(JLabel.CENTER);
        tituloIcon.setVerticalAlignment(JLabel.CENTER);
        menuDatos.add(tituloIcon);
        labelDatos.add(tituloIcon);
        //VOLVER
        volverDatos = new JButton();
        volverDatos.setIcon(new ImageIcon("./images/back.png"));
        volverDatos.setBounds(20,10,100,40);
        volverDatos.setFocusPainted(false);
        volverDatos.setBorderPainted(false);
        volverDatos.setContentAreaFilled(false);
        menuDatos.add(volverDatos);
        botonesDatos.add(volverDatos);
        //NOMBRE
        textoDigite = new JLabel();
        textoDigite.setIcon(new ImageIcon("./images/login.png"));
        textoDigite.setBounds((getWidth()/2)-112,200,230,40);
        menuDatos.add(textoDigite);
        labelDatos.add(textoDigite);
        //DIGITAR NOMBRE
        campoNombre = new JTextField("Player");
        campoNombre.setHorizontalAlignment(JTextField.CENTER);
        campoNombre.setBounds((getWidth()/2)-112,240,220,40);
        campoNombre.setFont(customFont(22));
        campoNombre.setForeground(new Color(0x00FF00));
        campoNombre.setBackground(Color.BLACK);
        campoNombre.setBorder(borde);
        menuDatos.add(campoNombre);
        //COLOR
        colorInd = new JButton("Color Fondo");
        colorInd.setBackground(Color.magenta);
        colorInd.setFont(customFont(22));
        colorInd.setForeground(Color.black);
        colorInd.setBounds((getWidth()/2)-112,280,220,40);
        menuDatos.add(colorInd);
        botonesDatos.add(colorInd);
        //SIGUIENTE
        siguienteDatos = new JButton();
        siguienteDatos.setIcon(new ImageIcon("./images/start.png"));
        siguienteDatos.setBounds((getWidth()/2)-45,340,90,60);
        siguienteDatos.setFocusPainted(false);
        siguienteDatos.setBorderPainted(false);
        siguienteDatos.setContentAreaFilled(false);
        botonesDatos.add(siguienteDatos);
        menuDatos.add(siguienteDatos);
        //FONDO MENU
        ImageIcon imagenFondo = new ImageIcon("./images/fondodata.png");
        JLabel fondo = new JLabel();
        fondo.setBounds(0,0,WIDTH, HEIGHT);
        fondo.setIcon(imagenFondo);
        menuDatos.add(fondo);
    }

    /**
     * Prepara los elementos del Menu Datos Multijugador
     */
    private void prepareElementosDatosMulti() {
        //PANEL
        menuDatosMulti = new JPanel();
        menuDatosMulti.setLayout(null);
        mainPanel.add(menuDatosMulti,"MenuDatosMulti");
        //TITULO
        tituloIconMulti = new JLabel();
        tituloIconMulti.setIcon(new ImageIcon("./images/menuMul.gif"));
        tituloIconMulti.setBounds((getWidth()/2)-155,35,300,140);
        tituloIconMulti.setHorizontalAlignment(JLabel.CENTER);
        tituloIconMulti.setVerticalAlignment(JLabel.CENTER);
        menuDatosMulti.add(tituloIconMulti);
        labelDatosMulti.add(tituloIconMulti);
        //VOLVER
        volverDatosMulti = new JButton();
        volverDatosMulti.setIcon(new ImageIcon("./images/back.png"));
        volverDatosMulti.setBounds(20,10,100,40);
        volverDatosMulti.setFocusPainted(false);
        volverDatosMulti.setBorderPainted(false);
        volverDatosMulti.setContentAreaFilled(false);
        menuDatosMulti.add(volverDatosMulti);
        botonesDatosMulti.add(volverDatosMulti);
        //NOMBRE 1
        textoDigiteMulti1 = new JLabel();
        textoDigiteMulti1.setIcon(new ImageIcon("./images/login.png"));
        textoDigiteMulti1.setBounds((getWidth()/4)-112,200,230,40);
        textoDigiteMulti1.setHorizontalAlignment(JButton.LEFT);
        textoDigiteMulti1.setVerticalAlignment(JButton.CENTER);
        menuDatosMulti.add(textoDigiteMulti1);
        labelDatosMulti.add(textoDigiteMulti1);
        //NOMBRE 2
        textoDigiteMulti2 = new JLabel();
        textoDigiteMulti2.setIcon(new ImageIcon("./images/login.png"));
        textoDigiteMulti2.setBounds((3*getWidth()/4)-112,200,230,40);
        textoDigiteMulti2.setHorizontalAlignment(JButton.LEFT);
        textoDigiteMulti2.setVerticalAlignment(JButton.CENTER);
        menuDatosMulti.add(textoDigiteMulti2);
        labelDatosMulti.add(textoDigiteMulti2);
        //DIGITAR NOMBRE 1
        campoNombreMulti1 = new JTextField("Player 1");
        campoNombreMulti1.setHorizontalAlignment(JTextField.CENTER);
        campoNombreMulti1.setBounds((getWidth()/4)-112,240,220,40);
        campoNombreMulti1.setFont(customFont(22));
        campoNombreMulti1.setForeground(new Color(0x00FF00));
        campoNombreMulti1.setBorder(borde);
        campoNombreMulti1.setBackground(Color.BLACK);
        menuDatosMulti.add(campoNombreMulti1);
        //COLOR 1
        colorMul1 = new JButton("Color Fondo 1");
        colorMul1.setBackground(Color.green);
        colorMul1.setFont(customFont(22));
        colorMul1.setForeground(Color.blue);
        colorMul1.setBounds((getWidth()/4)-112,280,220,40);
        menuDatosMulti.add(colorMul1);
        botonesDatosMulti.add(colorMul1);
        //DIGITAR NOMBRE 2
        campoNombreMulti2 = new JTextField("Player 2");
        campoNombreMulti2.setHorizontalAlignment(JTextField.CENTER);
        campoNombreMulti2.setBounds((3*getWidth()/4)-112,240,220,40);
        campoNombreMulti2.setFont(customFont(22));
        campoNombreMulti2.setForeground(new Color(0x00FF00));
        campoNombreMulti2.setBorder(borde);
        campoNombreMulti2.setBackground(Color.BLACK);
        menuDatosMulti.add(campoNombreMulti2);
        //COLOR 2
        colorMul2 = new JButton("Color Fondo 2");
        colorMul2.setBackground(Color.green);
        colorMul2.setFont(customFont(22));
        colorMul2.setForeground(Color.blue);
        colorMul2.setBounds((3*getWidth()/4)-112,280,220,40);
        menuDatosMulti.add(colorMul2);
        botonesDatosMulti.add(colorMul2);
        //SIGUIENTE
        siguienteDatosMulti = new JButton();
        siguienteDatosMulti.setIcon(new ImageIcon("./images/start.png"));
        siguienteDatosMulti.setBounds((getWidth()/2)-45,340,90,60);
        siguienteDatosMulti.setFocusPainted(false);
        siguienteDatosMulti.setBorderPainted(false);
        siguienteDatosMulti.setContentAreaFilled(false);
        botonesDatosMulti.add(siguienteDatosMulti);
        menuDatosMulti.add(siguienteDatosMulti);
        //FONDO MENU
        ImageIcon imagenFondo = new ImageIcon("./images/fondodata.png");
        JLabel fondo = new JLabel();
        fondo.setBounds(0,0,WIDTH, HEIGHT);
        fondo.setIcon(imagenFondo);
        menuDatosMulti.add(fondo);
    }

    /**
     * Prepara los elementos del Menu Datos Maquiana
     */
    private void prepareElementosDatosMaqui() {
        //PANEL
        menuDatosMaqui = new JPanel();
        menuDatosMaqui.setLayout(null);
        mainPanel.add(menuDatosMaqui,"MenuDatosMaqui");
        //TITULO
        tituloIconMaqui = new JLabel();
        tituloIconMaqui.setIcon(new ImageIcon("./images/menuCon.gif"));
        tituloIconMaqui.setBounds((getWidth()/2)-155,35,300,140);
        tituloIconMaqui.setHorizontalAlignment(JLabel.CENTER);
        tituloIconMaqui.setVerticalAlignment(JLabel.CENTER);
        menuDatosMaqui.add(tituloIconMaqui);
        labelDatosMaqui.add(tituloIconMaqui);
        //VOLVER
        volverDatosMaqui = new JButton();
        volverDatosMaqui.setIcon(new ImageIcon("./images/back.png"));
        volverDatosMaqui.setBounds(20,10,100,40);
        volverDatosMaqui.setFocusPainted(false);
        volverDatosMaqui.setBorderPainted(false);
        volverDatosMaqui.setContentAreaFilled(false);
        menuDatosMaqui.add(volverDatosMaqui);
        botonesDatosMaqui.add(volverDatosMaqui);
        //NOMBRE 1
        textoDigiteMaqui1 = new JLabel();
        textoDigiteMaqui1.setIcon(new ImageIcon("./images/login.png"));
        textoDigiteMaqui1.setBounds((getWidth()/4)-112,200,230,40);
        textoDigiteMaqui1.setHorizontalAlignment(JButton.LEFT);
        textoDigiteMaqui1.setVerticalAlignment(JButton.CENTER);
        menuDatosMaqui.add(textoDigiteMaqui1);
        labelDatosMaqui.add(textoDigiteMaqui1);
        //NOMBRE 2
        textoDigiteMaqui2 = new JLabel();
        textoDigiteMaqui2.setIcon(new ImageIcon("./images/login.png"));
        textoDigiteMaqui2.setBounds((3*getWidth()/4)-112,200,230,40);
        textoDigiteMaqui2.setHorizontalAlignment(JButton.LEFT);
        textoDigiteMaqui2.setVerticalAlignment(JButton.CENTER);
        menuDatosMaqui.add(textoDigiteMaqui2);
        labelDatosMaqui.add(textoDigiteMaqui2);
        //DIGITAR NOMBRE 1
        campoNombreMaqui1 = new JTextField("IRMA-NEITOR");
        campoNombreMaqui1.setHorizontalAlignment(JTextField.CENTER);
        campoNombreMaqui1.setBounds((getWidth()/4)-112,240,220,40);
        campoNombreMaqui1.setFont(customFont(22));
        campoNombreMaqui1.setForeground(new Color(0x00FF00));
        campoNombreMaqui1.setBorder(borde);
        campoNombreMaqui1.setBackground(Color.BLACK);
        menuDatosMaqui.add(campoNombreMaqui1);
        //COLOR 1
        colorMaq1 = new JButton("Color Fondo 1");
        colorMaq1.setBackground(Color.red);
        colorMaq1.setFont(customFont(22));
        colorMaq1.setForeground(Color.black);
        colorMaq1.setBounds((getWidth()/4)-112,280,220,40);
        menuDatosMaqui.add(colorMaq1);
        botonesDatosMaqui.add(colorMaq1);
        //DIGITAR NOMBRE 2
        campoNombreMaqui2 = new JTextField("Player");
        campoNombreMaqui2.setHorizontalAlignment(JTextField.CENTER);
        campoNombreMaqui2.setBounds((3*getWidth()/4)-112,240,220,40);
        campoNombreMaqui2.setFont(customFont(22));
        campoNombreMaqui2.setForeground(new Color(0x00FF00));
        campoNombreMaqui2.setBorder(borde);
        campoNombreMaqui2.setBackground(Color.BLACK);
        menuDatosMaqui.add(campoNombreMaqui2);
        //COLOR 2
        colorMaq2 = new JButton("Color Fondo 2");
        colorMaq2.setBackground(Color.red);
        colorMaq2.setFont(customFont(22));
        colorMaq2.setForeground(Color.black);
        colorMaq2.setBounds((3*getWidth()/4)-112,280,220,40);
        menuDatosMaqui.add(colorMaq2);
        botonesDatosMaqui.add(colorMaq2);
        //SIGUIENTE
        siguienteDatosMaqui = new JButton();
        siguienteDatosMaqui.setIcon(new ImageIcon("./images/start.png"));
        siguienteDatosMaqui.setBounds((getWidth()/2)-45,340,90,60);
        siguienteDatosMaqui.setFocusPainted(false);
        siguienteDatosMaqui.setBorderPainted(false);
        siguienteDatosMaqui.setContentAreaFilled(false);
        botonesDatosMaqui.add(siguienteDatosMaqui);
        menuDatosMaqui.add(siguienteDatosMaqui);
        //FONDO MENU
        ImageIcon imagenFondo = new ImageIcon("./images/fondodata.png");
        JLabel fondo = new JLabel();
        fondo.setBounds(0,0,WIDTH, HEIGHT);
        fondo.setIcon(imagenFondo);
        menuDatosMaqui.add(fondo);
    }

    /**
     * Prepara los elementos del Menu Game Data
     */
    private void prepareElementosGameData() {
        //PANEL
        menuVelocidad = new JPanel();
        menuVelocidad.setLayout(null);
        mainPanel.add(menuVelocidad,"MenuVelocidad");
        //VOLVER
        volverGameData = new JButton();
        volverGameData.setIcon(new ImageIcon("./images/back.png"));
        volverGameData.setBounds(20,10,100,40);
        volverGameData.setFocusPainted(false);
        volverGameData.setBorderPainted(false);
        volverGameData.setContentAreaFilled(false);
        menuVelocidad.add(volverGameData);
        botonesSpeed.add(volverGameData);
        //TITULO
        tituloConfirmar = new JLabel();
        tituloConfirmar.setIcon(new ImageIcon("./images/moodSpeed.gif"));
        tituloConfirmar.setBounds((getWidth()/2)-155,10,300,160);
        tituloConfirmar.setHorizontalAlignment(JLabel.CENTER);
        tituloConfirmar.setVerticalAlignment(JLabel.CENTER);
        menuVelocidad.add(tituloConfirmar);
        //BOTON SLOW
        velSlow = new JButton(" SLOW ");
        velSlow.setBackground(Color.red);
        velSlow.setBounds(this.getWidth()/2-220,180,160,70);
        velSlow.setBorder(borderRed);
        velSlow.setOpaque(false);
        velSlow.setForeground(Color.green);
        velSlow.setHorizontalTextPosition(SwingConstants.CENTER);
        velSlow.setVerticalTextPosition(SwingConstants.CENTER);
        menuVelocidad.add(velSlow);
        botonesSpeed.add(velSlow);
        //BOTON FAST
        velSpeed = new JButton("FAST");
        velSpeed.setBackground(Color.green);
        velSpeed.setBounds(this.getWidth()/2+30,180,160,70);
        velSpeed.setBorder(borderRed);
        velSpeed.setOpaque(false);
        velSpeed.setForeground(Color.white);
        velSpeed.setHorizontalTextPosition(SwingConstants.CENTER);
        velSpeed.setVerticalTextPosition(SwingConstants.CENTER);
        menuVelocidad.add(velSpeed);
        botonesSpeed.add(velSpeed);
        //TITULO VELOCIDAD
        tituloVelocidad = new JLabel();
        tituloVelocidad.setIcon(new ImageIcon("./images/velocity.gif"));
        tituloVelocidad.setBounds((getWidth()/2)-250,280,200,40);
        tituloVelocidad.setHorizontalAlignment(JLabel.CENTER);
        tituloVelocidad.setVerticalAlignment(JLabel.CENTER);
        menuVelocidad.add(tituloVelocidad);
        //VELOCIDAD INCIAL
        velInicial = new JTextField("1000");
        velInicial.setHorizontalAlignment(JTextField.CENTER);
        velInicial.setBounds((getWidth()/2)-250,320,200,40);
        velInicial.setFont(customFont(22));
        velInicial.setForeground(new Color(0x00FF00));
        velInicial.setBackground(Color.BLACK);
        velInicial.setBorder(borde);
        menuVelocidad.add(velInicial);
        //TITULO BUFFOS
        tituloBuffos = new JLabel();
        tituloBuffos.setIcon(new ImageIcon("./images/buffos.gif"));
        tituloBuffos.setBounds((getWidth()/2)+25,280,200,40);
        tituloBuffos.setHorizontalAlignment(JLabel.CENTER);
        tituloBuffos.setVerticalAlignment(JLabel.CENTER);
        menuVelocidad.add(tituloBuffos);
        //NUMERO BUFFOS
        numBuffos = new JTextField("1000");
        numBuffos.setHorizontalAlignment(JTextField.CENTER);
        numBuffos.setBounds((getWidth()/2)+25,320,200,40);
        numBuffos.setFont(customFont(22));
        numBuffos.setForeground(new Color(0x00FF00));
        numBuffos.setBackground(Color.BLACK);
        numBuffos.setBorder(borde);
        menuVelocidad.add(numBuffos);
        //SIGUIENTE
        continueGame = new JButton();
        continueGame.setIcon(new ImageIcon("./images/startgif.gif"));
        continueGame.setBounds((getWidth()/2)-154,400,308,57);
        continueGame.setFocusPainted(false);
        continueGame.setBorderPainted(false);
        continueGame.setContentAreaFilled(false);
        botonesSpeed.add(continueGame);
        menuVelocidad.add(continueGame);
        //FONDO MENU
        ImageIcon imagenFondo = new ImageIcon("./images/fondodata.png");
        JLabel fondo = new JLabel();
        fondo.setBounds(0,0,WIDTH, HEIGHT);
        fondo.setIcon(imagenFondo);
        menuVelocidad.add(fondo);
    }

    /**
     * Prepara los elementos del Menu Historial
     */
    private void prepareElementosHistorial() {
        //PANEL HISTORIAL
        panelHistorial = new JPanel();
        panelHistorial.setLayout(null);
        mainPanel.add(panelHistorial,"MenuHistorial");
        //VOLVER
        volverHistorial = new JButton();
        volverHistorial.setIcon(new ImageIcon("./images/back.png"));
        volverHistorial.setBounds(20,10,100,40);
        volverHistorial.setFocusPainted(false);
        volverHistorial.setBorderPainted(false);
        volverHistorial.setContentAreaFilled(false);
        panelHistorial.add(volverHistorial);
        botonesHistorial.add(volverHistorial);
        //TITULO
        tituloHistorial = new JLabel();
        tituloHistorial.setIcon(new ImageIcon("./images/users.png"));
        tituloHistorial.setBounds((this.getWidth()/2)-115,40,230,50);
        tituloHistorial.setHorizontalAlignment(JButton.LEFT);
        tituloHistorial.setVerticalAlignment(JButton.CENTER);
        panelHistorial.add(tituloHistorial);
        //VER
        verHistorial = new JButton();
        verHistorial.setIcon(new ImageIcon("./images/ver.png"));
        verHistorial.setBounds((this.getWidth()/2)-45,420,90,60);
        verHistorial.setFocusPainted(false);
        verHistorial.setBorderPainted(false);
        verHistorial.setContentAreaFilled(false);
        verHistorial.setHorizontalTextPosition(JButton.CENTER);
        verHistorial.setVerticalTextPosition(JButton.BOTTOM);
        botonesHistorial.add(verHistorial);
        panelHistorial.add(verHistorial);
        //LISTA
        listaJugadores = new JList<String>();
        listaJugadores.setBackground(Color.BLACK);
        listaJugadores.setBorder(borde);
        listaJugadores.setForeground(Color.white);
        listaJugadores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        modelo = new DefaultListModel<>();
        scrollLista = new JScrollPane();
        scrollLista.setViewportView(listaJugadores);
        scrollLista.setBounds((this.getWidth()/2)-200, 100,400, 320);
        listaJugadores.setModel(modelo);
        panelHistorial.add(scrollLista);
        //FONDO MENU
        ImageIcon imagenFondo = new ImageIcon("./images/fondogif2.gif");
        JLabel fondo = new JLabel();
        fondo.setBounds(0,0,WIDTH, HEIGHT);
        fondo.setIcon(imagenFondo);
        panelHistorial.add(fondo);
    }

    /**
     * Prepara los elementos del Menu Usuario
     */
    private void prepareElementosUser() {
        //PANEL HISTORIAL
        panelUser = new JPanel();
        panelUser.setLayout(null);
        mainPanel.add(panelUser,"MenuUser");
        //VOLVER
        volverUser = new JButton();
        volverUser.setIcon(new ImageIcon("./images/back.png"));
        volverUser.setBounds(20,10,100,40);
        volverUser.setFocusPainted(false);
        volverUser.setBorderPainted(false);
        volverUser.setContentAreaFilled(false);
        panelUser.add(volverUser);
        botonesUser.add(volverUser);
        //TITULO
        tituloUser = new JLabel();
        tituloUser.setIcon(new ImageIcon("./images/login.png"));
        tituloUser.setBounds((this.getWidth()/2)-115,40,230,50);
        tituloUser.setHorizontalAlignment(JButton.LEFT);
        tituloUser.setVerticalAlignment(JButton.CENTER);
        panelUser.add(tituloUser);
        //NOMBRE
        nombreUser = new JLabel();
        nombreUser.setText(" NOMBRE ");
        nombreUser.setBounds((this.getWidth()/2)-115,90,220,50);
        nombreUser.setHorizontalAlignment(JButton.CENTER);
        nombreUser.setVerticalAlignment(JButton.CENTER);
        nombreUser.setFont(customFont(22));
        nombreUser.setForeground(new Color(0x00FF00));
        nombreUser.setBackground(Color.BLACK);
        nombreUser.setBorder(borde);
        panelUser.add(nombreUser);
        //LISTA
        listaUser = new JList<String>();
        listaUser.setBackground(Color.BLACK);
        listaUser.setBorder(borde);
        listaUser.setForeground(Color.white);
        listaUser.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        modeloUser = new DefaultListModel<>();
        scrollListaUser = new JScrollPane();
        scrollListaUser.setViewportView(listaUser);
        scrollListaUser.setBounds((this.getWidth()/2)-200, 150,400, 320);
        listaUser.setModel(modeloUser);
        panelUser.add(scrollListaUser);
        //FONDO MENU
        ImageIcon imagenFondo = new ImageIcon("./images/fondogif2.gif");
        JLabel fondo = new JLabel();
        fondo.setBounds(0,0,WIDTH, HEIGHT);
        fondo.setIcon(imagenFondo);
        panelUser.add(fondo);
    }

    /**
     * Prepara los elementos del Menu Modo Maquina
     */
    public void prepareElementosModoMaquina() {
        //PANEL MODO MAQUINA
        menuModoMaq = new JPanel();
        menuModoMaq.setLayout(null);
        mainPanel.add(menuModoMaq, "menuModMaq");
        //PRINCIPIANTE
        modoMaqPri = new JButton("PRINCIPIANTE");
        modoMaqPri.setBackground(Color.blue);
        modoMaqPri.setBounds((WIDTH+12)/2-220,220,160,70);
        modoMaqPri.setBorder(borderRed);
        modoMaqPri.setOpaque(false);
        modoMaqPri.setForeground(Color.blue);
        modoMaqPri.setHorizontalTextPosition(SwingConstants.CENTER);
        modoMaqPri.setVerticalTextPosition(SwingConstants.CENTER);
        menuModoMaq.add(modoMaqPri);
        //EXPERTO
        modoMaqExp = new JButton("EXPERTO");
        modoMaqExp.setBackground(Color.red);
        modoMaqExp.setBounds((WIDTH+12)/2+30,220,160,70);
        modoMaqExp.setBorder(borderRed);
        modoMaqExp.setOpaque(false);
        modoMaqExp.setForeground(Color.red);
        modoMaqExp.setHorizontalTextPosition(SwingConstants.CENTER);
        modoMaqExp.setVerticalTextPosition(SwingConstants.CENTER);
        menuModoMaq.add(modoMaqExp);
        //TITULO
        tituloModoMaq = new JLabel();
        tituloModoMaq.setText("           ELIGE UN MODO DE MAQUINA");
        tituloModoMaq.setBorder(borde);
        tituloModoMaq.setBounds((WIDTH+12)/2 - 125,100,250,50);
        tituloModoMaq.setForeground(Color.white);
        menuModoMaq.add(tituloModoMaq);
        //FONDO MENU
        ImageIcon imagenFondo = new ImageIcon("./images/fondodataMaq.png");
        JLabel fondo = new JLabel();
        fondo.setBounds(0,0,WIDTH, HEIGHT);
        fondo.setIcon(imagenFondo);
        menuModoMaq.add(fondo);
    }

    /**
     * Prepara Acciones de la clase
     */
    private void prepareAcciones() {
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ev) {
                getOut();
            }
        });
        prepareAccionesMenu();
        prepareAccionesGetOut();
        prepareAccionesNewGame();
        prepareAccionesModoInd();
        prepareAccionesConfiguracion();
        prepareAccionesControles();
        prepareAccionesPuntajes();
        prepareAccionesInstruccion();
        prepareAccionesModoMulti();
        prepareAccionesModoMaqui();
        prepareAccionesGameData();
        prepareAccionesHistorial();
        prepareAccionesUser();
        prepareAccionesModoMaq();
    }

    /**
     * Prepara Acciones del Menu de Inicio
     */
    private void prepareAccionesMenu() {
        MouseListener mouseListenerMenu = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource().equals(loadGame)){
                    loadGame.setIcon(new ImageIcon("./images/save2.png"));
                }
                else if(e.getSource().equals(configuracion)){
                    configuracion.setIcon(new ImageIcon("./images/configuracion2.png"));
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getSource().equals(loadGame)){
                    loadGame.setIcon(new ImageIcon("./images/save.png"));
                    try {
                        load();
                    } catch (TetrisException ex) {
                        ex.printStackTrace();
                    }
                }
                else if(e.getSource().equals(configuracion)){
                    configuracion.setIcon(new ImageIcon("./images/configuracion.png"));
                    config();
                }
                else if(e.getSource().equals(newGame)){
                    newGame.setBounds(WIDTH/2-68,190,130,40);
                    newGame();
                }
                else if(e.getSource().equals(getOut)){
                    getOut.setBounds(WIDTH/2-68,330,130,40);
                    getOut();
                }
                else if(e.getSource().equals(highScores)){
                    highScores.setBounds(WIDTH/2-68,260,130,40);
                    highScores();
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getSource().equals(loadGame)){
                    loadGame.setIcon(new ImageIcon("./images/save.png"));
                }
                else if(e.getSource().equals(configuracion)){
                    configuracion.setIcon(new ImageIcon("./images/configuracion.png"));
                }
                else if(e.getSource().equals(newGame)){
                    newGame.setBounds((WIDTH+12)/2-68,190,130,40);
                }
                else if(e.getSource().equals(getOut)){
                    getOut.setBounds((WIDTH+12)/2-68,330,130,40);
                }
                else if(e.getSource().equals(highScores)){
                    highScores.setBounds((WIDTH+12)/2-68,260,130,40);
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if(e.getSource().equals(loadGame)){
                    loadGame.setIcon(new ImageIcon("./images/save2.png"));
                }
                else if(e.getSource().equals(configuracion)){
                    configuracion.setIcon(new ImageIcon("./images/configuracion2.png"));
                }
                else if(e.getSource().equals(newGame)){
                    newGame.setBounds(WIDTH/2-67,185,140,50);;
                }
                else if(e.getSource().equals(getOut)){
                    getOut.setBounds(WIDTH/2-67,325,140,50);
                }
                else if(e.getSource().equals(highScores)){
                    highScores.setBounds(WIDTH/2-67,255,140,50);
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if(e.getSource().equals(loadGame)){
                    loadGame.setIcon(new ImageIcon("./images/save.png"));
                }
                else if(e.getSource().equals(configuracion)){
                    configuracion.setIcon(new ImageIcon("./images/configuracion.png"));
                }
                else if(e.getSource().equals(newGame)){
                    newGame.setBounds((WIDTH+12)/2-68,190,130,40);
                }
                else if(e.getSource().equals(getOut)){
                    getOut.setBounds((WIDTH+12)/2-68,330,130,40);
                }
                else if(e.getSource().equals(highScores)){
                    highScores.setBounds((WIDTH+12)/2-68,260,130,40);
                }
            }
        };
        for (JButton boton: botonesMenu) {
            boton.addMouseListener(mouseListenerMenu);
        }
    }

    /**
     * Prepara Acciones del Menu Get Out
     */
    private void prepareAccionesGetOut() {
        MouseListener mouseListenerMenuGetOut = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getSource().equals(getOutAceptar)){
                    getOutAceptar.setBounds(WIDTH/2-210,220,150,70);
                    try {
                        salir();
                    } catch (TetrisException ex) {
                        ex.printStackTrace();
                    }
                }
                else if(e.getSource().equals(getOutCancelar)){
                    getOutCancelar.setBounds(WIDTH/2+40,220,150,70);
                    volverMenu();
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getSource().equals(getOutAceptar)){
                    getOutAceptar.setBounds((WIDTH+12)/2-230,210,170,90);
                }
                else if(e.getSource().equals(getOutCancelar)){
                    getOutCancelar.setBounds((WIDTH+12)/2+30,220,160,70);
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if(e.getSource().equals(getOutAceptar)){
                    getOutAceptar.setBounds(WIDTH/2-220,210,170,90);
                }
                else if(e.getSource().equals(getOutCancelar)){
                    getOutCancelar.setBounds(WIDTH/2+30,210,170,90);
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if(e.getSource().equals(getOutAceptar)){
                    getOutAceptar.setBounds((WIDTH+12)/2-210,220,150,70);
                }
                else if(e.getSource().equals(getOutCancelar)){
                    getOutCancelar.setBounds((WIDTH+12)/2+40,220,150,70);
                }
            }
        };
        for (JButton boton: salirGame) {
            boton.addMouseListener(mouseListenerMenuGetOut);
        }
    }

    /**
     * Prepara Acciones del Menu Configuracion
     */
    private void prepareAccionesConfiguracion(){
        MouseListener mouseListenerConfiguracion = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource().equals(atrasConfig)){
                    atrasConfig.setIcon(new ImageIcon("./images/back2.png"));
                }
                if(e.getSource().equals(musica)){
                    controlMusica();
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getSource().equals(atrasConfig)){
                    atrasConfig.setIcon(new ImageIcon("./images/back.png"));
                    volverMenu();
                }
                if(e.getSource().equals(cambiarColor)){
                    cambiarColor.setBounds((WIDTH+12)/2-75,80,150,50);
                    cambiarColorFondo("0","0");
                }
                if(e.getSource().equals(controles)){
                    controles.setBounds((WIDTH+12)/2-75,160,150,50);
                    abrirControles();
                }
                if(e.getSource().equals(instrucciones)){
                    instrucciones.setBounds((WIDTH+12)/2-75,240,150,50);
                    abrirInstruccion();
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getSource().equals(atrasConfig)){
                    atrasConfig.setIcon(new ImageIcon("./images/back.png"));
                }
                if(e.getSource().equals(cambiarColor)){
                    cambiarColor.setBounds((WIDTH+12)/2-75,80,150,50);
                }
                if(e.getSource().equals(controles)){
                    controles.setBounds((WIDTH+12)/2-75,160,150,50);
                }
                if(e.getSource().equals(instrucciones)){
                    instrucciones.setBounds((WIDTH+12)/2-75,240,150,50);
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if(e.getSource().equals(atrasConfig)){
                    atrasConfig.setIcon(new ImageIcon("./images/back2.png"));
                }
                if(e.getSource().equals(cambiarColor)){
                    cambiarColor.setBounds((WIDTH+12)/2-85,70,170,70);
                }
                if(e.getSource().equals(controles)){
                    controles.setBounds((WIDTH+12)/2-85,150,170,70);
                }
                if(e.getSource().equals(instrucciones)){
                    instrucciones.setBounds((WIDTH+12)/2-85,230,170,70);
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if(e.getSource().equals(atrasConfig)){
                    atrasConfig.setIcon(new ImageIcon("./images/back.png"));
                }
                if(e.getSource().equals(cambiarColor)){
                    cambiarColor.setBounds((WIDTH+12)/2-75,80,150,50);
                }
                if(e.getSource().equals(controles)){
                    controles.setBounds((WIDTH+12)/2-75,160,150,50);
                }
                if(e.getSource().equals(instrucciones)){
                    instrucciones.setBounds((WIDTH+12)/2-75,240,150,50);
                }
            }
        };
        for (JButton boton: botonesConfi) {
            boton.addMouseListener(mouseListenerConfiguracion);
        }
        for (JCheckBox boton: botonesmusica) {
            boton.addMouseListener(mouseListenerConfiguracion);
        }
    }

    /**
     * Prepara Acciones del Menu Controles
     */
    private void prepareAccionesControles(){
        MouseListener mouseListenerControles = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource().equals(atrasConfig2)){
                    config();
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getSource().equals(atrasConfig2)){
                    atrasConfig2.setIcon(new ImageIcon("./images/back.png"));
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getSource().equals(atrasConfig2)){
                    atrasConfig2.setIcon(new ImageIcon("./images/back.png"));
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if(e.getSource().equals(atrasConfig2)){
                    atrasConfig2.setIcon(new ImageIcon("./images/back2.png"));
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if(e.getSource().equals(atrasConfig2)){
                    atrasConfig2.setIcon(new ImageIcon("./images/back.png"));
                }
            }
        };
        for (JButton boton: botonesControles) {
            boton.addMouseListener(mouseListenerControles);
        }
    }

    /**
     * Prepara Acciones del Menu Instruccion
     */
    private void prepareAccionesInstruccion(){
        MouseListener mouseListenerInstruccion = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource().equals(atrasConfig3)){
                    config();
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getSource().equals(atrasConfig3)){
                    atrasConfig3.setIcon(new ImageIcon("./images/back.png"));
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getSource().equals(atrasConfig3)){
                    atrasConfig3.setIcon(new ImageIcon("./images/back.png"));
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if(e.getSource().equals(atrasConfig3)){
                    atrasConfig3.setIcon(new ImageIcon("./images/back2.png"));
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if(e.getSource().equals(atrasConfig3)){
                    atrasConfig3.setIcon(new ImageIcon("./images/back.png"));
                }
            }
        };
        for (JButton boton: botonesInstruccion) {
            boton.addMouseListener(mouseListenerInstruccion);
        }
    }

    /**
     * Prepara Acciones del Menu New Game
     */
    public void prepareAccionesNewGame() {
        MouseListener mouseListenerNewGame= new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource().equals(individual)){
                    individual.setBounds(WIDTH/2-80,60,160,110);
                    datosInd();
                }
                else if(e.getSource().equals(multijugador)) {
                    multijugador.setBounds((WIDTH)/2-80,180,160,110);
                    datosMulti();
                }
                else if(e.getSource().equals(contraMaquina)){
                    contraMaquina.setBounds((WIDTH)/2-80,300,160,110);
                    datosMaqui();
                }
                else if(e.getSource().equals(volverMenu)){
                    volverMenu();
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getSource().equals(individual)){
                    individual.setBounds(WIDTH/2-80,60,160,110);
                }
                else if(e.getSource().equals(multijugador)) {
                    multijugador.setBounds((WIDTH)/2-80,180,160,110);
                }
                else if(e.getSource().equals(contraMaquina)){
                    contraMaquina.setBounds((WIDTH)/2-80,300,160,110);
                }
                else if(e.getSource().equals(volverMenu)){
                    volverMenu.setIcon(new ImageIcon("./images/back.png"));
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getSource().equals(individual)){
                    individual.setBounds((WIDTH+12)/2-80,60,160,110);
                }
                else if(e.getSource().equals(multijugador)) {
                    multijugador.setBounds((WIDTH+12)/2-100,180,200,115);
                }
                else if(e.getSource().equals(contraMaquina)){
                    contraMaquina.setBounds((WIDTH+12)/2-100,300,200,115);
                }
                else if(e.getSource().equals(volverMenu)){
                    volverMenu.setIcon(new ImageIcon("./images/back2.png"));
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if(e.getSource().equals(individual)){
                    individual.setBounds((WIDTH+10)/2-100,60,200,115);
                }
                else if(e.getSource().equals(multijugador)) {
                    multijugador.setBounds((WIDTH+10)/2-100,180,200,115);
                }
                else if(e.getSource().equals(contraMaquina)){
                    contraMaquina.setBounds((WIDTH+10)/2-100,300,200,115);
                }
                else if(e.getSource().equals(volverMenu)){
                    volverMenu.setIcon(new ImageIcon("./images/back2.png"));
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if(e.getSource().equals(individual)){
                    individual.setBounds((WIDTH+12)/2-80,60,160,110);
                }
                else if(e.getSource().equals(multijugador)) {
                    multijugador.setBounds((WIDTH+12)/2-80,180,160,110);
                }
                else if(e.getSource().equals(contraMaquina)){
                    contraMaquina.setBounds((WIDTH+12)/2-80,300,160,110);
                }
                else if(e.getSource().equals(volverMenu)){
                    volverMenu.setIcon(new ImageIcon("./images/back.png"));
                }
            }
        };
        for (JButton boton: botonesNewGame) {
            boton.addMouseListener(mouseListenerNewGame);
        }
    }

    /**
     * Prepara Acciones del Menu Puntajes
     */
    private void prepareAccionesPuntajes(){
        MouseListener mouseListenerHighScores = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource().equals(atrasPuntajes)){
                    volverMenu();
                }
                if(e.getSource().equals(historial)){
                    Historial();
                }

            }
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getSource().equals(atrasPuntajes)){
                    atrasPuntajes.setIcon(new ImageIcon("./images/back.png"));
                }
                if(e.getSource().equals(historial)){
                    historial.setIcon(new ImageIcon("./images/record.png"));
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getSource().equals(atrasPuntajes)){
                    atrasPuntajes.setIcon(new ImageIcon("./images/back.png"));
                }
                if(e.getSource().equals(historial)){
                    historial.setIcon(new ImageIcon("./images/record.png"));
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if(e.getSource().equals(atrasPuntajes)){
                    atrasPuntajes.setIcon(new ImageIcon("./images/back2.png"));
                }
                if(e.getSource().equals(historial)){
                    historial.setIcon(new ImageIcon("./images/record2.png"));
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if(e.getSource().equals(atrasPuntajes)){
                    atrasPuntajes.setIcon(new ImageIcon("./images/back.png"));
                }
                if(e.getSource().equals(historial)){
                    historial.setIcon(new ImageIcon("./images/record.png"));
                }
            }
        };
        for (JButton boton: botonesHighScores) {
            boton.addMouseListener(mouseListenerHighScores);
        }
    }

    /**
     * Prepara Acciones del Menu Modo Individual
     */
    public void prepareAccionesModoInd() {
        MouseListener mouseListenerModos = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource().equals(volverDatos)){
                    newGame();
                }
                else if(e.getSource().equals(siguienteDatos)){
                    ModoIndividual();
                }
                else if(e.getSource().equals(colorInd)){
                    cambiarColorFondo("I","0");
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getSource().equals(volverDatos)){
                    volverDatos.setIcon(new ImageIcon("./images/back.png"));
                }
                else if(e.getSource().equals(siguienteDatos)){
                    siguienteDatos.setIcon(new ImageIcon("./images/start.png"));
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getSource().equals(volverDatos)){
                    volverDatos.setIcon(new ImageIcon("./images/back.png"));
                }
                else if(e.getSource().equals(siguienteDatos)){
                    siguienteDatos.setIcon(new ImageIcon("./images/start.png"));
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if(e.getSource().equals(volverDatos)){
                    volverDatos.setIcon(new ImageIcon("./images/back2.png"));
                }
                else if(e.getSource().equals(siguienteDatos)){
                    siguienteDatos.setIcon(new ImageIcon("./images/start2.png"));
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if(e.getSource().equals(volverDatos)){
                    volverDatos.setIcon(new ImageIcon("./images/back.png"));
                }
                else if(e.getSource().equals(siguienteDatos)){
                    siguienteDatos.setIcon(new ImageIcon("./images/start.png"));
                }
            }
        };
        for (JButton boton: botonesDatos) {
            boton.addMouseListener(mouseListenerModos);
        }
    }

    /**
     * Prepara Acciones del Menu Modo Multijugador
     */
    public void prepareAccionesModoMulti() {
        MouseListener mouseListenerModosMulti = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource().equals(volverDatosMulti)){
                    newGame();
                }
                else if(e.getSource().equals(siguienteDatosMulti)){
                    ModoMultijugador();
                }
                else if(e.getSource().equals(colorMul1)){
                    cambiarColorFondo("M","1");
                }
                else if(e.getSource().equals(colorMul2)){
                    cambiarColorFondo("M","2");
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getSource().equals(volverDatosMulti)){
                    volverDatosMulti.setIcon(new ImageIcon("./images/back.png"));
                }
                else if(e.getSource().equals(siguienteDatosMulti)){
                    siguienteDatosMulti.setIcon(new ImageIcon("./images/start.png"));
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getSource().equals(volverDatosMulti)){
                    volverDatosMulti.setIcon(new ImageIcon("./images/back.png"));
                }
                else if(e.getSource().equals(siguienteDatosMulti)){
                    siguienteDatosMulti.setIcon(new ImageIcon("./images/start.png"));
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if(e.getSource().equals(volverDatosMulti)){
                    volverDatosMulti.setIcon(new ImageIcon("./images/back2.png"));
                }
                else if(e.getSource().equals(siguienteDatosMulti)){
                    siguienteDatosMulti.setIcon(new ImageIcon("./images/start2.png"));
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if(e.getSource().equals(volverDatosMulti)){
                    volverDatosMulti.setIcon(new ImageIcon("./images/back.png"));
                }
                else if(e.getSource().equals(siguienteDatosMulti)){
                    siguienteDatosMulti.setIcon(new ImageIcon("./images/start.png"));
                }
            }
        };
        for (JButton boton: botonesDatosMulti) {
            boton.addMouseListener(mouseListenerModosMulti);
        }
    }

    /**
     * Prepara Acciones del Menu Modo Maquina
     */
    public void prepareAccionesModoMaqui() {
        MouseListener mouseListenerModosMaqui = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource().equals(volverDatosMaqui)){
                    newGame();
                }
                else if(e.getSource().equals(siguienteDatosMaqui)){
                    ModoContraMaquina();
                }
                else if(e.getSource().equals(colorMaq1)){
                    cambiarColorFondo("C","1");
                }
                else if(e.getSource().equals(colorMaq2)){
                    cambiarColorFondo("C","2");
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getSource().equals(volverDatosMaqui)){
                    volverDatosMaqui.setIcon(new ImageIcon("./images/back.png"));
                }
                else if(e.getSource().equals(siguienteDatosMulti)){
                    siguienteDatosMulti.setIcon(new ImageIcon("./images/start.png"));
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getSource().equals(volverDatosMaqui)){
                    volverDatosMaqui.setIcon(new ImageIcon("./images/back.png"));
                }
                else if(e.getSource().equals(siguienteDatosMaqui)){
                    siguienteDatosMaqui.setIcon(new ImageIcon("./images/start.png"));
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if(e.getSource().equals(volverDatosMaqui)){
                    volverDatosMaqui.setIcon(new ImageIcon("./images/back2.png"));
                }
                else if(e.getSource().equals(siguienteDatosMaqui)){
                    siguienteDatosMaqui.setIcon(new ImageIcon("./images/start2.png"));
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if(e.getSource().equals(volverDatosMaqui)){
                    volverDatosMaqui.setIcon(new ImageIcon("./images/back.png"));
                }
                else if(e.getSource().equals(siguienteDatosMaqui)){
                    siguienteDatosMaqui.setIcon(new ImageIcon("./images/start.png"));
                }
            }
        };
        for (JButton boton: botonesDatosMaqui) {
            boton.addMouseListener(mouseListenerModosMaqui);
        }
    }

    /**
     * Prepara Acciones del Menu Game Data
     */
    public void prepareAccionesGameData() {
        MouseListener mouseListenerSpeed = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource().equals(velSlow)){
                    velSlow.setForeground(Color.green);
                    velSpeed.setForeground(Color.white);
                    velSlow.setBounds((WIDTH+12)/2-220,180,160,70);
                }
                else if(e.getSource().equals(velSpeed)){
                    velSpeed.setForeground(Color.green);
                    velSlow.setForeground(Color.white);
                    velSpeed.setBounds((WIDTH+12)/2+30,180,160,70);
                }
                else if(e.getSource().equals(continueGame)){
                    loadGame();
                }
                else if(e.getSource().equals(volverGameData)){
                    dataModoGame();
                }

            }
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getSource().equals(velSlow)){
                    velSlow.setBounds((WIDTH+12)/2-220,180,160,70);
                }
                else if(e.getSource().equals(velSpeed)){
                    velSpeed.setBounds((WIDTH+12)/2+30,180,160,70);
                }
                if(e.getSource().equals(volverGameData)){
                    volverGameData.setIcon(new ImageIcon("./images/back.png"));
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getSource().equals(velSlow)){
                    velSlow.setBounds((WIDTH+12)/2-220,180,160,70);
                }
                else if(e.getSource().equals(velSpeed)){
                    velSpeed.setBounds((WIDTH+12)/2+30,180,160,70);
                }
                if(e.getSource().equals(volverGameData)){
                    volverGameData.setIcon(new ImageIcon("./images/back.png"));
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if(e.getSource().equals(velSlow)){
                    velSlow.setBounds((WIDTH+12)/2-230,170,180,90);
                }
                else if(e.getSource().equals(velSpeed)){
                    velSpeed.setBounds((WIDTH+12)/2+20,170,180,90);
                }
                if(e.getSource().equals(volverGameData)){
                    volverGameData.setIcon(new ImageIcon("./images/back2.png"));
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if(e.getSource().equals(velSlow)){
                    velSlow.setBounds((WIDTH+12)/2-220,180,160,70);
                }
                else if(e.getSource().equals(velSpeed)){
                    velSpeed.setBounds((WIDTH+12)/2+30,180,160,70);
                }
                if(e.getSource().equals(volverGameData)){
                    volverGameData.setIcon(new ImageIcon("./images/back.png"));
                }
            }
        };
        for (JButton boton: botonesSpeed) {
            boton.addMouseListener(mouseListenerSpeed);
        }
    }

    /**
     * Prepara Acciones del Menu Historial
     */
    public void prepareAccionesHistorial() {
        MouseListener mouseListenerHisorial = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource().equals(verHistorial)){
                    verHistorial.setBounds(((WIDTH+12)/2)-45,420,90,60);
                    if (listaJugadores.getSelectedIndex() != -1) {
                        historialUser();
                    }
                }
                else if(e.getSource().equals(volverHistorial)){
                    highScores();
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getSource().equals(verHistorial)){
                    verHistorial.setBounds(((WIDTH+12)/2)-45,420,90,60);
                    verHistorial.setIcon(new ImageIcon("./images/ver.png"));
                }
                if(e.getSource().equals(volverHistorial)){
                    volverHistorial.setIcon(new ImageIcon("./images/back.png"));
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getSource().equals(verHistorial)){
                    verHistorial.setBounds(((WIDTH+12)/2)-45,420,90,60);
                    verHistorial.setIcon(new ImageIcon("./images/ver.png"));
                }
                if(e.getSource().equals(volverHistorial)){
                    volverHistorial.setIcon(new ImageIcon("./images/back.png"));
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if(e.getSource().equals(verHistorial)){
                    verHistorial.setBounds(((WIDTH+12)/2)-55,410,110,80);
                    verHistorial.setIcon(new ImageIcon("./images/ver2.png"));
                }
                if(e.getSource().equals(volverHistorial)){
                    volverHistorial.setIcon(new ImageIcon("./images/back2.png"));
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if(e.getSource().equals(verHistorial)){
                    verHistorial.setBounds(((WIDTH+12)/2)-45,420,90,60);
                    verHistorial.setIcon(new ImageIcon("./images/ver.png"));
                }
                if(e.getSource().equals(volverHistorial)){
                    volverHistorial.setIcon(new ImageIcon("./images/back.png"));
                }
            }
        };
        for (JButton boton: botonesHistorial) {
            boton.addMouseListener(mouseListenerHisorial);
        }
    }

    /**
     * Prepara Acciones del Menu User
     */
    public void prepareAccionesUser() {
        MouseListener mouseListenerUser = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource().equals(volverUser)){
                    Historial();
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getSource().equals(volverUser)){
                    volverUser.setIcon(new ImageIcon("./images/back.png"));
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getSource().equals(volverUser)){
                    volverUser.setIcon(new ImageIcon("./images/back.png"));
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if(e.getSource().equals(volverUser)){
                    volverUser.setIcon(new ImageIcon("./images/back2.png"));
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if(e.getSource().equals(volverUser)){
                    volverUser.setIcon(new ImageIcon("./images/back.png"));
                }
            }
        };
        for (JButton boton: botonesUser) {
            boton.addMouseListener(mouseListenerUser);
        }
    }

    /**
     * Prepara Acciones del Menu Modo Maquina
     */
    private void prepareAccionesModoMaq() {
        MouseListener mouseListenerMenuModoMaq = new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource().equals(modoMaqPri)){
                    modoMaqPri.setBounds(WIDTH/2-210,220,150,70);
                    setModoMaq("P");
                    panelModoMaq();
                }
                else if(e.getSource().equals(modoMaqExp)){
                    modoMaqExp.setBounds(WIDTH/2+40,220,150,70);
                    setModoMaq("E");
                    panelModoMaq();
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
                if(e.getSource().equals(modoMaqPri)){
                    modoMaqPri.setBounds(WIDTH/2-210,220,150,70);
                }
                else if(e.getSource().equals(modoMaqExp)){
                    modoMaqExp.setBounds(WIDTH/2+40,220,150,70);
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if(e.getSource().equals(modoMaqPri)){
                    modoMaqPri.setBounds((WIDTH+12)/2-230,210,170,90);
                }
                else if(e.getSource().equals(modoMaqExp)){
                    modoMaqExp.setBounds((WIDTH+12)/2+30,220,160,70);
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {
                if(e.getSource().equals(modoMaqPri)){
                    modoMaqPri.setBounds(WIDTH/2-220,210,170,90);
                }
                else if(e.getSource().equals(modoMaqExp)){
                    modoMaqExp.setBounds(WIDTH/2+30,210,170,90);
                }
            }
            @Override
            public void mouseExited(MouseEvent e) {
                if(e.getSource().equals(modoMaqPri)){
                    modoMaqPri.setBounds((WIDTH+12)/2-210,220,150,70);
                }
                else if(e.getSource().equals(modoMaqExp)){
                    modoMaqExp.setBounds((WIDTH+12)/2+40,220,150,70);
                }
            }
        };
        modoMaqPri.addMouseListener(mouseListenerMenuModoMaq);
        modoMaqExp.addMouseListener(mouseListenerMenuModoMaq);
    }

    /**
     * Metodo para cerrar el JFrame correctamente, antes de cerrarlo le pregunta al usuario si esta seguro de su eleccion
     */
    private void getOut() {
        cl.show(mainPanel,"menuGetOut");
    }

    /**
     * Metodo para volver del menu DataGame
     */
    private void dataModoGame() {
        if (Objects.equals(modo, "I")) datosInd();
        if (Objects.equals(modo, "M")) datosMulti();
        if (Objects.equals(modo, "C")) datosMaqui();
    }

    /**
     * Metodo para escoger el modo de maquina que quiere
     */
    private void modoMaquina() {
        cl.show(mainPanel,"menuModMaq");
    }

    /**
     * Funcion para salir del juego
     */
    private void salir() throws TetrisException {
        saveRegisteredUsers();
        System.exit(0);
    }

    /**
     * Metodo para abrir un nuevo archivo
     */
    private void load() throws TetrisException {
        opcionAbrir();
    }

    /**
     * Metodo que muestra historial del usuario
     */
    private void historialUser() {
        updatePartidasUser(modelo.get(listaJugadores.getSelectedIndex()));
        cl.show(mainPanel,"MenuUser");
    }

    /**
     * Metodo que carga los Datos de los nombres del juego
     */
    private void loadGame() {
        //DATOS NOMBRES
        dataNombres.clear();
        dataNombres.add(campoNombre);
        dataNombres.add(campoNombreMulti1);
        dataNombres.add(campoNombreMulti2);
        dataNombres.add(campoNombreMaqui1);
        dataNombres.add(campoNombreMaqui2);
        //DATA COLORS
        dataColores.clear();
        dataColores.add(colorInd.getBackground());
        dataColores.add(colorMul1.getBackground());
        dataColores.add(colorMul2.getBackground());
        dataColores.add(colorMaq1.getBackground());
        dataColores.add(colorMaq2.getBackground());
        moodSpeed = velSlow.getForeground() != Color.green;
        if (Objects.equals(modo, "I")) {
            panelGame = new PanelGame(this);
            cl.show(mainPanel,"menuModoInd");
        }
        else if (Objects.equals(modo, "M")) {
            panelGame = new PanelGame(this);
            cl.show(mainPanel,"menuModoMul");
        }
        else if (Objects.equals(modo, "C")) {
            modoMaquina();
        }
    }

    /**
     * Metodo que abre el panel despues de escoger el tipo de maquina
     */
    public void panelModoMaq() {
        panelGame = new PanelGame(this);
        cl.show(mainPanel,"menuModoMaq");
    }

    /**
     * Metodo para abrir el menu Historial
     */
    private void Historial() {
        cl.show(mainPanel,"MenuHistorial");
    }

    /**
     * Metodo para volver al Menu
     */
    private void volverMenu() {
        if (panelGame == null) cl.show(mainPanel,"Menu");
        else {
            panelGame.pausa();
        }
    }

    /**
     * Metodo para volver al Menu estando en una partida
     */
    public void salirGame() {
        if(Objects.equals(modo, "I")) removeKeyListener(panelGame.getMovimientosTablero());
        if(Objects.equals(modo, "M")) removeKeyListener(panelGame.getMovimientosTablero());
        if(Objects.equals(modo, "C")) removeKeyListener(panelGame.getMovimientosTablero());
        changePuntajes();
        changePuntajesPanel();
        panelGame = null;
        cl.show(mainPanel,"Menu");
    }

    /**
     * Metodo para reiniciar el juego
     */
    public void reiniciarGame() {
        if(Objects.equals(modo, "I")) removeKeyListener(panelGame.getMovimientosTablero());
        if(Objects.equals(modo, "M")) removeKeyListener(panelGame.getMovimientosTablero());
        if(Objects.equals(modo, "C")) removeKeyListener(panelGame.getMovimientosTablero());
        panelGame = null;
        loadGame();
    }

    /**
     * Metodo para abrir menu de Nuevo Juego
     */
    private void newGame() {
        cl.show(mainPanel,"menuModo");
    }

    /**
     * Metodo para abrir menu de Puntajes
     */
    private void highScores() {
        cl.show(mainPanel,"Puntajes");
    }

    /**
     * Metodo para abrir menu de configuracion
     */
    private void config(){
        if (panelGame == null) cl.show(mainPanel,"Config");
        else {
            panelGame.configuracionGame();
        }
    }

    /**
     * Metodo que muestra el menu los datos solicitida en modo Individual
     */
    private void datosInd(){
        this.modo = "I";
        cl.show(mainPanel,"MenuDatos");
    }

    /**
     * Metodo que muestra el menu los datos solicitida en modo Multiugador
     */
    private void datosMulti(){
        this.modo = "M";
        cl.show(mainPanel,"MenuDatosMulti");
    }

    /**
     * Metodo que muestra el menu los datos solicitida en modo Contra Maquina
     */
    private void datosMaqui(){
        this.modo = "C";
        cl.show(mainPanel,"MenuDatosMaqui");
    }

    /**
     * Metodo que permite cambiar el color del fondo de la partida
     */
    private void cambiarColorFondo(String modo, String num){
        Color color;
        if (Objects.equals(modo, "I")) {
            color = JColorChooser.showDialog(menuDatos, "Elija un color para el fondo", Color.BLACK);
            this.colorInd.setBackground(color);
        }
        else if (Objects.equals(modo, "M") && Objects.equals(num, "1")) {
            color = JColorChooser.showDialog(menuDatosMulti, "Elija un color para el fondo", Color.BLACK);
            this.colorMul1.setBackground(color);
        }
        else  if (Objects.equals(modo, "M") && Objects.equals(num, "2")) {
            color = JColorChooser.showDialog(menuDatosMulti, "Elija un color para el fondo", Color.BLACK);
            this.colorMul2.setBackground(color);
        }
        else if (Objects.equals(modo, "C") && Objects.equals(num, "1")) {
            color = JColorChooser.showDialog(menuDatosMaqui, "Elija un color para el fondo", Color.BLACK);
            this.colorMaq1.setBackground(color);
        }
        else  if (Objects.equals(modo, "C") && Objects.equals(num, "2")) {
            color = JColorChooser.showDialog(menuDatosMaqui, "Elija un color para el fondo", Color.BLACK);
            this.colorMaq2.setBackground(color);
        }
        else {
            color = JColorChooser.showDialog(menuConfig, "Elija un color para el fondo", Color.BLACK);
            this.colorInd.setBackground(color);
            this.cambiarColor.setBackground(color);
            System.out.println(colorInd.getBackground());
        }
    }

    /**
     * Metodo que muestra el menu de los controles del juego
     */
    public void abrirControles(){
        cl.show(mainPanel, "infoControles");
    }

    /**
     * Metodo que muestra el menu de Instrucciones del juego
     */
    public void abrirInstruccion(){
        cl.show(mainPanel, "infoInstruccion");
    }

    /**
     * Metodo para Modo de juego Individual
     */
    public void ModoIndividual() {
        cl.show(mainPanel,"MenuVelocidad");
    }

    /**
     * Metodo para Modo de juego Multijugador
     */
    public void ModoMultijugador() {
        cl.show(mainPanel,"MenuVelocidad");
    }

    /**
     * Metodo para Modo de juego Contra Maquina
     */
    public void ModoContraMaquina() {
        cl.show(mainPanel,"MenuVelocidad");
    }

    /**
     * Metodo que crea una fuente personalizada
     */
    public Font customFont(int size) {
        try {
            InputStream is = new FileInputStream("./fonts/arcade.ttf");
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            return font.deriveFont(Font.PLAIN, size);
        }
        catch (Exception a){
            return new Font("Arial", Font.PLAIN, 14);
        }
    }

    /**
     * Metodo que reproduce la musica
     */
    private void musica(){
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("musica/Tetristheme.wav").getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-30.0f);
        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println("Error al reproducir el sonido.");
        }
    }

    /**
     * Metodo que detiene o reanuda la musica
     */
    private void controlMusica() {
        if (musica.isSelected()) {
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } else {
            clip.stop();
        }
    }

    /**
     * Metodo que abre un archivo en formato txt
     */
    private void opcionAbrir() throws TetrisException {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("txt","txt");
        chooser.setFileFilter(filter);
        int archivoVal = chooser.showDialog(null,"Abrir");
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        if (archivoVal == JFileChooser.APPROVE_OPTION )
        {
            panelGame = new PanelGame(this, chooser.getSelectedFile());
            System.out.println(chooser.getSelectedFile());
        }
    }

    /**
     * Metodo para cargar Historial de todas las partidas de los Usuarios
     */
    private void loadRegisteredUsers()  {
        ArrayList<String> lines = new ArrayList<>();
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader("./archivos/historial.txt"));
            String line = bufferedReader.readLine();
            while (line != null) {
                lines.add(line);
                line = bufferedReader.readLine();
            }
        }catch (IOException e) {
            System.out.println(e.getCause());
        }
        if (lines.size() != 0) {
            for (String l: lines) {
                String[] splittedLine = l.trim().split(" ");
                ArrayList<String> values = new ArrayList<>();
                values.add(splittedLine[1]);
                values.add(splittedLine[2]);
                values.add(splittedLine[3]);
                if (!existsUsers(splittedLine[0])) {
                    HashMap<Integer,ArrayList<String>> user = new HashMap<>();
                    user.put(0,values);
                    dataUsers.put(splittedLine[0],user);
                    modelo.addElement(splittedLine[0]);
                }
                else {
                    dataUsers.get(splittedLine[0]).put(dataUsers.get(splittedLine[0]).size(),values);
                }
            }
            changePuntajes();
        }
    }

    /**
     * Metodo para guardar puntajes al terminar el juego
     * @throws TetrisException clase Exception
     */
    private void saveRegisteredUsers() throws TetrisException {
        try{
            PrintWriter pw = new PrintWriter(new FileOutputStream("./archivos/historial.txt"));
            for (String u: dataUsers.keySet()) {
                for (Integer d: dataUsers.get(u).keySet()) {
                    pw.print(u+" "+dataUsers.get(u).get(d).get(0)+" "+dataUsers.get(u).get(d).get(1)+" " +dataUsers.get(u).get(d).get(2)+'\n');
                }
            }
            pw.close();
        }catch (IOException e){
            throw new TetrisException(TetrisException.ENTRADA_SALIDA);
        }catch (Exception e) {
            throw new TetrisException(TetrisException.GUARDAR);
        }
    }

    /**
     * Metodo para cambiar los mejores puntajes del juego
     */
    private void changePuntajes() {
        ArrayList<Integer> times = new ArrayList<>();
        for(String u: dataUsers.keySet()) {
            for(Integer d: dataUsers.get(u).keySet()) {
                times.add(Integer.parseInt(dataUsers.get(u).get(d).get(0)));
            }
        }
        Comparator<Integer> comparador = Collections.reverseOrder();
        times.sort(comparador);
        ArrayList<Integer> mejoresCinco = new ArrayList<>(times);
        for (int i=0; i<mejoresCinco.size(); i++) {
            ArrayList<String> var = new ArrayList<>();
            var.add(asociarValorUsuario(mejoresCinco.get(i)));
            var.add(mejoresCinco.get(i) + "");
            datosPuntajes.put((i + 1 + ""),var);
        }
    }

    /**
     * Metodo para cambiar Puntajes en el panel de Puntajes
     */
    public void changePuntajesPanel() {
        if (datosPuntajes.size() >= 5) {
            puntajes.setText("<html><p>" +
                    "<p>"+ "1." + datosPuntajes.get("1").get(0) + " : "+datosPuntajes.get("1").get(1)+"</p>\n"  + "<p><br></p>\n" +
                    "<p>"+ "2." + datosPuntajes.get("2").get(0) + " : "+datosPuntajes.get("2").get(1)+"</p>\n" + "<p><br></p>\n" +
                    "<p>"+ "3." + datosPuntajes.get("3").get(0) + " : "+datosPuntajes.get("3").get(1)+"</p>\n" + "<p><br></p>\n" +
                    "<p>"+ "4." + datosPuntajes.get("4").get(0) + " : "+datosPuntajes.get("4").get(1)+"</p>\n" + "<p><br></p>\n" +
                    "<p>"+ "5." + datosPuntajes.get("5").get(0) + " : "+datosPuntajes.get("5").get(1)+"</p>\n" + "<p><br></p><html>");
        }
        else if (datosPuntajes.size() == 4) {
            puntajes.setText("<html><p>" +
                    "<p>"+ "1." + datosPuntajes.get("1").get(0) + " : "+datosPuntajes.get("1").get(1)+"</p>\n"  + "<p><br></p>\n" +
                    "<p>"+ "2." + datosPuntajes.get("2").get(0) + " : "+datosPuntajes.get("2").get(1)+"</p>\n" + "<p><br></p>\n" +
                    "<p>"+ "3." + datosPuntajes.get("3").get(0) + " : "+datosPuntajes.get("3").get(1)+"</p>\n" + "<p><br></p>\n" +
                    "<p>"+ "4." + datosPuntajes.get("4").get(0) + " : "+datosPuntajes.get("4").get(1)+"</p>\n" + "<p><br></p><html>");
        }
        else if (datosPuntajes.size() == 3) {
            puntajes.setText("<html><p>" +
                    "<p>"+ "1." + datosPuntajes.get("1").get(0) + " : "+datosPuntajes.get("1").get(1)+"</p>\n"  + "<p><br></p>\n" +
                    "<p>"+ "2." + datosPuntajes.get("2").get(0) + " : "+datosPuntajes.get("2").get(1)+"</p>\n" + "<p><br></p>\n" +
                    "<p>"+ "3." + datosPuntajes.get("3").get(0) + " : "+datosPuntajes.get("3").get(1)+"</p>\n" + "<p><br></p><html>");
        }
        else if (datosPuntajes.size() == 2) {
            puntajes.setText("<html><p>" +
                    "<p>"+ "1." + datosPuntajes.get("1").get(0) + " : "+datosPuntajes.get("1").get(1)+"</p>\n"  + "<p><br></p>\n" +
                    "<p>"+ "2." + datosPuntajes.get("2").get(0) + " : "+datosPuntajes.get("2").get(1)+"</p>\n" +  "<p><br></p><html>");
        }
        else if (datosPuntajes.size() == 1) {
            puntajes.setText("<html><p>" +
                    "<p>"+ "1." + datosPuntajes.get("1").get(0) + " : "+datosPuntajes.get("1").get(1)+"</p>\n"  + "<p><br></p><html>");
        }
    }

    /**
     * Metodo para asociar puntaje tiempo a su jugador
     * @param time tiempo
     * @return nombre del jugador que hizo ese tiempo
     */
    private String asociarValorUsuario(Integer time) {
        for(String u: dataUsers.keySet()) {
            for(Integer d: dataUsers.get(u).keySet()) {
                if(Integer.parseInt(dataUsers.get(u).get(d).get(0)) == time) {
                    return u;
                }
            }
        }
        return "";
    }

    /**
     * Metodo para actualizar registro de partidas de un Usuario
     * @param user Usuario
     */
    private void updatePartidasUser(String user) {
        nombreUser.setText(user);
        modeloUser.clear();
        for (String u: dataUsers.keySet()) {
            if (Objects.equals(u, user)) {
                for (Integer d: dataUsers.get(u).keySet()) {
                    modeloUser.addElement("TIME      " +
                            dataUsers.get(u).get(d).get(0) + "      SCORE      " +
                            dataUsers.get(u).get(d).get(1) + "      LEVEL      " +
                            dataUsers.get(u).get(d).get(2));
                }
            }
        }
    }

    /**
     * Metodo para anadir una partida del juego
     * @param name Nombre del jugador
     * @param time Tiempo del jugador
     * @param score Score del jugador
     * @param level level del jugador
     */
    public void anadirPartida(String name, int time, int score, int level) {
        ArrayList<String> values = new ArrayList<>();
        values.add(time + "");
        values.add(score + "");
        values.add(level + "");
        if (existsUsers(name)) {
            dataUsers.get(name).put(dataUsers.get(name).size(),values);
        }
        else {
            HashMap<Integer,ArrayList<String>> user = new HashMap<>();
            user.put(0,values);
            dataUsers.put(name,user);
            modelo.addElement(name);
        }
    }

    /**
     * Metodo para saber si Un usuario ya ha sido registrado en el juego
     * @param llave nombre del usuario a verificar
     * @return True si esta registrado, False de lo contrario
     */
    public boolean existsUsers(String llave) {
        for(String u: dataUsers.keySet()) {
            if (Objects.equals(u, llave)) return true;
        }
        return false;
    }

    /**
     * Metodo para cambiar el modo del juego
     * @param modo modo de juego
     */
    public void setModo(String modo) {
        this.modo = modo;
    }

    /**
     * Metodo para conocer el modo del juego
     */
    public String getModo() {
        return this.modo;
    }

    /**
     * Metodo para retornar boton de musica
     * @return boton musica
     */
    public JCheckBox getMusica() {
        return musica;
    }

    /**
     * Metodo que retorna velocidad Inicial del juego
     * @return velocidad
     */
    public int getVelInicial() {
        return Integer.parseInt(velInicial.getText());
    }

    /**
     * Metodo que retorna la cantidad de buffos en la partida
     * @return cantidad de buffos
     */
    public int getCantidadBuffos() {
        return Integer.parseInt(numBuffos.getText());
    }

    /**
     * Metodo que retorna el mejor puntaje del juego
     * @return mejor puntaje
     */
    public int getMejorPuntaje() {
        if (datosPuntajes.size() > 0) return Integer.parseInt(datosPuntajes.get("1").get(1));
        else return 0;
    }

    /**
     * Metodo que retorna el Card layout del Juego
     * @return Card Layout
     */
    public CardLayout getCl(){
        return cl;
    }

    /**
     * Metodo que retorna el Container del Juego
     * @return Container
     */
    public Container getMainPanel() {
        return mainPanel;
    }

    /**
     * Merodo para conocer el mood Speed de la partida
     * @return moodSpeed
     */
    public boolean isMoodSpeed() {
        return moodSpeed;
    }

    /**
     * Merodo para cambiar el mood Speed de la partida
     * @param speed Valor nuevo
     */
    public void setMoodSpeed(boolean speed) {
        this.moodSpeed = speed;
    }

    /**
     * Metodo que retorna el modo de Maquina
     * @return Modo Maquina
     */
    public String getModoMaq() {
        return modoMaq;
    }

    /**
     * Metodo que cambia el modo de Maquina
     * @param modo Modo Maquina
     */
    public void setModoMaq(String modo) {
        this.modoMaq = modo;
    }

    /**
     * Funcion principal del proyecto
     */
    public static void main(String[] args) {
        TetrisGUI tetrisGUI = new TetrisGUI();
        tetrisGUI.setVisible(true);
    }
}
