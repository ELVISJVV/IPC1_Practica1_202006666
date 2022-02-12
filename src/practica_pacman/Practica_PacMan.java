/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica_pacman;

import java.util.Scanner;

/**
 *
 * @author Elvis
 */
public class Practica_PacMan {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        boolean salirNumeroPremios = false;
        boolean salirNumeroParedes = false;
        boolean salirNumeroTrampas = false;
        boolean salirIniciarJuego = false;
        boolean salirIngresarPosicion = false;
        boolean salirPausa = false;
        int opcionMenuPausa;
        int opcion; //Guardaremos la opcion del usuario
        //char [][] tableroG = new char [12][12];
        //char [][] tableroP = new char[7][8];
        String[][] tableroG = new String[12][12];
        String[][] tableroP = new String[7][8];
        boolean[][] tableroG2 = new boolean[12][12];
        boolean[][] tableroP2 = new boolean[7][8];
        String elegirTablero, usuario = "";
        String respuesta;
        final String PACMAN = "<";
        final String FANTASMA = "@";
        final String PREMIOSIMPLE = "0";
        final String PREMIOESPECIAL = "$";
        final String PARED = "X";

        int posActualPacX = 2;
        int posActualPacY = 2;
        int posAntiguaPacX = 2;
        int posAntiguaPacY = 2;

        int numeroPremios = 0;
        int numeroParedes = 0;
        int numeroTrampas = 0;
        int numeroPremiosP = 0;
        int numeroParedesP = 0;
        int numeroTrampasP = 0;

        int filaInicial = 0;
        int columnaInicial = 0;

        int vidas = 3;
        String estado = ""; //gano, perdio o se rindiio
        int puntajeObtenido = 0;
        int[] partidaAlmacenadaPuntaje = new int[10];
        String[] partidaAlmacenadaNombre = new String[10];
        String[] partidaAlmacenadaEstado = new String[10];
        int contadorPuntaje = 0;
        int contadorNombre = 0;
        int contadorEstado = 0;
        int contadorHistorial = 0;
        tableroG[posActualPacX][posActualPacY] = PACMAN;

        while (!salir) {

            System.out.println("======= MENU PRINCIPAL ========");
            System.out.println("1. Iniciar Juego");
            System.out.println("2. Historial de Partidas");
            System.out.println("3. Salir");
            System.out.println("================================");

            try {

                System.out.println("Ingresa una de las opciones");
                opcion = sc.nextInt();

                switch (opcion) {
                    case 1:
                        System.out.println("1. Iniciar Juego");
                        System.out.println("Ingrese nombre del usuario");
                        usuario = sc.next().trim();
                        partidaAlmacenadaNombre[contadorNombre] = usuario;
                        System.out.println("========= Especificar Tablero ==========");

                        String sizeTablero = "";
                        while (!sizeTablero.equals("g") && !sizeTablero.equals("p")) {
                            try {
                                System.out.println("Tamaño del tablero Grande (G) o Pequeño (P)");
                                sizeTablero = sc.next().toLowerCase().trim();
                                switch (sizeTablero) {
                                    case "g":
                                        System.out.println("Elegiste tablero grande");
                                        vidas = 3;
                                        salirNumeroPremios = false;
                                        while (!salirNumeroPremios) {
                                            try {
                                                System.out.println("Ingrese numero de Premios [1-40]");
                                                numeroPremios = sc.nextInt();
                                                if (numeroPremios < 1 || numeroPremios > 40) {
                                                    System.out.println("Ingrese un numero entre 1 y 40");
                                                } else {
                                                    salirNumeroPremios = true;
                                                }
                                            } catch (Exception e) {
                                                System.out.println("Debes insertar un número");
                                                sc.next();
                                            }
                                        }

                                        // System.out.println("Ingrese numero de Premios [1-40]");
                                        //numeroPremios = sc.nextInt();
                                        salirNumeroParedes = false;
                                        while (!salirNumeroParedes) {
                                            try {
                                                System.out.println("Ingrese numero de Paredes [1-20]");
                                                numeroParedes = sc.nextInt();
                                                if (numeroParedes < 1 || numeroParedes > 20) {
                                                    System.out.println("Ingrese un numero entre 1 y 20");
                                                } else {
                                                    salirNumeroParedes = true;
                                                }
                                            } catch (Exception e) {
                                                System.out.println("Debes insertar un número");
                                                sc.next();
                                            }
                                        }
                                        salirNumeroTrampas = false;
                                        while (!salirNumeroTrampas) {
                                            try {
                                                System.out.println("Ingrese numero de Trampas [1-20]");
                                                numeroTrampas = sc.nextInt();
                                                if (numeroTrampas < 1 || numeroTrampas > 20) {
                                                    System.out.println("Ingrese un numero entre 1 y 20");
                                                } else {
                                                    salirNumeroTrampas = true;
                                                }
                                            } catch (Exception e) {
                                                System.out.println("Debes insertar un número");
                                                sc.next();
                                            }
                                        }

                                        int randomPremios = generaNumeroRandom(1, numeroPremios);
                                        int premioSimple,
                                         premioEspecial;
                                        premioEspecial = randomPremios;
                                        premioSimple = numeroPremios - randomPremios;
                                        int premioEspecial2 = premioEspecial;
                                        int premioSimple2 = premioSimple;

                                        int fila,
                                         columna;
                                        rellenarMatriz(tableroG);

                                        while (premioEspecial > 0) {
                                            fila = generaNumeroRandom(1, 11);
                                            columna = generaNumeroRandom(1, 11);
                                            if (tableroG[fila][columna].equals(" ")) {
                                                tableroG[fila][columna] = PREMIOESPECIAL;
                                                premioEspecial--;
                                            } else {
                                            }

                                        }
                                        while (premioSimple > 0) {
                                            fila = generaNumeroRandom(1, 11);
                                            columna = generaNumeroRandom(1, 11);
                                            if (tableroG[fila][columna].equals(" ")) {
                                                tableroG[fila][columna] = PREMIOSIMPLE;
                                                premioSimple--;
                                            }
                                        }
                                        while (numeroParedes > 0) {
                                            fila = generaNumeroRandom(1, 11);
                                            columna = generaNumeroRandom(1, 11);
                                            if (tableroG[fila][columna].equals(" ")) {
                                                tableroG[fila][columna] = PARED;
                                                numeroParedes--;
                                            }
                                        }
                                        while (numeroTrampas > 0) {
                                            fila = generaNumeroRandom(1, 11);
                                            columna = generaNumeroRandom(1, 11);
                                            if (tableroG[fila][columna].equals(" ")) {
                                                tableroG[fila][columna] = FANTASMA;
                                                numeroTrampas--;
                                            }

                                        }

                                        //Puntajes
                                        int puntajePremioSimple = premioSimple2 * 10;
                                        int puntajePremioEspecialpremio = premioEspecial2 * 15;
                                        int puntajeMaximo = puntajePremioSimple + puntajePremioEspecialpremio;
                                        int simple = 0,
                                         especial = 0;

                                        //fin puntajes
                                        mostrarMatriz(tableroG);
                                        salirIngresarPosicion = false;
                                        while (!salirIngresarPosicion) {
                                            try {
                                                System.out.println("Ingrese una posicion libre donde quiera comenzar");
                                                System.out.println("Ingrese numero de fila");
                                                filaInicial = sc.nextInt() + 1;
                                                System.out.println("Ingrese numeo de columna");
                                                columnaInicial = sc.nextInt() + 1;
                                                if (!tableroG[filaInicial][columnaInicial].equals(FANTASMA) && !tableroG[filaInicial][columnaInicial].equals(PARED) && !tableroG[filaInicial][columnaInicial].equals(PREMIOESPECIAL) && !tableroG[filaInicial][columnaInicial].equals(PREMIOSIMPLE) && !tableroG[filaInicial][columnaInicial].equals("-") && !tableroG[filaInicial][columnaInicial].equals("|")) {
                                                    posActualPacX = filaInicial;
                                                    posActualPacY = columnaInicial;
                                                    posAntiguaPacX = filaInicial;
                                                    posAntiguaPacY = columnaInicial;
                                                    salirIngresarPosicion = true;
                                                } else {
                                                    System.out.println("Ingrese coordenadas validas");
                                                }
                                            } catch (Exception e) {
                                                System.out.println("Debes insertar un número ");
                                                sc.next();
                                            }
                                        }
                                        /*
                                        if (tableroG[12][12]!="+") {
                                            System.out.println("neh");
                                        } else {
                                        }
                                         */

                                        tableroG[posActualPacX][posActualPacY] = PACMAN;

                                        //mostrarMatriz(tableroG);
                                        boolean salirTableroG = false;

                                        while (!salirTableroG) {
                                            tableroG[posActualPacX][posActualPacY] = PACMAN;//Sirve para que vuelva a aparecer el pacman si se ingresa opcion erronea o al poner pausa yregresar
                                            puntajeObtenido = simple + especial;
                                            System.out.println("Nombre del jugador: " + usuario);
                                            System.out.println("Numero de vidas: " + vidas);
                                            System.out.println("Puntaje:" + puntajeObtenido);
                                            mostrarMatriz(tableroG);

                                            try {
                                                posAntiguaPacX = posActualPacX;
                                                posAntiguaPacY = posActualPacY;
                                                System.out.println("8. Mover hacia arriba");
                                                System.out.println("5. Mover hacia abajo");
                                                System.out.println("6. Mover hacia la derecha");
                                                System.out.println("4. Mover hacia la izquierda");
                                                System.out.println("F. Pausa");

                                                String movimiento = sc.next().toLowerCase().trim();
                                                switch (movimiento) {
                                                    case "8":
                                                        posActualPacX--;
                                                        break;
                                                    case "5":
                                                        posActualPacX++;
                                                        break;
                                                    case "6":
                                                        posActualPacY++;
                                                        break;
                                                    case "4":
                                                        posActualPacY--;
                                                        break;
                                                    case "f":
                                                        salirPausa = false;
                                                        while (!salirPausa) {
                                                            System.out.println("============ PAUSA ============");
                                                            System.out.println("Por favor, seleccione una opcion");
                                                            System.out.println("1. Regresar");
                                                            System.out.println("2. Ver Historial de Partidas");
                                                            System.out.println("3. Terminar Partida");
                                                            System.out.println("===============================");

                                                            try {

                                                                opcionMenuPausa = sc.nextInt();
                                                                switch (opcionMenuPausa) {
                                                                    case 1:
                                                                        salirPausa = true;
                                                                        break;
                                                                    case 2:
                                                                        int matrizPartida[] = new int[contadorNombre];
                                                                        if (usuario.equals("") && estado.equals("") && puntajeObtenido == 0) {
                                                                            System.out.println("No hay partidas guardadas");
                                                                        } else {

                                                                            System.out.println("===============HISTORIAL DE PARTIDAS===============");
                                                                            System.out.println("No.  USUARIO              PUNTEO              ESTADO");

                                                                            contadorHistorial = 0;
                                                                            for (int i = matrizPartida.length - 1; i > -1; i--) {
                                                                                System.out.println((contadorHistorial + 1) + "     " + partidaAlmacenadaNombre[i] + "               " + partidaAlmacenadaPuntaje[i] + "                " + partidaAlmacenadaEstado[i]);
                                                                                contadorHistorial++;
                                                                            }

                                                                        }
                                                                        sc.nextLine();
                                                                        System.out.println("Presiona Enter para continuar");
                                                                        sc.nextLine();//presionar enter y regresara
                                                                        /*
                                                                         for (int i = 0; i < partidaAlmacenadaNombre.length; i++) {
                                                                            System.out.println(partidaAlmacenadaNombre[i]);
                                                                        }
                                                                        for (int i = 0; i < partidaAlmacenadaPuntaje.length; i++) {
                                                                            System.out.println("");
                                                                            partidaAlmacenadaPuntaje[contadorPuntaje] = puntajeObtenido;

                                                                        }
                                                                        for (int i = 0; i < partidaAlmacenadaPuntaje.length; i++) {
                                                                            System.out.println(partidaAlmacenadaPuntaje[i]);
                                                                        }
                                                                         */

                                                                        break;
                                                                    case 3:
                                                                        salirPausa = true;
                                                                        salirTableroG = true;
                                                                        estado = "RENUNCIA";
                                                                        partidaAlmacenadaEstado[contadorEstado] = estado;
                                                                        partidaAlmacenadaPuntaje[contadorPuntaje] = puntajeObtenido;
                                                                        contadorNombre++;
                                                                        contadorPuntaje++;

                                                                        contadorEstado++;
                                                                        break;
                                                                    default:
                                                                        System.out.println("Ingresa una opcion correcta");
                                                                }
                                                            } catch (Exception e) {
                                                                System.out.println("Debes insertar un número");
                                                                sc.next();
                                                            }
                                                        }

                                                        //salirIniciarJuego = true;
                                                        break;
                                                    default:

                                                        System.out.println("Elige una opcion correcta");

                                                }
                                                //Perdiendo Vidas
                                                if (tableroG[posActualPacX][posActualPacY].equals(FANTASMA)) {
                                                    vidas--;
                                                }
                                                if (vidas == 0) {
                                                    System.out.println("=======================");
                                                    System.out.println("      HAS PERDIDO      ");
                                                    System.out.println("   SUERTE LA PROXIMA   ");
                                                    System.out.println("=======================");
                                                    System.out.println("");
                                                    System.out.println("");
                                                    estado = "DERROTA";
                                                    partidaAlmacenadaEstado[contadorEstado] = estado;
                                                    partidaAlmacenadaPuntaje[contadorPuntaje] = puntajeObtenido;
                                                    contadorNombre++;
                                                    contadorPuntaje++;

                                                    contadorEstado++;
                                                    salirTableroG = true;

                                                }
                                                //Ganando por premios
                                                //PREMIO SIMPLE 10
                                                //PREMIO ESPECIAL 15    

                                                if (tableroG[posActualPacX][posActualPacY].equals(PREMIOSIMPLE)) {
                                                    simple = simple + 10;
                                                }
                                                if (tableroG[posActualPacX][posActualPacY].equals(PREMIOESPECIAL)) {
                                                    especial = especial + 15;
                                                }
                                                puntajeObtenido = simple + especial;
                                                if (puntajeObtenido == puntajeMaximo) {

                                                    System.out.println("=======================");
                                                    System.out.println("      FELICIDADES      ");
                                                    System.out.println("      HAS GANADO       ");
                                                    System.out.println("      Puntaje: " + puntajeObtenido);
                                                    System.out.println("=======================");
                                                    System.out.println("");
                                                    System.out.println("");

                                                    estado = "VICTORIA";
                                                    partidaAlmacenadaEstado[contadorEstado] = estado;
                                                    partidaAlmacenadaPuntaje[contadorPuntaje] = puntajeObtenido;
                                                    contadorNombre++;
                                                    contadorPuntaje++;

                                                    contadorEstado++;
                                                    salirTableroG = true;
                                                }
                                                /// Fin premio Especial
                                                  if (tableroG[posActualPacX][posActualPacY].equals(FANTASMA) || tableroG[posActualPacX][posActualPacY].equals(PREMIOESPECIAL) || tableroG[posActualPacX][posActualPacY].equals(PREMIOSIMPLE) || tableroG[posActualPacX][posActualPacY].equals(" ")) {
                                                    tableroG[posActualPacX][posActualPacY] = PACMAN;
                                                    tableroG[posAntiguaPacX][posAntiguaPacY] = " ";
                                                } else if (tableroG[posActualPacX][posActualPacY].equals(PARED)) {
                                                    posActualPacX = posAntiguaPacX;
                                                    posActualPacY = posAntiguaPacY;
                                                } else if (tableroG[posActualPacX][posActualPacY].equals("|")) {
                                                    if (posActualPacY == 11) {
                                                        posActualPacY = 1;
                                                        posActualPacY = posActualPacY;
                                                        if (tableroG[posActualPacX][posActualPacY].equals(FANTASMA)) {
                                                            vidas--;
                                                        }
                                                        if (tableroG[posActualPacX][posActualPacY].equals(PREMIOSIMPLE)) {
                                                            simple = simple + 10;
                                                        }
                                                        if (tableroG[posActualPacX][posActualPacY].equals(PREMIOESPECIAL)) {
                                                            especial = especial + 15;
                                                        }
                                                        if (tableroG[posActualPacX][posActualPacY].equals(PARED)) {
                                                            posActualPacX = posAntiguaPacX;
                                                            posActualPacY = posAntiguaPacY;
                                                        } else {
                                                            tableroG[posActualPacX][posActualPacY] = PACMAN;
                                                            tableroG[posAntiguaPacX][posAntiguaPacY] = " ";
                                                        }

                                                    } else if (posActualPacY == 0) {
                                                        posActualPacY = 10;
                                                        posActualPacY = posActualPacY;
                                                        if (tableroG[posActualPacX][posActualPacY].equals(FANTASMA)) {
                                                            vidas--;
                                                        }
                                                        if (tableroG[posActualPacX][posActualPacY].equals(PREMIOSIMPLE)) {
                                                            simple = simple + 10;
                                                        }
                                                        if (tableroG[posActualPacX][posActualPacY].equals(PREMIOESPECIAL)) {
                                                            especial = especial + 15;
                                                        }
                                                        if (tableroG[posActualPacX][posActualPacY].equals(PARED)) {
                                                            posActualPacX = posAntiguaPacX;
                                                            posActualPacY = posAntiguaPacY;
                                                        } else {
                                                            tableroG[posActualPacX][posActualPacY] = PACMAN;
                                                            tableroG[posAntiguaPacX][posAntiguaPacY] = " ";
                                                        }
                                                   
                                                    }
                                                }else if(tableroG[posActualPacX][posActualPacY].equals("-")){
                                                    if (posActualPacX == 11) {
                                                        posActualPacX = 1;
                                                        posActualPacX = posActualPacX;
                                                        if (tableroG[posActualPacX][posActualPacY].equals(FANTASMA)) {
                                                            vidas--;
                                                        }
                                                        if (tableroG[posActualPacX][posActualPacY].equals(PREMIOSIMPLE)) {
                                                            simple = simple + 10;
                                                        }
                                                        if (tableroG[posActualPacX][posActualPacY].equals(PREMIOESPECIAL)) {
                                                            especial = especial + 15;
                                                        }
                                                        if (tableroG[posActualPacX][posActualPacY].equals(PARED)) {
                                                            posActualPacX = posAntiguaPacX;
                                                            posActualPacY = posAntiguaPacY;
                                                        } else {
                                                            tableroG[posActualPacX][posActualPacY] = PACMAN;
                                                            tableroG[posAntiguaPacX][posAntiguaPacY] = " ";
                                                        }

                                                    } else if (posActualPacX == 0) {
                                                        posActualPacX = 10;
                                                        posActualPacX = posActualPacX;
                                                        if (tableroG[posActualPacX][posActualPacY].equals(FANTASMA)) {
                                                            vidas--;
                                                        }
                                                        if (tableroG[posActualPacX][posActualPacY].equals(PREMIOSIMPLE)) {
                                                            simple = simple + 10;
                                                        }
                                                        if (tableroG[posActualPacX][posActualPacY].equals(PREMIOESPECIAL)) {
                                                            especial = especial + 15;
                                                        }
                                                        if (tableroG[posActualPacX][posActualPacY].equals(PARED)) {
                                                            posActualPacX = posAntiguaPacX;
                                                            posActualPacY = posAntiguaPacY;
                                                        } else {
                                                            tableroG[posActualPacX][posActualPacY] = PACMAN;
                                                            tableroG[posAntiguaPacX][posAntiguaPacY] = " ";
                                                        }
                                                    
                                                
                                                }
                                                    
                                                }    
                                                
                                               /* esto sirve si no tiene que pasar de un extremo a otro
                                                if (estaDentroMatriz(tableroG.length, tableroG[0].length, posActualPacX, posActualPacY) && !tableroG[posActualPacX][posActualPacY].equals(PARED)) {

                                                    tableroG[posActualPacX][posActualPacY] = PACMAN;
                                                    tableroG[posAntiguaPacX][posAntiguaPacY] = " ";

                                                   
                                                } else {
                                                    posActualPacX = posAntiguaPacX;
                                                    posActualPacY = posAntiguaPacY;
                                                }
                                                */
                                                

                                            } catch (Exception e) {
                                                System.out.println("Debes insertar una opcion valida");
                                                sc.next();
                                            }
                                        }
                                        break;
                                    case "p":
                                        System.out.println("Elegiste tablero pequeño");
                                        //rellenarMatriz(tableroP);
                                        //mostrarMatriz(tableroP);
                                        //salirIniciarJuego = true;
                                        vidas = 3;
                                        salirNumeroPremios = false;
                                        while (!salirNumeroPremios) {
                                            try {
                                                System.out.println("Ingrese numero de Premios [1-12]");
                                                numeroPremiosP = sc.nextInt();
                                                if (numeroPremiosP < 1 || numeroPremiosP > 12) {
                                                    System.out.println("Ingrese un numero entre 1 y 12");
                                                } else {
                                                    salirNumeroPremios = true;
                                                }
                                            } catch (Exception e) {
                                                System.out.println("Debes insertar un número");
                                                sc.next();
                                            }
                                        }

                                        // System.out.println("Ingrese numero de Premios [1-40]");
                                        //numeroPremios = sc.nextInt();
                                        salirNumeroParedes = false;
                                        while (!salirNumeroParedes) {
                                            try {
                                                System.out.println("Ingrese numero de Paredes [1-6]");
                                                numeroParedesP = sc.nextInt();
                                                if (numeroParedesP < 1 || numeroParedesP > 6) {
                                                    System.out.println("Ingrese un numero entre 1 y 6");
                                                } else {
                                                    salirNumeroParedes = true;
                                                }
                                            } catch (Exception e) {
                                                System.out.println("Debes insertar un número");
                                                sc.next();
                                            }
                                        }
                                        salirNumeroTrampas = false;
                                        while (!salirNumeroTrampas) {
                                            try {
                                                System.out.println("Ingrese numero de Trampas [1-6]");
                                                numeroTrampasP = sc.nextInt();
                                                if (numeroTrampasP < 1 || numeroTrampasP > 6) {
                                                    System.out.println("Ingrese un numero entre 1 y 6");
                                                } else {
                                                    salirNumeroTrampas = true;
                                                }
                                            } catch (Exception e) {
                                                System.out.println("Debes insertar un número");
                                                sc.next();
                                            }
                                        }

                                        int randomPremiosP = generaNumeroRandom(1, numeroPremiosP);
                                        int premioSimpleP,
                                         premioEspecialP;
                                        premioEspecialP = randomPremiosP;
                                        premioSimpleP = numeroPremiosP - randomPremiosP;
                                        int premioEspecial2P = premioEspecialP;
                                        int premioSimple2P = premioSimpleP;

                                        int filaP,
                                         columnaP;
                                        rellenarMatriz(tableroP);

                                        while (premioEspecialP > 0) {
                                            filaP = generaNumeroRandom(1, 6);
                                            columnaP = generaNumeroRandom(1, 7);
                                            if (tableroP[filaP][columnaP].equals(" ")) {
                                                tableroP[filaP][columnaP] = PREMIOESPECIAL;
                                                premioEspecialP--;
                                            } else {
                                            }

                                        }
                                        while (premioSimpleP > 0) {
                                            filaP = generaNumeroRandom(1, 6);
                                            columnaP = generaNumeroRandom(1, 7);
                                            if (tableroP[filaP][columnaP].equals(" ")) {
                                                tableroP[filaP][columnaP] = PREMIOSIMPLE;
                                                premioSimpleP--;
                                            }
                                        }
                                        while (numeroParedesP > 0) {
                                            filaP = generaNumeroRandom(1, 6);
                                            columnaP = generaNumeroRandom(1, 7);
                                            if (tableroP[filaP][columnaP].equals(" ")) {
                                                tableroP[filaP][columnaP] = PARED;
                                                numeroParedesP--;
                                            }
                                        }
                                        while (numeroTrampasP > 0) {
                                            filaP = generaNumeroRandom(1, 6);
                                            columnaP = generaNumeroRandom(1, 7);
                                            if (tableroP[filaP][columnaP].equals(" ")) {
                                                tableroP[filaP][columnaP] = FANTASMA;
                                                numeroTrampasP--;
                                            }

                                        }

                                        //Puntajes
                                        int puntajePremioSimpleP = premioSimple2P * 10;
                                        int puntajePremioEspecialpremioP = premioEspecial2P * 15;
                                        int puntajeMaximoP = puntajePremioSimpleP + puntajePremioEspecialpremioP;
                                        int simpleP = 0,
                                         especialP = 0;

                                        //fin puntajes
                                        mostrarMatriz(tableroP);
                                        salirIngresarPosicion = false;
                                        while (!salirIngresarPosicion) {
                                            try {
                                                System.out.println("Ingrese una posicion libre donde quiera comenzar");
                                                System.out.println("Ingrese numero de fila");
                                                filaInicial = sc.nextInt() + 1;
                                                System.out.println("Ingrese numeo de columna");
                                                columnaInicial = sc.nextInt() + 1;
                                                if (!tableroP[filaInicial][columnaInicial].equals(FANTASMA) && !tableroP[filaInicial][columnaInicial].equals(PARED) && !tableroP[filaInicial][columnaInicial].equals(PREMIOESPECIAL) && !tableroP[filaInicial][columnaInicial].equals(PREMIOSIMPLE) && !tableroP[filaInicial][columnaInicial].equals("-") && !tableroP[filaInicial][columnaInicial].equals("|")) {
                                                    posActualPacX = filaInicial;
                                                    posActualPacY = columnaInicial;
                                                    posAntiguaPacX = filaInicial;
                                                    posAntiguaPacY = columnaInicial;
                                                    salirIngresarPosicion = true;
                                                } else {
                                                    System.out.println("Ingrese coordenadas validas");
                                                }
                                            } catch (Exception e) {
                                                System.out.println("Debes insertar un número ");
                                                sc.next();
                                            }
                                        }

                                        tableroG[posActualPacX][posActualPacY] = PACMAN;

                                        //mostrarMatriz(tableroG);
                                        boolean salirTableroP = false;

                                        while (!salirTableroP) {

                                            tableroP[posActualPacX][posActualPacY] = PACMAN;//Sirve para que vuelva a aparecer el pacman si se ingresa opcion erronea o al poner pausa yregresar
                                            puntajeObtenido = simpleP + especialP;
                                            System.out.println("Nombre del jugador: " + usuario);
                                            System.out.println("Numero de vidas: " + vidas);
                                            System.out.println("Puntaje:" + puntajeObtenido);
                                            mostrarMatriz(tableroP);

                                            try {
                                                posAntiguaPacX = posActualPacX;
                                                posAntiguaPacY = posActualPacY;
                                                System.out.println("8. Mover hacia arriba");
                                                System.out.println("5. Mover hacia abajo");
                                                System.out.println("6. Mover hacia la derecha");
                                                System.out.println("4. Mover hacia la izquierda");
                                                System.out.println("F. Pausa");

                                                String movimiento = sc.next().toLowerCase().trim();
                                                switch (movimiento) {
                                                    case "8":
                                                        posActualPacX--;
                                                        break;
                                                    case "5":
                                                        posActualPacX++;
                                                        break;
                                                    case "6":
                                                        posActualPacY++;
                                                        break;
                                                    case "4":
                                                        posActualPacY--;
                                                        break;
                                                    case "f":
                                                        salirPausa = false;
                                                        while (!salirPausa) {
                                                            System.out.println("============ PAUSA ============");
                                                            System.out.println("Por favor, seleccione una opcion");
                                                            System.out.println("1. Regresar");
                                                            System.out.println("2. Ver Historial de Partidas");
                                                            System.out.println("3. Terminar Partida");
                                                            System.out.println("===============================");

                                                            try {

                                                                opcionMenuPausa = sc.nextInt();
                                                                switch (opcionMenuPausa) {
                                                                    case 1:
                                                                        salirPausa = true;
                                                                        break;
                                                                    case 2:
                                                                        int matrizPartida[] = new int[contadorNombre];
                                                                        if (usuario.equals("") && estado.equals("") && puntajeObtenido == 0) {
                                                                            System.out.println("No hay partidas guardadas");
                                                                        } else {

                                                                            System.out.println("===============HISTORIAL DE PARTIDAS===============");
                                                                            System.out.println("No.  USUARIO              PUNTEO              ESTADO");

                                                                            contadorHistorial = 0;
                                                                            for (int i = matrizPartida.length - 1; i > -1; i--) {
                                                                                System.out.println((contadorHistorial + 1) + "     " + partidaAlmacenadaNombre[i] + "               " + partidaAlmacenadaPuntaje[i] + "                " + partidaAlmacenadaEstado[i]);
                                                                                contadorHistorial++;
                                                                            }

                                                                        }
                                                                        sc.nextLine();
                                                                        System.out.println("Presiona Enter para continuar");
                                                                        sc.nextLine();//presionar enter y regresara
                                                                      

                                                                        break;
                                                                    case 3:
                                                                        salirPausa = true;
                                                                        salirTableroP = true;
                                                                        estado = "RENUNCIA";
                                                                        partidaAlmacenadaEstado[contadorEstado] = estado;
                                                                        partidaAlmacenadaPuntaje[contadorPuntaje] = puntajeObtenido;
                                                                        contadorNombre++;
                                                                        contadorPuntaje++;

                                                                        contadorEstado++;
                                                                        break;
                                                                    default:
                                                                        System.out.println("Ingresa una opcion correcta");
                                                                }
                                                            } catch (Exception e) {
                                                                System.out.println("Debes insertar un número");
                                                                sc.next();
                                                            }
                                                        }

                                                        //salirIniciarJuego = true;
                                                        break;
                                                    default:

                                                        System.out.println("Elige una opcion correcta");

                                                }
                                                //Perdiendo Vidas
                                                if (tableroP[posActualPacX][posActualPacY].equals(FANTASMA)) {
                                                    vidas--;
                                                }
                                                if (vidas == 0) {
                                                    System.out.println("=======================");
                                                    System.out.println("      HAS PERDIDO      ");
                                                    System.out.println("   SUERTE LA PROXIMA   ");
                                                    System.out.println("=======================");
                                                    System.out.println("");
                                                    System.out.println("");
                                                    estado = "DERROTA";
                                                    partidaAlmacenadaEstado[contadorEstado] = estado;
                                                    partidaAlmacenadaPuntaje[contadorPuntaje] = puntajeObtenido;
                                                    contadorNombre++;
                                                    contadorPuntaje++;

                                                    contadorEstado++;
                                                    salirTableroP = true;

                                                }
                                                //Ganando por premios
                                                //PREMIO SIMPLE 10
                                                //PREMIO ESPECIAL 15    

                                                if (tableroP[posActualPacX][posActualPacY].equals(PREMIOSIMPLE)) {
                                                    simpleP = simpleP + 10;
                                                }
                                                if (tableroP[posActualPacX][posActualPacY].equals(PREMIOESPECIAL)) {
                                                    especialP = especialP + 15;
                                                }
                                                puntajeObtenido = simpleP + especialP;
                                                if (puntajeObtenido == puntajeMaximoP) {

                                                    System.out.println("=======================");
                                                    System.out.println("      FELICIDADES      ");
                                                    System.out.println("      HAS GANADO       ");
                                                    System.out.println("      Puntaje: " + puntajeObtenido);
                                                    System.out.println("=======================");
                                                    System.out.println("");
                                                    System.out.println("");
                                                    estado = "VICTORIA";
                                                    partidaAlmacenadaEstado[contadorEstado] = estado;
                                                    partidaAlmacenadaPuntaje[contadorPuntaje] = puntajeObtenido;
                                                    contadorNombre++;
                                                    contadorPuntaje++;

                                                    contadorEstado++;
                                                    salirTableroP = true;
                                                }
                                                /// Fin premio Especial
                                                if (tableroP[posActualPacX][posActualPacY].equals(FANTASMA) || tableroP[posActualPacX][posActualPacY].equals(PREMIOESPECIAL) || tableroP[posActualPacX][posActualPacY].equals(PREMIOSIMPLE) || tableroP[posActualPacX][posActualPacY].equals(" ")) {
                                                    tableroP[posActualPacX][posActualPacY] = PACMAN;
                                                    tableroP[posAntiguaPacX][posAntiguaPacY] = " ";
                                                } else if (tableroP[posActualPacX][posActualPacY].equals(PARED)) {
                                                    posActualPacX = posAntiguaPacX;
                                                    posActualPacY = posAntiguaPacY;
                                                } else if (tableroP[posActualPacX][posActualPacY].equals("|")) {
                                                    if (posActualPacY == 7) {
                                                        posActualPacY = 1;
                                                        posActualPacY = posActualPacY;
                                                        if (tableroP[posActualPacX][posActualPacY].equals(FANTASMA)) {
                                                            vidas--;
                                                        }
                                                        if (tableroP[posActualPacX][posActualPacY].equals(PREMIOSIMPLE)) {
                                                            simpleP = simpleP + 10;
                                                        }
                                                        if (tableroP[posActualPacX][posActualPacY].equals(PREMIOESPECIAL)) {
                                                            especialP = especialP + 15;
                                                        }
                                                        if (tableroP[posActualPacX][posActualPacY].equals(PARED)) {
                                                            posActualPacX = posAntiguaPacX;
                                                            posActualPacY = posAntiguaPacY;
                                                        } else {
                                                            tableroP[posActualPacX][posActualPacY] = PACMAN;
                                                            tableroP[posAntiguaPacX][posAntiguaPacY] = " ";
                                                        }

                                                    } else if (posActualPacY == 0) {
                                                        posActualPacY = 6;
                                                        posActualPacY = posActualPacY;
                                                        if (tableroP[posActualPacX][posActualPacY].equals(FANTASMA)) {
                                                            vidas--;
                                                        }
                                                        if (tableroP[posActualPacX][posActualPacY].equals(PREMIOSIMPLE)) {
                                                            simpleP = simpleP + 10;
                                                        }
                                                        if (tableroP[posActualPacX][posActualPacY].equals(PREMIOESPECIAL)) {
                                                            especialP = especialP + 15;
                                                        }
                                                        if (tableroP[posActualPacX][posActualPacY].equals(PARED)) {
                                                            posActualPacX = posAntiguaPacX;
                                                            posActualPacY = posAntiguaPacY;
                                                        } else {
                                                            tableroP[posActualPacX][posActualPacY] = PACMAN;
                                                            tableroP[posAntiguaPacX][posAntiguaPacY] = " ";
                                                        }
                                                   
                                                    }
                                                }else if(tableroP[posActualPacX][posActualPacY].equals("-")){
                                                    if (posActualPacX == 6) {
                                                        posActualPacX = 1;
                                                        posActualPacX = posActualPacX;
                                                        if (tableroP[posActualPacX][posActualPacY].equals(FANTASMA)) {
                                                            vidas--;
                                                        }
                                                        if (tableroP[posActualPacX][posActualPacY].equals(PREMIOSIMPLE)) {
                                                            simpleP = simpleP + 10;
                                                        }
                                                        if (tableroP[posActualPacX][posActualPacY].equals(PREMIOESPECIAL)) {
                                                            especialP = especialP + 15;
                                                        }
                                                        if (tableroP[posActualPacX][posActualPacY].equals(PARED)) {
                                                            posActualPacX = posAntiguaPacX;
                                                            posActualPacY = posAntiguaPacY;
                                                        } else {
                                                            tableroP[posActualPacX][posActualPacY] = PACMAN;
                                                            tableroP[posAntiguaPacX][posAntiguaPacY] = " ";
                                                        }

                                                    } else if (posActualPacX == 0) {
                                                        posActualPacX = 5;
                                                        posActualPacX = posActualPacX;
                                                        if (tableroP[posActualPacX][posActualPacY].equals(FANTASMA)) {
                                                            vidas--;
                                                        }
                                                        if (tableroP[posActualPacX][posActualPacY].equals(PREMIOSIMPLE)) {
                                                            simpleP = simpleP + 10;
                                                        }
                                                        if (tableroP[posActualPacX][posActualPacY].equals(PREMIOESPECIAL)) {
                                                            especialP = especialP + 15;
                                                        }
                                                        if (tableroP[posActualPacX][posActualPacY].equals(PARED)) {
                                                            posActualPacX = posAntiguaPacX;
                                                            posActualPacY = posAntiguaPacY;
                                                        } else {
                                                            tableroP[posActualPacX][posActualPacY] = PACMAN;
                                                            tableroP[posAntiguaPacX][posAntiguaPacY] = " ";
                                                        }
                                                    
                                                
                                                }
                                                    
                                                }    

                                                }catch (Exception e) {
                                                System.out.println("Debes insertar una opcion valida");
                                                sc.next();
                                            }
                                            }
                                            break;

                                        
                                default:
                                        System.out.println("Ingrese G o P");
                                }
                            } catch (Exception e) {
                                System.out.println("Debes insertar un número");
                                sc.next();
                            }
                            }

                            break;

                        
                case 2:
                        int matrizPartida[] = new int[contadorNombre];
                        if (usuario.equals("") && estado.equals("") && puntajeObtenido == 0) {
                            System.out.println("No hay partidas guardadas");
                        } else {

                            System.out.println("===============HISTORIAL DE PARTIDAS===============");
                            System.out.println("No.  USUARIO              PUNTEO              ESTADO");

                            contadorHistorial = 0;
                            for (int i = matrizPartida.length - 1; i > -1; i--) {                                
                                System.out.println((contadorHistorial + 1) + "     " + partidaAlmacenadaNombre[i] + "               " + partidaAlmacenadaPuntaje[i] + "                " + partidaAlmacenadaEstado[i]);
                                contadorHistorial++;
                            }

                        }
                        sc.nextLine();
                        System.out.println("Presiona Enter para continuar");
                        sc.nextLine();//presionar enter y regresara

                        /*
                             for (int i = 0; i < matrizPartida.length; i++) {
                            System.out.println(i+1 + "     "+partidaAlmacenadaNombre[i] + "               " + partidaAlmacenadaPuntaje[i]+"                   " + partidaAlmacenadaEstado[i] );
                            }  
                          
                         */
                        // System.out.println(usuario + " " + estado + " puntaje: " + puntajeObtenido);
                        //System.out.println("Historial de Partidas");
                        //System.out.println(usuario + " " + estado);
                        break;
                    case 3:

                        System.out.println("Vuelva Pronto");
                        salir = true;
                        break;

                    default:
                        System.out.println("Eliga un numero del menú");
                }

            } catch (Exception e) {
                System.out.println("Debes insertar un número");
                sc.next();
            }

                //System.out.println("¿Quieres continuar? (S/N)");
                //respuesta = sc.next().toLowerCase().trim();
                //if (respuesta.charAt(0) == 'n') {
                //  salir = true;
                //}
            }
        }

    

    public static int generaNumeroRandom(int minimo, int maximo) {
        return (int) (Math.random() * (maximo - minimo + 1) + (minimo));
    }

    public static void rellenarMatriz(String[][] matriz) {

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                if (i == 0 || i == matriz.length - 1) {
                    matriz[i][j] = "-";
                } else if (j == 0 || j == matriz[0].length - 1) {
                    matriz[i][j] = "|";
                } else {
                    matriz[i][j] = " ";
                }
            }
        }

    }

    public static void mostrarMatriz(String[][] matriz) {
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("");
    }

    public static boolean estaDentroMatriz(int longitudFilas, int longitudColumnas, int x, int y) {
        if (x >= 1 && x < longitudFilas - 1 & y >= 1 && y < longitudColumnas - 1) {
            return true;
        }
        return false;
    }
}
