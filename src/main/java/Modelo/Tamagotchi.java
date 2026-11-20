package Modelo;

public abstract class Tamagotchi implements TamagotchiActions{

    protected String nombre;
    protected int hambre;
    protected int energia;
    protected int felicidad;
    protected int salud;
    protected boolean durmiendo;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setHambre(int hambre) {
        this.hambre = hambre;
    }

    public void setEnergia(int energia) {
        this.energia = energia;
    }

    public void setFelicidad(int felicidad) {
        this.felicidad = felicidad;
    }

    public void setSalud(int salud) {
        this.salud = salud;
    }

    public String getNombre() {
        return nombre;
    }

    public int getHambre() {
        return hambre;
    }

    public int getEnergia() {
        return energia;
    }

    public int getFelicidad() {
        return felicidad;
    }

    public int getSalud() {
        return salud;
    }
    public int dormir(int horasdesuenio){
        return 0;

    }

    public void setDurmiendo(boolean durmiendo){
        this.durmiendo=durmiendo;
    }

    public boolean estaDurmiendo(){
        return durmiendo;
    }
    public Tamagotchi(String nombre, int hambre, int energia,int felicidad, int salud,boolean durmiendo){
        this.nombre=nombre;
        this.hambre=limitarRango(hambre);
        this.energia=limitarRango(energia);
        this.felicidad=limitarRango(felicidad);
        this.salud=limitarRango(salud);
        this.durmiendo=durmiendo;
    }

    public Tamagotchi(String nombre){
        this.nombre=nombre;
        this.hambre=30;
        this.energia=80;
        this.felicidad=70;
        this.salud=100;
        this.durmiendo=false;
    }

    protected int limitarRango(int valor){
        return Math.max(0,Math.min(100,valor));
    }

    public EstadoAnimo obtenerEstadoAnimo(){
        if(salud<30||hambre>85||energia<15){
            return EstadoAnimo.CRITICO;
        }
        if(hambre>60||energia<35||felicidad<40){
            return EstadoAnimo.REQUIERE_ATENCION;
        }
        if(salud>80&&energia>=60&&hambre<=30){
            return EstadoAnimo.EXCELENTE;
        }
        return EstadoAnimo.NORMAL;
    }

    public void pasarTiempo(){
        if(!isLife()){
            return;
        }
        if(durmiendo){
            setEnergia(limitarRango(this.energia+25));
            setHambre(limitarRango(this.hambre+5));
            if(this.energia>=100){
                this.durmiendo=false;
            }
        }else{
            setHambre(limitarRango(this.hambre+10));
            setEnergia(limitarRango(this.energia-8));
            setFelicidad(limitarRango(this.felicidad-7));
            if(this.hambre>=90||this.energia<=10){
                setSalud(this.salud-10);
            }
            //agregar to String
        }
    }

    @Override
    public void dormir(){
        this.durmiendo=true;
        setEnergia(this.energia+40);
    }

    public abstract String obtenDraw();
    public abstract String obtenerDetallesEspecificos();

public String toString() {
    return (nombre+"|Hambre: "+hambre+"|Energia: "+ energia+"Felicidad: "+felicidad+"Salud: "+salud+"Despierto: "+durmiendo);
}
public boolean isLife(){
    return salud>0;
}
public String despedida(){
    return "¡" + nombre +"ha fallecido";
}
}






