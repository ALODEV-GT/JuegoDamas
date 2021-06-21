package tablero;

import test.Run;

public class Movimientos {

    private Casilla casilla;
    private int xInicial;
    private int yInicial;
    private Casilla[][] tablero;
    private Casilla[] alrededor = { null, null, null, null };
    private Casilla[] alrededorComer = { null, null, null, null };
    private String[] coordenadasMovimientos = new String[4];
    private String[] coordenadasComer = new String[4];
    private int xDestino = -1; // coordenada default
    private int yDestino = -1; // coordenada default
    private int[] opcionesDisponibles = { -1, -1, -1, -1 }; // Opcion default
    private int[] opcionesParaComer = { -1, -1, -1, -1 }; // Opciones default
    private boolean comerObligatoriamente = false;
    private int xFichaComer;
    private int yFichaComer;
    private boolean hayMovimientos = true;
    private String opcionesDeMovimiento = "";

    public Movimientos(Casilla[][] tablero) {
        this.tablero = tablero;
    }

    public boolean getComerObligatoriamente() {
        return this.comerObligatoriamente;
    }

    public boolean comerObligatoriamente(int xInicial, int yInicial, boolean esTurnoRojo) {
        this.xInicial = xInicial;
        this.yInicial = yInicial;
        this.comerObligatoriamente = false;

        if (verificarCasillaSinTexto(xInicial, yInicial, esTurnoRojo)) {
            buscarAlrededor();
            buscarAlrededorComer();
            MostrarMovimientosComer();

            if (hayMovimientos) {
                System.out.println(opcionesDeMovimiento);
                int eleccionOpcionComer = eleccionOpcionComer();
                definirCoordenadaDespuesDeComer(eleccionOpcionComer);
                definirFichaAComer(eleccionOpcionComer);
                if (xDestino != -1 && yDestino != -1) {
                    moverFicha(xInicial, yInicial, xDestino, yDestino);
                    tablero[xFichaComer][yFichaComer].quitarFicha();
                }
            }

        }
        System.out.println("Terminee comerObligatoriamente" );
        System.out.println("Dovolvi: " + comerObligatoriamente);
        return comerObligatoriamente;
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

    private void definirCoordenadaDespuesDeComer(int opcion) {
        String[] coordenadas = coordenadasComer[opcion].split(",");
        xDestino = Integer.valueOf(coordenadas[0]);
        yDestino = Integer.valueOf(coordenadas[1]);
    }

    private void definirFichaAComer(int opcion) {
        String[] coordenadas = coordenadasMovimientos[opcion].split(",");
        xFichaComer = Integer.valueOf(coordenadas[0]);
        yFichaComer = Integer.valueOf(coordenadas[1]);
    }

    public boolean verificarCasilla(int x, int y, boolean esRoja) {
        boolean correcto = false;
        if (x >= 0 && x < tablero.length && y >= 0 && y < tablero.length) {
            if (tablero[x][y].tieneFicha()) {
                this.casilla = tablero[x][y];
                if (esRoja && tablero[x][y].getFicha().esRoja()) {
                    correcto = true;
                } else if (!esRoja && !tablero[x][y].getFicha().esRoja()) {
                    correcto = true;
                } else {
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

    public void buscarAlrededorComer() {
        int contador = 0;
        for (int i = xInicial - 2; i <= xInicial + 2; i += 4) {
            for (int j = yInicial - 2; j <= yInicial + 2; j += 4) {
                try {
                    alrededorComer[contador] = tablero[i][j];
                } catch (IndexOutOfBoundsException e) {
                    // Dejar pasar la excepcion
                }
                coordenadasComer[contador] = i + "," + j;
                contador++;
            }
        }
    }

    public void MostrarMovimientosComer() {
        
        if (casilla.getFicha().esReyna()) {
            for (int i = 0; i < alrededor.length; i++) {
                if (alrededor[i] != null && alrededor[i].tieneFicha() == true) {
                    if (!alrededor[i].getFicha().getColor().equals(casilla.getFicha().getColor())) {
                        if (alrededorComer[i] != null && alrededorComer[i].tieneFicha() == false) {
                            this.opcionesDeMovimiento += i + ".- Comer, y pasar a (" + coordenadasComer[i] + ")\n";
                            opcionesParaComer[i] = i;
                            comerObligatoriamente = true;
                            this.hayMovimientos = true;
                        }
                    }
                }
            }
        } else {
            if (casilla.getFicha().esRoja()) {
                for (int i = 2; i < alrededor.length; i++) {
                    if (alrededor[i] != null && alrededor[i].tieneFicha() == true) {
                        if (!alrededor[i].getFicha().getColor().equals(casilla.getFicha().getColor())) {
                            if (alrededorComer[i] != null && alrededorComer[i].tieneFicha() == false) {
                                this.opcionesDeMovimiento += i + ".- Comer, y pasar a (" + coordenadasComer[i] + ")\n";
                                opcionesParaComer[i] = i;
                                comerObligatoriamente = true;
                                this.hayMovimientos = true;
                            }
                        }
                    }
                }
            } else {
                for (int i = 0; i < 2; i++) {
                    if (alrededor[i] != null && alrededor[i].tieneFicha() == true) {
                        if (!alrededor[i].getFicha().getColor().equals(casilla.getFicha().getColor())) {
                            if (alrededorComer[i] != null && alrededorComer[i].tieneFicha() == false) {
                                this.opcionesDeMovimiento += i + ".- Comer, y pasar a (" + coordenadasComer[i] + ")\n";
                                opcionesParaComer[i] = i;
                                comerObligatoriamente = true;
                                this.hayMovimientos = true;
                            }
                        }
                    }
                }
            }
        }

        if (this.opcionesDeMovimiento.equals("")) {
            this.hayMovimientos = false;
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

    public static boolean obligarAComer(Casilla[][] tablero, boolean esTurnoRojo) {
        boolean seObligo = false;
        int contador = 0;

        Movimientos mov = new Movimientos(tablero);

        if (esTurnoRojo) {
            // Obligar a comer, solo si es roja
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (tablero[i][j].getFicha() != null) {
                        System.out.println("R x: " + i + " y: " + j);
                        mov.comerObligatoriamente(i, j, esTurnoRojo);
                        if (mov.getComerObligatoriamente()) {
                            contador++;
                            seObligo = true;
                            if (contador == 4) {
                                break;
                            }
                        }
                    }
                }
            }
        } else {
            // Obligar a comer, solo si es negro
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    System.out.print("N x: " + i );
                    System.out.println(" y: " + j);
                    if (tablero[i][j].getFicha() != null) {
                        System.out.println("----------Inicio------------");                        
                        mov.comerObligatoriamente(i, j, esTurnoRojo);
                        System.out.println("----------Fin-------------");
                        if (mov.getComerObligatoriamente()) {
                            contador++;
                            seObligo = true;
                            if (contador == 4) {
                                break;
                            }
                        }
                    }

                }
            }
            
            
        }
        return seObligo;
    }

    public boolean verificarCasillaSinTexto(int x, int y, boolean esRoja) {
        boolean correcto = false;
        if (x >= 0 && x < tablero.length && y >= 0 && y < tablero.length) {
            if (tablero[x][y].tieneFicha()) {
                this.casilla = tablero[x][y];
                if (esRoja && tablero[x][y].getFicha().esRoja()) {
                    correcto = true;
                } else if (!esRoja && !tablero[x][y].getFicha().esRoja()) {
                    correcto = true;
                } else {
                    // System.out.println("No puedes mover fichas de tu contrincante");
                }
            } else {
                // System.out.println("La posicion que ingresaste no tiene ficha");
            }
        } else {
            // System.out.println("La posicion que ingresaste no existe");
        }

        return correcto;
    }

    public int eleccionOpcionComer() {
        int opcion;
        boolean existe = false;
        do {
            System.out.println("Elige una opcion: ");
            opcion = Run.entrada.nextInt();

            for (int i = 0; i < alrededorComer.length; i++) {
                if (opcionesParaComer[i] == opcion) {
                    existe = true;                    
                }
            }

            if (!existe) {
                System.out.println("La opcion que ingresaste no existe, vuelve a intentarlo");
            }

        } while (!existe);
        return opcion;
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
