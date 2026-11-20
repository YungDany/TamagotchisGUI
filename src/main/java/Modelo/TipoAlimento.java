package Modelo;

public enum TipoAlimento {
    CROQUETAS("Croquetas",30,15),
    FRUTA("Fruta",20,20),
    DULCES("Dulces energéticos",10,35),
    CESPED("Cesped",35,25),
    TE_FAKE("Te",25,5);

    private final String nombre;
    private final int reduccionHambre;
    private final int energiaBase;

    TipoAlimento(String nombre, int reduccionHambre, int energiaBase){
        this.nombre=nombre;
        this.reduccionHambre=reduccionHambre;
        this.energiaBase=energiaBase;
    }

    public String getNombre(){
        return nombre;
    }

    public int getReduccionHambre(){
        return reduccionHambre;
    }

    public int getEnergiaBase(){
        return energiaBase;
    }
}
