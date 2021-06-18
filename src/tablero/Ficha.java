package tablero;
import test.Run;
public class Ficha {
    private boolean vivo;
    private boolean esReyna;
    private boolean esRoja;
    private String color;
    public Ficha(boolean esRoja){
        vivo = true;
        esReyna = false;
        this.esRoja = esRoja;
        if (esRoja) {
            this.color = Run.ROJO;
        }else{
            this.color = Run.NEGRO;
        }
    }   

    public String getColor(){
        return this.color;
    }

    @Override
    public String toString(){
        return "████████ " + 
             "██" + color + "████" + Run.COLOR_NORMAL + "██ " +
             "██" + color + "████" + Run.COLOR_NORMAL + "██ " +
             "████████"; 
    }


}
