package com.apagao;

import com.apagao.controller.CepController;
import com.apagao.service.ImpactoFinanceiroService;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CepController controller = new CepController();

        System.out.println("🔌 Sistema de Apoio em Falta de Energia");
        System.out.print("Digite o seu CEP: ");
        String cep = scanner.nextLine();
        controller.buscarCep(cep);

        // Impacto financeiro
        System.out.println("\n💸 Cálculo de Prejuízo Estimado:");
        System.out.print("Informe seu tipo de uso (residencial, comercial, industrial): ");
        String tipo = scanner.nextLine();

        System.out.print("Quantas horas sem energia?: ");
        double horas = scanner.nextDouble();

        ImpactoFinanceiroService impactoService = new ImpactoFinanceiroService();
        double prejuizo = impactoService.calcularPrejuizo(tipo, horas);

        System.out.printf("Prejuízo estimado: R$ %.2f%n", prejuizo);

        scanner.close();
    }
}
