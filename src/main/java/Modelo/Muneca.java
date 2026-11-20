package Modelo;

    public class Muneca extends Tamagotchi{
        private int nivelCoqueta;
        private int nivelCaprichosa;
        public Muneca(String nombre, int hambre, int energia, int felicidad, int salud, boolean durmiendo,int nivelCaprichosa,int nivelCoqueta) {
            super(nombre, hambre, energia, felicidad, salud, durmiendo);
            this.nivelCaprichosa=limitarRango(nivelCaprichosa);
            this.nivelCoqueta=limitarRango(nivelCoqueta);
        }

        public Muneca(String nombre) {
            super(nombre);
            nivelCoqueta=40;
            nivelCaprichosa=60;
        }

        @Override
        public String obtenDraw() {
            return "";
        }

        @Override
        public String obtenerDetallesEspecificos() {
            return "Especie: Muñeca | Coqueta: " + nivelCoqueta + " | Caprichosa: " + nivelCaprichosa;
        }

        @Override
        public void alimentar(TipoAlimento alimento) {
            int bonusEnergy = switch(alimento) {
                case CROQUETAS -> 0;
                case FRUTA -> 4;
                case DULCES -> 3;
                case CESPED -> 0;
                case TE_FAKE ->14 ;
            };
            int bonusFelicidad= switch(alimento) {
                case CROQUETAS ->0 ;
                case FRUTA ->2 ;
                case DULCES ->3 ;
                case CESPED -> 0;
                case TE_FAKE -> 15;
            };
            int bonusLife= switch(alimento) {
                case CROQUETAS ->0;
                case FRUTA ->3 ;
                case DULCES ->1 ;
                case CESPED -> 0;
                case TE_FAKE -> 9;
            };
            setHambre(limitarRango(hambre-alimento.getReduccionHambre()));
            setEnergia(limitarRango(energia+alimento.getEnergiaBase()+bonusEnergy));
            setFelicidad(limitarRango(felicidad+bonusFelicidad));
            setSalud(limitarRango(salud+bonusLife));



        }

        @Override
        public void jugar(TipoJuego juego) {
            int bonusGame= switch (juego){
                case PERSEGUIR_PELOTA ->0 ;
                case TRUCOS ->5 ;
                case ESCONDIDAS ->15;
            };
            setFelicidad(limitarRango(felicidad + juego.getAumentoFelicidad() + bonusGame));
            setEnergia(limitarRango(energia - juego.getGastoEnergetico()));
            setHambre(limitarRango(hambre +5 ));
            setNivelCoqueta(nivelCoqueta +8 );
            IO.println(nombre +"jugo" +juego.getNombre());

        }

        @Override
        public String accion1() {
            return (nombre+" Esta aburrida(Nivel: "+nivelCaprichosa+").");
        }

        @Override
        public String accion2() {
            return (nombre+" Esta feliz(Nivel: "+nivelCoqueta+").");
        }
        public String accion3() {
            return (nombre+" Esta llorando(Nivel: "+nivelCaprichosa+").");
        }

        public String accion4() {
            return (nombre+" Le encanto su cambio de ropa(Nivel: "+nivelCoqueta+").");
        }


        public void setNivelCoqueta(int nivelCoqueta) {
            this.nivelCoqueta = limitarRango(nivelCoqueta);
        }

        public void setNivelCaprichosa(int nivelCaprichosa) {
            this.nivelCaprichosa = limitarRango(nivelCaprichosa) ;
        }
        @Override
        public String toString(){
            return super.toString() + " | Especie: Muñeca | Coqueta: " + nivelCoqueta + " | Caprichosa: " + nivelCaprichosa;
        }
    }



