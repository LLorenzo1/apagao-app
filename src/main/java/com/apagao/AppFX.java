package com.apagao;

import com.apagao.controller.CepController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AppFX extends Application {

    @Override
    public void start(Stage stage) {
        Label labelCep = new Label("Digite seu CEP:");
        TextField fieldCep = new TextField();

        Label labelHoras = new Label("Horas sem energia:");
        TextField fieldHoras = new TextField();

        Label labelTipo = new Label("Tipo do imóvel:");
        ComboBox<String> tipoComboBox = new ComboBox<>();
        tipoComboBox.getItems().addAll("Residencial", "Comercial");
        tipoComboBox.setValue("Residencial");

        Button buscarButton = new Button("Buscar informações");
        TextArea resultadoArea = new TextArea();
        resultadoArea.setEditable(false);
        resultadoArea.setWrapText(true);

        buscarButton.setOnAction(e -> {
            String cep = fieldCep.getText().trim();
            String horasStr = fieldHoras.getText().trim();
            String tipo = tipoComboBox.getValue();

            if (cep.isEmpty()) {
                resultadoArea.setText("⚠️ Por favor, digite um CEP.");
                return;
            }

            // Buscar informações do CEP
            CepController controller = new CepController();
            String resultado = controller.buscarCep(cep);

            // Cálculo do prejuízo
            if (!horasStr.isEmpty()) {
                try {
                    double horas = Double.parseDouble(horasStr);
                    double valorHora = tipo.equalsIgnoreCase("Comercial") ? 100.0 : 30.0;
                    double prejuizo = horas * valorHora;

                    resultado += String.format("\n💸 Prejuízo estimado (%s): R$ %.2f\n", tipo, prejuizo);
                } catch (NumberFormatException ex) {
                    resultado += "\n⚠️ Horas sem energia inválidas.\n";
                }
            }

            // Exibir resultado
            resultadoArea.setText(resultado);
        });

        VBox layout = new VBox(10, labelCep, fieldCep, labelHoras, fieldHoras, labelTipo, tipoComboBox, buscarButton, resultadoArea);
        layout.setPadding(new Insets(15));

        Scene scene = new Scene(layout, 550, 520);
        stage.setTitle("Assistente de Falta de Energia");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
