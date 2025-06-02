package com.apagao.service;

public class ImpactoFinanceiroService {

    public double calcularPrejuizo(String tipo, double horasSemEnergia) {
        double consumoPorHora;
        double tarifa;

        switch (tipo.toLowerCase()) {
            case "residencial":
                consumoPorHora = 0.8;
                tarifa = 0.70;
                break;
            case "comercial":
                consumoPorHora = 3.5;
                tarifa = 0.95;
                break;
            case "industrial":
                consumoPorHora = 10.0;
                tarifa = 0.68;
                break;
            default:
                System.out.println("Tipo não reconhecido. Usando valores residenciais como padrão.");
                consumoPorHora = 0.8;
                tarifa = 0.70;
        }

        return consumoPorHora * tarifa * horasSemEnergia;
    }
}
