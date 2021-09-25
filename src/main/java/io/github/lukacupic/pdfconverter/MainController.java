package io.github.lukacupic.pdfconverter;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class MainController {

    private Stage stage;

    @FXML
    protected Label loadDescription;

    @FXML
    protected void onFileChoose() {
        final FileChooser fileChooser = new FileChooser();
        List<File> list = fileChooser.showOpenMultipleDialog(stage);
        if (list != null) {
            for (File imageFile : list) {
                try {
                    convertImage(imageFile);
                } catch (Exception e) {
                    showErrorPopup();
                    return;
                }
            }
            showSuccessPopup();
        }
    }

    private void showSuccessPopup() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setTitle("Message");
        alert.setHeaderText("Success!");
        alert.setContentText("Conversion completed successfully!");
        alert.setResizable(true);

        alert.showAndWait();
    }

    private void showErrorPopup() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setTitle("Error");
        alert.setHeaderText("Error!");
        alert.setContentText("An error has occurred during conversion. Please try again.");
        alert.setResizable(true);

        alert.showAndWait();
    }

    private void convertImage(File imageFile) throws DocumentException, IOException {
        String imagePath = imageFile.getAbsolutePath();
        String pdfPath = imagePath.substring(0, imagePath.lastIndexOf('.')) + ".pdf";

        Image image = Image.getInstance(imagePath);

        Document document = new Document(image);
        PdfWriter.getInstance(document, new FileOutputStream(pdfPath));
        document.open();

        document.setPageSize(image);
        image.setAbsolutePosition(0, 0);

        document.add(image);
        document.close();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}