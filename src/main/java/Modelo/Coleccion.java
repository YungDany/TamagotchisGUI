package Modelo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Coleccion {
    private final ArrayList<Tamagotchi> misJuguetes;

    public Coleccion() {
        this.misJuguetes = new ArrayList<>();
    }

    public void agregar(Tamagotchi t) {
        misJuguetes.add(t);
    }

    public Tamagotchi obtener(int indice) {
        if (indice >= 0 && indice < misJuguetes.size()) {
            return misJuguetes.get(indice);
        }
        return null;
    }

    public boolean estaVacia() {
        return misJuguetes.isEmpty();
    }

    public List<Tamagotchi> getLista() {
        return misJuguetes;
    }

    // Devuelve una lista ordenada por felicidad de mayor a menor usando Lambdas
    public List<Tamagotchi> obtenerOrdenadosPorFelicidad() {
        return misJuguetes.stream()
                .sorted(Comparator.comparingInt(Tamagotchi::getFelicidad).reversed())
                .collect(Collectors.toList());
    }

    // Cuenta cuántos están en estado crítico
    public long contarCriticos() {
        return misJuguetes.stream()
                .filter(t -> t.obtenerEstadoAnimo() == EstadoAnimo.CRITICO)
                .count();
    }

    // Avanza el tiempo para todos
    public void avanzarTiempoTodos() {
        misJuguetes.forEach(Tamagotchi::pasarTiempo);
    }

    //muestra los tamagochis existentes

    public String listaJuguetes() {
        if (misJuguetes.isEmpty()) {
            return "No hay Tamagotchis en la colección.";
        }
        StringBuffer cadena = new StringBuffer();
        for (int i = 0; i < misJuguetes.size(); i++) {
            Tamagotchi t = misJuguetes.get(i);
            cadena.append(i + 1)
                    .append(". ")
                    .append(t.getNombre())
                    .append(" (")
                    .append(t.getClass().getSimpleName())
                    .append(") - Estado: ")
                    .append(t.obtenerEstadoAnimo())
                    .append("\n");
        }
        return cadena.toString();
    }
}