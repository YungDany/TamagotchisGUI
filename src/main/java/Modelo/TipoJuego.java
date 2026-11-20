package Modelo;

public enum TipoJuego {
    PERSEGUIR_PELOTA("Perseguir la pelota",25,20),
    ESCONDIDAS("Jugar a las escondidas",20,10),
    TRUCOS("Entrenamiento de trucos",30,25);

    private final String nombre;
    private final int aumentoFelicidad;
    private final int gastoEnergetico;

    TipoJuego(String nombre, int aumentoFelicidad,int gastoEnergetico){
        this.nombre=nombre;
        this.aumentoFelicidad=aumentoFelicidad;
        this.gastoEnergetico=gastoEnergetico;
    }

    public String getNombre(){
        return nombre;
    }
    public int getAumentoFelicidad(){
        return aumentoFelicidad;
    }
    public int getGastoEnergetico(){
        return gastoEnergetico;
    }
}
