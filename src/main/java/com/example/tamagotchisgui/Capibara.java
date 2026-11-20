package com.example.tamagotchisgui;

public class Capibara extends Tamagotchi{
private int nivelChill;
 private int nivelSociable;
    public Capibara(String nombre, int hambre, int energia, int felicidad, int salud, boolean durmiendo,int nivelChill,int nivelSociable) {
        super(nombre, hambre, energia, felicidad, salud, durmiendo);
        this.nivelChill=limitarRango(nivelChill);
        this.nivelSociable=limitarRango(nivelSociable);
    }

    public Capibara(String nombre) {
        super(nombre);
        nivelSociable=60;
        nivelChill=40;
    }

    @Override
    public String obtenDraw() {
        return "";
    }

    @Override
    public String obtenerDetallesEspecificos() {
        return "Especie: Capibara | Chill: " + nivelChill + " | Sociable: " + nivelSociable;
    }
    @Override
    public void alimentar(TipoAlimento alimento) {
        int bonusEnergy = switch(alimento) {
            case CROQUETAS ->10 ;
            case FRUTA -> 8;
            case DULCES -> -5;
            case CESPED ->18 ;
            case TE_FAKE ->0 ;
        };
        int bonusFelicidad= switch(alimento) {
            case CROQUETAS -> 8;
            case FRUTA -> 16;
            case DULCES -> -5;
            case CESPED -> 15;
            case TE_FAKE -> 0;
        };
        int bonusLife= switch(alimento) {
            case CROQUETAS ->5;
            case FRUTA -> 4;
            case DULCES ->-15 ;
            case CESPED ->10 ;
            case TE_FAKE -> -10;
        };
        setHambre(limitarRango(hambre-alimento.getReduccionHambre()));
        setEnergia(limitarRango(energia+alimento.getEnergiaBase()+bonusEnergy));
        setFelicidad(limitarRango(felicidad+bonusFelicidad));
        setSalud(limitarRango(salud+bonusLife));



    }

    @Override
    public void jugar(TipoJuego juego) {
        int bonusGame= switch (juego){
            case PERSEGUIR_PELOTA ->8 ;
            case TRUCOS ->11 ;
            case ESCONDIDAS ->14;
        };
        setFelicidad(limitarRango(felicidad + juego.getAumentoFelicidad() + bonusGame));
        setEnergia(limitarRango(energia - juego.getGastoEnergetico()));
        setHambre(limitarRango(hambre +8 ));
        setNivelSociable(nivelSociable+8);
        System.out.println(nombre +"jugo" +juego.getNombre());

    }

    //

    @Override
    public String accion1() {
        return (nombre+"Esta viendo el paisaje(Nivel: "+nivelChill+").");
    }

    @Override
    public String accion2() {
        return (nombre+"Esta arriba de un cocodrilo!!(Nivel: "+nivelSociable+").");
    }
    public String accion3() {
        return (nombre+"Esta acostado a tu lado(Nivel: "+nivelSociable+").");
    }

    public String accion4() {
        return (nombre+"Esta descansando en el rio(Nivel: "+nivelChill+").");
    }


    public void setNivelSociable(int nivelSociable) {
        this.nivelSociable = limitarRango(nivelSociable);
    }

    public void setNivelChill(int nivelChill) {
        this.nivelChill = limitarRango(nivelChill) ;
    }
    @Override
    public String toString(){
        return super.toString() + " | Especie: Capibara | Chill: " + nivelChill + " | Sociable: " + nivelSociable;

    }
}




