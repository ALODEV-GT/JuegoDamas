package partida;

import test.Run;

public class VectorJugadores {
    Jugador[] jugadores = new Jugador[10];
    int contadorJugadores = 0;
    private boolean hayEspacio = true;


    public void pedirDatosJugador(){
        System.out.println("Ingrese su nombre: ");
        String nombre = Run.entrada.next();
        Jugador nuevoJugador = new Jugador(nombre);
        agregarJugador(nuevoJugador);
    }


    private void agregarJugador(Jugador nuevoJugador) {
        if (contadorJugadores < jugadores.length) {
            jugadores[contadorJugadores] = nuevoJugador;
            System.out.println("Se ha registrado correctamente\n");
            contadorJugadores++;    
        } else {
            hayEspacio = false;
        }
    }

    public boolean hayEspacio(){
        return this.hayEspacio;
    }

    public boolean hayJugadores(){
        if (contadorJugadores > 0) {
            return true;
        } else {
            return false;
        }
    }

    public void mostrarJugadores(){
        ordenarJugadores();
        for (int i = 0; i < contadorJugadores; i++) {
            System.out.println(jugadores[i]);
        }
    }

    private void ordenarJugadores(){
        for (int i = 1; i < contadorJugadores; i++) {
            for (int j = i; j > 0; j--) {
                if (jugadores[j].getPartidasGanadas() < jugadores[j-1].getPartidasGanadas()) {
                    Jugador aux = jugadores[j];
                    jugadores[j] = jugadores[j-1];
                    jugadores[j-1] = aux;
                } else {
                    break;
                }
            }
        }
    }
}
