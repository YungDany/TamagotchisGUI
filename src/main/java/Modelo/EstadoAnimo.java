package Modelo;

public enum EstadoAnimo {
    EXCELENTE("Está excelente y con mucha vida."),
    NORMAL("Está tranquilo y estable."),
    REQUIERE_ATENCION("Necesita atención lo más pronto posible"),
    CRITICO("Se encuentra en estado crítico. Requiere cuidado de inmediato.");

    private final String descripcion;

    EstadoAnimo(String descripcion){
        this.descripcion=descripcion;
    }

    public String getDescripcion(){
        return descripcion;
    }
}
