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
        int opcion; //Guardaremos la opcion del usuario
        //char [][] tableroG = new char [12][12];
        //char [][] tableroP = new char[7][8];
        String[][] tableroG = new String[12][12];
        String[][] tableroP = new String[7][8];
        boolean[][] tableroG2 = new boolean[12][12];
        boolean[][] tableroP2 = new boolean[7][8];
        String elegirTablero, usuario;
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

        int filaInicial = 0;
        int columnaInicial = 0;

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
                        usuario = sc.next();

                        System.out.println("======= Especificar Tablero ========");

                        String sizeTablero = "";
                        while (!sizeTablero.equals("g") && !sizeTablero.equals("p")) {
                            try {
                                System.out.println("Tamaño del tablero Grande (G) o Pequeño (P)");
                                sizeTablero = sc.next().toLowerCase().trim();
                                switch (sizeTablero) {
                                    case "g":
                                        System.out.println("Elegiste tablero grande");
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
                                            System.out.println("Nombre del jugador: " + usuario);
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
                                                        salirTableroG = true;
                                                        //salirIniciarJuego = true;
                                                        break;
                                                    default:
                                                        System.out.println("Elige una opcion correcta");
                                                }

                                                if (estaDentroMatriz(tableroG.length, tableroG[0].length, posActualPacX, posActualPacY)&& !tableroG[posActualPacX][posActualPacY].equals(PARED)) {
                                                    
                                                    tableroG[posActualPacX][posActualPacY] = PACMAN;
                                                    tableroG[posAntiguaPacX][posAntiguaPacY] = " ";
                                                } else {
                                                    //System.out.println("Te sales del tablero");
                                                    posActualPacX = posAntiguaPacX;
                                                    posActualPacY = posAntiguaPacY;
                                                }

                                            } catch (Exception e) {
                                                System.out.println("Debes insertar una opcion valida");
                                                sc.next();
                                            }
                                        }
                                        break;
                                    case "p":
                                        System.out.println("Elegiste tablero pequeño");
                                        rellenarMatriz(tableroP);
                                        mostrarMatriz(tableroP);
                                        salirIniciarJuego = true;
                                        break;
                                    default:
                                        System.out.println("Ingrese G o P");
                                }
                            } catch (Exception e) {
                                System.out.println("Debes insertar un número");
                                sc.next();
                            }
                        }

                        /*
                         while (!salirIniciarJuego) {
                            
                                
                                switch (sizeTablero) {
                                    case "g":
                                        
                                        
                                        break;
                                    
                                    case "p":
                                      
                                        break;
                                    
                                    default:
                                        System.out.println("Ingrese G o P");
                                }
                                
                            
                            
                        }
                         */
                        //UN OPCION TRUE AL TERMINAR LA EJECUCION PUEDE SERVIR?
                        break;

                    case 2:

                        System.out.println("Historial de Partidas");

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
