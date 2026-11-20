package Vista;

import Modelo.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.net.URL;

public class VistaGUI{

    public ComboBox<String> comboMascotas = new ComboBox<>();
    public Button btnAdoptar = new Button("+ Adoptar");

    public Label lblNombreEspecie = new Label("Sin mascota seleccionada");
    public TextArea areaDibujo = new TextArea();
    public ImageView imgMascota = new ImageView();
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
    public Button btnEspecial3 = new Button("Especial 3");
    public Button btnEspecial4 = new Button("Especial 4");

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
        imgMascota.setFitWidth(200);
        imgMascota.setFitHeight(160);
        imgMascota.setPreserveRatio(true);
        VBox panelIzquierdo = new VBox(8, lblNombreEspecie, imgMascota, lblEstadoAnimo, lblDetalles);
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
        HBox filaBotones2 = new HBox(10, btnEspecial1, btnEspecial2,btnEspecial3,btnEspecial4,btnTiempo);
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

        Scene scene = new Scene(root, 820, 560);
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
        boolean viva = t.isLife();
        btnAlimentar.setDisable(!viva);
        btnJugar.setDisable(!viva);
        btnDormir.setDisable(!viva);
        btnEspecial1.setDisable(!viva);
        btnEspecial2.setDisable(!viva);
        btnEspecial3.setDisable(!viva);
        btnEspecial4.setDisable(!viva);
        actualizarImagenMascota(t);

    }
    private void actualizarImagenMascota(Tamagotchi t) {
        String especie = t.getClass().getSimpleName().toLowerCase();
        String archivo = switch (especie) {
            case "capibara" -> "capibara.jpg";
            case "cuyo" -> "cuyo.jpg";
            case "gato" -> "gato.jpg";
            case "muneca" -> "muneca.jpg";
            case "perro" -> "perro.jpg";
            default -> especie + ".jpg";
        };

        URL url = getClass().getResource("/Imagenes/" + archivo);
        if (url != null) {
            imgMascota.setImage(new Image(url.toExternalForm()));
        } else {
            imgMascota.setImage(null);
        }
    }
}

