package tablero;

import test.Run;

public class Movimientos {

    private Casilla casilla;
    private int xInicial;
    private int yInicial;
    private Casilla[][] tablero;
    private Casilla[] alrededor = { null, null, null, null };
    private String[] coordenadasMovimientos = new String[4];
    private int xDestino = -1; // coordenada default
    private int yDestino = -1; // coordenada default
    private int[] opcionesDisponibles = { -1, -1, -1, -1 }; // Opcion default

    public Movimientos(Casilla[][] tablero) {
        this.tablero = tablero;
    }

    public void realizarMovimiento(int xInicial, int yInicial, boolean esTurnoRojo) {
        this.xInicial = xInicial;
        this.yInicial = yInicial;

        if (verificarCasilla(xInicial, yInicial, esTurnoRojo)) {
            buscarAlrededor();
            mostrarMovimientos();
            definirCoordenadasDestino(eleccionDeOpcion());
            if (xDestino != -1 && yDestino != -1) {
                moverFicha(xInicial, yInicial, xDestino, yDestino);
            }
        }
    }

    private void moverFicha(int xi, int yi, int xf, int yf) {
        Ficha ficha = tablero[xi][yi].getFicha();
        tablero[xi][yi].quitarFicha();

        tablero[xf][yf].agregarFicha(ficha);
    }

    private void definirCoordenadasDestino(int opcion) {

        if (opcion != 9) {
            String[] coordenadas = coordenadasMovimientos[opcion].split(",");
            xDestino = Integer.valueOf(coordenadas[0]);
            yDestino = Integer.valueOf(coordenadas[1]);

        }
        // de lo contrario sale
    }

    public boolean verificarCasilla(int x, int y, boolean esRoja) {
        boolean correcto = false;
        if (x >= 0 && x < tablero.length && y >= 0 && y < tablero.length) {
            if (tablero[x][y].tieneFicha()) {
                this.casilla = tablero[x][y];
                if (esRoja && tablero[x][y].getFicha().esRoja()) {
                    correcto = true;
                } else if(!esRoja && !tablero[x][y].getFicha().esRoja()){
                    correcto = true;
                }else{
                    System.out.println("No puedes mover fichas de tu contrincante");
                }
            } else {
                System.out.println("La posicion que ingresaste no tiene ficha");
            }
        } else {
            System.out.println("La posicion que ingresaste no existe");
        }

        return correcto;
    }

    public void buscarAlrededor() {
        // arribaI, arribaD, abajoIz, abajoD
        int contador = 0;
        for (int i = xInicial - 1; i <= xInicial + 1; i += 2) {
            for (int j = yInicial - 1; j <= yInicial + 1; j += 2) {
                try {
                    alrededor[contador] = tablero[i][j];
                } catch (IndexOutOfBoundsException e) {
                    // Dejar pasar la excepcion
                }
                coordenadasMovimientos[contador] = i + "," + j;
                contador++;
            }
        }
    }

    public void mostrarMovimientos() {
        String opcionesDeMovimiento = "";
        int posicion = 0;

        if (casilla.getFicha().esReyna()) {
            for (int i = 0; i < alrededor.length; i++) {
                if (alrededor[i] != null && alrededor[i].tieneFicha() == false) {
                    opcionesDeMovimiento += i + ".- Mover a (" + coordenadasMovimientos[i] + ")\n";
                    opcionesDisponibles[i] = i;
                }
            }

        } else {
            if (casilla.getFicha().esRoja()) {
                for (int i = 2; i < alrededor.length; i++) {
                    if (alrededor[i] != null && alrededor[i].tieneFicha() == false) {
                        opcionesDeMovimiento += i + ".- Mover a (" + coordenadasMovimientos[i] + ")\n";
                        opcionesDisponibles[i] = i;
                    }
                }
            } else {
                for (int i = 0; i < 2; i++) {
                    if (alrededor[i] != null && alrededor[i].tieneFicha() == false) {
                        opcionesDeMovimiento += i + ".- Mover a (" + coordenadasMovimientos[i] + ")\n";
                        opcionesDisponibles[i] = i;
                    }
                }
            }

        }

        if (opcionesDeMovimiento.equals("")) {
            opcionesDeMovimiento = "No hay movimientos para esta ficha";
        }

        System.out.println(opcionesDeMovimiento);
    }

    public int eleccionDeOpcion() {
        int opcion;
        boolean existe = false;

        System.out.println("9.- Cancelar");
        do {
            System.out.println("\nElige una opcion: ");
            opcion = Run.entrada.nextInt();

            for (int i = 0; i < alrededor.length; i++) {
                if (opcionesDisponibles[i] == opcion) {
                    existe = true;
                }
            }

            if (opcion == 9) {
                existe = true;
            }

            if (!existe) {
                System.out.println("La opcion que ingresaste no existe, vuelve a intentarlo");
            }
        } while (!existe);

        return opcion;
    }

}
