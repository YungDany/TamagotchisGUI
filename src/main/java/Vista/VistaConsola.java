package Vista;

import Modelo.Tamagotchi;
import Modelo.TipoAlimento;
import Modelo.TipoJuego;

import java.util.List;
import java.util.Scanner;

public class VistaConsola {
    private Scanner scanner;

    public VistaConsola() {
        this.scanner = new Scanner(System.in);
    }

    public void mostrarBannerInicio() {

        System.out.println("    Simulador de Tamagotchi    ");

    }

    public int mostrarMenuPrincipal() {
        System.out.println("\n--- Menú principal ---");
        System.out.println("1. Crear nuevo Tamagotchi");
        System.out.println("2. Interactuar con un Tamagotchi");
        System.out.println("3. Ver todos mis Tamagotchis");
        System.out.println("4. Avanzar el tiempo");
        System.out.println("5. Salir");
        return leerEntero("Elige una opcion: ", 1, 5);
    }

    public int seleccionarUnaEspecie() {
        System.out.println("\n--- Seleccione una especie ---");
        System.out.println("1. Perro");
        System.out.println("2. Gato");
        System.out.println("3. Cuyo");
        System.out.println("4. Muneca");
        System.out.println("5. Capibara");
        return leerEntero("Especie: ", 1, 5);
    }

    public String pedirNombreMascota() {
        System.out.print("Escribe el nombre de tu mascota: ");
        String nombre = scanner.nextLine();
        if (nombre.trim().isEmpty()) {
            return "SinNombre";
        }
        return nombre;
    }

    public int seleccionarMascota(List<Tamagotchi> mascotas) {
        System.out.println("\n--- Sus Mascotas ---");
        for (int i = 0; i < mascotas.size(); i++) {
            Tamagotchi t = mascotas.get(i);
            System.out.println((i + 1) + ". " + t.getNombre() + " (" + t.getClass().getSimpleName() + ")");
        }
        int opcion = leerEntero("Selecciona una mascota: ", 1, mascotas.size());
        return opcion - 1;
    }

    public int mostrarMenuInteraccion(Tamagotchi t) {
        mostrarTarjetaEstado(t);
        System.out.println("\n--- Acciones ---");
        System.out.println("1. Alimentar");
        System.out.println("2. Jugar");
        System.out.println("3. Dormir");
        System.out.println("4. Comportamiento especial 1");
        System.out.println("5. Comportamiento especial 2");
        System.out.println("6.Comportamiento especial 3");
        System.out.println("7.Comportamiento especial 4");
        System.out.println("8. Volver al menu");

        return leerEntero("Que deseas hacer?: ", 1, 8);
    }

    public TipoAlimento seleccionarAlimento() {
        System.out.println("\n--- Alimentos ---");
        TipoAlimento[] alimentos = TipoAlimento.values();
        for (int i = 0; i < alimentos.length; i++) {
            System.out.println((i + 1) + ". " + alimentos[i].getNombre());
        }
        int opcion = leerEntero("Elige un alimento: ", 1, alimentos.length);
        return alimentos[opcion - 1];
    }

    public TipoJuego seleccionarJuego() {
        System.out.println("\n--- JUEGOS ---");
        TipoJuego[] juegos = TipoJuego.values();
        for (int i = 0; i < juegos.length; i++) {
            System.out.println((i + 1) + ". " + juegos[i].getNombre());
        }
        int opcion = leerEntero("Elige un juego: ", 1, juegos.length);
        return juegos[opcion - 1];
    }

    public void mostrarTarjetaEstado(Tamagotchi t) {
        System.out.println("\n");
        System.out.println("Nombre: " + t.getNombre() + " (" + t.getClass().getSimpleName() + ")");
        if(!t.obtenDraw().isEmpty()){
            System.out.println(t.obtenDraw());
        }
        System.out.println("Estado de vida: "+ (t.isLife()?"Vivo":"Fallecido"));
        System.out.println("Estado de animo: " + t.obtenerEstadoAnimo().getDescripcion());

        if (t.estaDurmiendo()) {
            System.out.println("Durmiendo: Si");
        } else {
            System.out.println("Durmiendo: No");
        }

        System.out.println("Detalles: " + t.obtenerDetallesEspecificos());
        System.out.println("Hambre: " + t.getHambre() + "/100");
        System.out.println("Energia: " + t.getEnergia() + "/100");
        System.out.println("Felicidad: " + t.getFelicidad() + "/100");
        System.out.println("Salud: " + t.getSalud() + "/100");

    }

    public void mostrarMensaje(String mensaje) {
        System.out.println("\n" + mensaje);
    }

    private int leerEntero(String mensaje, int min, int max) {
        int numero = -1;
        boolean valido = false;

        while (!valido) {
            System.out.print(mensaje);
            try {
                numero = Integer.parseInt(scanner.nextLine());
                if (numero >= min && numero <= max) {
                    valido = true;
                } else {
                    System.out.println("Numero fuera de rango. Intenta de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: ingresa un numero valido.");
            }
        }
        return numero;
    }
//
}
