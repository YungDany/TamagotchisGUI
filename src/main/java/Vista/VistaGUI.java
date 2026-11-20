package Vista;

import Modelo.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class VistaGUI{

    public ComboBox<String> comboMascotas = new ComboBox<>();
    public Button btnAdoptar = new Button("+ Adoptar");

    public Label lblNombreEspecie = new Label("Sin mascota seleccionada");
    public TextArea areaDibujo = new TextArea();
    public Label lblEstadoAnimo = new Label("Ánimo: -");
    public Label lblDetalles = new Label("Detalles: -");

    public ProgressBar barHambre = new ProgressBar(0);
    public ProgressBar barEnergia = new ProgressBar(0);
    public ProgressBar barFelicidad = new ProgressBar(0);
    public ProgressBar barSalud = new ProgressBar(0);

    public Button btnAlimentar = new Button("Alimentar");
    public Button btnJugar = new Button("Jugar");
    public Button btnDormir = new Button("Dormir");
    public Button btnEspecial1 = new Button("Especial 1");
    public Button btnEspecial2 = new Button("Especial 2");
    public Button btnTiempo = new Button("Avanzar Tiempo (+1h)");

    public Label lblMensaje = new Label("Bienvenido al Simulador Tamagotchi");

    public void iniciarVentana(Stage stage){
        comboMascotas.setPromptText("Selecciona una mascota");
        comboMascotas.setPrefWidth(200);
        HBox panelSuperior = new HBox(10, new Label("Mascota:"), comboMascotas, btnAdoptar);
        panelSuperior.setAlignment(Pos.CENTER_LEFT);
        panelSuperior.setPadding(new Insets(10));

        areaDibujo.setEditable(false);
        areaDibujo.setFont(Font.font("Monospaced", 14));
        areaDibujo.setPrefSize(220, 140);
        VBox panelIzquierdo = new VBox(8, lblNombreEspecie, areaDibujo, lblEstadoAnimo, lblDetalles);
        panelIzquierdo.setPadding(new Insets(10));

        GridPane gridBarras = new GridPane();
        gridBarras.setHgap(10);
        gridBarras.setVgap(12);
        gridBarras.setPadding(new Insets(10));

        gridBarras.addRow(0, new Label("Hambre:"), barHambre);
        gridBarras.addRow(1, new Label("Energía:"), barEnergia);
        gridBarras.addRow(2, new Label("Felicidad:"), barFelicidad);
        gridBarras.addRow(3, new Label("Salud:"), barSalud);

        HBox panelCentral = new HBox(20, panelIzquierdo, gridBarras);
        panelCentral.setAlignment(Pos.CENTER);

        HBox filaBotones1 = new HBox(10, btnAlimentar, btnJugar, btnDormir);
        HBox filaBotones2 = new HBox(10, btnEspecial1, btnEspecial2, btnTiempo);
        filaBotones1.setAlignment(Pos.CENTER);
        filaBotones2.setAlignment(Pos.CENTER);

        lblMensaje.setStyle("-fx-font-weight: bold; -fx-text-fill: #2a5885;");
        VBox panelInferior = new VBox(10, filaBotones1, filaBotones2, lblMensaje);
        panelInferior.setAlignment(Pos.CENTER);
        panelInferior.setPadding(new Insets(10));

        BorderPane root = new BorderPane();
        root.setTop(panelSuperior);
        root.setCenter(panelCentral);
        root.setBottom(panelInferior);

        Scene scene = new Scene(root, 550, 420);
        stage.setTitle("Tamagotchi Virtual - POO");
        stage.setScene(scene);
        stage.show();



    }

    public void actualizarVista(Tamagotchi t) {
        if (t == null) return;
        lblNombreEspecie.setText(t.getNombre() + " (" + t.getClass().getSimpleName() + ")");
        areaDibujo.setText(t.obtenDraw());
        lblEstadoAnimo.setText("Estado: " + t.obtenerEstadoAnimo().getDescripcion() + (t.estaDurmiendo() ? " [Durmiendo]" : ""));
        lblDetalles.setText(t.obtenerDetallesEspecificos());

        // ProgressBar en JavaFX usa valores de 0.0 a 1.0
        barHambre.setProgress(t.getHambre() / 100.0);
        barEnergia.setProgress(t.getEnergia() / 100.0);
        barFelicidad.setProgress(t.getFelicidad() / 100.0);
        barSalud.setProgress(t.getSalud() / 100.0);
    }

}
