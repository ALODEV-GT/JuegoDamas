package manejadores;

import partida.Jugador;
import tablero.Movimientos;
import tablero.Tablero;
import test.Run;
import tablero.Casilla;

public class MotorDamas {

    private Jugador[] jugadores;
    private Tablero tab = new Tablero();
    private Casilla[][] tablero;

    public MotorDamas(Jugador[] jugadores) {
        this.jugadores = jugadores;
    }

    public void iniciarJuego() {

        System.out.println("-------- EMPIEZA EL JUEGO --------");
        tab.iniciarTablero();
        tablero = tab.getTablero();
        manejadorTurnos();
    }

    public void manejadorTurnos() {
        boolean temino = false;
        int indiceJugador = 0;

        for (int i = 0; i < jugadores.length; i++) { // Detecta el primer jugador
            if (jugadores[i].getIniciador()) {
                indiceJugador = i;
                break;
            }
        }

        do {
            boolean seObligo = false;
            System.out.println("Turno de " + jugadores[indiceJugador].getNombre() + " ("
                    + jugadores[indiceJugador].getColor() + ")");

            seObligo = Movimientos.obligarAComer(tablero, jugadores[indiceJugador].esRoja(), tab);
            if (!seObligo) {
                realizarMovimiento(jugadores[indiceJugador].esRoja());
            }
            indiceJugador = cambiarTurno(indiceJugador);
            tab.mostrarTablero();

            temino = tab.termino();

        } while (!temino);

        Jugador ganador = null;
        if (tab.ganoRoja()) {
            for (int i = 0; i < jugadores.length; i++) {
                if (jugadores[i].esRoja()) {
                    ganador = jugadores[i];
                }
            }
        } else {
            for (int i = 0; i < jugadores.length; i++) {
                if (jugadores[i].esRoja() == false) {
                    ganador = jugadores[i];
                }
            }
        }

        for (int i = 0; i < jugadores.length; i++) {
            jugadores[i].agregarPartida();
        }

        System.out.println("------- FIN DE LA PARTIDA, GANADOR: " + ganador.getNombre() + " ("+ganador.getColor()+")" );
        ganador.agregarVictoria();
    }

    public void realizarMovimiento(boolean esRoja) {
        Movimientos mov = new Movimientos(tablero, tab);
        System.out.println("Ingresa el numero de fila");
        int xInicial = Run.entrada.nextInt();
        System.out.println("Ingresa el numero de columna");
        int yInicial = Run.entrada.nextInt();
        mov.realizarMovimiento(xInicial, yInicial, esRoja);
    }

    public int cambiarTurno(int indiceJugador) {
        int indice;
        if (indiceJugador == 0) {
            indice = 1;
        } else {
            indice = 0;
        }
        return indice;
    }

}
