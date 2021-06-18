package tablero;

public class Tablero {

    public void dibujarTablero() {
        Casilla[] tab = new Casilla[64];

        boolean esBlanca = false;
        for (int i = 0; i < tab.length; i++) {

            tab[i] = new Casilla(false, esBlanca);
            

            if ((i+1) % 8 == 0 && i != 0) {
                //
            }else{
                esBlanca = !esBlanca;
            }
        }

        int contador = 0;
        for (int i = 0; i < Math.sqrt(tab.length); i++) {
            Casilla[] filaFichas = new Casilla[(int) Math.sqrt(tab.length)];
            for (int j = 0; j < filaFichas.length; j++) {
                filaFichas[j] = tab[contador];
                contador++;
            }
            imprimirFichas(filaFichas);
            System.out.println();
        }
    }

    public void imprimirFichas(Casilla[] fila) {
        String[] lineasImprimir = { "", "", "", "" };

        for (int i = 0; i < fila.length; i++) {
            String[] sp = fila[i].toString().split(" ");
            for (int j = 0; j < sp.length; j++) {
                lineasImprimir[j] += " " + sp[j];
            }
        }

        for (int i = 0; i < lineasImprimir.length; i++) {
            System.out.println(lineasImprimir[i]);
        }
    }
}
