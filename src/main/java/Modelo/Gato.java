package Modelo;

public class Gato extends Tamagotchi{
    private  boolean ronroneando;
 private int nivelIrritabilidad;
 private int nivelPereza;



    public Gato(String nombre, int hambre, int energia, int felicidad, int salud, boolean durmiendo, int nivelIrritabilidad, int nivelPereza) {
        super(nombre,hambre,energia,felicidad,salud,durmiendo);
        this.nivelPereza = limitarRango(nivelPereza);
        this.nivelIrritabilidad = limitarRango(nivelIrritabilidad);
        this.ronroneando = false;
    }

    public Gato(String nombre) {
        super(nombre);
        this.nivelPereza = 50;
        this.nivelIrritabilidad =35;
        this.ronroneando = false;
    }

    @Override
    public String obtenDraw() {
        return "";
    }

    @Override
    public String obtenerDetallesEspecificos() {
        return "Especie: Gato | Irritabilidad: " + nivelIrritabilidad + " | Pereza: " + nivelPereza+"|Ronroneando: "+(ronroneando? "Si":"No");
    }

    @Override
    public void alimentar(TipoAlimento alimento) {
        int bonusEnergy = switch(alimento) {
            case CROQUETAS ->18 ;
            case FRUTA -> 5;
            case DULCES -> 6;
            case CESPED -> 0;
            case TE_FAKE -> 0;
        };
        int bonusFelicidad= switch(alimento) {
            case CROQUETAS ->15 ;
            case FRUTA -> 2;
            case DULCES -> 3;
            case CESPED -> 0;
            case TE_FAKE ->0 ;
        };
        int bonusLife= switch(alimento) {
            case CROQUETAS ->9;
            case FRUTA -> 3;
            case DULCES ->-3 ;
            case CESPED ->0 ;
            case TE_FAKE ->0 ;
        };
        setHambre(limitarRango(hambre-alimento.getReduccionHambre()));
        setEnergia(limitarRango(energia+alimento.getEnergiaBase()+bonusEnergy));
        setFelicidad(limitarRango(felicidad+bonusFelicidad));
        setSalud(limitarRango(salud+bonusLife));



    }

    @Override
    public void jugar(TipoJuego juego) {
        int bonusGame= switch (juego){
            case PERSEGUIR_PELOTA ->2 ;
            case TRUCOS ->5 ;
            case ESCONDIDAS ->15;
        };
        setFelicidad(limitarRango(felicidad + juego.getAumentoFelicidad() + bonusGame));
        setEnergia(limitarRango(energia - juego.getGastoEnergetico()));
        setHambre(limitarRango(hambre +15 ));
        setNivelPereza(nivelPereza -7 );
        setNivelIrritabilidad(nivelIrritabilidad-9);
        IO.println(nombre +"jugo" +juego.getNombre());

    }

    @Override
    public String accion1() {
        return nombre + " se lame la pata con mucha calma(Nivel: " + nivelPereza + ").";

    }

    @Override
    public String accion2() {
        return nombre + " Esta maullando con fuerza(Nivel: " + nivelIrritabilidad + ").";
    }
    public String accion3() {
        ronroneando = true;
        return nombre +" Esta ronroneando(Nivel: "+nivelPereza+").";
    }
    public String accion4() {
        return nombre+ " Esta sacudiendo su cola con fuerza(Nivel: "+nivelIrritabilidad+").";
    }



        @Override
    public void dormir(){
        super.dormir();
        setEnergia(limitarRango(energia+15));
        setNivelPereza(nivelPereza+10);
        ronroneando=true;
    }
public boolean estaRonroneando(){
        return ronroneando;
}
    public void setNivelPereza(int nivelPereza) {
        this.nivelPereza = limitarRango(nivelPereza);
    }

    public void setNivelIrritabilidad(int nivelIrritabilidad) {
        this.nivelIrritabilidad = limitarRango(nivelIrritabilidad);
    }
    @Override
    public String toString(){
        return super.toString() + " | Especie: Gato | Irritabilidad: " + nivelIrritabilidad + " | Pereza: " + nivelPereza+"| Ronroneando: "+ (ronroneando?"Si":"No");

    }
}


