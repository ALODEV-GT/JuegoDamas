package tablero;

import test.Run;

public class Tablero {
    private Casilla[] tab = new Casilla[64];
    private Casilla[][] tablero = new Casilla[8][8];
    private int contadorFilas = 0;

    public void iniciarTablero() {

        boolean esBlanca = false;
        for (int i = 0; i < tab.length; i++) {

            tab[i] = new Casilla(false, esBlanca);

            if ((i + 1) % 8 == 0 && i != 0) {
                //
            } else {
                esBlanca = !esBlanca;
            }
        }

        agregarFichas(tab, true);
        agregarFichas(tab, false);

        mostrarTablero();
        organizarTablero();
    }

    private void organizarTablero(){
        int contador = 0;
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[i].length; j++) {
                tablero[i][j] = tab[contador];
                contador++;
            }
        }
    }

    /****ELIMINAR */
    public void pruebaOpcionesMover(int x, int y){
        Movimientos mov = new Movimientos(tablero);
        mov.realizarMovimiento(x, y);
    }

    public void mostrarTablero() {
        int contador = 0;
        this.contadorFilas = 0;
        
        for (int i = 0; i < Math.sqrt(tab.length); i++) {
            System.out.print("     " + i +  "  ");
        }
        System.out.println();

        for (int i = 0; i < Math.sqrt(tab.length); i++) {
            Casilla[] filaFichas = new Casilla[(int) Math.sqrt(tab.length)];
            for (int j = 0; j < filaFichas.length; j++) {
                filaFichas[j] = tab[contador];
                contador++;
            }
            imprimirFichas(filaFichas);
        }
    }

    private void agregarFichas(Casilla[] casillas, boolean rojas) {

        int contador = 0;
        int i = 0;

        if (rojas) {
            while (contador != 12) {
                if (casillas[i].esBlanca()) {
                    Ficha ficha = new Ficha(true);
                    casillas[i].agregarFicha(ficha);
                    contador++;
                }
                i++;
            }
        } else {
            contador = 0;
            i = casillas.length - 1;
            while (contador != 12) {
                if (casillas[i].esBlanca()) {
                    Ficha ficha = new Ficha(false);
                    casillas[i].agregarFicha(ficha);
                    contador++;
                }
                i--;
            }
        }
    }

    private void imprimirFichas(Casilla[] fila) {
        String[] lineasImprimir = { "  ", "", "  ", "  " };
        
        for (int i = 0; i < fila.length; i++) {
            String[] sp = fila[i].toString().split(" ");
            for (int j = 0; j < sp.length; j++) {
                if (i == 0 && j == 1) {
                    lineasImprimir[j] +=Run.COLOR_NORMAL+contadorFilas+" ";
                    //System.out.print(contadorFilas);
                    contadorFilas++;
                }
                lineasImprimir[j] += "" + sp[j];
            }
        }

        for (int i = 0; i < lineasImprimir.length; i++) {
            System.out.println(lineasImprimir[i]);
        }
    }
}