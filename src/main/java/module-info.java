module edu.miracosta.cs112.ic24_tipcalculatorfx {
    requires javafx.controls;
    requires javafx.fxml;


    opens edu.miracosta.cs112.ic24_tipcalculatorfx to javafx.fxml;
    exports edu.miracosta.cs112.ic24_tipcalculatorfx;
}