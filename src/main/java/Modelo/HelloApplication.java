package Modelo;
import Vista.VistaGUI;
import Controlador.ControladorGUI;
import Modelo.Tamagotchi;
import Modelo.TipoAlimento;
import Modelo.TipoJuego;
import javafx.application.Application;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;

public class HelloApplication extends Application {

    private ControladorGUI controlador;
    private VistaGUI vista;
    private Tamagotchi mascotaActual;

    @Override
    public void start(Stage stage) {
        controlador = new ControladorGUI();
        vista = new VistaGUI();

        vista.iniciarVentana(stage);
        configurarEventos();
    }

    private void configurarEventos() {
        // Cambiar de mascota al seleccionar otra en el ComboBox
        vista.comboMascotas.setOnAction(e -> {
            String etiqueta = vista.comboMascotas.getValue();
            if (etiqueta != null) {
                mascotaActual = buscarPorEtiqueta(etiqueta);
                vista.actualizarVista(mascotaActual);
            }
        });

        // Adoptar nueva mascota
        vista.btnAdoptar.setOnAction(e -> {
            TextInputDialog dialogNombre = new TextInputDialog("Max");
            dialogNombre.setTitle("Adoptar Mascota");
            dialogNombre.setHeaderText("Ingresa el nombre de tu Tamagotchi");
            dialogNombre.setContentText("Nombre:");

            Optional<String> nombreOpt = dialogNombre.showAndWait();
            if (nombreOpt.isEmpty()) return;

            List<String> especies = List.of("Perro", "Gato", "Cuyo", "Muneca", "Capibara");
            ChoiceDialog<String> dialogEspecie = new ChoiceDialog<>("Perro", especies);
            dialogEspecie.setTitle("Elegir Especie");
            dialogEspecie.setHeaderText("Selecciona la especie");
            dialogEspecie.setContentText("Especie:");

            Optional<String> especieOpt = dialogEspecie.showAndWait();
            if (especieOpt.isEmpty()) return;

            Tamagotchi[] creada = new Tamagotchi[1];
            String mensaje = controlador.crearMascota(especieOpt.get(), nombreOpt.get(), creada);

            if (creada[0] != null) {
                mascotaActual = creada[0];
                refrescarCombo(mascotaActual);
                vista.actualizarVista(mascotaActual);
            }
            vista.lblMensaje.setText(mensaje);
        });

        // Alimentar
        vista.btnAlimentar.setOnAction(e -> {
            if (mascotaActual == null) {
                vista.lblMensaje.setText("Primero adopta o selecciona una mascota.");
                return;
            }
            ChoiceDialog<TipoAlimento> dialog = new ChoiceDialog<>(
                    TipoAlimento.values()[0], TipoAlimento.values()
            );
            dialog.setTitle("Alimentar");
            dialog.setHeaderText("Elige un alimento para " + mascotaActual.getNombre());
            dialog.setContentText("Alimento:");

            Optional<TipoAlimento> elegido = dialog.showAndWait();
            if (elegido.isPresent()) {
                List<String> msgs = controlador.alimentar(mascotaActual, elegido.get());
                actualizarPantalla(msgs);
            }
        });

        // Jugar
        vista.btnJugar.setOnAction(e -> {
            if (mascotaActual == null) {
                vista.lblMensaje.setText("Primero adopta o selecciona una mascota.");
                return;
            }
            ChoiceDialog<TipoJuego> dialog = new ChoiceDialog<>(
                    TipoJuego.values()[0], TipoJuego.values()
            );
            dialog.setTitle("Jugar");
            dialog.setHeaderText("Elige un juego para " + mascotaActual.getNombre());
            dialog.setContentText("Juego:");

            Optional<TipoJuego> elegido = dialog.showAndWait();
            if (elegido.isPresent()) {
                List<String> msgs = controlador.jugar(mascotaActual, elegido.get());
                actualizarPantalla(msgs);
            }
        });

        // Dormir
        vista.btnDormir.setOnAction(e -> {
            List<String> msgs = controlador.dormir(mascotaActual);
            actualizarPantalla(msgs);
        });

        // Acciones especiales 1 y 2
        vista.btnEspecial1.setOnAction(e -> {
            List<String> msgs = controlador.ejecutarAccionEspecial(mascotaActual, 1);
            actualizarPantalla(msgs);
        });

        vista.btnEspecial2.setOnAction(e -> {
            List<String> msgs = controlador.ejecutarAccionEspecial(mascotaActual, 2);
            actualizarPantalla(msgs);
        });

        // Avanzar tiempo (+1h) en todas las mascotas
        vista.btnTiempo.setOnAction(e -> {
            List<String> msgs = controlador.avanzarTiempoTodos();
            actualizarPantalla(msgs);
        });
    }

    private void actualizarPantalla(List<String> mensajes) {
        if (mascotaActual != null) {
            refrescarCombo(mascotaActual);
            vista.actualizarVista(mascotaActual);
        }
        if (mensajes != null && !mensajes.isEmpty()) {
            vista.lblMensaje.setText(String.join(" | ", mensajes));
        }
    }

    private void refrescarCombo(Tamagotchi seleccionada) {
        vista.comboMascotas.getItems().clear();
        for (Tamagotchi t : controlador.getMascotas()) {
            vista.comboMascotas.getItems().add(formatearMascota(t));
        }
        if (seleccionada != null) {
            vista.comboMascotas.getSelectionModel().select(formatearMascota(seleccionada));
        }
    }

    private Tamagotchi buscarPorEtiqueta(String etiqueta) {
        for (Tamagotchi t : controlador.getMascotas()) {
            if (formatearMascota(t).equals(etiqueta)) {
                return t;
            }
        }
        return null;
    }

    private String formatearMascota(Tamagotchi t) {
        String estado = t.isLife() ? "" : " [FALLECIDO]";
        return t.getNombre() + " (" + t.getClass().getSimpleName() + ")" + estado;
    }

    public static void main(String[] args) {
        launch(args);
    }
}