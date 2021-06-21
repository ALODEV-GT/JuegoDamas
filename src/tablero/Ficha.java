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

    public boolean esRoja(){
        return esRoja;
    }

    public boolean esReyna(){
        return esReyna;
    }

    public String getColor(){
        return this.color;
    }

    public void setEsReyna(boolean esReyna){
        this.esReyna = true;
    }

    public String colorReyna(){
        if (esReyna) {
            return Run.AMARILLO;
        }else{
            return this.color;
        }
    }

    @Override
    public String toString(){
        return "████████ " + 
             "██" + color + "█"+colorReyna()+"███" + Run.COLOR_NORMAL + "██ " +
             "██" + color + "██"+colorReyna()+"██" + Run.COLOR_NORMAL + "██ " +
             "████████"; 
    }


}
