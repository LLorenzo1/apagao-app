package com.apagao.controller;

import com.apagao.model.Endereco;
import com.apagao.service.AlertaService;
import com.apagao.service.CepService;
import com.apagao.service.LocalSeguroService;

public class CepController {

    private CepService cepService;

    public CepController() {
        this.cepService = new CepService();
    }

    public String buscarCep(String cep) {
        StringBuilder resultado = new StringBuilder();

        Endereco endereco = cepService.buscarEnderecoPorCep(cep);

        if (endereco != null) {
            resultado.append("Endereço encontrado:\n");
            resultado.append(endereco).append("\n\n");

            LocalSeguroService localSeguroService = new LocalSeguroService();
            String[] coordenadas = localSeguroService.obterCoordenadas(endereco);

            if (coordenadas != null) {
                resultado.append("Coordenadas encontradas:\n");
                resultado.append("Latitude: ").append(coordenadas[0]).append("\n");
                resultado.append("Longitude: ").append(coordenadas[1]).append("\n\n");

                resultado.append("Locais seguros nas proximidades:\n");

                String hospitais = localSeguroService.buscarHospitaisProximos(coordenadas[0], coordenadas[1]);
                resultado.append(hospitais).append("\n");

                String delegacias = localSeguroService.buscarDelegaciasProximas(coordenadas[0], coordenadas[1]);
                resultado.append(delegacias).append("\n");

                String abrigos = localSeguroService.buscarAbrigosProximos(coordenadas[0], coordenadas[1]);
                resultado.append(abrigos).append("\n");

            } else {
                resultado.append("Não foi possível obter as coordenadas.\n");
            }

            AlertaService alertaService = new AlertaService();
            String alerta = alertaService.consultarAlertaPorEstado(endereco.getUf());
            resultado.append("\n🔔 Alerta da região:\n");
            resultado.append(alerta).append("\n");

        } else {
            resultado.append("Erro ao buscar o CEP: ").append(cep).append("\n");
        }

        return resultado.toString();
    }
}
