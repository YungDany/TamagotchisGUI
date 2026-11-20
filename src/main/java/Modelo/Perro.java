package Modelo;

public class Perro extends Tamagotchi{
    private int nivelImperactividad;
    private int nivelAgresividad;

        public Perro(String nombre, int hambre, int energia, int felicidad, int salud, boolean durmiendo, int nivelImperactividad, int nivelAgresividad) {
            super(nombre, hambre, energia, felicidad, salud, durmiendo);
            this.nivelImperactividad = limitarRango(nivelImperactividad);
            this.nivelAgresividad = limitarRango(nivelAgresividad);
        }

        public Perro(String nombre) {
            super(nombre);
            this.nivelImperactividad = 70;
            this.nivelAgresividad = 20;
        }

        @Override
        public void alimentar(TipoAlimento alimento) {
            int bonusEnergy = switch(alimento) {
                case CROQUETAS -> 20;
                case FRUTA -> 0;
                case DULCES -> 10;
                case CESPED -> 0;
                case TE_FAKE -> 0;
            };
            int bonusFelicidad= switch(alimento) {
                case CROQUETAS -> 10;
                case FRUTA -> 5;
                case DULCES -> 12;
                case CESPED -> 0;
                case TE_FAKE -> 0;
            };
            int bonusLife= switch(alimento) {
                case CROQUETAS -> 5;
                case FRUTA -> 5;
                case DULCES -> -8;
                case CESPED -> 0;
                case TE_FAKE -> -5;
            };
    setHambre(limitarRango(hambre-alimento.getReduccionHambre()));
    setEnergia(limitarRango(energia+alimento.getEnergiaBase()+bonusEnergy));
    setFelicidad(limitarRango(felicidad+bonusFelicidad));
    setSalud(limitarRango(salud+bonusLife));



        }

        @Override
        public void jugar(TipoJuego juego) {
           int bonusGame= switch (juego){
               case PERSEGUIR_PELOTA -> 7;
               case TRUCOS -> 5;
               case ESCONDIDAS ->3;
           };
            setFelicidad(limitarRango(felicidad + juego.getAumentoFelicidad() + bonusGame));
            setEnergia(limitarRango(energia - juego.getGastoEnergetico()));
            setHambre(limitarRango(hambre + 10));
            setNivelImperactividad(nivelImperactividad + 3);
            IO.println(nombre + " jugo" + juego.getNombre());
        }

        @Override
        public String accion1() {
            return nombre + " Ansioso de que regresaras(Nivel: " + nivelAgresividad + ").";
        }
    @Override
    public String accion2() {
        return nombre + " Esta persiguiendo su cola (Nivel: " + nivelImperactividad + ").";
    }
        @Override
        public String accion3(){
            return nombre + " Guau guau (Nivel: " + nivelAgresividad + ").";
        }
        @Override

        public String accion4(){
            return nombre+" Esta escarbando la tierra (Nivel: " + nivelImperactividad + ").";

        }
        @Override
        public String obtenDraw() {
            return "";
        }

        @Override
        public String toString(){
            return super.toString() + " | Especie: Perro | Imperactividad: " + nivelImperactividad + " | Agresividad: " + nivelAgresividad;
        }
        @Override
        public String obtenerDetallesEspecificos() {
            return "Especie: Perro | Imperactividad: " + nivelImperactividad + " | Agresividad: " + nivelAgresividad;
        }


        public int getNivelImperactividad() {

            return nivelImperactividad;
        }

        public void setNivelImperactividad(int nivelImperactividad) {
            this.nivelImperactividad = limitarRango(nivelImperactividad);
        }

        public int getNivelAgresividad() {
            return nivelAgresividad;
        }

        public void setNivelAgresividad(int nivelAgresividad) {
            this.nivelAgresividad = limitarRango(nivelAgresividad);
        }
    }

