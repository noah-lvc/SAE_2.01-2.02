module com.example.sae_202_203 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.xml;

    opens com.example.sae_202_203 to javafx.fxml;
    exports vue;
    exports modele;
    exports controleur;
    exports algo;
    exports test;
}