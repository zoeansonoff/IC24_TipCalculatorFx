package edu.miracosta.cs112.ic24_tipcalculatorfx;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.text.NumberFormat;

public class Main extends Application {

    // Fields:
    // Nodes we want to display
    // Make fields for each Node that
    // changes or is interacted with by user

    private NumberFormat currency = NumberFormat.getCurrencyInstance();
    private TextField billAmountTF = new TextField();
    private Slider tipPercentSlider = new Slider(0, 30, 15);

    private TextField tipAmountTF = new TextField();
    private TextField totalAmountTF = new TextField();

    private Button clearButton = new Button("Clear");
    private Button calculateButton = new Button("Calculate");

    private Label tipPercentLabel = new Label("15%");



    @Override
    public void start(Stage stage) {

        // GridPane - table layout for placing "Nodes"  (column, row)
        GridPane pane = new GridPane();

        pane.add(new Label("Bill Amount:"), 0, 0);
        pane.add(tipPercentLabel, 0, 1);
        pane.add(new Label("Tip Amount:"), 0, 2);
        pane.add(new Label("Total Amount:"), 0, 3);

        pane.add(billAmountTF, 1, 0);
        pane.add(tipPercentSlider, 1, 1);
        pane.add(tipAmountTF, 1, 2);
        pane.add(totalAmountTF, 1, 3);
        // Clear and Calculate buttons have to share the same "cell"
        // Put both buttons in a Horizontal Box:
        HBox hBox = new HBox(clearButton, calculateButton);
        pane.add(hBox, 1, 4);

        // Center the GridPane (horizontally and vertically)
        pane.setAlignment(Pos.CENTER);
        // Set the spacing of the GridPane
        pane.setHgap(5);
        pane.setVgap(5);

        // Set the spacing of the HBox
        hBox.setSpacing(5);
        hBox.setAlignment(Pos.CENTER_RIGHT);

        // Configure the slider's tick marks
        tipPercentSlider.setShowTickMarks(true);
        tipPercentSlider.setShowTickLabels(true);
        tipPercentSlider.setMajorTickUnit(5);
        tipPercentSlider.setSnapToTicks(true);

        // Disable the entering of text into tip and total amount
        tipAmountTF.setMouseTransparent(true);
        tipAmountTF.setFocusTraversable(false);

        totalAmountTF.setMouseTransparent(true);
        totalAmountTF.setFocusTraversable(false);

        // "Wire up" buttons to their respective methods
        // e.g. clearButton calls clear method when its clicked
        // Use "lambda expressions" to do this
        clearButton.setOnAction(e -> clear());
        calculateButton.setOnAction(e -> calculate());

        tipPercentSlider.valueProperty().addListener((obsVal, oldVal, newVal) -> {
            tipPercentLabel.setText(newVal.intValue() + " %");
            calculate();
                }
        );

        billAmountTF.textProperty().addListener((obsVal, oldVal, newVal) -> {
            calculate();
                }
        );


        Scene scene = new Scene(pane, 320, 240);
        stage.setTitle("Tip Calculator");
        stage.setScene(scene);
        stage.show();
    }

    private void calculate() {
        try {
            double billAmount = Double.parseDouble(billAmountTF.getText());
            double tipPercentage = (int) tipPercentSlider.getValue() / 100.0;
            double tipAmount = billAmount * tipPercentage;
            tipAmountTF.setText(currency.format(tipAmount));
            double totalAmount = billAmount + tipAmount;
            totalAmountTF.setText(currency.format(totalAmount));
        }
        catch (IllegalArgumentException e) {
            billAmountTF.clear();
            tipAmountTF.clear();
            totalAmountTF.clear();
            billAmountTF.requestFocus();
        }
    }

    private void clear(){
        // Clear the text fields
        billAmountTF.clear();   // billAmountTF.setText("");
        tipAmountTF.clear();
        totalAmountTF.clear();
        tipPercentSlider.setValue(15);
        tipPercentLabel.setText("15%");

        // Set focus back on billAmountTF
        billAmountTF.requestFocus();
    }

    public static void main(String[] args) {
        launch();
    }
}