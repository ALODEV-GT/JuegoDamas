package test;
import java.util.Scanner;

import manejadores.Menu;
public class Run {
    //Colores
    public static final String COLOR_NORMAL = "\u001B[0m";
    public static final String ROJO = "\u001B[31m";
    public static final String NEGRO = "\u001B[30m";
    public static final String AZUL = "\u001B[34m";
    public static final String AMARILLO = "\u001B[33m";
    public static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {
        Menu menu = new Menu(); 
    }

    public static void limpiarPantalla(){
        for (int i = 0; i < 10; i++) {
            System.out.println("\n\n\n\n");
        }
    }
}