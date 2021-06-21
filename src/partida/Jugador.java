package partida;

public class Jugador {
    private String nombre;
    private int partidasJugadas;
    private int partidasGanadas;
    private boolean esRoja = false;
    private boolean iniciador = false;

    public Jugador(String nombre) {
        this.nombre = nombre;
        partidasJugadas = 0;
        partidasGanadas = 0;
    }

    public void setIniciador(boolean iniciar){
        this.iniciador = iniciar;
    }

    public boolean getIniciador(){
        return this.iniciador;
    }

    public void agregarVictoria() {
        partidasGanadas++;
    }

    public void agregarPartida() {
        partidasJugadas++;
    }

    public void setEsRoja(boolean esRoja){
        this.esRoja = esRoja;
    }

    public boolean esRoja(){
        return this.esRoja;
    }

    public String getColor(){
        if (esRoja) {
            return "rojo";
        } else {
            return "negro";
        }
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
