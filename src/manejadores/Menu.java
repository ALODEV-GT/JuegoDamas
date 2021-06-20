package manejadores;

import test.Run;

public class Menu {
    private VectorJugadores vectorJugadores = new VectorJugadores();

    public Menu() {
        int opcion;
        do {
            System.out.println("\n------- Bienvenido al juego de DAMAS -------");
            System.out.println("1.- Jugar");
            System.out.println("2.- Registrar jugador");
            System.out.println("3.- Mostrar informacion de jugadores");
            System.out.println("4.- Salir");
            System.out.print("Ingrese una opcion: ");
            opcion = Run.entrada.nextInt();

            switch (opcion) {
                case 1:
                    // Jugar                    
                    if (vectorJugadores.genNumJugadores()<2) {
                        System.out.println("Para jugar, almenos deben existir 2 jugadores, Registrate");
                    } else {
                        Jugar jugar = new Jugar(vectorJugadores);
                        jugar.jugar();    
                    }
                    
                    break;
                case 2:
                    // Registrar jugador
                    if (vectorJugadores.hayEspacio()) {
                        System.out.println("\n---- BIENVENIDO AL REGISTRO DE JUGADORES ----");
                        vectorJugadores.pedirDatosJugador();
                    }
                    break;
                case 3:
                    // Mostrar informacion jugadores
                    if (vectorJugadores.hayJugadores()) {
                        System.out.println("\n---- TOP JUGADORES ----");
                        vectorJugadores.mostrarJugadores();
                    } else {
                        System.out.println("Todavia no hay jugadores, Registrate\n");
                    }
                    break;
                default:
                    opcion = 4;
                    break;
            }

        } while (opcion != 4);
    }
}