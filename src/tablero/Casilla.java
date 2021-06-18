package tablero;
import test.Run;
public class Casilla{

    private boolean tieneFicha;
    private Ficha ficha;
    private String color = Run.COLOR_NORMAL;
    private boolean esBlanca;

    public Casilla(boolean tieneFicha, boolean esBlanca){
        this.esBlanca = esBlanca;
        this.tieneFicha = tieneFicha;
        establecerColor(esBlanca);
        if (tieneFicha) {
            this.ficha = new Ficha(true);
            this.tieneFicha = true;
        }
    }

    private void establecerColor(boolean esBlanca){
        if(!esBlanca){
            this.color = Run.PURPURA; 
        }
    }
    

    @Override
    public String toString(){
        if (tieneFicha) {
            return ficha.toString();
        }else{
            return color + "████████ " +Run.COLOR_NORMAL+
                   color + "████████ " +Run.COLOR_NORMAL+
                   color + "████████ " +Run.COLOR_NORMAL+
                   color + "████████" + Run.COLOR_NORMAL;
        }
    }
}