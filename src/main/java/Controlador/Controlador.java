package Controlador;

import Modelo.*;
import Vista.VistaConsola;
import java.util.List;

public class Controlador {
    private final Coleccion coleccion;
    private final VistaConsola vista;

    public Controlador(VistaConsola vista) {
        this.vista = vista;
        this.coleccion = new Coleccion();
    }

    public void iniciar() {
        vista.mostrarBannerInicio();
        boolean ejecutando = true;

        while (ejecutando) {
            int opcion = vista.mostrarMenuPrincipal();
            switch (opcion) {
                case 1 -> crearMascota();
                case 2 -> gestionarMascota();
                case 3 -> mostrarResumenRefugio();
                case 4 -> simularPasoDelTiempo();
                case 5 -> {
                    vista.mostrarMensaje("Cerrando simulador. ¡Hasta luego!");
                    ejecutando = false;
                }
            }
        }
    }

    private void crearMascota() {
        int tipo = vista.seleccionarUnaEspecie();
        String nombre = vista.pedirNombreMascota();

        Tamagotchi nuevo = switch (tipo) {
            case 1 -> new Perro(nombre);
            case 2 -> new Gato(nombre);
            case 3 -> new Cuyo(nombre);
            case 4 -> new Muneca(nombre);
            case 5 -> new Capibara(nombre);
            default -> throw new IllegalArgumentException("Especie no válida");
        };

        coleccion.agregar(nuevo);
        vista.mostrarMensaje("¡Has adoptado a " + nombre + " (" + nuevo.getClass().getSimpleName() + ")!");
    }

    private void gestionarMascota() {
        if (coleccion.estaVacia()) {
            vista.mostrarMensaje("Aún no tienes mascotas. ¡Adopta una primero!");
            return;
        }

        int indice = vista.seleccionarMascota(coleccion.getLista());
        Tamagotchi seleccionada = coleccion.obtener(indice);
        boolean enSubmenu = true;

        while (enSubmenu) {
            int accion = vista.mostrarMenuInteraccion(seleccionada);
            if (accion == 8) {
                enSubmenu = false;
                continue;
            }
            if (!seleccionada.isLife()) {
                vista.mostrarMensaje("¡" + seleccionada.getNombre() + "ha fallecido ya no hay nada que hacer");
                continue;
            }
            switch (accion) {
                case 1 -> {
                    TipoAlimento alimento = vista.seleccionarAlimento();
                    seleccionada.alimentar(alimento);
                    vista.mostrarMensaje(seleccionada.getNombre() + " ha comido " + alimento.getNombre() + ".");
                }
                case 2 -> {
                    TipoJuego juego = vista.seleccionarJuego();
                    seleccionada.jugar(juego);
                    vista.mostrarMensaje(seleccionada.getNombre() + " jugó a: " + juego.getNombre() + ".");
                }
                case 3 -> {
                    seleccionada.dormir();
                    vista.mostrarMensaje(seleccionada.getNombre() + " se ha ido a dormir.");
                }
                case 4 -> vista.mostrarMensaje(seleccionada.accion1());
                case 5 -> vista.mostrarMensaje(seleccionada.accion2());
                case 6 -> vista.mostrarMensaje(seleccionada.accion3());
                case 7 -> vista.mostrarMensaje(seleccionada.accion4());
                case 8 -> enSubmenu = false;
            }
        }
    }

    private void mostrarResumenRefugio() {
        if (coleccion.estaVacia()) {
            vista.mostrarMensaje("No hay Tamagotchis registrados.");
            return;
        }

        List<Tamagotchi> ordenados = coleccion.obtenerOrdenadosPorFelicidad();
        ordenados.forEach(vista::mostrarTarjetaEstado);

        long enPeligro = coleccion.contarCriticos();
        if (enPeligro > 0) {
            vista.mostrarMensaje("¡ALERTA! Tienes " + enPeligro + " mascota(s) en estado crítico.");
        }
    }

    private void simularPasoDelTiempo() {
        if (coleccion.estaVacia()) {
            vista.mostrarMensaje("No hay mascotas para simular el paso del tiempo.");
            return;
        }
        coleccion.avanzarTiempoTodos();
        vista.mostrarMensaje("Han pasado unas horas... Las estadísticas han cambiado.");
        for (Tamagotchi t : coleccion.getLista()) {
            if (!t.isLife()) {
                vista.mostrarMensaje(t.despedida());
            }
        }
    }
}
