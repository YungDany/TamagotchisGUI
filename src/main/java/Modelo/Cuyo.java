package Modelo;

public class Cuyo extends Tamagotchi{
private int nivelMiedo;
private int nivelChillido;
    public Cuyo(String nombre, int hambre, int energia, int felicidad, int salud, boolean durmiendo,int nivelMiedo,int nivelChillido) {
        super(nombre, hambre, energia, felicidad, salud, durmiendo);
        this.nivelMiedo=limitarRango(nivelMiedo);
        this.nivelChillido=limitarRango(nivelChillido);
    }

    public Cuyo(String nombre) {
        super(nombre);
        nivelMiedo=20;
        nivelChillido=10;
    }

    @Override
    public String obtenDraw() {
        return "";
    }

    @Override
    public String obtenerDetallesEspecificos() {
        return "Especie: Cuyo | Miedo: " + nivelMiedo + " | Chillido: " + nivelChillido;
    }
    @Override
    public void alimentar(TipoAlimento alimento) {
        int bonusEnergy = switch(alimento) {
            case CROQUETAS ->10 ;
            case FRUTA -> 8;
            case DULCES -> -5;
            case CESPED -> 15;
            case TE_FAKE -> 0;
        };
        int bonusFelicidad= switch(alimento) {
            case CROQUETAS -> 8;
            case FRUTA -> 18;
            case DULCES -> 5;
            case CESPED -> 12;
            case TE_FAKE -> 4;
        };
        int bonusLife= switch(alimento) {
            case CROQUETAS ->5;
            case FRUTA -> 3;
            case DULCES -> -15;
            case CESPED -> 8;
            case TE_FAKE -> -7;
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
            case TRUCOS -> 10;
            case ESCONDIDAS ->18;
        };
        setFelicidad(limitarRango(felicidad + juego.getAumentoFelicidad() + bonusGame));
        setEnergia(limitarRango(energia - juego.getGastoEnergetico()));
        setHambre(limitarRango(hambre +7 ));
        setNivelMiedo( nivelMiedo-15 );
        IO.println(nombre +"jugo" +juego.getNombre());

    }
    @Override
    public String accion1() {
        return (nombre+"Se quedo Congelado(Nivel: "+nivelMiedo+").");

    }

    @Override
    public String accion2() {
        return (nombre+"cuy cuy(Nivel: "+nivelChillido+").");
    }
    public String accion3() {
        return (nombre+"esta roendo la madera(Nivel: "+nivelChillido+").");
    }
    public String accion4() {
        return (nombre+"le asusto el ruido de la puerta(Nivel: "+nivelMiedo+").");
    }

    public void setNivelMiedo(int nivelMiedo) {
        this.nivelMiedo = limitarRango(nivelMiedo);
    }

    public void setNivelChillido(int nivelChillido) {
        this.nivelChillido = limitarRango(nivelChillido) ;
    }
    @Override
    public String toString(){
        return super.toString() + " | Especie: Cuyo | Miedo: " + nivelMiedo + " | Chillido: " + nivelChillido;
    }
}


