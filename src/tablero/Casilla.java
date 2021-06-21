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

    public boolean tieneFicha(){
        return this.tieneFicha;
    }

    private void establecerColor(boolean esBlanca){
        if(!esBlanca){
            this.color = Run.AZUL; 
        }
    }

    public boolean esBlanca(){
        return this.esBlanca;
    }

    public void agregarFicha(Ficha ficha){
        this.ficha = ficha;
        this.tieneFicha = true;
    }

    public void quitarFicha(){
        this.ficha = null;        
        this.tieneFicha = false;
    }
    
    public Ficha getFicha(){
        return this.ficha;
    }

    @Override
    public String toString(){
        if (tieneFicha) {
            return color + "████████ " +Run.COLOR_NORMAL+
                   color + "██"+ficha.getColor()+"████"+color+"██ " +Run.COLOR_NORMAL+
                   color + "██"+ficha.getColor()+"████"+color+"██ " +Run.COLOR_NORMAL+
                   color + "████████" + Run.COLOR_NORMAL;
        }else{
            return color + "████████ " +Run.COLOR_NORMAL+
                   color + "████████ " +Run.COLOR_NORMAL+
                   color + "████████ " +Run.COLOR_NORMAL+
                   color + "████████" + Run.COLOR_NORMAL;
        }
    }
}