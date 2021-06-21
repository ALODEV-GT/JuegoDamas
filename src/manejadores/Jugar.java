package manejadores;

import partida.Jugador;
import test.Run;

public class Jugar {

    private VectorJugadores vectorJugadores;
    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador ganadorPPT;
    private Jugador[] jugadores = new Jugador[2];

    public Jugar(VectorJugadores vectorJugadores) {
        this.vectorJugadores = vectorJugadores;
    }

    public void jugar() {
        elegirJugadores();
        jugarPiedraPapelTijera();
        elegirPreferenciasGanador();
        elegirQuienInicia();
        MotorDamas motorDamas = new MotorDamas(jugadores);
        motorDamas.iniciarJuego();
    }

    public void elegirPreferenciasGanador() {
        int eleccion;
        boolean correcto = false;
        do {
            System.out.println(ganadorPPT.getNombre() + ", selecciona tu color");
            System.out.println("1.- Rojo");
            System.out.println("2.- Negro \n:");
            eleccion = Run.entrada.nextInt();

            switch (eleccion) {
                case 1:
                    for (int i = 0; i < jugadores.length; i++) {
                        if (ganadorPPT.equals(jugadores[i])) {
                            jugadores[i].setEsRoja(true);
                        } else {
                            jugadores[i].setEsRoja(false);
                        }
                    }
                    correcto = true;
                    break;
                case 2:
                    for (int i = 0; i < jugadores.length; i++) {
                        if (ganadorPPT.equals(jugadores[i])) {
                            jugadores[i].setEsRoja(false);
                        } else {
                            jugadores[i].setEsRoja(true);
                        }
                    }
                    correcto = true;
                    break;
                default:
                    System.out.println("La apcion que ingresaste no existe, vuelve a intentarlo");
                    break;
            }
        } while (!correcto);
    }

    private void elegirQuienInicia() {
        int eleccion;
        boolean correcto = false;
        System.out.println(ganadorPPT.getNombre() + ", quieres iniciar?");
        System.out.println("1.- Si");
        System.out.println("2.- No\n:");
        eleccion = Run.entrada.nextInt();
        switch (eleccion) {
            case 1:
                for (int i = 0; i < jugadores.length; i++) {
                    if (ganadorPPT.equals(jugadores[i])) {
                        jugadores[i].setIniciador(true);
                    } else {
                        jugadores[i].setIniciador(false);
                    }
                }
                break;
            case 2:
                for (int i = 0; i < jugadores.length; i++) {
                    if (ganadorPPT.equals(jugadores[i])) {
                        jugadores[i].setIniciador(false);
                    } else {
                        jugadores[i].setIniciador(true);
                    }
                }
                break;
            default:
                break;
        }
    }

    private void elegirJugadores() {
        int posJugador1;
        int posJugador2;
        boolean correcto = false;

        System.out.println("----- ELIGE A LOS JUGADORES ------");

        do {
            vectorJugadores.mostrarJugadores();
            System.out.println("Ingresa la posicion del jugador 1: ");
            posJugador1 = Run.entrada.nextInt();
            System.out.println("Ingresa la posicion del jugador 2: ");
            posJugador2 = Run.entrada.nextInt();

            if (posJugador1 != posJugador2) {
                if (posJugador1 > vectorJugadores.genNumJugadores() - 1
                        || posJugador2 > vectorJugadores.genNumJugadores() - 1) {
                    System.out.println("La posicion que ingresaste no existe, vuelve a intentarlo\n");
                } else {
                    correcto = true;
                }
            } else {
                System.out.println("No puedes seleccionar al mismo jugador, vuelve a intentarlo\n");
            }
        } while (!correcto);

        jugador1 = vectorJugadores.getJugador(posJugador1);
        jugador2 = vectorJugadores.getJugador(posJugador2);
        jugadores[0] = jugador1;
        jugadores[1] = jugador2;
    }

    // Inicio piedra papel o tijera

    private void jugarPiedraPapelTijera() {
        boolean correcto = false;
        System.out.println("\n----- PIEDRA, PAPEL O TIJERA -----");
        do {
            int opcion1 = elegirOpcion(jugador1);
            Run.limpiarPantalla();
            int opcion2 = elegirOpcion(jugador2);
            if (opcion1 == opcion2) {
                System.out.println("\nEmpate, vuelvan a elegir");
            } else {
                evaluarGanador(opcion1, opcion2);
                correcto = true;
            }
        } while (!correcto);
        System.out.println("\nGano " + ganadorPPT.getNombre());
    }

    private int elegirOpcion(Jugador jugador) {
        int opcionElegida;
        boolean correcto = false;

        do {
            System.out.println("1.- Piedra");
            System.out.println("2.- Papel");
            System.out.println("3.- Tijera");
            System.out.println("\nElige, " + jugador.getNombre());
            opcionElegida = Run.entrada.nextInt();
            switch (opcionElegida) {
                case 1:
                case 2:
                case 3:
                    correcto = true;
                    break;
                default:
                    System.out.println("\nLa opcion no existe, vuelve a intentarlo");
                    break;
            }
        } while (!correcto);
        return opcionElegida;
    }

    private void evaluarGanador(int opcion1, int opcion2) {
        if (opcion1 == 1 && opcion2 == 2) {
            // Gana jugador 2
            ganadorPPT = jugador2;
        } else if (opcion1 == 1 && opcion2 == 3) {
            // Gana jugador 1
            ganadorPPT = jugador1;
        } else if (opcion1 == 3 && opcion2 == 1) {
            // Gana jugador 2
            ganadorPPT = jugador2;
        } else if (opcion1 == 3 && opcion2 == 2) {
            // Gana jugador 1
            ganadorPPT = jugador1;
        } else if (opcion1 == 2 && opcion2 == 3) {
            // Gana jugador 2
            ganadorPPT = jugador2;
        } else if (opcion1 == 2 && opcion2 == 1) {
            // Gana jugador 1
            ganadorPPT = jugador1;
        }
    }

    // Fin piedra papel o tijera
}
