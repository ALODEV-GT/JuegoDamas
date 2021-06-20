package partida;

public class Jugador {
    private String nombre;
    private int partidasJugadas;
    private int partidasGanadas;

    public Jugador(String nombre) {
        this.nombre = nombre;
        partidasJugadas = 0;
        partidasGanadas = 0;
    }

    public void agregarVictoria() {
        partidasGanadas++;
    }

    public void agregarPartida() {
        partidasJugadas++;
    }

    public int getPartidasGanadas() {
        return this.partidasGanadas;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String toString() {
        return "Nombre: " + this.nombre + "\nPartidas ganadas: " + this.partidasGanadas + "\nPartidas jugadas: "
                + this.partidasJugadas + "\n";
    }

}
