/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacio_java;

import Teclat.*;
import java.util.Random;

/**
 *
 * @author Joel
 */
public class BuscaminesFusterBoschJoel {

    //Crea la variable BANDERA per a posar banderes.
    static char BANDERA = 'P';
    //Crea la variable CASELLA_DISPONIBLE per a saber les caselles que 
    //es poden seleccionar.
    static char TAPAT = 'X';
    //Crea la variable MINA per a simplificar.
    static char MINA = 'Q';
    //Funcio per a crear el tauler de char sense prametres d'entrada i 
    //retorna el tauler.
    static char[][] creaTaulerVisible() {
        int FILES;
        int COLUMNES;
        FILES = Teclat.lligInt("Quantes files te el tauler");
        COLUMNES = Teclat.lligInt("Quantes columnes te el tauler");
        char tauler[][] = new char[FILES][COLUMNES];
        for (int i = 0; i < tauler.length; i++) {
            for (int j = 0; j < tauler[0].length; j++) {
                tauler[i][j]=TAPAT;
            }
        }
        return tauler;
    }
    //Funcio per a crear el tauler de boolean amb el tauler i la quantitat de
    //mines com a prametres d'entrada i retorna el tauler de mines.
    static boolean[][] creaTaulerMines(char[][] tauler, int q_mines) {
        // Importa el random per a poder posar les mines en posicions aleàtories
        Random random = new Random();
        int q_fil = tauler.length;
        int q_col = tauler[0].length;
        boolean[][] tauler_mines = new boolean[q_fil][q_col];
        for (int i = 0; i < q_mines; i++) {
            int fa, ca;
            do {
                fa = random.nextInt(0, q_fil - 1);
                ca = random.nextInt(0, q_col - 1);
            } while (tauler_mines[fa][ca]);
            tauler_mines[fa][ca] = true;
        }
        return tauler_mines;
    }
    //Funcio de boolean per a vore si la posicio esta minada amb el tauler 
    //visible, el tauler de mines i la posició com a prametres d'entrada i
    //retorna un true o false.
    static boolean minada(boolean[][] tauler_mines, int fila, int columna) {
        if (incorrecta(tauler_mines, fila, columna)) {
            return false;
        } else {
            return tauler_mines[fila][columna];
        }
    }
    //Funcio per a vore si la posició es incorrecta de boolean amb el tauler i 
    //la posicio com a prametres d'entrada i retorna un true o un false.
    static boolean incorrecta(char[][] tauler, int fila, int columna) {
        return (((fila >= tauler.length) || (fila < 0)) || ((columna >= tauler[0].length) || (columna < 0)));
    }
//La mateixa, pero amb el tauler de mines
    static boolean incorrecta(boolean[][] tauler_mines, int fila, int columna) {
        return (((fila >= tauler_mines.length) || (fila < 0)) || ((columna >= tauler_mines[0].length) || (columna < 0)));

    }
//Funcio de int per a vore la quantitat de mines amb el tauler i la quantitat de
    //mines com a prametres d'entrada i retorna la quantitat de mines 
    //que hi han (0-8).
    static int qma(boolean[][] tauler_mines, int fila, int columna) {
        int qm = 0;
        if (minada(tauler_mines, fila - 1, columna - 1)) {
            qm++;
        }

        if (minada(tauler_mines, fila - 1, columna)) {
            qm++;
        }

        if (minada(tauler_mines, fila - 1, columna + 1)) {
            qm++;
        }

        if (minada(tauler_mines, fila, columna - 1)) {
            qm++;
        }

        if (minada(tauler_mines, fila, columna + 1)) {
            qm++;
        }

        if (minada(tauler_mines, fila + 1, columna - 1)) {
            qm++;
        }

        if (minada(tauler_mines, fila + 1, columna)) {
            qm++;
        }

        if (minada(tauler_mines, fila + 1, columna + 1)) {
            qm++;
        }

        return qm;
    }
    //Funcio de booleans per a vore si la posició esta destapada amb el tauler i la posició com a prametres d'entrada i retorna un true o un false.
    static boolean destapada(char[][] tauler, int fila, int columna) {
        return tauler[fila][columna] != TAPAT && tauler[fila][columna] != BANDERA;
    }
    //Funcio de ints per a vore la quantitat de casselles que s'han destapat al
    //voltant de tota la partida amb el tauler com a prametres d'entrada i 
    //retorna la quantitat de casselles destapades.
    static int qDestapades(char[][] tauler) {
        int q_dest = 0;
        for (int i = 0; i < tauler.length; i++) {
            for (int j = 0; j < tauler[0].length; j++) {
                if (destapada(tauler, i, j)) {
                    q_dest++;
                }
            }
        }
        return q_dest;
    }
//Funcio per a mostrar el tauler amb el tauler, 
    //el tauler de mines un boolean per a saber si es volen vore les mines com 
    //a prametres d'entrada i no retorna res.
    static void mostraTauler(char[][] tauler, boolean[][] tauler_mines, boolean mines) {
        int fil;
        int col;
        System.out.print("   ");
        for (int i = 0; i < tauler[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println("");
        int q_veg = 0;
        for (fil = 0; fil < tauler.length; fil++) {
            col = q_veg;
            System.out.print(col + " ");
            for (col = 0; col < tauler[0].length; col++) {
                if (mines) {
                    if (minada(tauler_mines, fil, col)) {
                        System.out.print("|" + MINA);
                    } else {
                        if (destapada(tauler, fil, col)) {
                            if (qma(tauler_mines, fil, col) > 0) {
                                System.out.print("|" + qma(tauler_mines, fil, col));
                            } else {
                                if (tauler[fil][col] == '|'+BANDERA) {
                                    System.out.println("|"+BANDERA);
                                } else {
                                    System.out.print("|_");
                                }
                            }
                        } else {
                            System.out.print("|" + TAPAT);
                        }
                    }
                } else {
                    if (destapada(tauler, fil, col)) {
                        System.out.print("|_");
                    } else {
                        System.out.print("|" + TAPAT);
                    }
                }
            }
            col = q_veg;
            System.out.print("| ");
            System.out.print(col + "");
            System.out.println("");
            q_veg++;
        }
        System.out.print("   ");
        for (fil = 0; fil < tauler[0].length; fil++) {
            System.out.print(fil + " ");
        }
        System.out.println("");
    }
//Funcio de boolean per a "picar" la posició amb el tauler, el tauler de mines i
    //la posicio com a prametres d'entrada i retorna un true o un false.
    static boolean pica(char[][] tauler, boolean[][] tauler_mines, int fila, int columna) {
        if (tauler_mines[fila][columna]) {
            return true;
        } else {
            destapa(tauler, tauler_mines, fila, columna);
            return false;
        }
    }
//Funcio per a mostrar el tauler amb el tauler, el tauler de mines i la posició 
    //com a prametres d'entrada i no retorna res.
    static void destapa(char[][] tauler, boolean[][] tauler_mines, int fila, int columna) {
        
        if (((incorrecta(tauler, fila, columna)))) {
            return;
        } 
        if (destapada(tauler, fila, columna)) {
                return;
        }
        if (tauler[fila][columna] == '|'+BANDERA) {
            return;
        } 
        if (qma(tauler_mines, fila, columna) >0) {
            destapa(tauler, tauler_mines, fila, columna);
            tauler[fila][columna]= (qma(tauler_mines, fila, columna)+"").charAt(0);
        }else{
            destapa(tauler, tauler_mines, fila, columna);
            tauler[fila][columna]='_';
        }
    }
    public static void main(String[] args) {
        //Definicio de variables i arrays.
        char tauler[][] = creaTaulerVisible();
        int mina = Teclat.lligInt("Quantes mines vols",1,tauler.length-1);
        boolean mines[][] = creaTaulerMines(tauler, mina);
        boolean op = Teclat.lligBoolean("Vols mines");
        boolean jugar = true;
        boolean partida = true;
        boolean guanyat=true;
        int fila;
        int columna;
        while (jugar == true) {
            //Comença la partida.
            while (partida) {
                mostraTauler(tauler, mines, op);
                int jugada = Teclat.lligOpcio("Quina opcio elegeixes", "Picar", "Posar una bandera");
                //El que passa amb les opcions de la jugada
                //0 Jugada no realitzable
                //1 Fer la jugada
                //2Posar la bandera
                if (jugada == 0) {
                    System.out.println("Aquesta jugada no es realitzable");
                }
                if (jugada == 1) {
                    fila = Teclat.lligInt("Quina fila vols picar", 0, tauler.length-1);
                    columna = Teclat.lligInt("Quina columna vols picar", 0, tauler[0].length)-1;
                    if (pica(tauler,mines, fila, columna)) {
                        System.out.println("Continua");
                    } else {
                        //Has tocat una mina i has perdut
                        partida = false;
                        guanyat=false;
                    }
                }
                if (jugada == 2) {
                    fila = Teclat.lligInt("Quina fila vols posar la bandera", 0, tauler.length-1);
                    columna = Teclat.lligInt("Quina columna vols posar la bandera", 0, tauler[0].length-1);
                    tauler[fila][columna]=(char) ('|'+BANDERA);
                }
                if(qDestapades(tauler)==tauler.length){
                    partida=false;
                }
            }
            //Resultats de la partida
            if(guanyat==true){
                System.out.println("Felicitats, has guanyat");
            }else{
                System.out.println("Llastima, has perdut");
            }
            int destapades = qDestapades(tauler);
            System.out.println("Has destapat " + destapades + " mines");
            //Decisió si es vol tornar a jugar o no
            int opcio = Teclat.lligOpcio("Vols continuar jugant?", "Continuar");
            if (opcio == 0) {
                jugar = false;
            }
            partida=true;
            guanyat=true;
        }
    }
}
