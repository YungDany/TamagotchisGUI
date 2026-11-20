package Controlador;

import Modelo.*;
import java.util.ArrayList;
import java.util.List;

public class ControladorGUI {

    private final Coleccion coleccion;

    public ControladorGUI() {
        this.coleccion = new Coleccion();
    }

    public List<Tamagotchi> getMascotas() {
        return coleccion.getLista();
    }

    public List<Tamagotchi> getMascotasOrdenadasPorFelicidad() {
        return coleccion.obtenerOrdenadosPorFelicidad();
    }

    public String crearMascota(String especie, String nombreLimpio, Tamagotchi[] creadaOut) {
        String nombre = (nombreLimpio == null || nombreLimpio.isBlank()) ? "SinNombre" : nombreLimpio.trim();

        Tamagotchi nueva = switch (especie) {
            case "Perro" -> new Perro(nombre);
            case "Gato" -> new Gato(nombre);
            case "Cuyo" -> new Cuyo(nombre);
            case "Muneca" -> new Muneca(nombre);
            case "Capibara" -> new Capibara(nombre);
            default -> new Perro(nombre);
        };

        coleccion.agregar(nueva);
        if (creadaOut != null && creadaOut.length > 0) {
            creadaOut[0] = nueva;
        }
        return "Has adoptado a " + nombre + " (" + nueva.getClass().getSimpleName() + ").";
    }

    public List<String> alimentar(Tamagotchi t, TipoAlimento alimento) {
        List<String> mensajes = new ArrayList<>();
        String error = validarMascotaViva(t);
        if (error != null) {
            mensajes.add(error);
            return mensajes;
        }

        t.alimentar(alimento);
        mensajes.add(t.getNombre() + " ha comido " + alimento.getNombre() + ".");
        if (!t.isLife()) {
            mensajes.add("¡" + t.getNombre() + " ha fallecido!");
        }
        return mensajes;
    }

    public List<String> jugar(Tamagotchi t, TipoJuego juego) {
        List<String> mensajes = new ArrayList<>();
        String error = validarMascotaViva(t);
        if (error != null) {
            mensajes.add(error);
            return mensajes;
        }

        t.jugar(juego);
        mensajes.add(t.getNombre() + " jugó a: " + juego.getNombre() + ".");
        if (!t.isLife()) {
            mensajes.add("¡" + t.getNombre() + " ha fallecido!");
        }
        return mensajes;
    }

    public List<String> dormir(Tamagotchi t) {
        List<String> mensajes = new ArrayList<>();
        String error = validarMascotaViva(t);
        if (error != null) {
            mensajes.add(error);
            return mensajes;
        }

        t.dormir();
        mensajes.add(t.getNombre() + " se ha ido a dormir.");
        return mensajes;
    }

    public List<String> ejecutarAccionEspecial(Tamagotchi t, int numero) {
        List<String> mensajes = new ArrayList<>();
        String error = validarMascotaViva(t);
        if (error != null) {
            mensajes.add(error);
            return mensajes;
        }

        String resultado = switch (numero) {
            case 1 -> t.accion1();
            case 2 -> t.accion2();
            case 3 -> t.accion3();
            case 4 -> t.accion4();
            default -> "Acción no válida.";
        };
        mensajes.add(resultado);
        if (!t.isLife()) {
            mensajes.add("¡" + t.getNombre() + " ha fallecido!");
        }
        return mensajes;
    }

    public List<String> avanzarTiempoTodos() {
        List<String> mensajes = new ArrayList<>();
        if (coleccion.estaVacia()) {
            mensajes.add("No hay mascotas para simular el paso del tiempo.");
            return mensajes;
        }

        coleccion.avanzarTiempoTodos();
        mensajes.add("Han pasado unas horas... Las estadísticas han cambiado.");

        for (Tamagotchi t : coleccion.getLista()) {
            if (!t.isLife()) {
                mensajes.add("¡" + t.getNombre() + " ha fallecido!");
            }
        }
        return mensajes;
    }

    private String validarMascotaViva(Tamagotchi t) {
        if (t == null) {
            return "Selecciona una mascota primero.";
        }
        if (!t.isLife()) {
            return "¡" + t.getNombre() + " ha fallecido! Ya no puede realizar más acciones.";
        }
        return null;
    }
}

