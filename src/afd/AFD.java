package afd;

import java.util.Scanner;

/**
 *
 * @author lmarcoss
 */
public class AFD {

    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//
//        // Constantes
//        int maximoEstados = 10;                         // se trabajará con 10 estados máximo 
//        int maximoAlfabetos = 4;                        // y 4 alfabetos máximo
//
//        // Variables
//        String[] alfabetos = new String[maximoAlfabetos];   // caracteres que conforman el alfabeto
//        String alfabeto;                                    // palabras del alfabeto
//        int numeroAlfabetos;
//
//        int numeroEstados;
//        int numeroEstadosFinales;
//        int[] estadosFinales = new int[maximoEstados];
//        int estadoInicial;                             // El estado final es por default el estado 1
//
//        int[][] transiciones = new int[maximoEstados][maximoAlfabetos];
//
//        // Leer alfabeto
//        Scanner leer = new Scanner(System.in);
//        alfabeto = leerAlfabeto(leer, maximoAlfabetos);
//        alfabetos = extraerAlfabetos(alfabeto, maximoAlfabetos); //alfabetos en un vector
//        numeroAlfabetos = alfabeto.length();                    // alfabetos a trabajar
//
//        //Leer número de estados del AFD
//        numeroEstados = leerNumeroEstados(leer, maximoEstados);
//
//        // Leer estados iniciales
//        estadoInicial = leerEstadosIniciales(leer);
//
//        if (estadoInicial == 1) { // tiene un sólo estado inicial
//            // Leer estados finales
//            numeroEstadosFinales = leerNumeroEdosFinales(leer, numeroEstados);
//            estadosFinales = leerEstadosFinales(leer, numeroEstadosFinales, numeroEstados);
//
//            if (numeroEstadosFinales > 0) {// Tiene estados finales
//                // Lectura de transiciones
//                transiciones = leerTransiciones(leer, alfabetos, numeroEstados, numeroAlfabetos);
//
//                if (transiciones[0][0] != 99) { // No tiene transiciones al vacío o transiciones con el vacío
//                    if (esAFD(alfabetos, numeroAlfabetos, numeroEstados, estadosFinales, numeroEstadosFinales, estadoInicial, transiciones)) {
//                        dibujarGrafo();
//                    } else {
//                        System.err.println("No es AFD");
//                    }
//                } else {
//                    System.err.println("\nUn AFD no tiene transiciones con E ni tiene más transiciones que la cantidad de símbolos del alfabeto\n Por tanto el que intenta introducir no es AFD");
//                }
//            } else { // No tiene estados finales
//                System.err.println("\nNo es un AFD");
//            }
//        } else {
//            System.err.println("\nUn AFD sólo debe tener 1 estado inicial\nPor tanto el que intenta introducir no es un AFD");
//        }
//    }

    private static String leerAlfabeto(Scanner leer, int maximoAlfabetos) {
        String alfabeto;
        // Leer la cadena de alfabeto
        do {
            System.out.print("\nEscriba el alfabeto (ej.: ab)(mín:1 caracter,máx:" + maximoAlfabetos + " caracteres): ");
            alfabeto = leer.nextLine();
        } while (alfabeto.length() > maximoAlfabetos || alfabeto.length() <= 0);

        return alfabeto;
    }

    private static String[] extraerAlfabetos(String alfabeto, int maximoAlfabetos) {
        //extraer los alfabetos
        String[] alfabetos = new String[maximoAlfabetos];
        for (int letra = 0; letra < alfabeto.length(); letra++) {
            alfabetos[letra] = String.valueOf(alfabeto.charAt(letra));
        }
        return alfabetos;
    }

    // Leer número de estados del AFD
    private static int leerNumeroEstados(Scanner leer, int maximoEstados) {
        int estados;
        do {
            System.out.print("\nEscriba el número de estados para el AFD (1-" + maximoEstados + "): ");
            estados = leer.nextInt();
        } while (estados > maximoEstados || estados <= 0);
        return estados;
    }

    private static int leerEstadosIniciales(Scanner leer) {
        int edoInicial;
        do {
            System.out.print("\nEl autómata que intenta introducir tiene un sólo estado inicial? (1 = Sí; 0 = No): ");
            edoInicial = leer.nextInt();
        } while (edoInicial != 1 && edoInicial != 0);
        return edoInicial;
    }

    private static int leerNumeroEdosFinales(Scanner leer, int numeroEstados) {
        int estados;
        do {
            System.out.print("\nEscriba el número de estados finales(1-" + numeroEstados + "): ");
            estados = leer.nextInt();
        } while (estados > numeroEstados || estados < 0);
        return estados;
    }

    private static int[] leerEstadosFinales(Scanner leer, int numeroEstadosFinales, int numeroEstados) {
        int[] estadosFinales = new int[numeroEstadosFinales];
        int contador;
        boolean diferente;
        for (int estado = 0; estado < numeroEstadosFinales; estado++) {
            do {
                System.out.print("\nEscriba el estado final " + (estado + 1) + " (1-" + numeroEstados + "): ");
                estadosFinales[estado] = leer.nextInt();
                // Verificamos que los estados finales estén completas
                contador = 0;
                diferente = true;
                while (contador < estado && diferente == true) {
                    if (estadosFinales[estado] == estadosFinales[contador]) {
                        diferente = false;
                    }
                    contador++;
                }
            } while (!diferente || estadosFinales[estado] > numeroEstados || estadosFinales[estado] <= 0);
        }
        return estadosFinales;
    }

    private static int[][] leerTransiciones(Scanner leer, String[] alfabetos, int numeroEstados, int numeroAlfabetos) {
        int[][] transiciones = new int[numeroEstados][numeroAlfabetos];
        int estado;
        boolean salir = false;
        for (int fila = 0; fila < numeroEstados; fila++) {
            estado = fila + 1;
            for (int columna = 0; columna < numeroAlfabetos; columna++) {
                do {
                    System.out.println("");
                    System.out.println("");
                    System.out.print("\nEl estado " + estado + " con \"" + alfabetos[columna] + "\" transiciona al estado (1-" + numeroEstados + ")? (99 = no hay transición\n con \"" + alfabetos[columna] + "\" o transición al vacío): ");
                    transiciones[fila][columna] = leer.nextInt();
                } while ((transiciones[fila][columna] > numeroEstados || transiciones[fila][columna] <= 0) && (transiciones[fila][columna] != 99));
                if (transiciones[fila][columna] == 99) {
                    salir = true;
                    break;
                }
            }
            if (salir) {
                transiciones[0][0] = 99;
                break; // se termina la lectura
            }
            //Verificamos si hay transiciones adicionales
            int edoAdicional;
            do {
                System.out.println("");
                System.out.println("");
                System.out.print("\nEl estado " + estado + " tiene más estados a la que transiciona? (1 = si, 0 = No): ");
                edoAdicional = leer.nextInt();
            } while (edoAdicional != 1 && edoAdicional != 0);
            if (edoAdicional == 1) {
                transiciones[0][0] = 99;
                break; // se termina la lectura
            }
        }
        return transiciones;
    }

    private static boolean esAFD(String[] alfabetos, int numeroAlfabetos, int numeroEstados, int[] estadosFinales, int numeroEstadosFinales, int estadoInicial, int[][] transiciones) {
        // Ha pasado todas las pruebas anteriores
        if (numeroEstados == 1) {
            // es AFD automáticamente
            return true;
        } else {
            // Los estados transicionan a otro estado excepto los finales que pueden transicionarse
            return TransicionConOtroEstado(transiciones, alfabetos, numeroAlfabetos, numeroEstados, estadosFinales, numeroEstadosFinales);
        }
    }

    private static void dibujarGrafo() {
        System.err.println("\nEs un AFD");
    }

    private static boolean TransicionConOtroEstado(int[][] transiciones, String[] alfabetos, int numeroAlfabetos, int numeroEstados, int[] estadosFinales, int numeroEstadosFinales) {
        // Debe haber al menos una transición con un alfabeto que nos lleve a otro estado: 
        // excepto cuando es estado final
        boolean transicionAOtroEdo = false;

        for (int estado = 0; estado < numeroEstados; estado++) {
            transicionAOtroEdo = false;
            for (int alfabeto = 0; alfabeto < numeroAlfabetos; alfabeto++) {
                if (!esEstadoFinal((estado + 1), estadosFinales, numeroEstadosFinales)) { // no es estado final
                    if (transiciones[estado][alfabeto] != (estado + 1)) {
                        // Con al menos una transición a otro estado se termina para cada estado
                        transicionAOtroEdo = true;
                        break;
                    }
                } else {
                    transicionAOtroEdo = true;
                }
            }
            if (!transicionAOtroEdo) {
                System.err.println("\nNo hay transición a otro estado desde el estado " + (estado + 1));
                break;
            } else {
                System.err.println("\nHayTransición con otro");
            }
        }
        return transicionAOtroEdo;
    }

    private static boolean esEstadoFinal(int estado, int[] estadosFinales, int numeroEstadosFinales) {
        boolean esEstadoFinal = false;
        for (int edoFinal = 0; edoFinal < numeroEstadosFinales; edoFinal++) {
            // Si encuentra una coincidencia es final automáticamente
            if (estadosFinales[edoFinal] == estado) {
                System.err.println("\nEstadoFinal");
                esEstadoFinal = true;
                break;// termina el ciclos
            }
        }
        return esEstadoFinal;
    }

}
