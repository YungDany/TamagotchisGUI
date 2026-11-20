module com.example.tamagotchisgui {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tamagotchisgui to javafx.fxml;
    exports com.example.tamagotchisgui;
}