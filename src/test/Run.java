package test;
import tablero.Ficha;
import tablero.Tablero;
public class Run {
    //Colores
    public static final String COLOR_NORMAL = "\u001B[0m";
    public static final String ROJO = "\u001B[31m";
    public static final String NEGRO = "\u001B[30m";
    public static final String AZUL = "\u001B[34m";
    public static void main(String[] args) {
        Tablero tab = new Tablero();
        tab.iniciarTablero();
    }
}
