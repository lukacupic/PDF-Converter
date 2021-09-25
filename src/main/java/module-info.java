module com.example.pdfconverter.pdfconverter {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires itextpdf;

    opens io.github.lukacupic.pdfconverter to javafx.fxml;
    exports io.github.lukacupic.pdfconverter;
}