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
    public static void main(String[] args) {
        
        // Constantes
        int maximoEstados = 10;                         // se trabajará con 10 estados máximo 
        int maximoAlfabetos = 4;                        // y 4 alfabetos máximo
        
        // Variables
        String[] alfabetos = new String[maximoAlfabetos];   // caracteres que conforman el alfabeto
        String alfabeto;                                    // palabras del alfabeto
        int numeroAlfabetos;
        
        int numeroEstados;
        int numeroEstadosFinales;
        int[] estadosFinales = new int[maximoEstados];
        int estadoInicial = 1;                             // El estado final es por default el estado 1
        
        int[][] transiciones = new int[maximoEstados][maximoAlfabetos];
        
        // Leer alfabeto
        Scanner leer = new Scanner(System.in);
        alfabeto = leerAlfabeto(leer,maximoAlfabetos);            
        alfabetos = extraerAlfabetos(alfabeto,maximoAlfabetos); //alfabetos en un vector
        numeroAlfabetos = alfabeto.length();                    // alfabetos a trabajar
        System.err.println("alfabetos: "+numeroAlfabetos);
        
        //Leer número de estados del AFD
        numeroEstados = leerNumeroEstados(leer,maximoEstados);
        System.err.println("estados: "+numeroEstados);
        
        // Leer estados finales
        numeroEstadosFinales = leerNumeroEdosFinales(leer,maximoEstados);
        estadosFinales = leerEstadosFinales(leer,numeroEstadosFinales);
        System.err.println("Estados finales: "+numeroEstadosFinales);
        
        // Leer tabla de transiciones
        transiciones = leerTransiciones(leer,alfabetos,numeroEstados,numeroAlfabetos);
        if(transiciones[0][0] == 99){
            System.err.println("El AF no es un Autómata finito determinista");
        }else if(!tieneTransicionVacia(transiciones,alfabetos,numeroEstados,numeroAlfabetos)){
            if(esAFD(alfabetos,numeroAlfabetos,numeroEstados,estadosFinales,numeroEstadosFinales,estadoInicial,transiciones)){
                dibujarGrafo();
            }
        }else{
            System.err.println("El AF introducido no es un Autómata finito determinista");
        }
    }

    private static String leerAlfabeto(Scanner leer, int maximoAlfabetos) {
        String alfabeto;
        // Leer la cadena de alfabeto
        do{
            System.out.print("Escriba el alfabeto (ej.: ab)(mín:1 caracter,máx:"+maximoAlfabetos+" caracteres): ");
            alfabeto = leer.nextLine();
        }while(alfabeto.length() > maximoAlfabetos || alfabeto.length() <= 0);
        
        return alfabeto;
    }

    private static String[] extraerAlfabetos(String alfabeto, int maximoAlfabetos) {
        //extraer los alfabetos
        String[] alfabetos = new String[maximoAlfabetos];
        for(int letra = 0; letra < alfabeto.length(); letra++){
            alfabetos[letra] = String.valueOf(alfabeto.charAt(letra));
        }
        return alfabetos;
    }
    
    // Leer número de estados del AFD
    private static int leerNumeroEstados(Scanner leer, int maximoEstados) {
        int estados;
        do{
            System.out.print("Escriba el número de estados para el AFD (1-"+maximoEstados+"): ");
            estados = leer.nextInt();
        }while(estados>maximoEstados || estados<=0);
        
        return estados;
    }

    private static int leerNumeroEdosFinales(Scanner leer, int maximoEstados) {
        int estados;
        do{
            System.out.print("Escriba el número de estados finales(1-"+maximoEstados+"): ");
        estados = leer.nextInt();
        }while( estados > maximoEstados || estados <= 0);
        return estados;
    }
    
    private static int[] leerEstadosFinales(Scanner leer, int numeroEstadosFinales) {
        int[] estadosFinales = new int[numeroEstadosFinales];
        int contador;
        boolean diferente;
        for(int estado = 0; estado < numeroEstadosFinales; estado++){
            do{
                System.out.print("Escriba el estado final "+(estado+1)+": ");
                estadosFinales[estado] = leer.nextInt();
                // Verificamos que los estados finales estén completas
                contador = 0;
                diferente = true;
                while(contador < estado && diferente == true){
                    if(estadosFinales[estado] == estadosFinales[contador]){
                        diferente = false;
                    }
                    contador++;
                }
            }while(!diferente);
        }
        return estadosFinales;
    }

    private static int[][] leerTransiciones(Scanner leer,String[] alfabetos, int numeroEstados, int numeroAlfabetos) {
        int[][] transiciones = new int[numeroEstados][numeroAlfabetos];
        int estado;
        for(int fila = 0; fila < numeroEstados; fila++){
            estado = fila+1;
            for(int columna = 0; columna < numeroAlfabetos; columna++){
                System.out.print("El estado "+estado+" con \""+alfabetos[columna]+"\" transiciona al estado?\n(Escriba 99 si no hay transición con el alfabeto \""+alfabetos[columna]+"\" o si la transición es al vacío): ");
                transiciones[fila][columna] = leer.nextInt();
            }
            //Verificamos si hay transiciones adicionales
            int edoAdicional;
            do{
                System.out.println("El estado "+estado+" tiene más estados a la que transiciona? (1 = si, 0 = No): ");
                edoAdicional = leer.nextInt();
            }while(edoAdicional != 1 && edoAdicional != 0);
            if(edoAdicional == 1){
                transiciones[0][0] = 99;
                break; // se termina la lectura
            }
        }
        return transiciones;
    }
    private static boolean tieneTransicionVacia(int[][] transiciones, String[] alfabetos, int numeroEstados, int numeroAlfabetos) {
        boolean transicionVacia = false;
        for(int fila = 0; fila < numeroEstados; fila++){
            for(int columna = 0; columna < numeroAlfabetos; columna++){
                if(transiciones[fila][columna] == 99){
                    transicionVacia = true;
                    break;
                }
            }
        }
        return transicionVacia;
    }
    
    private static boolean esAFD(String[] alfabetos, int numeroAlfabetos, int numeroEstados, int[] estadosFinales, int numeroEstadosFinales, int estadoInicial, int[][] transiciones) {
        boolean esAFD = true;
        return esAFD;
    }

    private static void dibujarGrafo() {
        System.err.println("Es un AFD");
    }

    

}
